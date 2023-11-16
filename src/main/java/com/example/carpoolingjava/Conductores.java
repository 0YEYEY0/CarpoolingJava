package com.example.carpoolingjava;

public class Conductores {
    private String carnet;
    private int nodo;
    private String direccion;
    private int nodoProveniente;
    // Otros campos y métodos

    public Conductores(String carnet, int nodo, String direccion) {
        this.carnet = carnet;
        this.nodo = nodo;
        this.direccion = direccion;
    }
    public String getCarnet() {
        return carnet;
    }
}

