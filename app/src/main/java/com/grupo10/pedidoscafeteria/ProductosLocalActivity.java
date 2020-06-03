package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ProductosLocalActivity extends AppCompatActivity {
    ControlBD helper;
    ListView listViewProductos;
    ArrayList<String> listaInfo;
    ArrayList<Producto> listaProducto;
    String localId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_local);
        Bundle paqueteR = getIntent().getExtras();
        if (paqueteR != null) localId = paqueteR.getString("codlocal");
        else localId = "local1";
        Log.d("paquete", "onCreate: "+localId);
        helper = new ControlBD(this);
        listViewProductos = (ListView) findViewById(R.id.listViewProductos);

        consultarProductosLocal();

        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInfo);
        listViewProductos.setAdapter(adaptador);

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
