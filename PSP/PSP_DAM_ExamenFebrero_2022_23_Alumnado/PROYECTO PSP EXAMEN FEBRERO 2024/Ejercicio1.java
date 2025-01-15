/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio1;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Ejercicio1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        if (args != null && args.length > 0 && args[0] != null && !args[0].isEmpty()) {
            
        String parametro;
        Process proceso=null;
        InputStream entrada=null;
        
        parametro = args[0];
        try {
            proceso = new ProcessBuilder ("CMD", "/C", parametro).start();
            entrada = proceso.getInputStream();
            int caracter;
            while ((caracter = entrada.read()) != -1) {
                System.out.print((char) caracter);
            }
        
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                entrada.close();
            } catch (IOException ex) {
                Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.exit (0);
    }else   {
            BufferedOutputStream bufferSalida = null;
            FileOutputStream ficheroSalida = null;
            PrintStream ps = null;
            try {
                File fichero = new File("errores.txt");
                try {
                    ficheroSalida = new FileOutputStream(fichero, true);
                } catch (FileNotFoundException ex) {
                    System.err.println("FIchero no encontrado.");
                }
                bufferSalida = new BufferedOutputStream(ficheroSalida);
                ps = new PrintStream(bufferSalida, true);
                System.setOut(ps);
                System.setErr(ps);

                System.err.println("No hay argumentos en el main");
            } catch (Exception e) {
                System.err.println("Ha ocurrido un error: " + e.getMessage());
            } finally {
                // Se cierran las conexiones
                try {
                    if (ficheroSalida != null) {
                        ficheroSalida.close();
                    }
                    if (bufferSalida != null) {
                        bufferSalida.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                } catch (IOException ioe) {
                   
                }
            }
            System.exit(-1);
        }
                
    }
    
}
