package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertarEmpleadoActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodEmpleado, editCodFacultad, editCodUbicacion;
    EditText editNomEmpleado, editApeEmpleado, editTelEmpleado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_empleado);
        helper = new ControlBD(this);
        editCodEmpleado = (EditText) findViewById(R.id.editCodEmpleado);
        editCodFacultad = (EditText) findViewById(R.id.editCodFacultad);
        editCodUbicacion = (EditText) findViewById(R.id.editCodUbicacion);
        editNomEmpleado = (EditText) findViewById(R.id.editNomEmpleado);
        editApeEmpleado = (EditText) findViewById(R.id.editApeEmpleado);
        editTelEmpleado = (EditText) findViewById(R.id.editTelEmpleado);
    }

    public void insertarEmpleado(View v){
        String regInsertados;
        String codempleado = editCodEmpleado.getText().toString();
        String codfacultad = editCodFacultad.getText().toString();
        String codubicacion = editCodUbicacion.getText().toString();
        String nomempleado = editNomEmpleado.getText().toString();
        String apeempleado = editApeEmpleado.getText().toString();
        String telempleado = editTelEmpleado.getText().toString();

        Empleado empleado = new Empleado();
        empleado.setCodempleado(codempleado);
        empleado.setCodfacultad(codfacultad);
        empleado.setCodubicacion(codubicacion);
        empleado.setNomempleado(nomempleado);
        empleado.setApeempleado(apeempleado);
        empleado.setTelempleado(telempleado);

        helper.abrir();
        regInsertados = helper.insertar(empleado);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
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
