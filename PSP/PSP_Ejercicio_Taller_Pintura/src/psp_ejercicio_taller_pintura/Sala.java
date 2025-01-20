package psp_ejercicio_taller_pintura;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class Sala {

    private int cochesPintados;
    private String nombre;
    private int cochesEnDescampado = 0;
    private int cantidadCochesSala = 0;
    private int cochesRecibidosParaPintar = 0;

    public Sala(String nombre) {
        this.nombre = nombre;
    }

    public int getCochesPintados() {
        return cochesPintados;
    }

    public synchronized void setCochesPintados(int cochesPintados) {
        this.cochesPintados = cochesPintados;
        notifyAll();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCochesEnDescampado() {
        return cochesEnDescampado;
    }

    public void setCochesEnDescampado(int cochesEnDescampado) {
        this.cochesEnDescampado = cochesEnDescampado;
    }

    public int getCantidadCochesSala() {
        return cantidadCochesSala;
    }

    public void setCantidadCochesSala(int cantidadCochesSala) {
        this.cantidadCochesSala = cantidadCochesSala;
    }

    public int getCochesRecibidosParaPintar() {
        return cochesRecibidosParaPintar;
    }

    public void setCochesRecibidosParaPintar(int cochesRecibidosParaPintar) {
        this.cochesRecibidosParaPintar = cochesRecibidosParaPintar;
    }

    public synchronized void meterCocheParaPintar() {
        while (this.cantidadCochesSala == 1 || this.cochesEnDescampado == 0) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.cochesEnDescampado--;
        this.cantidadCochesSala++;

        this.verEstadoSala();

        notifyAll();
    }

    public synchronized void entregarCocheAlCliente() {
        this.setCantidadCochesSala(0);
        this.setCochesPintados(cochesPintados + 1);
        System.out.println("\u001B[1;35;40m" + Thread.currentThread().getName() + " pintando coche ...\u001B[0m");
        System.out.println("\u001B[1;31;47mEntregando coche. Coches pintados: " + this.getCochesPintados() + "\u001B[0m");

        notifyAll();
    }

    public synchronized void aparcarCocheDescampado() {
        try {
            while (this.getCochesEnDescampado() == 5) {
                this.wait();
            }

            if (this.getCochesRecibidosParaPintar() < 10) {
                this.setCochesEnDescampado(cochesEnDescampado + 1);
                this.cochesRecibidosParaPintar++;
                System.out.println("\u001B[1;33;44mCliente aparca coche en descampado. Coches disponibles: " + this.getCochesEnDescampado() + "\u001B[0m");
            }
            this.notifyAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void verEstadoSala() {
        System.out.println("Coches pintados:" + this.getCochesPintados());
        System.out.println("Coches en descampado: " + this.getCochesEnDescampado());
    }
}
