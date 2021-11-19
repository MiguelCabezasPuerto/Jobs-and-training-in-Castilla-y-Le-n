package com.example.emplenews;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
/**
 * Clase ControladorPreferencias
 *
 * Controlador encargado de gestionar las preferencias del usuario
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class ControladorPreferencias extends Utils {
    public ControladorPreferencias(){}

    /**
     * Graba en SharedPreferences bien de empleos bien de formaciones que existen ofertas de ese tipo.
     * @param c contexto desde el que es invicado el controlador
     * @param valor flag de existencia de ofertas
     * @param tipoEscritura flag para indicar si se trata de escritura de existencia de ofertas de empleo o formacion
     */
    void escribirEnPreferencias(Context c, boolean valor, int tipoEscritura){
        if(tipoEscritura==1){
            SharedPreferences prefs = c.getSharedPreferences("HAYRESULTADOS",
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("HAYRESULTADOS",valor);
            editor.commit();
        }else if(tipoEscritura==2){
            SharedPreferences prefs2=c.getSharedPreferences("HAYFORMACIONES",
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor2 = prefs2.edit();
            editor2.putBoolean("HAYFORMACIONES",valor);
            editor2.commit();
        }
    }

    /**
     * Lee de SharedPreferences bien de empleos bien de formaciones si existen ofertas de ese tipo
     * @param c contexto desde el que es invicado el controlador
     * @param tipoLectura flag para indicar si se comprueba la existencia de ofertas de empleo o de formacion
     * @return <ul>
     *          <li>true: existen ofertas</li>
     *          <li>false: no existen ofertas</li>
     *          </ul>
     */
    boolean leerDePreferencias(Context c,int tipoLectura){
        if(tipoLectura==1){
            SharedPreferences preferences = c.getSharedPreferences("HAYRESULTADOS",
                    Context.MODE_PRIVATE);
            return preferences.getBoolean("HAYRESULTADOS",false);
        }else{
            SharedPreferences preferences2 = c.getSharedPreferences("HAYFORMACIONES",
                    Context.MODE_PRIVATE);
            return preferences2.getBoolean("HAYFORMACIONES",false);
        }
    }
    /**
     * Lee de SharedPreferences cuál es la provincia y materia elegida como predeterminada por el usuario en su configuración
     * @param c contexto desde el que es invicado el controlador
     * @param tipoLectura flag para indicar si se leen los parametros asociados a las preferencias de provincia o de materia de formacion
     * @return <ul>
     *          <li>String: provincia o materia preferida por configuracion por el usuario</li>
     *          </ul>
     */
    String leerPreferenciasDeConfiguracion(Context c,int tipoLectura){
        String valorDevuelto;
        SharedPreferences prefs;
        if(tipoLectura==1){
            prefs=c.getSharedPreferences("configuracionusuario", Context.MODE_PRIVATE);
            valorDevuelto=prefs.getString("Provincia","provincia");
            return valorDevuelto;
        }
        else{
            prefs=c.getSharedPreferences("configuracionusuario",Context.MODE_PRIVATE);
            valorDevuelto=prefs.getString("Familia","*");
            return valorDevuelto;
        }
    }
    /**
     * Escribe en SharedPreferences la provincia y materia elegida como predeterminada por el usuario en su configuración
     * @param prefs objeto vinculo con las preferencias de usuario
     * @param valor preferencia a grabar
     * @param tipoEscritura flag para indicar si se graba la provincia o la materia en preferencias
     */
    void escribirPreferenciasConfiguracion(SharedPreferences prefs,Object valor,int tipoEscritura){
        SharedPreferences.Editor editor = prefs.edit();
        if(tipoEscritura==1){
            editor.putString("Provincia", valor.toString());
            Log.e("Provincia: ",valor.toString());
        }else if(tipoEscritura==2){
            Log.e("Familia",valor.toString());
            editor.putString("Familia",valor.toString());
        }else{
            IdiomaSeleccionable idiomaSeleccionable=(IdiomaSeleccionable)valor;
            Log.e("Idioma: ",idiomaSeleccionable.getNombre());
            editor.putString("Idioma",idiomaSeleccionable.getNombre());
        }
        editor.commit();
    }
}
