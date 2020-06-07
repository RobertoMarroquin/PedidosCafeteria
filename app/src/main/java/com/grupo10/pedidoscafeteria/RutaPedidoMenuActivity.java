package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RutaPedidoMenuActivity extends AppCompatActivity {
    Button btnIngresarRuta, btnActualizarRuta, btnConsultarRuta, btnEliminarRuta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruta_pedido_menu);

        btnIngresarRuta = (Button) findViewById(R.id.btnIngresarRuta);
        btnActualizarRuta = (Button) findViewById(R.id.btnActualizarRuta);
        btnConsultarRuta = (Button) findViewById(R.id.btnConsultarRuta);
        btnEliminarRuta = (Button) findViewById(R.id.btnEliminarRuta);

        btnIngresarRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingresarRuta = new Intent(v.getContext(), InsertarRutaPedidoActivity.class);
                startActivity(ingresarRuta);
            }
        });

        btnActualizarRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actualizarRuta = new Intent(v.getContext(), ActualizarRutaPedidoActivity.class);
                startActivity(actualizarRuta);
            }
        });

        btnConsultarRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent consultarRuta = new Intent(v.getContext(), ConsultarRutaPedidoActivity.class);
                startActivity(consultarRuta);
            }
        });

        btnEliminarRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eliminarRuta = new Intent(v.getContext(), EliminarRutaPedidoActivity.class);
                startActivity(eliminarRuta);
            }
        });
    }
}