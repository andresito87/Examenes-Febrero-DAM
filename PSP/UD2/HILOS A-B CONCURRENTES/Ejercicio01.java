package Ejercicio6;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Ejercicio01 {

    public static void main(String[] args) {
        System.out.println();
        System.out.println("EJECUCIÓN DE HILOS A Y B CONCURRENTES");
        System.out.println("-------------------------------------");

        HiloTipoA h1 = new HiloTipoA();
        HiloTipoB h2 = new HiloTipoB();
        
        h1.start();
        h2.start();
        
        try {
            h1.join();
            h2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Ejercicio01.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        // Mensaje final (sólo cuando todos los hilos hayan terminado)
        System.out.println("\nTodos los hilos han finalizado su ejecución.");
        System.out.println("Fin del hilo principal.");

    }
}
