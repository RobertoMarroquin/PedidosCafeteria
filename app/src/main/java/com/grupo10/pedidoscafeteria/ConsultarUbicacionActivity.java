package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ConsultarUbicacionActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodUbicacion, editDescUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_ubicacion);
        helper = new ControlBD(this);
        editCodUbicacion = (EditText) findViewById(R.id.editCodUbicacion);
        editDescUbicacion = (EditText) findViewById(R.id.editDescUbicacion);
    }

    public void consultarUbicacion(View v){
        helper.abrir();
        Ubicacion ubicacion = helper.consultarUbicacion(editCodUbicacion.getText().toString());
        helper.cerrar();
        if(ubicacion==null){
            Toast.makeText(this, "Ubicacion con codigo" + editCodUbicacion.getText().toString() + "no encontrada", Toast.LENGTH_LONG).show();
        } else {
            editDescUbicacion.setText(ubicacion.getDescubicacion());
        }
    }

    public void limpiarTexto(View v){
        editCodUbicacion.setText("");
        editDescUbicacion.setText("");
    }
}
