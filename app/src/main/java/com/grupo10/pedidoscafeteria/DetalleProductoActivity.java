package com.grupo10.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class DetalleProductoActivity extends AppCompatActivity {
    Bundle paquete;
    Usuario user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        paquete = getIntent().getExtras();
        user = (Usuario) paquete.getSerializable("usuario");
        Log.d("Detalle  Producto", "onCreate: "+user.getNombreusuario());
    }
}
