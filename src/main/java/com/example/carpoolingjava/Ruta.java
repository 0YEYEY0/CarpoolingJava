package com.example.carpoolingjava;

import java.util.*;

/**
 * Clase que representa una ruta en un grafo y proporciona métodos para encontrar rutas utilizando el algoritmo de Dijkstra.
 */
public class Ruta {
    private Grafo grafo;

    /**
     * Constructor que recibe un grafo para trabajar con él.
     *
     * @param grafo Grafo sobre el cual se realizarán las operaciones de búsqueda de rutas.
     */
    public Ruta(Grafo grafo) {
        this.grafo = grafo;
    }

    /**
     * Realiza el algoritmo de Dijkstra para encontrar las distancias mínimas desde un nodo de origen a todos los demás nodos.
     *
     * @param origen Nodo de inicio para el algoritmo.
     * @return Mapa que asocia cada nodo con su distancia mínima desde el origen.
     */
    public Map<Integer, Integer> dijkstra(int origen) {
        Map<Integer, Integer> distancias = new HashMap<>();
        Map<Integer, Integer> padres = new HashMap<>();
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        distancias.put(origen, 0);
        heap.offer(new int[]{origen, 0});

        while (!heap.isEmpty()) {
            int[] actual = heap.poll();
            int nodoActual = actual[0];
            int distanciaActual = actual[1];

            if (distanciaActual > distancias.getOrDefault(nodoActual, Integer.MAX_VALUE)) {
                continue;
            }

            for (int[] vecino : grafo.obtenerGrafo().getOrDefault(nodoActual, Collections.emptyList())) {
                int nodoVecino = vecino[0];
                int pesoArista = vecino[1];
                int distanciaNueva = distanciaActual + pesoArista;

                if (distanciaNueva < distancias.getOrDefault(nodoVecino, Integer.MAX_VALUE)) {
                    distancias.put(nodoVecino, distanciaNueva);
                    padres.put(nodoVecino, nodoActual);
                    heap.offer(new int[]{nodoVecino, distanciaNueva});
                }
            }
        }

        return padres;
    }

    /**
     * Realiza la búsqueda de una ruta en el grafo, permitiendo la inclusión de un nodo intermedio.
     */
    public void encontrarRuta() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("¿Ocupas parar en algún lugar? (s/n)");
        String respuesta = scanner.nextLine().toLowerCase();

        if (respuesta.equals("s")) {
            System.out.println("Ingresa el nodo de partida:");
            int inicio = scanner.nextInt();
            System.out.println("Ingresa el nodo por el que debes pasar:");
            int intermedio = scanner.nextInt();
            System.out.println("Ingresa el nodo de llegada:");
            int destino = scanner.nextInt();

            encontrarRutaConIntermedio(inicio, intermedio, destino);
        } else if (respuesta.equals("n")) {
            System.out.println("Ingresa el nodo de partida:");
            int inicio = scanner.nextInt();
            System.out.println("Ingresa el nodo de llegada:");
            int destino = scanner.nextInt();

            encontrarRutaSinIntermedio(inicio, destino);
        } else {
            System.out.println("Respuesta no válida.");
        }
    }

    /**
     * Encuentra una ruta desde un nodo de inicio hasta un nodo de destino pasando por un nodo intermedio.
     *
     * @param inicio     Nodo de inicio.
     * @param intermedio Nodo intermedio por el que debe pasar la ruta.
     * @param destino    Nodo de destino.
     */
    void encontrarRutaConIntermedio(int inicio, int intermedio, int destino) {
        Map<Integer, Integer> padresDesdeInicio = dijkstra(inicio);
        Map<Integer, Integer> padresDesdeIntermedio = dijkstra(intermedio);

        List<Integer> rutaDesdeInicio = construirRuta(padresDesdeInicio, intermedio);
        List<Integer> rutaDesdeIntermedio = construirRuta(padresDesdeIntermedio, destino);

        if (rutaDesdeInicio.isEmpty() || rutaDesdeIntermedio.isEmpty()) {
            System.out.println("No hay ruta válida.");
        } else {
            rutaDesdeInicio.addAll(rutaDesdeIntermedio.subList(1, rutaDesdeIntermedio.size()));
            imprimirRuta(rutaDesdeInicio);
            imprimirPeso(distanciaTotal(padresDesdeInicio, rutaDesdeInicio));
        }
    }

    /**
     * Encuentra una ruta desde un nodo de inicio hasta un nodo de destino sin incluir nodos intermedios.
     *
     * @param inicio  Nodo de inicio.
     * @param destino Nodo de destino.
     */
    void encontrarRutaSinIntermedio(int inicio, int destino) {
        Map<Integer, Integer> padres = dijkstra(inicio);
        List<Integer> ruta = construirRuta(padres, destino);

        if (ruta.isEmpty()) {
            System.out.println("No hay ruta válida.");
        } else {
            imprimirRuta(ruta);
            imprimirPeso(distanciaTotal(padres, ruta));
        }
    }

    /**
     * Construye la ruta a partir de un conjunto de padres y un nodo de destino.
     *
     * @param padres  Mapa que asocia cada nodo con su padre en la ruta.
     * @param destino Nodo de destino.
     * @return Lista de nodos que forman la ruta desde el nodo de inicio hasta el nodo de destino.
     */
    private List<Integer> construirRuta(Map<Integer, Integer> padres, int destino) {
        List<Integer> ruta = new ArrayList<>();
        Integer actual = destino;

        while (actual != null) {
            ruta.add(actual);
            actual = padres.get(actual);
        }

        Collections.reverse(ruta);
        return ruta;
    }

    /**
     * Calcula la distancia total de una ruta dada.
     *
     * @param padres Mapa que asocia cada nodo con su padre en la ruta.
     * @param ruta   Lista de nodos que forman la ruta.
     * @return Distancia total de la ruta.
     */
    private int distanciaTotal(Map<Integer, Integer> padres, List<Integer> ruta) {
        int distanciaTotal = 0;

        for (int i = 0; i < ruta.size() - 1; i++) {
            int nodoActual = ruta.get(i);
            int nodoSiguiente = ruta.get(i + 1);

            distanciaTotal += grafo.obtenerPesoArista(nodoActual, nodoSiguiente);
        }

        return distanciaTotal;
    }

    /**
     * Imprime la lista de nodos que forman una ruta.
     *
     * @param ruta Lista de nodos que forman la ruta.
     */
    private void imprimirRuta(List<Integer> ruta) {
        System.out.print("Ruta: ");
        for (int nodo : ruta) {
            System.out.print(nodo + " ");
        }
        System.out.println();
    }

    /**
     * Imprime la distancia total de una ruta.
     *
     * @param peso Distancia total de la ruta.
     */
    private void imprimirPeso(int peso) {
        System.out.println("Distancia total: " + peso);
    }

    /**
     * Método principal que crea un grafo aleatorio, imprime sus conexiones y realiza la búsqueda de ruta.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        Grafo grafoAleatorio = new Grafo();
        grafoAleatorio.generarGrafo(30);
        grafoAleatorio.imprimirGrafo();

        Ruta rutador = new Ruta(grafoAleatorio);
        rutador.encontrarRuta();
    }
}


