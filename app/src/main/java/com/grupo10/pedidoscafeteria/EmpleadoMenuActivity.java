package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmpleadoMenuActivity extends AppCompatActivity {
    Button datosEmpleado, datosPedidos, datosLocales;
    Usuario user;

    Bundle objetosRecibidos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado_menu);

        //en el bundle recien creado se colocan las extras que trae del menu anterior
        objetosRecibidos = getIntent().getExtras();

        //se asigna a un objeto usuario los datos del bundle ========================
        user = (Usuario) objetosRecibidos.getSerializable("usuario");
        //no usado en esta pantalla =================================================


        datosEmpleado = (Button) findViewById(R.id.btnDatosEmpleado);
        datosLocales = (Button) findViewById(R.id.btnLocal);


        //PARA GESTIONAR EL EMPLEADO.
        //tablas de FACULTAD, UBICACION Y EMPLEADO
        datosEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuFacUbiEmp = new Intent(v.getContext(), FacultadUbicacionEmpleadoMenu.class);
                //se envia nuevamente el bundle hacia la siguiente pantalla.
                menuFacUbiEmp.putExtras(objetosRecibidos);
                startActivity(menuFacUbiEmp);
            }
        });





        //
        // PARA INGRESAR A LOS LOCALES
        datosLocales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listaLocales = new Intent(v.getContext(),ListaLocalesActivity.class);

                listaLocales.putExtras(objetosRecibidos);
                startActivity(listaLocales);
            }
        });
    }

}
