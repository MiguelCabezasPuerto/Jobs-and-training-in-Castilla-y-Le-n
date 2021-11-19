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
 * Clase FavoritosProvider
 *
 * DAO tratante de la información almacenada relativa a las ofertas de empleo favoritas
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class FavoritosProvider extends ContentProvider {
    //Definición del CONTENT_URI
    /**
     * Cadena con la URI de la tabla de contenido de las ofertas de empleo favoritas
     */
    private static final String uri =
            "content://net.favoritos.android.puente/ZFavoritos";
    /**
     * URI de la tabla de contenido de las ofertas de empleo favoritas
     */
    public static final Uri CONTENT_URI = Uri.parse(uri);

    //Necesario para UriMatcher
    private static final int CLIENTES = 1;
    private static final int CLIENTES_ID = 2;
    private static final UriMatcher uriMatcher;


    //Base de datos
    /**
     * Manejador de la base de datos que contiene las ofertas de empleo favoritas
     */
    private DbHelperFavoritos clidbh;
    private static final String BD_NOMBRE = "ZFavoritos";
    private static final int BD_VERSION = 1;
    /**
     * Nombre de la tabla contenedora de las ofertas de empleo favoritas
     */
    private static final String TABLA_FAVORITOS = "ZFavoritos";

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

        clidbh = new DbHelperFavoritos(getContext());

        return true;
    }

    @Override
    /**
     * Recupera de la tabla de ofertas de empleo favoritas las que coincidan con los filtros recibidos en los parámetros selection (Provincia) y selectionArgs[] (búsqueda libre realizada por el usuario)
     * @param uri Uri de la BD
     *  @param selection Privincia filtrada
     *  @param selectionArgs Busqueda libre filtrada
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
            c=db.rawQuery("SELECT * FROM ZFavoritos",null);
            if(c!=null)
                Log.e("FavoritosProvider","Hay datos en tabla");
            else
                Log.e("FavoritosProvider","No hay datos en tabla");
        }
        else{

            c=db.rawQuery("SELECT * FROM "+Estructura_Favoritos.TABLE_NAME +" WHERE " + Estructura_Favoritos.COLUMN_ID+ " LIKE "+selection,null);
            if(c.getCount()>0){
                Log.e("FavoritosProvider","Seleccion: "+selection+" hay resultados");
            }
            else {
                Log.e("FavoritosProvider","Seleccion: "+selection+" no hay resultados");
            }
        }
        return c;
    }

    /**
     * Introduce en la tabla de ofertas de empleo favoritas la tupla recibida en el parámetro values
     * @param values valores de la tupla de oferta de empleo a introducir
     *  @return <ul>
     *          <li>Uri: uri de la BD de insercion</li>
     *          </ul>
     */
    public Uri insert(Uri uri, ContentValues values) {


        long regId = 1;

        SQLiteDatabase db = clidbh.getWritableDatabase();

        regId = db.insert(TABLA_FAVORITOS, null, values);

        Uri newUri = ContentUris.withAppendedId(CONTENT_URI, regId);

        return newUri;
    }

    @Override
    /**
     * Actualiza la tupla coincidente con los filtros recibidos en selection y selectionArgs[] con los valores recibidos en values
     * @param values valores de la tupla de oferta de empleo a introducir
     * @param selection privincia filtrada
     * @param selectionArgs busqueda libre filtrada
     *  @return <ul>
     *          <li>int: nº de registros actualizados</li>
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
     * @param selection privincia filtrada
     * @param selectionArgs busqueda libre filtrada
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
        db.execSQL("DELETE FROM "+Estructura_Favoritos.TABLE_NAME+" WHERE "+Estructura_Favoritos.COLUMN_ID+" LIKE "+selection);


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
