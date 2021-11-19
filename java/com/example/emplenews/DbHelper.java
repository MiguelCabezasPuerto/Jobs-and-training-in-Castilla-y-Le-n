package com.example.emplenews;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Clase DbHelper
 *
 * Manejador de la tabla de la base de datos contenedora de las ofertas de empleo
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class DbHelper extends SQLiteOpenHelper{
    /**
     * Nombre de la base de datos de ofertas de empleo
     */
    private static final String DATABASE_NOMBRE="ZEmpleosNueva.db";
    /**
     * Versión de la base de datos de ofertas de empleo
     */
    private static final int DATABASE_VERSION=1;

    //private static DbHelper db;

    /**
     *Macro representante del caracter “,”
     */
    private final String comma=",";
    /**
     * Macro para la creación de la tabla estructura de la tabla de ofertas de empleo.
     */
    private  final String SQL_CREATE_BUGS_TABLE= "CREATE TABLE " + DbContract.TABLE_NAME + "(" +
            DbContract._ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            DbContract.COLUMN_TITULO+comma+
            DbContract.COLUMN_PROVINCIA +comma+
            DbContract.COLUMN_FECHA + comma+DbContract.COLUMN_DESCRIPCION +comma +
            DbContract.COLUMN_FUENTE+comma+
            DbContract.COLUMN_CIUDAD+comma+DbContract.COLUMN_COORDENADAS +comma+
            DbContract.COLUMN_ACTUALIZACION+comma+ DbContract.COLUMN_ENLACE+ comma+
            DbContract.COLUMN_ID+" );";
    /**
     * Macro para la eliminación de la base de datos de ofertas de empleo
     */
    private final String SQL_DELETE_ENTRIES= "DROP TABLE IF EXISTS "+DbHelper.DATABASE_NOMBRE;

    /**
     * Constructor por defecto
     */
    public DbHelper(Context context){
        super(context,DATABASE_NOMBRE,null,DATABASE_VERSION);
    }




    @Override
    /**
     * Crea la tabla de ofertas de empleo en la base de datos
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
                " FROM "+DbContract.TABLE_NAME +
                " WHERE provincia LIKE '%" +textSearch+ "%' ", null);
    }


}
