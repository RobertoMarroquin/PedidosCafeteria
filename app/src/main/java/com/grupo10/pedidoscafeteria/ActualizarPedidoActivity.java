package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import android.os.Bundle;

public class ActualizarPedidoActivity extends AppCompatActivity {
    ControlBD helper;
    Spinner spIdRuta, spCodEstado;
    Usuario user;
    Integer IdPedido;
    Bundle paquete;
    private static final String[] camposPedido = new String[]{"IdPedido", "codtrabajador", "codproducto","cantidadpedido","nombreproducto","preciounitario"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_pedido);

        helper = new ControlBD(this);
        paquete = getIntent().getExtras();
        if (paquete != null) {
            user = (Usuario) paquete.getSerializable("usuario");
            IdPedido = (Integer) paquete.getSerializable("idpedido");
        }
        else {
            user = null;
            IdPedido= null;
        }

        spIdRuta = (Spinner) findViewById(R.id.spIdRuta);
        spCodEstado = (Spinner) findViewById(R.id.spCodEstado);

        //spinner de idruta
        ArrayList<Integer> listaidruta = helper.getAllIdRutaPedido();
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, listaidruta);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spIdRuta.setPrompt("Seleccione el ID de la ruta deseado");
        spIdRuta.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner_layout,this));

        //spinner de codestado
        ArrayList<String> listacodestado = helper.getAllCodEstado();
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listacodestado);
        adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spCodEstado.setPrompt("Seleccione el codigo de estado deseado");
        spCodEstado.setAdapter(new NothingSelectedSpinnerAdapter(adapter2, R.layout.spinner_layout,this));

    }

    public void actualizarpedido(View v) {
        SQLiteDatabase db = helper.abrir2();
        String CodEstado;
        Integer Idruta;
        Idruta = (Integer) spIdRuta.getSelectedItem();
        CodEstado = spCodEstado.getSelectedItem().toString();
        String[] argumentos = {Idruta.toString(),CodEstado,IdPedido.toString()};

        db.execSQL("UPDATE pedido SET idruta=?,codestadopedido=? where idpedido =?",argumentos);
        Toast.makeText(ActualizarPedidoActivity.this, "Pedido Actualizado No.: "+ IdPedido.toString(), Toast.LENGTH_SHORT).show();
        helper.cerrar();
        finish();

    }

}