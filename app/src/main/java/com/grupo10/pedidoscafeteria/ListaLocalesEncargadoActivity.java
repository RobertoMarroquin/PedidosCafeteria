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

public class ListaLocalesEncargadoActivity extends AppCompatActivity {
    ListView listViewLocalesEncargado;
    ArrayList<String> listaInfo;
    ArrayList<Local> listaLocalesEncargado;
    ControlBD helper;
    Usuario user;
    Bundle paquete;
    private static final String[] camposLocal = new String[]{"codlocal", "codencargadolocal", "nombrelocal"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_locales_encargado);
        helper = new ControlBD(this);
        listViewLocalesEncargado = (ListView) findViewById(R.id.listViewLocalesEncargado);
        paquete = getIntent().getExtras();
        consultarlocales();

        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInfo);
        listViewLocalesEncargado.setAdapter(adaptador);

        listViewLocalesEncargado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                user = (Usuario) paquete.getSerializable("usuario");
                paquete.putString("codlocal", listaLocalesEncargado.get(position).getCodlocal());
                Intent pedidoslocal = new Intent(parent.getContext(), ListaPedidosLocalActivity.class);
                pedidoslocal.putExtras(paquete);
                Log.d("Paquete enviado", "onItemClick: "+pedidoslocal.getExtras().getString("codlocal"));
                Log.d("Paquete enviado", "onItemClick: "+user.getNombreusuario());
                startActivity(pedidoslocal);
            }
        });

    }
    private void consultarlocales() {
        SQLiteDatabase db = helper.abrir2();
        listaLocalesEncargado = new ArrayList<Local>();
        Local local = null;

        Cursor cursor = db.rawQuery("SELECT * FROM local",null);

        while (cursor.moveToNext()){
            local = new Local();
            local.setCodlocal(cursor.getString(0));
            local.setCodencargadolocal(cursor.getString(1));
            local.setNombrelocal(cursor.getString(2));
            listaLocalesEncargado.add(local);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInfo = new ArrayList<String>();

        for (int i = 0; i < listaLocalesEncargado.size(); i++){
            listaInfo.add(listaLocalesEncargado.get(i).getCodlocal() + " - "+ listaLocalesEncargado.get(i).getNombrelocal());
        }
    }

}
