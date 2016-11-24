package br.edu.ifspsaocarlos.sitesinteressantes.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.edu.ifspsaocarlos.sitesinteressantes.R;
import br.edu.ifspsaocarlos.sitesinteressantes.model.Site;

/**
 * Created by manutencao.sca on 13/09/2016.
 */
public class ListaSiteAdapter extends ArrayAdapter<Site>{

    private List<Site> sites;

    public ListaSiteAdapter(Context context, int resource, List<Site> objects) {
        super(context, resource, objects);
        sites = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       if (convertView == null){
           convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_item_lista_site, null);
       }
        Site site = sites.get(position);

        TextView textView = (TextView)convertView.findViewById(R.id.txtSite);
        textView.setText(site.getUrl());

        ImageView imageView = (ImageView)convertView.findViewById(R.id.imageFavorito);
        imageView.setImageResource(site.getIdImagemFavorito());
        imageView.setLabelFor(position);

        return convertView;
    }

}
