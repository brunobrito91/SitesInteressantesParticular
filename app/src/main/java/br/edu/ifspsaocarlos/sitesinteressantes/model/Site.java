package br.edu.ifspsaocarlos.sitesinteressantes.model;

import java.io.Serializable;

/**
 * Created by manutencao.sca on 13/09/2016.
 */
public class Site implements Serializable {
    private String url;
    private int idImagemFavorito;

    public Site(String url, int idImagemFavorito) {
        this.url = url;
        this.idImagemFavorito = idImagemFavorito;
    }

    public Site() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIdImagemFavorito() {
        return idImagemFavorito;
    }

    public void setIdImagemFavorito(int idImagemFavorito) {
        this.idImagemFavorito = idImagemFavorito;
    }
}
