/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palindromomain;

import java.io.IOException;
import java.io.InputStream;

public class PalindromoMain {

    public static void main(String[] args) throws IOException, InterruptedException {

        /*ProcessBuilder pb = new ProcessBuilder("CMD", "/C",
                "ECHO OSO | java -jar C:\\Users\\pc\\Desktop\\PSP_ejercicios_preparacion_febrero\\ClasePalindromo\\dist\\ClasePalindromo.jar ");
        */
        ProcessBuilder pb = new ProcessBuilder("CMD", "/C",
                "java -jar C:\\Users\\pc\\Desktop\\PSP_ejercicios_preparacion_febrero\\ClasePalindromo\\dist\\ClasePalindromo.jar ");
        Process proceso;
        proceso = pb.start();

        int estadoProceso = proceso.waitFor();

        System.out.println("\n Clase Ejecutada..");

        //Leemos respuesta del otro programa
        if (estadoProceso == 0) {
            InputStream is = null;
            int c;
            is = proceso.getInputStream();
            while ((c = is.read()) != -1) {
                System.out.print((char) c);
            }
        } else {
            System.out.println("Error en el proceso");
        }
       
                
                

    }

}
