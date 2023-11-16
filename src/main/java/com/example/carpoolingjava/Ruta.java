package com.example.carpoolingjava;


import java.util.*;

public class Ruta {
    private Grafo grafo;

    public Ruta(Grafo grafo) {
        this.grafo = grafo;
    }

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

    private int distanciaTotal(Map<Integer, Integer> padres, List<Integer> ruta) {
        int distanciaTotal = 0;

        for (int i = 0; i < ruta.size() - 1; i++) {
            int nodoActual = ruta.get(i);
            int nodoSiguiente = ruta.get(i + 1);

            distanciaTotal += grafo.obtenerPesoArista(nodoActual, nodoSiguiente);
        }

        return distanciaTotal;
    }

    private void imprimirRuta(List<Integer> ruta) {
        System.out.print("Ruta: ");
        for (int nodo : ruta) {
            System.out.print(nodo + " ");
        }
        System.out.println();
    }

    private void imprimirPeso(int peso) {
        System.out.println("Distancia total: " + peso);
    }

    public static void main(String[] args) {
        Grafo grafoAleatorio = new Grafo();
        grafoAleatorio.generarGrafo(30);
        grafoAleatorio.imprimirGrafo();

        Ruta rutador = new Ruta(grafoAleatorio);
        rutador.encontrarRuta();
    }
}


