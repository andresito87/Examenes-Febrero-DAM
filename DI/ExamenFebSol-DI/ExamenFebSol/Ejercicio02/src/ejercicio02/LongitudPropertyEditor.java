/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio02;

import java.awt.Component;
import java.beans.PropertyEditorSupport;

/**
 *
 * @author Alumno
 */
public class LongitudPropertyEditor extends PropertyEditorSupport{
    //declaro una variable editor basada (del tipo de la clase) en la clase LongitudPanel
    LongitudPanel editor = null;
    
     public LongitudPropertyEditor() {
        
        //o lo que es lo mismo, creo un objeto de la clase LongitudPanel
        // esto lo hago inicializando la variable editor con la instrucción new()
        this.editor=new LongitudPanel();
    }

    @Override
    public Component getCustomEditor() {
        return editor;
    }

    @Override
    public Object getValue() {
        if (editor.isVisible()){
            return editor.spLongitud.getValue();
        }else{
            return super.getValue();
        }
    }

    @Override
    public void setValue(Object value) {
        super.setValue(value);
    }

    @Override
    public boolean supportsCustomEditor() {
        return true;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (editor.isVisible()){
            setValue(editor.spLongitud.getValue());
        }else{
            setValue(Integer.parseInt(text));
        } 
    }

    @Override
    public String getAsText() {
        return super.getAsText(); 
    }

    @Override
    public String getJavaInitializationString() {
        return getValue().toString();
    }
}
