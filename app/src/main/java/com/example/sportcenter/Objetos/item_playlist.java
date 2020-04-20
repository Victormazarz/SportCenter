package com.example.sportcenter.Objetos;

import android.widget.ImageView;
import android.widget.TextView;

public class item_playlist {

    int imagen,reproductor;
    String Titulo,url;
    String imagenstr,reproductorstr;

   public item_playlist(int imagen, String titulo,int reproductor,String url) {
        this.imagen = imagen;
        this.Titulo = titulo;
        this.reproductor = reproductor;
        this.url = url;
    }

    public item_playlist(String titulo, String url, String imagenstr, String reproductorstr) {
        Titulo = titulo;
        this.url = url;
        this.imagenstr = imagenstr;
        this.reproductorstr = reproductorstr;
    }

    public item_playlist() {
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getReproductor() {
        return reproductor;
    }

    public void setReproductor(int reproductor) {
        this.reproductor = reproductor;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImagenstr() {
        return imagenstr;
    }

    public void setImagenstr(String imagenstr) {
        this.imagenstr = imagenstr;
    }

    public String getReproductorstr() {
        return reproductorstr;
    }

    public void setReproductorstr(String reproductorstr) {
        this.reproductorstr = reproductorstr;
    }
}
