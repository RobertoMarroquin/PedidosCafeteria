package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EncargadoLocalMenuActivity extends AppCompatActivity {
    Button btnEncargadoLocal, btnLocal, btnMenu, btnProducto, btnPedido;
    Button btnRepartidor, btnRuta;

    Button btnCerrarSesion;

    Usuario user;

    Bundle objetosRecibidos;

    ControlBD helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargado_local_menu);
        btnEncargadoLocal = (Button) findViewById(R.id.btnEncargadoLocal);
        btnLocal = (Button) findViewById(R.id.btnLocales);
        btnMenu = (Button) findViewById(R.id.btnMenus);
        btnProducto = (Button) findViewById(R.id.btnProducto);
        btnPedido = (Button) findViewById(R.id.btnPedido);
        btnRepartidor = (Button) findViewById(R.id.btnRepartidor);
        btnRuta = (Button) findViewById(R.id.btnRutas);
        btnCerrarSesion = (Button) findViewById(R.id.btnCerrarSesion);
        helper = new ControlBD(this);

        //en el bundle recien creado se colocan las extras que trae del menu anterior
        objetosRecibidos = getIntent().getExtras();

        //se asigna a un objeto usuario los datos del bundle ========================
        user = (Usuario) objetosRecibidos.getSerializable("usuario");
        //no usado en esta pantalla =================================================


        btnEncargadoLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crudEncargadoLocal = new Intent(v.getContext(), DatosEncargadoLocalMenuActivity.class);
                crudEncargadoLocal.putExtras(objetosRecibidos);
                startActivity(crudEncargadoLocal);
            }
        });

        btnLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crudLocal = new Intent(v.getContext(), LocalMenuActivity.class);
                crudLocal.putExtras(objetosRecibidos);
                startActivity(crudLocal);
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crudMenu = new Intent(v.getContext(), MenuMenuActivity.class);
                startActivity(crudMenu);
            }
        });

        btnProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crudProducto = new Intent(v.getContext(), ProductoMenuActivity.class);
                crudProducto.putExtras(objetosRecibidos);
                startActivity(crudProducto);
            }
        });

        btnRepartidor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crudRepartidor = new Intent(v.getContext(), RepartidorMenuActivity.class);
                startActivity(crudRepartidor);
            }
        });

        btnRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crudRuta = new Intent(v.getContext(), RutaPedidoMenuActivity.class);
                startActivity(crudRuta);
            }
        });

        btnPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listaLocalesEcargado = new Intent(v.getContext(),ListaLocalesEncargadoActivity.class);
                listaLocalesEcargado.putExtras(objetosRecibidos);
                if (estaVacio(user)){
                    startActivity(listaLocalesEcargado);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Para gestionar los pedidos, completa la información de tu perfil!", Toast.LENGTH_LONG).show();
                }


            }
        });

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cerrarSesion = new Intent(v.getContext(), MainActivity.class);
                startActivity(cerrarSesion);
            }
        });
    }

    public boolean estaVacio(Usuario usuario){
        String[] id = {usuario.getNombreusuario()};
        SQLiteDatabase db = helper.abrir2();
        Cursor c = db.rawQuery("select codencargadolocal, nomencargadolocal, apeencargadolocal, telencargadolocal from encargadolocal where codencargadolocal = ?", id);
        if (c.moveToFirst()){
            return true;
        }
        else {
            return false;
        }
    }
}
