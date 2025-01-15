/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HilosRunable.HilosNombres;

public class Main {
    public static void main(String[] args) {
        Hilo1 Hil1 = new Hilo1("Jose");
        Hil1.start();

        Hilo2 Hil2 = new Hilo2("Pijuan");
        Hil2.start();

        Hilo3 Hil3 = new Hilo3("Adriano");
        Hil3.start();

        Hilo4 Hil4 = new Hilo4("Andres");
        Hil4.start();
    }
}