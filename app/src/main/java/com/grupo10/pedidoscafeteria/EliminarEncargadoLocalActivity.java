package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarEncargadoLocalActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodEncargadoLocal;

    Bundle recibido;
    Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_encargado_local);
        helper = new ControlBD(this);
        editCodEncargadoLocal = (EditText) findViewById(R.id.editCodEncargadoLocal);

        //obteniendo el bundle y usando el objeto usuario que trae
        recibido = getIntent().getExtras();
        user = (Usuario) recibido.getSerializable("usuario");


        //para que el campo de codempleado ya quede con el mismo de nombreusuario
        editCodEncargadoLocal.setText(user.getNombreusuario());
    }

    public void eliminarEncargadoLocal(View v){
        String regEliminadas;
        EncargadoLocal encargadoLocal = new EncargadoLocal();
        encargadoLocal.setCodencargadolocal(editCodEncargadoLocal.getText().toString());
        helper.abrir();
        regEliminadas = helper.eliminar(encargadoLocal);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();

        Intent inicio = new Intent(v.getContext(), MainActivity.class);
        startActivity(inicio);
    }
}
