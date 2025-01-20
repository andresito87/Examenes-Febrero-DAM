package psp_ejercicio_taller_pintura;

/**
 *
 * @author andres
 */
public class Cliente extends Thread {

    private String nombre;
    private Sala sala;

    public Cliente(String nombre, Sala sala) {
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
        while (this.sala.getCochesRecibidosParaPintar() < 10) {
            this.sala.aparcarCocheDescampado();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
