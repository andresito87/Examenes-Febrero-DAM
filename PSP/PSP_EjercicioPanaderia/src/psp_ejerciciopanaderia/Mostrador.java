package psp_ejerciciopanaderia;

/**
 *
 * @author andres
 */
public class Mostrador {

    private int barrasPanActuales = 20; //horneadas el dia anterior
    private int barrasPanHorneadas = 0;
    private int barrasPanVendidas = 0;
    private float recaudacionTotal = 0;

    public Mostrador() {
    }

    public int getBarrasPanActuales() {
        return barrasPanActuales;
    }

    public void setBarrasPanActuales(int barrasPanActuales) {
        this.barrasPanActuales = barrasPanActuales;
    }

    public int getBarrasPanHorneadas() {
        return barrasPanHorneadas;
    }

    public void setBarrasPanHorneadas(int barrasPanHorneadas) {
        this.barrasPanHorneadas = barrasPanHorneadas;
    }

    public int getBarrasPanVendidas() {
        return barrasPanVendidas;
    }

    public void setBarrasPanVendidas(int barrasPanVendidas) {
        this.barrasPanVendidas = barrasPanVendidas;
    }

    public float getRecaudacionTotal() {
        return recaudacionTotal;
    }

    public void setRecaudacionTotal(float recaudacionTotal) {
        this.recaudacionTotal = recaudacionTotal;
    }

    public void verEstado() {
        System.out.println("\033[1;31;47mCantidad barras actual: " + this.getBarrasPanActuales());
        System.out.println("\u001B[31mCantidad de barras vendidas: " + this.barrasPanVendidas);
        System.out.println("\u001B[31mDinero total recaudado: " + this.getRecaudacionTotal() + " €");
        System.out.println("\u001B[31mCantidad de barras horneadas: " + this.getBarrasPanHorneadas() + "\033[0m");
    }

    public synchronized void quitarBarras(int cantidadBarrasCompradas) {
        while (barrasPanActuales - cantidadBarrasCompradas < 0) {
            try {
                this.wait();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
        this.setBarrasPanActuales(barrasPanActuales - cantidadBarrasCompradas);
        this.setBarrasPanVendidas(this.barrasPanVendidas + cantidadBarrasCompradas);
        this.setRecaudacionTotal((recaudacionTotal + ((cantidadBarrasCompradas - 1) * 0.75f) + 1));
    }

    public synchronized void reponerBarras() {

        if (this.barrasPanActuales < 10) {
            System.err.println("\u001B[32mPanadero horneando 20 barras durante 20 segundos...\u001B[0m");
            try {
                Thread.sleep(20 * 1000);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
            this.setBarrasPanActuales(this.barrasPanActuales + 20);
            this.setBarrasPanHorneadas(barrasPanHorneadas + 20);
            System.out.println("\u001B[32mPanadero termina de hornear, " + this.getBarrasPanActuales() + " barras disponibles !!!\u001B[0m");
            notifyAll();
        }
    }

    public synchronized void limpiandoParaCerrar() {
        this.setBarrasPanActuales(0);
        this.reponerBarras();
    }

    public void mostrarResumenFinal() {
        System.out.println("\u001B[33m---- Resumen Final ----\u001B[0m");
        System.out.println("\u001B[33mTotal de barras vendidas: " + this.getBarrasPanVendidas() + "\u001B[0m");
        System.out.println("\u001B[33mDinero recaudado: " + this.getRecaudacionTotal() + " €\u001B[0m");
        System.out.println("\u001B[33mPanadero dejó listas las barras para mañana. Hasta luego !!!\u001B[0m");
    }

}
