package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UbicacionMenuActivity extends AppCompatActivity {
    Button btnIngresarUbicacion, btnConsultarUbicacion, btnActualizarUbicacion, btnEliminarUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion_menu);
        btnIngresarUbicacion = (Button) findViewById(R.id.btnIngresarUbicacion);
        btnConsultarUbicacion = (Button) findViewById(R.id.btnConsultarUbicacion);
        btnActualizarUbicacion = (Button) findViewById(R.id.btnActualizarUbicacion);
        btnEliminarUbicacion = (Button) findViewById(R.id.btnEliminarUbicacion);

        btnIngresarUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingresarUbicacion = new Intent(v.getContext(), InsertarUbicacionActivity.class);
                startActivity(ingresarUbicacion);
            }
        });

        btnConsultarUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent consultarUbicacion = new Intent(v.getContext(), ConsultarUbicacionActivity.class);
                startActivity(consultarUbicacion);

            }
        });

        btnActualizarUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actualizarUbicacion = new Intent(v.getContext(), ActualizarUbicacionActivity.class);
                startActivity(actualizarUbicacion);
            }
        });

        btnEliminarUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eliminarUbicacion = new Intent(v.getContext(), EliminarUbicacionActivity.class);
                startActivity(eliminarUbicacion);
            }
        });


    }
}
