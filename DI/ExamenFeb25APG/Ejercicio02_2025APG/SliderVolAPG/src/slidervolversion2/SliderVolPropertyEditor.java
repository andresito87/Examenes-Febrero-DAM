package slidervolversion2;

import java.awt.Component;
import java.beans.PropertyEditorSupport;

/**
 *
 * @author andres
 */
public class SliderVolPropertyEditor extends PropertyEditorSupport {

    public SliderVolPanel sliderVolPanel = null;

    public SliderVolPropertyEditor() {
        this.sliderVolPanel = new SliderVolPanel();
    }

    @Override
    public boolean supportsCustomEditor() {
        return true; // siempre devuelve true si usamos un editor personalizado
    }

    @Override
    public Component getCustomEditor() {
        return this.sliderVolPanel; // devuelve el objeto ProgressBarPanel que será nuestro editor
    }

    @Override
    public String getJavaInitializationString() {
        RangosVolumen rangos = (RangosVolumen) getValue();
        // devuelve la cadena necesaria para generar el editor de rangos
        return ("new slidervolversion2.RangosVolumen(" 
                + rangos.getRango1() + ", " 
                + rangos.getRango2() + ", " 
                + rangos.getRango3() + ")");
    }

    @Override
    public Object getValue() {
        // devuelve el objeto Rangos con los valores inializados
        return this.sliderVolPanel.getSelectedValue(); 
    }

}
