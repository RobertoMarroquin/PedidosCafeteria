package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LocalMenuActivity extends AppCompatActivity {
    Button btnIngresarLocal, btnActualizarLocal, btnConsultarLocal, btnEliminarLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_menu);
        btnIngresarLocal = (Button) findViewById(R.id.btnIngresarLocal);
        btnActualizarLocal = (Button) findViewById(R.id.btnActualizarLocal);
        btnConsultarLocal = (Button) findViewById(R.id.btnConsultarLocal);
        btnEliminarLocal = (Button) findViewById(R.id.btnEliminarLocal);

        btnIngresarLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insertarLocal = new Intent(v.getContext(), InsertarLocalActivity.class);
                startActivity(insertarLocal);
            }
        });

        btnActualizarLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnConsultarLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnEliminarLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
