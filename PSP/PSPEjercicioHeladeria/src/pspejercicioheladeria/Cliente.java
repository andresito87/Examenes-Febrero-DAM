package pspejercicioheladeria;

/**
 *
 * @author andres
 */
public class Cliente implements Runnable {

    private final String nombre;
    private int cantidadBolasSolicitadas;
    private final Mostrador mostrador;

    public Cliente(String nombreCliente, int cantidadBolasSolicitadas, Mostrador mostradorCompartido) {
        this.nombre = nombreCliente;
        this.cantidadBolasSolicitadas = cantidadBolasSolicitadas;
        this.mostrador = mostradorCompartido;
    }

    public int getCantidadBolasSolicitadas() {
        return cantidadBolasSolicitadas;
    }

    public void setCantidadBolasSolicitadas(int cantidadBolasSolicitadas) {
        this.cantidadBolasSolicitadas = cantidadBolasSolicitadas;
    }

    @Override
    public void run() {
        mostrador.retirarBolas(this.nombre, cantidadBolasSolicitadas);
    }

}
