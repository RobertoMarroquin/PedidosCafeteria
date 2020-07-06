package com.grupo10.pedidoscafeteria;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ListaPedidosProductoActivity extends AppCompatActivity {
    ListView listViewPedidosProducto;
    ArrayList<String> listaInfo;
    ArrayList<DetalleProductoEmpleado> listaPedidosProducto;
    ControlBD helper;
    Usuario user;
    Integer IdPedido;
    Bundle paquete;
    private static final String[] camposPedido = new String[]{"IdPedido", "codtrabajador", "codproducto","cantidadpedido","nombreproducto","preciounitario"};

    Button pdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos_producto);
        helper = new ControlBD(this);

        pdf = (Button) findViewById(R.id.pdf);

        listViewPedidosProducto = (ListView) findViewById(R.id.lstPedidosProducto);
        paquete = getIntent().getExtras();
        if (paquete != null) {
            user = (Usuario) paquete.getSerializable("usuario");
            IdPedido = (Integer) paquete.getSerializable("idpedido");
        }
        else {
            user = null;
            IdPedido= null;
        }
        consultarPedidosProducto();

        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInfo);
        listViewPedidosProducto.setAdapter(adaptador);

        listViewPedidosProducto.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent,View view,int position,long id) {
                SQLiteDatabase db = helper.abrir2();
                Integer IdPed;
                String CodProducto;
                IdPed=listaPedidosProducto.get(position).getIdpedido();
                CodProducto=listaPedidosProducto.get(position).getIdproducto();
                String[] argumentos = {IdPed.toString(),CodProducto};

                db.execSQL("DELETE FROM detalleproductoempleado where idpedido =? AND codproducto=? ",argumentos);
                Toast.makeText(ListaPedidosProductoActivity.this, "Eliminado Producto No.: "+CodProducto, Toast.LENGTH_SHORT).show();
                finish();
                return false;
            }
        });


        pdf.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                int size = listaInfo.size();
                String cadena ="";
                for (int i=0; i<size; i++){
                    String numero = "PRODUCTO NUMERO " + (i+1) + " ";
                    String aux = listaInfo.get(i);
                    String espacio = "\n";
                    cadena=cadena.concat(numero);
                    cadena=cadena.concat(espacio);
                    cadena=cadena.concat(aux);
                    cadena=cadena.concat(espacio);

                }

                //en cadena ya esta el string a imprimir en pdf


                createPdf(cadena);
                //Toast.makeText(getApplicationContext(), cadena, Toast.LENGTH_LONG).show();

            }
        });


    }

    private void consultarPedidosProducto() {
        SQLiteDatabase db = helper.abrir2();
        listaPedidosProducto = new ArrayList<DetalleProductoEmpleado>();
        DetalleProductoEmpleado detalle = null;
        listaInfo = new ArrayList<String>();

        String [] argumentos = {IdPedido.toString(),};
        Cursor cursor = db.rawQuery("Select p.idpedido,d.codproducto, pro.nombreproducto,d.cantidadpedido,pro.preciounitario from pedido p " +
                "inner join pedidosasignados s on p.idpedido = s.idpedido " +
                "inner join detalleproductoempleado d on p.idpedido = d.idpedido " +
                "inner join producto pro on d.codproducto = pro.codproducto " +
                "inner join estadopedido es on p.codestadopedido = es.codestadopedido " +
                "where p.idpedido =? ",argumentos);

        while (cursor.moveToNext()){
            detalle = new DetalleProductoEmpleado();
            detalle.setIdpedido(cursor.getInt(0));
            detalle.setIdproducto(cursor.getString(1));
            listaPedidosProducto.add(detalle);
            listaInfo.add("Producto: "+ cursor.getString(1) + " - "+ cursor.getString(2) +"\n"+ "Cantidad : "+ cursor.getString(3)+"      Precio: "+cursor.getString(4) );
        }
    }

    //
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void createPdf(String cadena){
        // criando o documento novo
        PdfDocument document = new PdfDocument();

        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();

        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        //Paint paint = new Paint();
        //paint.setColor(Color.RED);
        //canvas.drawCircle(50, 50, 10, paint);
        //paint.setColor(Color.BLACK);
        //canvas.drawText(cadena, 80, 50, paint);

        //
        //2
        //String text = "This is some text.";


        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(2*getResources().getDisplayMetrics().density);
        textPaint.setColor(0xFF000000);

        int width = (int) textPaint.measureText(cadena);
        StaticLayout staticLayout = new StaticLayout(cadena, textPaint, (int) width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0, false);
        staticLayout.draw(canvas);





        //
        document.finishPage(page);



        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/";
        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String targetPdf = directory_path+"prueba.pdf";
        File filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(this, "Se guardo correctamente", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Log.e("main", "error "+e.toString());
            Toast.makeText(this, "No se pudo exportar el pdf: " + e.toString(),  Toast.LENGTH_LONG).show();
        }

        document.close();
    }


}
