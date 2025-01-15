package Ejercicio6ULAM;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */
public class SucesionThread extends Thread {

    Ulam ulam;
    String nombreHilo;

    SucesionThread(Ulam ulam, String nombre) {

        this.ulam = ulam;
        nombreHilo = nombre;
    }

    public String getNombre() {

        return nombreHilo;
    }
    
    

    @Override
    public void run() {

        synchronized (ulam) {

            ulam.generaTermino(this.nombreHilo);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SucesionThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
