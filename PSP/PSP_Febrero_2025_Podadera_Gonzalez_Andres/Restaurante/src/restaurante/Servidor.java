package restaurante;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author AlumnoFPD Andres Samuel Podadera Gonzalez
 */
public class Servidor implements Runnable {

    private static final int PUERTO = 60000;
    private static int MAX_CAPACIDAD = 25;
    private static int plazasOcupadasSabado = 0;
    private static int plazasOcupadasDomingo = 0;
    private static int contadorClientes = 1;

    private Socket skCliente;
    private String nombreCliente = "";

    public static int getPlazasOcupadasSabado() {
        return plazasOcupadasSabado;
    }

    public static void setPlazasOcupadasSabado(int plazasOcupadasSabado) {
        Servidor.plazasOcupadasSabado = plazasOcupadasSabado;
    }

    public static int getPlazasOcupadasDomingo() {
        return plazasOcupadasDomingo;
    }

    public static void setPlazasOcupadasDomingo(int plazasOcupadasDomingo) {
        Servidor.plazasOcupadasDomingo = plazasOcupadasDomingo;
    }

    public Servidor(Socket skCliente, String nombre) {
        this.skCliente = skCliente;
        this.nombreCliente = nombre;
    }

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(PUERTO);
            System.out.println("Servidor escuchando en el puerto " + PUERTO + " ...");

            while (true) {
                Socket skCliente = ss.accept();
                new Thread(new Servidor(skCliente, "Cliente " + contadorClientes)).start();
                contadorClientes++;
            }

        } catch (Exception e) {
        }
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(skCliente.getInputStream()));
            PrintWriter pw = new PrintWriter(skCliente.getOutputStream(), true);

            String dia = br.readLine();
            int comensales = Integer.parseInt(br.readLine());

            if (dia.equals("sabado")) {
                if (plazasOcupadasSabado < MAX_CAPACIDAD && plazasOcupadasSabado + comensales < MAX_CAPACIDAD) {
                    System.out.println(this.nombreCliente + " reserva para sabado de " + comensales + " comensales.");
                    System.out.println("Plazas restantes: Sábado = " + (MAX_CAPACIDAD - plazasOcupadasSabado) + " Domingo = " + (MAX_CAPACIDAD - plazasOcupadasDomingo));
                    pw.println(this.nombreCliente + " solicita mesa para " + comensales + " comensales para el Sábado");
                    setPlazasOcupadasSabado(plazasOcupadasDomingo + comensales);
                } else {
                    System.out.println("No hay suficinetes plazas para esa reserva. " + this.nombreCliente + " - Sábado - " + comensales + " comensales");
                    pw.println(this.nombreCliente + " solicita mesa para " + comensales + " comensales para el Sábado, pero no hay plazas disponibles para ese día");
                }
            } else {
                if (plazasOcupadasDomingo < MAX_CAPACIDAD && plazasOcupadasDomingo + comensales < MAX_CAPACIDAD) {
                    System.out.println(this.nombreCliente + " reserva para domingo de " + comensales + " comensales.");
                    System.out.println("Plazas restantes: Sábado = " + (MAX_CAPACIDAD - plazasOcupadasSabado) + " Domingo = " + (MAX_CAPACIDAD - plazasOcupadasDomingo));
                    pw.println(this.nombreCliente + " solicita mesa para " + comensales + " comensales para el Domingo");
                    setPlazasOcupadasDomingo(plazasOcupadasDomingo + comensales);
                } else {
                    System.out.println("No hay suficinetes plazas para esa reserva. " + this.nombreCliente + " - Domingo - " + comensales + " comensales");
                    pw.println(this.nombreCliente + " solicita mesa para " + comensales + " comensales para el Domingo, pero no hay plazas disponibles para ese día");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    

}
