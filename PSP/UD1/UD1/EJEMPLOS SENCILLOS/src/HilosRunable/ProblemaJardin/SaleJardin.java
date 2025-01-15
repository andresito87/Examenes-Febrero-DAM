/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HilosRunable.ProblemaJardin;


public class SaleJardin extends Thread {
    private RecursoJardin jardin;

    public SaleJardin(String nombre, RecursoJardin j) {
        this.setName(nombre);
        this.jardin = j;
    }

@Override
        public void run() {
        jardin.decrementaCuenta();
    }
}