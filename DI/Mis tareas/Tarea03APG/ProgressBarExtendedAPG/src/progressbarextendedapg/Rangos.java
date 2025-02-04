package progressbarextendedapg;

import java.io.Serializable;

/**
 * Clase que representa un rango numérico para definir zonas de interés en una
 * barra de progreso.
 * <p>
 * La clase {@code Rangos} permite almacenar y gestionar dos valores numéricos
 * que representan límites o puntos de referencia en un sistema relacionado con
 * progresos o intervalos.
 * </p>
 *
 * <h2>Características principales:</h2>
 * <ul>
 * <li>Permite definir dos límites o rangos personalizados.</li>
 * <li>Implementa la interfaz {@link Serializable} para que los objetos puedan
 * ser serializados y persistidos.</li>
 * </ul>
 *
 * <p>
 * Esta clase se utiliza comúnmente en el contexto de barras de progreso
 * extendidas para destacar zonas específicas según el valor actual.</p>
 *
 * @author ANDRÉS SAMUEL PODADERA GONZÁLEZ
 * <p>
 * Desarrollo Interfaces Desarrollo de aplicaciones multiplataforma 2024/2025
 * </p>
 */
public class Rangos implements Serializable {

    private double rango1;
    private double rango2;

    /**
     * Constructor de la clase {@code Rangos}.
     * <p>
     * Inicializa los valores de los rangos utilizados para definir las zonas de
     * una barra de progreso u otros elementos relacionados.
     * </p>
     *
     * @param rango1 el valor inicial para el primer rango.
     * @param rango2 el valor inicial para el segundo rango.
     */
    public Rangos(double rango1, double rango2) {
        this.rango1 = rango1;
        this.rango2 = rango2;
    }

    /**
     * Get the value of rango1
     *
     * @return the value of rango1
     */
    public double getRango1() {
        return rango1;
    }

    /**
     * Set the value of rango1
     *
     * @param rango1 new value of rango1
     */
    public void setRango1(double rango1) {
        this.rango1 = rango1;
    }

    /**
     * Get the value of rango2
     *
     * @return the value of rango2
     */
    public double getRango2() {
        return rango2;
    }

    /**
     * Set the value of rango2
     *
     * @param rango2 new value of rango2
     */
    public void setRango2(double rango2) {
        this.rango2 = rango2;
    }
}
