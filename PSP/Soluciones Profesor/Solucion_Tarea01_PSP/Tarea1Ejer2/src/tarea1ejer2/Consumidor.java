package tarea1ejer2;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class Consumidor extends Thread {
    private final Almacen almacen;

    public Consumidor(Almacen almacen) {
        this.almacen = almacen;
    }

    public void run() {
        while (!almacen.sumaAlcanzada()) {
            try {
                almacen.consumir();
                Thread.sleep(150); // Simular tiempo de consumo
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
