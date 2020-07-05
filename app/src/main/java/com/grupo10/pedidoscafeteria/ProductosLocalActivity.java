package com.grupo10.pedidoscafeteria;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ProductosLocalActivity extends AppCompatActivity {
    ControlBD helper;
    ListView listViewProductos;
    Button cancelarbtn,comprarbtn;
    ArrayList<String> listaInfo;
    ArrayList<Producto> listaProducto;
    String localId;
    Usuario user;
    Bundle paqueteR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_local);
        paqueteR = getIntent().getExtras();
        if (paqueteR != null) {
            localId = paqueteR.getString("codlocal");
            user = (Usuario) paqueteR.getSerializable("usuario");
        }
        else {
            localId = "local1";
        }
        Log.d("paquete", "onCreate: "+localId);
        helper = new ControlBD(this);
        listViewProductos = (ListView) findViewById(R.id.listViewProductos);
        cancelarbtn = (Button) findViewById(R.id.cancelarbtn);
        comprarbtn = (Button) findViewById(R.id.comprarbtn);

        consultarProductosLocal();

        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInfo);
        listViewProductos.setAdapter(adaptador);

        listViewProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                user = (Usuario) paqueteR.getSerializable("usuario");
                paqueteR.putString("codproducto", listaProducto.get(position).getCodproducto());
                Intent detalleProducto = new Intent(parent.getContext(), DetalleProductoActivity.class);
                detalleProducto.putExtras(paqueteR);
                Log.d("Paquete enviado", "onItemClick: "+detalleProducto.getExtras().getString("codproducto"));
                startActivity(detalleProducto);

            }
        });



        cancelarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paqueteR.getInt("idPedido",0)!=0){
                    int pedido = paqueteR.getInt("idPedido");

                    SQLiteDatabase db = helper.abrir2();
                    String[] argumentos = {String.valueOf(pedido),};
                    int cursor = db.delete("pedido","idpedido = ?",argumentos);
                    paqueteR.remove("idPedido");
                    Intent intent = new Intent(v.getContext(), ListaLocalesActivity.class);
                    intent.putExtras(paqueteR);
                    startActivity(intent);
                    finish();
                }
            }
        });

        comprarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paqueteR.getInt("idPedido",0)!=0){
                    int pedido = paqueteR.getInt("idPedido");
                    SQLiteDatabase db = helper.abrir2();
                    ContentValues values = new ContentValues();
                    values.put("codestadopedido","CO");
                    String[] argumentos = {String.valueOf(pedido),};
                    int cursor = db.update("pedido",values,"idpedido = ?",argumentos);
                    Intent intent = new Intent(v.getContext(), ListaLocalesActivity.class);
                    intent.putExtra("usuario",user);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    @Override
    public void onBackPressed()
    {

        Intent intent = new Intent(this, ListaLocalesActivity.class);
        intent.putExtra("usuario",user);
        startActivity(intent);
        finish();
    }

    private void consultarProductosLocal() {
        SQLiteDatabase db = helper.abrir2();
        listaProducto = new ArrayList<Producto>();
        Producto producto = null;

        String[] argumentos = {localId,};
        Cursor cursor = db.rawQuery("Select codproducto, nombreproducto, preciounitario, producto.codmenu from producto " +
                "inner join menu on menu.codmenu = producto.codmenu " +
                "where menu.codlocal =? ",argumentos);

        while (cursor.moveToNext()){
            producto = new Producto();
            producto.setCodproducto(cursor.getString(0));
            producto.setNombreproducto(cursor.getString(1));
            producto.setPreciounitario(cursor.getFloat(2));
            producto.setCodmenu(cursor.getString(3));

            listaProducto.add(producto);

        }
        obtenerlista();
        cursor.close();
    }

    private void obtenerlista() {
        listaInfo = new ArrayList<String>();
        for (int i = 0; i < listaProducto.size(); i++){
            listaInfo.add(listaProducto.get(i).getCodproducto() + " - "+ listaProducto.get(i).getNombreproducto());
        }
    }
}
