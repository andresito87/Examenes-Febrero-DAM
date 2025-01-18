/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio3;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Profe
 */
public class Servidor3 extends Thread {

    private Socket socketCliente;

    // Flujo de salida a través del cual enviaremos información al proceso cliente 
    // conectado a través del socket
    private DataOutputStream flujoEscrituraCliente;

    // Flujo de entrada a través del cual recibiremos información desde el proceso cliente
    private DataInputStream flujoEntradaCliente;
    
    private int numCliente;
    
    private int clave;

    public Servidor3(Socket socketCliente, int numCliente, int clave) throws IOException {
        this.socketCliente = socketCliente;
        // Flujo de salida a través del cual enviaremos información al proceso cliente 
        // conectado a través del socket
        this.flujoEscrituraCliente = new DataOutputStream(this.socketCliente.getOutputStream());

        // Flujo de entrada a través del cual recibiremos información desde el proceso cliente
        this.flujoEntradaCliente = new DataInputStream(this.socketCliente.getInputStream());
        
        this.numCliente = numCliente;
        
        this.clave = clave;
    }

    @Override
    public void run() {
        boolean descifrada=false;
        int numIntentos=0;
        int intento;
        System.out.printf("Iniciado hilo servidor %d.\n", this.numCliente);
        String cadena="";
        System.out.println("Se conecta un cliente que intenta descifrar la clave.");
        while(!descifrada && numIntentos<1000){

            try {
                //EL CLIENTE ME ENVÍA UNA PRUEBA DE CLAVE
                cadena = flujoEntradaCliente.readUTF();
                //System.out.println(cadena);
                intento = Integer.parseInt(cadena);

                numIntentos++;
                
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Servidor3.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                //ENVÍO LA RESPUESTA ANTE EL INTENTO
                if(clave == intento){
                    flujoEscrituraCliente.writeUTF("Has acertado la clave en "+numIntentos+" intentos.");
                    descifrada=true;
                }else if(numIntentos <1000){
                    flujoEscrituraCliente.writeUTF("Clave incorrecta. Llevas "+numIntentos+" intentos.");
                }else{
                    flujoEscrituraCliente.writeUTF("Has superado el número de intentos permitidos.");
                }

            } catch (IOException ex) {
                System.out.println("Aquí falla--->"+cadena);
                Logger.getLogger(Servidor3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(descifrada)
            System.out.println("El cliente ha descifrado la clave en "+numIntentos+" intentos.");
        else
            System.out.println("El cliente ha agotado el número de intentos máximo sin descifrar la clave.");
    }
}
