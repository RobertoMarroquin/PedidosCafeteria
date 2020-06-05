package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;

public class DetalleProductoActivity extends AppCompatActivity {
    Bundle paquete;
    Usuario user;
    String codP;
    Producto producto;
    Button BtnanadirP;
    EditText cantProducto;
    TextView TVnomproducto, TVprecioUnidad;
    ControlBD helper;
    Pedido pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        paquete = getIntent().getExtras();
        user = (Usuario) paquete.getSerializable("usuario");
        Log.d("Detalle  Producto", "onCreate: "+user.getNombreusuario());
        codP = paquete.getString("codproducto");
        Log.d("Codigo", "onCreate: "+codP);
        helper = new ControlBD(this);
        consultarProducto();
        TVnomproducto = (TextView) findViewById(R.id.TVnomproducto);
        TVnomproducto.setText("" + producto.getNombreproducto());
        TVprecioUnidad = (TextView) findViewById(R.id.TVprecioUnidad);
        TVprecioUnidad.setText("Precio Unitario: $"+String.format("%.2f",producto.getPreciounitario()));
        cantProducto = (EditText) findViewById(R.id.cantProducto);
        BtnanadirP = (Button) findViewById(R.id.BtnanadirP);


        BtnanadirP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresoListaLocales = new Intent(v.getContext(),ProductosLocalActivity.class);
                if (paquete.getInt("idPedido",0)==0)paquete.putInt("idPedido",registrarpedido());
                Log.d("pedido", "idPedido: "+paquete.getInt("idPedido"));
                regresoListaLocales.putExtras(paquete);
                Toast.makeText(DetalleProductoActivity.this, "Ordenar: "+cantProducto.getText().toString()+" "+
                        producto.getNombreproducto()+"(s)" +
                        "idPedido"+paquete.getInt("idPedido"), Toast.LENGTH_SHORT).show();
                startActivity(regresoListaLocales);

                }
            });

    }

    private int registrarpedido() {
            SQLiteDatabase db =  helper.abrir2();
            String today = Calendar.getInstance().getTime().toString();
            //paquete.getString("codlocal") today
            long idpedido;
            ContentValues ped = new ContentValues();
            ped.put("codlocal", paquete.getString("codlocal"));
            Log.d("Local", "registrarpedido: "+paquete.getString("codlocal"));
            ped.put("fechapedido", today);
            idpedido = db.insert("pedido", null, ped);
            Log.d("id Pedido", "registrarpedido: "+idpedido);
            String[] argumentos = {"" + idpedido,};
            String[] campos = {"idpedido", "codlocal", "fechapedido"};
            Cursor cursor = db.query("pedido", campos, "idpedido=?", argumentos, null, null, null);
            cursor.moveToLast();
            pedido = new Pedido();
            pedido.setIdpedido(cursor.getInt(0));
            Log.d("pedido idpedido", "registrarpedido: "+pedido.getIdpedido());
            pedido.setCodlocal(cursor.getString(1));
            pedido.setFechapedido(cursor.getString(2));

            return pedido.getIdpedido();
    }

    private void consultarProducto() {
        SQLiteDatabase db = helper.abrir2();

        String[] prod = {codP,};
        Cursor cursor = db.rawQuery("select codproducto, codmenu, nombreproducto, preciounitario " +
                "from producto " +
                "where codproducto = ?",prod);

        while (cursor.moveToNext()){
            producto = new Producto();
            producto.setCodproducto(cursor.getString(0));
            producto.setCodmenu(cursor.getString(1));
            producto.setNombreproducto(cursor.getString(2));
            producto.setPreciounitario(cursor.getFloat(3));
        }
    }
}
