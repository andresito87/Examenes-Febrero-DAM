package pspejercicioheladeria;

/**
 *
 * @author andres
 */
public class Heladero implements Runnable {

    private final Mostrador mostrador;

    public Heladero(Mostrador mostadorCompartido) {
        this.mostrador = mostadorCompartido;
    }

    public Mostrador getMostrador() {
        return mostrador;
    }

    public void colocarHeladoEnMostrador() {
        mostrador.colocarBolas();
    }

    public void cierraHeladeria() {
        System.out.println("Cerrando heladería...");
        mostrador.cerrar();
    }

    @Override
    public void run() {
        while (mostrador.estaAbierto()) {
            try {
                mostrador.colocarBolas();
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
