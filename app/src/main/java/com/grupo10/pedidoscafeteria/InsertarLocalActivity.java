package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class InsertarLocalActivity extends AppCompatActivity {
    EditText editCodLocal, editNomLocal, editCodEncargadoLocal;
    ControlBD helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_local);
        helper = new ControlBD(this);

        editCodLocal = (EditText) findViewById(R.id.editCodLocal);
        editNomLocal = (EditText) findViewById(R.id.editNomLocal);
        editCodEncargadoLocal = (EditText) findViewById(R.id.editCodEncargadoLocal);
    }

    public void insertarLocal(View v){
        String regInsertados;
        String codlocal = editCodLocal.getText().toString();
        String codencargadolocal = editCodEncargadoLocal.getText().toString();
        String nombrelocal = editNomLocal.getText().toString();

        Local local = new Local();
        local.setCodlocal(codlocal);
        local.setCodencargadolocal(codencargadolocal);
        local.setNombrelocal(nombrelocal);

        helper.abrir();
        regInsertados = helper.insertar(local);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        editCodLocal.setText("");
        editCodEncargadoLocal.setText("");
        editNomLocal.setText("");
    }


}
