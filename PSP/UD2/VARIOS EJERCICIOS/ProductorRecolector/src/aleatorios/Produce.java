package aleatorios;
import java.util.Random; //Paquete para la generaci�n de n�meros aleatorios
/** Aplicaci�n que genera 40 n�meros aleatorios entre los valores 0 y 100
 * @author Marga Nieto
 */
public class Produce {
    /** M�todo main de la aplicaci�n, punto de entrada al programa.
     * @param args Los comandos recibidos por la l�nea de ejecuci�n
     * no son tenidos en cuenta.
     */
    public static void main(String[] args) {
         // Lo �nico que hacemos es escribir los 40 n�meros aleatorios en
        // la salida est�ndar del proceso
        Random v = new Random(); //Instanciamos el objeto para generar aleatorios
        for (int i=0; i<40; i++) //Bucle para generar 40 valores
            /*//Podemos generar n�meros aleatorios utilizando Math.Random()
             *  System.out.println(Math.floor(Math.random()*100));
             */
            System.out.println(v.nextInt(101)); //valores enteros entre 0 y (101-1)
            //Escribimos un n�mero por l�nea, para que se incluya intro
    }
}