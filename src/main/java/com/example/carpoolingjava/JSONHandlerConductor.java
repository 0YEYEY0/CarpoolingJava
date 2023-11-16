package com.example.carpoolingjava;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class JSONHandlerConductor {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void escribirConductores(Conductores conductores, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(conductores, writer);
            System.out.println("Conductoro registrado: " + conductores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Conductores leerConductores(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, Conductores.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
