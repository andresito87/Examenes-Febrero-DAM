package psp_ejerciciomaquinaetiquetadora;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class Deposito {

    private int cantidadEtiquetas = 3;
    private final int maximaCapacidad = 5;
    private int cantidadEtiquetasAColocar;

    public Deposito(int cantidadEtiquetasAColocar) {
        this.cantidadEtiquetasAColocar = cantidadEtiquetasAColocar;
    }

    public int getCantidadEtiquetas() {
        return cantidadEtiquetas;
    }

    public void setCantidadEtiquetas(int cantidadEtiquetas) {
        this.cantidadEtiquetas = cantidadEtiquetas;
    }

    public int getCantidadEtiquetasAColocar() {
        return cantidadEtiquetasAColocar;
    }

    public synchronized void sacarEtiqueta() {
        while (this.cantidadEtiquetas == 0) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Deposito.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.cantidadEtiquetasAColocar--;
        this.setCantidadEtiquetas(cantidadEtiquetas - 1);
    }

    public synchronized void rellenarEtiquetas(int etiquetas) {
        while (this.cantidadEtiquetas == maximaCapacidad && !this.limiteEtiquetasAColocarAlcanzado()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Deposito.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.setCantidadEtiquetas(cantidadEtiquetas + etiquetas);
        notifyAll();
    }

    public synchronized boolean limiteEtiquetasAColocarAlcanzado() {
        return this.cantidadEtiquetasAColocar == 0;
    }

}
