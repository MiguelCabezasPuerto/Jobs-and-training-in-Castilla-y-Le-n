package com.example.emplenews;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.w3c.dom.Text;
/**
 * Clase EmpleoDialogFragment
 *
 * Clase que formatea el contenido de una oferta seleccionada sobre el mapa para poder visualizarlo en un cuadro de diálogo.
 *
 * Como variables usadas están todas aquellas de texto que recogen los datos asociados a una oferta de formación y las cajas de texto donde plasmar esas cadenas en pantalla.
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class EmpleoDialogFragment extends DialogFragment {
    public static final String ARG_TITULO = "TITULO";
    public static final String ARG_MATERIA = "MATERIA";
    public static final String ARG_LUGAR = "LUGAR";
    public static final String ARG_FECHA = "FECHA";
    public static final String ARG_DURACION = "DURACION";
    public static final String ARG_DESTINATARIOS = "DESTINATARIOS";
    public static final String ARG_DESCRIPCION = "DESCRIPCION";
    public static final String ARG_INSCRIPCION = "INSCRIPCION";
    public static final String ARG_ENLACE = "ENLACE";

    private MapaFormaciones.OnClickButtonListener listener;


    private String titulo,materiaO, lugarO, fechaO,duracionO,destinatariosO,inscripcionO, enlaceO;
    private Button verMas;
    TextView tituloOferta;
    TextView lugar;
    TextView fecha;
    TextView materia;
    TextView enlace;
    TextView duracion;
    TextView destinatarios;
    TextView inscripcion;
    ImageView font;
    /**
     * Crea una nueva instancia del cuadro de diálogo, esto se refiere, en este caso, a mandar los valores de los datos de la oferta para que se recojan en el método que crea el contenedor de la vista
     * Como parametros recibe los asociados a una oferta
     * @return <ul>
     *          <li>EmpleoDialogFragment: instancia de la clase </li>
     *          </ul>
     */
    public static EmpleoDialogFragment nuevaInstancia(String title, String area,String place, String date,String duration,String to, String signin, String link) {
        EmpleoDialogFragment fragment = new EmpleoDialogFragment();
        Bundle b = new Bundle();
        b.putString(ARG_TITULO, title);
        b.putString(ARG_MATERIA, area);
        b.putString(ARG_LUGAR, place);
        b.putString(ARG_FECHA, date);
        b.putString(ARG_DURACION, duration);
        b.putString(ARG_DESTINATARIOS, to);
        b.putString(ARG_INSCRIPCION, signin);
        b.putString(ARG_ENLACE, link);
        fragment.setArguments(b);
        Log.e("LOG","En nueva instancia empleo dialog fragment");
        return fragment;
    }


    @Override
    /**
     * Recoge los valores de los datos de la oferta y genera el contenedor para los componentes de la pantalla
     * @param inflater inflador de la vista
     * @param savedInstanceState parametros recibidos cuya informacion se muestra en la vista
     * @return <ul>
     *          <li>View: contenedor de la vista creada/li>
     *          </ul>
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle args = getArguments();
        titulo = args.getString(ARG_TITULO);
        materiaO=args.getString(ARG_MATERIA);
        lugarO = args.getString(ARG_LUGAR);
        fechaO = args.getString(ARG_FECHA);
        duracionO=args.getString(ARG_DURACION);
        destinatariosO=args.getString(ARG_DESTINATARIOS);
        inscripcionO = args.getString(ARG_INSCRIPCION);
        enlaceO = args.getString(ARG_ENLACE);
        Log.e("LOG","Inflando layout empleo");
        return inflater.inflate(R.layout.empleo_mapa_ventana, null);
    }

    /*public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        Bundle args=getArguments();
        titulo=args.getString(ARG_TITULO);
        snippetCompleto=args.getString(ARG_SNIPPET);

    }*/

   /* public Dialog onCreateDialog(Bundle saveInstanceState){
        Dialog dialog=new AlertDialog.Builder(getActivity()).setTitle(titulo).setMessage(snippetCompleto).create();
        return dialog;
    }*/

    @Override
    /**
     * Una vez creado el contenedor, rellena sus componentes con los datos de la oferta
     * @param view vista contenedor en la que plasmar la informacion
     * @param savedInstanceState parametros recibidos cuya informacion se muestra en la vista
     *
     */
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tituloOferta = (TextView) view.findViewById(R.id.textView_titulo);
        materia=(TextView)view.findViewById(R.id.textView_materia);
        fecha = (TextView) view.findViewById(R.id.textView_fechas);
        duracion=(TextView)view.findViewById(R.id.textView_destinatarios);
        destinatarios=(TextView)view.findViewById(R.id.textView_destinatarios);
        inscripcion = (TextView) view.findViewById(R.id.textView_inscripcion);
        font=(ImageView)view.findViewById(R.id.fuenteImg);

        inscripcion.setMovementMethod(LinkMovementMethod.getInstance());

        ControladorFormateador formateadorCadenas=new ControladorFormateador();
        tituloOferta.setText(titulo);
        Log.e("Titulo",titulo);
        materia.setText(materiaO);
        fecha.setText(getString(R.string.fechas)+fechaO);
        Log.e("Fecha",fechaO);
        duracion.setText(duracionO);
        destinatarios.setText(getString(R.string.destinatarios)+destinatariosO);
        inscripcion.setText(Html.fromHtml(getString(R.string.formasinscripcion)+"\n"+inscripcionO));

        ControladorFormateador formatter=new ControladorFormateador();

        String materia= formatter.limpiarCadenaExtremo(materiaO);
        Log.e("Materia",materia);
        if(formatter.coincideCadenaConExpReg(materia,"(?i:.*deport.*)")){
            font.setImageResource(R.drawable.deporte);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*gest.*)")){
            font.setImageResource(R.drawable.admin);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*agraria.*)")){
            font.setImageResource(R.drawable.campo);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*artes.*)")){
            font.setImageResource(R.drawable.arte);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*artesan.*)")){
            font.setImageResource(R.drawable.ceramica);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*comercio.*)")){
            font.setImageResource(R.drawable.comercio);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*obra.*)")){
            font.setImageResource(R.drawable.obras);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*energia.*)")){
            font.setImageResource(R.drawable.energia);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*mecanica.*)")){
            font.setImageResource(R.drawable.mecanico);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*turismo.*)")){
            font.setImageResource(R.drawable.turismo);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*personal.*)")){
            font.setImageResource(R.drawable.belleza);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*sonido.*)")){
            font.setImageResource(R.drawable.sonido);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*alimentaria.*)")){
            font.setImageResource(R.drawable.alimemntaria);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*extractiva.*)")){
            font.setImageResource(R.drawable.mineria);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*informatica.*)")){
            font.setImageResource(R.drawable.informatica);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*mantenimiento.*)")){
            font.setImageResource(R.drawable.mantenimiento);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*madera.*)")){
            font.setImageResource(R.drawable.madera);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*Maritimo.*)")){
            font.setImageResource(R.drawable.maritimo);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*Quimica.*)")){
            font.setImageResource(R.drawable.quimica);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*Sanidad.*)")){
            font.setImageResource(R.drawable.salud);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*seguridad.*)")){
            font.setImageResource(R.drawable.medioambiente);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*socioculturales.*)")){
            font.setImageResource(R.drawable.sociocultural);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*Textil.*)")){
            font.setImageResource(R.drawable.textil);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*Transporte.*)")){
            font.setImageResource(R.drawable.transporte);
        }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*Vidrio.*)")){
            font.setImageResource(R.drawable.vidrio);
        }else{
            font.setImageResource(R.drawable.nuevaformacion);
        }
    }

}
