package com.example.carpoolingjava;

public class Conductores {
    private String carnet;
    private int nodo;
    private int calificacion;

    private String direccion;
    private int nodoProveniente;
    // Otros campos y métodos

    public Conductores(String carnet, int nodo, String direccion) {
        this.carnet = carnet;
        this.nodo = nodo;
        this.direccion = direccion;
    }
    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
    public String getCarnet() {
        return carnet;
    }
}

