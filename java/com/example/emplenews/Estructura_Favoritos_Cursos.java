package com.example.emplenews;

import android.provider.BaseColumns;
/**
 * Clase Estructura_Favoritos
 *
 * Define la estructura de la tabla de ofertas de formacion favoritas de la base de datos
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class Estructura_Favoritos_Cursos implements BaseColumns {
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
