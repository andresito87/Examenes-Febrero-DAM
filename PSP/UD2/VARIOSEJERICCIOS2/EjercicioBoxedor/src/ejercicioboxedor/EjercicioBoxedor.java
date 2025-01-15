package ejercicioboxedor;

public class EjercicioBoxedor {

    public static void main(String[] args) throws InterruptedException {

       ClaseRing ring = new ClaseRing();

      
        ClaseBoxeador Tyson = new ClaseBoxeador(ring, "Tyson");
        ClaseBoxeador Rocky = new ClaseBoxeador(ring, "Rocky");
        

        System.out.println("Iniciando programa boxeador");
        Tyson.start();
        Rocky.start();
        
        Tyson.join();
        Rocky.join();

        System.out.println("Resumen resultados...");
        System.out.println("Golpes Tyson..." +  Tyson.getGolpes());
        System.out.println("Golpes Rocky..." +  Rocky.getGolpes());
        
        
        
        
    }

}
