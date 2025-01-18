package Ejercicio3;

import java.util.ArrayList;
/**
 *
 * @author Profe
 */
public class MainCliente3 {
    public static void main(String[] args) {
        int claveDescifrada=0;
        int numeroPuerto=6000;
        String ip = "localhost";
        ArrayList<Cliente3> clientes = new ArrayList<Cliente3>();
        for(int j=1,i=1; i<10000;i = i+1000,j++){
            System.out.println("HE CREADO EL CLIENTE que inicia en  "+i+" y termina en "+(i+1000));
            clientes.add(new Cliente3(numeroPuerto,ip,+j,i));
        }
        
        System.out.println("PROGRAMA DE CLIENTES DESCIFRADORES INICIADO...");
        System.out.println("----------------------------------------------");
        
        for (Cliente3 cliente : clientes) {
            cliente.start();
        }
        
        for (Cliente3 cliente : clientes) {
            try {
                cliente.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("\nResumen del proceso de descifrado por fuerza bruta:");
        for (Cliente3 cliente : clientes) {
            if(cliente.isDescifrada()){
                System.out.printf("El cliente %d ha descifrado la clave\n",cliente.getNumCliente());
                claveDescifrada = cliente.getClave();
            }else{
                System.out.printf("El cliente %d no ha conseguido descifrar la clave\n",cliente.getNumCliente());
            }
        }
    }
   
}
