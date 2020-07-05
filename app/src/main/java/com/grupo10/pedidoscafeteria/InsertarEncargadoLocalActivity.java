package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertarEncargadoLocalActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodEncargadoLocal, editNomEncargadoLocal, editApeEncargadoLocal, editTelEncargadoLocal;

    Bundle recibido;
    Usuario usuarioRecibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_encargado_local);
        helper = new ControlBD(this);
        editCodEncargadoLocal = (EditText) findViewById(R.id.editCodEncargadoLocal);
        editNomEncargadoLocal = (EditText) findViewById(R.id.editNomEncargadoLocal);
        editApeEncargadoLocal = (EditText) findViewById(R.id.editApeEncargadoLocal);
        editTelEncargadoLocal = (EditText) findViewById(R.id.editTelEncargadoLocal);

        //obteniendo el bundle y usando el objeto usuario que trae
        recibido = getIntent().getExtras();
        usuarioRecibido = (Usuario) recibido.getSerializable("usuario");


        //para que el campo de codempleado ya quede con el mismo de nombreusuario
        editCodEncargadoLocal.setText(usuarioRecibido.getNombreusuario());
    }

    public void insertarEncargadoLocal(View v){
        String codencargadolocal = editCodEncargadoLocal.getText().toString();
        String nomencargadolocal = editNomEncargadoLocal.getText().toString();
        String apeencargadolocal = editApeEncargadoLocal.getText().toString();
        String telencargadolocal = editTelEncargadoLocal.getText().toString();

        String reginsertados;

        EncargadoLocal encargadoLocal = new EncargadoLocal();
        encargadoLocal.setCodencargadolocal(codencargadolocal);
        encargadoLocal.setNomencargadolocal(nomencargadolocal);
        encargadoLocal.setApeencargadolocal(apeencargadolocal);
        encargadoLocal.setTelencargadolocal(telencargadolocal);

        helper.abrir();
        reginsertados = helper.insertar(encargadoLocal);
        helper.cerrar();
        Toast.makeText(this, reginsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        editCodEncargadoLocal.setText("");
        editNomEncargadoLocal.setText("");
        editApeEncargadoLocal.setText("");
        editTelEncargadoLocal.setText("");
    }
}
