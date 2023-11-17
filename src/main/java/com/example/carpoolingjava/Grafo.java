package com.example.carpoolingjava;

import java.util.*;

/**
 * Clase que representa un grafo no dirigido con pesos en las aristas.
 */
public class Grafo {
    private Map<Integer, List<int[]>> grafo;

    /**
     * Constructor de la clase Grafo. Inicializa la estructura de datos para representar el grafo.
     */
    public Grafo() {
        this.grafo = new HashMap<>();
    }

    /**
     * Agrega una arista con peso al grafo.
     *
     * @param origen  Nodo de origen de la arista.
     * @param destino Nodo de destino de la arista.
     * @param peso    Peso de la arista.
     */
    public void agregarArista(int origen, int destino, int peso) {
        List<int[]> vecinos = grafo.getOrDefault(origen, new ArrayList<>());
        vecinos.add(new int[]{destino, peso});
        grafo.put(origen, vecinos);
    }

    /**
     * Genera un grafo aleatorio con un número específico de nodos y aristas.
     *
     * @param numeroDeNodos Número total de nodos en el grafo.
     */
    public void generarGrafo(int numeroDeNodos) {
        Random random = new Random();

        for (int i = 1; i <= numeroDeNodos; i++) {
            for (int j = 1; j <= numeroDeNodos; j++) {
                if (i != j && random.nextDouble() < 0.1) {  // Agrega una arista con probabilidad del 10%
                    int peso = random.nextInt(10) + 1; // Genera un peso aleatorio de 1 a 10
                    agregarArista(i, j, peso);
                    agregarArista(j, i, peso); // Para hacer el grafo no dirigido
                }
            }
        }
    }

    /**
     * Imprime las conexiones del grafo en la consola.
     */
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

    /**
     * Obtiene el peso de la arista entre dos nodos dados.
     *
     * @param origen  Nodo de origen.
     * @param destino Nodo de destino.
     * @return Peso de la arista entre los nodos dados, o -1 si no hay arista.
     */
    public int obtenerPesoArista(int origen, int destino) {
        List<int[]> vecinos = grafo.getOrDefault(origen, Collections.emptyList());
        for (int[] vecino : vecinos) {
            if (vecino[0] == destino) {
                return vecino[1];
            }
        }
        return -1; // Valor que indica que no hay arista entre los nodos dados
    }

    /**
     * Obtiene el grafo representado por esta clase.
     *
     * @return Mapa que representa el grafo.
     */
    public Map<Integer, List<int[]>> obtenerGrafo() {
        return grafo;
    }

    /**
     * Método principal que crea un objeto Grafo y genera un grafo aleatorio para su impresión.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        Grafo grafoAleatorio = new Grafo();
        grafoAleatorio.generarGrafo(30);
        grafoAleatorio.imprimirGrafo();
    }
}

