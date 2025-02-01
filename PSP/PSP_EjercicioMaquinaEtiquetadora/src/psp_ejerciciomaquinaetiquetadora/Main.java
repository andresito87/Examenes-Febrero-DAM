package psp_ejerciciomaquinaetiquetadora;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenid@ al sistema de Etiquetado HEBRAS 2.0");
        System.out.print("Introduzca la cantidad de máquinas etiquetadoras: ");
        int cantidadMaquinas = scanner.nextInt();
        System.out.print("Introduzca la cantidad de etiquetas que desea añadir en cada producción: ");
        int cantidadEtiquetas = scanner.nextInt();
        System.out.println("Iniciando proceso de etiquetado...");

        Deposito deposito = new Deposito(cantidadMaquinas * 10);
        List<Thread> listaMaquinas = new ArrayList();
        Productor productor = new Productor("Productor", cantidadEtiquetas, deposito);
        Thread hiloProductor = new Thread(productor);
        hiloProductor.start();
        for (int i = 0; i < cantidadMaquinas; i++) {
            Thread maquina = new Thread(new Maquina("Maquina " + (i + 1), deposito));
            listaMaquinas.add(maquina);
            maquina.start();
        }

        try {
            hiloProductor.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Thread maquina : listaMaquinas) {
            try {
                maquina.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Proceso de etiquetado completado. Fin del programa.");
    }

}
