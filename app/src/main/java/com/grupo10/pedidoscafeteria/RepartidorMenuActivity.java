package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RepartidorMenuActivity extends AppCompatActivity {
    Button btnInsertarRepartidor, btnActualizarRepartidor, btnConsultarRepartidor, btnEliminarRepartidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repartidor_menu);
        btnInsertarRepartidor = (Button) findViewById(R.id.btnIngresarRepartidor);
        btnActualizarRepartidor = (Button) findViewById(R.id.btnActualizarRepartidor);
        btnConsultarRepartidor = (Button) findViewById(R.id.btnConsultarRepartidor);
        btnEliminarRepartidor = (Button) findViewById(R.id.btnEliminarRepartidor);

        btnInsertarRepartidor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingresarRepartidor = new Intent(v.getContext(), InsertarRepartidorActivity.class);
                startActivity(ingresarRepartidor);
            }
        });

        btnActualizarRepartidor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actualizarRepartidor = new Intent(v.getContext(), ActualizarRepartidorActivity.class);
                startActivity(actualizarRepartidor);
            }
        });

        btnConsultarRepartidor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent consultarRepartidor = new Intent(v.getContext(), ConsultarRepartidorActivity.class);
                startActivity(consultarRepartidor);
            }
        });

        btnEliminarRepartidor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eliminarRepartidor = new Intent(v.getContext(), EliminarRepartidorActivity.class);
                startActivity(eliminarRepartidor);
            }
        });
    }
}