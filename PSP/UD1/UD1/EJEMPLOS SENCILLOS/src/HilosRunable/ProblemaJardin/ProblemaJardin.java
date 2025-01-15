/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HilosRunable.ProblemaJardin;

/**
 *
 * @author Fran
 */
public class ProblemaJardin {

    public static void main(String[] args) {
        RecursoJardin jardin = new RecursoJardin();

        // Crea y lanza 10 hilos de entrada al jardín
        for (int i = 1; i <= 10; i++) {
            new EntraJardin("Entra" + i, jardin).start();
        }

        // Crea y lanza 15 hilos de salida del jardín
        for (int i = 1; i <= 15; i++) {
            new SaleJardin("Sale" + i, jardin).start();
        }
    }
}

