package Ejercicio4;

import Ejercicio3.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Principal {

    public static void main(String[] args) {
        ArrayList<ComprobadorPrimo> primos = new ArrayList<ComprobadorPrimo>();
        File f = null;
        FileReader fr = null;
        BufferedReader br = null;
        String cadena = "";
        long numero;
        
        System.out.println("COMPROBACIÓN DE NÚMEROS PRIMOS");
        System.out.println("------------------------------");
        System.out.println("Leyendo archivo de entrada.");
        System.out.print("\nNúmeros leídos: [");
        try {   
            f = new File("numeros.txt");
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            
            cadena = br.readLine();
            while (cadena != null) {
                numero = Long.parseLong(cadena);
                primos.add(new ComprobadorPrimo(numero));
                System.out.print(numero);
                cadena = br.readLine();
                if(cadena != null)
                    System.out.print(", ");
                else
                    System.out.println("]");
            }
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Lanzando comprobadores concurrentes de primalidad.\n");
        
        for(int i=0; i<primos.size();i++){
            primos.get(i).start();
        }
        
        for(int i=0; i<primos.size();i++){
            try {
                primos.get(i).join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println("\nAnalizados todos los números. Fin del programa");
    }
}
