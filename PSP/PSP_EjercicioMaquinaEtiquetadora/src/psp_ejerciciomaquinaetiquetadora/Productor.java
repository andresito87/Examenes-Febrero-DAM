package psp_ejerciciomaquinaetiquetadora;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class Productor implements Runnable {

    private String nombre;
    private int cantidadEtiquetasAProducir;
    private Deposito deposito;

    public Productor(String nombre, int cantidadEtiquetasAProducir, Deposito deposito) {
        this.nombre = nombre;
        this.cantidadEtiquetasAProducir = cantidadEtiquetasAProducir;
        this.deposito = deposito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadEtiquetasAProducir() {
        return cantidadEtiquetasAProducir;
    }

    public void setCantidadEtiquetasAProducir(int cantidadEtiquetasAProducir) {
        this.cantidadEtiquetasAProducir = cantidadEtiquetasAProducir;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }

    @Override
    public void run() {
        while (!deposito.limiteEtiquetasAColocarAlcanzado()) {
            if (deposito.getCantidadEtiquetas() < 10) {
                try {
                    deposito.rellenarEtiquetas(cantidadEtiquetasAProducir);
                    System.out.println("Productor reponiendo " + this.cantidadEtiquetasAProducir + " etiquetas.");
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        System.out.println("El productor ha acabado de producir las etiquetas necesarias, se va...");
    }

}
