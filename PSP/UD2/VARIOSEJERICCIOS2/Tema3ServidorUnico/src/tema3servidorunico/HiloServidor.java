/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema3servidorunico;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HiloServidor extends Thread {

    int numeroSecreto;
    Socket socketCliente;

    public HiloServidor(int numeroSecreto, Socket socketCliente) {
        this.numeroSecreto = numeroSecreto;
        this.socketCliente = socketCliente;
    }

    @Override
    public void run() {
        try {
            int numRecibido;
            DataInputStream flujoEntrada = new DataInputStream(this.socketCliente.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(this.socketCliente.getOutputStream());

            System.out.println("Soy el hilo servidor " + this.getName());
            numRecibido = Integer.parseInt(flujoEntrada.readUTF());

            if (this.numeroSecreto == numRecibido) {
                System.out.println("Un cliente ha acertado el numero!!");
                flujoSalida.writeUTF(" OK , Has acertado el número secreto...");
            } else {
                System.out.println("El cliente no ha acertado el número");
            }
            flujoSalida.writeUTF("No has acertado el número secreto...");

        } catch (IOException ex) {
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
