package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProductoMenuActivity extends AppCompatActivity {
    Button btnIngresarProducto, btnActualizarProducto, btnConsultarProducto, btnEliminarProducto;

    Button btnTablaCambioPrecios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_menu);
        btnIngresarProducto = (Button) findViewById(R.id.btnIngresarProducto);
        btnActualizarProducto = (Button) findViewById(R.id.btnActualizarProducto);
        btnConsultarProducto = (Button) findViewById(R.id.btnConsultarProducto);
        btnEliminarProducto = (Button) findViewById(R.id.btnEliminarProducto);

        btnTablaCambioPrecios = (Button) findViewById(R.id.btnCambioPrecios);


        btnIngresarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingresarProducto = new Intent(v.getContext(), InsertarProductoActivity.class);
                startActivity(ingresarProducto);
            }
        });

        btnConsultarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent consultarProducto = new Intent(v.getContext(), ConsultarProductoActivity.class);
                startActivity(consultarProducto);
            }
        });

        btnActualizarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actualizarProducto = new Intent(v.getContext(), ActualizarProductoActivity.class);
                startActivity(actualizarProducto);
            }
        });

        btnEliminarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eliminarProducto = new Intent(v.getContext(), EliminarProductoActivity.class);
                startActivity(eliminarProducto);
            }
        });



        //el boton de cambio precios
        btnTablaCambioPrecios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
