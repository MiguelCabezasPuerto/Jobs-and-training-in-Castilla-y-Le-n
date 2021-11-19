package com.example.emplenews;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;
/**
 * Clase ControladorAvisos
 *
 * Controlador encargado de formnatear los avisos lanzados en la aplicacion
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class ControladorAvisos extends Utils {
    /**
     * Genera una nueva instancia del controlador.
     */
    public ControladorAvisos(){}
    /**
     * Lanza el mensaje pasado como parámetro en el contexto de la vista que invoca el método a través de una ventana emergente situada en el centro de la pantalla durante unos segundos.
     * @param mensaje mensaje a mostrar
     * @param c contexto desde el que se invoca al metodo
     */
    public void avisosToast(String mensaje, Context c){
        Toast msg= Toast.makeText(c,mensaje,Toast.LENGTH_LONG);
        msg.setGravity(Gravity.CENTER, 0, 0);
        msg.show();
    }

}
