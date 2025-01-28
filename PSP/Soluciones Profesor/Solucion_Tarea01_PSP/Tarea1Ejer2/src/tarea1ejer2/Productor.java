package tarea1ejer2;

import java.util.Random;

class Productor extends Thread {
    private final Almacen almacen;
    private final Random random = new Random();
    private final String nombre;

    public Productor(Almacen almacen, String nombre) {
        this.almacen = almacen;
        this.nombre = nombre;
    }

    public void run() {
        while (!almacen.sumaAlcanzada()) {
            try {
                int valor = random.nextInt(11); // Generar un número aleatorio entre 0 y 10
                almacen.producir(valor, nombre);
                Thread.sleep(100); // Simular tiempo de producción
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}