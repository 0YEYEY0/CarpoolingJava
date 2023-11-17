package com.example.carpoolingjava;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

/**
 * Esta aplicación permite calificar a usuarios y conductores, así como agregar amigos.
 */
public class CalificacionApp extends Application {

    private TextField carnetField;
    private TextField puntuacionField;
    private TextArea usuariosTextArea;
    private TextArea conductoresTextArea;

    private TextField carnetAmigoField;
    private TextField numeroAmigoField;

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

        carnetAmigoField = new TextField();
        numeroAmigoField = new TextField();

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

        Button agregarAmigoButton = new Button("Agregar Amigo");
        agregarAmigoButton.setOnAction(e -> agregarAmigo());

        grid.add(new Label("Carnet Amigo:"), 0, 6);
        grid.add(carnetAmigoField, 1, 6);
        grid.add(new Label("Número Amigo:"), 0, 7);
        grid.add(numeroAmigoField, 1, 7);
        grid.add(agregarAmigoButton, 0, 8, 2, 1);
    }

    /**
     * Maneja el evento de calificación.
     */
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
                        usuario.calificar(puntuacion);
                        JSONHandlerUsuario.escribirUsuarios(usuarios, "usuarios.json");
                        encontrado = true;
                        actualizarUsuariosTextArea();
                        break;
                    }
                }

                if (!encontrado) {
                    for (Conductores conductor : conductores) {
                        if (carnet.equals(conductor.getCarnet())) {
                            conductor.calificar(puntuacion);
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

    /**
     * Maneja el evento de agregar amigo.
     */
    private void agregarAmigo() {
        String carnetAmigo = carnetAmigoField.getText();
        String numeroAmigoText = numeroAmigoField.getText();

        if (!carnetAmigo.isEmpty() && !numeroAmigoText.isEmpty()) {
            try {
                int numeroAmigo = Integer.parseInt(numeroAmigoText);

                List<Usuario> usuarios = JSONHandlerUsuario.leerUsuarios("usuarios.json");
                Optional<Usuario> usuarioOptional = usuarios.stream().filter(u -> u.getCarnet().equals(carnetAmigo)).findFirst();

                if (usuarioOptional.isPresent()) {
                    Usuario usuario = usuarioOptional.get();
                    JSONHandlerUsuario.agregarAmigo(usuario, numeroAmigo);
                    System.out.println("Amigo agregado exitosamente.");
                } else {
                    System.out.println("Usuario no encontrado con el carnet: " + carnetAmigo);
                }                JSONHandlerConductor.agregarAmigo(carnetAmigo, numeroAmigo);

                System.out.println("Amigo agregado exitosamente.");
            } catch (NumberFormatException e) {
                System.out.println("El número de amigo debe ser un número entero.");
            }
        } else {
            System.out.println("Por favor, ingrese el carnet y el número de amigo.");
        }
    }


    /**
     * Actualiza el área de texto de usuarios con la información actualizada.
     */
    private void actualizarUsuariosTextArea() {
        List<Usuario> usuarios = JSONHandlerUsuario.leerUsuarios("usuarios.json");
        if (usuarios != null) {
            StringBuilder usuariosText = new StringBuilder();
            for (Usuario usuario : usuarios) {
                usuariosText.append(usuario.getCarnet()).append(": ").append(usuario.getPromedioCalificacion()).append("\n");
            }
            usuariosTextArea.setText(usuariosText.toString());
        }
    }

    /**
     * Actualiza el área de texto de conductores con la información actualizada.
     */
    private void actualizarConductoresTextArea() {
        List<Conductores> conductores = JSONHandlerConductor.leerConductores();
        if (conductores != null) {
            StringBuilder conductoresText = new StringBuilder();
            for (Conductores conductor : conductores) {
                conductoresText.append(conductor.getCarnet()).append(": ").append(conductor.getPromedioCalificacion()).append("\n");
            }
            conductoresTextArea.setText(conductoresText.toString());
        }
    }
}




