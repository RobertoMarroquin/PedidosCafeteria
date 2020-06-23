package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

public class ListaPedidosLocalActivity extends AppCompatActivity {
    ListView listViewPedidoslocal;
    ArrayList<String> listaInfo;
    ArrayList<Pedido> listaPedidoslocal;
    ControlBD helper;
    Usuario user;
    String localId;
    Bundle paquete;
    private static final String[] camposPedido = new String[]{"IdPedido", "fechapedido", "codestadopedido","codfacultad","nomempleado","apeempleado","telempleado"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos_local);
        helper = new ControlBD(this);
        listViewPedidoslocal = (ListView) findViewById(R.id.lstPedidoslocal);
        paquete = getIntent().getExtras();
        if (paquete != null) {
            localId = paquete.getString("codlocal");
            user = (Usuario) paquete.getSerializable("usuario");
        } else {
            user = null;
            localId = "local1";
        }
        consultarPedidos();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInfo);
        listViewPedidoslocal.setAdapter(adaptador);
    }

    private void consultarPedidos() {
        SQLiteDatabase db = helper.abrir2();
        listaPedidoslocal = new ArrayList<Pedido>();
        Pedido pedido = null;
        listaInfo = new ArrayList<String>();



        String[] argumentos = {localId};
        Cursor cursor = db.rawQuery("Select p.idpedido,p.fechapedido,e.nomempleado,e.apeempleado,e.telempleado,u.descubicacion,rp.Idruta,r.nomrepartidor,r.telrepartidor, es.descestadopedido from pedido p " +
                "inner join pedidosasignados s on p.idpedido = s.idpedido " +
                "inner join empleado e on s.codtrabajador = e.codempleado " +
                "inner join ubicacion u on e.codubicacion = u.codubicacion " +
                "inner join rutapedido rp on p.idruta = rp.idruta " +
                "inner join repartidor r on rp.codrepartidor = r.codrepartidor " +
                "inner join estadopedido es on p.codestadopedido = es.codestadopedido " +
                "where p.codlocal =? ",argumentos);

        while (cursor.moveToNext()){
            pedido = new Pedido();
            pedido.setIdpedido(cursor.getInt(0));
            listaPedidoslocal.add(pedido);
            listaInfo.add("Pedido: "+ cursor.getString(0) + "  Fecha: "+ cursor.getString(1)+"\n" + "Empleado :"+ cursor.getString(2)+
                    "  Tel: "+cursor.getString(4)+"\n"+"Entrega: "+cursor.getString(5)+
                    "\n"+"Repartidor: "+cursor.getString(7) +
                    "  Tel: "+cursor.getString(8)+"\n"+"Estado : "+cursor.getString(9) );
        }
    }
}
