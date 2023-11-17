package com.example.carpoolingjava;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Clase que maneja la lectura y escritura de datos de conductores en formato JSON.
 */
public class JSONHandlerConductor {

    private static final String FILE_PATH = "conductor.json";
    private static final Gson gson = new Gson();

    /**
     * Lee la información de los conductores desde un archivo JSON.
     *
     * @return Lista de conductores leída desde el archivo, o una lista vacía si el archivo no existe.
     */
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

    /**
     * Guarda la lista de conductores en un archivo JSON.
     *
     * @param conductoresList Lista de conductores a ser guardada.
     */
    public static void guardarConductores(List<Conductores> conductoresList) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(conductoresList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Agrega un amigo al conductor especificado por su carnet.
     *
     * @param carnetConductor Carnet del conductor al que se le agregará el amigo.
     * @param carnetAmigo Carnet del nuevo amigo a agregar.
     */
    public static void agregarAmigo(String carnetConductor, int carnetAmigo) {
        List<Conductores> conductores = leerConductores();
        Optional<Conductores> conductorOptional = conductores.stream().filter(c -> c.getCarnet().equals(carnetConductor)).findFirst();

        if (conductorOptional.isPresent()) {
            Conductores conductor = conductorOptional.get();
            conductor.agregarAmigo(String.valueOf(carnetAmigo));
            guardarConductores(conductores);
        } else {
            System.out.println("Conductor no encontrado con el carnet: " + carnetConductor);
        }
    }
}



