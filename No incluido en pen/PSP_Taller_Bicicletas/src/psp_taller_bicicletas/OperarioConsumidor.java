package psp_taller_bicicletas;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class OperarioConsumidor implements Runnable {

    private final String nombre;
    private final Almacen almacen;
    private final Random random = new Random();

    public OperarioConsumidor(String nombre, Almacen almacenCompartido) {
        this.nombre = nombre;
        this.almacen = almacenCompartido;
    }

    @Override
    public void run() {
        while (!almacen.haSidoCompletadaTareaDiaria()) {
            try {
                int numeroAleatorio58 = random.nextInt(4) + 5;
                int numeroAleatorio23 = random.nextInt(2) + 2;

                almacen.sacarPiezas(1);
                Thread.sleep(numeroAleatorio58 * 1000);
                System.out.println("[Montaje]: Bicicleta montada. Stock bicicletas: " + almacen.getBicicletas());
                System.out.println("[Montaje]" + this.nombre + " descansa.");
                Thread.sleep(numeroAleatorio23 * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(OperarioConsumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("[Montaje] "+this.nombre+" finaliza la jornada laboral.");
    }

}
