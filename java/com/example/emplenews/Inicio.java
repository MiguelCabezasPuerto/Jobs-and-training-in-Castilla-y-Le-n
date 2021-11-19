package com.example.emplenews;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import java.util.Locale;

/**
 * Clase Inicio
 *
 * Actividad asociada a la pantalla inicial de la aplicación posterior a la carga de datos.
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class Inicio extends AppCompatActivity {
    /**
     * Variable indicativa de si se han cargado o no ofertas de empleo en la actividad de carga. Desde esta se manda un verdadero en caso de carga correcta y un falso en caso de carga incorrecta
     */
    boolean conResultados;
    /**
     * Variable indicativa de si se han cargado o no ofertas de formación en la actividad de carga. Desde esta se manda un verdadero en caso de carga correcta y un falso en caso de carga incorrecta
     */
    boolean conFormaciones;
    /**
     * Variable que indica si a la actividad se accede desde la pantalla de carga (con lo que habría que escribir en SharedPreferences) si se han cargado o no datos, o si por el contrario se ha accedido a ella desde cualquier otra actividad de la aplicación
     */
    int flag;
    /**
     * Caja de texto contenedora del nº de empleos recuperados de la base de datos
     */
    TextView numEmpleos;
    /**
     * Caja de texto contenedora del nº de favoritos recuperados de la base de datos
     */
    TextView numFavoritos;
    /**
     * Caja de texto contenedora del nº de acciones formativas recuperadas de la base de datos
     */
    TextView numFormaciones;
    ContentResolver cr1,cr2,cr3,cr4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        ControladorBD managerBD=new ControladorBD();
        ControladorPreferencias managerPreferencias=new ControladorPreferencias();






        conResultados=getIntent().getBooleanExtra("conResultados",false);
        conFormaciones=getIntent().getBooleanExtra("conFormaciones",false);
        flag=getIntent().getIntExtra("flag",0);

        if(flag==1){

            managerPreferencias.escribirEnPreferencias(getApplicationContext(),conResultados,1);
            managerPreferencias.escribirEnPreferencias(getApplicationContext(),conFormaciones,2);
            Log.e("INICIO CON FORMACIONES",""+conFormaciones);
            Log.e("INICIO CON EMPLEOS",""+conResultados);
        }

        numEmpleos=(TextView)findViewById(R.id.numOfertasEmpleo);
        numFavoritos=(TextView)findViewById(R.id.numFavoritos);
        numFormaciones=(TextView)findViewById(R.id.numOfertasFormacion);

        String cantidadEmpleos=managerBD.numRegistrosEmpleos(getApplicationContext());
        String cantidadFormaciones=managerBD.numRegistrosFormacion(getApplicationContext());

        String cantidadTotalFavoritos=managerBD.numRegistrosFavoritos(getApplicationContext());

        numFavoritos.setText(cantidadTotalFavoritos);
        numEmpleos.setText(cantidadEmpleos);
        numFormaciones.setText(cantidadFormaciones);



    }

    public void onResume(){
        super.onResume();
        ControladorPreferencias managerPreferencias=new ControladorPreferencias();
        conResultados = managerPreferencias.leerDePreferencias(getApplicationContext(),1);
        conFormaciones = managerPreferencias.leerDePreferencias(getApplicationContext(),2);
        Log.e("INICIO CON FORMACIONES",""+conFormaciones);
    }
    /**
     * Navega a otra pantalla en función de la opción del menú elegida por el usuario
     * @param view vista referencia
     */
    public void aOpcionDeMenu(View view){
        ControladorPreferencias managerPreferencias=new ControladorPreferencias();
        Intent intent;
        if(view.getId()==R.id.imgEmpleo){
            conResultados = managerPreferencias.leerDePreferencias(getApplicationContext(),1);
            intent=new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("conResultados",conResultados);
            intent.putExtra("flag",1);
            view.getContext().startActivity(intent);


        }else if(view.getId()==R.id.imgFormacion){

            conFormaciones = managerPreferencias.leerDePreferencias(getApplicationContext(),2);

            Log.e("INICIO-FORMACIONPASO",""+conFormaciones);

            intent=new Intent(getApplicationContext(),Formacion.class);
            intent.putExtra("conFormaciones",conFormaciones);
            intent.putExtra("flag",1);
            startActivity(intent);
        }else if(view.getId()==R.id.imgConfiguracion){
            intent=new Intent(getApplicationContext(),ConfiguracionFragment.class);
            startActivity(intent);

        }else if(view.getId()==R.id.imgFavoritos){
            intent=new Intent(getApplicationContext(),FavoritosFragment.class);
            startActivity(intent);

        }else if(view.getId()==R.id.imgAbout){
            intent=new Intent(getApplicationContext(),AboutFragment.class);
            startActivity(intent);

        }else if(view.getId()==R.id.imgCompartir){
                ControladorAvisos managerAvisos=new ControladorAvisos();
                managerAvisos.avisosToast(getString(R.string.menu_compartir),getApplicationContext());
        }
    }
}
