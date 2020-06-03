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
    EditText editCodProducto, editNombreProducto, editPrecioUnitario;

    Spinner spCodMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_producto);
        helper = new ControlBD(this);
        editCodProducto = (EditText) findViewById(R.id.editCodProducto);
        editNombreProducto = (EditText) findViewById(R.id.editNombreProducto);
        editPrecioUnitario = (EditText) findViewById(R.id.editPrecioUnitario);

        spCodMenu = (Spinner) findViewById(R.id.spinnercodmenu);

        ArrayList<String> listacodmenu = helper.getAllCodMenu();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listacodmenu);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spCodMenu.setPrompt("Seleccione el codigo de menu deseado");
        spCodMenu.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner_layout,this));

    }

    public void insertarProducto(View v){
        String regInsertados;

        String codproducto = editCodProducto.getText().toString();
        String codmenu = spCodMenu.getSelectedItem().toString();
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
