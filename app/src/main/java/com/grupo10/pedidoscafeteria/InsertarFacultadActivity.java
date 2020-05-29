package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertarFacultadActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodFacultad, editNomFacultad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_facultad);
        helper = new ControlBD(this);
        editCodFacultad = (EditText) findViewById(R.id.editCodFacultad);
        editNomFacultad = (EditText) findViewById(R.id.editNomFacultad);
    }

    public void insertarFacultad(View v){
        String codfacultad = editCodFacultad.getText().toString();
        String nomfacultad = editNomFacultad.getText().toString();

        String reginsertados;

        Facultad facultad = new Facultad();
        facultad.setCodfacultad(codfacultad);
        facultad.setNomfacultad(nomfacultad);
        helper.abrir();
        reginsertados = helper.insertar(facultad);
        helper.cerrar();
        Toast.makeText(this, reginsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        editNomFacultad.setText("");
        editCodFacultad.setText("");
    }


}
