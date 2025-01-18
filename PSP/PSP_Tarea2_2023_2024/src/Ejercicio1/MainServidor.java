package Ejercicio1;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Programa principal para lanzar el proceso de un Servidor Secuencial

 
 */
public class MainServidor {

    public static void main(String[] args) {
        try {
            Process nuevoProceso1 = Runtime.getRuntime().exec("cmd /c start cmd.exe /k java Servidor");
        } catch (IOException ex) {
            Logger.getLogger(MainServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
