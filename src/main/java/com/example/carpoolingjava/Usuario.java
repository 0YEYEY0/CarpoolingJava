package com.example.carpoolingjava;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un usuario en el sistema de carpooling.
 */
public class Usuario {
    private String carnet;
    private int nodo;
    private int calificacion;

    private List<Integer> amigos = new ArrayList<>();

    private String direccion;
    private int nodoProveniente;

    private int sumaCalificaciones = 0;
    private int cantidadCalificaciones = 0;
    private double promedioCalificacion = 0.0;

    /**
     * Constructor de la clase Usuario.
     *
     * @param carnet    Carnet del usuario.
     * @param nodo      Nodo al que está asociado el usuario.
     * @param direccion Dirección del usuario.
     */
    public Usuario(String carnet, int nodo, String direccion) {
        this.carnet = carnet;
        this.nodo = nodo;
        this.direccion = direccion;
        // Inicializa otros campos según sea necesario
    }

    /**
     * Obtiene la calificación actual del usuario.
     *
     * @return Calificación del usuario.
     */
    public int getCalificacion() {
        return calificacion;
    }

    /**
     * Obtiene el promedio de calificación del usuario.
     *
     * @return Promedio de calificación del usuario.
     */
    public double getPromedioCalificacion() {
        if (cantidadCalificaciones == 0) {
            return 0.0; // Evitar la división por cero
        }
        return (double) sumaCalificaciones / cantidadCalificaciones;
    }

    /**
     * Agrega un amigo al usuario.
     *
     * @param numeroAmigo Número del amigo a agregar.
     */
    public void agregarAmigo(int numeroAmigo) {
        amigos.add(numeroAmigo);
    }

    /**
     * Califica al usuario con una nueva calificación.
     *
     * @param nuevaCalificacion Nueva calificación a agregar.
     */
    public void calificar(int nuevaCalificacion) {
        // Actualizar la suma total de calificaciones y contar la cantidad de calificaciones
        sumaCalificaciones += nuevaCalificacion;
        cantidadCalificaciones++;

        // Calcular el nuevo promedio
        promedioCalificacion = (double) sumaCalificaciones / cantidadCalificaciones;
    }

    /**
     * Establece la calificación del usuario.
     *
     * @param calificacion Calificación a establecer.
     */
    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * Obtiene el carnet del usuario.
     *
     * @return Carnet del usuario.
     */
    public String getCarnet() {
        return carnet;
    }
}





