package com.example.sportcenter.Objetos;

public class item_playlist_s {

    String Titulo,url;
    String imagen,reproductor;

    public item_playlist_s(String titulo, String url, String imagen, String reproductor) {
        Titulo = titulo;
        this.url = url;
        this.imagen = imagen;
        this.reproductor = reproductor;
    }

    public item_playlist_s() {
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagenstr) {
        this.imagen = imagenstr;
    }

    public String getReproductor() {
        return reproductor;
    }

    public void setReproductor(String reproductorstr) {
        this.reproductor = reproductorstr;
    }
}
