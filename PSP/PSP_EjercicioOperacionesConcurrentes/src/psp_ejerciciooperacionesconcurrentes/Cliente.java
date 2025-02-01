package psp_ejerciciooperacionesconcurrentes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author andres
 */
public class Cliente implements Runnable {

    private static final String HOST = "localhost";
    private static final int PUERTO = 6000;

    private String nombre;
    private final String modo;
    private String operacion;

    public Cliente(String nombre, String modoUso, String operacion) {
        this.nombre = nombre;
        this.modo = modoUso;
        this.operacion = operacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModo() {
        return modo;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    @Override
    public void run() {
        try {
            Socket clienteSocket = new Socket(HOST, PUERTO);

            BufferedReader br = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            PrintWriter pw = new PrintWriter(clienteSocket.getOutputStream(), true);

            this.nombre = "Cliente " + br.readLine();
            String resultado;
            if (modo.equals("manual")) {
                pw.println(this.operacion);
                resultado = br.readLine();
            } else {
                Random random = new Random();
                String[] operaciones = {"+", "-"};
                this.operacion = String.valueOf(random.nextInt(100)) + operaciones[random.nextInt(2)] + String.valueOf(random.nextInt(100));
                pw.println(operacion);
                resultado = br.readLine();
            }
            System.out.println(this.nombre + " realizó operación " + this.operacion + " y recibe como resultado: " + resultado);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
