package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DatosEncargadoLocalMenuActivity extends AppCompatActivity {
    Button btnIngresarEncargadoLocal, btnActualizarEncargadoLocal, btnConsultarEncargadoLocal;
    Button btnELiminarEncargadoLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_encargado_local_menu);
        btnIngresarEncargadoLocal = (Button) findViewById(R.id.btnIngresarEncargadoLocal);
        btnActualizarEncargadoLocal = (Button) findViewById(R.id.btnActualizarEncargadoLocal);
        btnConsultarEncargadoLocal = (Button) findViewById(R.id.btnConsultarEncargadoLocal);
        btnELiminarEncargadoLocal = (Button) findViewById(R.id.btnEliminarEncargadoLocal);

        btnIngresarEncargadoLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingresarEncargadoLocal = new Intent(v.getContext(), InsertarEncargadoLocalActivity.class);
                startActivity(ingresarEncargadoLocal);
            }
        });

        btnActualizarEncargadoLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actualizarEncargoLocal = new Intent(v.getContext(), ActualizarEncargadoLocalActivity.class);
                startActivity(actualizarEncargoLocal);
            }
        });

        btnConsultarEncargadoLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent consultarEncargadoLocal = new Intent(v.getContext(), ConsultarEncargadoLocalActivity.class);
                startActivity(consultarEncargadoLocal);
            }
        });

        btnELiminarEncargadoLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eliminarEncargadoLocal = new Intent(v.getContext(), EliminarEncargadoLocalActivity.class);
                startActivity(eliminarEncargadoLocal);
            }
        });

    }
}
