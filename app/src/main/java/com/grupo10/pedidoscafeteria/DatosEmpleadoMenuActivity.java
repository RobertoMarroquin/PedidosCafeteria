package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DatosEmpleadoMenuActivity extends AppCompatActivity {
    Button btnIngresarEmpleado, btnActualizarEmpleado, btnConsultarEmpleado, btnEliminarEmpleado;

    Bundle recibido;
    //muy improbable que en esta pantalla se utilice el usuario, asi que no se crea,

    //jaja me rio de mi mismo, porque al final si se uso

    //el bundle es solo para pasar los datos
    Usuario user;

    ControlBD helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_empleado_menu);

        helper = new ControlBD(this);

        btnIngresarEmpleado = (Button) findViewById(R.id.btnIngresarEmpleado);
        btnActualizarEmpleado = (Button) findViewById(R.id.btnActualizarEmpleado);
        btnConsultarEmpleado = (Button) findViewById(R.id.btnConsultarEmpleado);
        btnEliminarEmpleado = (Button) findViewById(R.id.btnEliminarEmpleado);

        recibido = getIntent().getExtras();

        //se asigna a un objeto usuario los datos del bundle ========================
        user = (Usuario) recibido.getSerializable("usuario");

        //PARA INGRESAR UN NUEVO EMPLEADO
        btnIngresarEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insertarEmpleado = new Intent(v.getContext(), InsertarEmpleadoActivity.class);

                //se va a mandar el usuario al menu de crear empleado para que el nombreusuario sea
                //el codempleado

                insertarEmpleado.putExtras(recibido);
                if (existe(user)){
                    Toast.makeText(getApplicationContext(), "Este usuario ya tiene el perfil completo", Toast.LENGTH_LONG).show();
                } else{
                    startActivity(insertarEmpleado);
                }
            }
        });

        //PARA ACTUALIZAR EL EMPLEADO
        btnActualizarEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actualizarEmpleado = new Intent(v.getContext(), ActualizarEmpleadoActivity.class);
                actualizarEmpleado.putExtras(recibido);
                startActivity(actualizarEmpleado);
            }
        });

        //PARA CONSULTAR UN EMPLEADO
        btnConsultarEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent consultarEmpleado = new Intent(v.getContext(), ConsultarEmpleadoActivity.class);
                consultarEmpleado.putExtras(recibido);
                startActivity(consultarEmpleado);
            }
        });

        //PARA ELIMINAR UN EMPLEADO
        btnEliminarEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eliminarEmpleado = new Intent(v.getContext(), EliminarEmpleadoActivity.class);
                eliminarEmpleado.putExtras(recibido);
                startActivity(eliminarEmpleado);
            }
        });
    }

    public boolean existe(Usuario usuario){
        String[] id = {usuario.getNombreusuario()};
        SQLiteDatabase db = helper.abrir2();
        Cursor c = db.rawQuery("select codempleado, codfacultad, codubicacion, nomempleado, apeempleado, telempleado from empleado where codempleado = ?", id);
        if (c.moveToFirst()){
            return true;
        }
        else {
            return false;
        }
    }
}
