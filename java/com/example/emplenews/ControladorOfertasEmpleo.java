package com.example.emplenews;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.widget.Spinner;

import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
/**
 * Clase ControladorOfertasEmpleo
 *
 * Controlador encargado de gestionar la informacion relativa a las ofertas de empleo y formacion
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class ControladorOfertasEmpleo extends Utils {
    public ControladorOfertasEmpleo(){}
    /**
     * Comunicándose con el ContentProvider asociado a las ofertas de empleo, selecciona aquellas que cumplan con la provincia y búsqueda libre que introduzca el usuario y las devuelve en una lista.
     * @param provinciaSeleccionada provincia filtrada
     * @param busquedaUsuario busqueda libre del usuario filtrada
     * @param c contexto desde el que se invoca al controlador
     * @return <ul>
     *          <li>ArrayList: lista de ofertas recuperadas coincidentes</li>
     *          </ul>
     */
    public ArrayList hacerBusquedaOfertas(String provinciaSeleccionada,String busquedaUsuario,Context c,int cuantosRegistros){
        String provincia, fuente;
        provincia=provinciaSeleccionada;
        String[] selectionArgs;
        ContentResolver cr;
        ArrayList<OfertasEmpleo>ofers;
        ControladorPreferencias managerPreferencias=new ControladorPreferencias();

        if(busquedaUsuario.isEmpty()){
            selectionArgs=null;
        }
        else{
            selectionArgs=new String[]{busquedaUsuario};
        }

        if(provincia.equalsIgnoreCase(c.getString(R.string.provincia))){

            String provin;

            provin=managerPreferencias.leerPreferenciasDeConfiguracion(c,1);
            Log.e("PROVIN", provin);

            if(provin.equalsIgnoreCase(c.getString(R.string.todas))){
                String temp="provincia";
                provin=temp;
                Log.e("PROVIN", temp);
            }
            if(provin.equalsIgnoreCase(c.getString(R.string.provincia))){
                String temp="provincia";
                provin=temp;
                Log.e("PROVIN", temp);
            }
            Uri clientesUri =  ClientesProvider.CONTENT_URI;
            cr=c.getContentResolver();
            Cursor cursor=cr.query(clientesUri,
                    null,
                    provin,
                    selectionArgs,
                    null);




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
                o.setTitulo(titulo);
                o.setProvincia(prov);
                o.setCiudad(ciudad);
                o.setFuente(source);
                o.setFecha_publicacion(date);
                o.setDescripcion(description);
                o.setEnlace(enlace);
                o.setActualizacion(actualizacion);
                o.setCoordenadas(coordenadas);
                o.setId(id);
                ofers.add(o);
            }
            cursor.close();

            cuantosRegistros=cursor.getCount();
            return ofers;
        }

        else if(provincia.equalsIgnoreCase("Salamanca")){

            Uri clientesUri =  ClientesProvider.CONTENT_URI;
            cr=c.getContentResolver();
            Cursor cursor=cr.query(clientesUri,
                    null,
                    "Salamanca",
                    selectionArgs,
                    null);

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
                o.setTitulo(titulo);
                o.setProvincia(prov);
                o.setCiudad(ciudad);
                o.setFuente(source);
                o.setFecha_publicacion(date);
                o.setDescripcion(description);
                o.setEnlace(enlace);
                o.setActualizacion(actualizacion);
                o.setCoordenadas(coordenadas);
                o.setId(id);
                ofers.add(o);
            }
            cursor.close();

            for(int i=0;i<ofers.size();i++){
                Log.e(i+"Oferta", " "+ofers.get(i).getProvincia());
            }

            cuantosRegistros=cursor.getCount();
            return ofers;
        }
        else if(provincia.equalsIgnoreCase("Segovia")){
            Uri clientesUri =  ClientesProvider.CONTENT_URI;
            cr=c.getContentResolver();
            Cursor cursor=cr.query(clientesUri,
                    null,
                    "Segovia",
                    selectionArgs,
                    null);

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
                o.setTitulo(titulo);
                o.setProvincia(prov);
                o.setCiudad(ciudad);
                o.setFuente(source);
                o.setFecha_publicacion(date);
                o.setDescripcion(description);
                o.setEnlace(enlace);
                o.setActualizacion(actualizacion);
                o.setCoordenadas(coordenadas);
                o.setId(id);
                ofers.add(o);
            }
            cursor.close();

            for(int i=0;i<ofers.size();i++){
                Log.e(i+"Oferta", " "+ofers.get(i).getProvincia());
            }

            cuantosRegistros=cursor.getCount();
            return ofers;
        }
        else if(provincia.equalsIgnoreCase("Soria")){

            Uri clientesUri =  ClientesProvider.CONTENT_URI;
            cr=c.getContentResolver();
            Cursor cursor=cr.query(clientesUri,
                    null,
                    "Soria",
                    selectionArgs,
                    null);

            ofers= new ArrayList<>();
            int m=0;

            if(cursor==null){
                return null;
            }

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
                o.setTitulo(titulo);
                o.setProvincia(prov);
                o.setCiudad(ciudad);
                o.setFuente(source);
                o.setFecha_publicacion(date);
                o.setDescripcion(description);
                o.setEnlace(enlace);
                o.setActualizacion(actualizacion);
                o.setCoordenadas(coordenadas);
                o.setId(id);
                ofers.add(o);
            }
            cursor.close();
            cuantosRegistros=cursor.getCount();
            return ofers;

        }
        else if(provincia.equalsIgnoreCase("Valladolid")){

            Uri clientesUri =  ClientesProvider.CONTENT_URI;
            cr=c.getContentResolver();
            Cursor cursor=cr.query(clientesUri,
                    null,
                    "Valladolid",
                    selectionArgs,
                    null);



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
                o.setTitulo(titulo);
                o.setProvincia(prov);
                o.setCiudad(ciudad);
                o.setFuente(source);
                o.setFecha_publicacion(date);
                o.setDescripcion(description);
                o.setEnlace(enlace);
                o.setActualizacion(actualizacion);
                o.setCoordenadas(coordenadas);
                o.setId(id);
                ofers.add(o);
            }
            cursor.close();


            for(int i=0;i<ofers.size();i++){
                Log.e(i+"Oferta", " "+ofers.get(i).getProvincia());
            }

            cuantosRegistros=cursor.getCount();
            return ofers;
        }
        else if(provincia.equalsIgnoreCase("León")){

            Uri clientesUri =  ClientesProvider.CONTENT_URI;
            cr=c.getContentResolver();
            Cursor cursor=cr.query(clientesUri,
                    null,
                    "León",
                    selectionArgs,
                    null);



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
                o.setTitulo(titulo);
                o.setProvincia(prov);
                o.setCiudad(ciudad);
                o.setFuente(source);
                o.setFecha_publicacion(date);
                o.setDescripcion(description);
                o.setEnlace(enlace);
                o.setActualizacion(actualizacion);
                o.setCoordenadas(coordenadas);
                o.setId(id);
                ofers.add(o);
            }
            cursor.close();


            for(int i=0;i<ofers.size();i++){
                Log.e(i+"Oferta", " "+ofers.get(i).getProvincia());
            }

            cuantosRegistros=cursor.getCount();
            return ofers;
        }
        else if(provincia.equalsIgnoreCase("Zamora")){

            Uri clientesUri =  ClientesProvider.CONTENT_URI;
            cr=c.getContentResolver();
            Cursor cursor=cr.query(clientesUri,
                    null,
                    "Zamora",
                    selectionArgs,
                    null);



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
                o.setTitulo(titulo);
                o.setProvincia(prov);
                o.setCiudad(ciudad);
                o.setFuente(source);
                o.setFecha_publicacion(date);
                o.setDescripcion(description);
                o.setEnlace(enlace);
                o.setActualizacion(actualizacion);
                o.setCoordenadas(coordenadas);
                o.setId(id);
                ofers.add(o);
            }
            cursor.close();


            for(int i=0;i<ofers.size();i++){
                Log.e(i+"Oferta", " "+ofers.get(i).getProvincia());
            }

            cuantosRegistros=cursor.getCount();
            return ofers;
        }
        else if(provincia.equalsIgnoreCase("Palencia")){

            Uri clientesUri =  ClientesProvider.CONTENT_URI;
            cr=c.getContentResolver();
            Cursor cursor=cr.query(clientesUri,
                    null,
                    "Palencia",
                    selectionArgs,
                    null);



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
                o.setTitulo(titulo);
                o.setProvincia(prov);
                o.setCiudad(ciudad);
                o.setFuente(source);
                o.setFecha_publicacion(date);
                o.setDescripcion(description);
                o.setEnlace(enlace);
                o.setActualizacion(actualizacion);
                o.setCoordenadas(coordenadas);
                o.setId(id);
                ofers.add(o);
            }
            cursor.close();


            for(int i=0;i<ofers.size();i++){
                Log.e(i+"Oferta", " "+ofers.get(i).getProvincia());
            }
            cuantosRegistros=cursor.getCount();
            return ofers;
        }
        else if(provincia.equalsIgnoreCase("Ávila")){

            Uri clientesUri =  ClientesProvider.CONTENT_URI;
            cr=c.getContentResolver();
            Cursor cursor=cr.query(clientesUri,
                    null,
                    "Ávila",
                    selectionArgs,
                    null);



            ofers= new ArrayList<>();
            int m=0;

            if(cursor==null){
                return null;
            }

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
                o.setTitulo(titulo);
                o.setProvincia(prov);
                o.setCiudad(ciudad);
                o.setFuente(source);
                o.setFecha_publicacion(date);
                o.setDescripcion(description);
                o.setEnlace(enlace);
                o.setActualizacion(actualizacion);
                o.setCoordenadas(coordenadas);
                o.setId(id);
                ofers.add(o);
            }
            cursor.close();


            for(int i=0;i<ofers.size();i++){
                Log.e(i+"Oferta", " "+ofers.get(i).getProvincia());
            }

            cuantosRegistros=cursor.getCount();
            return ofers;
        }
        else if(provincia.equalsIgnoreCase("Burgos")){

            Uri clientesUri =  ClientesProvider.CONTENT_URI;
            cr=c.getContentResolver();
            Cursor cursor=cr.query(clientesUri,
                    null,
                    "Burgos",
                    selectionArgs,
                    null);
            ofers= new ArrayList<>();
            int m=0;

            if(cursor==null){
                return null;
            }

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
                o.setTitulo(titulo);
                o.setProvincia(prov);
                o.setCiudad(ciudad);
                o.setFuente(source);
                o.setFecha_publicacion(date);
                o.setDescripcion(description);
                o.setEnlace(enlace);
                o.setActualizacion(actualizacion);
                o.setCoordenadas(coordenadas);
                o.setId(id);
                ofers.add(o);
            }
            cursor.close();

            for(int i=0;i<ofers.size();i++){
                Log.e(i+"Oferta", " "+ofers.get(i).getProvincia());
            }

            cuantosRegistros=cursor.getCount();
            return ofers;
        }
        else{

            Uri clientesUri =  ClientesProvider.CONTENT_URI;
            cr=c.getContentResolver();
            Cursor cursor=cr.query(clientesUri,
                    null,
                    "provincia",
                    selectionArgs,
                    null);




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
                o.setTitulo(titulo);
                o.setProvincia(prov);
                o.setCiudad(ciudad);
                o.setFuente(source);
                o.setFecha_publicacion(date);
                o.setDescripcion(description);
                o.setEnlace(enlace);
                o.setActualizacion(actualizacion);
                o.setCoordenadas(coordenadas);
                o.setId(id);
                ofers.add(o);
            }
            cursor.close();

            cuantosRegistros=cursor.getCount();
            return ofers;
        }
    }
    /**
     * Comunicándose con el ContentProvider asociado a las ofertas de formación, selecciona aquellas que cumplan con la provincia,materia y búsqueda libre que introduzca el usuario y las devuelve en una lista.
     * @param provinciaSeleccionada provincia filtrada
     * @param busquedaUsuario busqueda libre del usuario filtrada
     * @param c contexto desde el que se invoca al controlador
     * @param  materiaSeleccionada ambito de formacion filtrado
     * @return <ul>
     *          <li>ArrayList: lista de ofertas recuperadas coincidentes</li>
     *          </ul>
     */
    public ArrayList hacerBusquedaFormaciones(Spinner provinciaSeleccionada,Spinner materiaSeleccionada,Context c,String busquedaUsuario){
        String provincia, materia;
        String[] selectionArgs;
        provincia=provinciaSeleccionada.getSelectedItem().toString();
        materia=materiaSeleccionada.getSelectedItem().toString();
        ControladorPreferencias managerPreferencias=new ControladorPreferencias();


        if(materia.equalsIgnoreCase(c.getString(R.string.todas))){
            if(busquedaUsuario.isEmpty()){
                selectionArgs=new String[]{"*"};
            }
            else{
                selectionArgs=new String[]{"*",busquedaUsuario};
            }

        }
        else if(materia.equalsIgnoreCase(c.getString(R.string.familias))){
            String family;
            family=managerPreferencias.leerPreferenciasDeConfiguracion(c,2);
            if(family.equalsIgnoreCase(c.getString(R.string.todas))){
                family="*";
            }
            Log.e("FAMILY", family);
            if(family.equalsIgnoreCase(c.getString(R.string.familias))){
                if(busquedaUsuario.isEmpty()){
                    selectionArgs=new String[]{"*"};
                }
                else{
                    selectionArgs=new String[]{"*",busquedaUsuario};
                }
            }
            else{
                if(family.equalsIgnoreCase(c.getString(R.string.fisicasydeportivas))){
                    String temp="Actividades Físicas y Deportivas";
                    family=temp;
                }else if(family.equalsIgnoreCase(c.getString(R.string.administraciongestion))){
                    String temp="Administración y Gestión";
                    family=temp;
                }else if(family.equalsIgnoreCase(c.getString(R.string.agraria))){
                    String temp="Agraria";
                    family=temp;
                }else if(family.equalsIgnoreCase(c.getString(R.string.artesgraficas))){
                    String temp="Artes Gráficas";
                    family=temp;
                }else if(family.equalsIgnoreCase(c.getString(R.string.artesartesanias))){
                    String temp="Artes y artesanías";
                    family=temp;
                }else if(family.equalsIgnoreCase(c.getString(R.string.comerciomarketing))){
                    String temp="Comercio y Marketing";
                    family=temp;
                }else if(family.equalsIgnoreCase(c.getString(R.string.edificacionobracivil))){
                    String temp="Edificación y Obra Civil";
                    family=temp;
                }else if(family.equalsIgnoreCase(c.getString(R.string.energiaagua))){
                    String temp="Energía y Agua";
                    family=temp;
                }else if(family.equalsIgnoreCase(c.getString(R.string.electricidadelectronica))){
                    String temp="Electricidad y Electrónica";
                    family=temp;
                }else if(family.equalsIgnoreCase(c.getString(R.string.fabricacionmecanica))){
                    String temp="Fabricación Mecánica";
                    family=temp;
                }else if(family.equalsIgnoreCase(c.getString(R.string.formacioncomplementaria))){
                    String temp="Formación Complementaria";
                    family=temp;
                }else if(family.equalsIgnoreCase(c.getString(R.string.hosteleriaturismo))){
                    String temp="Hostelería y Turismo";
                    family=temp;
                }else if(family.equalsIgnoreCase(c.getString(R.string.imagenpersonal))){
                    String temp="Imagen Personal";
                    family=temp;
                }else if(family.equalsIgnoreCase(c.getString(R.string.imagensonido))){
                    String temp="Imagen y Sonido";
                    family=temp;
                }else if(family.equalsIgnoreCase(c.getString(R.string.insdustriasalimentarias))){
                    String temp="Industrias Alimentarias";
                    family=temp;
                }else if(family.equalsIgnoreCase(c.getString(R.string.insdustriasextractivas))){
                    String temp="Industrias Extractivas";
                    family=temp;
                }else if(family.equalsIgnoreCase(c.getString(R.string.informaticacaomunicaciones))){
                    String temp="Informática y Comunicaciones";
                    family=temp;
                }else if(family.equalsIgnoreCase(c.getString(R.string.instalacionmantenimiento))){
                    String temp="Instalación y Mantenimiento";
                    family=temp;
                }else if(family.equalsIgnoreCase(c.getString(R.string.maderamueblecorcho))){
                    String temp="Madera, Mueble y Corcho";
                    family=temp;
                }else if(family.equalsIgnoreCase(c.getString(R.string.maritimopesquera))){
                    String temp="Marítimo Pesquera";
                    family=temp;
                }else if(family.equalsIgnoreCase(c.getString(R.string.quimica))){
                    String temp="Química";
                    family=temp;
                }else if(family.equalsIgnoreCase(c.getString(R.string.sanidad))){
                    String temp="Sanidad";
                    family=temp;
                }else if(family.equalsIgnoreCase(c.getString(R.string.seguridadmedioambiente))){
                    String temp="Seguridad y Medioambiente";
                    family=temp;
                }else if(family.equalsIgnoreCase(c.getString(R.string.serviciossocioculturales))){
                    String temp="Servicios Socioculturales y a la Comunidad";
                    family=temp;
                }else if(family.equalsIgnoreCase(c.getString(R.string.textil))){
                    String temp="Textil, Confección y Piel";
                    family=temp;
                }else if(family.equalsIgnoreCase(c.getString(R.string.transporte))){
                    String temp="Transporte y mantenimiento de vehículos";
                    family=temp;
                }else if(family.equalsIgnoreCase(c.getString(R.string.vidrio))){
                    String temp="Vidrio y Cerámica";
                    family=temp;
                }
                if(busquedaUsuario.isEmpty()){
                    selectionArgs=new String[]{family};
                }
                else{
                    selectionArgs=new String[]{family,busquedaUsuario};
                }
            }


        }else{
            if(materia.equalsIgnoreCase(c.getString(R.string.fisicasydeportivas))){
                String temp="Actividades Físicas y Deportivas";
                materia=temp;
            }else if(materia.equalsIgnoreCase(c.getString(R.string.administraciongestion))){
                String temp="Administración y Gestión";
                materia=temp;
            }else if(materia.equalsIgnoreCase(c.getString(R.string.agraria))){
                String temp="Agraria";
                materia=temp;
            }else if(materia.equalsIgnoreCase(c.getString(R.string.artesgraficas))){
                String temp="Artes Gráficas";
                materia=temp;
            }else if(materia.equalsIgnoreCase(c.getString(R.string.artesartesanias))){
                String temp="Artes y artesanías";
                materia=temp;
            }else if(materia.equalsIgnoreCase(c.getString(R.string.comerciomarketing))){
                String temp="Comercio y Marketing";
                materia=temp;
            }else if(materia.equalsIgnoreCase(c.getString(R.string.edificacionobracivil))){
                String temp="Edificación y Obra Civil";
                materia=temp;
            }else if(materia.equalsIgnoreCase(c.getString(R.string.energiaagua))){
                String temp="Energía y Agua";
                materia=temp;
            }else if(materia.equalsIgnoreCase(c.getString(R.string.electricidadelectronica))){
                String temp="Electricidad y Electrónica";
                materia=temp;
            }else if(materia.equalsIgnoreCase(c.getString(R.string.fabricacionmecanica))){
                String temp="Fabricación Mecánica";
                materia=temp;
            }else if(materia.equalsIgnoreCase(c.getString(R.string.formacioncomplementaria))){
                String temp="Formación Complementaria";
                materia=temp;
            }else if(materia.equalsIgnoreCase(c.getString(R.string.hosteleriaturismo))){
                String temp="Hostelería y Turismo";
                materia=temp;
            }else if(materia.equalsIgnoreCase(c.getString(R.string.imagenpersonal))){
                String temp="Imagen Personal";
                materia=temp;
            }else if(materia.equalsIgnoreCase(c.getString(R.string.imagensonido))){
                String temp="Imagen y Sonido";
                materia=temp;
            }else if(materia.equalsIgnoreCase(c.getString(R.string.insdustriasalimentarias))){
                String temp="Industrias Alimentarias";
                materia=temp;
            }else if(materia.equalsIgnoreCase(c.getString(R.string.insdustriasextractivas))){
                String temp="Industrias Extractivas";
                materia=temp;
            }else if(materia.equalsIgnoreCase(c.getString(R.string.informaticacaomunicaciones))){
                String temp="Informática y Comunicaciones";
                materia=temp;
            }else if(materia.equalsIgnoreCase(c.getString(R.string.instalacionmantenimiento))){
                String temp="Instalación y Mantenimiento";
                materia=temp;
            }else if(materia.equalsIgnoreCase(c.getString(R.string.maderamueblecorcho))){
                String temp="Madera, Mueble y Corcho";
                materia=temp;
            }else if(materia.equalsIgnoreCase(c.getString(R.string.maritimopesquera))){
                String temp="Marítimo Pesquera";
                materia=temp;
            }else if(materia.equalsIgnoreCase(c.getString(R.string.quimica))){
                String temp="Química";
                materia=temp;
            }else if(materia.equalsIgnoreCase(c.getString(R.string.sanidad))){
                String temp="Sanidad";
                materia=temp;
            }else if(materia.equalsIgnoreCase(c.getString(R.string.seguridadmedioambiente))){
                String temp="Seguridad y Medioambiente";
                materia=temp;
            }else if(materia.equalsIgnoreCase(c.getString(R.string.serviciossocioculturales))){
                String temp="Servicios Socioculturales y a la Comunidad";
                materia=temp;
            }else if(materia.equalsIgnoreCase(c.getString(R.string.textil))){
                String temp="Textil, Confección y Piel";
                materia=temp;
            }else if(materia.equalsIgnoreCase(c.getString(R.string.transporte))){
                String temp="Transporte y mantenimiento de vehículos";
                materia=temp;
            }else if(materia.equalsIgnoreCase(c.getString(R.string.vidrio))){
                String temp="Vidrio y Cerámica";
                materia=temp;
            }
            if(busquedaUsuario.isEmpty()){
                selectionArgs=new String[]{materia};
            }
            else{
                selectionArgs=new String[]{materia,busquedaUsuario};
            }
        }




        ContentResolver cr;
        ArrayList<OfertasFormacion>ofers;


        if(provincia.equalsIgnoreCase(c.getString(R.string.todas))){
            Uri formacionUri =  FormacionProvider.CONTENT_URI;
            cr=c.getContentResolver();
            Cursor cursor=cr.query(formacionUri,
                    null,
                    "*",
                    selectionArgs,
                    null);




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
                o.setId(id);
                o.setTitulo(titulo);
                o.setCiudad(ciudad);
                o.setCoordenadas(coordenadas);
                o.setFechas(date);
                o.setOrganismo(organismo);
                o.setTipo(tipo);
                o.setDuracion(duracion);
                o.setDestinatarios(destinatarios);
                o.setDescripcion(description);
                o.setMateria(area);
                o.setLugar(lugar_celebracion);
                o.setInscripcion(inscripcion);
                o.setCod_provincia(prov);
                o.setPlazas(plazas);
                o.setInfoAdicional(infoAdicional);
                o.setActualizacion(actualizacion);
                o.setEnlace(enlace);
                ofers.add(o);
            }
            return ofers;
        }

        else if(provincia.equalsIgnoreCase("Salamanca")){

            Uri formacionUri =  FormacionProvider.CONTENT_URI;
            cr=c.getContentResolver();
            Cursor cursor=cr.query(formacionUri,
                    null,
                    "Salamanca",
                    selectionArgs,
                    null);



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
                o.setId(id);
                o.setTitulo(titulo);
                o.setCiudad(ciudad);
                o.setCoordenadas(coordenadas);
                o.setFechas(date);
                o.setOrganismo(organismo);
                o.setTipo(tipo);
                o.setDuracion(duracion);
                o.setDestinatarios(destinatarios);
                o.setDescripcion(description);
                o.setMateria(area);
                o.setLugar(lugar_celebracion);
                o.setInscripcion(inscripcion);
                o.setCod_provincia(prov);
                o.setPlazas(plazas);
                o.setInfoAdicional(infoAdicional);
                o.setActualizacion(actualizacion);
                o.setEnlace(enlace);
                ofers.add(o);
            }
            //cursor.close();

            return ofers;

        }
        else if(provincia.equalsIgnoreCase("Segovia")){
            Uri formacionUri =  FormacionProvider.CONTENT_URI;
            cr=c.getContentResolver();
            Cursor cursor=cr.query(formacionUri,
                    null,
                    "Segovia",
                    selectionArgs,
                    null);



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
                o.setId(id);
                o.setTitulo(titulo);
                o.setCiudad(ciudad);
                o.setCoordenadas(coordenadas);
                o.setFechas(date);
                o.setOrganismo(organismo);
                o.setTipo(tipo);
                o.setDuracion(duracion);
                o.setDestinatarios(destinatarios);
                o.setDescripcion(description);
                o.setMateria(area);
                o.setLugar(lugar_celebracion);
                o.setInscripcion(inscripcion);
                o.setCod_provincia(prov);
                o.setPlazas(plazas);
                o.setInfoAdicional(infoAdicional);
                o.setActualizacion(actualizacion);
                o.setEnlace(enlace);
                ofers.add(o);
            }
            //cursor.close();

            return ofers;
        }
        else if(provincia.equalsIgnoreCase("Soria")){

            Uri formacionUri =  FormacionProvider.CONTENT_URI;
            cr=c.getContentResolver();
            Cursor cursor=cr.query(formacionUri,
                    null,
                    "Soria",
                    selectionArgs,
                    null);



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
                o.setId(id);
                o.setTitulo(titulo);
                o.setCiudad(ciudad);
                o.setCoordenadas(coordenadas);
                o.setFechas(date);
                o.setOrganismo(organismo);
                o.setTipo(tipo);
                o.setDuracion(duracion);
                o.setDestinatarios(destinatarios);
                o.setDescripcion(description);
                o.setMateria(area);
                o.setLugar(lugar_celebracion);
                o.setInscripcion(inscripcion);
                o.setCod_provincia(prov);
                o.setPlazas(plazas);
                o.setInfoAdicional(infoAdicional);
                o.setActualizacion(actualizacion);
                o.setEnlace(enlace);
                ofers.add(o);
            }
            //cursor.close();

           return ofers;
        }
        else if(provincia.equalsIgnoreCase("Valladolid")){

            Uri formacionUri =  FormacionProvider.CONTENT_URI;
            cr=c.getContentResolver();
            Cursor cursor=cr.query(formacionUri,
                    null,
                    "Valladolid",
                    selectionArgs,
                    null);



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
                o.setId(id);
                o.setTitulo(titulo);
                o.setCiudad(ciudad);
                o.setCoordenadas(coordenadas);
                o.setFechas(date);
                o.setOrganismo(organismo);
                o.setTipo(tipo);
                o.setDuracion(duracion);
                o.setDestinatarios(destinatarios);
                o.setDescripcion(description);
                o.setMateria(area);
                o.setLugar(lugar_celebracion);
                o.setInscripcion(inscripcion);
                o.setCod_provincia(prov);
                o.setPlazas(plazas);
                o.setInfoAdicional(infoAdicional);
                o.setActualizacion(actualizacion);
                o.setEnlace(enlace);
                ofers.add(o);
            }
            //cursor.close();

          return ofers;
        }
        else if(provincia.equalsIgnoreCase("León")){

            Uri formacionUri =  FormacionProvider.CONTENT_URI;
            cr=c.getContentResolver();
            Cursor cursor=cr.query(formacionUri,
                    null,
                    "León",
                    selectionArgs,
                    null);



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
                o.setId(id);
                o.setTitulo(titulo);
                o.setCiudad(ciudad);
                o.setCoordenadas(coordenadas);
                o.setFechas(date);
                o.setOrganismo(organismo);
                o.setTipo(tipo);
                o.setDuracion(duracion);
                o.setDestinatarios(destinatarios);
                o.setDescripcion(description);
                o.setMateria(area);
                o.setLugar(lugar_celebracion);
                o.setInscripcion(inscripcion);
                o.setCod_provincia(prov);
                o.setPlazas(plazas);
                o.setInfoAdicional(infoAdicional);
                o.setActualizacion(actualizacion);
                o.setEnlace(enlace);
                ofers.add(o);
            }
            //cursor.close();

            return ofers;
        }
        else if(provincia.equalsIgnoreCase("Zamora")){

            Uri formacionUri =  FormacionProvider.CONTENT_URI;
            cr=c.getContentResolver();
            Cursor cursor=cr.query(formacionUri,
                    null,
                    "Zamora",
                    selectionArgs,
                    null);



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
                o.setId(id);
                o.setTitulo(titulo);
                o.setCiudad(ciudad);
                o.setCoordenadas(coordenadas);
                o.setFechas(date);
                o.setOrganismo(organismo);
                o.setTipo(tipo);
                o.setDuracion(duracion);
                o.setDestinatarios(destinatarios);
                o.setDescripcion(description);
                o.setMateria(area);
                o.setLugar(lugar_celebracion);
                o.setInscripcion(inscripcion);
                o.setCod_provincia(prov);
                o.setPlazas(plazas);
                o.setInfoAdicional(infoAdicional);
                o.setActualizacion(actualizacion);
                o.setEnlace(enlace);
                ofers.add(o);
            }
            //cursor.close();

            return ofers;

        }
        else if(provincia.equalsIgnoreCase("Palencia")){

            Uri formacionUri =  FormacionProvider.CONTENT_URI;
            cr=c.getContentResolver();
            Cursor cursor=cr.query(formacionUri,
                    null,
                    "Palencia",
                    selectionArgs,
                    null);



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
                o.setId(id);
                o.setTitulo(titulo);
                o.setCiudad(ciudad);
                o.setCoordenadas(coordenadas);
                o.setFechas(date);
                o.setOrganismo(organismo);
                o.setTipo(tipo);
                o.setDuracion(duracion);
                o.setDestinatarios(destinatarios);
                o.setDescripcion(description);
                o.setMateria(area);
                o.setLugar(lugar_celebracion);
                o.setInscripcion(inscripcion);
                o.setCod_provincia(prov);
                o.setPlazas(plazas);
                o.setInfoAdicional(infoAdicional);
                o.setActualizacion(actualizacion);
                o.setEnlace(enlace);
                ofers.add(o);
            }
            //cursor.close();

            return ofers;
        }
        else if(provincia.equalsIgnoreCase("Ávila")){

            Uri formacionUri =  FormacionProvider.CONTENT_URI;
            cr=c.getContentResolver();
            Cursor cursor=cr.query(formacionUri,
                    null,
                    "Ávila",
                    selectionArgs,
                    null);



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
                o.setId(id);
                o.setTitulo(titulo);
                o.setCiudad(ciudad);
                o.setCoordenadas(coordenadas);
                o.setFechas(date);
                o.setOrganismo(organismo);
                o.setTipo(tipo);
                o.setDuracion(duracion);
                o.setDestinatarios(destinatarios);
                o.setDescripcion(description);
                o.setMateria(area);
                o.setLugar(lugar_celebracion);
                o.setInscripcion(inscripcion);
                o.setCod_provincia(prov);
                o.setPlazas(plazas);
                o.setInfoAdicional(infoAdicional);
                o.setActualizacion(actualizacion);
                o.setEnlace(enlace);
                ofers.add(o);
            }
            //cursor.close();

            return ofers;
        }
        else if(provincia.equalsIgnoreCase("Burgos")){

            Uri formacionUri =  FormacionProvider.CONTENT_URI;
            cr=c.getContentResolver();
            Cursor cursor=cr.query(formacionUri,
                    null,
                    "Burgos",
                    selectionArgs,
                    null);



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
                o.setId(id);
                o.setTitulo(titulo);
                o.setCiudad(ciudad);
                o.setCoordenadas(coordenadas);
                o.setFechas(date);
                o.setOrganismo(organismo);
                o.setTipo(tipo);
                o.setDuracion(duracion);
                o.setDestinatarios(destinatarios);
                o.setDescripcion(description);
                o.setMateria(area);
                o.setLugar(lugar_celebracion);
                o.setInscripcion(inscripcion);
                o.setCod_provincia(prov);
                o.setPlazas(plazas);
                o.setInfoAdicional(infoAdicional);
                o.setActualizacion(actualizacion);
                o.setEnlace(enlace);
                ofers.add(o);
            }
            //cursor.close();

            return ofers;

        }
        else{
            String provin;
            provin=managerPreferencias.leerPreferenciasDeConfiguracion(c,1);
            if(provin.equalsIgnoreCase(c.getString(R.string.todas)) || provin.equalsIgnoreCase(c.getString(R.string.provincia))){
                String temp="*";
                provin=temp;
            }
            Log.e("PROVIN", provin);
            Uri formacionUri =  FormacionProvider.CONTENT_URI;
            cr=c.getContentResolver();
            Cursor cursor=cr.query(formacionUri,
                    null,
                    provin,
                    selectionArgs,
                    null);



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
                o.setId(id);
                o.setTitulo(titulo);
                o.setCiudad(ciudad);
                o.setCoordenadas(coordenadas);
                o.setFechas(date);
                o.setOrganismo(organismo);
                o.setTipo(tipo);
                o.setDuracion(duracion);
                o.setDestinatarios(destinatarios);
                o.setDescripcion(description);
                o.setMateria(area);
                o.setLugar(lugar_celebracion);
                o.setInscripcion(inscripcion);
                o.setCod_provincia(prov);
                o.setPlazas(plazas);
                o.setInfoAdicional(infoAdicional);
                o.setActualizacion(actualizacion);
                o.setEnlace(enlace);
                ofers.add(o);
            }
            //cursor.close();

           return ofers;

        }
    }
    /**
     *A partir de la lista de ofertas de empleo recibida, selecciona aquellas cuyo atributo coordenadas esté relleno, separa la parte asociada a latitud de la asociada a la longitud, las introduce en una nueva lista y la devuelve.
     * @param ofers listado de ofertas de las que calcular su latitud y longitud
     * @return <ul>
     *          <li>ArraList: listado de ofertas que disponen de ubicacion</li>
     *          </ul>
     */
    public ArrayList asignarCoordenadas(ArrayList<OfertasEmpleo>ofers){
        double inc;
        ControladorFormateador formateadorCadena=new ControladorFormateador();
        ArrayList<OfertasEmpleo>ofertasConUbicacion=new ArrayList<>();
        for(int i=0;i<ofers.size();i++){
            inc=0.000001;
            if(!(ofers.get(i).getCoordenadas().isEmpty()) && !(ofers.get(i).getCoordenadas().equalsIgnoreCase(""))){
                String[]coordenadas=formateadorCadena.dividirCadena(ofers.get(i).getCoordenadas(),"#");
                //Log.e(i+"Titulo ",ofers.get(i).getTitulo()+"  Coordenadas: "+coordenadas[0]+"#"+coordenadas[1]+" Ciudad: "+ofers.get(i).getCiudad());
                ofers.get(i).setLatitud(coordenadas[0]);
                ofers.get(i).setLongitud(coordenadas[1]);

                for(int j=0;j<ofers.size();j++){
                    if((ofers.get(i).getLatitud().equalsIgnoreCase(ofers.get(j).getLatitud())) && (ofers.get(i).getLongitud().equalsIgnoreCase(ofers.get(j).getLongitud())) && i!=j){
                        String lat=formateadorCadena.limpiarCadenaExtremo(ofers.get(i).getLatitud());
                        String longit=formateadorCadena.limpiarCadenaExtremo(ofers.get(i).getLongitud());
                        Double latit=Double.parseDouble(lat);
                        Double longi=Double.parseDouble(longit);
                        latit+=inc;
                        longi+=inc;
                        ofers.get(i).setLatitud(String.valueOf(latit));
                        ofers.get(i).setLongitud(String.valueOf(longi));
                        inc+=0.000001;
                        Log.e(j+"Coordenadas nuevas",ofers.get(i).getLatitud()+"#"+ofers.get(i).getLongitud());
                    }
                }

                ofertasConUbicacion.add(ofers.get(i));
            }
        }
        return ofertasConUbicacion;
    }
    /**
     *A partir de la lista de ofertas de formacion recibida, selecciona aquellas cuyo atributo coordenadas esté relleno, separa la parte asociada a latitud de la asociada a la longitud, las introduce en una nueva lista y la devuelve.
     * @param ofers listado de ofertas de las que calcular su latitud y longitud
     * @return <ul>
     *          <li>ArraList: listado de ofertas que disponen de ubicacion</li>
     *          </ul>
     */
    public ArrayList asignarCoordenadasFormacion(ArrayList<OfertasFormacion>ofers){
        double inc;
        ControladorFormateador formateadorCadena=new ControladorFormateador();
        ArrayList<OfertasFormacion>ofertasConUbicacion=new ArrayList<>();
        for(int i=0;i<ofers.size();i++){
            inc=0.000001;
            if(!(ofers.get(i).getCoordenadas().isEmpty()) && !(ofers.get(i).getCoordenadas().equalsIgnoreCase(""))){
                String[]coordenadas=formateadorCadena.dividirCadena(ofers.get(i).getCoordenadas(),"#");
                Log.e(i+"Titulo ",ofers.get(i).getTitulo()+"  Coordenadas: "+coordenadas[0]+"#"+coordenadas[1]+" Ciudad: "+ofers.get(i).getCiudad());
                ofers.get(i).setLatitud(coordenadas[0]);
                ofers.get(i).setLongitud(coordenadas[1]);

                for(int j=0;j<ofers.size();j++){
                    if((ofers.get(i).getLatitud().equalsIgnoreCase(ofers.get(j).getLatitud())) && (ofers.get(i).getLongitud().equalsIgnoreCase(ofers.get(j).getLongitud())) && i!=j){

                        String lat=formateadorCadena.limpiarCadenaExtremo(ofers.get(i).getLatitud());
                        String longit=formateadorCadena.limpiarCadenaExtremo(ofers.get(i).getLongitud());
                        Double latit=Double.parseDouble(lat);
                        Double longi=Double.parseDouble(longit);
                        latit+=inc;
                        longi+=inc;
                        ofers.get(i).setLatitud(String.valueOf(latit));
                        ofers.get(i).setLongitud(String.valueOf(longi));
                        inc+=0.000001;
                        Log.e(j+"Coordenadas nuevas",ofers.get(i).getLatitud()+"#"+ofers.get(i).getLongitud());
                    }
                }

                ofertasConUbicacion.add(ofers.get(i));
            }
        }
        return ofertasConUbicacion;
    }
    /**
     * Comprueba si la lista de empleos recibida contiene elementos
     * @param ofers lista de ofertas recibida
     * @return <ul>
     *          <li>true: esta vacia</li>
     *          <li>false: tiene elementos</li>
     *          </ul>
     */
    boolean listaEmpleosVacia(ArrayList<OfertasEmpleo>ofers){
        if(ofers.isEmpty()){
            return true;
        }
        return false;
    }
    /**
     * Comprueba si la lista de empleos recibida esta inicializada
     * @param ofers lista de ofertas recibida
     * @return <ul>
     *          <li>true: no esta inicializada</li>
     *          <li>false: esta inicalizada</li>
     *          </ul>
     */
    boolean listaEmpleosNula(ArrayList<OfertasEmpleo>ofers){
        if(ofers==null){
            return true;
        }
        return false;
    }
    /**
     * Comprueba si la lista de formaciones recibida contiene elementos
     * @param ofers lista de ofertas recibida
     * @return <ul>
     *          <li>true: esta vacia</li>
     *          <li>false: tiene elementos</li>
     *          </ul>
     */
    boolean listaFormacionesVacia(ArrayList<OfertasFormacion>ofers){
        if(ofers.isEmpty()){
            return true;
        }
        return false;
    }
    /**
     * Comprueba si la lista de formaciones recibida esta inicializada
     * @param ofers lista de ofertas recibida
     * @return <ul>
     *          <li>true: no esta inicializada</li>
     *          <li>false: esta inicalizada</li>
     *          </ul>
     */
    boolean listaFormacionesNula(ArrayList<OfertasFormacion>ofers){
        if(ofers==null){
            return true;
        }
        return false;
    }
    /**
     * Devuelve el nº de ofertas de empleo contenidas en la lista recibida
     * @param ofers lista de ofertas recibida
     * @return <ul>
     *          <li>int: nº de ofertas almacenadas</li>
     *          </ul>
     */
    int tamListaEmpleos(ArrayList<OfertasEmpleo>ofers){
        return ofers.size();
    }
    /**
     * Devuelve el nº de ofertas de formacion contenidas en la lista recibida
     * @param ofers lista de ofertas recibida
     * @return <ul>
     *          <li>int: nº de ofertas almacenadas</li>
     *          </ul>
     */
    int tamListaFormaciones(ArrayList<OfertasFormacion>ofers){
        return ofers.size();
    }
    /**
     * Comprueba si un marcador de coordenadas pasado se encuentra en la lista de marcadores de coordenadas de ofertas de empleo recibida.
     * En caso verdadero se comunica con el controlador de avisos para que muestre el título de la oferta de empleo asociada al marcador
     * @param marker marcador a comprobar
     * @param marcadores listado de marcadores almacenados
     * @param ofertasConUbicacion informacion de las ofertas con ubicacion recuperadas
     * @return <ul>
     *          <li>true: existe ese mrcador entre los disponibles</li>
     *          <li>false: no existe ese mrcador entre los disponibles </li>
     *          </ul>
     */
    boolean coincideConMarcadorEmpleo(Marker marker, ArrayList<Marker> marcadores,ArrayList<OfertasEmpleo>ofertasConUbicacion,Context c){
        ControladorAvisos managerAvisos=new ControladorAvisos();
        for(int i=0;i<marcadores.size();i++){
            if(marker.equals(marcadores.get(i))){
                managerAvisos.avisosToast(ofertasConUbicacion.get(i).getTitulo(),c.getApplicationContext());
                return false;
            }
        }
        return false;
    }
    /**
     * Comprueba si un marcador de coordenadas pasado se encuentra en la lista de marcadores de coordenadas de ofertas de formacion recibida.
     * En caso verdadero se comunica con el controlador de avisos para que muestre el título de la oferta de formacion asociada al marcador
     * @param marker marcador a comprobar
     * @param marcadores listado de marcadores almacenados
     * @param ofertasConUbicacion informacion de las ofertas con ubicacion recuperadas
     * @return <ul>
     *          <li>true: existe ese mrcador entre los disponibles</li>
     *          <li>false: no existe ese mrcador entre los disponibles </li>
     *          </ul>
     */
    boolean coincideConMarcadorFormacion(Marker marker, ArrayList<Marker> marcadores,ArrayList<OfertasFormacion>ofertasConUbicacion,Context c){
        ControladorAvisos managerAvisos=new ControladorAvisos();
        for(int i=0;i<marcadores.size();i++){
            if(marker.equals(marcadores.get(i))){
                managerAvisos.avisosToast(ofertasConUbicacion.get(i).getTitulo(),c.getApplicationContext());
                return false;
            }
        }
        return false;
    }

    /**
     * Obtiene el título de la oferta de formación de una determinada posición de la lista. Estos métodos de lectura en el controlador se utilizan para no manipular directamente métodos del modelo desde las vistas ya que esto no cumpliría con el patrón MVC.
     * explicacion analoga para el resto de atributos de las ofertas, por ello no se documentan
     * @param o listado de ofertas de la que leer el titulo
     * @param i posicion de la oferta deseada
     * @return <ul>
     *          <li>String: titulo de la oferta</li>
     *          </ul>
     */
    String leerTituloFormacion(ArrayList<OfertasFormacion> o,int i){
        return o.get(i).getTitulo();
    }
    String leerMateriaFormacion(ArrayList<OfertasFormacion> o,int i){
        return o.get(i).getMateria();
    }
    String leerSitioFormacion(ArrayList<OfertasFormacion> o,int i){
        return o.get(i).getCiudad();
    }
    String leerFechasFormacion(ArrayList<OfertasFormacion> o,int i){
        return o.get(i).getFechas();
    }
    String leerDuracionFormacion(ArrayList<OfertasFormacion> o,int i){
        return o.get(i).getDuracion();
    }
    String leerDestinatariosFormacion(ArrayList<OfertasFormacion> o,int i){
        return o.get(i).getDestinatarios();
    }
    String leerInscripcionFormacion(ArrayList<OfertasFormacion> o,int i){
        return o.get(i).getInscripcion();
    }
    String leerEnlaceFormacion(ArrayList<OfertasFormacion> o,int i){
        return o.get(i).getEnlace();
    }
    String leerLatitudFormacion(OfertasFormacion o){
        return o.getLatitud();
    }
    String leerLongitudFormacion(OfertasFormacion o){
        return o.getLongitud();
    }
    /**
     * Obtiene la fecha de última actualización bien de una oferta recibida o de una oferta en una determinada posición de la lista en función del valor del parámetro tipoLectura recibido.
     * Explicacion analoga para el resto de atributos de las ofertas, por ello no se documentan los metodos asociados
     * @param o listado de ofertas de las que leer la actualizacion
     * @param i posicion de la oferta deseada
     * @param oferta oferta de la que leer la actualizacion
     * @param tipoLectura parametro indicativo del tipo de lectura (por busqueda en listado o recuperacion directa de una oferta individual)
     * @return <ul>
     *          <li>true: atributo recuperado (fecha de ultima actualizacion en este caso)</li>
     *          </ul>
     */
    String leerActualizacionFormacion(ArrayList<OfertasFormacion> o,int i,OfertasFormacion oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getActualizacion();
        }
        else{
            return o.get(i).getActualizacion();
        }
    }
    String leerTituloFormacion2(ArrayList<OfertasFormacion> o,int i,OfertasFormacion oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getTitulo();
        }
        else{
            return o.get(i).getTitulo();
        }
    }

    String leerCiudadFormacion(ArrayList<OfertasFormacion> o,int i,OfertasFormacion oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getCiudad();
        }
        else{
            return o.get(i).getCiudad();
        }
    }
    String leerIdFormacion(ArrayList<OfertasFormacion> o,int i,OfertasFormacion oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getId();
        }
        else{
            return o.get(i).getId();
        }
    }
    String leerCoordenadasFormacion(ArrayList<OfertasFormacion> o,int i,OfertasFormacion oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getCoordenadas();
        }
        else{
            return o.get(i).getCoordenadas();
        }
    }
    String leerFechasFormacion2(ArrayList<OfertasFormacion> o,int i,OfertasFormacion oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getFechas();
        }
        else{
            return o.get(i).getFechas();
        }
    }
    String leerOrganismoFormacion(ArrayList<OfertasFormacion> o,int i,OfertasFormacion oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getOrganismo();
        }
        else{
            return o.get(i).getOrganismo();
        }
    }
    String leerTipoFormacion(ArrayList<OfertasFormacion> o,int i,OfertasFormacion oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getTipo();
        }
        else{
            return o.get(i).getTipo();
        }
    }
    String leerDuracionFormacion(ArrayList<OfertasFormacion> o,int i,OfertasFormacion oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getDuracion();
        }
        else{
            return o.get(i).getDuracion();
        }
    }
    String leerDescripcionFormacion(ArrayList<OfertasFormacion> o,int i,OfertasFormacion oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getDescripcion();
        }
        else{
            return o.get(i).getDescripcion();
        }
    }
    String leerMateriaFormacion(ArrayList<OfertasFormacion> o,int i,OfertasFormacion oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getMateria();
        }
        else{
            return o.get(i).getMateria();
        }
    }
    String leerDestinatariosFormacion(ArrayList<OfertasFormacion> o,int i,OfertasFormacion oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getDestinatarios();
        }
        else{
            return o.get(i).getDestinatarios();
        }
    }
    String leerLugarFormacion(ArrayList<OfertasFormacion> o,int i,OfertasFormacion oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getLugar();
        }
        else{
            return o.get(i).getLugar();
        }
    }
    String leerInscripcionFormacion(ArrayList<OfertasFormacion> o,int i,OfertasFormacion oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getInscripcion();
        }
        else{
            return o.get(i).getInscripcion();
        }
    }
    String leerPlazasFormacion(ArrayList<OfertasFormacion> o,int i,OfertasFormacion oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getPlazas();
        }
        else{
            return o.get(i).getPlazas();
        }
    }
    String leerInfoFormacion(ArrayList<OfertasFormacion> o,int i,OfertasFormacion oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getInfoAdicional();
        }
        else{
            return o.get(i).getInfoAdicional();
        }
    }
    String leerEnlaceFormacion(ArrayList<OfertasFormacion> o,int i,OfertasFormacion oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getEnlace();
        }
        else{
            return o.get(i).getEnlace();
        }
    }
    String leerCodProvinciaFormacion(ArrayList<OfertasFormacion> o,int i,OfertasFormacion oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getCod_provincia();
        }
        else{
            return o.get(i).getCod_provincia();
        }
    }


    String leerProvinciaEmpleo(ArrayList<OfertasEmpleo> o,int i,OfertasEmpleo oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getProvincia();
        }
        else{
            return o.get(i).getProvincia();
        }
    }
    String leerCiudadEmpleo(ArrayList<OfertasEmpleo> o,int i,OfertasEmpleo oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getCiudad();
        }
        else{
            return o.get(i).getCiudad();
        }
    }
    String leerTituloEmpleo(ArrayList<OfertasEmpleo> o,int i,OfertasEmpleo oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getTitulo();
        }
        else{
            return o.get(i).getTitulo();
        }
    }
    String leerFechaEmpleo(ArrayList<OfertasEmpleo> o,int i,OfertasEmpleo oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getFecha_publicacion();
        }
        else{
            return o.get(i).getFecha_publicacion();
        }
    }
    String leerDescripcionEmpleo(ArrayList<OfertasEmpleo> o,int i,OfertasEmpleo oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getDescripcion();
        }
        else{
            return o.get(i).getDescripcion();
        }
    }
    String leerFuenteEmpleo(ArrayList<OfertasEmpleo> o,int i,OfertasEmpleo oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getFuente();
        }
        else{
            return o.get(i).getFuente();
        }
    }
    String leerEnlaceEmpleo(ArrayList<OfertasEmpleo> o,int i,OfertasEmpleo oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getEnlace();
        }
        else{
            return o.get(i).getEnlace();
        }
    }
    String leerLatitudEmpleo(ArrayList<OfertasEmpleo> o,int i,OfertasEmpleo oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getLatitud();
        }
        else{
            return o.get(i).getLatitud();
        }
    }
    String leerLongitudEmpleo(ArrayList<OfertasEmpleo> o,int i,OfertasEmpleo oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getLongitud();
        }
        else{
            return o.get(i).getLongitud();
        }
    }
    String leerIdEmpleo(ArrayList<OfertasEmpleo> o,int i,OfertasEmpleo oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getId();
        }
        else{
            return o.get(i).getId();
        }
    }
    String leerCoordenadasEmpleo(ArrayList<OfertasEmpleo> o,int i,OfertasEmpleo oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getCoordenadas();
        }
        else{
            return o.get(i).getCoordenadas();
        }
    }
    String leerActualizacionEmpleo(ArrayList<OfertasEmpleo> o,int i,OfertasEmpleo oferta,int tipoLectura){
        if(tipoLectura==1){
            return oferta.getActualizacion();
        }
        else{
            return o.get(i).getActualizacion();
        }
    }











    /**
     * Establece el título de la oferta de empleo recibida al recibido
     * Explicacion analoga para el resto de atributos de las ofertas, por ello no se documentan los metodos asociados
     * @param o oferta sobre la que establecer un valor de atributo (en este caso el titulo)
     * @param titulo valor del atributo a establecer (en este caso el titulo)
     */
    void escribirTituloEmpleo(OfertasEmpleo o,String titulo){
        o.setTitulo(titulo);
    }
    void escribirCiudadEmpleo(OfertasEmpleo o,String ciudad){
        o.setCiudad(ciudad);
    }
    void escribirProvinciaEmpleo(OfertasEmpleo o,String prov){
        o.setProvincia(prov);
    }
    void escribirFuenteEmpleo(OfertasEmpleo o,String source){
        o.setFuente(source);
    }
    void escribirFechaPubEmpleo(OfertasEmpleo o,String date){
        o.setFecha_publicacion(date);
    }
    void escribirDescripcionEmpleo(OfertasEmpleo o,String description){
        o.setDescripcion(description);
    }
    void escribirEnlaceEmpleo(OfertasEmpleo o,String enlace){
        o.setEnlace(enlace);
    }
    void escribirActualizacionEmpleo(OfertasEmpleo o,String actualizacion){
        o.setActualizacion(actualizacion);
    }
    void escribirCoordenadasEmpleo(OfertasEmpleo o,String coordenadas){
        o.setCoordenadas(coordenadas);
    }
    void escribirIdEmpleo(OfertasEmpleo o,String id){
        o.setId(id);
    }
    void escribirIdProvinciaEmpleo(OfertasEmpleo o,String id){
        o.setIdProvincia(id);
    }
    void escribirIdCiudadEmpleo(OfertasEmpleo o,String id){
        o.setIdLocalidad(id);
    }
    void escribirTituloFormacion(OfertasFormacion o,String titulo){
        o.setTitulo(titulo);
    }
    void escribirIdFormacion(OfertasFormacion o,String id){
        o.setId(id);
    }
    void escribirCiudadFormacion(OfertasFormacion o,String ciudad){
        o.setCiudad(ciudad);
    }
    void escribirCoordenadasFormacion(OfertasFormacion o,String coordenadas){
        o.setCoordenadas(coordenadas);
    }
    void escribirFechasFormacion(OfertasFormacion o,String date){
        o.setFechas(date);
    }
    void escribirOrganismoFormacion(OfertasFormacion o,String organismo){
        o.setOrganismo(organismo);
    }
    void escribirTipoFormacion(OfertasFormacion o,String tipo){
        o.setTipo(tipo);
    }
    void escribirDuracionFormacion(OfertasFormacion o,String duracion){
        o.setDuracion(duracion);
    }
    void escribirDestinatariosFormacion(OfertasFormacion o,String destinatarios){
        o.setDestinatarios(destinatarios);
    }
    void escribirDescripcionFormacion(OfertasFormacion o,String description){
        o.setDescripcion(description);
    }
    void escribirMateriaFormacion(OfertasFormacion o,String area){
        o.setMateria(area);
    }
    void escribirLugarFormacion(OfertasFormacion o,String lugar_celebracion){
        o.setLugar(lugar_celebracion);
    }
    void escribirInscripcionFormacion(OfertasFormacion o,String inscripcion){
        o.setInscripcion(inscripcion);
    }
    void escribirCodProvinciaFormacion(OfertasFormacion o,String prov){
        o.setCod_provincia(prov);
    }
    void escribirPlazasFormacion(OfertasFormacion o,String plazas){
        o.setPlazas(plazas);
    }
    void escribirInfoFormacion(OfertasFormacion o,String infoAdicional){
        o.setInfoAdicional(infoAdicional);
    }
    void escribirActualizacionFormacion(OfertasFormacion o,String actualizacion){
        o.setActualizacion(actualizacion);
    }
    void escribirEnlaceFormacion(OfertasFormacion o,String enlace){
        o.setEnlace(enlace);
    }
}
