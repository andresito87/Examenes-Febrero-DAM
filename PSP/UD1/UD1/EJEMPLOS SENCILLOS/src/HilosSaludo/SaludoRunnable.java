/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HilosSaludo;

/**
 *
 * @author Fran
 */
/* Crea un hilo implementando la interfaz Runnable */
public class SaludoRunnable implements Runnable {
    
    // Método run() redefinido con el código asociado al hilo
    @Override
    public void run() {
        System.out.println("¡Saludo desde un hilo creado con Runnable!");
    }

    public static void main(String args[]) {
        SaludoRunnable miRunnable = new SaludoRunnable(); // Se crea un objeto SaludoRunnable
        Thread hilo1 = new Thread(miRunnable); // Se crea un objeto Thread (el hilo hilo1) pasando como argumento
                                                // al constructor un objeto SaludoRunnable
        hilo1.start(); // Se invoca al método start() del hilo hilo1
    }
}
