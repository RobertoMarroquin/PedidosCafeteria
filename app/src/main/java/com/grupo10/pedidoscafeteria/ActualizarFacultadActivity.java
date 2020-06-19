package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ActualizarFacultadActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodFacultad, editNomFacultad;

    Spinner spCodFacultad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_facultad);
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

    public void actualizarFacultad(View v){
        Facultad facultad = new Facultad();
        //facultad.setCodfacultad(editCodFacultad.getText().toString());
        facultad.setCodfacultad(spCodFacultad.getSelectedItem().toString());
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
