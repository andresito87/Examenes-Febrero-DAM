/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HilosSaludo;

/* Crea un hilo derivando de la clase Thread */
public class Saludo extends Thread {
    
    // Método run() redefinido con el código asociado al hilo
    @Override
    public void run() {
        System.out.println("¡Saludo desde un hilo extendiendo Thread!");
    }

    public static void main(String args[]) {
        Saludo hilo1 = new Saludo(); // Se crea un objeto Thread, el hilo hilo1
        hilo1.start(); // Invoca a start() y pone en marcha el hilo hilo1
    }
}
