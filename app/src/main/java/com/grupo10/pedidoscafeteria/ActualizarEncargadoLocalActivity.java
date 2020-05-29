package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActualizarEncargadoLocalActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodEncargadoLocal, editNomEncargadoLocal, editApeEncargadoLocal, editTelEncargadoLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_encargado_local);
        helper = new ControlBD(this);
        editCodEncargadoLocal = (EditText) findViewById(R.id.editCodEncargadoLocal);
        editNomEncargadoLocal = (EditText) findViewById(R.id.editNomEncargadoLocal);
        editApeEncargadoLocal = (EditText) findViewById(R.id.editApeEncargadoLocal);
        editTelEncargadoLocal = (EditText) findViewById(R.id.editTelEncargadoLocal);
    }

    public void actualizarEncargadoLocal(View v){
        EncargadoLocal encargadoLocal = new EncargadoLocal();
        encargadoLocal.setCodencargadolocal(editCodEncargadoLocal.getText().toString());
        encargadoLocal.setNomencargadolocal(editNomEncargadoLocal.getText().toString());
        encargadoLocal.setApeencargadolocal(editApeEncargadoLocal.getText().toString());
        encargadoLocal.setTelencargadolocal(editTelEncargadoLocal.getText().toString());

        helper.abrir();
        String estado = helper.actualizar(encargadoLocal);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        editCodEncargadoLocal.setText("");
        editNomEncargadoLocal.setText("");
        editApeEncargadoLocal.setText("");
        editTelEncargadoLocal.setText("");
    }
}
