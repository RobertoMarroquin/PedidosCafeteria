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
    }

    public void consultarEmpleado(View v){
        helper.abrir();
        Empleado empleado = helper.consultarEmpleado(editCodEmpleado.getText().toString(), editCodFacultad.getText().toString(), editCodUbicacion.getText().toString());
        helper.cerrar();
        if (empleado==null){
            Toast.makeText(this, "Empleado no registrado", Toast.LENGTH_LONG).show();
        }
        else {
            editNomEmpleado.setText(empleado.getNomempleado());
            editApeEmpleado.setText(empleado.getApeempleado());
            editTelEmpleado.setText(empleado.getTelempleado());
        }
    }

    public void limpiarTexto(View v){
        editCodEmpleado.setText("");
        editCodFacultad.setText("");
        editCodUbicacion.setText("");
        editNomEmpleado.setText("");
        editApeEmpleado.setText("");
        editTelEmpleado.setText("");
    }




}
