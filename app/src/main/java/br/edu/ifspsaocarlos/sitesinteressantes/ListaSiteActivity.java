package br.edu.ifspsaocarlos.sitesinteressantes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.edu.ifspsaocarlos.sitesinteressantes.adapter.ListaSiteAdapter;
import br.edu.ifspsaocarlos.sitesinteressantes.model.Site;

/**
 * Created by manutencao.sca on 12/09/2016.
 */
public class ListaSiteActivity extends AppCompatActivity {

    private static final int NOVO_SITE = 1;
    private ListView listView;
    private List<Site> sites;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Set<String> sitesUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_site);

        listView = (ListView) findViewById(R.id.listViewSites);
        sites = new ArrayList<>();

        sharedPreferences = getSharedPreferences("SitesFavoritos", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        sitesUrl = sharedPreferences.getStringSet("sitesURL", new HashSet<String>());
        for (String siteURL :
                sitesUrl) {
            sites.add(new Site(siteURL, sharedPreferences.getInt(siteURL, R.mipmap.icone_favorito_off)));
        }

        ListAdapter listAdapter = new ListaSiteAdapter(this, 0, sites);
        listView.setAdapter(listAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_site, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.novoSite) {
            Intent intentNovoSite = new Intent(this, NovoSiteActivity.class);
            startActivityForResult(intentNovoSite, NOVO_SITE);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NOVO_SITE) {
            if (resultCode == RESULT_OK) {
                Site site = (Site) data.getSerializableExtra("novoSite");
                sites.add(site);
                sitesUrl.add(site.getUrl());

                editor.putInt(site.getUrl(), site.getIdImagemFavorito());
                editor.putStringSet("sitesURL", sitesUrl);
                editor.commit();

                ((ArrayAdapter) listView.getAdapter()).notifyDataSetChanged();
                Toast.makeText(this, "Novo site adicionado", Toast.LENGTH_SHORT).show();
            }
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Ação cancelada", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onClickTexto(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(((TextView) view).getText().toString()));
        startActivity(intent);
    }

    public void onClickImagem(View view) {
        ImageView imageView = (ImageView) view;
        Site site = sites.get(imageView.getLabelFor());
        if (site.getIdImagemFavorito() == R.mipmap.icone_favorito_on) {
            imageView.setImageResource(R.mipmap.icone_favorito_off);
            site.setIdImagemFavorito(R.mipmap.icone_favorito_off);
        } else if (site.getIdImagemFavorito() == R.mipmap.icone_favorito_off) {
            imageView.setImageResource(R.mipmap.icone_favorito_on);
            site.setIdImagemFavorito(R.mipmap.icone_favorito_on);
        }
        editor.putInt(site.getUrl(),site.getIdImagemFavorito());
        editor.commit();
        ((ArrayAdapter) listView.getAdapter()).notifyDataSetChanged();
    }


}
