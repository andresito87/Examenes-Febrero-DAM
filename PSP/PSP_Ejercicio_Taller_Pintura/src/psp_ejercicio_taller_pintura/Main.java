package psp_ejercicio_taller_pintura;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Sala sala = new Sala("Sala 1");
            Cliente cliente = new Cliente("Cliente 1", sala);
            Pintor pintor1 = new Pintor("Pintor 1", sala);
            Pintor pintor2 = new Pintor("Pintor 2", sala);
            Pintor pintor3 = new Pintor("Pintor 3", sala);
            
            pintor1.setName("Pintor 1");
            pintor2.setName("Pintor 2");
            pintor3.setName("Pintor 3");
            
            cliente.start();
            pintor1.start();
            pintor2.start();
            pintor3.start();
            
            cliente.join();
            pintor1.join();
            pintor2.join();
            pintor3.join();
            
            
            sala.verEstadoSala();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
