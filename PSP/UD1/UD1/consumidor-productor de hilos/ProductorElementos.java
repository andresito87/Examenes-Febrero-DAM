/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio_2;

/**
 *
 * @author profe
 */
public class ProductorElementos extends Thread {

    private static final String LETRAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final ColaElementos colaElementosProducidos;
    private final ColaElementos colaElementosDisponibles;
    private final String nombre;
    private final int cantidadElementosAGenerar;
    private final int espera;
    private int proximaLetra;
    private int cantidadElementosGenerados;

    public ProductorElementos(String nombre, ColaElementos colaElementosDisponibles,
            int espera, int cantidadLetras) {
        this.nombre = nombre;
        this.colaElementosProducidos = new ColaElementos();
        this.colaElementosDisponibles = colaElementosDisponibles;
        this.cantidadElementosGenerados = 0;
        this.proximaLetra = 0;
        this.espera = espera;
        this.cantidadElementosAGenerar = cantidadLetras;

    }

    @Override
    public void run() {
   
        while (cantidadElementosGenerados < cantidadElementosAGenerar) {

            // Producir elemento
            char letra = LETRAS.charAt(proximaLetra);
            proximaLetra = (proximaLetra + 1) % LETRAS.length();
            cantidadElementosGenerados++;

            // Añadir elemento a la cola de elementos disponibles
            synchronized (colaElementosDisponibles) {
               
                colaElementosDisponibles.addElemento(letra);
                // Despertamos a alguno de los hilos pendientes de tomar un elemento de la cola
                colaElementosDisponibles.notify();
                               
            }
            // Añadir elemento a la cola local de registro
            colaElementosProducidos.addElemento(letra);

            // Parada que simula el tiempo requerido para producir 
            try {
                Thread.sleep(espera);
            } catch (InterruptedException ex) {
            }

        }
        synchronized (this.colaElementosDisponibles) {
            System.out.printf("Fin del productor %s. Elementos producidos: %s\n%s%s\n",
                    nombre, colaElementosProducidos,
                    "Elementos disponibles en la cola: ", colaElementosDisponibles.toString());
          
        }

    }

    public String getNombre() {
        return nombre;
    }

    public ColaElementos getColaElementosProducidos() {
        return colaElementosProducidos;
    }

}
