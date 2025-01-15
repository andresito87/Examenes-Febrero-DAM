/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HilosRunable.ProblemaJardin;


public class RecursoJardin {
    private int cuenta; // Para contar las entradas y salidas al Jardín

    public RecursoJardin() {
        cuenta = 100; // Inicialmente hay 100 personas en el jardín
    }

    public synchronized void incrementaCuenta() {
        System.out.println("Hilo " + Thread.currentThread().getName() + " - Entra en Jardín");
        cuenta++;
        System.out.println(cuenta + " en jardín");
    }

    public synchronized void decrementaCuenta() {
        System.out.println("Hilo " + Thread.currentThread().getName() + " - Sale de Jardín");
        cuenta--;
        System.out.println(cuenta + " en jardín");
    }
}