package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ActualizarProductoActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodProducto, editNombreProducto, editPrecioUnitario, editCodMenu;

    Spinner spCodMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_producto);
        helper = new ControlBD(this);
        editCodProducto = (EditText) findViewById(R.id.editCodProducto);
        editNombreProducto = (EditText) findViewById(R.id.editNombreProducto);
        editPrecioUnitario = (EditText) findViewById(R.id.editPrecioUnitario);
        editCodMenu = (EditText) findViewById(R.id.editCodMenu);

        //spCodMenu = (Spinner) findViewById(R.id.spCodMenu);

        ArrayList<String> listacodmenu = helper.getAllCodMenu();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listacodmenu);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spCodMenu.setPrompt("Seleccione el codigo de menu deseado");
        spCodMenu.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner_layout,this));
    }

    public void actualizarProducto(View v) {
        Producto producto = new Producto();
        producto.setCodproducto(editCodProducto.getText().toString());
        //producto.setCodmenu(spCodMenu.getSelectedItem().toString());
        producto.setCodmenu(editCodMenu.getText().toString());
        producto.setNombreproducto(editNombreProducto.getText().toString());
        producto.setPreciounitario(Float.valueOf(editPrecioUnitario.getText().toString()));

        helper.abrir();
        String estado = helper.actualizar(producto);
        helper.cerrar();

        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto (View v){
        editCodProducto.setText("");
        editNombreProducto.setText("");
        editPrecioUnitario.setText("");
    }
}
