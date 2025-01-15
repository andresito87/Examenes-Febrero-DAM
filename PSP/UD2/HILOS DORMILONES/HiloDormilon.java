package Ejercicio7;

public class HiloDormilon extends Thread {

    private final long tiempoEspera;
    private final String nombre;

    public HiloDormilon(String nombre, long tiempo) {
        this.tiempoEspera = tiempo;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        System.out.printf("Iniciando hilo dormilón %s que va a dormir %d segundos.\n",
                this.nombre, this.tiempoEspera);
        try {
            Thread.sleep(this.tiempoEspera * 1000);
        } catch (InterruptedException ex) {
        }
        System.out.printf("Finalizando hilo dormilón %s que acaba de dormir %d segundos.\n",
                this.nombre, this.tiempoEspera);
    }

}
