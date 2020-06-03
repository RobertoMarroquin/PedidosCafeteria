package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarLocalActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodLocal, editCodEnc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_local);
        helper = new ControlBD(this);
        editCodLocal = (EditText) findViewById(R.id.editCodLocal);
        editCodEnc   = (EditText) findViewById(R.id.editCodEncargadoLocal);
    }

    public void eliminarLocal(View v){
        String regEliminadas;
        Local local = new Local();
        local.setCodlocal(editCodLocal.getText().toString());
        local.setCodencargadolocal(editCodEnc.getText().toString());
        helper.abrir();
        regEliminadas = helper.eliminar(local);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        editCodLocal.setText("");
        editCodEnc.setText("");
    }
}
