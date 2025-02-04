package psp_taller_bicicletas;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class Almacen {

    private final int MAX_PIEZAS = 5;
    private int piezas = 0;

    private final int MAX_BICICLETAS = 10;
    private int bicicletas = 0;

    public Almacen() {
    }

    public int getPiezas() {
        return piezas;
    }

    public void setPiezas(int piezas) {
        this.piezas = piezas;
    }

    public int getBicicletas() {
        return bicicletas;
    }

    public void setBicicletas(int bicicletas) {
        this.bicicletas = bicicletas;
    }

    public synchronized void sacarPiezas(int cantidad) {
        while (piezas < cantidad && !haSidoCompletadaTareaDiaria()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!this.haSidoCompletadaTareaDiaria()) {
            this.piezas--;
            this.bicicletas++;
            notifyAll();
        }
    }

    public synchronized void ponerPiezas(int cantidad) {
        while (this.piezas >= this.MAX_PIEZAS && !this.haSidoCompletadaTareaDiaria()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!this.haSidoCompletadaTareaDiaria()&& this.piezas <= 0) {
            this.piezas++;
            System.out.println("[Piezas] pedido de piezas preparado. Stock en almacén: " + this.piezas);
            notifyAll();
        }
    }

    public synchronized boolean haSidoCompletadaTareaDiaria() {
        return this.bicicletas >= this.MAX_BICICLETAS;
    }
}
