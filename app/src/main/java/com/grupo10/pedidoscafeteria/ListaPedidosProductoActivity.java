package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos_producto);
        helper = new ControlBD(this);
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

}
