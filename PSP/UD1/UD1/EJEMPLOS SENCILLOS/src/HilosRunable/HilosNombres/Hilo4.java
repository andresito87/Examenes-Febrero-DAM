/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HilosRunable.HilosNombres;

public class Hilo4 extends Thread {
    public Hilo4(String str) {
        super(str);
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Posicion " +i + " de: " + getName());
        }
    }
}