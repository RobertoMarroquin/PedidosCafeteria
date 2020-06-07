package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class ActualizarRutaPedidoActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editInicioRuta, editFinRuta;
    Spinner spIdRuta, spCodRepartidor;

    Calendar calendario;
    DatePickerDialog datepicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_ruta_pedido);

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

        //PARA LA FECHA INICIO
        editInicioRuta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calendario = Calendar.getInstance();
                int day = calendario.get(Calendar.DAY_OF_MONTH);
                int month = calendario.get(Calendar.MONTH);
                int year = calendario.get(Calendar.YEAR);

                datepicker = new DatePickerDialog(ActualizarRutaPedidoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editInicioRuta.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                }, day, month, year);
                datepicker.updateDate(year, month, day);
                datepicker.show();
            }
        });

        //PARA LA FECHA FIN
        editFinRuta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calendario = Calendar.getInstance();
                int day = calendario.get(Calendar.DAY_OF_MONTH);
                int month = calendario.get(Calendar.MONTH);
                int year = calendario.get(Calendar.YEAR);

                datepicker = new DatePickerDialog(ActualizarRutaPedidoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editFinRuta.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                }, day, month, year);
                datepicker.updateDate(year, month, day);
                datepicker.show();
            }
        });
    }

    public void actualizarRutaPedido(View v) {
        RutaPedido rp = new RutaPedido();
        rp.setIdruta((Integer) spIdRuta.getSelectedItem());
        rp.setCodrepartidor(spCodRepartidor.getSelectedItem().toString());
        rp.setInicioruta(editInicioRuta.getText().toString());
        rp.setFinruta(editFinRuta.getText().toString());

        helper.abrir();
        String estado = helper.actualizar(rp);
        helper.cerrar();

        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto (View v){
        editInicioRuta.setText("");
        editFinRuta.setText("");
    }

}