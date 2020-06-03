package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuMenuActivity extends AppCompatActivity {
    Button btnIngresarMenu, btnConsultarMenu, btnActualizarMenu, btnEliminarMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_menu);
        btnIngresarMenu = (Button) findViewById(R.id.btnIngresarMenu);
        btnActualizarMenu = (Button) findViewById(R.id.btnActualizarMenu);
        btnConsultarMenu = (Button) findViewById(R.id.btnConsultarMenu);
        btnEliminarMenu = (Button) findViewById(R.id.btnEliminarMenu);


        btnIngresarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insertarMenu = new Intent(v.getContext(), InsertarMenuActivity.class);
                startActivity(insertarMenu);
            }
        });


        btnConsultarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent consultarMenu = new Intent(v.getContext(), ConsultarMenuActivity.class);
                startActivity(consultarMenu);
            }
        });


        btnActualizarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actualizarMenu = new Intent(v.getContext(), ActualizarMenuActivity.class);
                startActivity(actualizarMenu);
            }
        });


        btnEliminarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eliminarMenu = new Intent(v.getContext(), EliminarMenuActivity.class);
                startActivity(eliminarMenu);
            }
        });
    }
}
