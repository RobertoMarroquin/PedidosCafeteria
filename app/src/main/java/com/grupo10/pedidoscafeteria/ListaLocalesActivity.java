package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

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

public class ListaLocalesActivity extends AppCompatActivity {
    ListView listViewLocales;
    ArrayList<String> listaInfo;
    ArrayList<Local> listaLocales;
    ControlBD helper;
    private static final String[] camposLocal = new String[]{"codlocal", "codencargadolocal", "nombrelocal"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_locales);
        helper = new ControlBD(this);
        listViewLocales = (ListView) findViewById(R.id.listViewLocales);

        consultarlocales();

        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInfo);
        listViewLocales.setAdapter(adaptador);

        listViewLocales.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mensaje = "CodLocal: "+ listaLocales.get(position).getCodlocal()+"\n"+"NombreLocal: "+listaLocales.get(position).getNombrelocal();
                Toast.makeText(ListaLocalesActivity.this, mensaje, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void consultarlocales() {
        SQLiteDatabase db = helper.abrir2();
        listaLocales = new ArrayList<Local>();
        Local local = null;

        Cursor cursor = db.rawQuery("SELECT * FROM local",null);

        while (cursor.moveToNext()){
            local = new Local();
            local.setCodlocal(cursor.getString(0));
            local.setCodencargadolocal(cursor.getString(1));
            local.setNombrelocal(cursor.getString(2));
            listaLocales.add(local);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInfo = new ArrayList<String>();

        for (int i = 0; i < listaLocales.size(); i++){
            listaInfo.add(listaLocales.get(i).getCodlocal() + " - "+ listaLocales.get(i).getNombrelocal());
            Log.d("index", "obtenerLista: "+listaLocales.get(i).getNombrelocal());
        }
    }
}
