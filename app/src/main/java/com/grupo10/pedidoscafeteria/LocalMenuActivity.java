package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LocalMenuActivity extends AppCompatActivity {
    Button btnIngresarLocal, btnActualizarLocal, btnConsultarLocal, btnEliminarLocal;

    Usuario user;

    Bundle objetosRecibidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_menu);
        btnIngresarLocal = (Button) findViewById(R.id.btnIngresarLocal);
        btnActualizarLocal = (Button) findViewById(R.id.btnActualizarLocal);
        btnConsultarLocal = (Button) findViewById(R.id.btnConsultarLocal);
        btnEliminarLocal = (Button) findViewById(R.id.btnEliminarLocal);

        //en el bundle recien creado se colocan las extras que trae del menu anterior
        objetosRecibidos = getIntent().getExtras();

        //se asigna a un objeto usuario los datos del bundle ========================
        user = (Usuario) objetosRecibidos.getSerializable("usuario");


        btnIngresarLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insertarLocal = new Intent(v.getContext(), InsertarLocalActivity.class);
                insertarLocal.putExtras(objetosRecibidos);
                startActivity(insertarLocal);
            }
        });

        btnActualizarLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actualizarLocal = new Intent(v.getContext(), ActualizarLocalActivity.class);
                actualizarLocal.putExtras(objetosRecibidos);
                startActivity(actualizarLocal);

            }
        });

        btnConsultarLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent consultarLocal = new Intent(v.getContext(), ConsultarLocalActivity.class);
                consultarLocal.putExtras(objetosRecibidos);
                startActivity(consultarLocal);

            }
        });

        btnEliminarLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eliminarLocal = new Intent(v.getContext(), EliminarLocalActivity.class);
                eliminarLocal.putExtras(objetosRecibidos);
                startActivity(eliminarLocal);
            }
        });
    }
}
