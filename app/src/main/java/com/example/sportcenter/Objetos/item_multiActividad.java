package com.example.sportcenter.Objetos;

import java.io.Serializable;

public class item_multiActividad implements Serializable {

    String nombre;
    int cantidad,tiempo;

    public item_multiActividad(String nombre, int cantidad, int tiempo) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tiempo = tiempo;
    }

    public item_multiActividad() {
    }

    public String getNombre() {
        return nombre;
    }


    public int getCantidad() {
        return cantidad;
    }

    public int getTiempo() {
        return tiempo;
    }
}
