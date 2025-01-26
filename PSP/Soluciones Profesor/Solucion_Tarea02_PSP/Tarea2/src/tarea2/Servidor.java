package tarea2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;


/**
 *
 * @author 
 */
public class Servidor extends Thread {
    
    private static int jugadores=1;

    public static void main(String[] args) {
        try {
            // Abrir conexión
            ServerSocket ss = new ServerSocket(12346);
            System.out.println("Servidor esperando clientes...");

            //Gestión de archivos para que se limpien al arrancar el programa.
            File archivo1 = new File("./Juego1.txt");
            if (archivo1.exists()) {
                archivo1.delete();
            }

            File archivo2 = new File("./Juego2.txt");
            if (archivo2.exists()) {
                archivo2.delete();
            }

            File archivo3 = new File("./Juego3.txt");
            if (archivo3.exists()) {
                archivo3.delete();
            }

            while (true) { // Permitir múltiples clientes
                Socket socket = ss.accept();
                System.out.println("Cliente se ha conectado...");
                
                // Manejo de cada cliente que llega al servidor
                new Thread(new HiloServidor(socket, jugadores++ )).start();

            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }


}


class HiloServidor implements Runnable {
        private final Socket socket;
        private final int numJugador;
        private Object candado1 = new Object();
        private Object candado2 = new Object();
        private Object candado3 = new Object();


        public HiloServidor(Socket socket, int numJugador) {
            this.socket = socket;
            this.numJugador = numJugador;
        }

        @Override
        public void run() {
            try {
                // Lectura de datos
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

                //Se envia el numero de jugador al cliente
                pw.println(numJugador);
                //Se recibe el juego del cliente (jugador) que ha iniciado el hilo.
                String juego = br.readLine();

                //Gestión de entrada en los juegos en base a lo recibido por el cliente (jugador).
                switch (juego) {
                    case "1" -> {
                        String texto = adivinarNumero(socket, "Jugador "+String.valueOf(numJugador));
                        ficheroJuego1(texto);
                    }
                    case "2" -> {
                        String texto = lanzarDados(socket, "Jugador "+String.valueOf(numJugador));
                        ficheroJuego2(texto);
                    }
                    case "3" -> {
                        String texto = piedraPapelTijeras(socket, "Jugador "+String.valueOf(numJugador));
                        ficheroJuego3(texto);
                    }
                }

            } catch (IOException e) {
                System.err.println("Error al manejar el cliente: " + e.getMessage());
            } finally {
                try {
                    socket.close();
                    System.out.println("Cliente desconectado.");
                } catch (IOException e) {
                    System.err.println("Error al cerrar el socket: " + e.getMessage());
                }
            }
            
        }
        
    
    /**
     * Juego en el que se adivina en un máximo de 10 intentos un número
     *
     * @param socket
     * @param nombre
     * @return
     * @throws IOException
     */
    private static String adivinarNumero(Socket socket, String nombre) throws IOException {

        //Lectura y escritura de datos
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

        //Número de intentos para adivinar número
        int intentos = 0;

        //Condición para salir del bucle si se acierta el número
        boolean numeroAcertado = false;

        String linea = "";
        String resultado = "";

        //Creación del número aleatorio a adivinar
        int numeroAleatorio = (int) (Math.random() * 100 + 1);
        pw.println("Comenzamos!");

        while (!numeroAcertado && intentos < 10 && (linea = br.readLine()) != null ) {

            intentos++;
            int numeroRecibido = Integer.parseInt(linea);

            //Se le mandará al cliente (jugador) si el número es mayor, menor o si ha acertado.
            if (numeroRecibido == numeroAleatorio) {
                numeroAcertado = true;
                pw.println("acertado");
            } else if (numeroRecibido < numeroAleatorio) {
                pw.println("mayor");
            } else if (numeroRecibido > numeroAleatorio) {
                pw.println("menor");
            }

        }
        //Gestión de información final con el resultado de cada jugador.
        if (numeroAcertado) {
            resultado = nombre + ": " + intentos + " intentos.";
        }else{
            pw.println("perdido");
            resultado = nombre + ": " + "no acertó el número.";
        }
        
        pw.println(resultado);


        //Resultado para mandar al método de gestión de archivos correspondiente
        return resultado;
    }

    
    /**
     * Juego donde se lanzan dados durante 5 rondas hasta encontrar un ganador
     * (si se saca el mismo números e repite la ronda)
     *
     * @param socket
     * @param nombre
     * @return
     * @throws IOException
     */
    private static String lanzarDados(Socket socket, String nombre) throws IOException {

        //Lectura y escritura de datos
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

        String linea = "";
        String resultado = "";
        int contadorPartida = 0;
        int puntosCliente = 0;
        int puntosServidor = 0;

        pw.println("Comenzamos!");

        while (contadorPartida < 5 && (linea = br.readLine()) != null ) {

            //Simulación de dados de juego
            int dadoServidor = (int) (Math.random() * 6 + 1);
            int dadoCliente = Integer.parseInt(linea);
            //pw.println(dadoServidor);

            //Gestión de puntos de cada ronda
            if (dadoServidor > dadoCliente) {
                puntosServidor++;
                contadorPartida++;
            } else if (dadoServidor < dadoCliente) {
                puntosCliente++;
                contadorPartida++;
            }

            if (contadorPartida == 5) {
                pw.println("fin");
            }else{
                pw.println("Tira!");
            }

        }
        
        //Gestión de información final con el resultado de cada jugador.
        if (puntosCliente > puntosServidor) {
            resultado = nombre + ": Gana " + puntosCliente + "-" + puntosServidor + " contra el servidor";
        } else {
            resultado = nombre + ": Pierde " + puntosCliente + "-" + puntosServidor + " contra el servidor";
        }
        pw.println(resultado);

        //Resultado para mandar al método de gestión de archivos correspondiente
        return resultado;
    }

    

    /**
     * Juego de piedra, papel y tijeras durante 5 rondas hasta encontrar un
     * ganador (si se saca el mismo números e repite la ronda)
     *
     * @param socket
     * @param nombre
     * @return
     * @throws IOException
     */
    private static String piedraPapelTijeras(Socket socket, String nombre) throws IOException {

        //Lectura y escritura de datos
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

        String linea = "";
        String resultado = "";
        int contadorPartida = 0;
        int puntosCliente = 0;
        int puntosServidor = 0;

        pw.println("Comenzamos!");

        while (contadorPartida < 5 && (linea = br.readLine()) != null ) {

            //Se recibe el tipo de mano gestionado en un método (selectorMano)
            String manoServidor = selectorMano((int)(Math.random() * 3 + 1));
            String manoCliente = selectorMano(Integer.parseInt(linea));


            //Comprobaciones de cada una de las rondas
            if (manoServidor.equals("Piedra") && manoCliente.equals("Papel")
                    || manoServidor.equals("Papel") && manoCliente.equals("Tijeras")
                    || manoServidor.equals("Tijeras") && manoCliente.equals("Piedra")) {

                puntosCliente++;
                contadorPartida++;

            } else if (manoCliente.equals("Piedra") && manoServidor.equals("Papel")
                    || manoCliente.equals("Papel") && manoServidor.equals("Tijeras")
                    || manoCliente.equals("Tijeras") && manoServidor.equals("Piedra")) {

                puntosServidor++;
                contadorPartida++;
            }

            if (contadorPartida == 5) {
                pw.println("fin");
            }else{
                pw.println("3,2,1... ya");
            }
    
        }        
        //Gestión de información final con el resultado de cada jugador.
        if (puntosCliente > puntosServidor) {
            resultado = nombre + ": Gana " + puntosCliente + "-" + puntosServidor + " contra el servidor.";
        } else {
            resultado = nombre + ": Pierde " + puntosCliente + "-" + puntosServidor + " contra el servidor.";
        }
        pw.println(resultado);

        //Resultado para mandar al método de gestión de archivos correspondiente
        return resultado;
    }

    
    /**
     * Transformar el tipo de número aleatorio en una forma de la mano
     *
     * @param mano
     * @return
     */
    public static String selectorMano(int mano) {
        String eleccion="";
        switch (mano) {
            case 1 ->
                eleccion = "Piedra";
            case 2 ->
                eleccion = "Papel";
            case 3 ->
                eleccion = "Tijeras";
        }
        return eleccion;
    }
    
    /**
     * Método que crea el archivo con los resultados del juego 1
     *
     * @param texto
     */
    private void ficheroJuego1(String texto) {

        synchronized (candado1) {
            try (PrintWriter salida = new PrintWriter(new FileWriter("./Juego1.txt", true))) {

                salida.println(texto);

            } catch (IOException ex) {
                System.out.printf("Error: %s\n", ex.getMessage());
            }

        }
    }

    
    /**
     * Método que crea el archivo con los resultados del juego 2
     *
     * @param texto
     */
    private void ficheroJuego2(String texto) {
        synchronized (candado2) {
            try (PrintWriter salida = new PrintWriter(new FileWriter("./Juego2.txt", true))) {

                salida.println(texto);

            } catch (IOException ex) {
                System.out.printf("Error: %s\n", ex.getMessage());
            }

        }

    }
    
    
    /**
     * Método que crea el archivo con los resultados del juego 3
     *
     * @param texto
     */
    private void ficheroJuego3(String texto) {
        synchronized (candado3) {
            try (PrintWriter salida = new PrintWriter(new FileWriter("./Juego3.txt", true))) {

                salida.println(texto);

            } catch (IOException ex) {
                System.out.printf("Error: %s\n", ex.getMessage());
            }

        }

    }

    
        
        
    }
