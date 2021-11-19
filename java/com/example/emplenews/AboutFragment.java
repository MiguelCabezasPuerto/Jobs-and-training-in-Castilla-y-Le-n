package com.example.emplenews;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.io.InputStream;


/**
 * Clase About
 *
 * Actividad gerente de la vista asociada a la pantalla Acerca de
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class AboutFragment extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    /**
     * Contenedor del resto de elementos de la interfaz
     */
    private DrawerLayout drawer;
    /**
     * Caja de texto con el correo de contacto
     */
    TextView correoContacto;

    /**
     * Inicializa el contenido de la vista
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
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
        correoContacto=(TextView)findViewById(R.id.correo_contacto);
        correoContacto.setMovementMethod(LinkMovementMethod.getInstance());

        ControladorFormateador formateadorCadenas=new ControladorFormateador();

        String cadenaCorreo=formateadorCadenas.generarEnlace(getString(R.string.correo_contacto));
        correoContacto.setText(Html.fromHtml(cadenaCorreo));
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
    /**
     * Navega a una actividad en función de la opción del menú lateral pulsada
     * @param item Item de menu seleccionado
     */
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        ControladorAvisos managerAvisos=new ControladorAvisos();

        if (id == R.id.nav_home) {

            Intent intent=new Intent(getApplicationContext(),Inicio.class);
            startActivity(intent);

            return true;
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
            Intent intent=new Intent(getApplicationContext(),FavoritosFragment.class);
            startActivity(intent);
            return true;


        } else if (id == R.id.nav_about) {

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
    /**
     * Cuando el hipervinculo del correo es pulsado, se comunica con el controlador de conexiones para el envío de un correo.
     * @param view Vista desde la que se invoca el metodo
     */
    public void mandarCorreo(View view){
        ControladorConexionesFicheros managerConexiones=new ControladorConexionesFicheros();
        managerConexiones.mandarCorreo(getApplicationContext(),view);
    }

}
