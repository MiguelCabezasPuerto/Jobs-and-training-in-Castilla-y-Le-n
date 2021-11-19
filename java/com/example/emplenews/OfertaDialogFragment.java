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
/**
 * Clase OfertaDialogFragment
 *
 * Clase que formatea el contenido de una oferta seleccionada sobre el mapa para poder visualizarlo en un cuadro de diálogo.
 *
 * Como variables usadas están todas aquellas de texto que recogen los datos asociados a una oferta de empleo y las cajas de texto donde plasmar esas cadenas en pantalla.
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class OfertaDialogFragment extends DialogFragment {
    public static final String ARG_TITULO = "TITULO";
    public static final String ARG_LUGAR = "LUGAR";
    public static final String ARG_FECHA = "FECHA";
    public static final String ARG_DESCRIPCION = "DESCRIPCION";
    public static final String ARG_FUENTE = "FUENTE";
    public static final String ARG_ENLACE = "ENLACE";

    private Mapa.OnClickButtonListener listener;

    private String titulo, lugarO, fechaO, descripcionO, fuenteO, enlaceO;
    private Button verMas;
    TextView tituloOferta;
    TextView lugar;
    TextView fecha;
    TextView descripcion;
    TextView fuente;
    TextView enlace;
    ImageView font;
    /**
     * Crea una nueva instancia del cuadro de diálogo, esto se refiere, en este caso, a mandar los valores de los datos de la oferta para que se recojan en el método que crea el contenedor de la vista
     * Como parametros recibe los asociados a una oferta
     * @return <ul>
     *          <li>EmpleoDialogFragment: instancia de la clase </li>
     *          </ul>
     */
    public static OfertaDialogFragment nuevaInstancia(String title, String place, String date, String description, String source, String link) {
        OfertaDialogFragment fragment = new OfertaDialogFragment();
        Bundle b = new Bundle();
        b.putString(ARG_TITULO, title);
        b.putString(ARG_LUGAR, place);
        b.putString(ARG_FECHA, date);
        b.putString(ARG_DESCRIPCION, description);
        b.putString(ARG_FUENTE, source);
        b.putString(ARG_ENLACE, link);
        fragment.setArguments(b);
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
        lugarO = args.getString(ARG_LUGAR);
        fechaO = args.getString(ARG_FECHA);
        descripcionO = args.getString(ARG_DESCRIPCION);
        fuenteO = args.getString(ARG_FUENTE);
        enlaceO = args.getString(ARG_ENLACE);
        return inflater.inflate(R.layout.oferta_mapa_ventana, null);
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
        fecha = (TextView) view.findViewById(R.id.textView_fecha);
        descripcion = (TextView) view.findViewById(R.id.textView_descripcion);
        font=(ImageView)view.findViewById(R.id.fuenteImg);


        descripcion.setMovementMethod(LinkMovementMethod.getInstance());

        ControladorFormateador formateadorCadenas=new ControladorFormateador();
        tituloOferta.setText(titulo);
        char[] aCaracteres = fechaO.toCharArray();

        String fechaPub = formateadorCadenas.generarFecha(fechaO,view,1);
        fecha.setText(fechaPub);
        descripcion.setText(Html.fromHtml(descripcionO));

        ControladorOfertasEmpleo managerEmpleos=new ControladorOfertasEmpleo();

        String fuenteOferta=formateadorCadenas.limpiarCadena(fuenteO);

        if (formateadorCadenas.coincideCadenaConExpReg(fuenteOferta,"(?i:.*ECYL.*)")) {
            font.setImageResource(R.drawable.unnamed);

        } else if (formateadorCadenas.coincideCadenaConExpReg(fuenteOferta,"(?i:.*EURES.*)")) {
            font.setImageResource(R.drawable.logo_eures);

        } else {
            font.setImageResource(R.drawable.internet2);

        }


    }

}
