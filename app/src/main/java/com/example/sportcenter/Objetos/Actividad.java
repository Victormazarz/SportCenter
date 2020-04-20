package com.example.sportcenter.Objetos;

public class Actividad {
    String tipo,titulo, tiempo, descripcion, ambiente,fecha,username;
    int calorias,brazadas;
    double distancia,ritmo;

    public Actividad(String tipo, String titulo, String tiempo, String descripcion, String ambiente, int calorias, double distancia, double ritmo,String fecha,String username) {
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

    public Actividad() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTipo() {
        return tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getRitmo() {
        return ritmo;
    }

    public void setRitmo(double ritmo) {
        this.ritmo = ritmo;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "tipo='" + tipo + '\'' +
                ", titulo='" + titulo + '\'' +
                ", tiempo='" + tiempo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", ambiente='" + ambiente + '\'' +
                ", calorias=" + calorias +
                ", distancia=" + distancia +
                ", ritmo=" + ritmo +
                '}';
    }
}
