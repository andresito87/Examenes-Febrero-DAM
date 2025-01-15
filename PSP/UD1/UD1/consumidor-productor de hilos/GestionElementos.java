package Ejercicio_2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author profe
 */
public class GestionElementos {

    // Nombre del archivo de texto con la configuración de hilos
    private static final String NOMBRE_ARCHIVO_ENTRADA_TXT_DEFAULT = "hilos.cfg";

    // Cola de elementos compartida por productor y consumidores
    private static final ColaElementos colaElementos = new ColaElementos();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Creamos la estructura vacía para ir llenándola línea a línea (hilo a hilo)
        List<Thread> listaHilos = new LinkedList<>();

        System.out.println("HILOS PRODUCTORES Y CONSUMIDORES");
        System.out.println("--------------------------------");
        System.out.println("Leyendo configuración de hilos del archivo de texto...");

        boolean lecturaCorrecta = false;

        try (FileReader fr = new FileReader(NOMBRE_ARCHIVO_ENTRADA_TXT_DEFAULT);
                BufferedReader entrada = new BufferedReader(fr);) {

            // Patrón que contiene la estructura de una línea de texto que representa a un producto
            Pattern patronHilo = Pattern.compile("([^:]+)::([^:]+)::([^:]+)::([^:]+)");

            String linea = entrada.readLine(); //se lee la primera línea del archivo
            while (linea != null) {  //mientras no se llegue al final del archivo
               
                Matcher m = patronHilo.matcher(linea);
                if (m.matches()) { // Si la línea cumple las condiciones de estructura
                    // Se extraen los atributos de tipo String
                    String tipo = m.group(1);
                    String nombre = m.group(2);
                    try {
                        int tiempo = Integer.parseInt(m.group(3));
                        int size = Integer.parseInt(m.group(4));
                        if (tipo.equalsIgnoreCase("Productor")) {
                            System.out.printf("Hilo Productor:  nombre \"%s\", tiempo=%d, número de elementos=%d\n",
                                    nombre, tiempo, size);
                            listaHilos.add(new ProductorElementos(nombre, colaElementos, tiempo, size));
                        } else if (tipo.equalsIgnoreCase("Consumidor")) {
                            System.out.printf("Hilo Consumidor: nombre \"%s\", tiempo=%d, número de elementos=%d\n",
                                    nombre, tiempo, size);
                            listaHilos.add(new ConsumidorElementos(nombre, colaElementos, tiempo, size));
                        }
                    } catch (NumberFormatException e) {
                        // Formato de número incorrecto. Línea no válida (no se hace nada).
                    }
                }
                linea = entrada.readLine(); // Se lee la siguiente línea del archivo
            }
            lecturaCorrecta = true;

        } catch (FileNotFoundException e) {
            System.out.println("Error: archivo " + NOMBRE_ARCHIVO_ENTRADA_TXT_DEFAULT + "·no encontrado.");
        } catch (IOException e) {
            System.out.println("Error: fallo en el acceso al archivo: " + e.getMessage());
        }

        // Lanzamos los hilos creados a partir de la configuración del archivo
        if (lecturaCorrecta) {
            System.out.println("\nCargada configuración de hilos.");
            System.out.printf("Cantidad de hilos: %d\n", listaHilos.size());

            System.out.println();
            System.out.println("Ejecución de hilos concurrentes");
            System.out.println("-------------------------------");
            for (Thread hilo : listaHilos) {
                hilo.start();
            }
        }

        // Esperamos a que finalice la ejecución de todos los hilos:
        // (no se pide en el enunciado)
        for (Thread hilo : listaHilos) {
            try {
                hilo.join();
            } catch (InterruptedException ex) {
                System.out.printf("Error: %s", ex.getMessage());
            }
        }
        System.out.println("\nTodos los hilos han finalizado su ejecución.");
        System.out.println("Fin del programa principal.");


        // Resumen final
        // (no se pide en el enunciado)
        System.out.println("\nResumen final de la ejecución de los hilos");
        System.out.println("------------------------------------------");
        for (Thread hilo : listaHilos) {
            if (hilo instanceof ProductorElementos) {
                System.out.printf("Productor %s. Elementos producidos: %s\n",
                        ((ProductorElementos) hilo).getNombre(),
                        ((ProductorElementos) hilo).getColaElementosProducidos().toString());
            } else if (hilo instanceof ConsumidorElementos) {
                System.out.printf("Consumidor %s. Elementos consumidos: %s\n",
                        ((ConsumidorElementos) hilo).getNombre(),
                        ((ConsumidorElementos) hilo).getColaElementosConsumidos().toString());
            }
            
        }

    }
}
