package com.example.emplenews;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Clase FavoritosFragment
 *
 * Actividad asociada a la pantalla “Favoritos” de la aplicación.
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class FavoritosFragment extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    /**
     * Contenedor del resto de elementos de la interfaz
     */
    private DrawerLayout drawer;

    ContentResolver cr;
    ContentResolver crCursos;
    /**
     * Lista de ofertas favoritas de formación mandada por el controlador y enviada a una clase que adapta su contenido para mostrarlo por pantalla
     */
    ArrayList<OfertasFormacion>ofertasFormacion;
    /**
     * Lista de ofertas favoritas de empleo mandada por el controlador y enviada a una clase que adapta su contenido para mostrarlo por pantalla
     */
    ArrayList<OfertasEmpleo> ofers;
    /**
     * Clase adaptadora que permite modelar el contenido de la lista de ofertas de empleo para visualizarlas con el formato deseado en pantalla.
     */
    private AdaptadorResultados adapter;
    /**
     * Clase adaptadora que permite modelar el contenido de la lista de ofertas de formación para visualizarlas con el formato deseado en pantalla.
     */
    private AdaptadorResultadosFormacion adapterCursos;
    /**
     * Contenedor donde se mostrarán las ofertas de empleo favoritas
     */
    ListView listaView;
    /**
     * Contenedor donde se mostrarán las ofertas de formación favoritas
     */
    ListView listaViewCursos;
    /**
     * Pestañas separadoras de la visualización de los cursos y los empleos favoritos
     */
    TabHost tabHost;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setItemIconTintList(null);

        listaView=(ListView)findViewById(R.id.listaOfertas);
        listaViewCursos=(ListView)findViewById(R.id.listaCursos);
        tabHost=(TabHost)findViewById(R.id.tabhost);
        tabHost.setup();
        TabHost.TabSpec tabSpec1= tabHost.newTabSpec(getString(R.string.empleo));
        tabSpec1.setIndicator(getString(R.string.empleo));
        tabSpec1.setContent(R.id.layout_lista_ofertas);
        tabHost.addTab(tabSpec1);

        tabHost.setup();
        TabHost.TabSpec tabSpec2= tabHost.newTabSpec(getString(R.string.cursos));
        tabSpec2.setIndicator(getString(R.string.cursos));
        tabSpec2.setContent(R.id.layout_lista_cursos);
        tabHost.addTab(tabSpec2);
        ControladorBD managerBD=new ControladorBD();
        ControladorOfertasEmpleo managerOfertas=new ControladorOfertasEmpleo();
        ofers=managerBD.mostrarFavoritos(1,getApplicationContext());
        ofertasFormacion=managerBD.mostrarFavoritos(2,getApplicationContext());
        if(!(managerOfertas.listaEmpleosNula(ofers)) && managerOfertas.tamListaEmpleos(ofers)>0){
            adapter = new AdaptadorResultados(this, ofers);
            listaView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            listaView.setOnItemClickListener(adapter);
        }
        if(!(managerOfertas.listaFormacionesNula(ofertasFormacion)) && managerOfertas.tamListaFormaciones(ofertasFormacion)>0){
            adapterCursos = new AdaptadorResultadosFormacion(this, ofertasFormacion);
            listaViewCursos.setAdapter(adapterCursos);
            adapterCursos.notifyDataSetChanged();
            listaViewCursos.setOnItemClickListener(adapterCursos);
        }

    }
    /**
     * Carga de nuevo la vista si se vuelve a ella sin haber sido destruida
     * Analoga explicacion en el resto de clases, por ello no se documentan
     */
    public void onResume(){
        super.onResume();
        ControladorBD managerBD=new ControladorBD();
        managerBD.mostrarFavoritos(1,getApplicationContext());
        managerBD.mostrarFavoritos(2,getApplicationContext());
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

        ControladorAvisos managerAvisos=new ControladorAvisos();

        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent=new Intent(getApplicationContext(),Inicio.class);
            startActivity(intent);
        } else if (id == R.id.nav_ofertas) {
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_formacion) {
            Intent intent=new Intent(getApplicationContext(),Formacion.class);
            startActivity(intent);

        } else if (id == R.id.nav_config) {
            Intent intent=new Intent(getApplicationContext(),ConfiguracionFragment.class);
            startActivity(intent);
            return true;

        } else if (id == R.id.nav_favs) {


        } else if (id == R.id.nav_about) {
            Intent intent=new Intent(getApplicationContext(),AboutFragment.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.nav_share) {
            managerAvisos.avisosToast(getString(R.string.menu_compartir),getApplicationContext());
        }

        drawer.closeDrawers();
        return true;
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

}
