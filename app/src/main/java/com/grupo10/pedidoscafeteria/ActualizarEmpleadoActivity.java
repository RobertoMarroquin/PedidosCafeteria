package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ActualizarEmpleadoActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodEmpleado, editCodFacultad, editCodUbicacion;
    EditText editNomEmpleado, editApeEmpleado, editTelEmpleado;

    Bundle recibido;
    Usuario user;

    Spinner spCodFacultad, spCodUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_empleado);
        helper = new ControlBD(this);
        editCodEmpleado = (EditText) findViewById(R.id.editCodEmpleado);
        editCodFacultad = (EditText) findViewById(R.id.editCodFacultad);
        editCodUbicacion = (EditText) findViewById(R.id.editCodUbicacion);
        editNomEmpleado = (EditText) findViewById(R.id.editNomEmpleado);
        editApeEmpleado = (EditText) findViewById(R.id.editApeEmpleado);
        editTelEmpleado = (EditText) findViewById(R.id.editTelEmpleado);

        spCodFacultad = (Spinner) findViewById(R.id.spCodFacultad);
        spCodUbicacion = (Spinner) findViewById(R.id.spCodUbicacion);

        //obteniendo el bundle y usando el objeto usuario que trae
        recibido = getIntent().getExtras();
        user = (Usuario) recibido.getSerializable("usuario");


        //para que el campo de codempleado ya quede con el mismo de nombreusuario
        editCodEmpleado.setText(user.getNombreusuario());

        //para el spinner de codfacultad
        ArrayList<String> listacodfacultad = helper.getAllCodFacultad();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listacodfacultad);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spCodFacultad.setPrompt("Seleccione el codigo de facultad deseado");
        spCodFacultad.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.spinner_layout,this));

        //para el spinner de codubicacion
        ArrayList<String> listacodubi = helper.getAllCodUbicacion();
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listacodubi);
        adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spCodUbicacion.setPrompt("Seleccione el codigo de ubicacion deseado");
        spCodUbicacion.setAdapter(new NothingSelectedSpinnerAdapter(adapter2, R.layout.spinner_layout,this));
    }

    public void actualizarEmpleado(View v) {
        Empleado empleado = new Empleado();
        empleado.setCodempleado(editCodEmpleado.getText().toString());
        //empleado.setCodfacultad(editCodFacultad.getText().toString());
        empleado.setCodfacultad(spCodFacultad.getSelectedItem().toString());
        //empleado.setCodubicacion(editCodUbicacion.getText().toString());
        empleado.setCodubicacion(spCodUbicacion.getSelectedItem().toString());
        empleado.setNomempleado(editNomEmpleado.getText().toString());
        empleado.setApeempleado(editApeEmpleado.getText().toString());
        empleado.setTelempleado(editTelEmpleado.getText().toString());

        helper.abrir();
        //existe actualizar(empleado) pero ese ya no se ocupa porque no es necesario que
        //el codfacultad y codubicacion sean los mismos antes y despues de la actualizacion
        String estado = helper.actualizar2(empleado);
        helper.cerrar();

        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto (View v){
        editCodEmpleado.setText("");
        editCodFacultad.setText("");
        editCodUbicacion.setText("");
        editNomEmpleado.setText("");
        editApeEmpleado.setText("");
        editTelEmpleado.setText("");
    }
}
