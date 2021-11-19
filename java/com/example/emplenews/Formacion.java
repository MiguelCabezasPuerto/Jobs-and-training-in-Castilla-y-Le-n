package com.example.emplenews;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Locale;
/**
 * Clase Formacion
 *
 * Actividad asociada a la pantalla “Formación”, búsqueda de ofertas formativas, de la aplicación.
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class Formacion extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private AppBarConfiguration mAppBarConfiguration;
    /**
     * Constante que contiene la palabra homónima en diferentes idiomas
     */
    private static String CURSOS;
    /**
     * Contenedor del resto de elementos de la interfaz
     */
    private DrawerLayout drawer;

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
     * Desplegable con los ámbitos de acción formativa que el usuario puede elegir filtro de búsqueda
     */
    Spinner materiaSeleccionada;
    /**
     * Contenedor donde se mostrarán las ofertas de formación
     */
    ListView listaView;
    /**
     * Caja de texto que recoge el nº total de ofertas recuperadas en la búsqueda
     */
    TextView cantidadOfertas;
    SQLiteDatabase db;
    /**
     * Clase adaptadora que permite modelar el contenido de la lista de ofertas de formación para visualizarlas con el formato deseado en pantalla.
     */
    private AdaptadorResultadosFormacion adapter;
    /**
     * Lista de ofertas de formación mandada por el controlador y enviada a una clase que adapta su contenido para mostrarlo por pantalla
     */
    ArrayList<OfertasFormacion> ofers;
    /**
     * Indica si existen o no acciones formativas almacenadas
     */
    boolean conFormaciones;
    /**
     * Barra superior de la aplicación que contiene el menú lateral desplegable y el nombre de la aplicación
     */
    Toolbar toolbar;

    ControladorPreferencias managerPreferencias;

    ControladorOfertasEmpleo managerFormacion;
    /**
     * Contenedor del resto de elementos de la interfaz
     */
    int flag;
    /**
     * Caja de texto donde el usuario puede introducir su búsqueda libre de acciones formativas
     */
    EditText autoformacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formacion);
        CURSOS=getString(R.string.numCursos);
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
        materiaSeleccionada=(Spinner)findViewById(R.id.spFuentes);
        listaView=(ListView)findViewById(R.id.listaOfertas);
        cantidadOfertas=(TextView)findViewById(R.id.textNumOfertas);


        conFormaciones=getIntent().getBooleanExtra("conFormaciones",false);
        flag=getIntent().getIntExtra("flag",0);
        Log.e("FLAG",""+flag);

        managerPreferencias=new ControladorPreferencias();
        managerFormacion=new ControladorOfertasEmpleo();



        if(flag==1){
            managerPreferencias.escribirEnPreferencias(getApplicationContext(),conFormaciones,2);
            Log.e("FORMACIONCONFORMACIONES",""+conFormaciones);
        }



        ofers=null;
        botonBuscar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ControladorAvisos managerAvisos=new ControladorAvisos();
                if(conFormaciones){
                    ofers= managerFormacion.hacerBusquedaFormaciones(provinciaSeleccionada,materiaSeleccionada,getApplicationContext(),autoformacion.getText().toString());
                    adapter = new AdaptadorResultadosFormacion(Formacion.this, ofers);
                    listaView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    listaView.setOnItemClickListener(adapter);
                    String textOfertas= ofers.size()+ " "+CURSOS;
                    cantidadOfertas.setText(textOfertas);

                    if(managerFormacion.listaFormacionesNula(ofers) || managerFormacion.tamListaFormaciones(ofers)==0){
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

        autoformacion=(EditText)findViewById(R.id.autocompleteFormacion);


    }

    public void onResume(){
        super.onResume();
        ControladorPreferencias managerPreferencias=new ControladorPreferencias();
        Log.e("FORMACION","En OnResume()");
        conFormaciones = managerPreferencias.leerDePreferencias(getApplicationContext(),2);
        Log.e("FLAGFORMACION",""+conFormaciones);
        CURSOS=getString(R.string.numCursos);
    }
    /**
     * Oculta el teclado una vez el usuario pulse cualquier parte de la pantalla
     * @param v vista referencia
     */
    public void quitarTeclado(View v){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(autoformacion.getWindowToken (), 0);
    }
    /**
     * Oculta el teclado una vez el usuario pulse cualquier parte de la pantalla
     */
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
        ControladorOfertasEmpleo managerFormacion=new ControladorOfertasEmpleo();
        ControladorAvisos managerAvisos=new ControladorAvisos();
        if(managerFormacion.listaFormacionesNula(ofers)){
            managerAvisos.avisosToast(getString(R.string.sinofertas),getApplicationContext());
        }
        else{
            Intent intent=new Intent(getApplicationContext(),MapaFormaciones.class);
            String provincia_seleccionada=provinciaSeleccionada.getSelectedItem().toString();
            String materia_seleccionada=materiaSeleccionada.getSelectedItem().toString();
            String busquedaLibre=autoformacion.getText().toString();
            intent.putExtra("selection",provincia_seleccionada);
            intent.putExtra("materia",materia_seleccionada);
            intent.putExtra("busquedalibre",busquedaLibre);
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
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_formacion) {


        } else if (id == R.id.nav_config) {
            Intent intent=new Intent(getApplicationContext(),ConfiguracionFragment.class);
            startActivity(intent);

        } else if (id == R.id.nav_favs) {
            Intent intent=new Intent(getApplicationContext(),FavoritosFragment.class);
            startActivity(intent);

        } else if (id == R.id.nav_about) {
            Intent intent=new Intent(getApplicationContext(),AboutFragment.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_share) {
            managerAvisos.avisosToast(getString(R.string.menu_compartir),getApplicationContext());
        }

        drawer.closeDrawers();
        return true;
    }
}
