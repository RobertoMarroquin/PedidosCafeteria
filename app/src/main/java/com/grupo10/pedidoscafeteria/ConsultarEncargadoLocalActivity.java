package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ConsultarEncargadoLocalActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodEncargadoLocal, editNomEncargadoLocal, editApeEncargadoLocal, editTelEncargadoLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_encargado_local);
        helper = new ControlBD(this);
        editCodEncargadoLocal = (EditText) findViewById(R.id.editCodEncargadoLocal);
        editNomEncargadoLocal = (EditText) findViewById(R.id.editNomEncargadoLocal);
        editApeEncargadoLocal = (EditText) findViewById(R.id.editApeEncargadoLocal);
        editTelEncargadoLocal = (EditText) findViewById(R.id.editTelEncargadoLocal);
    }

    public void consultarEncargadoLocal(View v){
        helper.abrir();
        EncargadoLocal encargadoLocal = helper.consultarEncargadoLocal(editCodEncargadoLocal.getText().toString());
        helper.cerrar();
        if(encargadoLocal==null){
            Toast.makeText(this, "Encargado de local con codigo" + editCodEncargadoLocal.getText().toString() + "no encontrado", Toast.LENGTH_LONG).show();
        } else {
            editNomEncargadoLocal.setText(encargadoLocal.getNomencargadolocal());
            editApeEncargadoLocal.setText(encargadoLocal.getApeencargadolocal());
            editTelEncargadoLocal.setText(encargadoLocal.getTelencargadolocal());
        }
    }

    public void limpiarTexto(View v){
        editNomEncargadoLocal.setText("");
        editApeEncargadoLocal.setText("");
        editTelEncargadoLocal.setText("");
        editCodEncargadoLocal.setText("");
    }
}
