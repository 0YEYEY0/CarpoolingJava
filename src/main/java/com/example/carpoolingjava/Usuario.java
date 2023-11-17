package com.example.carpoolingjava;

public class Usuario {
    private String carnet;
    private int nodo;
    private int calificacion;

    private String direccion;
    private int nodoProveniente;

    private int sumaCalificaciones = 0;
    private int cantidadCalificaciones = 0;
    private double promedioCalificacion = 0.0;
    // Otros campos y métodos

    public Usuario(String carnet, int nodo, String direccion) {
        this.carnet = carnet;
        this.nodo = nodo;
        this.direccion = direccion;
        // Inicializa otros campos según sea necesario
    }
    public int getCalificacion() {
        return calificacion;
    }

    public double getPromedioCalificacion() {
        if (cantidadCalificaciones == 0) {
            return 0.0; // Evitar la división por cero
        }
        return (double) sumaCalificaciones / cantidadCalificaciones;
    }
    public void calificar(int nuevaCalificacion) {
        // Actualizar la suma total de calificaciones y contar la cantidad de calificaciones
        sumaCalificaciones += nuevaCalificacion;
        cantidadCalificaciones++;

        // Calcular el nuevo promedio
        promedioCalificacion = (double) sumaCalificaciones / cantidadCalificaciones;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
    public String getCarnet() {
        return carnet;
    }
}




