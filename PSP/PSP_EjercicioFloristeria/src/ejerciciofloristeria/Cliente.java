package ejerciciofloristeria;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente implements Runnable {
    private final int cantidadRosas;
    private final int cantidadClaveles;
    private final int cantidadMargaritas;
    private final List<Florista> floristas;
    
    public Cliente(int cantidadRosas, int cantidadClaveles, int cantidadMargaritas, List<Florista> floristas) {
        this.cantidadRosas = cantidadRosas;
        this.cantidadClaveles = cantidadClaveles;
        this.cantidadMargaritas = cantidadMargaritas;
        this.floristas = floristas;
    }
    
    @Override
    public void run() {
        Florista floristaDisponible = null;
        
        synchronized (floristas) {
            while (floristaDisponible == null) {
                for (Florista florista : floristas) {
                    if (florista.estaDisponible()) {
                        floristaDisponible = florista;
                        break;
                    }
                }
                if (floristaDisponible == null) {
                    try {
                        floristas.wait(); // Esperar a que un florista esté disponible
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
        System.out.println("[Sistema] Cliente solicita ramo (" + cantidadRosas + " rosas, " + cantidadClaveles + " claveles, " + cantidadMargaritas + " margaritas)");
        floristaDisponible.prepararRamo(cantidadRosas, cantidadClaveles, cantidadMargaritas);
        synchronized (floristas) {
            floristas.notifyAll(); // Notificar a otros clientes que pueden intentar encontrar un florista
        }
    }
}
