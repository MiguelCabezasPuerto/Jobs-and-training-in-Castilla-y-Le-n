package com.example.emplenews;

import android.content.Context;
import android.os.Build;
import android.view.View;
/**
 * Clase ControladorFormateador
 *
 * Controlador encargado de dar formato y manipular las cadenas de caracteres
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class ControladorFormateador extends Utils {
    public ControladorFormateador(){}

    /**
     * Genera un enlace en formato HTML del contenido pasado como parámetro
     * @param contenido contenido del enlace
     * @return <ul>
     *          <li>String: enlace</li>
     *          </ul>
     */
    String generarEnlace(String contenido){
        String enlace="<a href=\""+"\">"+contenido+"</a>";
        return enlace;
    }
    /**
     * Genera un formato de fecha dependiendo del parámetro tipo
     * @param fecha fecha a formatear
     * @pararm view vista desde la que se llama al controlador
     * @tipo tipo de formato de fecha
     * @return <ul>
     *          <li>String: fecha con formato</li>
     *          </ul>
     */
    String generarFecha(String fecha, View view,int tipo){
        if(tipo==1){
            char[] aCaracteres = fecha.toCharArray();
            char[] aNo, mes, dia;
            aNo = new char[]{aCaracteres[1], aCaracteres[2], aCaracteres[3], aCaracteres[4]};
            mes = new char[]{aCaracteres[5], aCaracteres[6]};
            dia = new char[]{aCaracteres[7], aCaracteres[8]};
            String year, month, day;
            year = new String(aNo);
            month = new String(mes);
            day = new String(dia);
            String fechaPub = view.getContext().getString(R.string.publicadoel) + day + "/" + month + "/" + year;
            return fechaPub;
        }else if(tipo==2){
            char[] aCaracteres = fecha.toCharArray();
            char[] aNo, mes, dia;
            aNo = new char[]{aCaracteres[0], aCaracteres[1], aCaracteres[2], aCaracteres[3]};
            mes = new char[]{aCaracteres[4], aCaracteres[5]};
            dia = new char[]{aCaracteres[6], aCaracteres[7]};
            String year, month, day;
            year = new String(aNo);
            month = new String(mes);
            day = new String(dia);
            String fechaPub = view.getContext().getString(R.string.publicadoel) + day + "/" + month + "/" + year;
            return fechaPub;
        }else{
            char[]aCaracteres=fecha.toCharArray();
            char[] aNo,mes,dia;
            aNo=new char[]{aCaracteres[1],aCaracteres[2],aCaracteres[3],aCaracteres[4]};
            mes=new char[]{aCaracteres[5],aCaracteres[6]};
            dia=new char[]{aCaracteres[7],aCaracteres[8]};
            String year,month,day;
            year=new String(aNo);
            month=new String(mes);
            day=new String(dia);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(day);
            sb2.append("/");
            sb2.append(month);
            sb2.append("/");
            sb2.append(year);
            return sb2.toString();
        }

    }
    /**
     * Da un formato “ciudad(provincia)” a una cadena a partir  de los parámetros recibidos.
     * @param ciudad ciudad del lugar
     * @param provincia provincia del lugar
     * @return <ul>
     *          <li>String: lugar con formato</li>
     *          </ul>
     */
    String generarLugar(String ciudad,String provincia){
        String a = ciudad;
        String b ="(";
        String c=provincia;
        String d=")";
        StringBuilder sb = new StringBuilder();
        sb.append(a);
        sb.append(b);
        sb.append(c);
        sb.append(d);
        String temp= sb.toString();
        String lugarPub=limpiarCadena(temp);
        return lugarPub;
    }
    /**
     * Elimina de la cadena recibida saltos de línea.
     * @param cadena cadena a limpiar
     * @return <ul>
     *          <li>String: cadena sin caracteres de salto</li>
     *          </ul>
     */
    String limpiarCadena(String cadena){
        String cadenaLimpia=cadena.replaceAll("\n","");
        return cadenaLimpia;
    }
    /**
     * Elimina de la cadena recibida caracteres separadores.
     * @param cadena cadena a limpiar
     * @return <ul>
     *          <li>String: cadena sin caracteres separadores</li>
     *          </ul>
     */
    String limpiarCadenaExtremo(String cadena){
        String cadenaLimpia=cadena.replaceAll("\\s","");
        return cadenaLimpia;
    }
    /**
     * Separa la cadena recibida en diferentes subcadenas en base al caracter separador deseado.
     * @param cadena cadena a dividir
     * @param caracterSeparador separador por el que dividir la cadena
     * @return <ul>
     *          <li>String[]: cadenas resultantes de la division</li>
     *          </ul>
     */
    String[]dividirCadena(String cadena,String caracterSeparador){
        String[]array=cadena.split(caracterSeparador);
        return array;
    }
    /**
     * Comprueba si una determinada cadena casa con una determinada expresión regular
     * @param cadena cadena a comprobar
     * @param regExp expresion regular a aplicar
     * @return <ul>
     *          <li>true: coincide con la expresion regular</li>
     *          <li>false: no coincide con la expresion regular</li>
     *          </ul>
     */
    boolean coincideCadenaConExpReg(String cadena,String regExp){
        return (cadena.matches(regExp));
    }
    /**
     * Genera el cuerpo de correo por defecto enviado en un mail.
     * @param c contexto desde el que se invoca al controlador
     * @return <ul>
     *          <li>String: cuerpo con formato</li>
     *          </ul>
     */
    String generarCuerpoCorreo(Context c){
        StringBuilder sb=new StringBuilder();
        sb.append(c.getString(R.string.caracteristicas_dispositivo));
        sb.append(System.getProperty("line.separator"));
        sb.append(c.getString(R.string.versionandroid));
        sb.append(Build.VERSION.RELEASE);
        sb.append(System.getProperty("line.separator"));
        sb.append(c.getString(R.string.versionapp));
        sb.append(BuildConfig.VERSION_NAME);
        sb.append(System.getProperty("line.separator"));
        sb.append(c.getString(R.string.descripcion_problema_app));
        sb.append(System.getProperty("line.separator"));
        String cuerpoCorreo=sb.toString();
        return cuerpoCorreo;
    }
    /**
     * Genera un formato para una cadena que contenga la duración de una oferta de formación
     * @param c contexto desde el que se invoca al controlador
     * @param duracion a la que establecer formato
     * @return <ul>
     *          <li>String: duracion con formato</li>
     *          </ul>
     */
    String generarDuracion(Context c,String duracion){
        StringBuilder sb=new StringBuilder();
        sb.append(c.getString(R.string.duracion));
        sb.append(duracion);
        return limpiarCadena(sb.toString());
    }
    /**
     * Convierte una cadena pasada como parámetro al valor double que representa.
     * @param cadena cadena de la que obtener el valor numerico que representa
     * @return <ul>
     *          <li>double: valor numerico</li>
     *          </ul>
     */
    double deCadenaADouble(String cadena){
        return Double.parseDouble(cadena);
    }
    /**
     * Une dos cadenas recibidas mediante el carácter separador deseado.
     * @param s1 cadena primera recibida
     * @param s2 cadena segunda recibida
     * @param separador caracter de union
     * @return <ul>
     *          <li>String: cadena concatenada</li>
     *          </ul>
     */
    String concatenarCadenas(String s1,String s2,String separador){
        return s1+separador+s2;
    }
}
