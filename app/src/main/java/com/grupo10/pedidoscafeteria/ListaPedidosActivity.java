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

public class ListaPedidosActivity extends AppCompatActivity {
    ListView listViewPedidos;
    ArrayList<String> listaInfo;
    ArrayList<Pedido> listaPedidos;
    ControlBD helper;
    Usuario user;
    Bundle paquete;
    private static final String[] camposPedido = new String[]{"IdPedido", "fechapedido", "codestadopedido","codfacultad","nomempleado","apeempleado","telempleado"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos);
        helper = new ControlBD(this);
        listViewPedidos = (ListView) findViewById(R.id.lstPedidos);
        paquete = getIntent().getExtras();
        if (paquete != null) {
            user = (Usuario) paquete.getSerializable("usuario");
        }
        else {
            user = null;
        }
        consultarPedidos();

        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInfo);
        listViewPedidos.setAdapter(adaptador);

        listViewPedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                user = (Usuario) paquete.getSerializable("usuario");
                paquete.putInt("idpedido", listaPedidos.get(position).getIdpedido());
                Intent pedidosproducto = new Intent(parent.getContext(), ListaPedidosProductoActivity.class);
                pedidosproducto.putExtras(paquete);
                Log.d("Paquete enviado", "onItemClick: "+pedidosproducto.getExtras().getInt("idpedido"));
                Log.d("Paquete enviado", "onItemClick: "+user.getNombreusuario());
                startActivity(pedidosproducto);
            }
        });

    }
    private void consultarPedidos() {
        SQLiteDatabase db = helper.abrir2();
        listaPedidos = new ArrayList<Pedido>();
        Pedido pedido = null;
        listaInfo = new ArrayList<String>();

        String[] argumentos = {user.getNombreusuario(),};
        Cursor cursor = db.rawQuery("Select p.idpedido,p.fechapedido, es.descestadopedido from pedido p " +
                "inner join pedidosasignados s on p.idpedido = s.idpedido " +
                "inner join empleado e on s.codtrabajador = e.codempleado " +
                "inner join estadopedido es on p.codestadopedido = es.codestadopedido " +
                "where e.codempleado =? ",argumentos);

        while (cursor.moveToNext()){
            pedido = new Pedido();
            pedido.setIdpedido(cursor.getInt(0));
            listaPedidos.add(pedido);
            listaInfo.add(cursor.getString(0) + " - "+ cursor.getString(1) + " - "+ cursor.getString(2) );
        }
    }

}
