package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DatosEmpleadoMenuActivity extends AppCompatActivity {
    Button btnIngresarEmpleado, btnActualizarEmpleado, btnConsultarEmpleado, btnEliminarEmpleado;

    Bundle recibido;
    //muy improbable que en esta pantalla se utilice el usuario, asi que no se crea,
    //el bundle es solo para pasar los datos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_empleado_menu);
        btnIngresarEmpleado = (Button) findViewById(R.id.btnIngresarEmpleado);
        btnActualizarEmpleado = (Button) findViewById(R.id.btnActualizarEmpleado);
        btnConsultarEmpleado = (Button) findViewById(R.id.btnConsultarEmpleado);
        btnEliminarEmpleado = (Button) findViewById(R.id.btnEliminarEmpleado);

        recibido = getIntent().getExtras();

        //PARA INGRESAR UN NUEVO EMPLEADO
        btnIngresarEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insertarEmpleado = new Intent(v.getContext(), InsertarEmpleadoActivity.class);

                //se va a mandar el usuario al menu de crear empleado para que el nombreusuario sea
                //el codempleado

                insertarEmpleado.putExtras(recibido);
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
