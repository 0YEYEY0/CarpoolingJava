package com.example.carpoolingjava;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que maneja la lectura y escritura de datos de usuarios en formato JSON.
 */
public class JSONHandlerUsuario {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILE_PATH = "usuarios.json";

    /**
     * Escribe la lista de usuarios en un archivo JSON.
     *
     * @param usuarios Lista de usuarios a ser escrita.
     * @param filePath Ruta del archivo donde se guardará la información.
     */
    public static void escribirUsuarios(List<Usuario> usuarios, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(usuarios, writer);
            System.out.println("Usuarios registrados: " + usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Agrega un amigo al usuario especificado por su número de amigo.
     *
     * @param usuario      Usuario al que se le agregará el amigo.
     * @param numeroAmigo  Número del nuevo amigo a agregar.
     */
    public static void agregarAmigo(Usuario usuario, int numeroAmigo) {
        usuario.agregarAmigo(numeroAmigo);
        List<Usuario> usuarios = leerUsuarios(FILE_PATH);
        escribirUsuarios(usuarios, FILE_PATH);
    }

    /**
     * Lee la información de los usuarios desde un archivo JSON.
     *
     * @param filePath Ruta del archivo desde donde se leerá la información.
     * @return Lista de usuarios leída desde el archivo, o una lista vacía si el archivo no existe.
     */
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





