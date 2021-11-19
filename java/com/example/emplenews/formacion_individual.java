package com.example.emplenews;

import android.content.ContentResolver;
import android.content.ContentValues;
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

import org.w3c.dom.Text;
/**
 * Clase formacion_individual
 *
 * Actividad asociada a la pantalla que muestra el contenido de una oferta de formación.
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class formacion_individual extends AppCompatActivity {
    /**
     * Oferta recuperada en la búsqueda y seleccionada por el usuario cuyo contenido se mostrará en pantalla
     */
    private OfertasFormacion ofertaAMostrar;
    /**
     * Imagen asociada a la entidad fuente de la oferta
     */
    ImageView fuente;
    TextView titulo,lugar,fecha,descripcion,irContenido,compatirText,duracion,materia;
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
        setContentView(R.layout.activity_formacion_individual);

        ofertaAMostrar=(OfertasFormacion) getIntent().getSerializableExtra("oferta");

        btnFavorito=(ImageButton)findViewById(R.id.botonFavorito);
        btnBorrar=(ImageButton)findViewById(R.id.botonBorrar);

        fuente=(ImageView)findViewById(R.id.fuenteImg);
        titulo=(TextView)findViewById(R.id.tituloText);
        lugar=(TextView)findViewById(R.id.lugarText);
        fecha=(TextView)findViewById(R.id.fechaText);
        duracion=(TextView)findViewById(R.id.duracionText);
        descripcion=(TextView)findViewById(R.id.descripcionText);
        materia=(TextView)findViewById(R.id.materiaText);

        descripcion.setMovementMethod(LinkMovementMethod.getInstance());

        irContenido=(TextView)findViewById(R.id.irContenido);

        irContenido.setMovementMethod(LinkMovementMethod.getInstance());

        compatirText=(TextView)findViewById(R.id.compartirText);

        compatirText.setMovementMethod(LinkMovementMethod.getInstance());

        String cadenaCompartir="<a href=\""+"\">"+getString(R.string.menu_compartir)+"</a>";

        compatirText.setText(Html.fromHtml(cadenaCompartir));

        ControladorFormateador formateadorCadenas=new ControladorFormateador();


        titulo.setText(formateadorCadenas.limpiarCadena(ofertaAMostrar.getTitulo()));
        materia.setText(formateadorCadenas.limpiarCadena(ofertaAMostrar.getMateria()));
        lugar.setText(formateadorCadenas.limpiarCadena(ofertaAMostrar.getCiudad()));
        fecha.setText(formateadorCadenas.limpiarCadena(ofertaAMostrar.getFechas()));
        Log.e("Fechas",ofertaAMostrar.getFechas()+"algo");
        String dur=formateadorCadenas.generarDuracion(getApplicationContext(),ofertaAMostrar.getDuracion());
        duracion.setText(dur);

        descripcion.setText(Html.fromHtml(ofertaAMostrar.getInscripcion()));


       String irAContenido="<a href=\""+ofertaAMostrar.getEnlace()+"\">"+getString(R.string.iracontenido)+"</a>";
        irContenido.setText(Html.fromHtml(irAContenido));





        ControladorFormateador formatter=new ControladorFormateador();
        ControladorOfertasEmpleo managerFormaciones= new ControladorOfertasEmpleo();

        String materia= formatter.limpiarCadenaExtremo(managerFormaciones.leerMateriaFormacion(null,-1,ofertaAMostrar,1));
        Log.e("Materia",materia);
        if(formatter.coincideCadenaConExpReg(materia,"(?i:.*deport.*)")){
            fuente.setImageResource(R.drawable.deporte);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*gest.*)")){
            fuente.setImageResource(R.drawable.admin);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*agraria.*)")){
            fuente.setImageResource(R.drawable.campo);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*artes.*)")){
            fuente.setImageResource(R.drawable.arte);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*artesan.*)")){
            fuente.setImageResource(R.drawable.ceramica);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*comercio.*)")){
            fuente.setImageResource(R.drawable.comercio);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*obra.*)")){
            fuente.setImageResource(R.drawable.obras);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*energia.*)")){
            fuente.setImageResource(R.drawable.energia);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*mecanica.*)")){
            fuente.setImageResource(R.drawable.mecanico);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*turismo.*)")){
            fuente.setImageResource(R.drawable.turismo);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*personal.*)")){
            fuente.setImageResource(R.drawable.belleza);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*sonido.*)")){
            fuente.setImageResource(R.drawable.sonido);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*alimentaria.*)")){
            fuente.setImageResource(R.drawable.alimemntaria);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*extractiva.*)")){
            fuente.setImageResource(R.drawable.mineria);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*informatica.*)")){
            fuente.setImageResource(R.drawable.informatica);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*mantenimiento.*)")){
            fuente.setImageResource(R.drawable.mantenimiento);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*madera.*)")){
            fuente.setImageResource(R.drawable.madera);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*Maritimo.*)")){
            fuente.setImageResource(R.drawable.maritimo);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*Quimica.*)")){
            fuente.setImageResource(R.drawable.quimica);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*Sanidad.*)")){
            fuente.setImageResource(R.drawable.salud);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*seguridad.*)")){
            fuente.setImageResource(R.drawable.medioambiente);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*socioculturales.*)")){
            fuente.setImageResource(R.drawable.sociocultural);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*Textil.*)")){
           fuente.setImageResource(R.drawable.textil);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*Transporte.*)")){
            fuente.setImageResource(R.drawable.transporte);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*Vidrio.*)")){
            fuente.setImageResource(R.drawable.vidrio);
        }else{
            fuente.setImageResource(R.drawable.nuevaformacion);
        }

        final ControladorBD managerBD=new ControladorBD();
        final ControladorAvisos managerAvisos=new ControladorAvisos();

        if((managerBD.buscarRegistroBD(ofertaAMostrar.getId(),getApplicationContext(),4).getCount())>0){
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


                Uri test=managerBD.insertarCursoEnFavoritos(ofertaAMostrar,getApplicationContext());
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
                managerBD.borrarRegistroBD(ofertaAMostrar.getId(),getApplicationContext(),4);
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
