package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ConsultarRutaPedidoActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editInicioRuta, editFinRuta;
    Spinner spIdRuta, spCodRepartidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_ruta_pedido);

        helper = new ControlBD(this);
        editInicioRuta = (EditText) findViewById(R.id.editInicioRuta);
        editFinRuta = (EditText) findViewById(R.id.editFinRuta);
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

    public void consultarRutaPedido(View v){
        helper.abrir();
        RutaPedido rp = helper.consultarRutaPedido((Integer) spIdRuta.getSelectedItem(), spCodRepartidor.getSelectedItem().toString());
        helper.cerrar();
        if (rp==null){
            Toast.makeText(this, "Ruta de pedido no registrada", Toast.LENGTH_LONG).show();
        }
        else {
            editInicioRuta.setText(rp.getInicioruta());
            editFinRuta.setText(rp.getFinruta());
        }
    }

    public void limpiarTexto(View v){
        editInicioRuta.setText("");
        editFinRuta.setText("");
    }

}