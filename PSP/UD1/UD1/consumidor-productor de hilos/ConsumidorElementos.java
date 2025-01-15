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
public class ConsumidorElementos extends Thread {

    private final ColaElementos colaElementosConsumidos;
    private final ColaElementos colaElementosDisponibles;
    private final String nombre;
    private final int espera;
    private final int cantidadAConsumir;


    public ConsumidorElementos(String nombre, ColaElementos colaElementosDisponibles,
            int espera, int cantidad) {
        this.nombre = nombre;
        this.colaElementosConsumidos = new ColaElementos();
        this.colaElementosDisponibles = colaElementosDisponibles;
        this.espera = espera;
        this.cantidadAConsumir = cantidad;

    }

    @Override
    public void run() {

        while (colaElementosConsumidos.size() < cantidadAConsumir) {

            synchronized (colaElementosDisponibles) {
                // Consumir elemento
                char letra = colaElementosDisponibles.getElemento();

                // Añadir elemento (si se ha podido consumir) a la cola local de registro
                if (letra != 0) {  // Había letra disponible en la cola
                    colaElementosConsumidos.addElemento(letra);
                    
                } else {  // No había elemento disponible en la cola
                                       
                    try {
                        colaElementosDisponibles.wait();
                    } catch (InterruptedException ex) {
                    }
                }
            }
            // Parada que simula el tiempo requerido para consumir 
            try {
                Thread.sleep(espera);
            } catch (InterruptedException ex) {
            }

        }
        synchronized (colaElementosDisponibles) {
            System.out.printf("Fin del consumidor %s. Elementos consumidos: %s\n%s%s\n",
                    nombre, colaElementosConsumidos,
                    "Elementos disponibles en la cola: ", colaElementosDisponibles.toString());
        }

    }
    public String getNombre() {
        return nombre;
    }

    public ColaElementos getColaElementosConsumidos() {
        return colaElementosConsumidos;
    }

   
}
