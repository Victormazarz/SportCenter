package com.example.sportcenter.Objetos;

public class Actividades_Get {

    String tipo,titulo, tiempo, descripcion, ambiente,fecha,username;
    int calorias,brazadas;
    double distancia,ritmo;

    public Actividades_Get(String tipo, String titulo, String tiempo, String descripcion, String ambiente, int calorias, double distancia, double ritmo,String fecha,String username) {
        this.tipo = tipo;
        this.titulo = titulo;
        this.tiempo = tiempo;
        this.descripcion = descripcion;
        this.ambiente = ambiente;
        this.calorias = calorias;
        this.distancia = distancia;
        this.ritmo = ritmo;
        this.fecha = fecha;
        this.username = username;
    }

    public Actividades_Get(String tipo, String titulo, String tiempo, String descripcion, String ambiente, int calorias, int brazadas, double distancia, String fecha, String username) {
        this.tipo = tipo;
        this.titulo = titulo;
        this.tiempo = tiempo;
        this.descripcion = descripcion;
        this.ambiente = ambiente;
        this.calorias = calorias;
        this.brazadas = brazadas;
        this.distancia = distancia;
        this.fecha = fecha;
        this.username = username;
    }

    public Actividades_Get(String tipo, String titulo, String tiempo, String descripcion, String ambiente, int calorias, String fecha, String username) {
        this.tipo = tipo;
        this.titulo = titulo;
        this.tiempo = tiempo;
        this.descripcion = descripcion;
        this.ambiente = ambiente;
        this.calorias = calorias;
        this.fecha = fecha;
        this.username = username;
    }



    public Actividades_Get() {
    }


    public String getTipo() {
        return tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTiempo() {
        return tiempo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public String getFecha() {
        return fecha;
    }

    public String getUsername() {
        return username;
    }

    public int getCalorias() {
        return calorias;
    }

    public int getBrazadas() {
        return brazadas;
    }

    public double getDistancia() {
        return distancia;
    }

    public double getRitmo() {
        return ritmo;
    }
}
