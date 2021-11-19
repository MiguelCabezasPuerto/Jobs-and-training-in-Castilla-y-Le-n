package com.example.emplenews;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.emplenews.DbContract;
import com.example.emplenews.DbHelper;
import com.example.emplenews.Estructura_Favoritos;
/**
 * Clase DbHelperFavoritos
 *
 * Manejador de la tabla de la base de datos contenedora de las ofertas de empleo favoritas
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class DbHelperFavoritos extends SQLiteOpenHelper {
    /**
     * Nombre de la base de datos de ofertas de empleo favorito
     */
    private static final String DATABASE_NOMBRE="ZFavoritos.db";
    /**
     * Versión de la base de datos de ofertas de empleo favoritas
     */
    private static final int DATABASE_VERSION=1;
    /**
     *Macro representante del caracter “,”
     */
    private final String comma=",";
    /**
     * Macro para la creación de la tabla estructura de la tabla de ofertas de empleo favoritas.
     */
    private  final String SQL_CREATE_BUGS_TABLE= "CREATE TABLE " + Estructura_Favoritos.TABLE_NAME + "(" +
            Estructura_Favoritos._ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            Estructura_Favoritos.COLUMN_TITULO+comma+
            Estructura_Favoritos.COLUMN_PROVINCIA +comma+
            Estructura_Favoritos.COLUMN_FECHA + comma+Estructura_Favoritos.COLUMN_DESCRIPCION +comma +
            Estructura_Favoritos.COLUMN_FUENTE+comma+
            Estructura_Favoritos.COLUMN_CIUDAD+comma+Estructura_Favoritos.COLUMN_COORDENADAS +comma+
            Estructura_Favoritos.COLUMN_ACTUALIZACION+comma+ Estructura_Favoritos.COLUMN_ENLACE+ comma+
            Estructura_Favoritos.COLUMN_ID+" );";
    /**
     * Macro para la eliminación de la base de datos de ofertas de empleo favoritas
     */
    private final String SQL_DELETE_ENTRIES= "DROP TABLE IF EXISTS "+ DbHelperFavoritos.DATABASE_NOMBRE;
    /**
     * Constructor por defecto
     */
    public DbHelperFavoritos(Context context){
        super(context,DATABASE_NOMBRE,null,DATABASE_VERSION);
    }

    @Override
    /**
     * Crea la tabla de ofertas de empleo favoritas en la base de datos
     * @param db manejador de la base de datos
     */
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_BUGS_TABLE);
    }

    @Override
    /**
     * Actualiza la base de datos
     * @param db manejador de la base de datos
     * @param oldVersion version anterior
     * @param newVersion nueva version
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    /**
     * Regresa a la anterior versión de la base de datos
     * @param db manejador de la base de datos
     * @param oldVersion version anterior
     * @param newVersion nueva version
     */
    public void onDowngrade(SQLiteDatabase db,int oldVersion,int newVersion){
        onUpgrade(db,oldVersion,newVersion);
    }

    public Cursor getCursorBuscador(String textSearch){
        textSearch = textSearch.replace("'", "''");
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT provincia AS _id, titulo AS item" +
                " FROM "+Estructura_Favoritos.TABLE_NAME +
                " WHERE provincia LIKE '%" +textSearch+ "%' ", null);
    }
}
