package com.example.sportcenter.Objetos;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public class item_recientes implements Serializable {

    int imagen;
    String tiempo, ambiente, fecha, titulo;
    Double distancia;


    public item_recientes(String titulo, int imagen, Double distancia, String tiempo, String ambiente, String fecha) {
        this.titulo = titulo;
        this.imagen = imagen;
        this.distancia = distancia;
        this.tiempo = tiempo;
        this.ambiente = ambiente;
        this.fecha = fecha;
    }

    public int getImagen() {
        return imagen;
    }

    public String getTiempo() {
        return tiempo;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public String getFecha() {
        return fecha;
    }

    public Double getDistancia() {
        return distancia;
    }

    public String getTitulo() { return titulo; }
}
