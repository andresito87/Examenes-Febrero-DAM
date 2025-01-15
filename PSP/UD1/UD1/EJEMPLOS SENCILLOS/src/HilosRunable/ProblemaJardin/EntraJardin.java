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
    
public class EntraJardin extends Thread {
    private RecursoJardin jardin;

    public EntraJardin(String nombre, RecursoJardin j) {
        this.setName(nombre);
        this.jardin = j;
    }

    @Override
    public void run() {
        jardin.incrementaCuenta();
    }
}
