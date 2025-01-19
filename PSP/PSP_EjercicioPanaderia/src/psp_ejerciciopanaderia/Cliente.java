package psp_ejerciciopanaderia;

/**
 *
 * @author andres
 */
public class Cliente extends Thread {

    private int cantidadBarrasCompradas;
    private int gastoTotal;
    private final String nombre;
    private final Mostrador mostrador;

    public Cliente(String nombreCliente, Mostrador mostradorCompartido, int cantidadBarras) {
        this.nombre = nombreCliente;
        this.mostrador = mostradorCompartido;
        this.cantidadBarrasCompradas = cantidadBarras;
    }

    public int getNumBarrasCompradas() {
        return cantidadBarrasCompradas;
    }

    public void setNumBarrasCompradas(int cantidadBarras) {
        this.cantidadBarrasCompradas = cantidadBarras;
    }

    public int getGastoTotal() {
        return gastoTotal;
    }

    public void setGastoTotal(int gastoTotal) {
        this.gastoTotal = gastoTotal;
    }

    public String getNombre() {
        return nombre;
    }

    private void cogerBarras() {
        mostrador.quitarBarras(this.cantidadBarrasCompradas);
    }

    @Override
    public void run() {
        this.cogerBarras();
        this.setGastoTotal((int) (((cantidadBarrasCompradas - 1) * 0.75f) + 1));
    }

}
