package customtext;

import java.io.Serializable;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CustomTextfield extends JTextField implements Serializable {
    
    private int longitud;

    public CustomTextfield() {
        super();
        this.longitud = 10; // Valor inicial predeterminado
        setColumns(this.longitud); // Aplica el tamaño predeterminado
    }

    // Getter para la propiedad longitud
    public int getLongitud() {
        return this.longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
        setColumns(this.longitud); // Cambia el tamaño del JTextField
    }

}
