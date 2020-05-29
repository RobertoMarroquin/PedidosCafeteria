package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarEncargadoLocalActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodEncargadoLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_encargado_local);
        helper = new ControlBD(this);
        editCodEncargadoLocal = (EditText) findViewById(R.id.editCodEncargadoLocal);
    }

    public void eliminarEncargadoLocal(View v){
        String regEliminadas;
        EncargadoLocal encargadoLocal = new EncargadoLocal();
        encargadoLocal.setCodencargadolocal(editCodEncargadoLocal.getText().toString());
        helper.abrir();
        regEliminadas = helper.eliminar(encargadoLocal);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
