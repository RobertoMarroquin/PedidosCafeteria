package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProductoMenuActivity extends AppCompatActivity {
    Button btnIngresarProducto, btnActualizarProducto, btnConsultarProducto, btnEliminarProducto;

    Button btnTablaCambioPrecios;

    Usuario user;

    Bundle objetosRecibidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_menu);
        btnIngresarProducto = (Button) findViewById(R.id.btnIngresarProducto);
        btnActualizarProducto = (Button) findViewById(R.id.btnActualizarProducto);
        btnConsultarProducto = (Button) findViewById(R.id.btnConsultarProducto);
        btnEliminarProducto = (Button) findViewById(R.id.btnEliminarProducto);

        //btnTablaCambioPrecios = (Button) findViewById(R.id.btnCambioPrecios);

        //en el bundle recien creado se colocan las extras que trae del menu anterior
        objetosRecibidos = getIntent().getExtras();

        //se asigna a un objeto usuario los datos del bundle ========================
        user = (Usuario) objetosRecibidos.getSerializable("usuario");
        //no usado en esta pantalla =================================================


        btnIngresarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingresarProducto = new Intent(v.getContext(), InsertarProductoActivity.class);
                ingresarProducto.putExtras(objetosRecibidos);
                startActivity(ingresarProducto);
            }
        });

        btnConsultarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent consultarProducto = new Intent(v.getContext(), ConsultarProductoActivity.class);
                consultarProducto.putExtras(objetosRecibidos);
                startActivity(consultarProducto);
            }
        });

        btnActualizarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actualizarProducto = new Intent(v.getContext(), ActualizarProductoActivity.class);
                actualizarProducto.putExtras(objetosRecibidos);
                startActivity(actualizarProducto);
            }
        });

        btnEliminarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eliminarProducto = new Intent(v.getContext(), EliminarProductoActivity.class);
                eliminarProducto.putExtras(objetosRecibidos);
                startActivity(eliminarProducto);
            }
        });



        //el boton de cambio precios
        /*
        btnTablaCambioPrecios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

         */


    }
}
