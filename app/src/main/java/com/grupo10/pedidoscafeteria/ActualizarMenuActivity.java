package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class ActualizarMenuActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodMenu, editCodLocal, editPrecioMenu, editFechaDesdeMenu, editFechaHastaMenu;

    Calendar calendario;
    DatePickerDialog datepicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_menu);
        helper = new ControlBD(this);
        editCodMenu = (EditText) findViewById(R.id.editCodMenu);
        editCodLocal = (EditText) findViewById(R.id.editCodLocal);
        editPrecioMenu = (EditText) findViewById(R.id.editPrecioMenu);
        editFechaDesdeMenu = (EditText) findViewById(R.id.editFechaDesdeMenu);
        editFechaHastaMenu = (EditText) findViewById(R.id.editFechaHastaMenu);

        //para las fechas
        editFechaDesdeMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calendario = Calendar.getInstance();
                int day = calendario.get(Calendar.DAY_OF_MONTH);
                int month = calendario.get(Calendar.MONTH);
                int year = calendario.get(Calendar.YEAR);

                datepicker = new DatePickerDialog(ActualizarMenuActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editFechaDesdeMenu.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                }, day, month, year);
                datepicker.updateDate(year, month, day);
                datepicker.show();

            }
        });

        editFechaHastaMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendario = Calendar.getInstance();
                int dia = calendario.get(Calendar.DAY_OF_MONTH);
                int mes = calendario.get(Calendar.MONTH);
                int year = calendario.get(Calendar.YEAR);

                datepicker = new DatePickerDialog(ActualizarMenuActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editFechaHastaMenu.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                }, dia, mes, year);
                datepicker.updateDate(year,mes, dia);
                datepicker.show();

            }
        });
    }

    public void actualizarMenu(View v) {
        Menu menu = new Menu();
        menu.setCodmenu(editCodMenu.getText().toString());
        menu.setCodlocal(editCodLocal.getText().toString());
        menu.setPreciomenu(Float.valueOf(editPrecioMenu.getText().toString()));
        menu.setFechadesdemenu(editFechaDesdeMenu.getText().toString());
        menu.setFechahastamenu(editFechaHastaMenu.getText().toString());

        helper.abrir();
        String estado = helper.actualizar(menu);
        helper.cerrar();

        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto (View v){
        editCodMenu.setText("");
        editCodLocal.setText("");
        editPrecioMenu.setText("");
        editFechaDesdeMenu.setText("");
        editFechaHastaMenu.setText("");
    }
}
