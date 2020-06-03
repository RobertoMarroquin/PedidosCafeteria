package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarMenuActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodMenu, editCodLocal ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_menu);
        helper = new ControlBD(this);
        editCodMenu = (EditText) findViewById(R.id.editCodMenu);
        editCodLocal = (EditText) findViewById(R.id.editCodLocal);
    }

    public void eliminarMenu(View v){
        String regEliminadas;
        Menu menu = new Menu();
        menu.setCodmenu(editCodMenu.getText().toString());
        menu.setCodlocal(editCodLocal.getText().toString());
        helper.abrir();
        regEliminadas = helper.eliminar(menu);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        editCodLocal.setText("");
        editCodMenu.setText("");
    }
}
