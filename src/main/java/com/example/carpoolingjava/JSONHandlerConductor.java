package com.example.carpoolingjava;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JSONHandlerConductor {

    private static final String FILE_PATH = "conductor.json";
    private static final Gson gson = new Gson();

    public static List<Conductores> leerConductores() {
        List<Conductores> conductoresList = new ArrayList<>();

        try (Reader reader = new FileReader(FILE_PATH)) {
            Type conductoresListType = new TypeToken<ArrayList<Conductores>>(){}.getType();
            conductoresList = gson.fromJson(reader, conductoresListType);

            if (conductoresList == null) {
                conductoresList = new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            // El archivo no existe, lo creamos
            guardarConductores(conductoresList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return conductoresList;
    }

    public static void guardarConductores(List<Conductores> conductoresList) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(conductoresList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

