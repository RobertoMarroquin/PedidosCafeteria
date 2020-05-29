package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarFacultadActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodFacultad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_facultad);
        helper = new ControlBD(this);
        editCodFacultad = (EditText) findViewById(R.id.editCodFacultad);
    }

    public void eliminarFacultad(View v){
        String regEliminadas;
        Facultad facultad = new Facultad();
        facultad.setCodfacultad(editCodFacultad.getText().toString());
        helper.abrir();
        regEliminadas = helper.eliminar(facultad);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
