package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActualizarEmpleadoActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodEmpleado, editCodFacultad, editCodUbicacion;
    EditText editNomEmpleado, editApeEmpleado, editTelEmpleado;

    Bundle recibido;
    Usuario user;

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

        //obteniendo el bundle y usando el objeto usuario que trae
        recibido = getIntent().getExtras();
        user = (Usuario) recibido.getSerializable("usuario");


        //para que el campo de codempleado ya quede con el mismo de nombreusuario
        editCodEmpleado.setText(user.getNombreusuario());
    }

    public void actualizarEmpleado(View v) {
        Empleado empleado = new Empleado();
        empleado.setCodempleado(editCodEmpleado.getText().toString());
        empleado.setCodfacultad(editCodFacultad.getText().toString());
        empleado.setCodubicacion(editCodUbicacion.getText().toString());
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
