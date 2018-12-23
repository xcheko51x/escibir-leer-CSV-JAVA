/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplocsv;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xcheko51x
 */
public class EjemploCSV {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        
        usuarios.add(new Usuario("Sergio P", "1234567", "sergiop@hola.com"));
        usuarios.add(new Usuario("Ana L", "7654321", "AnaL@hola.com"));
        
        //ExportarCSV(usuarios);
        
        ImportarCSV();
    }
    
    public static void ExportarCSV(List<Usuario> usuarios) {
        String salidaArchivo = "Usuarios.csv"; // Nombre del archivo
        boolean existe = new File(salidaArchivo).exists(); // Verifica si existe
        
        // Si existe un archivo llamado asi lo borra
        if(existe) {
            File archivoUsuarios = new File(salidaArchivo);
            archivoUsuarios.delete();
        }
        
        try {
            // Crea el archivo
            CsvWriter salidaCSV = new CsvWriter(new FileWriter(salidaArchivo, true), ',');
            
            // Datos para identificar las columnas
            salidaCSV.write("Nombre");
            salidaCSV.write("Telefono");
            salidaCSV.write("Email");
            
            salidaCSV.endRecord(); // Deja de escribir en el archivo
            
            // Recorremos la lista y lo insertamos en el archivo
            for(Usuario user : usuarios) {
                salidaCSV.write(user.getNombre());
                salidaCSV.write(user.getTelefono());
                salidaCSV.write(user.getEmail());
                
                salidaCSV.endRecord(); // Deja de escribir en el archivo
            }
            
            salidaCSV.close(); // Cierra el archivo
            
        } catch(IOException e) {
            e.printStackTrace();
        }    
    }
    
    public static void ImportarCSV() {
        try{
            List<Usuario> usuarios = new ArrayList<Usuario>(); // Lista donde guardaremos los datos del archivo
            
            CsvReader leerUsuarios = new CsvReader("Usuarios.csv");
            leerUsuarios.readHeaders();
            
            // Mientras haya lineas obtenemos los datos del archivo
            while(leerUsuarios.readRecord()) {
                String nombre = leerUsuarios.get(0);
                String telefono = leerUsuarios.get(1);
                String email = leerUsuarios.get(2);
                
                usuarios.add(new Usuario(nombre, telefono, email)); // Añade la informacion a la lista
            }
            
            leerUsuarios.close(); // Cierra el archivo
            
            // Recorremos la lista y la mostramos en la pantalla
            for(Usuario user : usuarios) {
                System.out.println(user.getNombre() + " , "
                    + user.getTelefono() + " , "
                    +user.getEmail());
            }
            
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
