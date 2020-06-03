package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ConsultarMenuActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodMenu, editCodLocal, editPrecioMenu, editFechaDesde, editFechaHasta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_menu);
        helper = new ControlBD(this);
        editCodMenu = (EditText) findViewById(R.id.editCodMenu);
        editCodLocal = (EditText) findViewById(R.id.editCodLocal);
        editPrecioMenu = (EditText) findViewById(R.id.editPrecioMenu);
        editFechaDesde = (EditText) findViewById(R.id.editFechaDesdeMenu);
        editFechaHasta = (EditText) findViewById(R.id.editFechaHastaMenu);
    }

    public void consultarMenu(View v){
        helper.abrir();
        Menu menu = helper.consultarMenu(editCodMenu.getText().toString(), editCodLocal.getText().toString());
        helper.cerrar();
        if (menu==null){
            Toast.makeText(this, "Menu no registrado", Toast.LENGTH_LONG).show();
        }
        else {
            editPrecioMenu.setText(String.valueOf(menu.getPreciomenu()));
            editFechaDesde.setText(menu.getFechadesdemenu());
            editFechaHasta.setText(menu.getFechahastamenu());
        }
    }

    public void limpiarTexto(View v){
        editCodMenu.setText("");
        editCodLocal.setText("");
        editPrecioMenu.setText("");
        editFechaHasta.setText("");
        editFechaDesde.setText("");
    }
}
