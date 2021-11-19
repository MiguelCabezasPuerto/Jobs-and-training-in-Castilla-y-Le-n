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
 * Clase FormacionProvider
 *
 * DAO tratante de la información almacenada relativa a las ofertas de formacion favoritas
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class FormacionProvider extends ContentProvider {
    //Definición del CONTENT_URI
    /**
     * Cadena con la URI de la tabla de contenido de las ofertas de formacion
     */
    private static final String uri =
            "content://net.formacion.android.provider/ZFormacionNueva";
    /**
     * URI de la tabla de contenido de las ofertas de formacion
     */
    public static final Uri CONTENT_URI = Uri.parse(uri);

    //Necesario para UriMatcher
    private static final int CLIENTES = 1;
    private static final int CLIENTES_ID = 2;
    private static final UriMatcher uriMatcher;


    //Base de datos
    /**
     * Manejador de la base de datos que contiene las ofertas de formacion
     */
    private DbHelperFormacion clidbh;
    private static final String BD_NOMBRE = "ZFormacionNueva";
    private static final int BD_VERSION = 1;
    /**
     * Nombre de la tabla contenedora de las ofertas de formacion
     */
    private static final String TABLA_FORMACION = "ZFormacionNueva";

    //Inicializamos el UriMatcher
    /**
     * Contenedor con los tipos de URI
     */
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("net.formacion.android.provider", "clientes", CLIENTES);
        uriMatcher.addURI("net.formacion.android.provider", "clientes/#", CLIENTES_ID);
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

        clidbh = new DbHelperFormacion(getContext());

        return true;
    }

    @Override
    /**
     * Recupera de la tabla de ofertas de formacion  las que coincidan con los filtros recibidos en los parámetros selection (Provincia) y selectionArgs[] (búsqueda libre realizada por el usuario y materia)
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
        Cursor c=null;
        SQLiteDatabase db = clidbh.getWritableDatabase();

        if(selectionArgs==null){
            if(selection.equalsIgnoreCase("*")){

                c=db.rawQuery("SELECT * FROM ZFormacionNueva",null);
            }
            else if(selection.equalsIgnoreCase("Ávila")){
                c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%05%'",null);
            }
            else if(selection.equalsIgnoreCase("Burgos")){
                c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%09%'",null);
            }else if(selection.equalsIgnoreCase("León")){
                c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%24%'",null);
            }else if(selection.equalsIgnoreCase("Palencia")){
                c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%34%'",null);
            }else if(selection.equalsIgnoreCase("Salamanca")){
                c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%37%'",null);
            }else if(selection.equalsIgnoreCase("Segovia")){
                c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%40%'",null);
            }else if(selection.equalsIgnoreCase("Soria")){
                c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%42%'",null);
            }else if(selection.equalsIgnoreCase("Valladolid")){
                c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%47%'",null);
            }else if(selection.equalsIgnoreCase("Zamora")){
                c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%49%'",null);
            }
            else{
                c=db.rawQuery("SELECT * FROM ZFormacionNueva",null);
            }
        }else{
            if(selectionArgs.length==1){
                String familia=selectionArgs[0];
                Log.e("FAMILIA ES",familia);

                if(familia.equalsIgnoreCase("*")){
                    Log.e("EN FAMILIA",familia);
                    if(selection.equalsIgnoreCase("*")){
                        Log.e("EN Ciudad","*");
                        c=db.rawQuery("SELECT * FROM ZFormacionNueva",null);
                    }
                    else if(selection.equalsIgnoreCase("Ávila")){
                        Log.e("EN Ciudad","Avila");
                        c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%05%'",null);
                    }
                    else if(selection.equalsIgnoreCase("Burgos")){
                        Log.e("EN Ciudad","Burgos");
                        c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%09%'",null);
                    }else if(selection.equalsIgnoreCase("León")){
                        Log.e("EN Ciudad","Leon");
                        c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%24%'",null);
                    }else if(selection.equalsIgnoreCase("Palencia")){
                        Log.e("EN Ciudad","Palencia");
                        c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%34%'",null);
                    }else if(selection.equalsIgnoreCase("Salamanca")){
                        Log.e("EN Ciudad","Salamanca");
                        c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%37%'",null);
                    }else if(selection.equalsIgnoreCase("Segovia")){
                        Log.e("EN Ciudad","Segovia");
                        c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%40%'",null);
                    }else if(selection.equalsIgnoreCase("Soria")){
                        Log.e("EN Ciudad","Soria");
                        c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%42%'",null);
                    }else if(selection.equalsIgnoreCase("Valladolid")){
                        Log.e("EN Ciudad","Valladolid");
                        c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%47%'",null);
                    }else if(selection.equalsIgnoreCase("Zamora")){
                        Log.e("EN Ciudad","Zamora");
                        c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%49%'",null);
                    }
                    else{
                        Log.e("EN Ciudad","Otro");
                        c=db.rawQuery("SELECT * FROM ZFormacionNueva",null);
                    }
                }
                else{
                    Log.e("FAMILIA ES",familia);
                    if(selection.equalsIgnoreCase("*")){
                        Log.e("EN Ciudad","*");
                        c=db.rawQuery("SELECT * FROM ZFormacionNueva"+" WHERE "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%'",null);
                    }
                    else if(selection.equalsIgnoreCase("Ávila")){
                        Log.e("EN Ciudad","Avila");
                        c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%05%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%'",null);
                    }
                    else if(selection.equalsIgnoreCase("Burgos")){
                        Log.e("EN Ciudad","Burgos");
                        c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%09%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%'",null);
                    }else if(selection.equalsIgnoreCase("León")){
                        Log.e("EN Ciudad","Leon");
                        c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%24%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%'",null);
                    }else if(selection.equalsIgnoreCase("Palencia")){
                        Log.e("EN Ciudad","Palencia");
                        c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%34%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%'",null);
                    }else if(selection.equalsIgnoreCase("Salamanca")){
                        Log.e("EN Ciudad","Salamanca");
                        c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%37%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%'",null);
                    }else if(selection.equalsIgnoreCase("Segovia")){
                        Log.e("EN Ciudad","Segovia");
                        c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%40%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%'",null);
                    }else if(selection.equalsIgnoreCase("Soria")){
                        Log.e("EN Ciudad","Soria");
                        c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%42%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%'",null);
                    }else if(selection.equalsIgnoreCase("Valladolid")){
                        Log.e("EN Ciudad","Valladolid");
                        c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%47%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%'",null);
                    }else if(selection.equalsIgnoreCase("Zamora")){
                        Log.e("EN Ciudad","Zamora");
                        c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%49%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%'",null);
                    }
                    else{
                        Log.e("EN Ciudad","Otro");
                        c=db.rawQuery("SELECT * FROM ZFormacionNueva"+" WHERE "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%'",null);
                    }
                }
            }
            else {
                String familia=selectionArgs[0];
                String busquedaLibre=selectionArgs[1];
                Log.e("FAMILIA ES",familia);
                Log.e("Busqueda libre es",busquedaLibre);

                if(busquedaLibre.isEmpty() || busquedaLibre.equalsIgnoreCase("")){
                    if(familia.equalsIgnoreCase("*")){
                        Log.e("EN FAMILIA",familia);
                        if(selection.equalsIgnoreCase("*")){
                            Log.e("EN Ciudad","*");
                            c=db.rawQuery("SELECT * FROM ZFormacionNueva",null);
                        }
                        else if(selection.equalsIgnoreCase("Ávila")){
                            Log.e("EN Ciudad","Avila");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%05%'",null);
                        }
                        else if(selection.equalsIgnoreCase("Burgos")){
                            Log.e("EN Ciudad","Burgos");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%09%'",null);
                        }else if(selection.equalsIgnoreCase("León")){
                            Log.e("EN Ciudad","Leon");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%24%'",null);
                        }else if(selection.equalsIgnoreCase("Palencia")){
                            Log.e("EN Ciudad","Palencia");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%34%'",null);
                        }else if(selection.equalsIgnoreCase("Salamanca")){
                            Log.e("EN Ciudad","Salamanca");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%37%'",null);
                        }else if(selection.equalsIgnoreCase("Segovia")){
                            Log.e("EN Ciudad","Segovia");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%40%'",null);
                        }else if(selection.equalsIgnoreCase("Soria")){
                            Log.e("EN Ciudad","Soria");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%42%'",null);
                        }else if(selection.equalsIgnoreCase("Valladolid")){
                            Log.e("EN Ciudad","Valladolid");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%47%'",null);
                        }else if(selection.equalsIgnoreCase("Zamora")){
                            Log.e("EN Ciudad","Zamora");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%49%'",null);
                        }
                        else{
                            Log.e("EN Ciudad","Otro");
                            c=db.rawQuery("SELECT * FROM ZFormacionNueva",null);
                        }
                    }
                    else{
                        Log.e("FAMILIA ES",familia);
                        if(selection.equalsIgnoreCase("*")){
                            Log.e("EN Ciudad","*");
                            c=db.rawQuery("SELECT * FROM ZFormacionNueva"+" WHERE "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%'",null);
                        }
                        else if(selection.equalsIgnoreCase("Ávila")){
                            Log.e("EN Ciudad","Avila");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%05%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%'",null);
                        }
                        else if(selection.equalsIgnoreCase("Burgos")){
                            Log.e("EN Ciudad","Burgos");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%09%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%'",null);
                        }else if(selection.equalsIgnoreCase("León")){
                            Log.e("EN Ciudad","Leon");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%24%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%'",null);
                        }else if(selection.equalsIgnoreCase("Palencia")){
                            Log.e("EN Ciudad","Palencia");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%34%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%'",null);
                        }else if(selection.equalsIgnoreCase("Salamanca")){
                            Log.e("EN Ciudad","Salamanca");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%37%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%'",null);
                        }else if(selection.equalsIgnoreCase("Segovia")){
                            Log.e("EN Ciudad","Segovia");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%40%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%'",null);
                        }else if(selection.equalsIgnoreCase("Soria")){
                            Log.e("EN Ciudad","Soria");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%42%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%'",null);
                        }else if(selection.equalsIgnoreCase("Valladolid")){
                            Log.e("EN Ciudad","Valladolid");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%47%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%'",null);
                        }else if(selection.equalsIgnoreCase("Zamora")){
                            Log.e("EN Ciudad","Zamora");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%49%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%'",null);
                        }
                        else{
                            Log.e("EN Ciudad","Otro");
                            c=db.rawQuery("SELECT * FROM ZFormacionNueva"+" WHERE "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%'",null);
                        }
                    }
                }else{
                    if(familia.equalsIgnoreCase("*")){
                        Log.e("EN FAMILIA",familia);
                        if(selection.equalsIgnoreCase("*")){
                            Log.e("EN Ciudad","*");
                            c=db.rawQuery("SELECT * FROM ZFormacionNueva WHERE "+Estructura_Formacion.COLUMN_TITULO+ " LIKE '%"+busquedaLibre+"%'",null);
                        }
                        else if(selection.equalsIgnoreCase("Ávila")){
                            Log.e("EN Ciudad","Avila");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%05%' AND "+Estructura_Formacion.COLUMN_TITULO+ " LIKE '%"+busquedaLibre+"%'",null);
                        }
                        else if(selection.equalsIgnoreCase("Burgos")){
                            Log.e("EN Ciudad","Burgos");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%09%' AND "+Estructura_Formacion.COLUMN_TITULO+ " LIKE '%"+busquedaLibre+"%'",null);
                        }else if(selection.equalsIgnoreCase("León")){
                            Log.e("EN Ciudad","Leon");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%24%' AND "+Estructura_Formacion.COLUMN_TITULO+ " LIKE '%"+busquedaLibre+"%'",null);
                        }else if(selection.equalsIgnoreCase("Palencia")){
                            Log.e("EN Ciudad","Palencia");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%34%' AND "+Estructura_Formacion.COLUMN_TITULO+ " LIKE '%"+busquedaLibre+"%'",null);
                        }else if(selection.equalsIgnoreCase("Salamanca")){
                            Log.e("EN Ciudad","Salamanca");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%37%' AND "+Estructura_Formacion.COLUMN_TITULO+ " LIKE '%"+busquedaLibre+"%'",null);
                        }else if(selection.equalsIgnoreCase("Segovia")){
                            Log.e("EN Ciudad","Segovia");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%40%' AND "+Estructura_Formacion.COLUMN_TITULO+ " LIKE '%"+busquedaLibre+"%'",null);
                        }else if(selection.equalsIgnoreCase("Soria")){
                            Log.e("EN Ciudad","Soria");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%42%' AND "+Estructura_Formacion.COLUMN_TITULO+ " LIKE '%"+busquedaLibre+"%'",null);
                        }else if(selection.equalsIgnoreCase("Valladolid")){
                            Log.e("EN Ciudad","Valladolid");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%47%' AND "+Estructura_Formacion.COLUMN_TITULO+ " LIKE '%"+busquedaLibre+"%'",null);
                        }else if(selection.equalsIgnoreCase("Zamora")){
                            Log.e("EN Ciudad","Zamora");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%49%' AND "+Estructura_Formacion.COLUMN_TITULO+ " LIKE '%"+busquedaLibre+"%'",null);
                        }
                        else{
                            Log.e("EN Ciudad","Otro");
                            c=db.rawQuery("SELECT * FROM ZFormacionNueva WHERE "+Estructura_Formacion.COLUMN_TITULO+ " LIKE '%"+busquedaLibre+"%'",null);
                        }
                    }
                    else{
                        Log.e("FAMILIA ES",familia);
                        if(selection.equalsIgnoreCase("*")){
                            Log.e("EN Ciudad","*");
                            c=db.rawQuery("SELECT * FROM ZFormacionNueva"+" WHERE "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%' AND "+Estructura_Formacion.COLUMN_TITULO+ " LIKE '%"+busquedaLibre+"%'",null);
                        }
                        else if(selection.equalsIgnoreCase("Ávila")){
                            Log.e("EN Ciudad","Avila");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%05%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%' AND "+Estructura_Formacion.COLUMN_TITULO+ " LIKE '%"+busquedaLibre+"%'",null);
                        }
                        else if(selection.equalsIgnoreCase("Burgos")){
                            Log.e("EN Ciudad","Burgos");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%09%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%' AND "+Estructura_Formacion.COLUMN_TITULO+ " LIKE '%"+busquedaLibre+"%'",null);
                        }else if(selection.equalsIgnoreCase("León")){
                            Log.e("EN Ciudad","Leon");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%24%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%' AND "+Estructura_Formacion.COLUMN_TITULO+ " LIKE '%"+busquedaLibre+"%'",null);
                        }else if(selection.equalsIgnoreCase("Palencia")){
                            Log.e("EN Ciudad","Palencia");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%34%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%' AND "+Estructura_Formacion.COLUMN_TITULO+ " LIKE '%"+busquedaLibre+"%'",null);
                        }else if(selection.equalsIgnoreCase("Salamanca")){
                            Log.e("EN Ciudad","Salamanca");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%37%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%' AND "+Estructura_Formacion.COLUMN_TITULO+ " LIKE '%"+busquedaLibre+"%'",null);
                        }else if(selection.equalsIgnoreCase("Segovia")){
                            Log.e("EN Ciudad","Segovia");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%40%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%' AND "+Estructura_Formacion.COLUMN_TITULO+ " LIKE '%"+busquedaLibre+"%'",null);
                        }else if(selection.equalsIgnoreCase("Soria")){
                            Log.e("EN Ciudad","Soria");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%42%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%' AND "+Estructura_Formacion.COLUMN_TITULO+ " LIKE '%"+busquedaLibre+"%'",null);
                        }else if(selection.equalsIgnoreCase("Valladolid")){
                            Log.e("EN Ciudad","Valladolid");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%47%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%' AND "+Estructura_Formacion.COLUMN_TITULO+ " LIKE '%"+busquedaLibre+"%'",null);
                        }else if(selection.equalsIgnoreCase("Zamora")){
                            Log.e("EN Ciudad","Zamora");
                            c=db.rawQuery("SELECT * FROM "+Estructura_Formacion.TABLE_NAME +" WHERE " + Estructura_Formacion.COLUMN_ID_PROVINCIA + " LIKE '%49%' AND "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%' AND "+Estructura_Formacion.COLUMN_TITULO+ " LIKE '%"+busquedaLibre+"%'",null);
                        }
                        else{
                            Log.e("EN Ciudad","Otro");
                            c=db.rawQuery("SELECT * FROM ZFormacionNueva"+" WHERE "+Estructura_Formacion.COLUMN_MATERIA+" LIKE "+"'%"+familia+"%' AND "+Estructura_Formacion.COLUMN_TITULO+ " LIKE '%"+busquedaLibre+"%'",null);
                        }
                    }
                }


            }

        }


        return c;
    }
    /**
     * Introduce en la tabla de ofertas de formacion la tupla recibida en el parámetro values
     * @param values valores de la tupla de oferta de formacion  a introducir
     *  @return <ul>
     *          <li>Uri: uri de la BD de insercion</li>
     *          </ul>
     */
    public Uri insert(Uri uri, ContentValues values) {


        long regId = 1;

        SQLiteDatabase db = clidbh.getWritableDatabase();

        regId = db.insert(TABLA_FORMACION, null, values);

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

        cont = db.update(TABLA_FORMACION, values, "", selectionArgs);

        return cont;
    }

    @Override
    /**
     * Borra la tupla que coincida con los filtros pasados en selection y selectionArgs[]
     * @param selection provincia filtrada
     * @param selectionArgs busqueda libre filtrada y materia
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
        db.execSQL("DELETE FROM "+Estructura_Formacion.TABLE_NAME);
        //db.execSQL("DELETE FROM "+Estructura_Formacion.TABLE_NAME+" WHERE "+Estructura_Formacion.COLUMN_ID+" LIKE "+selection);


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