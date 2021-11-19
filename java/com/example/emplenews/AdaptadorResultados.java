package com.example.emplenews;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;
/**
 * Clase AdaptadorResultados
 *
 * Actividad encargada de formatear el contenido de las ofertas de empleo a una vista
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class AdaptadorResultados extends BaseAdapter implements AdapterView.OnItemClickListener
{

    ContentResolver cr;
    Context context;

    @Override
    /**
     *Inicia la actividad de mostrar una oferta de manera individual cuando esta es pulsada por el usuario
     */
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        OfertasEmpleo d = (OfertasEmpleo) data.get(position);
        Log.v(TAG, "Objeto " + d.getTitulo());
        Intent intent=new Intent(view.getContext(),oferta_individual.class);
        intent.putExtra("oferta",d);
        view.getContext().startActivity(intent);


        //MOSTRAR OTRA PAGINA CON LA OFERTA COMPLETA EN DETALLE
    }

    static class ViewHolder
    {
        TextView ofertaTexto;
        ImageView imagenFuente;
        TextView ofertaLugar;
        TextView ofertaFecha;
        ImageView esFavorito;
        CardView cardView;
    }

    private static final String TAG = "Adaptador";
    private static int convertViewCounter = 0;

    /**
     * Lista de ofertas de empleo a mostrar
     */
    private ArrayList<OfertasEmpleo> data;
    /**
     *Inflador de la vista donde se deben mostrar las ofertas
     */
    private LayoutInflater inflater = null;

    /**
     * Crea una instancia de la clase guardando las ofertas de empleo que tiene que mostrar y la vista (pantalla) en la que las tiene que mostrar.
     * @param c contexto pasado
     * @param d listado de ofertas de empleo de las que adaptar su contenido a una vista
     */
    public AdaptadorResultados(Context c, ArrayList<OfertasEmpleo> d)
    {
        //Log.v(TAG, "Constructing CustomAdapter");

        this.data = d;
        inflater = LayoutInflater.from(c);
        cr = c.getContentResolver();
        context=c;
    }

    @Override
    public int getCount()
    {
       // Log.v(TAG, "in getCount()");
        return data.size();
    }

    @Override
    public Object getItem(int position)
    {
        //Log.v(TAG, "in getItem() for position " + position);
        return data.get(position);
    }

    @Override
    public long getItemId(int position)
    {
       // Log.v(TAG, "in getItemId() for position " + position);
        return position;
    }

    @Override
    public int getViewTypeCount()
    {
       // Log.v(TAG, "in getViewTypeCount()");
        return 1;
    }

    @Override
    public int getItemViewType(int position)
    {
        //Log.v(TAG, "in getItemViewType() for position " + position);
        return 0;
    }

    @Override
    public void notifyDataSetChanged()
    {
        super.notifyDataSetChanged();
    }

    @Override
    /**
     * Formatea el contenido de cada oferta recibida en la lista para mostrarla por pantalla.
     * @param position ppsicion de la oferta en el array
     * @param convertView vista sobre la que se muestra el listado
     */
    public View getView(int position, View convertView, ViewGroup parent) {

        ControladorOfertasEmpleo managerEmpleos=new ControladorOfertasEmpleo();
        ViewHolder holder;

        //Log.v(TAG, "in getView for position " + position + ", convertView is "
        //  + ((convertView == null) ? "null" : "being recycled"));

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_list_oferta, null);

            convertViewCounter++;
            //Log.v(TAG, convertViewCounter + " convertViews have been created");

            holder = new ViewHolder();

            holder.ofertaTexto = (TextView) convertView
                    .findViewById(R.id.oferta_texto);
            holder.imagenFuente = (ImageView) convertView
                    .findViewById(R.id.imagen_fuente);
            holder.ofertaFecha = (TextView) convertView.findViewById(R.id.oferta_fecha);
            holder.ofertaLugar = (TextView) convertView.findViewById(R.id.oferta_lugar);
            holder.esFavorito = (ImageView) convertView.findViewById(R.id.esFavorito);
            holder.cardView= (CardView) convertView.findViewById(R.id.cardView);
            convertView.setTag(holder);

        } else

        if(managerEmpleos.listaEmpleosNula(data) || managerEmpleos.listaEmpleosVacia(data)){
            holder = (ViewHolder) convertView.getTag();
            holder.ofertaTexto.setText("");
            holder.ofertaFecha.setText("");
            holder.ofertaLugar.setText("");
            //holder.esFavorito.setVisibility(View.INVISIBLE);
            return convertView;
        }

            holder = (ViewHolder) convertView.getTag();

        // Setting all values in listview
        holder.ofertaTexto.setText(managerEmpleos.leerTituloEmpleo(data,position,null,2));
        ControladorFormateador formateadorCadenas=new ControladorFormateador();
        String fechaPub = formateadorCadenas.generarFecha(managerEmpleos.leerFechaEmpleo(data,position,null,2),convertView,1);
        String lugarPub = formateadorCadenas.generarLugar(managerEmpleos.leerCiudadEmpleo(data,position,null,2),managerEmpleos.leerProvinciaEmpleo(data,position,null,2));
        holder.ofertaFecha.setText(fechaPub);
        holder.ofertaLugar.setText(lugarPub);

        ControladorBD managerBD=new ControladorBD();


        if ((managerBD.buscarRegistroBD(managerEmpleos.leerIdEmpleo(data,position,null,2),context,3).getCount()) > 0) {
            //holder.esFavorito.setImageResource(R.drawable.favoritos);
            holder.esFavorito.setVisibility(View.VISIBLE);
            holder.cardView.setCardBackgroundColor(convertView.getResources().getColor(R.color.amarillo2));
        } else {
            holder.esFavorito.setVisibility(View.INVISIBLE);
            holder.cardView.setCardBackgroundColor(convertView.getResources().getColor(R.color.white));
        }


        Log.e("Oferta", data.get(position).getTitulo()+" Con  fuente: "+data.get(position).getFuente());

        String fuenteOferta=formateadorCadenas.limpiarCadena(managerEmpleos.leerFuenteEmpleo(data,position,null,2));

        if (formateadorCadenas.coincideCadenaConExpReg(fuenteOferta,"(?i:.*ECYL.*)")) {
            holder.imagenFuente.setImageResource(R.drawable.unnamed);
            Log.e("FUENTE","ECYL "+fuenteOferta);
            return convertView;
        } else if (formateadorCadenas.coincideCadenaConExpReg(fuenteOferta,"(?i:.*EURES.*)")) {
            holder.imagenFuente.setImageResource(R.drawable.logo_eures);
            Log.e("FUENTE","EURES "+fuenteOferta);
            return convertView;
        } else {
            holder.imagenFuente.setImageResource(R.drawable.internet2);
            Log.e("FUENTE","internet "+fuenteOferta);
            return convertView;
        }

        //return convertView;
    }





}