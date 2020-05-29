package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FacultadMenuActivity extends AppCompatActivity {
    Button ingresarFacultad, consultarFacultad, actualizarFacultad, eliminarFacultad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultad_menu);
        ingresarFacultad = (Button) findViewById(R.id.btnIngresarFacultad);
        consultarFacultad = (Button) findViewById(R.id.btnConsultarFacultad);
        actualizarFacultad = (Button) findViewById(R.id.btnActualizarFacultad);
        eliminarFacultad = (Button) findViewById(R.id.btnEliminarFacultad);

        //AL DARLE CLICK AL BOTON DE INGRESAR FACULTAD
        ingresarFacultad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingresarFacultad = new Intent(v.getContext(), InsertarFacultadActivity.class);
                startActivity(ingresarFacultad);
            }
        });

        //AL DARLE CLICK AL BOTON DE CONSULTAR FACULTAD
        consultarFacultad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent consultarFacultad = new Intent(v.getContext(), ConsultarFacultadActivity.class);
                startActivity(consultarFacultad);
            }
        });

        //AL DARLE CLICK AL BOTON DE ACTUALIZAR FACULTAD
        actualizarFacultad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actualizarFacultad = new Intent(v.getContext(), ActualizarFacultadActivity.class);
                startActivity(actualizarFacultad);
            }
        });

        //AL DARLE CLICK AL BOTON DE ELIMINAR FACULTAD
        eliminarFacultad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eliminarFacultad = new Intent(v.getContext(), EliminarFacultadActivity.class);
                startActivity(eliminarFacultad);
            }
        });
    }
}
