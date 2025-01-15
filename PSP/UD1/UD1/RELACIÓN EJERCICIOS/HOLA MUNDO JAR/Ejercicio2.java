/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio02;

import java.io.*;

/**
 *
 * @author Usuario
 */
public class Ejercicio2 {
     public static void main(String args[]) throws IOException {

        Process p = new ProcessBuilder(/*"CMD", "/k", */"java", "-jar", "C:\\Relacion1\\HolaMundo.jar").start();
        try{
            InputStream is = p.getInputStream();
            int c;
            int cont=0;
            while((c= is.read())!=-1){
                System.out.print((char)c);
                cont++;
            }
            is.close();
            System.out.println("Número de caracteres del 'HolaMundo.jar': "+cont);
        } catch (SecurityException ex) {
            System.out.println("Ha ocurrido un error de Seguridad."
                    + "No se ha podido crear el proceso por falta de permisos.");
        } catch (Exception ex) {
            System.out.println("Ha ocurrido un error, descripciÃ³n: "
                    + ex.toString());
        }
    }
}
