package com.example.emplenews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emplenews.IdiomaSeleccionable;
import com.example.emplenews.R;

import java.util.List;

public class IdiomasSpinnerAdapter extends ArrayAdapter<IdiomaSeleccionable> {
    private Context context;

    List<IdiomaSeleccionable> datos = null;

    public IdiomasSpinnerAdapter(Context context, List<IdiomaSeleccionable> datos)
    {
        //se debe indicar el layout para el item que seleccionado (el que se muestra sobre el botón del botón)
        super(context, R.layout.spinner_idiomas_selected_item, datos);
        this.context = context;
        this.datos = datos;
    }

    //este método establece el elemento seleccionado sobre el botón del spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.spinner_idiomas_selected_item,null);
        }
        ((TextView) convertView.findViewById(R.id.texto)).setText(datos.get(position).getNombre());
        ((ImageView) convertView.findViewById(R.id.icono)).setBackgroundResource(datos.get(position).getIcono());

        return convertView;
    }

    //gestiona la lista usando el View Holder Pattern. Equivale a la típica implementación del getView
    //de un Adapter de un ListView ordinario
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        if (row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.spinner_idiomas_lista, parent, false);
        }


        if (row.getTag() == null)
        {
            IdiomaHolder idiomaHolder = new IdiomaHolder();
            idiomaHolder.setIcono((ImageView) row.findViewById(R.id.icono));
            idiomaHolder.setTextView((TextView) row.findViewById(R.id.texto));
            row.setTag(idiomaHolder);
        }

        //rellenamos el layout con los datos de la fila que se está procesando
        IdiomaSeleccionable idioma = datos.get(position);
        ((IdiomaHolder)row.getTag()).getIcono().setImageResource(idioma.getIcono());
        ((IdiomaHolder) row.getTag()).getTextView().setText(idioma.getNombre());

        return row;
    }

    /**
     * Holder para el Adapter del Spinner
     * @author danielme.com
     *
     */
    private static class IdiomaHolder
    {

        private ImageView icono;

        private TextView textView;

        public ImageView getIcono()
        {
            return icono;
        }

        public void setIcono(ImageView icono)
        {
            this.icono = icono;
        }

        public TextView getTextView()
        {
            return textView;
        }

        public void setTextView(TextView textView)
        {
            this.textView = textView;
        }

    }
}
