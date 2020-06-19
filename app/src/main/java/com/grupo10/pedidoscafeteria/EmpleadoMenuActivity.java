package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EmpleadoMenuActivity extends AppCompatActivity {
    Button datosEmpleado, datosPedidos, datosLocales;
    Usuario user;

    Bundle objetosRecibidos;

    //para ver si el empleado esta vacio, si esta vacio NO pasar a los locales.
    ControlBD helper;
    Empleado emp;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado_menu);
        helper = new ControlBD(this);

        //en el bundle recien creado se colocan las extras que trae del menu anterior
        objetosRecibidos = getIntent().getExtras();

        //se asigna a un objeto usuario los datos del bundle ========================
        user = (Usuario) objetosRecibidos.getSerializable("usuario");
        //no usado en esta pantalla =================================================


        datosEmpleado = (Button) findViewById(R.id.btnDatosEmpleado);
        datosLocales = (Button) findViewById(R.id.btnLocal);
        datosPedidos = (Button) findViewById(R.id.btnPedidos);

        //PARA GESTIONAR EL EMPLEADO.
        //tablas de FACULTAD, UBICACION Y EMPLEADO
        datosEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuFacUbiEmp = new Intent(v.getContext(), FacultadUbicacionEmpleadoMenu.class);
                //se envia nuevamente el bundle hacia la siguiente pantalla.
                menuFacUbiEmp.putExtras(objetosRecibidos);
                startActivity(menuFacUbiEmp);
            }
        });

        //
        // PARA INGRESAR A LOS PEDIDOS
        datosPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listaPedidos = new Intent(v.getContext(),ListaPedidosActivity.class);
                listaPedidos.putExtras(objetosRecibidos);
                startActivity(listaPedidos);
            }
        });

        //
        // PARA INGRESAR A LOS LOCALES
        datosLocales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listaLocales = new Intent(v.getContext(),ListaLocalesActivity.class);
                listaLocales.putExtras(objetosRecibidos);

                if (estaVacio(user)){
                    startActivity(listaLocales);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Para relizar un pedido, completa la información de tu perfil!", Toast.LENGTH_LONG).show();
                }

                //startActivity(listaLocales);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public boolean estaVacio(Usuario usuario){
        String[] id = {usuario.getNombreusuario()};
        SQLiteDatabase db = helper.abrir2();
        Cursor c = db.rawQuery("select codempleado, codfacultad, codubicacion, nomempleado, apeempleado, telempleado from empleado where codempleado = ?", id);
        if (c.moveToFirst()){
            return true;
        }
        else {
            return false;
        }
    }

}
