package com.example.carpoolingjava;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class EmployeeApp extends Application {
    private static final String FILE_PATH = "usuarios.json";
    private List<Usuario> usuarios;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Employee App");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        Label carnetLabel = new Label("Carnet:");
        TextField carnetRegistro = new TextField();
        grid.add(carnetLabel, 0, 0);
        grid.add(carnetRegistro, 1, 0);

        Label nodoLabel = new Label("Nodo:");
        TextField nodoRegistro = new TextField();
        grid.add(nodoLabel, 0, 1);
        grid.add(nodoRegistro, 1, 1);

        Label direccionLabel = new Label("Dirección:");
        TextField direccionRegistro = new TextField();
        grid.add(direccionLabel, 0, 2);
        grid.add(direccionRegistro, 1, 2);

        Button registroButton = new Button("Registrar Usuario");
        grid.add(registroButton, 0, 3);

        Label carnetLoginLabel = new Label("Carnet:");
        TextField carnetLogin = new TextField();
        grid.add(carnetLoginLabel, 0, 4);
        grid.add(carnetLogin, 1, 4);

        Button loginButton = new Button("Iniciar Sesión");
        grid.add(loginButton, 0, 5);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);

        // Cargar usuarios existentes
        usuarios = JSONHandlerUsuario.leerUsuarios(FILE_PATH);

        // Registro de usuario
        registroButton.setOnAction(e -> {
            String carnet = carnetRegistro.getText();
            int nodo = Integer.parseInt(nodoRegistro.getText());
            String direccion = direccionRegistro.getText();

            Usuario nuevoUsuario = new Usuario(carnet, nodo, direccion);
            usuarios.add(nuevoUsuario);

            JSONHandlerUsuario.escribirUsuarios(usuarios, FILE_PATH);
        });

        // Inicio de sesión
        loginButton.setOnAction(e -> {
            String carnet = carnetLogin.getText();

            Usuario usuario = usuarios.stream()
                    .filter(u -> u.getCarnet().equals(carnet))
                    .findFirst()
                    .orElse(null);

            if (usuario != null) {
                System.out.println("Inicio de sesión exitoso. Usuario actual: " + usuario);
            } else {
                System.out.println("Inicio de sesión fallido. Usuario no encontrado.");
            }
        });

        primaryStage.show();
    }
}

