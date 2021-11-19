package com.example.emplenews;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
/**
 * Clase Inicio
 *
 * Actividad asociada a la pantalla “Empleos”, búsqueda de ofertas de empleo, de la aplicación.
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    /**
     * Contenedor del resto de elementos de la interfaz
     */
    private DrawerLayout drawer;
    /**
     * Constante que contiene la palabra “empleos” en diferentes idiomas
     */
    private static String OFERTAS;

    ContentResolver cr;
    /**
     * Botón para lanzar la búsqueda de ofertas con los filtros aplicados
     */
    Button botonBuscar;
    /**
     * Botón para lanzar la visualización de las ofertas filtradas en un mapa
     */
    ImageButton botonMapa;
    /**
     * Desplegable con las provincias que el usuario puede elegir filtro de búsqueda
     */
    Spinner provinciaSeleccionada;
    /**
     * Contenedor donde se mostrarán las ofertas de empleo
     */
    ListView listaView;
    /**
     * Caja de texto que recoge el nº total de ofertas recuperadas en la búsqueda
     */
    TextView cantidadOfertas;
    SQLiteDatabase db;
    /**
     * Clase adaptadora que permite modelar el contenido de la lista de ofertas de empleo para visualizarlas con el formato deseado en pantalla.
     */
    private AdaptadorResultados adapter;
    /**
     * Lista de ofertas de empleo mandada por el controlador y enviada a una clase que adapta su contenido para mostrarlo por pantalla
     */
    ArrayList<OfertasEmpleo> ofers;
    /**
     * Variable que sirve para comprobar si se reciben o no ofertas de la pantalla de carga
     */
    boolean conResultados;
    /**
     * Barra superior de la aplicación que contiene el menú lateral desplegable y el nombre de la aplicación
     */
     Toolbar toolbar;
    /**
     * Caja de texto donde el usuario puede introducir su búsqueda libre de empleo
     */
     EditText autoEmpleos;

     int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OFERTAS=getString(R.string.numOfertas);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setItemIconTintList(null);

        botonBuscar=(Button)findViewById(R.id.botonBuscar);
        botonMapa=(ImageButton)findViewById(R.id.botonMapa);
        provinciaSeleccionada=(Spinner)findViewById(R.id.spProvincias);
        listaView=(ListView)findViewById(R.id.listaOfertas);
        cantidadOfertas=(TextView)findViewById(R.id.textNumOfertas);


        conResultados=getIntent().getBooleanExtra("conResultados",false);
        flag=getIntent().getIntExtra("flag",0);
        Log.e("FLAG",""+flag);
        ControladorPreferencias managerPreferencias=new ControladorPreferencias();

        if(flag==1){
            managerPreferencias.escribirEnPreferencias(getApplication(),conResultados,1);
        }

        autoEmpleos=(EditText)findViewById(R.id.autocompleteEmpleo);




        ofers=null;
        final ControladorOfertasEmpleo managerEmpleos=new ControladorOfertasEmpleo();

        botonBuscar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ControladorAvisos managerAvisos=new ControladorAvisos();
                if(conResultados){
                    int numRegistros=-1;
                    ofers=managerEmpleos.hacerBusquedaOfertas(provinciaSeleccionada.getSelectedItem().toString(),autoEmpleos.getText().toString(),getApplicationContext(),numRegistros);
                    if(managerEmpleos.listaEmpleosNula(ofers)){
                        botonMapa.setEnabled(false);
                        botonMapa.setColorFilter(Color.parseColor("#9E9E9E"));
                        managerAvisos.avisosToast(getString(R.string.sindatos),getApplicationContext());
                    }
                    adapter = new AdaptadorResultados(MainActivity.this, ofers);
                    listaView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    listaView.setOnItemClickListener(adapter);
                    String textOfertas= ofers.size()+" "+OFERTAS;
                    cantidadOfertas.setText(textOfertas);
                    if(managerEmpleos.listaEmpleosVacia(ofers)){
                        botonMapa.setEnabled(false);
                        botonMapa.setColorFilter(Color.parseColor("#9E9E9E"));
                    }
                    else{
                        botonMapa.setEnabled(true);
                        botonMapa.clearColorFilter();
                    }
                    ocultarTeclado();
                }

                else
                    managerAvisos.avisosToast(getString(R.string.sindatos),getApplicationContext());

            }
        });
        botonMapa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mostrarEnMapa();
            }
        });
        //conResultados=false;
        Log.e("Paso por aqui", "onCreate");

        String idioma;
        SharedPreferences prefs2=getSharedPreferences("configuracionusuario",Context.MODE_PRIVATE);
        idioma=prefs2.getString("Idioma",getString(R.string.espana));
        Locale localizacion;
        if(idioma.equalsIgnoreCase("Español") ||idioma.equalsIgnoreCase("Spanish") || idioma.equalsIgnoreCase("Espagnol") ){
            localizacion = new Locale("es", "ES");
        }else if(idioma.equalsIgnoreCase("Inglés") ||idioma.equalsIgnoreCase("English") || idioma.equalsIgnoreCase("Anglais") ){
            localizacion = new Locale("en", "US");
        }else{
            localizacion = new Locale("fr", "FR");
        }
        Log.e("IDIOMA EN MAIN",localizacion.getLanguage());
        Locale.setDefault(localizacion);
        Configuration config = new Configuration();
        config.locale = localizacion;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

    public void onResume(){
        super.onResume();
        ControladorPreferencias managerPreferencias=new ControladorPreferencias();
        Log.e("MAIN","En OnResume()");

        conResultados = managerPreferencias.leerDePreferencias(getApplicationContext(),1);
        Log.e("FLAG",""+conResultados);
        OFERTAS=getString(R.string.numOfertas);
    }


    public void quitarTeclado(View v){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(autoEmpleos.getWindowToken (), 0);
    }

    public void ocultarTeclado(){
            View view=this.getCurrentFocus();
            if(view!=null){
                InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
            }
    }


    /**
     * Lanza una nueva actividad que muestre las ofertas filtradas en un mapa una vez el usuario pulsa el botón con el signo del mapa
     */
    public void mostrarEnMapa(){
        ControladorAvisos managerAvisos=new ControladorAvisos();
        ControladorOfertasEmpleo managerEmpleos=new ControladorOfertasEmpleo();
        if(managerEmpleos.listaEmpleosNula(ofers)){
            managerAvisos.avisosToast(getString(R.string.sinofertas),getApplicationContext());
        }
        else{
            Intent intent=new Intent(getApplicationContext(),Mapa.class);
            String provincia_seleccionada=provinciaSeleccionada.getSelectedItem().toString();
            intent.putExtra("selection",provincia_seleccionada);
            intent.putExtra("busquedalibre",autoEmpleos.getText().toString());
            startActivity(intent);
        }
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        ControladorAvisos managerAvisos=new ControladorAvisos();

        if (id == R.id.nav_home) {
            Intent intent=new Intent(getApplicationContext(),Inicio.class);
            startActivity(intent);
        } else if (id == R.id.nav_ofertas) {


        } else if (id == R.id.nav_formacion) {
            Intent intent=new Intent(getApplicationContext(),Formacion.class);
            startActivity(intent);

        } else if (id == R.id.nav_config) {
            Intent intent=new Intent(getApplicationContext(),ConfiguracionFragment.class);
            startActivity(intent);

            return true;


        } else if (id == R.id.nav_favs) {
            Intent intent=new Intent(getApplicationContext(),FavoritosFragment.class);
            startActivity(intent);

        } else if (id == R.id.nav_about) {
            Intent intent=new Intent(getApplicationContext(),AboutFragment.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_share) {
            managerAvisos.avisosToast(getString(R.string.sindatos),getApplicationContext());
        }

        drawer.closeDrawers();
        return true;
    }




}
