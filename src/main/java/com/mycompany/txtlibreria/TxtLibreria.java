/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.txtlibreria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TxtLibreria es una clase de utilidad para operaciones con archivos de texto.
 */

public class TxtLibreria {
        private String nombreArchivo;
        
    /**
     * Constructor por defecto para TxtLibreria.
     */
    public TxtLibreria() {
      
    }

    /**
     * Mueve un archivo desde la ruta de origen a la ruta de destino especificadas.
     *
     * @param rutaOrigen La ruta del archivo de origen.
     * @param rutaDestino La ruta del archivo de destino.
     */
  public void moverArchivo(String rutaOrigen, String rutaDestino) {
    // Obtener las rutas del origen y destino
    Path origenPath = Paths.get(rutaOrigen);
    Path destinoPath = Paths.get(rutaDestino, origenPath.getFileName().toString());  // Ruta de destino con el mismo nombre de archivo

    // Mostrar información sobre las rutas
    System.out.println("Ruta de origen: " + origenPath.toString());
    System.out.println("Ruta de destino: " + destinoPath.toString());

    // Verificar si el archivo de origen existe 
    if (Files.exists(origenPath) && Files.isRegularFile(origenPath)) {
        try {
            // Mover el archivo de origen al destino
            Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING); /*StandardCopyOption.REPLACE_EXISTING =
                                                                                                      para reemplazar el archivo de destino si ya existe.*/
            System.out.println("Archivo movido exitosamente.");
        } catch (IOException e) {
            System.out.println("No se pudo mover el archivo.");
            e.printStackTrace();
        }
    } else {
        System.out.println("El archivo en la ruta de origen no existe o no es un archivo regular.");
    }
}
    /**
     * Copia un archivo desde una ruta de origen a una ruta de destino.
     *
     * @param rutaOrigen  La ruta del archivo de origen.
     * @param rutaDestino La ruta del archivo de destino.
     */
 public void copiarYPegarArchivo(String rutaOrigen, String rutaDestino) {
    Scanner scanner = new Scanner(System.in);
    try {
        // Obtener el nombre del archivo de origen
        String nombreArchivoOrigen = Paths.get(rutaOrigen).getFileName().toString();

        // Leer el contenido del archivo de origen
        List<String> contenido = Files.readAllLines(Paths.get(rutaOrigen));

        // Obtener el nombre para el archivo de destino
        System.out.println("");
        System.out.print("Ingrese el nombre para el archivo de destino agrenado (.txt): ");
        String nombreArchivoDestino = scanner.nextLine();

        // Componer la ruta del archivo de destino con el nombre proporcionado
        Path pathDestino = Paths.get(rutaDestino, nombreArchivoDestino);

        // Copiar el archivo de origen al destino
        Files.copy(Paths.get(rutaOrigen), pathDestino, StandardCopyOption.REPLACE_EXISTING);

        // Escribir el contenido del archivo de origen en el archivo de destino
        Files.write(pathDestino, contenido);

        System.out.println("Archivo copiado y pegado exitosamente.");
    } catch (IOException e) {
        e.printStackTrace();
    }      
    
}
     /**
     * Elimina un archivo con la ruta especificada.
     *
     * @param rutaArchivo La ruta del archivo a eliminar.
     */
    public void eliminarArchivo(String rutaArchivo) {
        try {
            File archivo = new File(rutaArchivo);
            if (archivo.delete()) {
                System.out.println("Archivo eliminado exitosamente.");
            } else {
                System.out.println("No se pudo eliminar el archivo.");
            }
        } catch (Exception e) {
           
        }
    }

    /**
     * Crea un nuevo archivo con el nombre y la ruta especificados.
     *
     * @param nombreArchivo El nombre del archivo a crear.
     * @param rutaDestino   La ruta donde se creará el archivo.
     */
    public void crearArchivo(String nombreArchivo, String rutaDestino) {
        try {
            Path rutaArchivo = Paths.get(rutaDestino, nombreArchivo);
            Files.createFile(rutaArchivo);
            System.out.println("Archivo creado exitosamente en: " + rutaArchivo.toString());
        } catch (IOException e) {
            System.out.println("Verifica la Ruta o Si el archivo ya existe " );
        }
    }

      /**
     * Lee el contenido de un archivo y lo devuelve como una cadena.
     *
     * @param nombreArchivo El nombre del archivo a leer.
     */
    public void lectorTxt(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    
      /**
     * Lee el contenido de un archivo y lo devuelve como una cadena.
     *
     * @return El contenido del archivo como una cadena.
     */
    public String leerTxt() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                sb.append(linea).append("\n");
            }
        } catch (IOException e){
        }
        return sb.toString();
    }
      /**
     * Escribe el contenido en un archivo en la ruta especificada.
     *
     * @param contenido    El contenido a escribir en el archivo.
     * @param rutaArchivo  La ruta del archivo en el que se escribirá el contenido.
     */
    
      public void escribirEnArchivo(String contenido, String rutaArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            writer.write(contenido);
            System.out.println("Contenido escrito en el archivo exitosamente.");
        } catch (IOException e) {
            System.err.println("No se pudo escribir en el archivo: " + e.getMessage());
        }
    }

}