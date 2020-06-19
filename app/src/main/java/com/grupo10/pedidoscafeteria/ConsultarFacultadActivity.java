package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ConsultarFacultadActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodFacultad, editNomFacultad;

    Spinner spCodFacultad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_facultad);
        helper = new ControlBD(this);
        editCodFacultad = (EditText) findViewById(R.id.editCodFacultad);
        editNomFacultad = (EditText) findViewById(R.id.editNomFacultad);
        spCodFacultad = (Spinner) findViewById(R.id.spCodFacultad);

        ArrayList<String> listacodfacultad = helper.getAllCodFacultad();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listacodfacultad);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spCodFacultad.setPrompt("Seleccione el codigo de facultad deseada");
        spCodFacultad.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner_layout,this));
    }

    public void consultarFacultad(View v){
        helper.abrir();
        Facultad facultad = helper.consultarFacultad(spCodFacultad.getSelectedItem().toString());
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
