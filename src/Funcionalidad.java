
import java.io.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jorge Aguirre
 */

public class Funcionalidad {

    public File dirActual;

    public Funcionalidad(String path) {
        dirActual = new File(path);
    }

    public String escribir(String mensaje, String path) {
        File file = new File(path);
        String msj = "";

        if (file.exists()) {
            if (file.isFile()) {
                try (FileWriter writer = new FileWriter(file, true)) { // true habilita el modo de anexado
                    writer.write(mensaje);
                    writer.flush();
                    
                    msj = "Texto agregado al archivo";
                } catch (IOException e) {
                    msj = "Error al escribir en el archivo";
                }
                
            } else {
                msj = "Debe seleccionar un archivo primero";
            }
        } else {
            msj = "Error: Archivo inexistente";
        }
        return msj;
    }

    public String mkdir(String path) {
        String msj = "";
        File folder = new File(path);

        if (folder.exists()) {
            msj = "Error: Esta carpeta ya existe";
            return msj;
        } else {
            if (folder.mkdir()) {
                msj = "Carpeta creada exitosamente";
            } else {
                msj = "Error: No se pudo crear la carpeta";
            }
            return msj;
        }
    }

    public String cd(String path) {
        if (path.charAt(0) != '/') {
            File newDir = new File(dirActual.getAbsolutePath() + "/" + path);

            if (!newDir.isDirectory()) {
                return "Error: La direccion tiene que ser una carpeta";
            }

            dirActual = newDir;
            return "";
        }

        dirActual = new File(path);
        return "";
    }

    public void vaciar(File vacioFile) {
        if (vacioFile.isDirectory()) {
            for (File fileVacio : vacioFile.listFiles()) {
                try {
                    if (fileVacio.isDirectory()) {
                        vaciar(fileVacio);
                    }
                    fileVacio.delete();
                } catch (Exception e) {
                    System.out.println("Error al eliminar: " + fileVacio.getAbsolutePath());
                }
            }
        }
    }

    public String eliminar(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                vaciar(file);
                try {
                    if (file.delete()) {
                        return "Carpeta eliminada";
                        
                    } else {
                        return "Error: No se pudo eliminar la carpeta.";
                    }
                } catch (Exception e) {
                    return "Error: No se pudo eliminar la carpeta.";
                }
            } else if (file.isFile()) {
                try {
                    if (file.delete()) {
                        return "Archivo eliminado";
                    } else {
                        return "Error: No se pudo eliminar el archivo.";
                    }
                } catch (Exception e) {
                    return "Error: No se pudo eliminar el archivo.";
                }
            }
        }
        return "Error: El archivo o carpeta no existe.";
    }

    public String getPath() {
        try {
            return dirActual.getCanonicalPath();
        } catch (IOException e) {
            return dirActual.getAbsolutePath();
        }
    }

    public String mfile(String path) {
        String msj = "";
        File file = new File(path);

        if (file.exists()) {
            msj = "Error: Este archivo ya existe";
            return msj;
        } else {
            msj = "Archivo creado existosamente";
            try {
                file.createNewFile();
            } catch (IOException e) {
                msj = "Error: no se pudo crear";
            }
            
            return msj;
        }
    }

    public static String leer(String path) {
        File file = new File(path);
        String msj = "";

        if (file.exists()) {
            if (file.isFile()) {
                try {
                    FileReader reader = new FileReader(file);
                    String contenido = "";
                    for (int i = reader.read(); i != -1; i = reader.read()) {
                        contenido += (char) i;
                    }
                    return contenido;
                    
                } catch (IOException e) {
                    msj = "Error: No se pudo leer";
                    return msj;
                }
            } else {
                msj = "Error: Debe seleccionar un archivo";
                return msj;
            }
        } else {
            msj = "Error: Archivo inexistente";
            return msj;
        }
    }

    public static String listar(String path) {
        String list = "";
        File listFile = new File(path);

        if (listFile.isDirectory()) {
            for (File file : listFile.listFiles()) {
                
                list += "\n ->" + file.getName();
            }
            return list;
        } else {
            return "Error: Debe seleccionar un directorio";
        }
    }
}