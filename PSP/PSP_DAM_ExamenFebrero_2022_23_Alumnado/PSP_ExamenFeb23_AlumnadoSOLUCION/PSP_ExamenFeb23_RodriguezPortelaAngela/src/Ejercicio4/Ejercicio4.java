
package Ejercicio4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angela Rodriguez Portela
 */
public class Ejercicio4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int numeroPuerto = 1234;
        ServerSocket servidor = null;
        Socket clienteConectado = null;
        InputStream entrada = null;
        OutputStream salida = null;
        DataInputStream flujoEntrada = null;
        DataOutputStream flujoSalida = null;
        String mensaje = "";
        long numero;
        int num;

        System.out.println("SERVIDOR NO CONCURRENTE DE ESCRITURA DE NUMEROS");
        System.out.println("Servidor de la alumna ANGELA RODRIGUEZ PORTELA iniciado en el puerto 1234");
        System.out.println("----------------------------------------------");

        try {
            servidor = new ServerSocket(numeroPuerto);

            System.out.println("Esperando al cliente...");
            System.out.println("----------------------------------------------");

            clienteConectado = servidor.accept();

            //CREO FLUJO DE ENTRADA DEL CLIENTE
            entrada = clienteConectado.getInputStream();
            flujoEntrada = new DataInputStream(entrada);

            //CREO FLUJO DE SALIDA AL CLIENTE
            salida = clienteConectado.getOutputStream();
            flujoSalida = new DataOutputStream(salida);
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio4.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Recibiendo del cliente:");

        try {
            //EL CLIENTE ME ENVÍA UNA PRUEBA DE CLAVE
            mensaje = flujoEntrada.readUTF();
            if (mensaje.length() <= 2 && mensaje.length() > 0) {
                numero = Long.parseLong(mensaje);
                num = Long.valueOf(mensaje).intValue();
                System.out.println("Mensaje:" + mensaje);
                System.out.println("El número es " + traducir(num));
                flujoSalida.writeUTF("El número es " + traducir(num));
                flujoSalida.flush();
            }else{
                flujoSalida.writeUTF("El valor introducido no es numérico o cumple con las caracteristicas requeridas.");
                flujoSalida.flush();
            }
        } catch (Exception ex) {
            try {
                flujoSalida.writeUTF("El valor introducido no es numérico o cumple con las caracteristicas requeridas.");
                flujoSalida.flush();
            } catch (IOException ex1) {
                Logger.getLogger(Ejercicio4.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        System.out.println("FIN DE EJECUCION DEL SERVIDOR");

        try {
            //CERRAR STREAMS Y SOCKETS
            entrada.close();
            flujoEntrada.close();
            salida.close();
            flujoSalida.close();
            clienteConectado.close();
            servidor.close();
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static String traducir(int num) {
        String nombre;
        String[][] arrayNombresNumeros = {{"cero", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve"},
        {"diez", "once", "doce", "trece", "catorce", "quince", "dieciséis", "diecisiete", "dieciocho", "diecinueve"},
        {"veinte", "veintiuno", "veintidós", "veintitrés", "veinticuatro", "veinticinco", "veintiséis", "veintisiete", "veintiocho", "veintinueve"},
        {"treinta"},
        {"cuarenta"},
        {"cincuenta"},
        {"sesenta"},
        {"setenta"},
        {"ochenta"},
        {"noventa"}};
        int decena;
        int unidad;
        
        decena = num / 10;
        unidad = num % 10;
        if (decena < 3) {    
            nombre = arrayNombresNumeros[decena][unidad];
        } else {
            if (unidad == 0) {
                nombre = arrayNombresNumeros[decena][0];
            } else {
                nombre = arrayNombresNumeros[decena][0] + " y " + arrayNombresNumeros[0][unidad];
            }

        }

        return nombre;
    }

}
