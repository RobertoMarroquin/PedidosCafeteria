package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActualizarEmpleadoActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodEmpleado, editCodFacultad, editCodUbicacion;
    EditText editNomEmpleado, editApeEmpleado, editTelEmpleado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_empleado);
        helper = new ControlBD(this);
        editCodEmpleado = (EditText) findViewById(R.id.editCodEmpleado);
        editCodFacultad = (EditText) findViewById(R.id.editCodFacultad);
        editCodUbicacion = (EditText) findViewById(R.id.editCodUbicacion);
        editNomEmpleado = (EditText) findViewById(R.id.editNomEmpleado);
        editApeEmpleado = (EditText) findViewById(R.id.editApeEmpleado);
        editTelEmpleado = (EditText) findViewById(R.id.editTelEmpleado);
    }

    public void actualizarEmpleado(View v) {
        Empleado empleado = new Empleado();
        empleado.setCodempleado(editCodEmpleado.getText().toString());
        empleado.setCodfacultad(editCodFacultad.getText().toString());
        empleado.setCodubicacion(editCodUbicacion.getText().toString());
        empleado.setNomempleado(editNomEmpleado.getText().toString());
        empleado.setApeempleado(editApeEmpleado.getText().toString());
        empleado.setTelempleado(editTelEmpleado.getText().toString());

        helper.abrir();
        String estado = helper.actualizar(empleado);
        helper.cerrar();

        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto (View v){
        editCodEmpleado.setText("");
        editCodFacultad.setText("");
        editCodUbicacion.setText("");
        editNomEmpleado.setText("");
        editApeEmpleado.setText("");
        editTelEmpleado.setText("");
    }
}
