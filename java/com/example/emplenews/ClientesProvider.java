package com.example.emplenews;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
/**
 * Clase ClientesProvider
 *
 * DAO tratante de la información almacenada relativa a las ofertas de empleo
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class ClientesProvider extends ContentProvider {

    //Definición del CONTENT_URI
    /**
     * Cadena con la URI de la tabla de contenido de las ofertas de empleo
     */
    private static final String uri =
            "content://net.empleos.android.contentproviders2/ZEmpleos";
    /**
     * URI de la tabla de contenido de las ofertas de empleo
     */
    public static final Uri CONTENT_URI = Uri.parse(uri);

    //Necesario para UriMatcher

    private static final int CLIENTES = 1;
    private static final int CLIENTES_ID = 2;
    private static final UriMatcher uriMatcher;


    //Base de datos
    /**
     * Manejador de la base de datos que contiene las ofertas de empleo
     */
    private DbHelper clidbh;
    private static final String BD_NOMBRE = "ZEmpleosNueva";
    private static final int BD_VERSION = 1;
    /**
     * Nombre de la tabla contenedora de las ofertas de empleo
     */
    private static final String TABLA_CLIENTES = "ZEmpleos";

    //Inicializamos el UriMatcher
    /**
     * Contenedor con los tipos de URI
     */
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("net.empleo.android.contentproviders", "clientes", CLIENTES);
        uriMatcher.addURI("net.empleo.android.contentproviders", "clientes/#", CLIENTES_ID);
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

        clidbh = new DbHelper(getContext());

        return true;
    }

    @Override
    /**
     * Recupera de la tabla de ofertas de empleo las que coincidan con los filtros recibidos en los parámetros selection (Provincia) y selectionArgs[] (búsqueda libre realizada por el usuario)
     * @param uri Uri de la BD
     *  @param selection Privincia filtrada
     *  @param selectionArgs Busqueda libre filtrada
     *  @return <ul>
     *          <li>Cursor: Cursor a la primera coincidencia de la busqueda en la BD</li>
     *          </ul>
     */
    public Cursor query(Uri uri, String[] projection,
                        String selection, String[] selectionArgs, String sortOrder) {


        if(selectionArgs==null){
            Cursor c;
            SQLiteDatabase db = clidbh.getWritableDatabase();
            if(selection.equalsIgnoreCase("provincia")){
                c=db.rawQuery("SELECT * FROM ZEmpleos",null);
            }
            else if(selection.equalsIgnoreCase("Salamanca")){
                c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%Salamanca%'",null);
            }
            else if(selection.equalsIgnoreCase("Segovia")){
                c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%Segovia%'",null);
            }
            else if(selection.equalsIgnoreCase("Soria")){
                c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%Soria%'",null);
            }
            else if(selection.equalsIgnoreCase("Valladolid")){
                c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%Valladolid%'",null);
            }
            else if(selection.equalsIgnoreCase("León")){
                c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%León%'",null);
            }
            else if(selection.equalsIgnoreCase("Zamora")){
                c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%Zamora%'",null);
            }
            else if(selection.equalsIgnoreCase("Burgos")){
                c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%Burgos%'",null);
            }
            else if(selection.equalsIgnoreCase("Ávila")){
                c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%Ávila%'",null);
            }
            else if(selection.equalsIgnoreCase("Palencia")){
                c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%Palencia%'",null);
            }
            else{
                if(selectionArgs!=null){
                    selection = selection.replace("'", "''");
                    return db.rawQuery("SELECT "+ DbContract.COLUMN_TITULO+" AS _id, "+DbContract.COLUMN_DESCRIPCION+" AS item" +
                            " FROM "+DbContract.TABLE_NAME +
                            " WHERE "+ DbContract.COLUMN_DESCRIPCION +" LIKE '%" +selection+ "%' ", null);
                }
                c=null;
            }
            return c;
        }
        else{
            String busquedaLibre=selectionArgs[0];
            if(!busquedaLibre.equalsIgnoreCase("")){
                Cursor c;
                SQLiteDatabase db = clidbh.getWritableDatabase();
                if(selection.equalsIgnoreCase("provincia")){
                    c=db.rawQuery("SELECT * FROM ZEmpleos WHERE "+DbContract.COLUMN_DESCRIPCION+" LIKE '%"+busquedaLibre+"%'",null);
                }
                else if(selection.equalsIgnoreCase("Salamanca")){
                    c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%Salamanca%' AND "+DbContract.COLUMN_DESCRIPCION+" LIKE '%"+busquedaLibre+"%'",null);
                }
                else if(selection.equalsIgnoreCase("Segovia")){
                    c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%Segovia%' AND "+DbContract.COLUMN_DESCRIPCION+" LIKE '%"+busquedaLibre+"%'",null);
                }
                else if(selection.equalsIgnoreCase("Soria")){
                    c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%Soria%' AND "+DbContract.COLUMN_DESCRIPCION+" LIKE '%"+busquedaLibre+"%'",null);
                }
                else if(selection.equalsIgnoreCase("Valladolid")){
                    c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%Valladolid%' AND "+DbContract.COLUMN_DESCRIPCION+" LIKE '%"+busquedaLibre+"%'",null);
                }
                else if(selection.equalsIgnoreCase("León")){
                    c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%León%' AND "+DbContract.COLUMN_DESCRIPCION+" LIKE '%"+busquedaLibre+"%'",null);
                }
                else if(selection.equalsIgnoreCase("Zamora")){
                    c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%Zamora%' AND "+DbContract.COLUMN_DESCRIPCION+" LIKE '%"+busquedaLibre+"%'",null);
                }
                else if(selection.equalsIgnoreCase("Burgos")){
                    c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%Burgos%' AND "+DbContract.COLUMN_DESCRIPCION+" LIKE '%"+busquedaLibre+"%'",null);
                }
                else if(selection.equalsIgnoreCase("Ávila")){
                    c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%Ávila%' AND "+DbContract.COLUMN_DESCRIPCION+" LIKE '%"+busquedaLibre+"%'",null);
                }
                else if(selection.equalsIgnoreCase("Palencia")){
                    c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%Palencia%' AND "+DbContract.COLUMN_DESCRIPCION+" LIKE '%"+busquedaLibre+"%'",null);
                }
                else{
                    c=null;
                }
                return c;
            }
            else{
                Cursor c;
                SQLiteDatabase db = clidbh.getWritableDatabase();
                if(selection.equalsIgnoreCase("provincia")){
                    c=db.rawQuery("SELECT * FROM ZEmpleos",null);
                }
                else if(selection.equalsIgnoreCase("Salamanca")){
                    c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%Salamanca%'",null);
                }
                else if(selection.equalsIgnoreCase("Segovia")){
                    c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%Segovia%'",null);
                }
                else if(selection.equalsIgnoreCase("Soria")){
                    c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%Soria%'",null);
                }
                else if(selection.equalsIgnoreCase("Valladolid")){
                    c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%Valladolid%'",null);
                }
                else if(selection.equalsIgnoreCase("León")){
                    c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%León%'",null);
                }
                else if(selection.equalsIgnoreCase("Zamora")){
                    c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%Zamora%'",null);
                }
                else if(selection.equalsIgnoreCase("Burgos")){
                    c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%Burgos%'",null);
                }
                else if(selection.equalsIgnoreCase("Ávila")){
                    c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%Ávila%'",null);
                }
                else if(selection.equalsIgnoreCase("Palencia")){
                    c=db.rawQuery("SELECT * FROM "+DbContract.TABLE_NAME +" WHERE " + DbContract.COLUMN_PROVINCIA + " LIKE '%Palencia%'",null);
                }
                else{
                    if(selectionArgs!=null){
                        selection = selection.replace("'", "''");
                        return db.rawQuery("SELECT "+ DbContract.COLUMN_TITULO+" AS _id, "+DbContract.COLUMN_DESCRIPCION+" AS item" +
                                " FROM "+DbContract.TABLE_NAME +
                                " WHERE "+ DbContract.COLUMN_DESCRIPCION +" LIKE '%" +selection+ "%' ", null);
                    }
                    c=null;
                }
                return c;
            }

        }


    }

    @Override
    /**
     * Introduce en la tabla de ofertas de empleo la tupla recibida en el parámetro values
     * @param values valores de la tupla de oferta de empleo a introducir
     *  @return <ul>
     *          <li>Uri: uri de la BD de insercion</li>
     *          </ul>
     */
    public Uri insert(Uri uri, ContentValues values) {

        long regId = 1;

        SQLiteDatabase db = clidbh.getWritableDatabase();

        regId = db.insert(TABLA_CLIENTES, null, values);

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

        cont = db.update(TABLA_CLIENTES, values, "", selectionArgs);

        return cont;
    }

    @Override
    /**
     * Borra la tupla que coincida con los filtros pasados en selection y selectionArgs[]
     * @param selection privincia filtrada
     * @param selectionArgs busqueda libre filtrada
     *  @return <ul>
     *          <li>int: nº registros borrados</li>
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
        db.execSQL("DELETE FROM "+DbContract.TABLE_NAME);

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