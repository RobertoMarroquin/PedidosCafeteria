package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CrearUsuarioActivity extends AppCompatActivity {
    EditText editUsuario, editPass;
    Spinner spinner;
    Button btnCreate, btnClean;
    ControlBD helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);
        helper = new ControlBD(this);
        editUsuario = (EditText) findViewById(R.id.editUsuario);
        editPass = (EditText) findViewById(R.id.editPass);
        spinner = (Spinner) findViewById(R.id.spinner);
        btnCreate = (Button) findViewById(R.id.btnCreateUser);
        btnClean = (Button) findViewById(R.id.btnCleanText);
    }

    public void crearUsuario(View v){
        String nombreUsuario = editUsuario.getText().toString();
        String pass = editPass.getText().toString();
        String tipoUsuario = spinner.getSelectedItem().toString();

        String reginsertados;
        Usuario usuario = new Usuario();
        usuario.setUsuario(tipoUsuario);
        usuario.setContrasena(pass);
        usuario.setNombreusuario(nombreUsuario);
        helper.abrir();
        reginsertados = helper.insertar(usuario);
        helper.cerrar();
        Toast.makeText(this, reginsertados, Toast.LENGTH_LONG).show();
    }

    public void limpiarTexto(View v){
        editUsuario.setText("");
        editPass.setText("");
    }




}
