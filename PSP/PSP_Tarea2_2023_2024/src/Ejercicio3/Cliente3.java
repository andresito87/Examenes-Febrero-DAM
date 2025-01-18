/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio3;

import Ejercicio2.*;
import Ejercicio1.*;
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
 * @author Profe
 */
public class Cliente3 extends Thread{
    private String host;
    private int clave;
    private boolean descifrada;
    private int numCliente;
    private int puerto;
    private int comienzo;
    
    public Cliente3(int puerto,String host,int numCliente,int comienzo){
        this.puerto=puerto;
        this.host=host;
        this.numCliente = numCliente;
        this.comienzo=comienzo;
        descifrada = false;
        clave = 0;
    }
    
    public void run() {
        DataInputStream flujoEntrada=null;
        DataOutputStream flujoSalida = null;
        String respuesta="";
        Socket cliente = null; 
        boolean over=false;
        
        System.out.printf("PROGRAMA CLIENTE %d INICIADO...\n",numCliente);

        try {
            cliente = new Socket(host, puerto);
        
            //CREO FLUJO DE SALIDA AL SERVIDOR
            flujoSalida = new DataOutputStream(cliente.getOutputStream());
        
            //CREO FLUJO DE ENTRADA DEL CLIENTE
            flujoEntrada = new DataInputStream(cliente.getInputStream());
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente3.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        for(int i=comienzo; i<(comienzo+1000) && !descifrada && !over; i++){   
            try {
                //ENVÍO UN SALUDO AL SERVIDOR
                flujoSalida.writeUTF(""+i);
                
                //EL SERVIDOR ME ENVÍA UN MENSAJE
                respuesta = flujoEntrada.readUTF();
                
                if(respuesta.contains("Clave incorrecta")==true)
                    System.out.println("He intentado como clave el valor "+i+" y me dice: "+respuesta);
                else if(respuesta.contains("Has acertado la clave")==true){
                    System.out.println("He intentado como clave el valor "+i+" y me dice: "+respuesta);
                    descifrada = true;
                    clave = i;
                }else{
                    System.out.println("Game over. No he conseguido descifrar la clave.");
                    over = true;
                }
            } catch (IOException ex) {
                Logger.getLogger(Cliente3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        try {
            //CERRAR STREAMS Y SOCKETS
            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isDescifrada() {
        return this.descifrada;
    }

    public int getClave() {
        return clave;
    }

    public int getNumCliente() {
        return numCliente;
    }
    
    
}
