
package customtext;


import java.beans.PropertyEditorSupport;

public class LongitudPropertyEditor extends PropertyEditorSupport {
    
    private final String[] opciones = {"5", "10", "15", "20", "25", "30"};

    @Override
    public String[] getTags() {
        return opciones;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            setValue(Integer.valueOf(text)); // Convierte el texto en un valor entero
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Valor inválido: " + text);
        }
    }

    @Override
    public String getAsText() {
        Object value = getValue();
        return value != null ? value.toString() : "10"; // Devuelve el valor predeterminado si es nulo
    }

    @Override
    public String getJavaInitializationString() {
        return getAsText();
    }
    
}
