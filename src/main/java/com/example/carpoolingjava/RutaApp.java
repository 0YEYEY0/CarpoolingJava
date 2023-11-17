package com.example.carpoolingjava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Clase principal que representa la aplicación de Carpooling.
 */
public class RutaApp extends Application {
    private Grafo grafo;
    private Ruta rutador;

    /**
     * Método principal que inicia la aplicación.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Método que configura la interfaz gráfica de la aplicación.
     *
     * @param primaryStage Escenario principal de la aplicación.
     */
    @Override
    public void start(Stage primaryStage) {
        // Crear el grafo y el rutador
        grafo = new Grafo();
        grafo.generarGrafo(30);

        rutador = new Ruta(grafo);

        // Configurar la interfaz gráfica
        primaryStage.setTitle("Carpooling App");

        Label label = new Label("¿Ocupas parar en algún lugar? (s/n)");
        TextField respuestaField = new TextField();
        Button button = new Button("Calcular Ruta");

        button.setOnAction(e -> {
            String respuesta = respuestaField.getText().toLowerCase();
            if (respuesta.equals("s")) {
                // Pedir nodos de partida, intermedio y destino
                int inicio = obtenerNodo("Ingresa el nodo de partida:");
                int intermedio = obtenerNodo("Ingresa el nodo por el que debes pasar:");
                int destino = obtenerNodo("Ingresa el nodo de llegada:");

                rutador.encontrarRutaConIntermedio(inicio, intermedio, destino);
            } else if (respuesta.equals("n")) {
                // Pedir nodos de partida y destino
                int inicio = obtenerNodo("Ingresa el nodo de partida:");
                int destino = obtenerNodo("Ingresa el nodo de llegada:");

                rutador.encontrarRutaSinIntermedio(inicio, destino);
            } else {
                System.out.println("Respuesta no válida.");
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, respuestaField, button);

        Scene scene = new Scene(layout, 300, 150);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * Método que muestra un cuadro de diálogo para obtener el número de un nodo.
     *
     * @param mensaje Mensaje que se muestra en el cuadro de diálogo.
     * @return Número de nodo ingresado por el usuario.
     */
    private int obtenerNodo(String mensaje) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(null);
        dialog.setContentText(mensaje);

        return Integer.parseInt(dialog.showAndWait().orElse("0"));
    }
}


