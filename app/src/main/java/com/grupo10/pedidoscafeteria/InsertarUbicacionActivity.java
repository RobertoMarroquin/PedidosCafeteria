package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertarUbicacionActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodUbicacion, editDescUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_ubicacion);
        helper = new ControlBD(this);
        editCodUbicacion = (EditText) findViewById(R.id.editCodUbicacion);
        editDescUbicacion = (EditText) findViewById(R.id.editDescUbicacion);
    }

    public void insertarUbicacion(View v){
        String codubicacion = editCodUbicacion.getText().toString();
        String descubicacion = editDescUbicacion.getText().toString();

        String reginsertados;

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setCodubicacion(codubicacion);
        ubicacion.setDescubicacion(descubicacion);
        helper.abrir();
        reginsertados = helper.insertar(ubicacion);
        helper.cerrar();
        Toast.makeText(this, reginsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        editCodUbicacion.setText("");
        editDescUbicacion.setText("");
    }
}
