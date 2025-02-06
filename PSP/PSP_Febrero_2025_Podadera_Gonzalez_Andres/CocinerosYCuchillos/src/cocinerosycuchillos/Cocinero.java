package cocinerosycuchillos;

import java.util.Random;

/**
 *
 * @author AlumnoFPD Andres Samuel Podadera Gonzalez
 */
public class Cocinero implements Runnable {

    private final String nombre;
    private final Random random = new Random();
    private Cocina cocina;
    private int vecesCojidocuchillo = 0;

    public String getNombre() {
        return nombre;
    }

    public Cocinero(String nombre, Cocina cocina) {
        this.nombre = nombre;
        this.cocina = cocina;
    }

    @Override
    public void run() {
        while (this.vecesCojidocuchillo < 5) {
            try {
                cocina.cogerAlgunCuchillo(nombre, vecesCojidocuchillo + 1);
                Thread.sleep((random.nextInt(11) + 10)*1000);
                this.vecesCojidocuchillo+=1;
                cocina.soltarAlgunCuchillo(nombre);
                Thread.sleep((random.nextInt(6) + 5)*1000);
                System.out.println(this.nombre + " he terminado mi plato");
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println(this.nombre + " he terminado mi trabajo.");
    }

}
