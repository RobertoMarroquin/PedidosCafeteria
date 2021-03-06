package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarEmpleadoActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodEmpleado, editCodFacultad, editCodUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_empleado);
        helper = new ControlBD(this);
        editCodEmpleado = (EditText) findViewById(R.id.editCodEmpleado);
        editCodFacultad = (EditText) findViewById(R.id.editCodFacultad);
        editCodUbicacion = (EditText) findViewById(R.id.editCodUbicacion);
    }

    public void eliminarEmpleado(View v){
        String regEliminadas;
        Empleado empleado = new Empleado();
        empleado.setCodempleado(editCodEmpleado.getText().toString());
        empleado.setCodfacultad(editCodFacultad.getText().toString());
        empleado.setCodubicacion(editCodUbicacion.getText().toString());

        helper.abrir();
        regEliminadas = helper.eliminar(empleado);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        editCodEmpleado.setText("");
        editCodFacultad.setText("");
        editCodUbicacion.setText("");
    }
}
