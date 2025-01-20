package psp_ejercicio_taller_pintura;

/**
 *
 * @author andres
 */
public class Pintor extends Thread {

    private String nombre;
    private Sala sala;

    public Pintor(String nombre, Sala sala) {
        this.nombre = nombre;
        this.sala = sala;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    @Override
    public void run() {
        while (sala.getCochesRecibidosParaPintar() < 10) {
            try {
                this.sala.meterCocheParaPintar();
                Thread.sleep(3000);
                this.sala.entregarCocheAlCliente();
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
