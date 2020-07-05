package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DatosEncargadoLocalMenuActivity extends AppCompatActivity {
    Button btnIngresarEncargadoLocal, btnActualizarEncargadoLocal, btnConsultarEncargadoLocal;
    Button btnELiminarEncargadoLocal;

    Bundle recibido;

    Usuario user;

    ControlBD helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_encargado_local_menu);
        btnIngresarEncargadoLocal = (Button) findViewById(R.id.btnIngresarEncargadoLocal);
        btnActualizarEncargadoLocal = (Button) findViewById(R.id.btnActualizarEncargadoLocal);
        btnConsultarEncargadoLocal = (Button) findViewById(R.id.btnConsultarEncargadoLocal);
        btnELiminarEncargadoLocal = (Button) findViewById(R.id.btnEliminarEncargadoLocal);
        helper = new ControlBD(this);

        recibido = getIntent().getExtras();

        //se asigna a un objeto usuario los datos del bundle ========================
        user = (Usuario) recibido.getSerializable("usuario");

        btnIngresarEncargadoLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingresarEncargadoLocal = new Intent(v.getContext(), InsertarEncargadoLocalActivity.class);

                ingresarEncargadoLocal.putExtras(recibido);
                if (existe(user)){
                    Toast.makeText(getApplicationContext(), "Este usuario ya tiene el perfil completo", Toast.LENGTH_LONG).show();
                } else{
                    startActivity(ingresarEncargadoLocal);
                }

            }
        });

        btnActualizarEncargadoLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actualizarEncargoLocal = new Intent(v.getContext(), ActualizarEncargadoLocalActivity.class);
                actualizarEncargoLocal.putExtras(recibido);
                startActivity(actualizarEncargoLocal);
            }
        });

        btnConsultarEncargadoLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent consultarEncargadoLocal = new Intent(v.getContext(), ConsultarEncargadoLocalActivity.class);
                consultarEncargadoLocal.putExtras(recibido);
                startActivity(consultarEncargadoLocal);
            }
        });

        btnELiminarEncargadoLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eliminarEncargadoLocal = new Intent(v.getContext(), EliminarEncargadoLocalActivity.class);
                eliminarEncargadoLocal.putExtras(recibido);
                startActivity(eliminarEncargadoLocal);
            }
        });

    }

    public boolean existe(Usuario usuario){
        String[] id = {usuario.getNombreusuario()};
        SQLiteDatabase db = helper.abrir2();
        Cursor c = db.rawQuery("select codencargadolocal, nomencargadolocal, apeencargadolocal, telencargadolocal from encargadolocal where codencargadolocal = ?", id);
        if (c.moveToFirst()){
            return true;
        }
        else {
            return false;
        }
    }
}
