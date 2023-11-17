package com.example.carpoolingjava;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a los conductores en la aplicación de carpooling.
 */
public class Conductores {

    private String carnet;
    private int nodo;
    private int calificacion;

    private List<String> amigos = new ArrayList<>();

    private int sumaCalificaciones = 0;
    private int cantidadCalificaciones = 0;
    private double promedioCalificacion = 0.0;

    private String direccion;
    private int nodoProveniente;

    /**
     * Constructor de la clase Conductores.
     *
     * @param carnet     El carnet del conductor.
     * @param nodo       El nodo al que pertenece el conductor.
     * @param direccion  La dirección del conductor.
     */
    public Conductores(String carnet, int nodo, String direccion) {
        this.carnet = carnet;
        this.nodo = nodo;
        this.direccion = direccion;
    }

    /**
     * Obtiene la calificación actual del conductor.
     *
     * @return La calificación del conductor.
     */
    public int getCalificacion() {
        return calificacion;
    }

    /**
     * Obtiene el promedio de calificación del conductor.
     *
     * @return El promedio de calificación.
     */
    public double getPromedioCalificacion() {
        if (cantidadCalificaciones == 0) {
            return 0.0; // Evitar la división por cero
        }
        return (double) sumaCalificaciones / cantidadCalificaciones;
    }

    /**
     * Agrega un amigo al conductor.
     *
     * @param carnetAmigo El carnet del amigo a agregar.
     */
    public void agregarAmigo(String carnetAmigo) {
        amigos.add(carnetAmigo);
    }

    /**
     * Obtiene la lista de amigos del conductor.
     *
     * @return La lista de amigos.
     */
    public List<String> getAmigos() {
        return amigos;
    }

    /**
     * Califica al conductor con una nueva calificación.
     *
     * @param nuevaCalificacion La nueva calificación a agregar.
     */
    public void calificar(int nuevaCalificacion) {
        // Actualizar la suma total de calificaciones y contar la cantidad de calificaciones
        sumaCalificaciones += nuevaCalificacion;
        cantidadCalificaciones++;

        // Calcular el nuevo promedio
        promedioCalificacion = (double) sumaCalificaciones / cantidadCalificaciones;
    }

    /**
     * Obtiene la cantidad de calificaciones recibidas por el conductor.
     *
     * @return La cantidad de calificaciones.
     */
    public int getCantidadCalificaciones() {
        return cantidadCalificaciones;
    }

    /**
     * Establece la cantidad de calificaciones recibidas por el conductor.
     *
     * @param cantidadCalificaciones La nueva cantidad de calificaciones.
     */
    public void setCantidadCalificaciones(int cantidadCalificaciones) {
        this.cantidadCalificaciones = cantidadCalificaciones;
    }

    /**
     * Establece la calificación del conductor.
     *
     * @param calificacion La nueva calificación del conductor.
     */
    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * Obtiene el carnet del conductor.
     *
     * @return El carnet del conductor.
     */
    public String getCarnet() {
        return carnet;
    }
}


