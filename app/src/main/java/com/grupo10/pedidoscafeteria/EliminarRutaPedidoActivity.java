package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class EliminarRutaPedidoActivity extends AppCompatActivity {
    ControlBD helper;

    Spinner spIdRuta, spCodRepartidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_ruta_pedido);

        helper = new ControlBD(this);

        spIdRuta = (Spinner) findViewById(R.id.spIdRuta);
        spCodRepartidor = (Spinner) findViewById(R.id.spCodRepartidor);

        //spinner de idruta
        ArrayList<Integer> listaidruta = helper.getAllIdRutaPedido();
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, listaidruta);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spIdRuta.setPrompt("Seleccione el ID de la ruta deseado");
        spIdRuta.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner_layout,this));

        //spinner de codrepartidor
        ArrayList<String> listacodrepartidor = helper.getAllCodRepartidor();
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listacodrepartidor);
        adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spCodRepartidor.setPrompt("Seleccione el codigo de repartidor deseado");
        spCodRepartidor.setAdapter(new NothingSelectedSpinnerAdapter(adapter2, R.layout.spinner_layout,this));
    }

    public void eliminarRutaPedido(View v){
        String regEliminadas;
        RutaPedido rp = new RutaPedido();

        rp.setCodrepartidor(spCodRepartidor.getSelectedItem().toString());
        rp.setIdruta((Integer) spIdRuta.getSelectedItem());

        helper.abrir();
        regEliminadas = helper.eliminar(rp);
        helper.cerrar();

        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}