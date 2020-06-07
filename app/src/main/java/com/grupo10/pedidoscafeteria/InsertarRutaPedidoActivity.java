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

public class InsertarRutaPedidoActivity extends AppCompatActivity {
    ControlBD helper;

    EditText editFinRuta, editInicioRuta;

    Spinner spCodRepartidor;

    Calendar calendario;
    DatePickerDialog datepicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_ruta_pedido);
        helper = new ControlBD(this);

        spCodRepartidor = (Spinner) findViewById(R.id.spCodRepartidor);
        editFinRuta = (EditText) findViewById(R.id.editFinRuta);
        editInicioRuta = (EditText) findViewById(R.id.editInicioRuta);

        //PARA LA FECHA INICIO
        editInicioRuta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calendario = Calendar.getInstance();
                int day = calendario.get(Calendar.DAY_OF_MONTH);
                int month = calendario.get(Calendar.MONTH);
                int year = calendario.get(Calendar.YEAR);

                datepicker = new DatePickerDialog(InsertarRutaPedidoActivity.this, new DatePickerDialog.OnDateSetListener() {
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

                datepicker = new DatePickerDialog(InsertarRutaPedidoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editFinRuta.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                }, day, month, year);
                datepicker.updateDate(year, month, day);
                datepicker.show();
            }
        });

        //PARA EL SPINNER DE CODREPARTIDOR
        ArrayList<String> listacodrepartidor = helper.getAllCodRepartidor();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listacodrepartidor);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spCodRepartidor.setPrompt("Seleccione el codigo de repartidor deseado");
        spCodRepartidor.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner_layout,this));
    }

    public void insertarRutaPedido(View v){
        String codrepartidor = spCodRepartidor.getSelectedItem().toString();
        String fechainicio = editInicioRuta.getText().toString();
        String fechafin = editFinRuta.getText().toString();

        String reginsertados;

        RutaPedido rp = new RutaPedido();
        rp.setCodrepartidor(codrepartidor);
        rp.setInicioruta(fechainicio);
        rp.setFinruta(fechafin);

        helper.abrir();
        reginsertados = helper.insertar(rp);
        helper.cerrar();
        Toast.makeText(this, reginsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        editInicioRuta.setText("");
        editFinRuta.setText("");
    }
}