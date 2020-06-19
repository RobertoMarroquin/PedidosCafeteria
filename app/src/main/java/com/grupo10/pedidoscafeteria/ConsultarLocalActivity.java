package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ConsultarLocalActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodLocal, editCodEncargadoLocal, editNombreLocal;

    Bundle recibido;
    Usuario usuarioRecibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_local);
        helper = new ControlBD(this);
        editCodLocal = (EditText) findViewById(R.id.editCodLocal);
        editCodEncargadoLocal = (EditText) findViewById(R.id.editCodEncargadoLocal);
        editNombreLocal = (EditText) findViewById(R.id.editNomLocal);

        //obteniendo el bundle y usando el objeto usuario que trae
        recibido = getIntent().getExtras();
        usuarioRecibido = (Usuario) recibido.getSerializable("usuario");


        //para que el campo de codempleado ya quede con el mismo de nombreusuario
        editCodEncargadoLocal.setText(usuarioRecibido.getNombreusuario());
    }

    public void consultarLocal(View v){
        helper.abrir();
        Local local = helper.consultarLocal(editCodLocal.getText().toString(), editCodEncargadoLocal.getText().toString());
        helper.cerrar();
        if (local==null){
            Toast.makeText(this, "Local no registrado", Toast.LENGTH_LONG).show();
        }
        else {
            editNombreLocal.setText(local.getNombrelocal());
        }
    }

    public void limpiarTexto(View v){
        editCodLocal.setText("");
        editCodEncargadoLocal.setText("");
        editNombreLocal.setText("");
    }



}
