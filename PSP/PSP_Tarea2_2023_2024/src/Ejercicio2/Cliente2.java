/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio2;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author profe
 */
public class Cliente2 {
    public static void main(String[] arg){
        String Host = "localhost";
        int numeroPuerto=6000;
        int clave = 0;
        boolean descifrada = false, over = false;
        DataInputStream flujoEntrada=null;
        DataOutputStream flujoSalida = null;
        String respuesta="";
        Socket cliente = null;       
        
        System.out.println("PROGRAMA CLIENTE INICIADO...");

        try {
            cliente = new Socket(Host, numeroPuerto);
        
            //CREO FLUJO DE SALIDA AL SERVIDOR
            flujoSalida = new DataOutputStream(cliente.getOutputStream());
        
            //CREO FLUJO DE ENTRADA DEL CLIENTE
            flujoEntrada = new DataInputStream(cliente.getInputStream());
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente2.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        for(int i=1; i<=9999 && !descifrada && !over; i++){   
            try {
                //ENVÍO UN NUMERO AL SERVIDOR
                flujoSalida.writeUTF(""+i);
                
                //EL SERVIDOR ME ENVÍA UN MENSAJE
                respuesta = flujoEntrada.readUTF();
                
                if(respuesta.contains("Clave incorrecta")==true)
                    System.out.println("He intentado como clave el valor "+i+" y me dice: "+respuesta);
                else if(respuesta.contains("Has acertado la clave")==true){
                    System.out.println("He intentado como clave el valor "+i+" y me dice: "+respuesta);
                    descifrada = true;
                }else{
                    System.out.println("Game over. No he conseguido descifrar la clave.");
                    over = true;
                }
            } catch (IOException ex) {
                Logger.getLogger(Cliente2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        try {
            //CERRAR STREAMS Y SOCKETS
            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
