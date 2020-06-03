package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class EliminarProductoActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodProducto;

    Spinner spCodMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_producto);

        helper = new ControlBD(this);

        editCodProducto = (EditText) findViewById(R.id.editCodProducto);

        spCodMenu = (Spinner) findViewById(R.id.spinnercodmenu);

        ArrayList<String> listacodmenu = helper.getAllCodMenu();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listacodmenu);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spCodMenu.setPrompt("Seleccione el codigo de menu deseado");
        spCodMenu.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner_layout,this));
    }


    public void eliminarProducto(View v){
        String regEliminadas;
        Producto producto = new Producto();
        producto.setCodproducto(editCodProducto.getText().toString());
        producto.setCodmenu(spCodMenu.getSelectedItem().toString());

        helper.abrir();
        regEliminadas = helper.eliminar(producto);
        helper.cerrar();

        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        editCodProducto.setText("");
    }


}
