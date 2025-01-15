/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasepalindromo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */
public class ClasePalindromo {

    public static void main(String[] args) throws IOException {

        try {
            String palabra = "";
            InputStreamReader streamTuberia = null;
            BufferedReader tuberia = null;
            streamTuberia = new InputStreamReader(System.in);
            tuberia = new BufferedReader(streamTuberia);

            boolean preparado = tuberia.ready();

            if (preparado) {

                palabra = tuberia.readLine();

                if (esPalindromo(palabra)) {
                    System.out.println("La palabra " + palabra + " si es un palindromo");
                } else {
                    System.out.println("La palabra " + palabra + " no es un palindromo");        
                }

            } else {
                //Pedimo palabra por teclado
                Scanner teclado = new Scanner(System.in);
                System.out.println("Dime una palabra");
                palabra = teclado.nextLine();
                
                if (esPalindromo(palabra)) {
                    System.out.println("La palabra " + palabra + " si es un palindromo");
                } else {
                    System.out.println("La palabra " + palabra + " no es un palindromo");        
                }
                
                
            }
            
        } catch (IOException ex) {
            escribirAArchivo("Ha habido un error de tipo IO");
            
        }
    }

    public static boolean esPalindromo(String palabra) {
        System.out.println("Soy el analizador de palabras analizando la palabra..." + palabra);
        String palabraAAnalizar = palabra;
        palabraAAnalizar = palabraAAnalizar.replace(" ", "");
        String palabraInversa = new StringBuilder(palabraAAnalizar).reverse().toString();
        return palabraAAnalizar.equals(palabraInversa);

    }
    
    public static void escribirAArchivo(String error) throws IOException{
        
        String errorGuardado = error;
        File f = new File("Errores.txt");
        if (!f.exists())
            f.createNewFile();
        FileWriter fw = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        pw.write(error);
        
    }

}
