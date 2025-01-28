package tarea2;
import java.util.Scanner;

public class SimuladorCliente {
    public static void main(String[] args) {

        int jugadoresJuego1, jugadoresJuego2, jugadoresJuego3;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Cuantos jugadores para el juego 1? (adivinar un numero): ");
        jugadoresJuego1 = scanner.nextInt();

        System.out.print("Cuantos jugadores para el juego 2?(lanzar dados): ");
        jugadoresJuego2 = scanner.nextInt();

        System.out.print("Cuantos jugadores para el juego 3?(piedra, papel o tijeras): ");
        jugadoresJuego3 = scanner.nextInt();

        // Creamos un array de hilos con la cantidad total de clientes
        int totalClientes = jugadoresJuego1 + jugadoresJuego2 + jugadoresJuego3;
        Thread[] hilos = new Thread[totalClientes];

        int indice = 0;

        // Crear hilos para el juego 1
        for (int i = 0; i < jugadoresJuego1; i++) {
            hilos[indice++] = new Thread(new Cliente(1));
        }

        // Crear hilos para el juego 2
        for (int i = 0; i < jugadoresJuego2; i++) {
            hilos[indice++] = new Thread(new Cliente(2));
        }

        // Crear hilos para el juego 3
        for (int i = 0; i < jugadoresJuego3; i++) {
            hilos[indice++] = new Thread(new Cliente(3));
        }

        // Ejecutamos todos los hilos en paralelo
        for (Thread hilo : hilos) {
            hilo.start();
        }

        // Esperar a que todos los hilos terminen
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.err.println("Error al esperar el hilo: " + e.getMessage());
            }
        }
        System.out.println("\nSi1mulacion de clientes terminada.");
    }
}

