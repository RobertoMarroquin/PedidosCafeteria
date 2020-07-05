package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class InsertarProductoActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodProducto, editNombreProducto, editPrecioUnitario, editCodMenu;

    Spinner spCodMenu;

    Usuario user;

    Bundle objetosRecibidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_producto);
        helper = new ControlBD(this);
        editCodProducto = (EditText) findViewById(R.id.editCodProducto);
        editNombreProducto = (EditText) findViewById(R.id.editNombreProducto);
        editPrecioUnitario = (EditText) findViewById(R.id.editPrecioUnitario);
        editCodMenu = (EditText) findViewById(R.id.editCodMenu);

        //spCodMenu = (Spinner) findViewById(R.id.spinnercodmenu);

        //en el bundle recien creado se colocan las extras que trae del menu anterior
        objetosRecibidos = getIntent().getExtras();

        //se asigna a un objeto usuario los datos del bundle ========================
        user = (Usuario) objetosRecibidos.getSerializable("usuario");
        //no usado en esta pantalla =================================================

/*
        ArrayList<String> listacodmenu = helper.getAllCodMenu();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listacodmenu);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spCodMenu.setPrompt("Seleccione el codigo de menu deseado");
        spCodMenu.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner_layout,this));


 */


    }

    public void insertarProducto(View v){
        String regInsertados;

        String codproducto = editCodProducto.getText().toString();
        //String codmenu = spCodMenu.getSelectedItem().toString();
        String codmenu = editCodMenu.getText().toString();
        String nombreproducto = editNombreProducto.getText().toString();
        Float preciounitario = Float.valueOf(editPrecioUnitario.getText().toString());

        Producto producto = new Producto();
        producto.setCodproducto(codproducto);
        producto.setCodmenu(codmenu);
        producto.setNombreproducto(nombreproducto);
        producto.setPreciounitario(preciounitario);

        helper.abrir();
        regInsertados = helper.insertar(producto);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        editCodProducto.setText("");
        spCodMenu.setPrompt("Seleccione el codigo de menu deseado");
        editNombreProducto.setText("");
        editPrecioUnitario.setText("");
    }
}
