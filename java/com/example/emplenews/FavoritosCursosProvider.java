package com.example.emplenews;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Clase FavoritosCursosProvider
 *
 * DAO tratante de la información almacenada relativa a las ofertas de formacion favoritas
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class FavoritosCursosProvider extends ContentProvider {
    //Definición del CONTENT_URI
    /**
     * Cadena con la URI de la tabla de contenido de las ofertas de formacion favoritas
     */
    private static final String uri =
            "content://net.favoritosCursos.android.puente/ZFavoritosCursos";
    /**
     * URI de la tabla de contenido de las ofertas de formacion favoritas
     */
    public static final Uri CONTENT_URI = Uri.parse(uri);

    //Necesario para UriMatcher
    private static final int CLIENTES = 1;
    private static final int CLIENTES_ID = 2;
    private static final UriMatcher uriMatcher;

    //Clase interna para declarar las constantes de columna
    public static final class Favoritos implements BaseColumns
    {
        private Favoritos() {}

        //Nombres de columnas
        public static final String TABLE_NAME= "ZFavoritosCursos";
        public static final String COLUMN_TITULO= "titulo";
        public static final String COLUMN_ORGANISMO= "organismo";
        public static final String COLUMN_GESTOR= "gestor";
        public static final String COLUMN_TIPO= "tipo";
        public static final String COLUMN_CIUDAD= "ciudad";
        public static final String COLUMN_COORDENADAS= "coordenadas";
        public static final String COLUMN_INICIO= "inicio";
        public static final String COLUMN_FIN= "fin";
        public static final String COLUMN_FECHAS= "fechas";
        public static final String COLUMN_DURACION= "duracion";
        public static final String COLUMN_DESCRIPCION= "descripcion";
        public static final String COLUMN_MATERIA= "materia";
        public static final String COLUMN_INSCRIPCION= "inscripcion";
        public static final String COLUMN_DESTINATARIOS= "destinatarios";
        public static final String COLUMN_REQUISITOS= "requisitos";
        public static final String COLUMN_LUGAR= "lugar";
        public static final String COLUMN_PLAZAS= "plazas";
        public static final String COLUMN_INFOADICIONAL= "adicional";
        public static final String COLUMN_ID= "id";
        public static final String COLUMN_ACTUALIZACION= "actualizacion";
        public static final String COLUMN_ENLACE= "enlace";
        public static final String COLUMN_ID_PROVINCIA= "provincia";
    }

    //Base de datos
    /**
     * Manejador de la base de datos que contiene las ofertas de formacion favoritas
     */
    private DbHelperFavoritosCursos clidbh;
    private static final String BD_NOMBRE = "ZFavoritosCursos";
    private static final int BD_VERSION = 1;
    /**
     * Nombre de la tabla contenedora de las ofertas de formacion favoritas
     */
    private static final String TABLA_FAVORITOS = "ZFavoritosCursos";

    //Inicializamos el UriMatcher
    /**
     * Contenedor con los tipos de URI
     */
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("net.favoritos.android.puente", "clientes", CLIENTES);
        uriMatcher.addURI("net.favoritos.android.puente", "clientes/#", CLIENTES_ID);
    }

    @Override
    /**
     * Inicializa el ContentProvider, esto se hace cada vez que un controlador hace uso del método getContentResolver()
     * En esta inicialización se le especifica el manejador de base de datos que le corresponde y con el que se va a comunicar.
     * @return <ul>
     *          <li>true: creacion correcta</li>
     *          </ul>
     */
    public boolean onCreate() {

        clidbh = new DbHelperFavoritosCursos(getContext());

        return true;
    }

    @Override
    /**
     * Recupera de la tabla de ofertas de formacion favoritas las que coincidan con los filtros recibidos en los parámetros selection (Provincia) y selectionArgs[] (búsqueda libre realizada por el usuario y materia)
     * @param uri Uri de la BD
     *  @param selection Privincia filtrada
     *  @param selectionArgs Busqueda libre filtrada y materia
     *  @return <ul>
     *          <li>Cursor: Cursor a la primera coincidencia de la busqueda en la BD</li>
     *          </ul>
     */
    public Cursor query(Uri uri, String[] projection,
                        String selection, String[] selectionArgs, String sortOrder) {

        //Si es una consulta a un ID concreto construimos el WHERE
        /*String where = selection;
        if(uriMatcher.match(uri) == CLIENTES_ID){
            where = "_id=" + uri.getLastPathSegment();
        }*/

        Cursor c;
        SQLiteDatabase db = clidbh.getWritableDatabase();
        if(selection.equalsIgnoreCase("*")){
            c=db.rawQuery("SELECT * FROM ZFavoritosCursos",null);
            if(c!=null)
                Log.e("FavoritosProvider","Hay datos en tabla");
            else
                Log.e("FavoritosProvider","No hay datos en tabla");
        }
        else{

            c=db.rawQuery("SELECT * FROM "+Estructura_Favoritos_Cursos.TABLE_NAME +" WHERE " + Estructura_Favoritos_Cursos.COLUMN_ID+ " LIKE "+selection,null);
            if(c.getCount()>0){
                Log.e("FavoritosCursosProvider","Seleccion: "+selection+" hay resultados");
            }
            else {
                Log.e("FavoritosCursosProvider","Seleccion: "+selection+" no hay resultados");
            }
        }
        return c;
    }
    /**
     * Introduce en la tabla de ofertas de formacion favoritas la tupla recibida en el parámetro values
     * @param values valores de la tupla de oferta de formacion  a introducir
     *  @return <ul>
     *          <li>Uri: uri de la BD de insercion</li>
     *          </ul>
     */
    public Uri insert(Uri uri, ContentValues values) {

        Log.e("En uri","FavoritosCursos");
        Log.e("Oferta",values.toString());

        long regId = 1;

        SQLiteDatabase db = clidbh.getWritableDatabase();

        regId = db.insert(TABLA_FAVORITOS, null, values);

        Uri newUri = ContentUris.withAppendedId(CONTENT_URI, regId);

        return newUri;
    }

    @Override
    /**
     * Actualiza la tupla coincidente con los filtros recibidos en selection y selectionArgs[] con los valores recibidos en values
     * @param values valores de la tupla de oferta de formacion a introducir
     * @param selection provincia filtrada
     * @param selectionArgs busqueda libre filtrada y materia
     *  @return <ul>
     *          <li>int: nº registros actualizados</li>
     *          </ul>
     */
    public int update(Uri uri, ContentValues values,
                      String selection, String[] selectionArgs) {

        int cont;

        //Si es una consulta a un ID concreto construimos el WHERE
       /* String where = selection;
        if(uriMatcher.match(uri) == CLIENTES_ID){
            where = "_id=" + uri.getLastPathSegment();
        }*/

        SQLiteDatabase db = clidbh.getWritableDatabase();

        cont = db.update(TABLA_FAVORITOS, values, "", selectionArgs);

        return cont;
    }

    @Override
    /**
     * Borra la tupla que coincida con los filtros pasados en selection y selectionArgs[]
     * @param selection provincia filtrada
     * @param selectionArgs busqueda libre filtrada y materia
     *  @return <ul>
     *          <li>int: nº de registros borrados</li>
     *          </ul>
     */
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int cont;

        //Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if(uriMatcher.match(uri) == CLIENTES_ID){
            where = "_id=" + uri.getLastPathSegment();
        }

        SQLiteDatabase db = clidbh.getWritableDatabase();

        //cont = db.delete(TABLA_CLIENTES, where, selectionArgs);
        db.execSQL("DELETE FROM "+Estructura_Favoritos_Cursos.TABLE_NAME+" WHERE "+Estructura_Favoritos_Cursos.COLUMN_ID+" LIKE "+selection);


        return 1;
    }

    @Override
    public String getType(Uri uri) {

        int match = uriMatcher.match(uri);

        switch (match)
        {
            case CLIENTES:
                return "vnd.android.cursor.dir/vnd.empleos.cliente";
            case CLIENTES_ID:
                return "vnd.android.cursor.item/vnd.empleos.cliente";
            default:
                return null;
        }
    }
}
