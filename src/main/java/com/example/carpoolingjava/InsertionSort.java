package com.example.carpoolingjava;

import java.util.List;

/**
 * Clase que implementa el algoritmo de ordenación por inserción para una lista de conductores.
 */
public class InsertionSort {

    /**
     * Ordena la lista de conductores por cantidad de calificaciones en orden descendente.
     *
     * @param conductoresList Lista de conductores a ordenar.
     */
    public static void sortByCalificaciones(List<Conductores> conductoresList) {
        int n = conductoresList.size();

        for (int i = 1; i < n; ++i) {
            Conductores key = conductoresList.get(i);
            int j = i - 1;

            // Mover elementos del conductor[0..i-1] que son mayores que key.cantidadCalificaciones
            while (j >= 0 && conductoresList.get(j).getCantidadCalificaciones() < key.getCantidadCalificaciones()) {
                conductoresList.set(j + 1, conductoresList.get(j));
                j = j - 1;
            }
            conductoresList.set(j + 1, key);
        }
    }
}


