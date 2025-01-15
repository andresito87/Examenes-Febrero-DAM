package ejercicioboxedor;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ClaseBoxeador extends Thread {

    ClaseRing ring;
    String nombreBoxeador;
    int totalGolpes;

    public ClaseBoxeador(ClaseRing ring, String nombreBoxeador) {
        this.ring = ring;
        this.nombreBoxeador = nombreBoxeador;
        totalGolpes = 0;
        System.out.println("Creando boxeador.." + nombreBoxeador);
    }

    public void golpear() {
        totalGolpes++;
    }

    public String getNombre() {
        return nombreBoxeador;
    }
    
    public int getGolpes() {
        return totalGolpes;
    }

    @Override
    public void run() {
      
            boolean terminar = false;
            while (!terminar) {
                try {
                    terminar = ring.golpear(this);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ClaseBoxeador.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }
        
    }
}
