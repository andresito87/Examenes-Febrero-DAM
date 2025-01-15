/*
Varios hilos de ejecución simple, sin ningúna instruccion en general salvo la acumulación de lanzamientos
 */
package HilosSaludo;


public class VariosHilos {
    public static void main(String[] args) {
        // Creamos 2 hilos del tipo Hilo_Thread con 2 constructores diferentes
        Thread hilo1 = new Hilo_Thread("Isabel");
        Thread hilo2 = new Hilo_Thread("Pablo");
        
        // Creamos un hilo Runnable en un paso
        Thread hilo3 = new Thread(new Hilo_Runnable());
        
        // Ponemos en marcha los 3 hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}

// Clase Hilo_Thread
class Hilo_Thread extends Thread {
    private String nombre = "Hilo_derviaThread";
    
    // Constructor 1
    public Hilo_Thread(String nb) {
        nombre = nb;
    }

    // Constructor 2
    public Hilo_Thread() {
        // Sin parámetros
    }

    @Override
    public void run() {
        // Redefinimos run() con el código asociado al hilo
        for (int i = 1; i <= 10; i++) {
            System.out.println(nombre);
        }
    }
}

// Clase Hilo_Runnable
class Hilo_Runnable implements Runnable {
    // Redefinimos run() con el código asociado al hilo
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("  Hilo_Runnable");
        }
    }
}
