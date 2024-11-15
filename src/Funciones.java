
import java.io.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jorge Aguirre
 */


public class Funciones {

    public File dirActual;
    
    public Funciones(String path) {
        dirActual = new File(path);
    }
    
    public String escribir(String mensaje, String path) {
        File file = new File(path);
        String msj = "";
        
        if (file.exists()) {
            if (file.isFile()) {
                
                try {
                    FileWriter writer = new FileWriter(file);
                    writer.write(mensaje);
                    writer.flush();
                } catch (IOException e) {
                    msj = "Error al crear";
                    
                }
                
                msj = "Texto Agregado";
                return msj;
                
            } else {
                msj = "Debe de seleccionar un archivo primero";
                return msj;
                
            }
        } else {
            msj = "Error: Archivo inexistente";
            return msj;
            
        }
    }
    
    public String mkdir(String path) {
        String msj = "";
        File folder = new File(path);
        
        if (folder.exists()) {
            msj = "Error: Esta carpeta ya existe";
            return msj;
            
        } else {
            msj = "Carpeta creada existosamente";
            folder.mkdir();
            
            return msj;
        }
    }

}