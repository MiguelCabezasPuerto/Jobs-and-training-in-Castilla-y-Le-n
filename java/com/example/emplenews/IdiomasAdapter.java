package com.example.emplenews;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class IdiomasAdapter extends ArrayAdapter<String> {
    Context context;
    String[]paises;
    int[]banderas;

    public IdiomasAdapter(@NonNull Context context,String[] paises, int[] banderas) {
        super(context, R.layout.idiomas_spinner_fila,paises);
        this.context = context;
        this.paises = paises;
        this.banderas = banderas;
    }

    public View getDropDownViewTheme(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View fila=inflater.inflate(R.layout.idiomas_spinner_fila,null);
        TextView t1=(TextView)fila.findViewById(R.id.textoPais);
            ImageView i1=(ImageView)fila.findViewById(R.id.iconoPais);
            t1.setText(paises[position]);
            i1.setImageResource(banderas[position]);

        return fila;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View fila=inflater.inflate(R.layout.idiomas_spinner_fila,null);
        TextView t1=(TextView)fila.findViewById(R.id.textoPais);
        ImageView i1=(ImageView)fila.findViewById(R.id.iconoPais);
        t1.setText(paises[position]);
        i1.setImageResource(banderas[position]);

        return fila;
    }

}
