package com.example.carpoolingjava;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class CalificacionApp extends Application {

    private TextField carnetField;
    private TextField puntuacionField;
    private TextArea usuariosTextArea;
    private TextArea conductoresTextArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calificación App");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        carnetField = new TextField();
        carnetField.setPromptText("Carnet");

        puntuacionField = new TextField();
        puntuacionField.setPromptText("Puntuación");

        Button calificarButton = new Button("Calificar");
        calificarButton.setOnAction(e -> calificar());

        usuariosTextArea = new TextArea();
        conductoresTextArea = new TextArea();

        grid.add(new Label("Carnet:"), 0, 0);
        grid.add(carnetField, 1, 0);
        grid.add(new Label("Puntuación:"), 0, 1);
        grid.add(puntuacionField, 1, 1);
        grid.add(calificarButton, 0, 2, 2, 1);
        grid.add(new Label("Usuarios:"), 0, 3);
        grid.add(usuariosTextArea, 1, 3);
        grid.add(new Label("Conductores:"), 0, 4);
        grid.add(conductoresTextArea, 1, 4);

        actualizarUsuariosTextArea();
        actualizarConductoresTextArea();

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calificar() {
        String carnet = carnetField.getText();
        String puntuacionText = puntuacionField.getText();

        if (!carnet.isEmpty() && !puntuacionText.isEmpty()) {
            try {
                int puntuacion = Integer.parseInt(puntuacionText);

                List<Usuario> usuarios = JSONHandlerUsuario.leerUsuarios("usuarios.json");
                List<Conductores> conductores = JSONHandlerConductor.leerConductores();

                boolean encontrado = false;

                for (Usuario usuario : usuarios) {
                    if (carnet.equals(usuario.getCarnet())) {
                        usuario.setCalificacion(usuario.getCalificacion() + puntuacion);
                        JSONHandlerUsuario.escribirUsuarios(usuarios, "usuarios.json");
                        encontrado = true;
                        actualizarUsuariosTextArea();
                        break;
                    }
                }

                if (!encontrado) {
                    for (Conductores conductor : conductores) {
                        if (carnet.equals(conductor.getCarnet())) {
                            conductor.setCalificacion(conductor.getCalificacion() + puntuacion);
                            JSONHandlerConductor.guardarConductores(conductores);
                            actualizarConductoresTextArea();
                            break;
                        }
                    }
                }

                if (!encontrado) {
                    System.out.println("Carnet no encontrado o no coincide con ningún usuario o conductor.");
                }
            } catch (NumberFormatException e) {
                System.out.println("La puntuación debe ser un número entero.");
            }
        } else {
            System.out.println("Por favor, ingrese el carnet y la puntuación.");
        }
    }

    private void actualizarUsuariosTextArea() {
        List<Usuario> usuarios = JSONHandlerUsuario.leerUsuarios("usuarios.json");
        if (usuarios != null) {
            StringBuilder usuariosText = new StringBuilder();
            for (Usuario usuario : usuarios) {
                usuariosText.append(usuario.getCarnet()).append(": ").append(usuario.getCalificacion()).append("\n");
            }
            usuariosTextArea.setText(usuariosText.toString());
        }
    }

    private void actualizarConductoresTextArea() {
        List<Conductores> conductores = JSONHandlerConductor.leerConductores();
        if (conductores != null) {
            StringBuilder conductoresText = new StringBuilder();
            for (Conductores conductor : conductores) {
                conductoresText.append(conductor.getCarnet()).append(": ").append(conductor.getCalificacion()).append("\n");
            }
            conductoresTextArea.setText(conductoresText.toString());
        }
    }
}


