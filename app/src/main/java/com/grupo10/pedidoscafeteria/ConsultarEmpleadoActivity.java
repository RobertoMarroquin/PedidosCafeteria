package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ConsultarEmpleadoActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodEmpleado, editCodFacultad, editCodUbicacion;
    EditText editNomEmpleado, editApeEmpleado, editTelEmpleado;

    EditText editCorreoEmpleado;

    Bundle recibido;
    Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_empleado);
        helper = new ControlBD(this);
        editCodEmpleado = (EditText) findViewById(R.id.editCodEmpleado);
        editCodFacultad = (EditText) findViewById(R.id.editCodFacultad);
        editCodUbicacion = (EditText) findViewById(R.id.editCodUbicacion);
        editNomEmpleado = (EditText) findViewById(R.id.editNomEmpleado);
        editApeEmpleado = (EditText) findViewById(R.id.editApeEmpleado);
        editTelEmpleado = (EditText) findViewById(R.id.editTelEmpleado);

        editCorreoEmpleado = (EditText) findViewById(R.id.editCorreoEmpleado);

        //obteniendo el bundle y usando el objeto usuario que trae
        recibido = getIntent().getExtras();
        user = (Usuario) recibido.getSerializable("usuario");


        //para que el campo de codempleado ya quede con el mismo de nombreusuario
        editCodEmpleado.setText(user.getNombreusuario());
    }

    public void consultarEmpleado2(View v){
        helper.abrir();
        //Empleado empleado = helper.consultarEmpleado(editCodEmpleado.getText().toString(), editCodFacultad.getText().toString(), editCodUbicacion.getText().toString());
        Empleado empleado = helper.consultarEmpleado2(editCodEmpleado.getText().toString());
        helper.cerrar();
        if (empleado==null){
            Toast.makeText(this, "Empleado no registrado", Toast.LENGTH_LONG).show();
        }
        else {
            editCodFacultad.setText(empleado.getCodfacultad());
            editCodUbicacion.setText(empleado.getCodubicacion());
            editNomEmpleado.setText(empleado.getNomempleado());
            editApeEmpleado.setText(empleado.getApeempleado());
            editTelEmpleado.setText(empleado.getTelempleado());
            editCorreoEmpleado.setText(empleado.getCorreoempleado());
        }
    }

    public void limpiarTexto(View v){
        editCodEmpleado.setText("");
        editCodFacultad.setText("");
        editCodUbicacion.setText("");
        editNomEmpleado.setText("");
        editApeEmpleado.setText("");
        editTelEmpleado.setText("");
        editCorreoEmpleado.setText("");
    }




}
