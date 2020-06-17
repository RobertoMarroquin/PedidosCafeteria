package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class EliminarMenuActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodMenu, editCodLocal ;

    Spinner spCodLocal;

    Bundle recibido;
    Usuario usuarioRecibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_menu);
        helper = new ControlBD(this);
        editCodMenu = (EditText) findViewById(R.id.editCodMenu);
        editCodLocal = (EditText) findViewById(R.id.editCodLocal);
        spCodLocal = (Spinner) findViewById(R.id.spCodLocal);

        //obteniendo el bundle y usando el objeto usuario que trae
        recibido = getIntent().getExtras();
        usuarioRecibido = (Usuario) recibido.getSerializable("usuario");


        //para que el campo de codempleado ya quede con el mismo de nombreusuario
        //editCodEncargadoLocal.setText(usuarioRecibido.getNombreusuario());

        //para el spinner de codigos de local
        ArrayList<String> listacodlocal = helper.getAllCodLocal(usuarioRecibido);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listacodlocal);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spCodLocal.setPrompt("Seleccione el codigo de local deseado");
        spCodLocal.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner_layout,this));
    }

    public void eliminarMenu(View v){
        String regEliminadas;
        Menu menu = new Menu();
        menu.setCodmenu(editCodMenu.getText().toString());
        //menu.setCodlocal(editCodLocal.getText().toString());
        menu.setCodlocal(spCodLocal.getSelectedItem().toString());
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
