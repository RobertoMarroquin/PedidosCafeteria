package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FacultadUbicacionEmpleadoMenu extends AppCompatActivity {
    Button btnFacultad, btnUbicacion, btnEmpleado;
    Bundle recibido;

    //por si se llegaran a usar los datos del usuario en esta tabla
    Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultad_ubicacion_empleado_menu);
        //BOTONES PARA IR A CADA MENU (CADA CRUD DE CADA TABLA)
        btnFacultad = (Button) findViewById(R.id.btnFacultad);
        btnUbicacion = (Button) findViewById(R.id.btnUbicacion);
        btnEmpleado = (Button) findViewById(R.id.btnEmpleado);
        recibido = getIntent().getExtras();

        //
        user = (Usuario) recibido.getSerializable("usuario");
        //

        //para ir al menu de facultad.
        btnFacultad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crudFacultad = new Intent(v.getContext(), FacultadMenuActivity.class);
                startActivity(crudFacultad);
            }
        });
        // para ir al menu de ubicacion.
        btnUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crudUbicacion = new Intent(v.getContext(), UbicacionMenuActivity.class);
                startActivity(crudUbicacion);
            }
        });

        //para ir al menu de empleado.
        btnEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crudEmpleado = new Intent(v.getContext(), DatosEmpleadoMenuActivity.class);
                crudEmpleado.putExtras(recibido);
                startActivity(crudEmpleado);
            }
        });
    }
}
