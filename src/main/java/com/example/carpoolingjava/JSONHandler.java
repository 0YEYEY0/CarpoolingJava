package com.example.carpoolingjava;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class JSONHandler {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void escribirUsuario(Usuario usuario, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(usuario, writer);
            System.out.println("Usuario registrado: " + usuario);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Usuario leerUsuario(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, Usuario.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}




