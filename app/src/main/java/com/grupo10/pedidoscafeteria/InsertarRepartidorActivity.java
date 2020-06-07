package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertarRepartidorActivity extends AppCompatActivity {
    EditText editCodRepartidor, editNomRepartidor, editApeRepartidor, editTelRepartidor;
    ControlBD helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_repartidor);
        helper = new ControlBD(this);

        editCodRepartidor = (EditText) findViewById(R.id.editCodRepartidor);
        editNomRepartidor = (EditText) findViewById(R.id.editNomRepartidor);
        editApeRepartidor = (EditText) findViewById(R.id.editApeRepartidor);
        editTelRepartidor = (EditText) findViewById(R.id.editTelRepartidor);

    }

    public void insertarRepartidor(View v){
        String codrepartidor = editCodRepartidor.getText().toString();
        String nomrepartidor = editNomRepartidor.getText().toString();
        String aperepartidor = editApeRepartidor.getText().toString();
        String telrepartidor = editTelRepartidor.getText().toString();

        String reginsertados;

        Repartidor repartidor = new Repartidor();
        repartidor.setCodrepartidor(codrepartidor);
        repartidor.setNomrepartidor(nomrepartidor);
        repartidor.setAperepartidor(aperepartidor);
        repartidor.setTelrepartidor(telrepartidor);
        helper.abrir();
        reginsertados = helper.insertar(repartidor);
        helper.cerrar();
        Toast.makeText(this, reginsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        editCodRepartidor.setText("");
        editNomRepartidor.setText("");
        editApeRepartidor.setText("");
        editTelRepartidor.setText("");
    }
}