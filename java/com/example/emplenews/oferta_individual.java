package com.example.emplenews;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
/**
 * Clase oferta_individual
 *
 * Actividad asociada a la pantalla que muestra el contenido de una oferta de empleo.
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class oferta_individual extends AppCompatActivity {
    /**
     * Oferta recuperada en la búsqueda y seleccionada por el usuario cuyo contenido se mostrará en pantalla
     */
    private OfertasEmpleo ofertaAMostrar;
    /**
     * Imagen asociada a la entidad fuente de la oferta
     */
    ImageView fuente;
    TextView titulo,lugar,fecha,descripcion,irContenido,compatirText;
    /**
     * Botón que lanza la acción de guardar una oferta en la tabla de favoritas
     */
    ImageButton btnFavorito;
    /**
     * Botón que lanza la acción de borrar una oferta de la tabla de favoritas
     */
    ImageButton btnBorrar;
    ContentResolver cr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta_individual);

        final ControladorBD managerBD=new ControladorBD();
        final ControladorAvisos managerAvisos=new ControladorAvisos();

        ofertaAMostrar=(OfertasEmpleo)getIntent().getSerializableExtra("oferta");

        btnFavorito=(ImageButton)findViewById(R.id.botonFavorito);
        btnBorrar=(ImageButton)findViewById(R.id.botonBorrar);

        fuente=(ImageView)findViewById(R.id.fuenteImg);
        titulo=(TextView)findViewById(R.id.tituloText);
        lugar=(TextView)findViewById(R.id.lugarText);
        fecha=(TextView)findViewById(R.id.fechaText);
        descripcion=(TextView)findViewById(R.id.descripcionText);
        descripcion.setMovementMethod(LinkMovementMethod.getInstance());

        irContenido=(TextView)findViewById(R.id.irContenido);

        irContenido.setMovementMethod(LinkMovementMethod.getInstance());

        compatirText=(TextView)findViewById(R.id.compartirText);

        compatirText.setMovementMethod(LinkMovementMethod.getInstance());

        ControladorFormateador formateadorCadenas=new ControladorFormateador();
        String cadenaCompartir=formateadorCadenas.generarEnlace(getString(R.string.menu_compartir));

        compatirText.setText(Html.fromHtml(cadenaCompartir));


        titulo.setText(ofertaAMostrar.getTitulo());
        String resultado= formateadorCadenas.generarLugar(ofertaAMostrar.getCiudad(),ofertaAMostrar.getProvincia());
        lugar.setText(resultado);
        String resultado2=formateadorCadenas.generarFecha(ofertaAMostrar.getFecha_publicacion(),null,3);
        fecha.setText("Publicado el: "+resultado2);

        descripcion.setText(Html.fromHtml(ofertaAMostrar.getDescripcion()));



        fuente.setImageResource(R.drawable.logo_eures);

        String fuenteOferta=formateadorCadenas.limpiarCadena(ofertaAMostrar.getFuente());


        if (formateadorCadenas.coincideCadenaConExpReg(fuenteOferta,"(?i:.*ECYL.*)")) {
            fuente.setImageResource(R.drawable.unnamed);
            Log.e("FUENTE","ECYL "+fuenteOferta);
        } else if (formateadorCadenas.coincideCadenaConExpReg(fuenteOferta,"(?i:.*EURES.*)")) {
            fuente.setImageResource(R.drawable.logo_eures);
            Log.e("FUENTE","EURES "+fuenteOferta);
        } else {
            fuente.setImageResource(R.drawable.internet2);
            Log.e("FUENTE","internet "+fuenteOferta);
        }

        String irAContenido="<a href=\""+ofertaAMostrar.getEnlace()+"\">"+getString(R.string.iracontenido)+"</a>";
        irContenido.setText(Html.fromHtml(irAContenido));

        cr = getContentResolver();

        if((managerBD.buscarRegistroBD(ofertaAMostrar.getId(),getApplicationContext(),3).getCount())>0){
            btnFavorito.setEnabled(false);
            btnFavorito.setVisibility(View.INVISIBLE);
            btnBorrar.setEnabled(true);
            btnBorrar.setVisibility(View.VISIBLE);
        }
        else{
            btnFavorito.setEnabled(true);
            btnFavorito.setVisibility(View.VISIBLE);
            btnBorrar.setEnabled(false);
            btnBorrar.setVisibility(View.INVISIBLE);
        }



        btnFavorito.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri test=managerBD.insertarEmpleoEnFavoritos(ofertaAMostrar,getApplicationContext());
                if(test==null){
                    managerAvisos.avisosToast(getString(R.string.guardadofallido),getApplicationContext());
                }
                else{
                    btnFavorito.setEnabled(false);
                    btnFavorito.setVisibility(View.INVISIBLE);
                    btnBorrar.setEnabled(true);
                    btnBorrar.setVisibility(View.VISIBLE);
                    managerAvisos.avisosToast(getString(R.string.guardadoexitoso),getApplicationContext());
                }

            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                managerBD.borrarRegistroBD(ofertaAMostrar.getId(),getApplicationContext(),3);
                managerAvisos.avisosToast(getString(R.string.eliminadoexistoso),getApplicationContext());
                btnFavorito.setEnabled(true);
                btnFavorito.setVisibility(View.VISIBLE);
                btnBorrar.setEnabled(false);
                btnBorrar.setVisibility(View.INVISIBLE);
            }
        });

    }
    /**
     * Inicia una nueva actividad donde se compartirá mediante algún medio el enlace al contenido web de la oferta
     * @param view vista de referencia
     */
    public void compartirOferta(View view){
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        String mensaje = "Mira esta oferta que te podría interesar: "+ofertaAMostrar.getTitulo();
        String enlace = ofertaAMostrar.getEnlace();
        share.putExtra(Intent.EXTRA_SUBJECT,mensaje);
        share.putExtra(Intent.EXTRA_TEXT,enlace);
        startActivity(Intent.createChooser(share,getString(R.string.compartirvia)));

    }
}
