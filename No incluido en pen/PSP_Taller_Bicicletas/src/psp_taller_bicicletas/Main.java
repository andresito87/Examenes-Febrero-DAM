package psp_taller_bicicletas;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        List<Thread> listaProductores = new ArrayList();
        List<Thread> listaConsumidores = new ArrayList();

        Almacen almacenCompartido = new Almacen();
        OperarioProductor operarioProductor1 = new OperarioProductor("Pedro Pro", almacenCompartido);
        OperarioProductor operarioProductor2 = new OperarioProductor("Sandra Pro", almacenCompartido);
        OperarioProductor operarioProductor3 = new OperarioProductor("Laura Pro", almacenCompartido);
        OperarioProductor operarioProductor4 = new OperarioProductor("Jose Pro", almacenCompartido);
        OperarioProductor operarioProductor5 = new OperarioProductor("Felipe Pro", almacenCompartido);

        listaProductores.add(new Thread(operarioProductor1));
        listaProductores.add(new Thread(operarioProductor2));
        listaProductores.add(new Thread(operarioProductor3));
        listaProductores.add(new Thread(operarioProductor4));
        listaProductores.add(new Thread(operarioProductor5));

        OperarioConsumidor operarioConsumidor1 = new OperarioConsumidor("Ana Con", almacenCompartido);
        OperarioConsumidor operarioConsumidor2 = new OperarioConsumidor("Luis Con", almacenCompartido);
        OperarioConsumidor operarioConsumidor3 = new OperarioConsumidor("Andrés Con", almacenCompartido);

        listaConsumidores.add(new Thread(operarioConsumidor1));
        listaConsumidores.add(new Thread(operarioConsumidor2));
        listaConsumidores.add(new Thread(operarioConsumidor3));

        for (Thread productor : listaProductores) {
            productor.start();
        }

        for (Thread consumidor : listaConsumidores) {
            consumidor.start();
        }

        for (Thread productor : listaProductores) {
            try {
                productor.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (Thread consumidor : listaConsumidores) {
            try {
                consumidor.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("Finalizada la jornada laboral. TODOS PARA CASA.");
    }

}
