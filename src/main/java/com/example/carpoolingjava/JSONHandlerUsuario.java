package com.example.carpoolingjava;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONHandlerUsuario {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void escribirUsuarios(List<Usuario> usuarios, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(usuarios, writer);
            System.out.println("Usuarios registrados: " + usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Usuario> leerUsuarios(String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            // Si el archivo no existe, devuelve una lista vacía
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(file)) {
            Usuario[] usuariosArray = gson.fromJson(reader, Usuario[].class);
            return usuariosArray != null ? List.of(usuariosArray) : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}




