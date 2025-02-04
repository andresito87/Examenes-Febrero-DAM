package psp_taller_bicicletas;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class OperarioProductor implements Runnable {

    private final String nombre;
    private final Almacen almacen;
    private final Random random = new Random();

    public OperarioProductor(String nombre, Almacen almacenCompartido) {
        this.nombre = nombre;
        this.almacen = almacenCompartido;
    }

    @Override
    public void run() {
        int numeroAleatorio35 = random.nextInt(3) + 3;
        int numeroAleatorio23 = random.nextInt(2) + 2;
        while (!almacen.haSidoCompletadaTareaDiaria()) {
            try {
                almacen.ponerPiezas(1);
                Thread.sleep(numeroAleatorio35 * 1000);
                Thread.sleep(numeroAleatorio23 * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(OperarioProductor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("[Piezas] "+this.nombre+" finaliza la jornada laboral.");
    }

}
