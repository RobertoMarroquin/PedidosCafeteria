package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarUbicacionActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_ubicacion);
        helper = new ControlBD(this);
        editCodUbicacion = (EditText) findViewById(R.id.editCodUbicacion);
    }

    public void eliminarUbicacion(View v){
        String regEliminadas;
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setCodubicacion(editCodUbicacion.getText().toString());
        helper.abrir();
        regEliminadas = helper.eliminar(ubicacion);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
