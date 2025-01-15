package Ejercicio7;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ejercicio02 {

    public static void main(String[] args) {
        long arrayTiempos[]= {5, 1, 3, 4, 2};
        
        System.out.println("HILOS DORMILONES CONCURRENTES");
        System.out.println("-----------------------------");

        ArrayList<HiloDormilon> hilos = new ArrayList<HiloDormilon>();
        
        for(int i=0;i<arrayTiempos.length;i++){
            hilos.add(new HiloDormilon("Dormilón "+(i+1),arrayTiempos[i]));
        }
        
        for(int i=0;i<hilos.size();i++){
            hilos.get(i).start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ejercicio02.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
