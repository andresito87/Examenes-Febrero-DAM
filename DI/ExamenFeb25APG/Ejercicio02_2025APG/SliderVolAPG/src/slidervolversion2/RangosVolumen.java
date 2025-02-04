package slidervolversion2;

import java.io.Serializable;

/**
 *
 * @author andres
 */
public class RangosVolumen implements Serializable {

    private double rango1;
    private double rango2;
    private double rango3;

    public RangosVolumen(double rango1, double rango2, double rango3) {
        this.rango1 = rango1;
        this.rango2 = rango2;
        this.rango3 = rango3;
    }

    public double getRango1() {
        return rango1;
    }

    public void setRango1(double rango1) {
        this.rango1 = rango1;
    }

    public double getRango2() {
        return rango2;
    }

    public void setRango2(double rango2) {
        this.rango2 = rango2;
    }

    public double getRango3() {
        return rango3;
    }

    public void setRango3(double rango3) {
        this.rango3 = rango3;
    }
}
