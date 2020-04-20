package com.example.sportcenter.Objetos;

import java.io.Serializable;

public class item_card implements Serializable {

    int imagen;
    String titulo;

    public item_card(int imagen, String titulo) {
        this.imagen = imagen;
        this.titulo = titulo;
    }

    public item_card() {
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
