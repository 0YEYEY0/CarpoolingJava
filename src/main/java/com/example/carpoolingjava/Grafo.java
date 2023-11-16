package com.example.carpoolingjava;

import java.util.*;


import java.util.*;

import java.util.*;

public class Grafo {
    private Map<Integer, List<int[]>> grafo;

    public Grafo() {
        this.grafo = new HashMap<>();
    }

    public void agregarArista(int origen, int destino, int peso) {
        List<int[]> vecinos = grafo.getOrDefault(origen, new ArrayList<>());
        vecinos.add(new int[]{destino, peso});
        grafo.put(origen, vecinos);
    }

    public void generarGrafo(int numeroDeNodos) {
        Random random = new Random();

        for (int i = 1; i <= numeroDeNodos; i++) {
            for (int j = 1; j <= numeroDeNodos; j++) {
                if (i != j && random.nextDouble() < 0.1) {  // Agrega una arista con probabilidad del 50%
                    int peso = random.nextInt(10) + 1; // Genera un peso aleatorio de 1 a 10
                    agregarArista(i, j, peso);
                    agregarArista(j, i, peso); // Para hacer el grafo no dirigido
                }
            }
        }
    }

    public void imprimirGrafo() {
        System.out.println("Conexiones del Grafo:");
        for (int nodo : grafo.keySet()) {
            System.out.print(nodo + " -> ");
            for (int[] vecino : grafo.get(nodo)) {
                System.out.print("(" + vecino[0] + ", peso " + vecino[1] + ") ");
            }
            System.out.println();
        }
    }

    public int obtenerPesoArista(int origen, int destino) {
        List<int[]> vecinos = grafo.getOrDefault(origen, Collections.emptyList());
        for (int[] vecino : vecinos) {
            if (vecino[0] == destino) {
                return vecino[1];
            }
        }
        return -1; // Valor que indica que no hay arista entre los nodos dados
    }

    public Map<Integer, List<int[]>> obtenerGrafo() {
        return grafo;
    }





    public static void main(String[] args) {
        Grafo grafoAleatorio = new Grafo();
        grafoAleatorio.generarGrafo(30);
        grafoAleatorio.imprimirGrafo();
    }
}
