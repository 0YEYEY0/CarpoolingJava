package com.example.carpoolingjava;

public class Usuario {
    private String carnet;
    private int nodo;
    private String direccion;
    private int nodoProveniente;
    // Otros campos y métodos

    public Usuario(String carnet, int nodo, String direccion) {
        this.carnet = carnet;
        this.nodo = nodo;
        this.direccion = direccion;
        // Inicializa otros campos según sea necesario
    }
    public String getCarnet() {
        return carnet;
    }
}




