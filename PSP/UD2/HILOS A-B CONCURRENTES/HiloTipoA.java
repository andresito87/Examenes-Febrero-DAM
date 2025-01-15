package Ejercicio6;

public class HiloTipoA extends Thread {

    private final int espera;


    public HiloTipoA() {
        this.espera = 1000;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.printf("A");
            // Parada que simula el tiempo requerido para producir 
            try {
                HiloTipoA.sleep(espera);
            } catch (InterruptedException ex) {
            }
            HiloTipoA.yield(); // Sugiere al planficador Java que puede seleccionar otro hilo,
        }
    }


}
