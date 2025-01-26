package tarea1ejer2;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author LuisRosillo <>
 */
class Almacen {
    private final int[] array;
    private int contador = 0; // N�mero de elementos en el buffer
    private int suma = 0; // Suma total de los elementos consumidos
    private final int maxSuma = 100; // Valor m�ximo de la suma
    private FileWriter writer;

    public Almacen(int size) {
        array = new int[size];
        try {
            writer = new FileWriter("log.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void producir(int valor, String productor) throws InterruptedException {
        while (contador == array.length) { // Si el buffer est� lleno, esperar
            wait();
        }
        if (!sumaAlcanzada()) {
            // Agregar el valor al array
            array[contador] = valor;
            contador++;
            registrar(productor + " introduce el valor " + valor + " en la posici�n " + contador, array, suma);

            // Notificar al consumidor que hay datos disponibles
            notifyAll();
        }
        
    }

    public synchronized void consumir() throws InterruptedException, IOException {
        while (contador == 0) { // Si el buffer est� vac�o, esperar
            wait();
        }

        // Consumir el �ltimo elemento
        int valor = array[contador - 1];
        contador--;
        suma += valor;
        registrar("Hilo consumidor saca el valor " + valor + " de la posici�n " + (contador + 1), array, suma);

        // Notificar a los productores que hay espacio disponible
        notifyAll();

        // Comprobar si hemos alcanzado el m�ximo
        if (suma >= maxSuma) {
            registrar("Suma m�xima alcanzada: " + suma, array, suma);
            writer.close();
        }
    }

    private void registrar(String accion, int[] array, int suma) {
        try {
            StringBuilder arrayStr = new StringBuilder();
            for (int i = 0; i < contador; i++) {
                arrayStr.append(array[i]);
                if (i < contador - 1) arrayStr.append(" - ");
            }
            writer.write(accion + ", Array = " + arrayStr.toString() + ", Suma = " + suma + "\n");
            writer.flush();
            System.out.println(accion + ", Array = " + arrayStr.toString() + ", Suma = " + suma + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public synchronized boolean sumaAlcanzada(){
        return (suma>=100);
    }

}

