package com.example.carpoolingjava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

/**
 * Clase principal que representa la aplicación para mostrar el Top 5 de conductores.
 */
public class TopConductores extends Application {

    private TextArea conductoresTop5TextArea;

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
        primaryStage.setTitle("Top 5 Conductores App");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        conductoresTop5TextArea = new TextArea();
        grid.add(new Label("Top 5 Conductores:"), 0, 0);
        grid.add(conductoresTop5TextArea, 1, 0);

        mostrarTop5Conductores();

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Método que muestra el Top 5 de conductores en el área de texto.
     */
    private void mostrarTop5Conductores() {
        List<Conductores> conductoresList = JSONHandlerConductor.leerConductores();

        if (!conductoresList.isEmpty()) {
            // Ordenar la lista de conductores usando el algoritmo de Insertion Sort
            InsertionSort.sortByCalificaciones(conductoresList);

            // Mostrar los 5 primeros conductores
            conductoresTop5TextArea.clear();
            int top5Count = Math.min(conductoresList.size(), 5);
            for (int i = 0; i < top5Count; i++) {
                Conductores conductor = conductoresList.get(i);
                conductoresTop5TextArea.appendText(conductor.getCarnet() + ": " + conductor.getCantidadCalificaciones() + " viajes\n");
            }
        } else {
            conductoresTop5TextArea.setText("No hay conductores registrados.");
        }
    }
}
