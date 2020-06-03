package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmpleadoMenuActivity extends AppCompatActivity {
    Button datosEmpleado, datosPedidos, datosLocales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado_menu);
        Bundle objetosRecividos = getIntent().getExtras();
        datosEmpleado = (Button) findViewById(R.id.btnDatosEmpleado);
        datosLocales = (Button) findViewById(R.id.btnLocal);
        //aqui irian los otros botones de los otros dos menus
        //


        //al darle click al boton de datos empleado llevara al menu en donde se gestionan las
        //tablas de FACULTAD, UBICACION Y EMPLEADO
        datosEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuFacUbiEmp = new Intent(v.getContext(), FacultadUbicacionEmpleadoMenu.class);
                startActivity(menuFacUbiEmp);
            }
        });

        datosLocales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent listaLocales = new Intent(v.getContext(),ListaLocalesActivity.class);
                startActivity(listaLocales);
            }
        });
    }

}
