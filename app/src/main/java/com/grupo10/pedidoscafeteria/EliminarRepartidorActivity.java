package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class EliminarRepartidorActivity extends AppCompatActivity {
    ControlBD helper;

    Spinner spCodRepartidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_repartidor);
        helper = new ControlBD(this);

        spCodRepartidor = (Spinner) findViewById(R.id.spCodRepartidor);


        ArrayList<String> listacodrepartidor = helper.getAllCodRepartidor();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listacodrepartidor);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spCodRepartidor.setPrompt("Seleccione el codigo de repartidor deseado");

        spCodRepartidor.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner_layout,this));
    }

    public void eliminarRepartidor(View v){
        String regEliminadas;
        Repartidor repartidor = new Repartidor();
        repartidor.setCodrepartidor(spCodRepartidor.getSelectedItem().toString());

        helper.abrir();
        regEliminadas = helper.eliminar(repartidor);
        helper.cerrar();

        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }

}