package com.example.sportcenter.Objetos;

public class Actividad_otro {
    String tipo,titulo, tiempo, descripcion, ambiente,fecha,username;
    int calorias;

    public Actividad_otro(String tipo, String titulo, String tiempo, String descripcion, String ambiente, int calorias, String fecha, String username) {
        this.tipo = tipo;
        this.titulo = titulo;
        this.tiempo = tiempo;
        this.descripcion = descripcion;
        this.ambiente = ambiente;
        this.calorias = calorias;
        this.fecha = fecha;
        this.username = username;
    }


    public Actividad_otro() {
    }

    public String getTipo() {
        return tipo;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    @Override
    public String toString() {
        return "Actividad_otro{" +
                "tipo='" + tipo + '\'' +
                ", titulo='" + titulo + '\'' +
                ", tiempo='" + tiempo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", ambiente='" + ambiente + '\'' +
                ", fecha='" + fecha + '\'' +
                ", username='" + username + '\'' +
                ", calorias=" + calorias +
                '}';
    }
}
