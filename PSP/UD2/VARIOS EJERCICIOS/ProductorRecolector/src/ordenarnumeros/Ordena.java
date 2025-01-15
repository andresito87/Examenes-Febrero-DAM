package ordenarnumeros;
import java.io.InputStreamReader; //Importamos los paquetes necesarios
import java.io.BufferedReader;    //para manejar la entrada/salida del proceso
import java.io.IOException;
import java.util.LinkedList; //Para trabajar con listas
import java.util.Collections; //Para ordenar listas
/** Aplicaci�n que devolver� ordenados los n�meros que recibe en su entrada est�ndar.
 * El n�mero de elemntos en el conjunto de n�meros puede ser cualquiera (no fijo).
 * @author Marga Nieto
 */
public class Ordena {
    /** M�todo main de la aplicaci�n. Punto de entrada de ejecuci�n.
     * Devolver� ordenados los n�meros que recibe en su entrada est�ndar.
     * El n�mero de elemntos en el conjunto de n�meros puede ser cualquiera (no fijo).
     * @param args Los argumentos de la l�nea de comandos no se tendr�n en cuenta
     */
    public static void main(String[] args) {
        // Vamos a leer de la entrada est�ndar del proceso y escribir
        // los valores recibidos en la salida est�ndar del proceso, ordenados.
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader (isr);
        // Obtenemos el stream de lectura de la entrada est�ndar
        // utilizamos un lector con Buffered, para no perder ning�n dato
        String lineaTeclado = null; //Variable para ir leyendo lo le�do de teclado
        LinkedList<Integer> lista = new LinkedList<Integer>();
        try{
            System.out.println("Proceso ordenaN�meros");
            //Mostramos que el proceso que est� escribiendo es el que est�
            //leyendo los datos.
            while ((lineaTeclado = bf.readLine())!= null){ //Mientras haya datos disponibles
                if (isNumeric(lineaTeclado)) { //Comprobamos que el valor le�do es num�rico
                    int v = Integer.parseInt(lineaTeclado);
                    //Estamos seguros de que no
                    lista.add(v);
                }
            }
            //Ordenamos la lista de valores
            ordena(lista);
            //Mostramos los datos ordenados
            System.out.println("Los enteros ordenados son: ");
            for(Object elemento: lista) //Recorremos todos los elementos de la lista
                System.out.println(elemento.toString()); //escribimos cada uno de ellos
            System.out.println("N�mero de elementos le�dos: " + lista.size());
        }catch(IOException ex){
            System.err.println("Se ha producido un error de E/S. Su descripci�n es: ");
            System.err.println(ex.toString());
        }catch(Exception ex){
            System.err.println("Se ha producido un error. Su descripci�n es: ");
            System.err.println(ex.toString());
        }
    }
    /**
     * M�todo que comprueba si una cadena representa un dato num�rico entero
     * @param dato String con el valor que se quiere comprobar.
     * @return true en el caso de que el contenido sea num�rico entero y false en caso contrario
     */
    private static boolean isNumeric(String dato){
        try{
            Integer.parseInt(dato); //Intentamos hacer la conversi�n
            return true; //Si no se ha producido excepci�n, el valor es num�rico
        }catch(Exception e){
            return false; //El dato no se puede convertir en n�mero
        }
    }
    /**
     * M�todo que ordena una lista
     * @param lista Lista de tipo LinkedList que va a ser ordenada
     */
    private static void ordena(LinkedList<Integer> lista) {
        Collections.sort(lista);   //Ordenamos los elementos, en orden ascenente
                                   //seg�n su orden natural
        //Podemos utilizar directamente Collections.sort(lista);
        //Porque los elementos que contiene son enteros y son comparables
    }
}