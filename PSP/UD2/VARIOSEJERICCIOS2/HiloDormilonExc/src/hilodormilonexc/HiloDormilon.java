
package hilodormilonexc;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HiloDormilon extends Thread {
    String nombre;
    int tiempoDormir;
    
    public HiloDormilon(String nombre){
        this.nombre = nombre;
        tiempoDormir = 0;
        
    }
    
    @Override
    public void run(){
        synchronized(System.out){
            
            tiempoDormir = (int)(Math.random()*10);
           
            System.out.println("Soy el hilo dormilón " + this.getName() + 
                     " y voy a dormir " + tiempoDormir);
            try {
                Thread.sleep(tiempoDormir * 500);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloDormilon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
