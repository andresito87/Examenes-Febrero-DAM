package cocinerosycuchillos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AlumnoFPD Andres Samuel Podadera Gonzalez
 */
public class Cocina {

    private boolean cuchilloAlfaEnUso = false;
    private boolean cuchilloBetaEnUso = false;

    public synchronized void cogerAlgunCuchillo(String nombre, int vecesCojido) {

        while (cuchilloAlfaEnUso && cuchilloBetaEnUso) {
            try {
                System.out.println("Soy " + nombre + ", esperando un cuchillo...");
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Cocina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!cuchilloAlfaEnUso) {
            System.out.println("Soy " + nombre + " cojo el cuchillo por " + vecesCojido + " vez. Cuchillos Disponibles: " + this.cuchillosDisponibles());
            this.cuchilloAlfaEnUso = true;
        } else if (!cuchilloBetaEnUso) {
            System.out.println("Soy " + nombre + " cojo el cuchillo por " + vecesCojido + " vez. Cuchillos Disponibles: " + this.cuchillosDisponibles());
            this.cuchilloBetaEnUso = true;
        }
        notifyAll();
    }

    public synchronized void soltarAlgunCuchillo(String nombre) {
        if (cuchilloAlfaEnUso) {
            System.out.println("Soy " + nombre + " suelto el cuchillo, voy a hacer otra tarea. Cuchillos Disponibles: " + this.cuchillosDisponibles());
            this.cuchilloAlfaEnUso = false;
        } else if (cuchilloBetaEnUso) {
            System.out.println("Soy " + nombre + " suelto el cuchillo, voy a hacer otra tarea. Cuchillos Disponibles: " + this.cuchillosDisponibles());
            this.cuchilloBetaEnUso = false;
        }
        notifyAll();
    }

    public synchronized int cuchillosDisponibles() {
        if (this.cuchilloAlfaEnUso && this.cuchilloBetaEnUso) {
            return 0;
        } else if (!this.cuchilloAlfaEnUso && !this.cuchilloBetaEnUso) {
            return 2;
        } else {
            return 1;
        }
    }

}
