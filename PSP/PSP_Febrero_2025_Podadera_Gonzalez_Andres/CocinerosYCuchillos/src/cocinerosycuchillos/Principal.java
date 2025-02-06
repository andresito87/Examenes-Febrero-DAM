package cocinerosycuchillos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AlumnoFPD Andres Samuel Podadera Gonzalez
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        List<Thread> hilosCocineros= new ArrayList();
        Cocina cocina = new Cocina();
        
        Cocinero cocinero1= new Cocinero("Cocinero 1",cocina);
        Thread hilo1= new Thread(cocinero1);
        hilosCocineros.add(hilo1);
        hilo1.start();
        Cocinero cocinero2= new Cocinero("Cocinero 2",cocina);
        Thread hilo2= new Thread(cocinero2);
        hilosCocineros.add(hilo2);
        hilo2.start();
        Cocinero cocinero3= new Cocinero("Cocinero 3",cocina);
        Thread hilo3= new Thread(cocinero3);
        hilosCocineros.add(hilo3);
        hilo3.start();
        Cocinero cocinero4= new Cocinero("Cocinero 4",cocina);
        Thread hilo4= new Thread(cocinero4);
        hilosCocineros.add(hilo4);
        hilo4.start();
        
        for (Thread hilo : hilosCocineros) {
            try {
                hilo.join();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println("Todos los cocineros han terminado");
    }
    
}
