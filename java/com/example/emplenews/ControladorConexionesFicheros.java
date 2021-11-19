package com.example.emplenews;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
/**
 * Clase ControladorConexionesFicheros
 *
 * Controlador encargado de las conexiones y tratamiento interno de ficheros
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class ControladorConexionesFicheros extends Utils {

    public ControladorConexionesFicheros(){

    }

    /**
     * Comprueba si el dispositivo dispone de conectividad a una red con acceso a Internet. Devuelve cierto en caso afirmativo, falso en caso de no disponer de ella.
     * @param context contexto desde el que se invoca al metodo
     * @return <ul>
     *          <li>true: hay conexion</li>
     *          <li>false: no hay conexion</li>
     *          </ul>
     */
    public boolean conexionInternet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // Si hay conexión a Internet en este momento
            return true;
        } else {
            return false;
        }
    }
    /**
     * Trata de abrir una petición de conexión con el servidor de la URL pasada como parámetro (fuente de datos abiertos de la Junta de Castilla y León). En caso de conexión exitosa descarga el fichero XML contenedor de las ofertas de empleo y lo guarda en el dispositivo. En caso de error en la conexión, devuelve “-1” y no procede a la descarga.
     * @param url url a la que establecer la conexion
     * @param context contexto desde el que se invoca al metodo
     * @return <ul>
     *          <li>0: conexion existosa</li>
     *          <li>-1: error/li>
     *          <li>-2: excepciones producidas/li>
     *          </ul>
     */
    int abrirConexion(String url,Context context){
        HttpURLConnection conexion = null;
        InputStream input = null;
        OutputStream output = null;
        try {
            URL urlFichero = new URL(url);
            conexion = (HttpURLConnection) urlFichero.openConnection();
            conexion.connect();

            if (conexion.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return -1;
            }
            input = conexion.getInputStream();
            String rutaFichero = context.getFilesDir() + "/empleos.xml";
            output = new FileOutputStream(rutaFichero);
            byte[] data = new byte[1024];

            int count;

            while ((count = input.read(data)) != -1) {
                output.write(data, 0, count);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return -2;
        } catch (IOException e) {
            e.printStackTrace();
            return -2;
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
                if (output != null) {
                    output.close();
                }
                if (conexion != null) {
                    conexion.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return -2;
            }
        }
        conexion.disconnect();
        return 0;
    }

    /**
     * Trata de abrir una petición de conexión con el servidor de la URL pasada como parámetro (fuente de datos abiertos de la Junta de Castilla y León). En caso de conexión exitosa descarga el fichero XML contenedor de las ofertas de formacion y lo guarda en el dispositivo. En caso de error en la conexión, devuelve “-1” y no procede a la descarga.
     * @param url url a la que establecer la conexion
     * @param context contexto desde el que se invoca al metodo
     * @return <ul>
     *          <li>0: conexion existosa</li>
     *          <li>-1: error/li>
     *          <li>-2: excepciones producidas/li>
     *          </ul>
     */
    int abrirConexionFormacion(String url,Context context){
        HttpURLConnection conexion = null;
        InputStream input = null;
        OutputStream output = null;
        try {
            URL urlFichero = new URL(url);
            conexion = (HttpURLConnection) urlFichero.openConnection();
            conexion.connect();

            if (conexion.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return -1;
            }
            input = conexion.getInputStream();
            String rutaFichero = context.getFilesDir() + "/formacion.xml";
            output = new FileOutputStream(rutaFichero);
            byte[] data = new byte[1024];

            int count;

            while ((count = input.read(data)) != -1) {
                output.write(data, 0, count);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return -2;
        } catch (IOException e) {
            e.printStackTrace();
            return -2;
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
                if (output != null) {
                    output.close();
                }
                if (conexion != null) {
                    conexion.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return -2;
            }
        }
        conexion.disconnect();
        return 0;
    }
    /**
     * Obtiene los diferentes nodos que componen el fichero XML de ofertas de empleo situado en el almacenamiento del dispositivo.
     * @param c contexto desde el que se invoca al metodo
     * @return <ul>
     *          <li>NodeList: nodos del fichero XML</li>
     *          </ul>
     */
    NodeList obtenerXML(Context c){
        String ruta=c.getFilesDir()+"/empleos.xml";


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder= null;
        NodeList listaElements=null;
        try {
            builder = factory.newDocumentBuilder();
            Document documento=builder.parse(new File(ruta));
            listaElements=documento.getElementsByTagName("element");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        } catch (SAXException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return listaElements;
    }
    /**
     * Obtiene los diferentes nodos que componen el fichero XML de ofertas de formacion situado en el almacenamiento del dispositivo.
     * @param c contexto desde el que se invoca al metodo
     * @return <ul>
     *          <li>NodeList: nodos del fichero XML</li>
     *          </ul>
     */
    NodeList obtenerXMLFormacion(Context c){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder= null;
        String ruta=c.getFilesDir()+"/formacion.xml";
        NodeList listaElements=null;
        try {
            builder = factory.newDocumentBuilder();
            Document documento=builder.parse(new File(ruta));
            listaElements=documento.getElementsByTagName("element");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaElements;
    }
    /**
     * Convierte los datos almacenados en el fichero XML de ofertas de empleo en una serie de ofertas de empleo y almacena estas en la tabla correspondiente comunicándose con el ContentProvider.
     * @param listaElements nodos del XML
     * @param cr Vinculo con el ContentProvider asociado
     * @return <ul>
     *          <li>0: ofertas almacenadas</li>
     *          </ul>
     */
    int parsearXMLaBD(NodeList listaElements, ContentResolver cr){
        int i=0;
        int j=0;
        ControladorOfertasEmpleo managerEmpleos=new ControladorOfertasEmpleo();
        while(listaElements.item(i)!=null){
            Node el=listaElements.item(i);
            i++;
            NodeList atributos=el.getChildNodes();
            j=0;
            OfertasEmpleo oferta=new OfertasEmpleo();
            while(atributos.item(j)!=null){
                Node atributo=atributos.item(j);


                if(j==1){
                    managerEmpleos.escribirIdEmpleo(oferta,atributo.getTextContent());
                }
                if(j==3){
                    managerEmpleos.escribirTituloEmpleo(oferta,atributo.getTextContent());
                }
                if(j==5){
                    managerEmpleos.escribirProvinciaEmpleo(oferta,atributo.getTextContent());
                }
                if(j==7){
                    managerEmpleos.escribirFechaPubEmpleo(oferta,atributo.getTextContent());
                }
                if(j==9){
                    managerEmpleos.escribirDescripcionEmpleo(oferta,atributo.getTextContent());
                }
                if(j==13){
                    managerEmpleos.escribirFuenteEmpleo(oferta,atributo.getTextContent());
                }
                if(j==15){
                    managerEmpleos.escribirIdProvinciaEmpleo(oferta,atributo.getTextContent());
                }
                if(j==17){
                    managerEmpleos.escribirCiudadEmpleo(oferta,atributo.getTextContent());
                }
                if(j==19){
                    managerEmpleos.escribirCoordenadasEmpleo(oferta,atributo.getTextContent());
                }
                if(j==21){
                    managerEmpleos.escribirIdCiudadEmpleo(oferta,atributo.getTextContent());
                }
                if(j==25){
                    managerEmpleos.escribirActualizacionEmpleo(oferta,atributo.getTextContent());
                }
                if(j==27){
                    managerEmpleos.escribirEnlaceEmpleo(oferta,atributo.getTextContent());
                }
                j++;
            }






            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();

            values.put(DbContract.COLUMN_ID,managerEmpleos.leerIdEmpleo(null,-1,oferta,1));
            values.put(DbContract.COLUMN_TITULO,managerEmpleos.leerTituloEmpleo(null,-1,oferta,1));
            values.put(DbContract.COLUMN_PROVINCIA,managerEmpleos.leerProvinciaEmpleo(null,-1,oferta,1));
            values.put(DbContract.COLUMN_FECHA,managerEmpleos.leerFechaEmpleo(null,-1,oferta,1));
            values.put(DbContract.COLUMN_DESCRIPCION,managerEmpleos.leerDescripcionEmpleo(null,-1,oferta,1));
            values.put(DbContract.COLUMN_FUENTE,managerEmpleos.leerFuenteEmpleo(null,-1,oferta,1));
            values.put(DbContract.COLUMN_CIUDAD,managerEmpleos.leerCiudadEmpleo(null,-1,oferta,1));
            values.put(DbContract.COLUMN_COORDENADAS,managerEmpleos.leerCoordenadasEmpleo(null,-1,oferta,1));
            values.put(DbContract.COLUMN_ACTUALIZACION,managerEmpleos.leerActualizacionEmpleo(null,-1,oferta,1));
            values.put(DbContract.COLUMN_ENLACE,managerEmpleos.leerEnlaceEmpleo(null,-1,oferta,1));



            cr.insert(ClientesProvider.CONTENT_URI, values);

        }
        return 0;
    }


    @Override
    /**
     * Convierte los datos almacenados en el fichero XML de ofertas de formacion en una serie de ofertas de formacion y almacena estas en la tabla correspondiente comunicándose con el ContentProvider.
     * @param listaElements nodos del XML
     * @param cr2 Vinculo con el ContentProvider asociado
     * @return <ul>
     *          <li>0: ofertas almacenadas</li>
     *          </ul>
     */
    int parsearXMLaBDFormacion(NodeList listaElements, ContentResolver cr2) {
        int i = 0;
        int j = 0;
        ControladorOfertasEmpleo managerFormaciones=new ControladorOfertasEmpleo();
        while (listaElements.item(i) != null) {
            Node el = listaElements.item(i);
            i++;
            NodeList atributos = el.getChildNodes();
            j = 0;
            OfertasFormacion oferta = new OfertasFormacion();
            while (atributos.item(j) != null) {
                Node atributo = atributos.item(j);


                if (j == 1) {
                    managerFormaciones.escribirIdFormacion(oferta,atributo.getTextContent());
                }
                if (j == 3) {
                    managerFormaciones.escribirTituloFormacion(oferta,atributo.getTextContent());

                }
                if (j == 7) {
                    managerFormaciones.escribirCiudadFormacion(oferta,atributo.getTextContent());

                }
                if (j == 9) {
                    managerFormaciones.escribirCoordenadasFormacion(oferta,atributo.getTextContent());

                }

                if (j == 11) {
                    managerFormaciones.escribirCodProvinciaFormacion(oferta,atributo.getTextContent());

                }
                if (j == 19) {
                    managerFormaciones.escribirFechasFormacion(oferta,atributo.getTextContent());

                }
                if (j == 21) {
                    managerFormaciones.escribirOrganismoFormacion(oferta,atributo.getTextContent());

                }
                if (j == 25) {
                    managerFormaciones.escribirTipoFormacion(oferta,atributo.getTextContent());

                }
                if (j == 27) {
                    managerFormaciones.escribirDuracionFormacion(oferta,atributo.getTextContent());

                }

                if (j == 29) {
                    managerFormaciones.escribirDescripcionFormacion(oferta,atributo.getTextContent());

                }
                if (j == 31) {
                    managerFormaciones.escribirMateriaFormacion(oferta,atributo.getTextContent());

                }
                if (j == 33) {
                    managerFormaciones.escribirLugarFormacion(oferta,atributo.getTextContent());

                }
                if (j == 35) {
                    managerFormaciones.escribirInscripcionFormacion(oferta,atributo.getTextContent());

                }
                if (j == 45) {
                    managerFormaciones.escribirDestinatariosFormacion(oferta,atributo.getTextContent());

                }
                if (j == 49) {
                    managerFormaciones.escribirPlazasFormacion(oferta,atributo.getTextContent());

                }
                if (j == 51) {
                    managerFormaciones.escribirInfoFormacion(oferta,atributo.getTextContent());

                }
                if (j == 57) {
                    managerFormaciones.escribirActualizacionFormacion(oferta,atributo.getTextContent());

                }
                if (j == 59) {
                    managerFormaciones.escribirEnlaceFormacion(oferta,atributo.getTextContent());

                }
                j++;
            }


            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();

            values.put(Estructura_Formacion.COLUMN_ID, managerFormaciones.leerIdFormacion(null,-1,oferta,1));
            values.put(Estructura_Formacion.COLUMN_TITULO, managerFormaciones.leerTituloFormacion2(null,-1,oferta,1));
            values.put(Estructura_Formacion.COLUMN_CIUDAD, managerFormaciones.leerCiudadFormacion(null,-1,oferta,1));
            values.put(Estructura_Formacion.COLUMN_COORDENADAS, managerFormaciones.leerCoordenadasFormacion(null,-1,oferta,1));
            values.put(Estructura_Formacion.COLUMN_FECHAS, managerFormaciones.leerFechasFormacion2(null,-1,oferta,1));
            values.put(Estructura_Formacion.COLUMN_ORGANISMO, managerFormaciones.leerOrganismoFormacion(null,-1,oferta,1));
            values.put(Estructura_Formacion.COLUMN_TIPO,managerFormaciones.leerTipoFormacion(null,-1,oferta,1));
            values.put(Estructura_Formacion.COLUMN_DURACION,managerFormaciones.leerDuracionFormacion(null,-1,oferta,1));
            values.put(Estructura_Formacion.COLUMN_DESCRIPCION, managerFormaciones.leerDescripcionFormacion(null,-1,oferta,1));
            values.put(Estructura_Formacion.COLUMN_MATERIA,managerFormaciones.leerMateriaFormacion(null,-1,oferta,1));
            values.put(Estructura_Formacion.COLUMN_DESTINATARIOS, managerFormaciones.leerDestinatariosFormacion(null,-1,oferta,1));
            values.put(Estructura_Formacion.COLUMN_LUGAR, managerFormaciones.leerLugarFormacion(null,-1,oferta,1));
            values.put(Estructura_Formacion.COLUMN_INSCRIPCION, managerFormaciones.leerInscripcionFormacion(null,-1,oferta,1));
            values.put(Estructura_Formacion.COLUMN_PLAZAS, managerFormaciones.leerPlazasFormacion(null,-1,oferta,1));
            values.put(Estructura_Formacion.COLUMN_INFOADICIONAL, managerFormaciones.leerInfoFormacion(null,-1,oferta,1));
            values.put(Estructura_Formacion.COLUMN_ACTUALIZACION, managerFormaciones.leerActualizacionFormacion(null,-1,oferta,1));
            values.put(Estructura_Formacion.COLUMN_ENLACE, managerFormaciones.leerEnlaceFormacion(null,-1,oferta,1));
            values.put(Estructura_Formacion.COLUMN_ID_PROVINCIA, managerFormaciones.leerCodProvinciaFormacion(null,-1,oferta,1));


            Log.e("14MARZO",oferta.getTitulo()+oferta.getActualizacion());


            cr2.insert(FormacionProvider.CONTENT_URI, values);
        }
        return 0;
    }
    /**
     * Comprueba la existencia del fichero pasado como argumento en ruta en el sistema.
     * @param ruta ruta al fichero
     * @param c contexto desde el que se invoca el metodo
     * @return <ul>
     *          <li>true: existe el fichero</li>
     *          <li>false: no existe el fichero</li>
     *          </ul>
     */
    boolean existeFichero(Context c,String ruta){
        File archivo = new File(ruta);
        return archivo.exists();
    }
    /**
     * Recolecta los gestores de correo disponibles en el dispositivo dando al usuario la opción de elegir uno. Una vez elegido lo abre estableciendo un destinatario, un asunto y un cuerpo de correo predeterminado.
     * @param c contexto desde el que se invoca el metodo
     */
    public void mandarCorreo(Context c,View view){
        ControladorFormateador formateadorCadenas=new ControladorFormateador();
        String cuerpoCorreo=formateadorCadenas.generarCuerpoCorreo(c);


        String email=c.getString(R.string.correo_contacto);
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/html");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, c.getString(R.string.subject_correo));
        emailIntent.putExtra(Intent.EXTRA_TEXT,cuerpoCorreo);

        try {
            view.getContext().startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            ControladorAvisos managerAvisos=new ControladorAvisos();
            managerAvisos.avisosToast(c.getString(R.string.sinclientecorreo),c);
        }
    }
    /**
     * Comprueba si el sistema de geolocalización se encuentra activo en el dispositivo.
     * @param c contexto desde el que se invoca el metodo
     * @return <ul>
     *          <li>true: la localizacion esta activa</li>
     *          <li>false: la localizacion no esta activa< </li>
     *          </ul>
     */
    public boolean localizacionActiva(Context c){
        String provider = Settings.Secure.getString(c.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if (provider.contains("gps") || provider.contains("network")){
            return true;
        }
        return false;
    }
}
