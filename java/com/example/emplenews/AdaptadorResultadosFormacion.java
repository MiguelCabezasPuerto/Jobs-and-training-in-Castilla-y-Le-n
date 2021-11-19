package com.example.emplenews;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
 * Clase AdaptadorResultadosFormacion
 *
 * Actividad encargada de formatear el contenido de las ofertas de formación a una vista
 *
 * @author Miguel Cabezas Puerto
 * @version 1.0
 */
public class AdaptadorResultadosFormacion extends BaseAdapter implements AdapterView.OnItemClickListener
{

    ContentResolver cr;
    /**
     * Contexto desde el que se llama al adaptador
     */
    Context context;

    @Override
    /**
     * Inicia la actividad de mostrar una oferta de manera individual cuando esta es pulsada por el usuario
     */
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        OfertasFormacion d = (OfertasFormacion) data.get(position);
        Intent intent=new Intent(view.getContext(),formacion_individual.class);
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
     * Lista de ofertas de formacion a mostrar
     */
    private ArrayList<OfertasFormacion> data;
    /**
     * Inflador de la vista a mostrar
     */
    private LayoutInflater inflater = null;

    /**
     * Crea una instancia de la clase guardando las ofertas de formación que tiene que mostrar y la vista (pantalla) en la que las tiene que mostrar.
     * @param c contexto pasado
     * @param d lista de ofertas de formacion a adaptar su contenido a una vista
     */
    public AdaptadorResultadosFormacion(Context c, ArrayList<OfertasFormacion> d)
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
        if(data.isEmpty() || data==null){
            return 0;
        }
        return data.size();
    }

    @Override
    public Object getItem(int position)
    {
        //Log.v(TAG, "in getItem() for position " + position);
        if(data.isEmpty() || data==null){
            return null;
        }
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
     * @param position posición de la oferta en el array
     * @param convertView vista donde se situan los elementos a mostrar
     */
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        ControladorOfertasEmpleo managerFormaciones=new ControladorOfertasEmpleo();

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
            if (managerFormaciones.listaFormacionesNula(data) || managerFormaciones.listaFormacionesVacia(data)) {
                holder = (ViewHolder) convertView.getTag();
                holder.ofertaTexto.setText("");
                holder.ofertaFecha.setText("");
                holder.ofertaLugar.setText("");
                //holder.esFavorito.setVisibility(View.INVISIBLE);
                return convertView;
            }

            holder = (ViewHolder) convertView.getTag();
            ControladorFormateador formateadorCadenas=new ControladorFormateador();
            // Setting all values in listview
            holder.ofertaTexto.setText(managerFormaciones.leerTituloFormacion(data,position));
            String fechaPub = formateadorCadenas.generarFecha(managerFormaciones.leerActualizacionFormacion(data,position,null,2),convertView,2);
            String lugarPub = managerFormaciones.leerSitioFormacion(data,position);
            holder.ofertaFecha.setText(fechaPub);
            holder.ofertaLugar.setText(lugarPub);


            ControladorFormateador formatter=new ControladorFormateador();

            String materia= formatter.limpiarCadenaExtremo(managerFormaciones.leerMateriaFormacion(data,position,null,2));
            Log.e("Materia",materia);
            if(formatter.coincideCadenaConExpReg(materia,"(?i:.*deport.*)")){
                        holder.imagenFuente.setImageResource(R.drawable.deporte);
            }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*gest.*)")){
                holder.imagenFuente.setImageResource(R.drawable.admin);
            }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*agraria.*)")){
                holder.imagenFuente.setImageResource(R.drawable.campo);
            }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*artes.*)")){
            holder.imagenFuente.setImageResource(R.drawable.arte);
            }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*artesan.*)")){
            holder.imagenFuente.setImageResource(R.drawable.ceramica);
            }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*comercio.*)")){
            holder.imagenFuente.setImageResource(R.drawable.comercio);
            }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*obra.*)")){
            holder.imagenFuente.setImageResource(R.drawable.obras);
            }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*energia.*)")){
            holder.imagenFuente.setImageResource(R.drawable.energia);
            }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*mecanica.*)")){
            holder.imagenFuente.setImageResource(R.drawable.mecanico);
            }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*turismo.*)")){
            holder.imagenFuente.setImageResource(R.drawable.turismo);
            }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*personal.*)")){
            holder.imagenFuente.setImageResource(R.drawable.belleza);
            }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*sonido.*)")){
            holder.imagenFuente.setImageResource(R.drawable.sonido);
            }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*alimentaria.*)")){
            holder.imagenFuente.setImageResource(R.drawable.alimemntaria);
            }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*extractiva.*)")){
            holder.imagenFuente.setImageResource(R.drawable.mineria);
            }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*informatica.*)")){
            holder.imagenFuente.setImageResource(R.drawable.informatica);
            }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*mantenimiento.*)")){
            holder.imagenFuente.setImageResource(R.drawable.mantenimiento);
            }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*madera.*)")){
            holder.imagenFuente.setImageResource(R.drawable.madera);
            }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*Maritimo.*)")){
            holder.imagenFuente.setImageResource(R.drawable.maritimo);
            }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*Quimica.*)")){
            holder.imagenFuente.setImageResource(R.drawable.quimica);
            }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*Sanidad.*)")){
            holder.imagenFuente.setImageResource(R.drawable.salud);
            }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*seguridad.*)")){
            holder.imagenFuente.setImageResource(R.drawable.medioambiente);
            }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*socioculturales.*)")){
            holder.imagenFuente.setImageResource(R.drawable.sociocultural);
            }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*Textil.*)")){
            holder.imagenFuente.setImageResource(R.drawable.textil);
            }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*Transporte.*)")){
            holder.imagenFuente.setImageResource(R.drawable.transporte);
            }else if(formatter.coincideCadenaConExpReg(materia,"(?i:.*Vidrio.*)")){
            holder.imagenFuente.setImageResource(R.drawable.vidrio);
            }else{
            holder.imagenFuente.setImageResource(R.drawable.nuevaformacion);
            }



            ControladorBD managerBD=new ControladorBD();

            if ((managerBD.buscarRegistroBD(managerFormaciones.leerIdFormacion(data,position,null,2),context,4).getCount()) > 0) {
               // holder.esFavorito.setImageResource(R.drawable.favoritos);
                holder.esFavorito.setVisibility(View.VISIBLE);
                holder.cardView.setCardBackgroundColor(convertView.getResources().getColor(R.color.amarillo2));
                Log.e("Oferta", "VISIBLE");
            } else {
                holder.esFavorito.setVisibility(View.INVISIBLE);
                holder.cardView.setCardBackgroundColor(convertView.getResources().getColor(R.color.white));
                Log.e("Oferta", "INVISIBLE");
            }


            return convertView;
        }
    }


