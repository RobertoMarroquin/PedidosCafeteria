package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ConsultarRepartidorActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editNomRepartidor, editApeRepartidor, editTelRepartidor;
    Spinner spCodRepartidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_repartidor);
        helper = new ControlBD(this);

        spCodRepartidor = (Spinner) findViewById(R.id.spCodRepartidor);
        editNomRepartidor = (EditText) findViewById(R.id.editNomRepartidor);
        editApeRepartidor = (EditText) findViewById(R.id.editApeRepartidor);
        editTelRepartidor = (EditText) findViewById(R.id.editTelRepartidor);

        ArrayList<String> listacodrepartidor = helper.getAllCodRepartidor();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listacodrepartidor);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spCodRepartidor.setPrompt("Seleccione el codigo de repartidor deseado");

        spCodRepartidor.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner_layout,this));
    }

    public void consultarRepartidor(View v){
        helper.abrir();
        Repartidor repartidor = helper.consultarRepartidor(spCodRepartidor.getSelectedItem().toString());
        helper.cerrar();
        if(repartidor==null){
            Toast.makeText(this, "Repartidor con codigo" + spCodRepartidor.getSelectedItem().toString() + "no encontrado", Toast.LENGTH_LONG).show();
        } else {
            editApeRepartidor.setText(repartidor.getAperepartidor());
            editNomRepartidor.setText(repartidor.getNomrepartidor());
            editTelRepartidor.setText(repartidor.getTelrepartidor());
        }
    }

    public void limpiarTexto(View v){
        editNomRepartidor.setText("");
        editApeRepartidor.setText("");
        editTelRepartidor.setText("");
    }


}