package ejerciciofloristeria;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class Florista implements Runnable {

    private final String nombre;
    private final JardinAutomatico jardin;
    private int cantidadRosasAPreparar;
    private int cantidadClavelesAPreparar;
    private int cantidadMargaritasAPreparar;
    private boolean estaDisponible;

    public Florista(String nombre, JardinAutomatico jardin) {
        this.nombre = nombre;
        this.jardin = jardin;
    }

    public void prepararRamo(int cantidadRosas, int cantidadClaveles, int cantidadMargaritas) {
        jardin.venderFlores(cantidadRosas, cantidadClaveles, cantidadMargaritas);
    }

    public int getCantidadRosasAPreparar() {
        return cantidadRosasAPreparar;
    }

    public void setCantidadRosasAPreparar(int cantidadRosasAPreparar) {
        this.cantidadRosasAPreparar = cantidadRosasAPreparar;
    }

    public int getCantidadClavelesAPreparar() {
        return cantidadClavelesAPreparar;
    }

    public void setCantidadClavelesAPreparar(int cantidadClavelesAPreparar) {
        this.cantidadClavelesAPreparar = cantidadClavelesAPreparar;
    }

    public int getCantidadMargaritasAPreparar() {
        return cantidadMargaritasAPreparar;
    }

    public void setCantidadMargaritasAPreparar(int cantidadMargaritasAPreparar) {
        this.cantidadMargaritasAPreparar = cantidadMargaritasAPreparar;
    }

    public boolean estaDisponible() {
        return estaDisponible;
    }

    public void setEstaDisponible(boolean estaDisponible) {
        this.estaDisponible = estaDisponible;
    }

    @Override
    public void run() {
        try {
            this.estaDisponible = false;
            System.out.println("[" + nombre + "] Iniciando preparación de ramo - Tiempo estimado: 15s");
            Thread.sleep(15000);
            this.prepararRamo(cantidadRosasAPreparar, cantidadClavelesAPreparar, cantidadMargaritasAPreparar);
            System.out.println("[" + nombre + "] Ramo preparado con éxito.");
            this.estaDisponible = true;
        } catch (InterruptedException ex) {
            Logger.getLogger(Florista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
