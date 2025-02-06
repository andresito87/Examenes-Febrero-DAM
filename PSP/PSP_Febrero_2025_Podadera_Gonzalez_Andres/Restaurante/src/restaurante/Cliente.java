package restaurante;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

/**
 *
 * @author AlumnoFPD Andres Samuel Podadera Gonzalez
 */
public class Cliente implements Runnable {

    private static final String HOST = "localhost";
    private static final int PUERTO = 60000;
    private String nombre;
    private Random random= new Random();

    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            Socket sk = new Socket(HOST, PUERTO);

            BufferedReader br = new BufferedReader(new InputStreamReader(sk.getInputStream()));
            PrintWriter pw = new PrintWriter(sk.getOutputStream(), true);

            int dia = random.nextInt(2) + 1;
            int comensales = random.nextInt(5) + 2;
            pw.println((dia == 1) ? "sabado" : "domingo");
            pw.println(comensales);
            String respuesta = br.readLine();
            System.out.println(respuesta);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
