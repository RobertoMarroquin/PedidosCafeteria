package com.grupo10.pedidoscafeteria;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class ProductosLocalActivity extends AppCompatActivity {
    ControlBD helper;
    ListView listViewProductos;
    Button cancelarbtn, comprarbtn;
    ArrayList<String> listaInfo;
    ArrayList<Producto> listaProducto;
    String localId;
    Usuario user;
    Bundle paqueteR;

    TextToSpeech tts;
    Button leer;

    Button llamar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_local);
        paqueteR = getIntent().getExtras();
        if (paqueteR != null) {
            localId = paqueteR.getString("codlocal");
            user = (Usuario) paqueteR.getSerializable("usuario");
        } else {
            localId = "local1";
        }
        Log.d("paquete", "onCreate: " + localId);
        helper = new ControlBD(this);
        listViewProductos = (ListView) findViewById(R.id.listViewProductos);
        cancelarbtn = (Button) findViewById(R.id.cancelarbtn);
        comprarbtn = (Button) findViewById(R.id.comprarbtn);

        leer = (Button) findViewById(R.id.leer);
        tts = new TextToSpeech(this, OnInit);

        //
        llamar = (Button) findViewById(R.id.llamar);

        if (ActivityCompat.checkSelfPermission(
                ProductosLocalActivity.this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                ProductosLocalActivity.this, Manifest
                        .permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ProductosLocalActivity.this, new String[]
                    {Manifest.permission.SEND_SMS,}, 1000);
        } else {

        }
        ;

        consultarProductosLocal();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInfo);
        listViewProductos.setAdapter(adaptador);

        listViewProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                user = (Usuario) paqueteR.getSerializable("usuario");
                paqueteR.putString("codproducto", listaProducto.get(position).getCodproducto());
                Intent detalleProducto = new Intent(parent.getContext(), DetalleProductoActivity.class);
                detalleProducto.putExtras(paqueteR);
                Log.d("Paquete enviado", "onItemClick: " + detalleProducto.getExtras().getString("codproducto"));
                startActivity(detalleProducto);

            }
        });


        cancelarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paqueteR.getInt("idPedido", 0) != 0) {
                    int pedido = paqueteR.getInt("idPedido");

                    SQLiteDatabase db = helper.abrir2();
                    String[] argumentos = {String.valueOf(pedido),};
                    int cursor = db.delete("pedido", "idpedido = ?", argumentos);
                    paqueteR.remove("idPedido");
                    Intent intent = new Intent(v.getContext(), ListaLocalesActivity.class);
                    intent.putExtras(paqueteR);
                    startActivity(intent);
                    finish();
                }
            }
        });

        comprarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paqueteR.getInt("idPedido", 0) != 0) {
                    int pedido = paqueteR.getInt("idPedido");
                    SQLiteDatabase db = helper.abrir2();
                    ContentValues values = new ContentValues();
                    values.put("codestadopedido", "CO");
                    String[] argumentos = {String.valueOf(pedido),};
                    int cursor = db.update("pedido", values, "idpedido = ?", argumentos);
                    // obtengo el codigo de encargado
                    String[] camposlocal = {"codencargadolocal"};
                    String[] argumentoslocal = {localId,};
                    String varEncargado = "";
                    Cursor curlocal = db.query("local", camposlocal, "codlocal=?", argumentoslocal, null, null, null);
                    curlocal.moveToLast();
                    varEncargado = curlocal.getString(0);

                    //Obtengo el telefono del encargado
                    String[] camposEncargado = {"telencargadolocal"};
                    String[] argumentosEncargado = {varEncargado,};
                    String varTelefono = "";
                    Cursor curEncargado = db.query("encargadolocal", camposEncargado, "codencargadolocal=?", argumentosEncargado, null, null, null);
                    curEncargado.moveToLast();
                    varTelefono = curEncargado.getString(0);

                    //Obtengo el correo del encargado
                    String[] camposEmpleado = {"correoempleado"};
                    String[] argumentosEmpleado = {user.getNombreusuario(),};
                    String varCorreo = "";
                    Cursor curEmpleado = db.query("empleado", camposEmpleado, "codempleado=?", argumentosEmpleado, null, null, null);
                    curEmpleado.moveToLast();
                    varCorreo = curEmpleado.getString(0);


                    enviarmensaje(varTelefono, "Favor Revisar Pedido Procesado " + pedido);
                    sendMail(varCorreo, "Su Pedido fue Procesado No" + pedido + "  ... Espere pronto su entrega, Gracias por su compra ", "confirmacion Pedido No." + pedido);

                    Intent intent = new Intent(v.getContext(), ListaLocalesActivity.class);
                    intent.putExtra("usuario", user);
                    startActivity(intent);
                    finish();
                }
            }
        });

        //para el tts
        leer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.speak(listaInfo.toString(), TextToSpeech.QUEUE_ADD, null);
            }
        });

        //para llamar
        llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paqueteR.getInt("idPedido", 0) != 0) {
                    int pedido = paqueteR.getInt("idPedido");
                    SQLiteDatabase db = helper.abrir2();
                    ContentValues values = new ContentValues();
                    values.put("codestadopedido", "CO");
                    String[] argumentos = {String.valueOf(pedido),};
                    int cursor = db.update("pedido", values, "idpedido = ?", argumentos);


                }
                SQLiteDatabase db = helper.abrir2();
                // obtengo el codigo de encargado
                String[] camposlocal = {"codencargadolocal"};
                String[] argumentoslocal = {localId,};
                String varEncargado = "";
                Cursor curlocal = db.query("local", camposlocal, "codlocal=?", argumentoslocal, null, null, null);
                curlocal.moveToLast();
                varEncargado = curlocal.getString(0);

                //Obtengo el telefono del encargado
                String[] camposEncargado = {"telencargadolocal"};
                String[] argumentosEncargado = {varEncargado,};
                String varTelefono = "";
                Cursor curEncargado = db.query("encargadolocal", camposEncargado, "codencargadolocal=?", argumentosEncargado, null, null, null);
                curEncargado.moveToLast();
                varTelefono = curEncargado.getString(0);


                    /*
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }

                     */
                String dial = "tel:" + varTelefono;
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));

                Toast.makeText(getApplicationContext(), "Llamando al encargado de local", Toast.LENGTH_LONG).show();



            }
        });


    }

    @Override
    public void onBackPressed()
    {

        Intent intent = new Intent(this, ListaLocalesActivity.class);
        intent.putExtra("usuario",user);
        startActivity(intent);
        finish();
    }

    private void consultarProductosLocal() {
        SQLiteDatabase db = helper.abrir2();
        listaProducto = new ArrayList<Producto>();
        Producto producto = null;

        String[] argumentos = {localId,};
        Cursor cursor = db.rawQuery("Select codproducto, nombreproducto, preciounitario, producto.codmenu from producto " +
                "inner join menu on menu.codmenu = producto.codmenu " +
                "where menu.codlocal =? ",argumentos);

        while (cursor.moveToNext()){
            producto = new Producto();
            producto.setCodproducto(cursor.getString(0));
            producto.setNombreproducto(cursor.getString(1));
            producto.setPreciounitario(cursor.getFloat(2));
            producto.setCodmenu(cursor.getString(3));

            listaProducto.add(producto);

        }
        obtenerlista();
        cursor.close();
    }

    public void enviarmensaje(String numero,String mensaje){
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(numero,null,mensaje,null,null);
            Toast.makeText(getApplicationContext(),"mensaje enviado",Toast.LENGTH_LONG).show();
        } catch (Exception e){
            Toast.makeText(getApplicationContext(),"mensaje no enviado, Datos incorrectos",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


    private void sendMail(String mail,String message,String subject) {
        //Send Mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(this,mail,subject,message);

        javaMailAPI.execute();

    }



    private void obtenerlista() {
        listaInfo = new ArrayList<String>();
        for (int i = 0; i < listaProducto.size(); i++){
            listaInfo.add(listaProducto.get(i).getCodproducto() + " - "+ listaProducto.get(i).getNombreproducto());
        }
    }


    //para el tts
    TextToSpeech.OnInitListener OnInit = new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int status) {
            // TODO Auto-generated method stub
            if (TextToSpeech.SUCCESS==status){
                tts.setLanguage(new Locale("spa","ESP"));
            }
            else
            {
                Toast.makeText(getApplicationContext(), "TTS no disponible",
                        Toast.LENGTH_SHORT).show();
            }
        }
    };
    View.OnClickListener onClick=new View.OnClickListener() {
        @SuppressLint("SdCardPath")
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if (v.getId()==R.id.leer){
                tts.speak(listaInfo.toString(), TextToSpeech.QUEUE_ADD, null);
            }

        }
    };
    public void onDestroy(){
        tts.shutdown();
        super.onDestroy();
    }


}
