package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class InsertarEmpleadoActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodEmpleado, editCodFacultad, editCodUbicacion;
    EditText editNomEmpleado, editApeEmpleado, editTelEmpleado;

    EditText editCorreoEmpleado;

    //aqui si se crearan el bundle y el usuario.
    //ya que se usara el nombreusuario para hacerlo igual al codempleado.

    Bundle recibido;
    Usuario usuarioRecibido;

    Spinner spCodFacultad, spCodUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_empleado);
        helper = new ControlBD(this);
        editCodEmpleado = (EditText) findViewById(R.id.editCodEmpleado);
        editCodFacultad = (EditText) findViewById(R.id.editCodFacultad);
        editCodUbicacion = (EditText) findViewById(R.id.editCodUbicacion);
        editNomEmpleado = (EditText) findViewById(R.id.editNomEmpleado);
        editApeEmpleado = (EditText) findViewById(R.id.editApeEmpleado);
        editTelEmpleado = (EditText) findViewById(R.id.editTelEmpleado);

        editCorreoEmpleado = (EditText) findViewById(R.id.editCorreoEmpleado);

        spCodFacultad = (Spinner) findViewById(R.id.spCodFacultad);
        spCodUbicacion = (Spinner) findViewById(R.id.spCodUbicacion);

        //obteniendo el bundle y usando el objeto usuario que trae
        recibido = getIntent().getExtras();
        usuarioRecibido = (Usuario) recibido.getSerializable("usuario");


        //para que el campo de codempleado ya quede con el mismo de nombreusuario
        editCodEmpleado.setText(usuarioRecibido.getNombreusuario());

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

    public void insertarEmpleado(View v){
        String regInsertados;

        String codempleado = editCodEmpleado.getText().toString();

        //String codfacultad = editCodFacultad.getText().toString();
        String codfacultad = spCodFacultad.getSelectedItem().toString();
        //String codubicacion = editCodUbicacion.getText().toString();
        String codubicacion = spCodUbicacion.getSelectedItem().toString();
        String nomempleado = editNomEmpleado.getText().toString();
        String apeempleado = editApeEmpleado.getText().toString();
        String telempleado = editTelEmpleado.getText().toString();
        String correoempleado = editCorreoEmpleado.getText().toString();

        Empleado empleado = new Empleado();
        empleado.setCodempleado(codempleado);
        empleado.setCodfacultad(codfacultad);
        empleado.setCodubicacion(codubicacion);
        empleado.setNomempleado(nomempleado);
        empleado.setApeempleado(apeempleado);
        empleado.setTelempleado(telempleado);
        empleado.setCorreoempleado(correoempleado);

        helper.abrir();
        regInsertados = helper.insertar(empleado);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        editCodEmpleado.setText("");

        editNomEmpleado.setText("");
        editApeEmpleado.setText("");
        editTelEmpleado.setText("");
    }
}
