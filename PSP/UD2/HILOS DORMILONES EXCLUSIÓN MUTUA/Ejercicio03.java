package Ejercicio8;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ejercicio03 {

    public static void main(String[] args) {
        long arrayTiempos[]= {5, 1, 3, 4, 2};
        
        System.out.println("HILOS DORMILONES CONCURRENTES CON EXCLUSIÓN MUTUA");
        System.out.println("-------------------------------------------------");

        ArrayList<HiloDormilonExclusivo> hilos = new ArrayList<HiloDormilonExclusivo>();
        
        for(int i=0;i<arrayTiempos.length;i++){
            hilos.add(new HiloDormilonExclusivo("Dormilón "+(i+1),arrayTiempos[i]));
        }
        
        for(int i=0;i<hilos.size();i++){
            hilos.get(i).start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ejercicio03.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
}
