package ejerciciofloristeria;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class JardinAutomatico {
    
    private int cantidadRosas = 50;
    private int cantidadClaveles = 50;
    private int cantidadMargaritas = 50;
    private boolean estaOperativo = true;
    private double recaudacion = 0;
    private int floresVendidas = 0;
    
    public JardinAutomatico() {
    }
    
    public int getCantidadRosas() {
        return cantidadRosas;
    }
    
    public void setCantidadRosas(int cantidadRosas) {
        this.cantidadRosas = cantidadRosas;
    }
    
    public int getCantidadClaveles() {
        return cantidadClaveles;
    }
    
    public void setCantidadClaveles(int cantidadClaveles) {
        this.cantidadClaveles = cantidadClaveles;
    }
    
    public int getCantidadMargaritas() {
        return cantidadMargaritas;
    }
    
    public void setCantidadMargaritas(int cantidadMargaritas) {
        this.cantidadMargaritas = cantidadMargaritas;
    }
    
    public boolean estaOperativo() {
        return estaOperativo;
    }
    
    public void setEstaOperativo(boolean estaOperativo) {
        this.estaOperativo = estaOperativo;
    }
    
    public double getRecaudacion() {
        return recaudacion;
    }
    
    public void setRecaudacion(double recaudacion) {
        this.recaudacion = recaudacion;
    }
    
    public int getFloresVendidas() {
        return floresVendidas;
    }
    
    public void setFloresVendidas(int floresVendidas) {
        this.floresVendidas = floresVendidas;
    }
    
    public void verEstado() {
        System.out.println("Cantidad de rosas disponibles: " + this.getCantidadRosas());
        System.out.println("Cantidad de claveles disponibles: " + this.getCantidadClaveles());
        System.out.println("Cantidad de margaritas disponibles: " + this.getCantidadMargaritas());
        System.out.println("Cantidad de flores vendidas: " + this.getFloresVendidas());
        System.out.println("Dinero recaudado: " + this.getRecaudacion() + " €");
    }
    
    public synchronized void producirFlores() {
        while (estaOperativo) {
            this.setCantidadRosas(this.cantidadRosas + 5);
            this.setCantidadClaveles(this.cantidadClaveles + 5);
            this.setCantidadMargaritas(this.cantidadMargaritas + 5);
            
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(JardinAutomatico.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.notifyAll();
        }
    }
    
    public synchronized void venderFlores(int cantidadRosas, int cantidadClaveles, int cantidadMargaritas) {
        while (this.cantidadRosas < cantidadRosas) {
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(JardinAutomatico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        while (this.cantidadClaveles < cantidadClaveles) {
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(JardinAutomatico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        while (this.cantidadMargaritas < cantidadMargaritas) {
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(JardinAutomatico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        double precioRamo = this.calcularPrecioFlores(cantidadRosas, cantidadClaveles, cantidadMargaritas);
        this.setCantidadRosas(this.cantidadRosas - cantidadRosas);
        this.setCantidadClaveles(this.cantidadClaveles - cantidadClaveles);
        this.setCantidadMargaritas(this.cantidadMargaritas - cantidadMargaritas);
        this.setFloresVendidas(this.floresVendidas + cantidadRosas + cantidadClaveles + cantidadMargaritas);
        this.setRecaudacion(this.recaudacion + precioRamo);
        
    }
    
    public synchronized void cerrar() {
        this.setEstaOperativo(false);
    }
    
    private double calcularPrecioFlores(int cantidadRosas, int cantidadClaveles, int cantidadMargaritas) {
        double resultado = cantidadRosas * 2;
        resultado += cantidadClaveles * 1;
        resultado += cantidadMargaritas * 0.5;
        resultado += 3;
        
        return resultado;
    }
    
}
