package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editNomUsuario, editPass;
    Button btnRegister, btnLogin, btnLlenarBase;
    ControlBD helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new ControlBD(this);
        editNomUsuario = (EditText) findViewById(R.id.editNomusuario);
        editPass = (EditText) findViewById(R.id.editPass);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLlenarBase = (Button) findViewById(R.id.btnFillDB);

        //PARA LLENAR LA BASE CON ALGUNOS USUARIOS
        btnLlenarBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.abrir();
                String mensaje = helper.llenarBase();
                helper.cerrar();
                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
            }
        });

        //LLEVA A LA PANTALLA PARA CREAR UN NUEVO PERFIL
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PARA MOVERSE AL OTRO ACTIVITY
                Intent intent = new Intent(v.getContext(), CrearUsuarioActivity.class);
                startActivity(intent);

            }
        });
        //PARA LOGUEARSE
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.abrir();
                Usuario usuario = helper.ConsultarUsuario(editNomUsuario.getText().toString());
                helper.cerrar();
                if (usuario==null){
                    Toast.makeText(getApplicationContext(),"DATOS INCORRECTOS", Toast.LENGTH_SHORT).show();
                }
                else{
                    helper.abrir();
                    if (usuario.getUsuario().equals("Empleado")){
                        //EL USUARIO EXISTE Y SE TRATA DE UN EMPLEADO
                        //EL CURSOR REVISA QUE EXISTA EL USUARIO Y PASS
                        Cursor c = helper.ConsultarUsuPass(editNomUsuario.getText().toString(), editPass.getText().toString());

                        if (c.getCount()>0){
                            Bundle user = new Bundle();
                            user.putSerializable("usuario",usuario);
                            Intent menuEmpleado = new Intent(v.getContext(), EmpleadoMenuActivity.class);
                            menuEmpleado.putExtra("usuario",user);
                            startActivity(menuEmpleado);
                            //Toast.makeText(getApplicationContext(), "Datos de empleado CORRECTOS", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Dato de empleado ERRONEOS", Toast.LENGTH_SHORT).show();
                        }

                    } else
                        { // EL USUARIO EXISTE PERO SE TRATA DE UN ENCARGADO DE LOCAL
                        Cursor c2 = helper.ConsultarUsuPass(editNomUsuario.getText().toString(), editPass.getText().toString());
                        if (c2.getCount()>0){
                            Intent menuEncargadoLocal = new Intent(v.getContext(), EncargadoLocalMenuActivity.class);
                            startActivity(menuEncargadoLocal);
                            //Toast.makeText(getApplicationContext(), "Datos de encargado de local CORRECTOS", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Datos de encargado de local ERRONEOS", Toast.LENGTH_SHORT).show();
                        }

                    }
                    //Toast.makeText(getApplicationContext(), "Si hay datos", Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(v.getContext(), EmpleadoMenuActivity.class);
                    //startActivity(intent);
                helper.cerrar();
                }



            }
        });


    }




}
