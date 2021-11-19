package com.example.emplenews;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Clase Configuracion
 *
 * Gerente de la vista asociada a la pantalla de configuracion del usuario
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class ConfiguracionFragment extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    /**
     * Contenedor del resto de elementos de la interfaz
     */
    private DrawerLayout drawer;
    /**
     * Botón pulsado cuando el usuario desea guardar sus preferencias
     */
    ImageButton botonGuardar;
    /**
     * Botón pulsado por el usuario cuando desea información acerca de la pantalla
     */
    ImageButton botonAyuda;
    /**
     * Desplegable con las provincias que el usuario puede elegir como predeterminadas
     */
    Spinner provinciaSeleccionada;
    /**
     * Desplegable con los ámbitos de acción formativa que el usuario puede elegir como predeterminados.
     */
    Spinner materiaSeleccionada;

    /**
     * Inicializa el contenido de la vista así como los eventos sobre los botones. Llamar al controlador de preferencias en el caso de la opción de guardado, mostrar un mensaje de ayuda en el caso del botón de ayuda.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
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



        provinciaSeleccionada= (Spinner)findViewById(R.id.sp_provincias);
        materiaSeleccionada=(Spinner)findViewById(R.id.sp_familias);

        botonAyuda=(ImageButton)findViewById(R.id.boton_ayuda);
        botonGuardar=(ImageButton)findViewById(R.id.boton_guardar);

        botonAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControladorAvisos managerAvisos=new ControladorAvisos();
                managerAvisos.avisosToast(getString(R.string.ayudaConfiguracion),getApplicationContext());
                managerAvisos.avisosToast(getString(R.string.ayudaConfiguracion),getApplicationContext());
                managerAvisos.avisosToast(getString(R.string.ayudaConfiguracion),getApplicationContext());
                managerAvisos.avisosToast(getString(R.string.ayudaConfiguracion),getApplicationContext());
                managerAvisos.avisosToast(getString(R.string.ayudaConfiguracion),getApplicationContext());
                managerAvisos.avisosToast(getString(R.string.ayudaConfiguracion),getApplicationContext());
            }
        });

        botonGuardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    ManejadorBotones handleBotones=new ManejadorBotones();

                    handleBotones.onSaveConfigClick(provinciaSeleccionada.getSelectedItem(),materiaSeleccionada.getSelectedItem(),getSharedPreferences("configuracionusuario",
                            Context.MODE_PRIVATE));

                ControladorAvisos managerAvisos=new ControladorAvisos();
                managerAvisos.avisosToast(getString(R.string.guardadoexitoso),getApplicationContext());
            }
        });
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
    /**
     * Navega a una actividad en función de la opción del menú lateral pulsada. En el resto de vistas resulta analogo por lo que no se documenta
     * @return <ul>
     *          <li>true: exito en la navegacion</li>
     *          </ul>
     */
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
            Intent intent=new Intent(getApplicationContext(),Formacion.class);
            startActivity(intent);

        } else if (id == R.id.nav_config) {


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




    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }




    }
