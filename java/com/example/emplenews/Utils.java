package com.example.emplenews;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.maps.model.Marker;

import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Clase Controlador genérico
 *
 * Contiene los métodos a implementat en el resto de controladores
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */

public  class Utils {

    public Utils(){

    }

    void avisosToast(String mensaje,Context c){

    }
    boolean conexionInternet(Context c){
        return false;
    }
    int abrirConexion(String url,Context c){return -1;}
    int abrirConexionFormacion(String url,Context c){return -1;}
    NodeList obtenerXML(Context c){return null;}
    NodeList obtenerXMLFormacion(Context c){return null;}
    int parsearXMLaBD(NodeList listaElements,ContentResolver cr){return -1;}
    int parsearXMLaBDFormacion(NodeList listaElements,ContentResolver cr2){return -1;}
    boolean existeFichero(Context c,String ruta){ return false; }
    public void mandarCorreo(Context c,View view){}
    boolean localizacionActiva(Context c){return false;}

    String numRegistrosEmpleos(Context c){return "0";}
    String numRegistrosFormacion(Context c){return "0";}
    String numRegistrosFavoritos(Context c){return "0";}
    ArrayList buscarEmpleos(String provinciaSeleccionada,String busquedaLibre,Context c){return null;}
    ArrayList buscarFormaciones(String provincia_seleccionada,String busquedaLibre,String materia_seleccionada,Context c){return null;}
    public ArrayList mostrarFavoritos(int clase,Context c){return null;}
    Cursor buscarRegistroBD(String selection,Context c,int BD){return null;}
    void borrarRegistroBD(String selection,Context c,int BD){}
    Uri insertarEmpleoEnFavoritos(OfertasEmpleo ofertaAMostrar,Context c){return null;}
    Uri insertarCursoEnFavoritos(OfertasFormacion ofertaAMostrar,Context c){return null;}

    void escribirEnPreferencias(Context c,boolean valor,int tipoEscritura){}
    boolean leerDePreferencias(Context c,int tipoLectura){
        return false;
    }
    String leerPreferenciasDeConfiguracion(Context c,int tipoLectura){return null;}
    void escribirPreferenciasConfiguracion(SharedPreferences prefs,Object valor,int tipoEscritura){}

    ArrayList hacerBusquedaOfertas(Spinner provinciaSeleccionada, String busquedaUsuario, Context c, int cuantosRegistros){return null;}
    ArrayList asignarCoordenadas(ArrayList<OfertasEmpleo>ofers){return null;}
    public ArrayList asignarCoordenadasFormacion(ArrayList<OfertasFormacion>ofers){return null;}

    boolean listaEmpleosVacia(ArrayList<OfertasEmpleo>ofers){return true;}
    boolean listaEmpleosNula(ArrayList<OfertasEmpleo>ofers){return true;}
    boolean listaFormacionesVacia(ArrayList<OfertasFormacion>ofers){return true;}
    boolean listaFormacionesNula(ArrayList<OfertasFormacion>ofers){return true;}
    int tamListaEmpleos(ArrayList<OfertasEmpleo>ofers){return 0;}
    int tamListaFormaciones(ArrayList<OfertasFormacion>ofers){return 0;}

    boolean coincideConMarcadorEmpleo(Marker marker, ArrayList<Marker> marcadores,ArrayList<OfertasEmpleo>ofertasConUbicacion,Context c){return false;}
    boolean coincideConMarcadorFormacion(Marker marker, ArrayList<Marker> marcadores,ArrayList<OfertasFormacion>ofertasConUbicacion,Context c){return false;}



    void llamarActividad(View view, boolean conOfertas, int flag, int tipo, Context origen, Class<MainActivity> destino){}

    String obtenerRutaAFichero(String fichero){
        return null;
    }
    String escribirEnFichero(InputStream input, String rutaFichero){
        return null;
    }
    ContentResolver abrirBD(){
        return null;
    }
    void inhabilitarBoton(Button boton){
    }
    Cursor filtrarPorProvincia(ContentResolver cr, String provincia){
        return null;
    }
    ArrayList<OfertasEmpleo> meterDeBDenLista(Cursor cursor){
        return null;
    }

    void  onSaveConfigClick(Object provincia, Object idioma, SharedPreferences prefs){

    }

    Locale cambiarIdioma(SharedPreferences prefs, String porDefecto){
            return null;
    }





}
