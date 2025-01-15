package Ejercicio6;

public class HiloTipoB extends Thread {

    private final int espera;


    public HiloTipoB() {
        this.espera = 500;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("B");
            // Parada que simula el tiempo requerido para producir 
            try {
                HiloTipoB.sleep(espera);
            } catch (InterruptedException ex) {
            }
            HiloTipoB.yield(); // Sugiere al planficador Java que puede seleccionar otro hilo,
        }
    }


}
