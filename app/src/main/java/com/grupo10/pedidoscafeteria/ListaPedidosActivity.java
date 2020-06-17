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
    ArrayList<Local> listaPedidos;
    ControlBD helper;
    Usuario user;
    Bundle paquete;
    private static final String[] camposPedido = new String[]{"codlocal", "codencargadolocal", "nombrelocal"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos);
    }
}
