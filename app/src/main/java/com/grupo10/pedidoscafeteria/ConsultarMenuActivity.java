package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ConsultarMenuActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodMenu, editCodLocal, editPrecioMenu, editFechaDesde, editFechaHasta;

    Spinner spCodLocal;

    Bundle recibido;
    Usuario usuarioRecibido;

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

    public void consultarMenu(View v){
        helper.abrir();
        Menu menu = helper.consultarMenu(editCodMenu.getText().toString(), spCodLocal.getSelectedItem().toString());
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
