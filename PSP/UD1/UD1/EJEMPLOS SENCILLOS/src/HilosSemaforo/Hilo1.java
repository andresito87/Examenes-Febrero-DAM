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
public class Hilo1 extends Thread {
    protected Semaphore P1;

    public Hilo1(Semaphore P1) {
        this.P1 = P1;
    }

    public void run() {
        try {
            this.P1.acquire();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("Hola");
        }
    }
}