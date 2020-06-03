package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ConsultarProductoActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodProducto, editNombreProducto, editPrecioUnitario;

    Spinner spCodMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_producto);
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


    public void consultarProducto(View v){
        helper.abrir();
        Producto producto = helper.consultarProducto(editCodProducto.getText().toString(), spCodMenu.getSelectedItem().toString());
        helper.cerrar();
        if (producto==null){
            Toast.makeText(this, "Producto no registrado", Toast.LENGTH_LONG).show();
        }
        else {
            editNombreProducto.setText(producto.getNombreproducto());
            editPrecioUnitario.setText(String.valueOf(producto.getPreciounitario()));
        }
    }

    public void limpiarTexto(View v){
        editCodProducto.setText("");
        editPrecioUnitario.setText("");
        editNombreProducto.setText("");
    }
}
