package com.example.emplenews;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Clase DbHelperFavoritosCursos
 *
 * Manejador de la tabla de la base de datos contenedora de las ofertas de formacion favoritas
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class DbHelperFavoritosCursos extends SQLiteOpenHelper {
    /**
     * Nombre de la base de datos de ofertas de formacion favorito
     */
    private static final String DATABASE_NOMBRE="ZFavoritosCursos.db";

    /**
     * Versión de la base de datos de ofertas de formacion favoritas
     */
    private static final int DATABASE_VERSION=1;
    /**
     *Macro representante del caracter “,”
     */
    private final String comma=",";
    /**
     * Macro para la creación de la tabla estructura de la tabla de ofertas de formacion favoritas.
     */
    private  final String SQL_CREATE_BUGS_TABLE= "CREATE TABLE " + Estructura_Favoritos_Cursos.TABLE_NAME + "(" +
            Estructura_Favoritos_Cursos._ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            Estructura_Favoritos_Cursos.COLUMN_TITULO+comma+
            Estructura_Favoritos_Cursos.COLUMN_ORGANISMO +comma+
            Estructura_Favoritos_Cursos.COLUMN_GESTOR + comma+ Estructura_Favoritos_Cursos.COLUMN_TIPO +comma +
            Estructura_Favoritos_Cursos.COLUMN_CIUDAD+comma+
            Estructura_Favoritos_Cursos.COLUMN_COORDENADAS+comma+ Estructura_Favoritos_Cursos.COLUMN_INICIO +comma+
            Estructura_Favoritos_Cursos.COLUMN_FIN+comma+ Estructura_Favoritos_Cursos.COLUMN_FECHAS+ comma+
            Estructura_Favoritos_Cursos.COLUMN_DURACION+comma+ Estructura_Favoritos_Cursos.COLUMN_DESCRIPCION+comma+
            Estructura_Favoritos_Cursos.COLUMN_MATERIA+comma+ Estructura_Favoritos_Cursos.COLUMN_INSCRIPCION+comma+
            Estructura_Favoritos_Cursos.COLUMN_DESTINATARIOS+comma+ Estructura_Favoritos_Cursos.COLUMN_REQUISITOS+comma+
            Estructura_Favoritos_Cursos.COLUMN_LUGAR+comma+ Estructura_Favoritos_Cursos.COLUMN_PLAZAS+comma+
            Estructura_Favoritos_Cursos.COLUMN_INFOADICIONAL+comma+ Estructura_Favoritos_Cursos.COLUMN_ACTUALIZACION+comma+
            Estructura_Favoritos_Cursos.COLUMN_ID_PROVINCIA+comma+
            Estructura_Favoritos_Cursos.COLUMN_ENLACE+comma+ Estructura_Favoritos_Cursos.COLUMN_ID+" );";
    /**
     * Macro para la eliminación de la base de datos de ofertas de formacion favoritas
     */
    private final String SQL_DELETE_ENTRIES= "DROP TABLE IF EXISTS "+ DbHelperFavoritosCursos.DATABASE_NOMBRE;
    /**
     * Constructor por defecto
     */
    public DbHelperFavoritosCursos(Context context){
        super(context,DATABASE_NOMBRE,null,DATABASE_VERSION);
    }

    @Override
    /**
     * Crea la tabla de ofertas de formacion favoritas en la base de datos
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
                " FROM "+Estructura_Favoritos_Cursos.TABLE_NAME +
                " WHERE provincia LIKE '%" +textSearch+ "%' ", null);
    }
}
