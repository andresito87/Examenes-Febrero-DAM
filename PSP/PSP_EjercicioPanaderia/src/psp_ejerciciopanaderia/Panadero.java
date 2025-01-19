package psp_ejerciciopanaderia;

/**
 *
 * @author andres
 */
public class Panadero extends Thread {

    private final Mostrador mostrador;

    public Panadero(Mostrador mostradorCompartido) {
        this.mostrador = mostradorCompartido;
    }

    public void honerBarras() {
        this.mostrador.reponerBarras();
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.honerBarras();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("El panadero se prepara para irse ...");
                break;
            }
        }
    }
}
