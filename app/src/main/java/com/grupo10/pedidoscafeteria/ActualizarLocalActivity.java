package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActualizarLocalActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editCodLocal, editCodEncargadoLocal, editNomLocal;

    Bundle recibido;
    Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_local);
        helper = new ControlBD(this);
        editCodLocal = (EditText) findViewById(R.id.editCodLocal);
        editCodEncargadoLocal = (EditText) findViewById(R.id.editCodEncargadoLocal);
        editNomLocal = (EditText) findViewById(R.id.editNomLocal);

        //obteniendo el bundle y usando el objeto usuario que trae
        recibido = getIntent().getExtras();
        user = (Usuario) recibido.getSerializable("usuario");


        //para que el campo de codempleado ya quede con el mismo de nombreusuario
        editCodEncargadoLocal.setText(user.getNombreusuario());
    }

    public void actualizarLocal(View v) {
        Local local = new Local();
        local.setCodlocal(editCodLocal.getText().toString());
        local.setCodencargadolocal(editCodEncargadoLocal.getText().toString());
        local.setNombrelocal(editNomLocal.getText().toString());

        helper.abrir();
        String estado = helper.actualizar(local);
        helper.cerrar();

        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto (View v){
        editCodLocal.setText("");
        editCodEncargadoLocal.setText("");
        editNomLocal.setText("");
    }
}
