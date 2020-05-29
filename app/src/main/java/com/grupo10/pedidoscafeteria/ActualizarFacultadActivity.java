package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActualizarFacultadActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodFacultad, editNomFacultad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_facultad);
        helper = new ControlBD(this);
        editCodFacultad = (EditText) findViewById(R.id.editCodFacultad);
        editNomFacultad = (EditText) findViewById(R.id.editNomFacultad);
    }

    public void actualizarFacultad(View v){
        Facultad facultad = new Facultad();
        facultad.setCodfacultad(editCodFacultad.getText().toString());
        facultad.setNomfacultad(editNomFacultad.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(facultad);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        editCodFacultad.setText("");
        editNomFacultad.setText("");
    }


}
