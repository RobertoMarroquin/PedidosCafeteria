package com.grupo10.pedidoscafeteria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;
import android.util.Log;
import java.util.ArrayList;

public class ControlBD {
    //listas de todos los campos de todas las tablas
    // =====================================================SE USAN PARA LAS CONSULTAS
    private static final String[] camposUsuario = new String[]{"nombreusuario", "pass", "usuario"};
    private static final String[] camposAccesoUsuario = new String[]{"usuario", "idopcion"};
    private static final String[] camposOpcionCRUD = new String[]{"idopcion", "desopcion", "numcrud"};
    private static final String[] camposFacultad = new String[]{"codfacultad", "nomfacultad"};
    private static final String[] camposUbicacion = new String[]{"codubicacion", "descubicacion"};
    private static final String[] camposEmpleado = new String[]{"codempleado", "codfacultad", "codubicacion", "nomempleado", "apeempleado", "telempleado"};
    private static final String[] camposEncargadoLocal = new String[]{"codencargadolocal", "nomencargadolocal", "apeencargadolocal", "telencargadolocal"};
    private static final String[] camposLocal = new String[]{"codlocal", "codencargadolocal", "nombrelocal"};
    private static final String[] camposMenu = new String[]{"codmenu", "codlocal", "preciomenu", "fechadesdemenu", "fechahastamenu"};
    private static final String[] camposProducto = new String[]{"codproducto", "codmenu", "nombreproducto", "preciounitario"};
    private static final String[] camposRepartidor = new String[]{"codrepartidor", "nomrepartidor", "aperepartidor", "telrepartidor"};
    private static final String[] camposRutaPedido = new String[]{"idruta", "codrepartidor", "inicioruta", "finruta"};



    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlBD(Context ctx){
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    public static class DatabaseHelper extends SQLiteOpenHelper{
        private static final String BASE_DATOS = "aaaa.s3db";
        private static final int VERSION = 1;

        public DatabaseHelper(Context context){
            super(context, BASE_DATOS, null, VERSION);
        }

        public void onCreate(SQLiteDatabase db){
            try{
                //db.execSQL("drop table if exists usuario");
                //db.execSQL("drop table if exists opcioncrud");
                //db.execSQL("drop table if exists accesousuario");
                //db.execSQL("drop table if exists facultad");
                //db.execSQL("drop table if exists empleado");
                //db.execSQL("drop table if exists encargadolocal");
                //db.execSQL("drop table if exists local");
                //db.execSQL("drop table if exists menu");
                //db.execSQL("drop table if exists producto");
                //db.execSQL("drop table if exists pedido");
                //db.execSQL("drop table if exists pedidosasignados");
                //db.execSQL("drop table if exists detalleproductoempleado");
                //los db.execSQL de crear la base
                //=============================================bases parA LOGIN / OPCIONCRUD ACCESOUSUARIO / NO SE USAN aún
                db.execSQL("CREATE TABLE usuario (nombreusuario VARCHAR(7) NOT NULL PRIMARY KEY, pass VARCHAR(10) NOT NULL, usuario VARCHAR(256) NOT NULL);");
                db.execSQL("CREATE TABLE opcioncrud (idopcion CHAR(3) NOT NULL PRIMARY KEY, desopcion VARCHAR(30) NOT NULL, numcrud INTEGER NOT NULL);");
                db.execSQL("CREATE TABLE accesousuario (nombreusuario VARCHAR(7) NOT NULL, idopcion CHAR(3) NOT NULL, PRIMARY KEY (nombreusuario, idopcion));");
                db.execSQL("create TABLE facultad (codfacultad VARCHAR(7) NOT NULL PRIMARY KEY, nomfacultad VARCHAR(30));");
                db.execSQL("create TABLE ubicacion (codubicacion VARCHAR(7) NOT NULL PRIMARY KEY, descubicacion VARCHAR(100));");
                db.execSQL("create TABLE empleado (codempleado VARCHAR(15) NOT NULL PRIMARY KEY, codfacultad VARCHAR(7) NOT NULL, codubicacion VARCHAR(7) NOT NULL, nomempleado VARCHAR(30), apeempleado VARCHAR(30), telempleado VARCHAR(8), codlocal VARCHAR(7));");
                db.execSQL("create TABLE encargadolocal (codencargadolocal VARCHAR(10) NOT NULL PRIMARY KEY, nomencargadolocal VARCHAR(30), apeencargadolocal VARCHAR(30), telencargadolocal VARCHAR(8));");
                db.execSQL("create TABLE local (codlocal VARCHAR(10) NOT NULL PRIMARY KEY, codencargadolocal VARCHAR(19) NOT NULL, nombrelocal VARCHAR(50));");
                db.execSQL("create TABLE menu (codmenu VARCHAR(10) NOT NULL PRIMARY KEY, codlocal VARCHAR(10) NOT NULL, preciomenu REAL, fechadesdemenu VARCHAR(15), fechahastamenu VARCHAR(15));");
                db.execSQL("create TABLE producto (codproducto VARCHAR(10) NOT NULL PRIMARY KEY, codmenu VARCHAR(10) NOT NULL, nombreproducto VARCHAR(50), preciounitario REAL);");
                db.execSQL("create TABLE pedido (idpedido INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT, idruta INTEGER, codestadopedido VARCHAR(2), codlocal VARCHAR(10) NOT NULL,fechapedido VARCHAR(10));");
                db.execSQL("create TABLE pedidosasignados (codtrabajador VARCHAR(15) NOT NULL, idpedido INTEGER NOT NULL, PRIMARY KEY (codtrabajador,idpedido));");
                db.execSQL("create TABLE detalleproductoempleado ( iddpe INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,  idpedido INTEGER NOT NULL, codtrabajador VARCHAR(10) NOT NULL, codproducto VARCHAR(10) NOT NULL, idpedidosasignados INTEGER NOT NULL, cantidadpedido INTEGER );");
                db.execSQL("create table repartidor (codrepartidor VARCHAR(20) NOT NULL PRIMARY KEY, nomrepartidor VARCHAR(30), aperepartidor VARCHAR(30), telrepartidor VARCHAR(10));");
                db.execSQL("create table rutapedido (idruta INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, codrepartidor VARCHAR(20) NOT NULL, inicioruta VARCHAR(10), finruta VARCHAR(10))");
                db.execSQL("create table estadopedido (codestadopedido VARCHAR(2) NOT NULL PRIMARY KEY, descestadopedido VARCHAR(30));");

            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
           //
        }
    }

    public void abrir() throws SQLException{
        db = DBHelper.getWritableDatabase();
    }

    public SQLiteDatabase abrir2() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return db;
    }

    public void cerrar() {

        DBHelper.close();
    }


//============================================================================ CRUD TABLA USUARIO
    //INSERTAR USUARIO
    public String insertar(Usuario usuario){
        String regInsertados ="Registro n. ";
        long contador;

        ContentValues usu = new ContentValues();
        usu.put("nombreusuario", usuario.getNombreusuario());
        usu.put("pass", usuario.getContrasena());
        usu.put("usuario", usuario.getUsuario());
        contador = db.insert("usuario", null, usu);
        if (contador==-1 || contador==0){
            regInsertados = "Error";
        } else {
            regInsertados = regInsertados + contador ;
        }
        return regInsertados;
    }
    // CONSULTAR USUARIO
    public Usuario ConsultarUsuario(String nombreusuario){
        String [] id = {nombreusuario};
        Cursor cursor = db.query("usuario", camposUsuario, "nombreusuario = ?", id,null, null, null);
        if (cursor.moveToFirst()){
            Usuario usuario = new Usuario();
            usuario.setNombreusuario(cursor.getString(0));
            usuario.setContrasena(cursor.getString(1));
            usuario.setUsuario(cursor.getString(2));
            cursor.close();
            return usuario;
        } else {
            return null;
        }
    }
    //ELIMINAR USUARIO
    //ACTUALIZAR USUARIO


    //==================================================================================TABLA ESTADO PEDIDO
    //INSERTAR ESTADO PEDIDO
    public String insertar(EstadoPedido estado){
        String regInsertados ="Registro n. ";
        long contador;
        ContentValues esta = new ContentValues();
        esta.put("codestadopedido", estado.getCodestadopedido());
        esta.put("descestadopedido", estado.getDescestadopedido());
        contador = db.insert("estadopedido", null, esta);
        if (contador==-1 || contador==0){
            regInsertados = "Error";
        } else {
            regInsertados = regInsertados + contador ;
        }
        return regInsertados;
    }



//==================================================================================TABLA FACULTAD
    //INSERTAR FACULTAD
    public String insertar(Facultad facultad){
        String regInsertados ="Registro n. ";
        long contador;
        ContentValues facu = new ContentValues();
        facu.put("codfacultad", facultad.getCodfacultad());
        facu.put("nomfacultad", facultad.getNomfacultad());
        contador = db.insert("facultad", null, facu);
        if (contador==-1 || contador==0){
            regInsertados = "Error";
        } else {
            regInsertados = regInsertados + contador ;
        }
        return regInsertados;
    }

    //ACTUALIZAR FACULTAD
    public String actualizar(Facultad facultad) {
        if (verificarIntegridad(facultad,1)){ //verificar si existe la facultad
            String [] id = {facultad.getCodfacultad()};
            ContentValues cf = new ContentValues();
            cf.put("nomfacultad", facultad.getNomfacultad());
            db.update("facultad", cf, "codfacultad = ?", id);
            return "Registro actualizado correctamente";
        } else {
            return "Registro con codigo" + facultad.getCodfacultad() + "no existe";
        }
    }

    //ELIMINAR FACULTAD
    public String eliminar(Facultad facultad) {
        String regAfectados = "Filas afectadas = ";
        int contador = 0;
        /*
        //AL ELIMINAR FACULTAD VER SI HAY EMPLEADOS ASOCIADOS......................................
        if (verificarIntegridad(alumno,2)){                      SERIA RELACION 2
            contador+=db.delete("nota", "carnet = '" + alumno.getCarnet() + "'", null);
        }
        */
        contador+=db.delete("facultad", "codfacultad = '" + facultad.getCodfacultad() +"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

    //CONSULTAR FACULTAD
    public Facultad consultarFacultad(String codfacultad) {
        String [] id = {codfacultad};
        Cursor cursor = db.query("facultad", camposFacultad, "codfacultad = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Facultad facultad = new Facultad();
            facultad.setCodfacultad(cursor.getString(0));
            facultad.setNomfacultad(cursor.getString(1));
            cursor.close();
            return facultad;
        }
        else{
            return null;
        }
    }

//=================================================================================TABLA UBICACION
    //INSERTAR UBICACION
    public String insertar(Ubicacion ubicacion){
        String regInsertados ="Registro n. ";
        long contador;
        ContentValues ubi = new ContentValues();
        ubi.put("codubicacion", ubicacion.getCodubicacion());
        ubi.put("descubicacion", ubicacion.getDescubicacion());
        contador = db.insert("ubicacion", null, ubi);
        if (contador==-1 || contador==0){
            regInsertados = "Error";
        } else {
            regInsertados = regInsertados + contador ;
        }
        return regInsertados;
    }

    //ACTUALIZAR UBICACION
    public String actualizar(Ubicacion ubicacion) {
        if (verificarIntegridad(ubicacion,3)){ //verificar si existe la ubicacion
            String [] id = {ubicacion.getCodubicacion()};
            ContentValues cu = new ContentValues();
            cu.put("descubicacion", ubicacion.getDescubicacion());
            db.update("ubicacion", cu, "codubicacion = ?", id);
            return "Registro actualizado correctamente";
        } else {
            return "Registro con codigo" + ubicacion.getCodubicacion() + "no existe";
        }
    }

    //CONSULTAR UBICACION
    public Ubicacion consultarUbicacion(String codubicacion) {
        String [] id = {codubicacion};
        Cursor cursor = db.query("ubicacion", camposUbicacion, "codubicacion = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Ubicacion ubicacion = new Ubicacion();
            ubicacion.setCodubicacion(cursor.getString(0));
            ubicacion.setDescubicacion(cursor.getString(1));
            cursor.close();
            return ubicacion;
        }
        else{
            return null;
        }
    }

    //ELIMINAR UBICACION
    public String eliminar(Ubicacion ubicacion) {
        String regAfectados = "Filas afectadas = ";
        int contador = 0;
        /*
        //AL ELIMINAR UBICACION VER SI HAY EMPLEADOS ASOCIADOS......................................
        if (verificarIntegridad(alumno,4)){                      SERIA RELACION 4
            contador+=db.delete("empleado", "codubicacion = '" + ubicacion.getCodUbicacion() + "'", null);
        }
        */
        contador+=db.delete("ubicacion", "codubicacion = '" + ubicacion.getCodubicacion() +"'", null);
        regAfectados+=contador;
        return regAfectados;
    }



//====================================================================================TABLA EMPLEADO
    //INSERTAR EMPLEADO
    public String insertar(Empleado empleado) {
        String regInsertados = "Registro insertado n° ";
        long contador = 0;

        if (verificarIntegridad(empleado,5)){
            ContentValues emp = new ContentValues();
            emp.put("codempleado", empleado.getCodempleado());
            emp.put("codfacultad", empleado.getCodfacultad());
            emp.put("codubicacion", empleado.getCodubicacion());
            emp.put("nomempleado", empleado.getNomempleado());
            emp.put("apeempleado", empleado.getApeempleado());
            emp.put("telempleado", empleado.getTelempleado());
          //  emp.put("codlocal", empleado.getCodlocal());
            contador = db.insert("empleado", null, emp);
        }

        if (contador==-1 || contador==0){
            regInsertados = "Error al insetar el registro, Revisar los datos. Verificar";
        }
        else {
        regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    //ACTUALIZAR EMPLEADO
    public String actualizar(Empleado empleado) {
        if (verificarIntegridad(empleado, 6)){
            String[] id = {empleado.getCodempleado(), empleado.getCodfacultad(), empleado.getCodubicacion()};
            ContentValues cve = new ContentValues();
            cve.put("nomempleado", empleado.getNomempleado());
            cve.put("apeempleado", empleado.getApeempleado());
            cve.put("telempleado", empleado.getTelempleado());
            db.update("empleado", cve, "codempleado = ? AND codfacultad = ? AND codubicacion = ?", id);
            return "Registro actualizado correctamente";
        }
        else {
            return "Algunos datos que se están actualizando no existen!";
        }

    }

    //para actualizar el empleado escribiendo solo el codempleado.
    public String actualizar2(Empleado empleado) {
        if (verificarIntegridad(empleado, 19)){
            String[] id = {empleado.getCodempleado()};
            ContentValues cve = new ContentValues();
            cve.put("codfacultad", empleado.getCodfacultad());
            cve.put("codubicacion", empleado.getCodubicacion());
            cve.put("nomempleado", empleado.getNomempleado());
            cve.put("apeempleado", empleado.getApeempleado());
            cve.put("telempleado", empleado.getTelempleado());
            db.update("empleado", cve, "codempleado = ?", id);
            return "Registro actualizado correctamente";
        }
        else {
            return "Registro no existe";
        }

    }

    //CONSULTAR EMPLEADO
    public Empleado consultarEmpleado(String codempleado, String codfacultad, String codubicacion) {
        String[] id = {codempleado,codfacultad,codubicacion};
        Cursor cursor = db.query("empleado", camposEmpleado, "codempleado = ? AND codfacultad = ? AND codubicacion = ?", id, null, null, null);
        if (cursor.moveToFirst()){
            Empleado empleado = new Empleado();
            empleado.setCodempleado(cursor.getString(0));
            empleado.setCodfacultad(cursor.getString(1));
            empleado.setCodubicacion(cursor.getString(2));
            empleado.setNomempleado(cursor.getString(3));
            empleado.setApeempleado(cursor.getString(4));
            empleado.setTelempleado(cursor.getString(5));
//            empleado.setCodlocal(cursor.getString(6));
            cursor.close();
            return empleado;
        }
        else {
            return null;
        }
    }

    //para consultar empleado solo con el codempleado.

    public Empleado consultarEmpleado2(String codempleado) {
        String[] id = {codempleado};
        Cursor cursor = db.query("empleado", camposEmpleado, "codempleado = ?", id, null, null, null);
        if (cursor.moveToFirst()){
            Empleado empleado = new Empleado();
            empleado.setCodempleado(cursor.getString(0));
            empleado.setCodfacultad(cursor.getString(1));
            empleado.setCodubicacion(cursor.getString(2));
            empleado.setNomempleado(cursor.getString(3));
            empleado.setApeempleado(cursor.getString(4));
            empleado.setTelempleado(cursor.getString(5));

            cursor.close();
            return empleado;
        }
        else {
            return null;
        }
    }

    //PARA ELIMINAR EMPLEADO
    public String eliminar(Empleado empleado) {
        String regAfectados = "filas afectadas = ";
        int contador = 0;
        String where ="codempleado = '" + empleado.getCodempleado() +"'";
        where = where+" AND codfacultad = '" + empleado.getCodfacultad() +"'";
        where = where+" AND codubicacion = '" + empleado.getCodubicacion() +"'";
        contador += db.delete("empleado", where , null);
        regAfectados += contador;
        return regAfectados;
    }

    //QUE SE ELIMINE EL USUARIO JUNTO AL EMPLEADO
    public String eliminar2(Empleado empleado) {
        String regAfectados = "Filas afectadas = ";
        int contador = 0;

        //AL ELIMINAR empleado VER SI HAY usuarios ASOCIADOS, y que los elimine.....................
        if (verificarIntegridad(empleado,20)){   //SERIA RELACION 4
            contador+=db.delete("usuario", "nombreusuario = '" + empleado.getCodempleado() + "'", null);
        }

        contador+=db.delete("empleado", "codempleado = '" + empleado.getCodempleado() +"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

//=============================================================================TABLA ENCARGADOLOCAL

    //PARA INSERTAR ENCARGADOLOCAL
    public String insertar(EncargadoLocal encargadop){
        String regInsertados ="Registro n. ";
        long contador;
        ContentValues enc = new ContentValues();
        enc.put("codencargadolocal", encargadop.getCodencargadolocal());
        enc.put("nomencargadolocal", encargadop.getNomencargadolocal());
        enc.put("apeencargadolocal", encargadop.getApeencargadolocal());
        enc.put("telencargadolocal", encargadop.getTelencargadolocal());

        contador = db.insert("encargadolocal", null, enc);
        if (contador==-1 || contador==0){
            regInsertados = "Error";
        } else {
            regInsertados = regInsertados + contador ;
        }
        return regInsertados;
    }

    //PARA ACTUALIZAR ENCARGADOLOCAL
    public String actualizar(EncargadoLocal encargadoLocal) {
        if (verificarIntegridad(encargadoLocal,7)){ //verificar si existe el encargado del local
            String [] id = {encargadoLocal.getCodencargadolocal()};
            ContentValues cel = new ContentValues();
            cel.put("nomencargadolocal", encargadoLocal.getNomencargadolocal());
            cel.put("apeencargadolocal", encargadoLocal.getApeencargadolocal());
            cel.put("telencargadolocal", encargadoLocal.getTelencargadolocal());

            db.update("encargadolocal", cel, "codencargadolocal = ?", id);
            return "Registro actualizado correctamente";
        } else {
            return "Registro con codigo" + encargadoLocal.getCodencargadolocal() + "no existe";
        }
    }

    //PARA CONSULTAR EL ENCARGADO DEL LOCAL
    public EncargadoLocal consultarEncargadoLocal(String codencargadolocal) {
        String [] id = {codencargadolocal};
        Cursor cursor = db.query("encargadolocal", camposEncargadoLocal, "codencargadolocal = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            EncargadoLocal encargadoLocal = new EncargadoLocal();
            encargadoLocal.setCodencargadolocal(cursor.getString(0));
            encargadoLocal.setNomencargadolocal(cursor.getString(1));
            encargadoLocal.setApeencargadolocal(cursor.getString(2));
            encargadoLocal.setTelencargadolocal(cursor.getString(3));
            cursor.close();
            return encargadoLocal;
        }
        else{
            return null;
        }
    }

    //PARA ELIMINAR EL ENCARGADO DEL LOCAL
    public String eliminar(EncargadoLocal encargadoLocal) {
        String regAfectados = "Filas afectadas = ";
        int contador = 0;

        
        //
        //AL ELIMINAR encargado de local VER SI HAY locales ASOCIADOS......................................
        if (verificarIntegridad(encargadoLocal,8)){                      //SERIA RELACION 8
            contador+=db.delete("local", "codencargadolocal = '" + encargadoLocal.getCodencargadolocal() + "'", null);
        }

        contador+=db.delete("encargadolocal", "codencargadolocal = '" + encargadoLocal.getCodencargadolocal() +"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

//====================================================================================== TABLA LOCAL
//INSERTAR LOCAL
    public String insertar(Local local) {
        String regInsertados = "Registro insertado n° ";
        long contador = 0;

        if (verificarIntegridad(local,9)){
            ContentValues loc = new ContentValues();
            loc.put("codlocal", local.getCodlocal());
            loc.put("codencargadolocal", local.getCodencargadolocal());
            loc.put("nombrelocal", local.getNombrelocal());

            contador = db.insert("local", null, loc);
        }

        if (contador==-1 || contador==0){
            regInsertados = "Error al insetar el registro, Revisar los datos. Verificar";
        }
        else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }


    //ACTUALIZAR LOCAL
    public String actualizar(Local local) {
        if (verificarIntegridad(local, 10)){
            String[] id = {local.getCodlocal(), local.getCodencargadolocal()};
            ContentValues cvl = new ContentValues();
            cvl.put("nombrelocal", local.getNombrelocal());

            db.update("local", cvl, "codlocal = ? AND codencargadolocal = ?", id);
            return "Registro actualizado correctamente";
        }
        else {
            return "Registro no existe";
        }

    }


    //CONSULTAR LOCAL
    public Local consultarLocal(String codlocal, String codencargadolocal) {
        String[] id= {codlocal, codencargadolocal};
        Cursor cursor = db.query("local", camposLocal, "codlocal = ? AND codencargadolocal = ?", id, null, null, null);
        if (cursor.moveToFirst()){
            Local local = new Local();
            local.setCodlocal(cursor.getString(0));
            local.setCodencargadolocal(cursor.getString(1));
            local.setNombrelocal(cursor.getString(2));
            cursor.close();
            return local;
        }
        else {
            return null;
        }
    }

    //ELIMINAR LOCAL
    public String eliminar(Local local) {
        String regAfectados = "filas afectadas = ";
        int contador = 0;
        String where ="codlocal = '" + local.getCodlocal() +"'";
        where = where+" AND codencargadolocal = '" + local.getCodencargadolocal() +"'";
        contador += db.delete("local", where , null);
        regAfectados += contador;
        return regAfectados;
    }

//=======================================================================================TABLA MENU

    //INSERTAR MENU
    public String insertar(Menu menu) {
        String regInsertados = "Registro insertado n° ";
        long contador = 0;

        if (verificarIntegridad(menu,11)){
            ContentValues men = new ContentValues();
            men.put("codmenu", menu.getCodmenu());
            men.put("codlocal", menu.getCodlocal());
            men.put("preciomenu", menu.getPreciomenu());
            men.put("fechadesdemenu", menu.getFechadesdemenu());
            men.put("fechahastamenu", menu.getFechahastamenu());

            contador = db.insert("menu", null, men);
        }

        if (contador==-1 || contador==0){
            regInsertados = "Error al insetar el registro, Revisar los datos. Verificar";
        }
        else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    //CONSULTAR MENU
    public Menu consultarMenu(String codmenu, String codlocal) {
        String[] id = {codmenu, codlocal};
        Cursor cursor = db.query("menu", camposMenu, "codmenu = ? AND codlocal = ?", id, null, null, null);
        if (cursor.moveToFirst()){
            Menu menu = new Menu();
            menu.setCodmenu(cursor.getString(0));
            menu.setCodlocal(cursor.getString(1));
            menu.setPreciomenu(cursor.getFloat(2));
            menu.setFechadesdemenu(cursor.getString(3));
            menu.setFechahastamenu(cursor.getString(4));
            cursor.close();
            return menu;
        }
        else {
            return null;
        }
    }

    //ACTUALIZAR MENU
    public String actualizar(Menu menu) {
        if (verificarIntegridad(menu, 12)){
            String[] id = {menu.getCodmenu(), menu.getCodlocal()};
            ContentValues cvm = new ContentValues();
            cvm.put("preciomenu", menu.getPreciomenu());
            cvm.put("fechadesdemenu", menu.getFechadesdemenu());
            cvm.put("fechahastamenu", menu.getFechahastamenu());

            db.update("menu", cvm, "codmenu = ? AND codlocal = ?", id);
            return "Registro actualizado correctamente";
        }
        else {
            return "Registro no existe";
        }

    }

    //ELIMINAR MENU
    public String eliminar(Menu menu) {
        String regAfectados = "filas afectadas = ";
        int contador = 0;
        String where ="codmenu = '" + menu.getCodmenu() +"'";
        where = where+" AND codlocal = '" + menu.getCodlocal() +"'";
        contador += db.delete("menu", where , null);
        regAfectados += contador;
        return regAfectados;
    }

//===================================================================================TABLA PRODUCTO

    //INSERTAR PRODUCTO
    public String insertar(Producto producto) {
        String regInsertados = "Registro insertado n° ";
        long contador = 0;

        if (verificarIntegridad(producto,13)){
            ContentValues prod = new ContentValues();
            prod.put("codproducto", producto.getCodproducto());
            prod.put("codmenu", producto.getCodmenu());
            prod.put("nombreproducto", producto.getNombreproducto());
            prod.put("preciounitario", producto.getPreciounitario());

            contador = db.insert("producto", null, prod);
        }

        if (contador==-1 || contador==0){
            regInsertados = "Error al insetar el registro, Revisar los datos. Verificar";
        }
        else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }


    //CONSULTAR PRODUCTO
    public Producto consultarProducto(String codproducto, String codmenu) {
        String[] id= {codproducto, codmenu};
        Cursor cursor = db.query("producto", camposProducto, "codproducto = ? AND codmenu = ?", id, null, null, null);
        if (cursor.moveToFirst()){
            Producto producto = new Producto();
            producto.setCodproducto(cursor.getString(0));
            producto.setCodmenu(cursor.getString(1));
            producto.setNombreproducto(cursor.getString(2));
            producto.setPreciounitario(cursor.getFloat(3));
            cursor.close();
            return producto;
        }
        else {
            return null;
        }
    }

    //ACTUALIZAR PRODUCTO
    public String actualizar(Producto producto) {
        if (verificarIntegridad(producto, 14)){
            String[] id = {producto.getCodproducto(), producto.getCodmenu()};
            ContentValues cvp = new ContentValues();
            cvp.put("nombreproducto", producto.getNombreproducto());
            cvp.put("preciounitario", producto.getPreciounitario());
            db.update("producto", cvp, "codproducto = ? AND codmenu = ?", id);
            return "Registro actualizado correctamente";
        }
        else {
            return "Registro no existe";
        }

    }

    //ELIMINAR PRODUCTO
    public String eliminar(Producto producto) {
        String regAfectados = "filas afectadas = ";
        int contador = 0;
        String where ="codproducto = '" + producto.getCodproducto() +"'";
        where = where+" AND codmenu = '" + producto.getCodmenu() +"'";
        contador += db.delete("producto", where , null);
        regAfectados += contador;
        return regAfectados;
    }

//=================================================================================TABLA REPARTIDOR
    //INSERTAR REPARTIDOR
    public String insertar(Repartidor repartidor)   {
        String regInsertados ="Registro n. ";
        long contador;
        ContentValues rep = new ContentValues();
        rep.put("codrepartidor", repartidor.getCodrepartidor());
        rep.put("nomrepartidor", repartidor.getNomrepartidor());
        rep.put("aperepartidor", repartidor.getAperepartidor());
        rep.put("telrepartidor", repartidor.getTelrepartidor());

        contador = db.insert("repartidor", null, rep);
        if (contador==-1 || contador==0){
            regInsertados = "Error al ingresar datos";
        } else {
            regInsertados = regInsertados + contador ;
        }
        return regInsertados;
    }

    //PARA ACTUALIZAR REPARTIDOR
    public String actualizar(Repartidor repartidor) {
        if (verificarIntegridad(repartidor,15)){ //verificar si existe el encargado del local
            String [] id = {repartidor.getCodrepartidor()};
            ContentValues cr = new ContentValues();
            cr.put("nomrepartidor", repartidor.getNomrepartidor());
            cr.put("aperepartidor", repartidor.getAperepartidor());
            cr.put("telrepartidor", repartidor.getTelrepartidor());

            db.update("repartidor", cr, "codrepartidor = ?", id);
            return "Registro actualizado correctamente";
        } else {
            return "Registro con codigo" + repartidor.getCodrepartidor() + "no existe";
        }
    }

    //PARA CONSULTAR REPARTIDOR
    public Repartidor consultarRepartidor(String codrepartidor) {
        String [] id = {codrepartidor};
        Cursor cursor = db.query("repartidor", camposRepartidor, "codrepartidor = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Repartidor repartidor = new Repartidor();
            repartidor.setCodrepartidor(cursor.getString(0));
            repartidor.setNomrepartidor(cursor.getString(1));
            repartidor.setAperepartidor(cursor.getString(2));
            repartidor.setTelrepartidor(cursor.getString(3));

            return repartidor;
        }
        else{
            return null;
        }
    }

    //PARA ELIMINAR REPARTIDOR
    public String eliminar(Repartidor repartidor) {
        String regAfectados = "Filas afectadas = ";
        int contador = 0;
        /*
        //AL ELIMINAR repartidor VER SI HAY rutas ASOCIADOS......................................
        if (verificarIntegridad(repartidor,16)){                      SERIA RELACION 8
            contador+=db.delete("rutapedido", "codrepartidor = '" + repartidor.getCodrepartidor() + "'", null);
        }
        */
        contador+=db.delete("repartidor", "codrepartidor = '" + repartidor.getCodrepartidor() +"'", null);
        regAfectados+=contador;
        return regAfectados;
    }


    //=================================================================================TABLA PEDIDO

    //INSERTAR PEDIDO
    public String insertar(Pedido pedido) {
        String regInsertados = "Registro insertado n° ";
        long contador = 0;

            ContentValues rp = new ContentValues();
            rp.put("idpedido", pedido.getIdpedido());
            rp.put("idruta", pedido.getIdruta());
            rp.put("codestadopedido", pedido.getIdestadopedido());
            rp.put("codlocal", pedido.getCodlocal());
            rp.put("fechapedido", pedido.getFechapedido());

            contador = db.insert("pedido", null, rp);

        if (contador==-1 || contador==0){
            regInsertados = "Error al insetar el registro, Revisar los datos. Verificar";
        }
        else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    //=================================================================================TABLA PEDIDOASIGNADO
    //INSERTAR PEDIDOASIGNADO
    public String insertar(PedidosAsignados pedidoasignado) {
        String regInsertados = "Registro insertado n° ";
        long contador = 0;

        ContentValues rp = new ContentValues();
        rp.put("idpedido", pedidoasignado.getIdpedido());
        rp.put("codtrabajador", pedidoasignado.getIdtrabajador());

        contador = db.insert("pedidosasignados", null, rp);

        if (contador==-1 || contador==0){
            regInsertados = "Error al insetar el registro, Revisar los datos. Verificar";
        }
        else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }



    //=================================================================================TABLA DETALLEPRODUCTOEMPLEADO
    //INSERTAR DETALLEPRODUCTOEMPLEADO
    public String insertar(DetalleProductoEmpleado detalleProdEmp) {
        String regInsertados = "Registro insertado n° ";
        long contador = 0;

        ContentValues rp = new ContentValues();
        rp.put("idpedido", detalleProdEmp.getIdpedido());
        rp.put("codtrabajador", detalleProdEmp.getIdtrabajador());
        rp.put("codproducto", detalleProdEmp.getIdproducto());
        rp.put("cantidadpedido", detalleProdEmp.getCantidadpedido());
        rp.put("idpedidosasignados", detalleProdEmp.getIdpedido());
        contador = db.insert("detalleproductoempleado", null, rp);

        if (contador==-1 || contador==0){
            regInsertados = "Error al insetar el registro, Revisar los datos. Verificar";
        }
        else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }



//=================================================================================TABLA RUTA PEDIDO

    //INSERTAR RUTA PEDIDO
    public String insertar(RutaPedido rutaPedido) {
        String regInsertados = "Registro insertado n° ";
        long contador = 0;

        if (verificarIntegridad(rutaPedido,17)){
            ContentValues rp = new ContentValues();
            rp.put("idruta", rutaPedido.getIdruta());
            rp.put("codrepartidor", rutaPedido.getCodrepartidor());
            rp.put("inicioruta", rutaPedido.getInicioruta());
            rp.put("finruta", rutaPedido.getFinruta());

            contador = db.insert("rutapedido", null, rp);
        }

        if (contador==-1 || contador==0){
            regInsertados = "Error al insetar el registro, Revisar los datos. Verificar";
        }
        else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }


    //CONSULTAR RUTA PEDIDO
    public RutaPedido consultarRutaPedido(int idruta, String codrepartidor) {
        String id [] = {String.valueOf(idruta), codrepartidor};
        Cursor cursor = db.query("rutapedido", camposRutaPedido, "idruta = ? AND codrepartidor = ?", id, null, null, null);
        if (cursor.moveToFirst()){
            RutaPedido rp = new RutaPedido();
            rp.setIdruta(cursor.getInt(0));
            rp.setCodrepartidor(cursor.getString(1));
            rp.setInicioruta(cursor.getString(2));
            rp.setFinruta(cursor.getString(3));

            return rp;
        }
        else {
            return null;
        }
    }

    //ACTUALIZAR RUTA PEDIDO
    public String actualizar(RutaPedido rutapedido) {
        if (verificarIntegridad(rutapedido, 18)){
            String[] id = {String.valueOf(rutapedido.getIdruta()), rutapedido.getCodrepartidor()};
            ContentValues cvrp = new ContentValues();
            cvrp.put("inicioruta", rutapedido.getInicioruta());
            cvrp.put("finruta", rutapedido.getFinruta());

            db.update("rutapedido", cvrp, "idruta = ? AND codrepartidor = ?", id);
            return "Registro actualizado correctamente";
        }
        else {
            return "Registro no existe";
        }

    }

    //ELIMINAR RUTA PEDIDO
    public String eliminar(RutaPedido rutaPedido) {
        String regAfectados = "filas afectadas = ";
        int contador = 0;
        String where ="idruta = '" + rutaPedido.getIdruta() +"'";
        where = where+" AND codrepartidor = '" + rutaPedido.getCodrepartidor() +"'";
        contador += db.delete("rutapedido", where , null);
        regAfectados += contador;
        return regAfectados;
    }

//=================================================================================================
//================================================== PARA CONSULTAR SI EXISTE EL USUARIO Y EL PASS
//=================================================================================================
    public Cursor ConsultarUsuPass(String usu, String pass){
        Cursor cursor;
        cursor = db.query("usuario", new String[] {"nombreusuario", "pass", "usuario"}, "nombreusuario like '"+usu+"' and pass like'"+pass+"'", null,null,null,null );
        return cursor;
    }


//================================================================LAS VERIFICACIONES DE INTEGRIDAD
    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        switch (relacion){
            case 1:
            {  //PARA VER SI EXISTE FACULTAD AL MOMENTO DE ACTUALIZARLA
                Facultad facu = (Facultad) dato;
                String[] id = {facu.getCodfacultad()};
                abrir();
                Cursor cfacu = db.query("facultad", null, "codfacultad = ?", id, null, null, null);
                if (cfacu.moveToFirst()) {
                    cfacu.close();
                    return true;  // LA FACULTAD EXISTE
                }
                cfacu.close();
                return false;
            }
            case 2:
            { //PARA REVISAR SI EXISTEN EMPLEADOS ASOCIADOS A LA FACULTAD
                Facultad facu = (Facultad) dato;
                Cursor c = db.query(true, "empleado", new String[]{"codfacultad"}, "codfacultad = '" + facu.getCodfacultad() + "'", null, null, null, null, null);
                if (c.moveToFirst())
                    return true;
                else
                    return false;
            }
            case 3:
            {   //PARA REVISAR SI EXISTE LA UBICACION AL MOMENTO DE ACTUALIZARLA
                Ubicacion ubi = (Ubicacion) dato;
                String[] id = {ubi.getCodubicacion()};
                abrir();
                Cursor cubi = db.query("ubicacion", null, "codubicacion = ?", id, null, null, null);
                if (cubi.moveToFirst()) {
                    return true;  // LA FACULTAD EXISTE
                }
                return false;

            }
            case 4:
            { //PARA REVISAR SI EXISTEN EMPLEADOS ASOCIADOS A LA UBICACION
                Ubicacion ubi = (Ubicacion) dato;
                Cursor cubi = db.query(true, "empleado", new String[]{"codubicacion"}, "codubicacion = '" + ubi.getCodubicacion() + "'", null, null, null, null, null);
                if (cubi.moveToFirst())
                    return true;
                else
                    return false;
            }
            case 5:
            { // PARA REVISAR QUE AL INSERTAR EMPLEADO EXISTAN CODFACULTAD Y CODUBICACION QUE SE QUIEREN INGRESAR
                Empleado empleado = (Empleado) dato;
                String[] id1 = {empleado.getCodfacultad()};
                String[] id2 = {empleado.getCodubicacion()};
                //abrir();
                Cursor cursor1 = db.query("facultad", null, "codfacultad = ?", id1, null, null, null);
                Cursor cursor2 = db.query("ubicacion", null, "codubicacion = ?", id2, null, null, null);
                if (cursor1.moveToFirst() && cursor2.moveToFirst()) {
                    //se encontraron datos
                    return true;
                }
                return false;
            }
            case 6:
            { //AL ACTUALIZAR EL EMPLEADO, QUE EXISTA CODFACULTAD, CODUBICACION Y CODEMPLEADO ASOCIADOS
                Empleado empleado1 = (Empleado) dato;
                String[] ids = {empleado1.getCodempleado(),empleado1.getCodfacultad(), empleado1.getCodubicacion()};
                abrir();
                Cursor ce = db.query("empleado", null, "codempleado = ? AND codfacultad = ? AND codubicacion = ?", ids, null, null, null);
                if (ce.moveToFirst()) {
                    //se encontraron datos
                    return true;
                }
                return false;
            }
            case 7:
            {    //para ver si existe el encargado de local al actualizar
                EncargadoLocal enclocal = (EncargadoLocal) dato;
                String[] id = {enclocal.getCodencargadolocal()};
                abrir();
                Cursor cencloc = db.query("encargadolocal", null, "codencargadolocal = ?", id, null, null, null);
                if (cencloc.moveToFirst()) {
                    return true;  // ese encargado de local si existe
                }
                return false;
            }
            case 8:
            { //PARA REVISAR SI EXISTEN LOCALES ASOCIADOS AL ENCARGADO DE LOCAL
                EncargadoLocal enc = (EncargadoLocal) dato;
                Cursor cenc = db.query(true, "local", new String[]{"codencargadolocal"}, "codencargadolocal = '" + enc.getCodencargadolocal() + "'", null, null, null, null, null);
                if (cenc.moveToFirst())
                    return true;
                else
                    return false;
            }
            case 9:
            {  //PARA REVISAR QUE AL INSERTAR EL LOCAL, EXISTA EL ENCARGADO DE LOCAL
                Local local = (Local) dato;
                String[] id1 = {local.getCodencargadolocal()};
                //abrir();
                Cursor cursor1 = db.query("encargadolocal", null, "codencargadolocal = ?", id1, null, null, null);
                if (cursor1.moveToFirst() ) {
                    //se encontraron datos
                    return true;
                }
                return false;
            }
            case 10:
            {  //AL ACTUALIZAR local QUE EXISTA CODLOCAL Y CODENCARGADOLOCAL ASOCIADOS
                Local local1 = (Local) dato;
                String[] ids = {local1.getCodlocal(), local1.getCodencargadolocal()};

                abrir();
                Cursor cl = db.query("local", null, "codlocal = ? AND codencargadolocal = ?" , ids, null, null, null);
                if (cl.moveToFirst()) {
                    //se encontraron datos
                    return true;
                }
                return false;
            }
            case 11:
            {  //PARA REVISAR QUE AL INSERTAR EL MENU, EXISTA EL LOCAL
                Menu menu = (Menu) dato;
                String[] id1 = {menu.getCodlocal()};
                //abrir();
                Cursor cursor1 = db.query("local", null, "codlocal = ?", id1, null, null, null);
                if (cursor1.moveToFirst() ) {
                    //se encontraron datos
                    return true;
                }
                return false;
            }
            case 12:
            {   //AL ACTUALIZAR MENU, QUE EXISTA CODMENU Y CODLOCAL ASOCIADOS
                Menu menu1 = (Menu) dato;
                String[] ids = {menu1.getCodmenu(), menu1.getCodlocal()};
                abrir();
                Cursor cl = db.query("menu", null, "codmenu = ? AND codlocal = ?" , ids, null, null, null);
                if (cl.moveToFirst()) {
                    //se encontraron datos
                    return true;
                }
                return false;
            }
            case 13:
            {   //PARA REVISAR QUE AL INSERTAR UN PRODUCTO, EXISTA EL MENU DONDE SE VA A INSERTAR
                Producto producto = (Producto) dato;
                String[] id1 = {producto.getCodmenu()};
                //abrir();
                Cursor cursor1 = db.query("menu", null, "codmenu = ?", id1, null, null, null);
                if (cursor1.moveToFirst() ) {
                    //se encontraron datos
                    return true;
                }
                return false;
            }
            case 14:
            {   //AL ACTUALIZAR PRODUCTO, QUE EXISTA CODPRODUCTO Y CODMENU
                Producto producto1 = (Producto) dato;
                String[] ids = {producto1.getCodproducto(), producto1.getCodmenu()};
                abrir();
                Cursor cp = db.query("producto", null, "codproducto = ? AND codmenu = ?" , ids, null, null, null);
                if (cp.moveToFirst()) {
                    //se encontraron datos
                    return true;
                }
                return false;
            }
            case 15:
            {   //PARA SABER SI EL REPARTIDOR EXISTE AL MOMENTO DE ACTUALIZAR
                Repartidor repartidor = (Repartidor) dato;
                String[] id = {repartidor.getCodrepartidor()};
                abrir();
                Cursor crep = db.query("repartidor", null, "codrepartidor = ?", id, null, null, null);
                if (crep.moveToFirst()) {
                    return true;  // ese repartidor si existe
                }
                return false;
            }
            case 16:
            {   //PARA VER SI HAY REPARTIDORES ASOCIADOS A LA RUTA AL MOMENTO DE ELIMINAR UN REPARTIDOR
                Repartidor repartidor1 = (Repartidor) dato;
                Cursor crep = db.query(true, "rutapedido", new String[]{"codrepartidor"}, "codrepartidor = '" + repartidor1.getCodrepartidor() + "'", null, null, null, null, null);
                if (crep.moveToFirst())
                    return true;
                else
                    return false;
            }
            case 17:
            {   //para vERIFICAR QUE AL INSERTAR LA RUTA, YA EXISTA EL REPARTIDOR
                RutaPedido rp = (RutaPedido) dato;
                String[] id1 = {rp.getCodrepartidor()};
                //abrir();
                Cursor cursor1 = db.query("repartidor", null, "codrepartidor = ?", id1, null, null, null);
                if (cursor1.moveToFirst() ) {
                    //se encontraron datos
                    return true;
                }
                return false;
            }
            case 18:
            {   //AL ACTUALIZAR RUTAPEDIDO QUE EXISTA CODREPARTIDOR Y IDRUTA
                RutaPedido rp = (RutaPedido) dato;
                String[] ids = {String.valueOf(rp.getIdruta()), rp.getCodrepartidor()};
                abrir();
                Cursor cl = db.query("rutapedido", null, "idruta = ? AND codrepartidor = ?" , ids, null, null, null);
                if (cl.moveToFirst()) {
                    //se encontraron datos
                    return true;
                }
                return false;
            }
            //CAMBIOS EN ACTUALIZAR EMPLEADO.
            case 19:
            {   //AL ACTUALIZAR EL EMPLEADO QUE EXISTA EL EMPLEADO, LA UBICACION Y LA FACULTAD
                //NO NECESARIAMENTE ESTAN ASOCIADOS.
                Empleado emp = (Empleado) dato;
                String[] idcodemp = {emp.getCodempleado()};
                String[] idcodfac = {emp.getCodfacultad()};
                String[] idcodubi = {emp.getCodubicacion()};
                abrir();
                Cursor cEmp = db.query("empleado", null, "codempleado = ?", idcodemp, null, null, null);
                Cursor cFac = db.query("facultad", null, "codfacultad = ?", idcodfac, null, null, null);
                Cursor cUbi = db.query("ubicacion", null, "codubicacion = ?", idcodubi, null, null, null);
                if (cEmp.moveToFirst() && cFac.moveToFirst() && cUbi.moveToFirst()) {
                    //se encontraron datos
                    //los datos de emp, fac y ubi si existen!
                    return true;
                }
                return false;
            }
            case 20:
            {
                //PARA REVISAR SI EXISTEN EMPLEADOS ASOCIADOS A LA UBICACION
                Empleado emp = (Empleado) dato;
                Cursor c = db.query(true, "usuario", new String[]{"nombreusuario"}, "nombreusuario = '" + emp.getCodempleado() + "'", null, null, null, null, null);
                if (c.moveToFirst())
                    return true;
                else
                    return false;
            }
            default:
                return false;
        }
    }

//=====================================================================PARA LLENAR LOS SPINNERS


    //ARRAY LIST PARA SPINNER DE CODIGOS DE MENU

    public ArrayList<String> getAllCodMenu(){
        //String[] id = {usuario.getNombreusuario()};
        ArrayList<String> list = new ArrayList<String>();
        db = DBHelper.getReadableDatabase();
      //  db.beginTransaction();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM menu ", null);
            if (cursor.getCount()>0){
                while (cursor.moveToNext()){
                    String codmenu = cursor.getString(cursor.getColumnIndex("codmenu"));
                    list.add(codmenu);
                }
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    ///////////////////////////////////////////////////

    //ARRAY LIST PARA SPINNER DE CODIGOS DE REPARTIDOR

    public ArrayList<String> getAllCodRepartidor(){
        ArrayList<String> list = new ArrayList<String>();
        db = DBHelper.getReadableDatabase();
        //  db.beginTransaction();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM repartidor", null);
            if (cursor.getCount()>0){
                while (cursor.moveToNext()){
                    String codrepartidor = cursor.getString(cursor.getColumnIndex("codrepartidor"));
                    list.add(codrepartidor);
                }
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }


    ///////////////////////////////////////////////////

    //ARRAYLIST PARA SPINNER DE IDRUTA

    public ArrayList<Integer> getAllIdRutaPedido(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        db = DBHelper.getReadableDatabase();
        //  db.beginTransaction();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM rutapedido", null);
            if (cursor.getCount()>0){
                while (cursor.moveToNext()){
                    int idruta = cursor.getInt(cursor.getColumnIndex("idruta"));
                    list.add(idruta);
                }
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }


    //////////////////////////////////////////////////////


    //ARRAYLIST PARA SPINNER DE CODFACULTAD

    public ArrayList<String> getAllCodFacultad(){
        ArrayList<String> list = new ArrayList<String>();
        db = DBHelper.getReadableDatabase();
        //  db.beginTransaction();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM facultad", null);
            if (cursor.getCount()>0){
                while (cursor.moveToNext()){
                    String codfacultad = cursor.getString(cursor.getColumnIndex("codfacultad"));
                    list.add(codfacultad);
                }
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    ////////////////////////////////////////////////////

    //ARRAYLIST PARA SPINNER DE CODUBICACION

    public ArrayList<String> getAllCodUbicacion(){
        ArrayList<String> list = new ArrayList<String>();
        db = DBHelper.getReadableDatabase();
        //  db.beginTransaction();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM ubicacion", null);
            if (cursor.getCount()>0){
                while (cursor.moveToNext()){
                    String codubicacion = cursor.getString(cursor.getColumnIndex("codubicacion"));
                    list.add(codubicacion);
                }
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }


    //////////////////////////////////////////////////

    //ARRAY LIST PARA SPINNER DE CODIGOS DE ESTADO

    public ArrayList<String> getAllCodEstado(){
        ArrayList<String> list = new ArrayList<String>();
        db = DBHelper.getReadableDatabase();
        //  db.beginTransaction();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM estadopedido", null);
            if (cursor.getCount()>0){
                while (cursor.moveToNext()){
                    String codestadopedido = cursor.getString(cursor.getColumnIndex("codestadopedido"));
                    list.add(codestadopedido);
                }
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }


//==========================================================PARA LLENAR LA BASE CON DATOS INICIALES

    public String llenarBase() {
        final String[] Unombreusuario = {"emp1", "enc1"};
        final String[] Ucontrasena = {"123", "123"};
        final String[] Uusuario = {"Empleado", "Encargado de local"};

        final String[] Fcodfacultad = {"mate", "ing", "huma", "agro", "odonto", "medi", "juri", "quimica", "eco"};
        final String[] Fnomfacultad = {"Facultad de Ciencias y Matematica", "Facultad de Ingeniera y Arquitectura",
                                        "Facultad de ciencias y Humaniddes", "Facultad de agronomia ",
                                        "Facultad de odontologia", "Facultad de medicina", "Facultad de Jurisprudencia",
                                        "Facultad de quimica y farmacia", "Facultad de economia"};

        final String[] Ucodubicacion = {"ubi1", "ubi2"};
        final String[] Udescubicacion = {"Entre la facultad de ingenieria y humanidades", "Cerca de la facultad de agronomia"};

        final String[] ELcodenclocal = {"enc1", "enc2"};
        final String[] ELnomenclocal = {"Nestor", "Meli"};
        final String[] ELapeenclocal = {"Molina", "Moshi"};
        final String[] ELtelenclocal = {"71179082", "71591724"};

        final String[] Lcodlocal = {"local1", "local2"};
        final String[] Lcodencargadolocal = {"enc1", "enc2"};
        final String[] Lnombrelocal = {"Lacteos la vaquita", "Lacteos la cabrita"};

        final String[] Mcodmenu = {"menu1", "menu2"};
        final String[] Mcodlocal = {"local1", "local2"};
        final float[] Mpreciomenu = {20, 30};
        final String[] Mfechadesdemenu = {"01/06/2020", "01/05/2020"};
        final String[] Mfechahastamenu = {"20/06/2020", "20/05/2020"};

        final String[] Pcodmenu = {"menu1", "menu2","menu1", "menu2","menu1", "menu2"};
        final String[] Pcodproducto = {"p1", "p2","p3","p4", "p5","p6"};
        final String[] Pnombreproducto = {"leche","huevos","jamon","pollo","tocino","frijoles"};
        final float[] Ppreciounitario = {1,3,1,2,1,4};

        final String[] Rcodrepartidor = {"repartidor1", "repartidor2", "repartidor3"};
        final String[] Rnomrepartidor = {"moshi", "peter", "aaaa"};
        final String[] Raperepartidor = {"moshi", "parker", "bbb"};
        final String[] Rtelrepartidor = {"22222222", "22577777", "21212121"};

        final String[] EstPedCodEstado = {"PE", "CO","PR","RU","FI"};
        final String[] EstPedDescEstado = {"PENDIENTE", "CONFIRMADO","PROCESO","EN RUTA","FINALIZADO"};

        final String[] EmpCodEmpleado = {"111", "222","emp1"};
        final String[] EmpCodFacultad = {"ing", "ing","mate"};
        final String[] EmpCodUbicacion = {"ubi1", "ubi1","ubi2"};
        final String[] EmpNomEmpleado = {"ROBERTO", "NESTOR","SANTIAGO"};
        final String[] EmpApeEmpleado = {"MARROQUIN", "MOLINA","LARA"};
        final String[] EmpTelEmpleado = {"61199001", "71179082","77463008"};
        final String[] EmpCodLocal = {"local1", "local2","local1"};

        final Integer[] RutIdRuta = {1, 2,3};
        final String[] RutIdRepartidor = {"repartidor1","repartidor2","repartidor3"};
        final String[] RutFechaInicio = {"01/06/2020", "01/05/2020","01/02/2020"};
        final String[] RutFechaFin = {"20/09/2020", "20/10/2020","01/12/2020"};

        final Integer[] PedIdPedido = {1,2,3,4,5,6,7};
        final Integer[] PedIdRuta = {1,2,3,1,2,3,4};
        final String[] PedCodEstadoPedido = {"PE","CO","PR","RU","FI","PE","CO"};
        final String[] PedCodLocal = {"local1", "local2","local1","local2","local1","local2","local1"};
        final String[] PedFechaPedido = {"18/06/2020", "18/06/2020","18/06/2020","18/06/2020", "18/06/2020","19/06/2020","19/06/2020"};

        final String[] PedCodTrabajador = {"emp1","emp1","222","111","emp1","emp1","emp1"};
        final Integer[] PedAsigIdPedido = {1,2,3,4,5,6,7};

        final Integer[] ProdEmpIdPed = {1,2,3,4,5,6,7,1,2};
        final Integer[] ProdEmpIdPedido = {1,2,3,4,5,6,7,1,2};
        final String[] ProdEmpCodTrabajador = {"emp1","emp1","222","111","emp1","emp1","emp1","emp1","emp1"};
        final String[] ProdEmpcodproducto = {"p1", "p2","p3","p4", "p5","p6","p1","p4","p6"};
        final Integer[] ProdEmpIdPedidoasignados = {1,2,3,4,5,6,7,2,3};
        final Integer[] ProdEmpCantidad = {1,2,3,4,2,2,3,4,5};

        abrir();
        db.execSQL("DELETE FROM estadopedido ");
        db.execSQL("DELETE FROM usuario");
        db.execSQL("DELETE FROM facultad");
        db.execSQL("DELETE FROM ubicacion");
        db.execSQL("DELETE FROM encargadolocal");
        db.execSQL("DELETE FROM local");
        db.execSQL("DELETE FROM menu");
        db.execSQL("DELETE FROM producto");
        db.execSQL("delete from pedido");
        db.execSQL("delete from detalleproductoempleado");
        db.execSQL("delete from pedidosasignados");
        db.execSQL("DELETE FROM repartidor");
        db.execSQL("DELETE FROM empleado");
        db.execSQL("DELETE FROM rutapedido");

        Usuario usuario = new Usuario();
        for(int i=0; i<2; i++){
            usuario.setNombreusuario(Unombreusuario[i]);
            usuario.setContrasena(Ucontrasena[i]);
            usuario.setUsuario(Uusuario[i]);
            insertar(usuario);
        }

        Facultad facultad = new Facultad();
        for(int i=0; i<9; i++){
            facultad.setCodfacultad(Fcodfacultad[i]);
            facultad.setNomfacultad(Fnomfacultad[i]);
            insertar(facultad);
        }

        Ubicacion ubicacion = new Ubicacion();
        for(int i=0; i<2; i++){
            ubicacion.setCodubicacion(Ucodubicacion[i]);
            ubicacion.setDescubicacion(Udescubicacion[i]);
            insertar(ubicacion);
        }

        EncargadoLocal encargado = new EncargadoLocal();
        for (int i=0; i<2; i++){
            encargado.setCodencargadolocal(ELcodenclocal[i]);
            encargado.setNomencargadolocal(ELnomenclocal[i]);
            encargado.setApeencargadolocal(ELapeenclocal[i]);
            encargado.setTelencargadolocal(ELtelenclocal[i]);
            insertar(encargado);
        }

        Local local = new Local();
        for (int i=0; i<2; i++){
            local.setCodlocal(Lcodlocal[i]);
            local.setCodencargadolocal(Lcodencargadolocal[i]);
            local.setNombrelocal(Lnombrelocal[i]);
            insertar(local);
        }

        Menu menu = new Menu();
        for (int i=0; i<2; i++){
            menu.setCodmenu(Mcodmenu[i]);
            menu.setCodlocal(Mcodlocal[i]);
            menu.setPreciomenu(Mpreciomenu[i]);
            menu.setFechadesdemenu(Mfechadesdemenu[i]);
            menu.setFechahastamenu(Mfechahastamenu[i]);
            insertar(menu);
        }

        Producto producto = new Producto();
        for(int i = 0;i<6;i++){
            producto.setCodproducto(Pcodproducto[i]);
            producto.setNombreproducto(Pnombreproducto[i]);
            producto.setCodmenu(Pcodmenu[i]);
            producto.setPreciounitario(Ppreciounitario[i]);
            insertar(producto);
        }

        Repartidor repartidor = new Repartidor();
        for (int i = 0;i<3; i++){
            repartidor.setCodrepartidor(Rcodrepartidor[i]);
            repartidor.setNomrepartidor(Rnomrepartidor[i]);
            repartidor.setAperepartidor(Raperepartidor[i]);
            repartidor.setTelrepartidor(Rtelrepartidor[i]);
            insertar(repartidor);
        }

        EstadoPedido estado = new EstadoPedido();
        for(int i=0; i<5; i++){
            estado.setCodestadopedido(EstPedCodEstado[i]);
            estado.setDescestadopedido(EstPedDescEstado[i]);
            insertar(estado);
        }

        Empleado empleado = new Empleado();
        for (int i=0; i<3; i++){
            empleado.setCodempleado(EmpCodEmpleado[i]);
            empleado.setCodfacultad(EmpCodFacultad[i]);
            empleado.setCodubicacion(EmpCodUbicacion[i]);
            empleado.setNomempleado(EmpNomEmpleado[i]);
            empleado.setApeempleado(EmpApeEmpleado[i]);
            empleado.setTelempleado(EmpTelEmpleado[i]);
            empleado.setCodlocal(EmpCodLocal[i]);
            insertar(empleado);
        }

        RutaPedido rutapedido = new RutaPedido();
        for (int i=0; i<3; i++){
            rutapedido.setIdruta(RutIdRuta[i]);
            rutapedido.setCodrepartidor(RutIdRepartidor[i]);
            rutapedido.setInicioruta(RutFechaInicio[i]);
            rutapedido.setFinruta(RutFechaFin[i]);
            insertar(rutapedido);
        }

        Pedido pedido = new Pedido();
        for (int i=0; i<7; i++){
            pedido.setIdpedido(PedIdPedido[i]);
            pedido.setIdruta(PedIdRuta[i]);
            pedido.setIdestadopedido(PedCodEstadoPedido[i]);
            pedido.setCodlocal(PedCodLocal[i]);
            pedido.setFechapedido(PedFechaPedido[i]);
            insertar(pedido);
        }

        PedidosAsignados pedidoasignado = new PedidosAsignados();
        for (int i=0; i<7; i++){
            pedidoasignado.setIdpedido(PedAsigIdPedido[i]);
            pedidoasignado.setIdtrabajador(PedCodTrabajador[i]);
            insertar(pedidoasignado);
        }

        DetalleProductoEmpleado detalleprodeempleado = new DetalleProductoEmpleado();
        for (int i=0; i<9; i++){
            detalleprodeempleado.setIdpedido(ProdEmpIdPedido[i]);
            detalleprodeempleado.setIdtrabajador(ProdEmpCodTrabajador[i]);
            detalleprodeempleado.setIdproducto(ProdEmpcodproducto[i]);
            detalleprodeempleado.setCantidadpedido(ProdEmpCantidad[i]);
            insertar(detalleprodeempleado);
        }

        cerrar();
        return "Se insertaron datos de prueba";

    }



}
