package pspejercicioheladeria;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class Mostrador {

    private int bolasDiponibles = 30;
    private double dineroRecaudado = 0;
    private int bolasPreparadas = 30;
    private boolean estaAbierto = true;

    public Mostrador() {
    }

    public int getBolasDiponibles() {
        return bolasDiponibles;
    }

    public void setBolasDiponibles(int bolasDiponibles) {
        this.bolasDiponibles = bolasDiponibles;
    }

    public double getDineroRecaudado() {
        return dineroRecaudado;
    }

    public void setDineroRecaudado(double dineroRecaudado) {
        this.dineroRecaudado = dineroRecaudado;
    }

    public int getBolasPreparadas() {
        return bolasPreparadas;
    }

    public void setBolasPreparadas(int bolasPreparadas) {
        this.bolasPreparadas = bolasPreparadas;
    }

    public boolean estaAbierto() {
        return estaAbierto;
    }

    public void setEstaAbierto(boolean estaAbierto) {
        this.estaAbierto = estaAbierto;
    }

    public void consultarEstado() {
        System.out.println("Cantidad bolas disponibles: " + this.bolasDiponibles);
        System.out.println("Cantidad dinero recaudado: " + this.dineroRecaudado);
        System.out.println("Cantidad bolas preparadas: " + this.bolasPreparadas);
    }

    public synchronized void colocarBolas() {
        if (this.bolasDiponibles < 15) {
            System.out.println("Heladero hace 30 bolas de helado y descansa ...");
            this.setBolasDiponibles(this.bolasDiponibles + 30);
            this.setBolasPreparadas(this.bolasPreparadas + 30);
            try {
                Thread.sleep(15 * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Mostrador.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Heladero terminó de preparar. Bolas disponibles: " + this.getBolasDiponibles());
            this.notifyAll();
        }
    }

    public synchronized void retirarBolas(String nombreCliente, int cantidadBolas) {
        while (this.bolasDiponibles <= cantidadBolas) {
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Mostrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.setBolasDiponibles(this.bolasDiponibles - cantidadBolas);
        double precio = this.calcularPrecio(cantidadBolas);
        this.setDineroRecaudado(this.dineroRecaudado + precio);
        System.out.println(nombreCliente + " se lleva " + cantidadBolas + " bolas de helado por un precio de " + precio + " €. Bolas restantes: " + this.bolasDiponibles);
    }

    public synchronized void cerrar() {
        this.setBolasDiponibles(0);
        this.colocarBolas();
        this.setEstaAbierto(false);
        System.out.println("---- Resumen final ----");
        this.consultarEstado();
    }

    private double calcularPrecio(int cantidadBolas) {
        return 2 + ((cantidadBolas - 1) * 1.50);
    }

}
