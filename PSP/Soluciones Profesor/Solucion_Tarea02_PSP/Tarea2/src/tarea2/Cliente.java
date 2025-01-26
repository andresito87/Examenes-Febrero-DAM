package tarea2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

/**
 *
 * @author 
 */
public class Cliente implements Runnable {

    //Variables
    private static final String HOST = "localhost";
    private static final int PUERTO = 12346;
    private int numeroCliente;
    private int numeroJuego;

    /**
     * Constructor del cliente (jugador)
     *
     * @param numeroJuego
     */
    public Cliente( int numeroJuego) {
        this.numeroJuego = numeroJuego;
    }

    

    /**
     * M�todo run que ejecuta los diferentes inicios de los juegos
     */
    @Override
    public void run() {
        try {
            //Abrir conexi�n
            Socket clienteSocket = new Socket(HOST, PUERTO);

            //Lectura
            BufferedReader br = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));

            //Escritura
            PrintWriter pw = new PrintWriter(clienteSocket.getOutputStream(), true);

            String linea = "";

            //El servidor me indica que numero soy
            this.numeroCliente = Integer.parseInt(br.readLine());
            //Env�o del n�mero de juego a jugar hacia el servidor
            pw.println(this.numeroJuego);

            //------------------------------------------------------------------
            //------------- JUEGO 1 ADIVINAR N�MERO ALEATORIO ------------------
            //------------------------------------------------------------------
            if (this.numeroJuego == 1) {

                System.out.println("Jugador " + this.numeroCliente + " entra en el juego 1");
                int numeroMinimo = 1;
                int numeroMaximo = 100;
                int numeroAleatorio = 0;

                while ((linea = br.readLine()) != null && !linea.equals("acertado") && !linea.equals("perdido")) {
                    if (linea.equals("mayor")) {
                        //El m�nimo es el n�mero aleatorio + 1
                        numeroMinimo = numeroAleatorio + 1;
                    } else if (linea.equals("menor")) {
                        //El m�ximo es el n�mero aleatorio - 1
                        numeroMaximo = numeroAleatorio - 1;
                    }

                    //Controlaremos el rango de aleatorios
                    numeroAleatorio = new Random().nextInt(numeroMaximo-numeroMinimo+1)+numeroMinimo;
                    pw.println(numeroAleatorio);
                }
                //Gestionamos mensajes por consola (mismos mensajes que se guardan en los archivos)
                String resultado = br.readLine();
                System.out.println(resultado);
                

                //Finaliza la conversaci�n con el servidor
                pw.println("Buena partida!");

            }

            //------------------------------------------------------------------
            //---------------- JUEGO 2 LANZAMIENTO DE DADOS --------------------
            //------------------------------------------------------------------
            else if (this.numeroJuego == 2) {

                System.out.println("Jugador " + this.numeroCliente + " entra en el juego 2");

                while ((linea = br.readLine()) != null && !linea.equals("fin")) {
                    int numeroAleatorio = (int) (Math.random() * 6 + 1);
                    pw.println(numeroAleatorio);
                }

                //Gestionamos mensajes por consola (mismos mensajes que se guardan en los archivos)
                System.out.println(br.readLine());

            }

            //------------------------------------------------------------------
            //--------------- JUEGO 3 PIEDRA, PAPEL O TIJERAS ------------------
            //------------------------------------------------------------------
            else if (this.numeroJuego == 3) {

                System.out.println("Jugador " + this.numeroCliente + " entra en el juego 3");

                while ((linea = br.readLine()) != null && !linea.equals("fin")) {
                    int numeroAleatorio = (int) (Math.random() * 3 + 1);
                    pw.println(numeroAleatorio);
                }

                //Gestionamos mensajes por consola (mismos mensajes que se guardan en los archivos)
                System.out.println(br.readLine());
            }

        } catch (IOException e) {
            System.err.println("Error al conectar al servidor: " + e.getMessage());
        }
    }
}
