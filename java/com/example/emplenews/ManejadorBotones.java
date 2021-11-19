package com.example.emplenews;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.emplenews.OfertasEmpleo;

import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Clase ManejadorBotones
 *
 * Controlador encargado de comunicarse con el controlador de preferencias cuando se pulsa el botón de guardado
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class ManejadorBotones extends Utils{

    public ManejadorBotones(){

    }
    /**
     * Comunicándose con el Controlador de Preferencias escribe las indicadas por el usuario cuando decide guardar su configuración.
     * @param provincia provincia elegida como predeterminada
     * @param materia materia elegida como predeterminada
     * @param prefs gestor de preferencias
     */
    public void onSaveConfigClick(Object provincia,Object materia, SharedPreferences prefs){
        ControladorPreferencias managerPreferencias=new ControladorPreferencias();
        managerPreferencias.escribirPreferenciasConfiguracion(prefs,provincia,1);
        managerPreferencias.escribirPreferenciasConfiguracion(prefs,materia,2);
    }


    public Locale cambiarIdioma(SharedPreferences prefs,String porDefecto){
        String idioma;
        idioma=prefs.getString("Idioma",porDefecto);
        Locale localizacion;

        if(idioma.equalsIgnoreCase("Español") ||idioma.equalsIgnoreCase("Spanish") || idioma.equalsIgnoreCase("Espagnol") ){
            localizacion = new Locale("es", "ES");
            Log.e("Cambio a :","Español");
        }else if(idioma.equalsIgnoreCase("Inglés") ||idioma.equalsIgnoreCase("English") || idioma.equalsIgnoreCase("Anglais") ){
            localizacion = new Locale("en", "US");
            Log.e("Cambio a :","Inglés");
        }else{
            localizacion = new Locale("fr", "FR");
            Log.e("Cambio a :","Frances");
        }

        return localizacion;
    }

}
