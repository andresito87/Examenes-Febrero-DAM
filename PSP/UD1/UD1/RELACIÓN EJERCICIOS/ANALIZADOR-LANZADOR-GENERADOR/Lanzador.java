
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio13;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Usuario
 */
public class Lanzador {
    
    public static void main(String[] argv){

        try{
            Process p = Runtime.getRuntime().exec("java Generador");

	    InputStream is = p.getInputStream();
                
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
                              
            String linea;
            try {
                    while ((linea = br.readLine()) != null) {
                        System.out.println(linea);
                 }
             } catch (IOException ex) {
                    System.err.println("Fallo en la lectura");
                    System.err.println(ex.toString());
            }
	
        } catch (SecurityException ex) {
                System.out.println("Ha ocurrido un error de Seguridad."
                        + "No se ha podido crear el proceso por falta de permisos.");
        } catch (IOException ex) {
            Logger.getLogger(Lanzador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
