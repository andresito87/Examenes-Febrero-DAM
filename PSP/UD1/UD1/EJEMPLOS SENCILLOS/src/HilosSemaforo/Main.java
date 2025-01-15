/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HilosSemaforo;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Fran
 */
public class Main {
 protected static Semaphore P1;

    public static void main(String[] args) {
        P1 = new Semaphore(0, true);

        Hilo1 hilo1 = new Hilo1(P1);
        hilo1.start();

        Hilo2 hilo2 = new Hilo2(P1);
        hilo2.start();
    }
}