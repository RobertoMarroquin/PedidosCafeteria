package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuMenuActivity extends AppCompatActivity {
    Button btnIngresarMenu, btnConsultarMenu, btnActualizarMenu, btnEliminarMenu;

    Bundle objetosRecibidos;

    ControlBD helper;

    Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_menu);
        btnIngresarMenu = (Button) findViewById(R.id.btnIngresarMenu);
        btnActualizarMenu = (Button) findViewById(R.id.btnActualizarMenu);
        btnConsultarMenu = (Button) findViewById(R.id.btnConsultarMenu);
        btnEliminarMenu = (Button) findViewById(R.id.btnEliminarMenu);

        //en el bundle recien creado se colocan las extras que trae del menu anterior
        objetosRecibidos = getIntent().getExtras();

        //se asigna a un objeto usuario los datos del bundle ========================
        user = (Usuario) objetosRecibidos.getSerializable("usuario");
        //no usado en esta pantalla =================================================


        btnIngresarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insertarMenu = new Intent(v.getContext(), InsertarMenuActivity.class);
                insertarMenu.putExtras(objetosRecibidos);
                startActivity(insertarMenu);
            }
        });


        btnConsultarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent consultarMenu = new Intent(v.getContext(), ConsultarMenuActivity.class);
                consultarMenu.putExtras(objetosRecibidos);
                startActivity(consultarMenu);
            }
        });


        btnActualizarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actualizarMenu = new Intent(v.getContext(), ActualizarMenuActivity.class);
                actualizarMenu.putExtras(objetosRecibidos);
                startActivity(actualizarMenu);
            }
        });


        btnEliminarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eliminarMenu = new Intent(v.getContext(), EliminarMenuActivity.class);
                eliminarMenu.putExtras(objetosRecibidos);
                startActivity(eliminarMenu);
            }
        });
    }
}
