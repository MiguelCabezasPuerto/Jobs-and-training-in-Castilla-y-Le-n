package com.example.emplenews;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.util.ArrayList;
/**
 * Clase ControladorBD
 *
 * Controlador encargado de comunicarse con los DAO para gestionar los datos de las ofertas
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class ControladorBD extends Utils {
    /**
     * Genera una nueva instancia del controlador, los constructores sin ningun parametro pasado resultan de analoga explicacion por lo que no se documentan
     */
    public ControladorBD(){

    }

    /**
     * Se comunica con un determinado ContentProvider dependiendo del parámetro BD pasado para comprobar si existe un determinado registro en la tabla correspondiente que cumpla con el filtro pasado a través del parámetro selection. En caso de existir devuelve la posición en la tabla del registro. De lo contrario devuelve null.
     * @param selection filtro de busqueda
     * @param c contexto desde el que el metodo es usado
     * @param BD tabla de la base de datos en la que realizar la busqueda
     * @return <ul>
     *          <li>cursor: puntero a la primera tupla coincidente/li>
     *          <li>null: no hay coincidencias/li>
     *          </ul>
     */
    Cursor buscarRegistroBD(String selection,Context c,int BD){
        ContentResolver cr;
        if(BD==1){
            cr = c.getContentResolver();
            Uri clientesUri =  ClientesProvider.CONTENT_URI;
            Cursor cursor=cr.query(clientesUri,
                    null,
                    selection,
                    null,
                    null);
            return cursor;

        }
        else if(BD==2){
            cr = c.getContentResolver();
            Uri formacionUri =  FormacionProvider.CONTENT_URI;
            Cursor cursor=cr.query(formacionUri,
                    null,
                    selection,
                    null,
                    null);
            return cursor;
        }
        else if(BD==3){
            cr = c.getContentResolver();
            Uri favoritosUri =  FavoritosProvider.CONTENT_URI;
            Cursor cursor=cr.query(favoritosUri,
                    null,
                    selection,
                    null,
                    null);
            return cursor;
        }else if(BD==4){
            cr = c.getContentResolver();
            Uri favoritosCursosUri =  FavoritosCursosProvider.CONTENT_URI;
            Cursor cursor=cr.query(favoritosCursosUri,
                    null,
                    selection,
                    null,
                    null);
            return cursor;
        }
        else{
            return null;
        }
    }
    /**
     * Se comunica con un determinado ContentProvider dependiendo del parámetro BD pasado para borrar el registro de la tabla correspondiente que cumpla con el filtro pasado mediante el parámetro selection.
     * @param selection filtro de busqueda
     * @param c contexto desde el que el metodo es usado
     * @param BD tabla de la base de datos en la que realizar el borrado
     */
    void borrarRegistroBD(String selection,Context c,int BD){
        ContentResolver cr;
        if(BD==1){
            cr = c.getContentResolver();
            Uri clientesUri =  ClientesProvider.CONTENT_URI;
            cr.delete(clientesUri,selection,null);
        }
        else if(BD==2){
            cr = c.getContentResolver();
            Uri formacionUri =  FormacionProvider.CONTENT_URI;
            cr.delete(formacionUri,selection,null);
        }else if(BD==3){
            cr = c.getContentResolver();
            Uri favoritosUri =  FavoritosProvider.CONTENT_URI;
            cr.delete(favoritosUri,selection,null);
        }else if(BD==4){
            cr = c.getContentResolver();
            Uri favoritosCursosUri =  FavoritosCursosProvider.CONTENT_URI;
            cr.delete(favoritosCursosUri,selection,null);
        }
    }
    /**
     * Se comunica con el ContentProvider asociado a la tabla de las ofertas de empleo favoritas para insertar una nueva oferta. Devuelve null en caso de error en la inserción.
     * @param ofertaAMostrar Oferta marcada como favorita que se debe almacenar en la tabla
     * @return <ul>
     *          <li>Uri: URI de la tabla de la base de datos donde se inserta el registro</li>
     */
    Uri insertarEmpleoEnFavoritos(OfertasEmpleo ofertaAMostrar,Context c){
        ContentResolver cr;
        cr = c.getContentResolver();
        Uri favoritosUri =  FavoritosProvider.CONTENT_URI;
        ContentValues values = new ContentValues();

        values.put(Estructura_Favoritos.COLUMN_ID,ofertaAMostrar.getId());
        values.put(Estructura_Favoritos.COLUMN_TITULO,ofertaAMostrar.getTitulo());
        values.put(Estructura_Favoritos.COLUMN_PROVINCIA,ofertaAMostrar.getProvincia());
        values.put(Estructura_Favoritos.COLUMN_FECHA,ofertaAMostrar.getFecha_publicacion());
        values.put(Estructura_Favoritos.COLUMN_DESCRIPCION,ofertaAMostrar.getDescripcion());
        values.put(Estructura_Favoritos.COLUMN_FUENTE,ofertaAMostrar.getFuente());
        values.put(Estructura_Favoritos.COLUMN_CIUDAD,ofertaAMostrar.getCiudad());
        values.put(Estructura_Favoritos.COLUMN_COORDENADAS,ofertaAMostrar.getCoordenadas());
        values.put(Estructura_Favoritos.COLUMN_ACTUALIZACION,ofertaAMostrar.getActualizacion());
        values.put(Estructura_Favoritos.COLUMN_ENLACE,ofertaAMostrar.getEnlace());
        Uri test= cr.insert(favoritosUri, values);
        return test;
    }
    /**
     * Se comunica con el ContentProvider asociado a la tabla de las ofertas de formacion favoritas para insertar una nueva oferta. Devuelve null en caso de error en la inserción.
     * @param ofertaAMostrar Oferta marcada como favorita que se debe almacenar en la tabla
     * @return <ul>
     *          <li>Uri: URI de la tabla de la base de datos donde se inserta el registro</li>
     */
    Uri insertarCursoEnFavoritos(OfertasFormacion ofertaAMostrar,Context c){
        ContentResolver cr;
        cr = c.getContentResolver();
        Uri favoritosCursosUri =  FavoritosCursosProvider.CONTENT_URI;
        ContentValues values = new ContentValues();

        values.put(Estructura_Favoritos_Cursos.COLUMN_ID,ofertaAMostrar.getId());
        values.put(Estructura_Favoritos_Cursos.COLUMN_TITULO,ofertaAMostrar.getTitulo());
        values.put(Estructura_Favoritos_Cursos.COLUMN_CIUDAD,ofertaAMostrar.getCiudad());
        values.put(Estructura_Favoritos_Cursos.COLUMN_COORDENADAS,ofertaAMostrar.getCoordenadas());
        values.put(Estructura_Favoritos_Cursos.COLUMN_FECHAS,ofertaAMostrar.getFechas());
        values.put(Estructura_Favoritos_Cursos.COLUMN_ORGANISMO,ofertaAMostrar.getOrganismo());
        values.put(Estructura_Favoritos_Cursos.COLUMN_TIPO,ofertaAMostrar.getTipo());
        values.put(Estructura_Favoritos_Cursos.COLUMN_DURACION,ofertaAMostrar.getDuracion());
        values.put(Estructura_Favoritos_Cursos.COLUMN_DESCRIPCION,ofertaAMostrar.getDescripcion());
        values.put(Estructura_Favoritos_Cursos.COLUMN_MATERIA,ofertaAMostrar.getMateria());
        values.put(Estructura_Favoritos_Cursos.COLUMN_DESTINATARIOS,ofertaAMostrar.getDestinatarios());
        values.put(Estructura_Favoritos_Cursos.COLUMN_LUGAR,ofertaAMostrar.getLugar());
        values.put(Estructura_Favoritos_Cursos.COLUMN_INSCRIPCION,ofertaAMostrar.getInscripcion());
        values.put(Estructura_Favoritos_Cursos.COLUMN_PLAZAS,ofertaAMostrar.getPlazas());
        values.put(Estructura_Favoritos_Cursos.COLUMN_INFOADICIONAL,ofertaAMostrar.getInfoAdicional());
        values.put(Estructura_Favoritos_Cursos.COLUMN_ACTUALIZACION,ofertaAMostrar.getActualizacion());
        values.put(Estructura_Favoritos_Cursos.COLUMN_ENLACE,ofertaAMostrar.getEnlace());
        values.put(Estructura_Favoritos_Cursos.COLUMN_ID_PROVINCIA,ofertaAMostrar.getCod_provincia());
        Uri test=cr.insert(favoritosCursosUri, values);
        return test;
    }

    /**
     * Se comunica con el ContentProvider asociado a la tabla de las ofertas de empleo para devolver, en forma de cadena, el número de registros que contiene dicha tabla en el momento de la invocación del método.
     * @param context contexto desde donde se invoca el metodo
     * @return <ul>
     *          <li>String: nº de ofertas de empleo almacenadas</li>
     *          </ul>
     */
    String numRegistrosEmpleos(Context context){
        ContentResolver cr1=context.getContentResolver();
        Uri empleosUri =  ClientesProvider.CONTENT_URI;
        Cursor cursor1=cr1.query(empleosUri,
                null,
                "provincia",
                null,
                null);
        return ""+cursor1.getCount();
    }
    /**
     * Se comunica con el ContentProvider asociado a la tabla de las ofertas de formacion para devolver, en forma de cadena, el número de registros que contiene dicha tabla en el momento de la invocación del método.
     * @param context contexto desde donde se invoca el metodo
     * @return <ul>
     *          <li>String: nº de ofertas de formacion almacenadas</li>
     *          </ul>
     */
    String numRegistrosFormacion(Context context){
        ContentResolver cr3=context.getContentResolver();
        Uri formacionUri= FormacionProvider.CONTENT_URI;
        Cursor cursor3=cr3.query(formacionUri,
                null,
                "*",
                null,
                null);
        return ""+cursor3.getCount();
    }
    /**
     * Se comunica con el ContentProvider asociado a la tabla de las ofertas de formación favoritas y con el ContentProvider asociado a la tabla de las ofertas de empleo favoritas, suma el número de registros contenidos en cada tabla para así devolver, en forma de cadena, el número total de favoritos contenidos en la base de datos.
     * @param c contexto desde donde se invoca el metodo
     * @return <ul>
     *          <li>String: nº de registros favoritos almacenados</li>
     *          </ul>
     */
    String numRegistrosFavoritos(Context c){
        ContentResolver cr2=c.getContentResolver();
        ContentResolver cr4=c.getContentResolver();
        Uri favoritosUri= FavoritosProvider.CONTENT_URI;
        Cursor cursor2=cr2.query(favoritosUri,
                null,
                "*",
                null,
                null);



        Uri favoritosCursosUri= FavoritosCursosProvider.CONTENT_URI;


        Cursor cursor4=cr4.query(favoritosCursosUri,
                null,
                "*",
                null,
                null);

        String cantidadFavoritos=""+cursor2.getCount();
        String cantidadCursosFavoritos=""+cursor4.getCount();
        long totalFavoritos=Integer.parseInt(cantidadFavoritos)+Integer.parseInt(cantidadCursosFavoritos);
        String cantidadTotalFavoritos=String.valueOf(totalFavoritos);
        return cantidadTotalFavoritos;
    }
    /**
     * Se comunica con el ContentProvider correspondiente para recuperar todas las ofertas de empleo o formación contenidas en las tablas para así poder devolverlas en un listado.
     * @param clase indica si los favoritos son de empleo o de formacion
     * @return <ul>
     *          <li>ArrayList: listado de favoritos de empleo o formacion</li>
     *          </ul>
     */
    public ArrayList mostrarFavoritos(int clase,Context c){
        ControladorAvisos managerAvisos=new ControladorAvisos();
        ControladorOfertasEmpleo managerEmpleos=new ControladorOfertasEmpleo();
        ContentResolver cr;
        ContentResolver crCursos;

        if(clase==1){
            ArrayList<OfertasEmpleo>ofers;
            cr = c.getContentResolver();
            Uri favoritosUri =  FavoritosProvider.CONTENT_URI;
            Cursor cursor=cr.query(favoritosUri,
                    null,
                    "*",
                    null,
                    null);

            if(cursor.getCount()==0){
                managerAvisos.avisosToast(c.getString(R.string.sinfavoritosempleo),c);
            }




            ofers= new ArrayList<>();
            int m=0;
            while(cursor.moveToNext()) {
                m=0;
                String titulo = cursor.getString(
                        cursor.getColumnIndexOrThrow(Estructura_Favoritos.COLUMN_TITULO));
                String prov=cursor.getString(
                        cursor.getColumnIndexOrThrow(Estructura_Favoritos.COLUMN_PROVINCIA));
                String ciudad=cursor.getString(
                        cursor.getColumnIndexOrThrow(Estructura_Favoritos.COLUMN_CIUDAD));
                String source=cursor.getString(
                        cursor.getColumnIndexOrThrow(Estructura_Favoritos.COLUMN_FUENTE));
                String date=cursor.getString(
                        cursor.getColumnIndexOrThrow(Estructura_Favoritos.COLUMN_FECHA));
                String description=cursor.getString(
                        cursor.getColumnIndexOrThrow(Estructura_Favoritos.COLUMN_DESCRIPCION));
                String enlace=cursor.getString(
                        cursor.getColumnIndexOrThrow(Estructura_Favoritos.COLUMN_ENLACE));
                String actualizacion=cursor.getString(
                        cursor.getColumnIndexOrThrow(Estructura_Favoritos.COLUMN_ACTUALIZACION));
                String coordenadas=cursor.getString(
                        cursor.getColumnIndexOrThrow(Estructura_Favoritos.COLUMN_COORDENADAS));
                String id=cursor.getString(
                        cursor.getColumnIndexOrThrow(Estructura_Favoritos.COLUMN_ID));

                OfertasEmpleo o=new OfertasEmpleo();
                managerEmpleos.escribirTituloEmpleo(o,titulo);
                managerEmpleos.escribirProvinciaEmpleo(o,prov);
                managerEmpleos.escribirCiudadEmpleo(o,ciudad);
                managerEmpleos.escribirFuenteEmpleo(o,source);
                managerEmpleos.escribirFechaPubEmpleo(o,date);
                managerEmpleos.escribirDescripcionEmpleo(o,description);
                managerEmpleos.escribirEnlaceEmpleo(o,enlace);
                managerEmpleos.escribirActualizacionEmpleo(o,actualizacion);
                managerEmpleos.escribirCoordenadasEmpleo(o,coordenadas);
                managerEmpleos.escribirIdEmpleo(o,id);
                ofers.add(o);
            }
            cursor.close();


            for(int i=0;i<ofers.size();i++){
                Log.e(i+"Oferta",ofers.get(i).getTitulo());
            }
            return ofers;
        }
        else{
            ArrayList<OfertasFormacion>ofertasFormacion;
            crCursos=c.getContentResolver();
            Uri favoritosCursosUri=FavoritosCursosProvider.CONTENT_URI;
            Cursor cursorCursos=crCursos.query(favoritosCursosUri,null,"*",null,null);

            if(cursorCursos.getCount()==0){
                managerAvisos.avisosToast(c.getString(R.string.sinfavoritosformacion),c);
            }


            ofertasFormacion= new ArrayList<>();
            int m2=0;
            while(cursorCursos.moveToNext()) {
                m2=0;
                String titulo = cursorCursos.getString(
                        cursorCursos.getColumnIndexOrThrow(Estructura_Favoritos_Cursos.COLUMN_TITULO));
                String prov=cursorCursos.getString(
                        cursorCursos.getColumnIndexOrThrow(Estructura_Favoritos_Cursos.COLUMN_ID_PROVINCIA));
                String ciudad=cursorCursos.getString(
                        cursorCursos.getColumnIndexOrThrow(Estructura_Favoritos_Cursos.COLUMN_CIUDAD));
                String organismo=cursorCursos.getString(
                        cursorCursos.getColumnIndexOrThrow(Estructura_Favoritos_Cursos.COLUMN_ORGANISMO));
                String date=cursorCursos.getString(
                        cursorCursos.getColumnIndexOrThrow(Estructura_Favoritos_Cursos.COLUMN_FECHAS));
                String description=cursorCursos.getString(
                        cursorCursos.getColumnIndexOrThrow(Estructura_Favoritos_Cursos.COLUMN_DESCRIPCION));
                String enlace=cursorCursos.getString(
                        cursorCursos.getColumnIndexOrThrow(Estructura_Favoritos_Cursos.COLUMN_ENLACE));
                String actualizacion=cursorCursos.getString(
                        cursorCursos.getColumnIndexOrThrow(Estructura_Favoritos_Cursos.COLUMN_ACTUALIZACION));
                String coordenadas=cursorCursos.getString(
                        cursorCursos.getColumnIndexOrThrow(Estructura_Favoritos_Cursos.COLUMN_COORDENADAS));
                String id=cursorCursos.getString(
                        cursorCursos.getColumnIndexOrThrow(Estructura_Favoritos_Cursos.COLUMN_ID));
                String tipo=cursorCursos.getString(
                        cursorCursos.getColumnIndexOrThrow(Estructura_Favoritos_Cursos.COLUMN_TIPO));
                String duracion=cursorCursos.getString(
                        cursorCursos.getColumnIndexOrThrow(Estructura_Favoritos_Cursos.COLUMN_DURACION));
                String area=cursorCursos.getString(
                        cursorCursos.getColumnIndexOrThrow(Estructura_Favoritos_Cursos.COLUMN_MATERIA));
                String destinatarios=cursorCursos.getString(
                        cursorCursos.getColumnIndexOrThrow(Estructura_Favoritos_Cursos.COLUMN_DESTINATARIOS));
                String lugar_celebracion=cursorCursos.getString(
                        cursorCursos.getColumnIndexOrThrow(Estructura_Favoritos_Cursos.COLUMN_LUGAR));
                String inscripcion=cursorCursos.getString(
                        cursorCursos.getColumnIndexOrThrow(Estructura_Favoritos_Cursos.COLUMN_INSCRIPCION));
                String plazas=cursorCursos.getString(
                        cursorCursos.getColumnIndexOrThrow(Estructura_Favoritos_Cursos.COLUMN_PLAZAS));
                String infoAdicional=cursorCursos.getString(
                        cursorCursos.getColumnIndexOrThrow(Estructura_Favoritos_Cursos.COLUMN_INFOADICIONAL));



                OfertasFormacion o=new OfertasFormacion();
                managerEmpleos.escribirIdFormacion(o,id);
                managerEmpleos.escribirTituloFormacion(o,titulo);
                managerEmpleos.escribirCiudadFormacion(o,ciudad);
                managerEmpleos.escribirCoordenadasFormacion(o,coordenadas);
                managerEmpleos.escribirFechasFormacion(o,date);
                managerEmpleos.escribirOrganismoFormacion(o,organismo);
                managerEmpleos.escribirTipoFormacion(o,tipo);
                managerEmpleos.escribirDuracionFormacion(o,duracion);
                managerEmpleos.escribirDestinatariosFormacion(o,destinatarios);
                managerEmpleos.escribirDescripcionFormacion(o,description);
                managerEmpleos.escribirMateriaFormacion(o,area);
                managerEmpleos.escribirLugarFormacion(o,lugar_celebracion);
                managerEmpleos.escribirInscripcionFormacion(o,inscripcion);
                managerEmpleos.escribirCodProvinciaFormacion(o,prov);
                managerEmpleos.escribirPlazasFormacion(o,plazas);
                managerEmpleos.escribirInfoFormacion(o,infoAdicional);
                managerEmpleos.escribirActualizacionFormacion(o,actualizacion);
                managerEmpleos.escribirEnlaceFormacion(o,enlace);
                ofertasFormacion.add(o);
            }
            cursorCursos.close(); //***



            return ofertasFormacion;
        }
    }
    /**
     * Se comunica con el ContentProvider asociado a la tabla de ofertas de empleo para recuperar las ofertas de empleo que contiene que cumplan con la provincia y texto introducidos por el usuario como filtros en la búsqueda. En caso de existir coincidencias las devuelve en una lista, de lo contrario, devuelve null.
     * @param provincia_seleccionada provincia filtrada
     * @param busquedaLibre busqueda libre filtrada por el usuario
     * @param c contexto que desde el que se invoca al metodo
     * @return <ul>
     *          <li>true: listado de empleos recuperados/li>
     *          </ul>
     */
    public ArrayList buscarEmpleos(String provincia_seleccionada,String busquedaLibre,Context c){
        String[]selectionArgs;
        ArrayList<OfertasEmpleo>ofers;
        ContentResolver cr;
        ControladorPreferencias managerPreferencias=new ControladorPreferencias();
        ControladorOfertasEmpleo managerEmpleos=new ControladorOfertasEmpleo();
        if(busquedaLibre==null || busquedaLibre.isEmpty()){
            selectionArgs=null;
        }
        else{
            selectionArgs=new String[]{busquedaLibre};
        }

        if(provincia_seleccionada.equalsIgnoreCase(c.getString(R.string.provincia))){
            String temp;
            temp=managerPreferencias.leerPreferenciasDeConfiguracion(c,1);
            provincia_seleccionada=temp;
            if(provincia_seleccionada.equalsIgnoreCase(c.getString(R.string.todas))){
                temp="Provincia";
                provincia_seleccionada=temp;
            }

        }

        if(provincia_seleccionada.equalsIgnoreCase(c.getString(R.string.todas))){
            String temp="Provincia";
            provincia_seleccionada=temp;
        }




        cr = c.getContentResolver();
        Uri clientesUri =  ClientesProvider.CONTENT_URI;
        Cursor cursor=cr.query(clientesUri,
                null,
                provincia_seleccionada,
                selectionArgs,
                null);

        if(cursor==null){
            String s=c.getString(R.string.sinofertas);
            ControladorAvisos managerAvisos=new ControladorAvisos();
            managerAvisos.avisosToast(s,c);
            return null;
        }




        ofers= new ArrayList<>();
        int m=0;
        while(cursor.moveToNext()) {
            m=0;
            String titulo = cursor.getString(
                    cursor.getColumnIndexOrThrow(DbContract.COLUMN_TITULO));
            String prov=cursor.getString(
                    cursor.getColumnIndexOrThrow(DbContract.COLUMN_PROVINCIA));
            String ciudad=cursor.getString(
                    cursor.getColumnIndexOrThrow(DbContract.COLUMN_CIUDAD));
            String source=cursor.getString(
                    cursor.getColumnIndexOrThrow(DbContract.COLUMN_FUENTE));
            String date=cursor.getString(
                    cursor.getColumnIndexOrThrow(DbContract.COLUMN_FECHA));
            String description=cursor.getString(
                    cursor.getColumnIndexOrThrow(DbContract.COLUMN_DESCRIPCION));
            String enlace=cursor.getString(
                    cursor.getColumnIndexOrThrow(DbContract.COLUMN_ENLACE));
            String actualizacion=cursor.getString(
                    cursor.getColumnIndexOrThrow(DbContract.COLUMN_ACTUALIZACION));
            String coordenadas=cursor.getString(
                    cursor.getColumnIndexOrThrow(DbContract.COLUMN_COORDENADAS));
            String id=cursor.getString(
                    cursor.getColumnIndexOrThrow(DbContract.COLUMN_ID));

            OfertasEmpleo o=new OfertasEmpleo();
            managerEmpleos.escribirTituloEmpleo(o,titulo);
            managerEmpleos.escribirProvinciaEmpleo(o,prov);
            managerEmpleos.escribirCiudadEmpleo(o,ciudad);
            managerEmpleos.escribirFuenteEmpleo(o,source);
            managerEmpleos.escribirFechaPubEmpleo(o,date);
            managerEmpleos.escribirDescripcionEmpleo(o,description);
            managerEmpleos.escribirEnlaceEmpleo(o,enlace);
            managerEmpleos.escribirActualizacionEmpleo(o,actualizacion);
            managerEmpleos.escribirCoordenadasEmpleo(o,coordenadas);
            managerEmpleos.escribirIdEmpleo(o,id);
            ofers.add(o);
        }
        cursor.close();
        return ofers;
    }
    /**
     *Se comunica con el ContentProvider asociado a la tabla de ofertas de formación para recuperar las ofertas  que contiene que cumplan con la provincia,materia y texto introducidos por el usuario como filtros en la búsqueda. En caso de existir coincidencias las devuelve en una lista, de lo contrario, devuelve null.
     * @param provincia_seleccionada provincia filtrada
     * @param busquedaLibre busqueda libre filtrada por el usuario
     * @param c contexto que desde el que se invoca al metodo
     * @param materia_seleccionada ambito de la formacion filtrado
     * @return <ul>
     *          <li>true: listado de formaciones recuperadas/li>
     *          </ul>
     */
    public ArrayList buscarFormaciones(String provincia_seleccionada,String busquedaLibre,String materia_seleccionada,Context c){
        ArrayList<OfertasFormacion>ofers;
        ContentResolver cr;
        String[] selectionArgs;
        ControladorPreferencias managerPreferencias=new ControladorPreferencias();
        ControladorOfertasEmpleo managerEmpleos=new ControladorOfertasEmpleo();
        if(provincia_seleccionada.equalsIgnoreCase(c.getString(R.string.provincia))){
            String temp;
            temp=managerPreferencias.leerPreferenciasDeConfiguracion(c,1);
            provincia_seleccionada=temp;
            if(provincia_seleccionada.equalsIgnoreCase(c.getString(R.string.todas))){
                temp="Provincia";
                provincia_seleccionada=temp;
            }
        }

        if(provincia_seleccionada.equalsIgnoreCase(c.getString(R.string.todas))){
            String temp="Provincia";
            provincia_seleccionada=temp;
        }

        if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.todas))){
            if(busquedaLibre==null || busquedaLibre.equalsIgnoreCase("") || busquedaLibre.isEmpty()){
                Log.e("LA FAMILIA 1",materia_seleccionada);
                selectionArgs=new String[]{"*"};
            }
            else{
                selectionArgs=new String[]{"*",busquedaLibre};
            }
        }
        else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.familias))){
            String temp2;
            temp2=managerPreferencias.leerPreferenciasDeConfiguracion(c,2);
            materia_seleccionada=temp2;
            Log.e("LA FAMILIA 2",materia_seleccionada);
            if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.todas)) || materia_seleccionada.equalsIgnoreCase(c.getString(R.string.familias))){
                materia_seleccionada="*";
            }
            if( busquedaLibre==null || busquedaLibre.equalsIgnoreCase("") || busquedaLibre.isEmpty()){
                selectionArgs=new String[]{materia_seleccionada};
            }
            else{
                if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.fisicasydeportivas))){
                    String temp="Actividades Físicas y Deportivas";
                    materia_seleccionada=temp;
                }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.administraciongestion))){
                    String temp="Administración y Gestión";
                    materia_seleccionada=temp;
                }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.agraria))){
                    String temp="Agraria";
                    materia_seleccionada=temp;
                }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.artesgraficas))){
                    String temp="Artes Gráficas";
                    materia_seleccionada=temp;
                }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.artesartesanias))){
                    String temp="Artes y artesanías";
                    materia_seleccionada=temp;
                }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.comerciomarketing))){
                    String temp="Comercio y Marketing";
                    materia_seleccionada=temp;
                }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.edificacionobracivil))){
                    String temp="Edificación y Obra Civil";
                    materia_seleccionada=temp;
                }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.energiaagua))){
                    String temp="Energía y Agua";
                    materia_seleccionada=temp;
                }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.electricidadelectronica))){
                    String temp="Electricidad y Electrónica";
                    materia_seleccionada=temp;
                }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.fabricacionmecanica))){
                    String temp="Fabricación Mecánica";
                    materia_seleccionada=temp;
                }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.formacioncomplementaria))){
                    String temp="Formación Complementaria";
                    materia_seleccionada=temp;
                }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.hosteleriaturismo))){
                    String temp="Hostelería y Turismo";
                    materia_seleccionada=temp;
                }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.imagenpersonal))){
                    String temp="Imagen Personal";
                    materia_seleccionada=temp;
                }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.imagensonido))){
                    String temp="Imagen y Sonido";
                    materia_seleccionada=temp;
                }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.insdustriasalimentarias))){
                    String temp="Industrias Alimentarias";
                    materia_seleccionada=temp;
                }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.insdustriasextractivas))){
                    String temp="Industrias Extractivas";
                    materia_seleccionada=temp;
                }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.informaticacaomunicaciones))){
                    String temp="Informática y Comunicaciones";
                    materia_seleccionada=temp;
                }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.instalacionmantenimiento))){
                    String temp="Instalación y Mantenimiento";
                    materia_seleccionada=temp;
                }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.maderamueblecorcho))){
                    String temp="Madera, Mueble y Corcho";
                    materia_seleccionada=temp;
                }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.maritimopesquera))){
                    String temp="Marítimo Pesquera";
                    materia_seleccionada=temp;
                }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.quimica))){
                    String temp="Química";
                    materia_seleccionada=temp;
                }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.sanidad))){
                    String temp="Sanidad";
                    materia_seleccionada=temp;
                }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.seguridadmedioambiente))){
                    String temp="Seguridad y Medioambiente";
                    materia_seleccionada=temp;
                }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.serviciossocioculturales))){
                    String temp="Servicios Socioculturales y a la Comunidad";
                    materia_seleccionada=temp;
                }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.textil))){
                    String temp="Textil, Confección y Piel";
                    materia_seleccionada=temp;
                }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.transporte))){
                    String temp="Transporte y mantenimiento de vehículos";
                    materia_seleccionada=temp;
                }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.vidrio))){
                    String temp="Vidrio y Cerámica";
                    materia_seleccionada=temp;
                }
                selectionArgs=new String[]{materia_seleccionada,busquedaLibre};
            }

        }else{
            Log.e("LA FAMILIA 3",materia_seleccionada);
            if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.fisicasydeportivas))){
                String temp="Actividades Físicas y Deportivas";
                materia_seleccionada=temp;
            }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.administraciongestion))){
                String temp="Administración y Gestión";
                materia_seleccionada=temp;
            }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.agraria))){
                String temp="Agraria";
                materia_seleccionada=temp;
            }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.artesgraficas))){
                String temp="Artes Gráficas";
                materia_seleccionada=temp;
            }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.artesartesanias))){
                String temp="Artes y artesanías";
                materia_seleccionada=temp;
            }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.comerciomarketing))){
                String temp="Comercio y Marketing";
                materia_seleccionada=temp;
            }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.edificacionobracivil))){
                String temp="Edificación y Obra Civil";
                materia_seleccionada=temp;
            }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.energiaagua))){
                String temp="Energía y Agua";
                materia_seleccionada=temp;
            }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.electricidadelectronica))){
                String temp="Electricidad y Electrónica";
                materia_seleccionada=temp;
            }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.fabricacionmecanica))){
                String temp="Fabricación Mecánica";
                materia_seleccionada=temp;
            }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.formacioncomplementaria))){
                String temp="Formación Complementaria";
                materia_seleccionada=temp;
            }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.hosteleriaturismo))){
                String temp="Hostelería y Turismo";
                materia_seleccionada=temp;
            }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.imagenpersonal))){
                String temp="Imagen Personal";
                materia_seleccionada=temp;
            }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.imagensonido))){
                String temp="Imagen y Sonido";
                materia_seleccionada=temp;
            }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.insdustriasalimentarias))){
                String temp="Industrias Alimentarias";
                materia_seleccionada=temp;
            }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.insdustriasextractivas))){
                String temp="Industrias Extractivas";
                materia_seleccionada=temp;
            }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.informaticacaomunicaciones))){
                String temp="Informática y Comunicaciones";
                materia_seleccionada=temp;
            }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.instalacionmantenimiento))){
                String temp="Instalación y Mantenimiento";
                materia_seleccionada=temp;
            }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.maderamueblecorcho))){
                String temp="Madera, Mueble y Corcho";
                materia_seleccionada=temp;
            }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.maritimopesquera))){
                String temp="Marítimo Pesquera";
                materia_seleccionada=temp;
            }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.quimica))){
                String temp="Química";
                materia_seleccionada=temp;
            }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.sanidad))){
                String temp="Sanidad";
                materia_seleccionada=temp;
            }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.seguridadmedioambiente))){
                String temp="Seguridad y Medioambiente";
                materia_seleccionada=temp;
            }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.serviciossocioculturales))){
                String temp="Servicios Socioculturales y a la Comunidad";
                materia_seleccionada=temp;
            }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.textil))){
                String temp="Textil, Confección y Piel";
                materia_seleccionada=temp;
            }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.transporte))){
                String temp="Transporte y mantenimiento de vehículos";
                materia_seleccionada=temp;
            }else if(materia_seleccionada.equalsIgnoreCase(c.getString(R.string.vidrio))){
                String temp="Vidrio y Cerámica";
                materia_seleccionada=temp;
            }
            if(busquedaLibre==null || busquedaLibre.equalsIgnoreCase("") ||  busquedaLibre.isEmpty()){
                selectionArgs=new String[]{materia_seleccionada};
            }
            else{
                selectionArgs=new String[]{materia_seleccionada,busquedaLibre};
            }
        }




        cr = c.getContentResolver();
        Uri formacionUri =  FormacionProvider.CONTENT_URI;
        Cursor cursor=cr.query(formacionUri,
                null,
                provincia_seleccionada,
                selectionArgs,
                null);

        ControladorAvisos managerAvisos=new ControladorAvisos();

        if(cursor==null){
            String s=c.getString(R.string.sinofertas);
            managerAvisos.avisosToast(s,c);
            return null;
        }

        if(cursor.getCount()==0){
            String s=c.getString(R.string.sinofertas);
            managerAvisos.avisosToast(s,c);
            return null;
        }





        ofers= new ArrayList<>();
        int m=0;
        while(cursor.moveToNext()) {
            m=0;
            String titulo = cursor.getString(
                    cursor.getColumnIndexOrThrow(Estructura_Formacion.COLUMN_TITULO));
            String prov=cursor.getString(
                    cursor.getColumnIndexOrThrow(Estructura_Formacion.COLUMN_ID_PROVINCIA));
            String ciudad=cursor.getString(
                    cursor.getColumnIndexOrThrow(Estructura_Formacion.COLUMN_CIUDAD));
            String organismo=cursor.getString(
                    cursor.getColumnIndexOrThrow(Estructura_Formacion.COLUMN_ORGANISMO));
            String date=cursor.getString(
                    cursor.getColumnIndexOrThrow(Estructura_Formacion.COLUMN_FECHAS));
            String description=cursor.getString(
                    cursor.getColumnIndexOrThrow(Estructura_Formacion.COLUMN_DESCRIPCION));
            String enlace=cursor.getString(
                    cursor.getColumnIndexOrThrow(Estructura_Formacion.COLUMN_ENLACE));
            String actualizacion=cursor.getString(
                    cursor.getColumnIndexOrThrow(Estructura_Formacion.COLUMN_ACTUALIZACION));
            String coordenadas=cursor.getString(
                    cursor.getColumnIndexOrThrow(Estructura_Formacion.COLUMN_COORDENADAS));
            String id=cursor.getString(
                    cursor.getColumnIndexOrThrow(Estructura_Formacion.COLUMN_ID));
            String tipo=cursor.getString(
                    cursor.getColumnIndexOrThrow(Estructura_Formacion.COLUMN_TIPO));
            String duracion=cursor.getString(
                    cursor.getColumnIndexOrThrow(Estructura_Formacion.COLUMN_DURACION));
            String area=cursor.getString(
                    cursor.getColumnIndexOrThrow(Estructura_Formacion.COLUMN_MATERIA));
            String destinatarios=cursor.getString(
                    cursor.getColumnIndexOrThrow(Estructura_Formacion.COLUMN_DESTINATARIOS));
            String lugar_celebracion=cursor.getString(
                    cursor.getColumnIndexOrThrow(Estructura_Formacion.COLUMN_LUGAR));
            String inscripcion=cursor.getString(
                    cursor.getColumnIndexOrThrow(Estructura_Formacion.COLUMN_INSCRIPCION));
            String plazas=cursor.getString(
                    cursor.getColumnIndexOrThrow(Estructura_Formacion.COLUMN_PLAZAS));
            String infoAdicional=cursor.getString(
                    cursor.getColumnIndexOrThrow(Estructura_Formacion.COLUMN_INFOADICIONAL));

            OfertasFormacion o=new OfertasFormacion();
            managerEmpleos.escribirTituloFormacion(o,titulo);
            managerEmpleos.escribirIdFormacion(o,id);
            managerEmpleos.escribirTipoFormacion(o,titulo);
            managerEmpleos.escribirCiudadFormacion(o,ciudad);
            managerEmpleos.escribirCoordenadasFormacion(o,coordenadas);
            managerEmpleos.escribirFechasFormacion(o,date);
            managerEmpleos.escribirOrganismoFormacion(o,organismo);
            managerEmpleos.escribirTipoFormacion(o,tipo);
            managerEmpleos.escribirDuracionFormacion(o,duracion);
            managerEmpleos.escribirDestinatariosFormacion(o,destinatarios);
            managerEmpleos.escribirDescripcionFormacion(o,description);
            managerEmpleos.escribirMateriaFormacion(o,area);
            managerEmpleos.escribirLugarFormacion(o,lugar_celebracion);
            managerEmpleos.escribirInscripcionFormacion(o,inscripcion);
            managerEmpleos.escribirCodProvinciaFormacion(o,prov);
            managerEmpleos.escribirPlazasFormacion(o,plazas);
            managerEmpleos.escribirInfoFormacion(o,infoAdicional);
            managerEmpleos.escribirActualizacionFormacion(o,actualizacion);
            managerEmpleos.escribirEnlaceFormacion(o,enlace);
            ofers.add(o);
        }
        cursor.close();
        return ofers;
    }
}
