package com.example.emplenews;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
/**
 * Clase PantallaCargaDatos
 *
 * Actividad asociada a la pantalla de carga de datos de la aplicación que aparece nada más iniciarla.
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class PantallaCargaDatos extends AppCompatActivity {

    ContentResolver cr;
    ContentResolver cr2;
    SQLiteDatabase db;
    ArrayList<OfertasEmpleo> ofers;
    /**
     * Variable que sirve para comprobar si se han descargado correctamente o no las ofertas de empleo y poder comunicarlo al resto de actividades
     */
    boolean conResultados;
    /**
     * Variable que sirve para comprobar si se han descargado correctamente o no las ofertas de formación y poder comunicarlo al resto de actividades
     */
    boolean conFormaciones;
    ControladorConexionesFicheros managerConexionesFicheros;
    ControladorBD managerBD;



    @Override
    /**
     *Inicializa el contenido de la vista e inicia el proceso de llamada a los controladores para la descarga de las ofertas de la fuente
     * @param savedInstanceState estado de la actividad
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_carga_datos);
        descargarJSON();
        ofers=null;
        conResultados=false;
        conFormaciones=false;
        Log.e("Paso por aqui", "onCreate");

    }
    /**
     * Genera las URL de la fuente de datos e invoca a una tarea asíncrona que interaccionando con los controladores procede a la descarga de las ofertas
     */
    void descargarJSON(){
        String urlaDescargar="https://datosabiertos.jcyl.es/web/jcyl/risp/es/empleo/ofertas-empleo/1284354353012.xml";
        String urlFormacion="https://datosabiertos.jcyl.es/web/jcyl/risp/es/empleo/formacion-empleo/1284354357765.xml";
        new PantallaCargaDatos.CargaBD().execute(urlaDescargar,urlFormacion);
        Log.e("Paso por aqui", "descargarJSON");
    }
    /**
     * Clase asíncrona encargada de la descarga y conexión con la fuente de datos a través de los controladores
     */
    class CargaBD extends AsyncTask<String,Void,String> {
        ProgressDialog progressDialog;
        public CargaBD(){
            //this.progressDialog=p;
        }
        /**
         * Lo que se realiza justo antes de su ejecución, en este caso realiza el método de la clase padre.
         */
        protected void onPreExecute(){
            super.onPreExecute();
            //progressDialog.show();
        }
        /**
         * Lo que se realiza en segundo plano de forma asíncrona cuando se ejecuta la tarea.,En este caso, llamando a los métodos respectivos de los controladores, se conecta con la fuente de datos, se descargan los ficheros XML con las ofertas y se transforman para guardar el contenido en la base de datos.
         * @param strings parametros de recepcion del metodo, pueden ser cero o mas
         * @return <ul>
         *          <li>String: mensaje de exito o fallo de la operacion </li>
         *          </ul>
         */
        protected String doInBackground(String...strings) {

            String url = strings[0];
            String urlFormacion= strings[1];
            managerConexionesFicheros=new ControladorConexionesFicheros();
            managerBD=new ControladorBD();

            if(managerConexionesFicheros.conexionInternet(getApplicationContext())){

                int conexionError;
                conexionError=managerConexionesFicheros.abrirConexion(url,getApplicationContext());
                if(conexionError==-1){
                    return getString(R.string.sinconexionainternet);
                }
                if(conexionError==-2){
                    return getString(R.string.error);
                }


                if((managerBD.buscarRegistroBD("provincia",getApplicationContext(),1))!=null){
                    managerBD.borrarRegistroBD("todo",getApplicationContext(),1);
                    NodeList listaElements=managerConexionesFicheros.obtenerXML(getApplicationContext());
                    cr = getContentResolver();
                    int errorParseo=managerConexionesFicheros.parsearXMLaBD(listaElements,cr);
                    if(errorParseo==0){
                        conResultados=true;
                    }
                    else{
                        conResultados=false;
                    }
                    Log.e("14MARZO","ENTRO AQUI EN EMPLEOS");
                    return descargarFormaciones(urlFormacion);
                }
                else{
                    NodeList listaElements=managerConexionesFicheros.obtenerXML(getApplicationContext());
                    cr = getContentResolver();
                    int errorParseo=managerConexionesFicheros.parsearXMLaBD(listaElements,cr);
                    if(errorParseo==0){
                        conResultados=true;
                    }
                    else{
                        conResultados=false;
                    }
                    return descargarFormaciones(urlFormacion);

                }
            }
            else{
                String ruta=getFilesDir()+"/empleos.xml";
                if(managerConexionesFicheros.existeFichero(getApplicationContext(),ruta)){
                    managerBD.borrarRegistroBD("todo",getApplicationContext(),1);
                    NodeList listaElements=managerConexionesFicheros.obtenerXML(getApplicationContext());
                    cr = getContentResolver();
                    int errorParseo=managerConexionesFicheros.parsearXMLaBD(listaElements,cr);
                    if(errorParseo==0){
                        conResultados=true;
                    }
                    else{
                        conResultados=false;
                    }
                    return descargarFormaciones(urlFormacion);
                }
                else{
                    conResultados=false;
                    return descargarFormaciones(urlFormacion);
                }

            }
        }

        /**
         * Acción que se le muestra al usuario en el transcurso de la realización de la tarea en segundo plano.
         * @param valores informacion a mostrar en el transcurso de la operacion en segundo plano
         */
        protected void onProgressUpdate(Void...valores){
            super.onProgressUpdate(valores); //hay que subir API proyecto a la 29
        }
        /**
         * Acción realizada al terminar la ejecución de la tarea en segundo plano. En este caso navegar a la pantalla de Inicio de la aplicación.
         * @param resultado resultado de la operacion en segundo plano
         */
        protected void onPostExecute(String resultado){
            super.onPostExecute(resultado);
            Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_LONG).show();
            Intent intent=new Intent(getApplicationContext(),Inicio.class);
            intent.putExtra("conResultados",conResultados);
            intent.putExtra("conFormaciones",conFormaciones);
            Log.e("CARGA CON FORMACIONES",""+conFormaciones);
            intent.putExtra("flag",1);
            startActivity(intent);
        }

    }

    /**
     * Llamando a los métodos de los controladores, descarga y almacena las ofertas de acciones fomartivas de la fuente de datos abierta de la Junta de Castilla y León en la base de datos interna.
     * @param urlFormacion URL de la fuente de datos con la que establecer la conexion para proceder a la descarga de la informacion las ofertas de formacion
     * @return <ul>
     *          <li>String: mensaje de exito o fallo de la operacion </li>
     *          </ul>
     */
    public String descargarFormaciones(String urlFormacion){
        String url=urlFormacion;
        HttpURLConnection conexion = null;
        InputStream input = null;
        OutputStream output = null;
        if(managerConexionesFicheros.conexionInternet(getApplicationContext())){
                int conexionError;
                conexionError=managerConexionesFicheros.abrirConexionFormacion(url,getApplicationContext());
                if(conexionError==-1){
                    return getString(R.string.sinconexionainternet);
                }
                if(conexionError==-2){
                    return getString(R.string.error);
                }

            if((managerBD.buscarRegistroBD("*",getApplicationContext(),2))!=null){
                managerBD.borrarRegistroBD("todo",getApplicationContext(),2);
                NodeList listaElements=managerConexionesFicheros.obtenerXMLFormacion(getApplicationContext());
                cr2 = getContentResolver();
                int errorParseo=managerConexionesFicheros.parsearXMLaBDFormacion(listaElements,cr2);
                Log.e("14MARZO","ENTRO AQUI EN FORMACION");
                if(errorParseo==0){
                    conFormaciones=true;
                    return getString(R.string.actualizadacarga);
                }
                conFormaciones=false;
                return getString(R.string.sindatos);
            }
            else{
                cr2 = getContentResolver();
                NodeList listaElements=managerConexionesFicheros.obtenerXMLFormacion(getApplicationContext());
                int errorParseo=managerConexionesFicheros.parsearXMLaBDFormacion(listaElements,cr2);
                if(errorParseo==0){
                    conFormaciones=true;
                    return getString(R.string.actualizadacarga);
                }
                conFormaciones=false;
                return getString(R.string.sindatos);
            }

        }
        else{
            String ruta=getFilesDir()+"/formacion.xml";
            if(managerConexionesFicheros.existeFichero(getApplicationContext(),ruta)){
                cr2 = getContentResolver();
                managerBD.borrarRegistroBD("todo",getApplicationContext(),2);

                NodeList listaElements=managerConexionesFicheros.obtenerXMLFormacion(getApplicationContext());
                int errorParseo=managerConexionesFicheros.parsearXMLaBDFormacion(listaElements,cr2);
                if(errorParseo==0){
                    conFormaciones=true;
                    return "";
                }
                conFormaciones=false;
                return getString(R.string.sindatos);
            }
            else{
                conFormaciones=false;
                return getString(R.string.sindatos);
            }

        }

    }

}
