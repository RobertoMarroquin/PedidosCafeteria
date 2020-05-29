package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActualizarUbicacionActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodUbicacion, editDescUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_ubicacion);
        helper = new ControlBD(this);
        editCodUbicacion = (EditText) findViewById(R.id.editCodUbicacion);
        editDescUbicacion = (EditText) findViewById(R.id.editDescUbicacion);
    }

    public void actualizarUbicacion(View v){
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setCodubicacion(editCodUbicacion.getText().toString());
        ubicacion.setDescubicacion(editDescUbicacion.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(ubicacion);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        editCodUbicacion.setText("");
        editDescUbicacion.setText("");
    }
}
