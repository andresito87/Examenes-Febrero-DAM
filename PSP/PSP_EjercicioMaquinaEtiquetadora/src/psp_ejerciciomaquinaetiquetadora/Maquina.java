package psp_ejerciciomaquinaetiquetadora;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class Maquina implements Runnable {

    private String nombre;
    private Deposito deposito;
    private final int cantidadMaximaEtiquetas = 10;
    private int cantidadEtiquetasColocadas = 0;
    private final Random random = new Random();

    public Maquina(String nombre, Deposito deposito) {
        this.nombre = nombre;
        this.deposito = deposito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }

    @Override
    public void run() {
        while (this.cantidadEtiquetasColocadas < this.cantidadMaximaEtiquetas) {
            deposito.sacarEtiqueta();
            this.cantidadEtiquetasColocadas++;
            System.out.println(this.nombre + " colocando etiqueta. Etiquetas colocadas: " + this.cantidadEtiquetasColocadas);
            try {
                Thread.sleep((random.nextInt(5) + 1) * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Maquina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(this.nombre + " ha acabado su tarea de etiquetado.");
    }

}
