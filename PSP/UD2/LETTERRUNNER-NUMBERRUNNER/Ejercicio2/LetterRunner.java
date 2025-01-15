/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio2;

/**
 *
 * @author Usuario
 */
public class LetterRunner implements Runnable {
    RecursoCompartido res;
 
    public LetterRunner(RecursoCompartido res) {
        this.res = res;
    }
 
    @Override
    public void run() {
        while (res.letter <= 'Z') {
            synchronized (res) {
                if (!res.isLetter) {
                    try {
                        res.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.print(res.letter);
                    res.letter++;
                    res.isLetter = false;
                    res.notify();
                }
            }
        }
    }
}