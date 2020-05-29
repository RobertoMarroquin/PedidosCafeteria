package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DatosEmpleadoMenuActivity extends AppCompatActivity {
    Button btnIngresarEmpleado, btnActualizarEmpleado, btnConsultarEmpleado, btnEliminarEmpleado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_empleado_menu);
        btnIngresarEmpleado = (Button) findViewById(R.id.btnIngresarEmpleado);
        btnActualizarEmpleado = (Button) findViewById(R.id.btnActualizarEmpleado);
        btnConsultarEmpleado = (Button) findViewById(R.id.btnConsultarEmpleado);
        btnEliminarEmpleado = (Button) findViewById(R.id.btnEliminarEmpleado);

        //PARA INGRESAR UN NUEVO EMPLEADO
        btnIngresarEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insertarEmpleado = new Intent(v.getContext(), InsertarEmpleadoActivity.class);
                startActivity(insertarEmpleado);
            }
        });

        //PARA ACTUALIZAR EL EMPLEADO
        btnActualizarEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actualizarEmpleado = new Intent(v.getContext(), ActualizarEmpleadoActivity.class);
                startActivity(actualizarEmpleado);
            }
        });

        //PARA CONSULTAR UN EMPLEADO
        btnConsultarEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent consultarEmpleado = new Intent(v.getContext(), ConsultarEmpleadoActivity.class);
                startActivity(consultarEmpleado);
            }
        });

        //PARA ELIMINAR UN EMPLEADO
        btnEliminarEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eliminarEmpleado = new Intent(v.getContext(), EliminarEmpleadoActivity.class);
                startActivity(eliminarEmpleado);
            }
        });
    }
}
