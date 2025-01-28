package tarea1ejer2;

public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Almacen almacen = new Almacen(10); // Buffer con capacidad para 10 valores

        // Crear dos hilos productores y un hilo consumidor
        Productor productor1 = new Productor(almacen, "Hilo productor 1");
        Productor productor2 = new Productor(almacen, "Hilo productor 2");
        Consumidor consumidor = new Consumidor(almacen);

        // Iniciar los hilos
        productor1.start();
        productor2.start();
        consumidor.start();
    }
    
}
