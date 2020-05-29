package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ConsultarFacultadActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodFacultad, editNomFacultad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_facultad);
        helper = new ControlBD(this);
        editCodFacultad = (EditText) findViewById(R.id.editCodFacultad);
        editNomFacultad = (EditText) findViewById(R.id.editNomFacultad);
    }

    public void consultarFacultad(View v){
        helper.abrir();
        Facultad facultad = helper.consultarFacultad(editCodFacultad.getText().toString());
        helper.cerrar();
        if(facultad==null){
            Toast.makeText(this, "Facultad con codigo" + editCodFacultad.getText().toString() + "no encontrada", Toast.LENGTH_LONG).show();
        } else {
            editNomFacultad.setText(facultad.getNomfacultad());
        }
    }

    public void limpiarTexto(View v){
        editCodFacultad.setText("");
        editNomFacultad.setText("");
    }
}
