/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio02;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import javax.swing.JTextField;

/**
 *
 * @author Alumno
 */
public class TextoLongitud extends JTextField implements Serializable {

    private int longitud;
    private Color colorVacio;

    public TextoLongitud() {
        
        //Definimos la propiedad longitud
        this.longitud = 20;
        //Definimos la propiedad colorVacio
        this.colorVacio = Color.BLUE;
        
        
        this.gestionaEntrada();
        
    }
    
    

    /**
     * Get the value of colorVacio
     *
     * @return the value of colorVacio
     */
    public Color getColorVacio() {
        return colorVacio;
    }

    /**
     * Set the value of colorVacio
     *
     * @param colorVacio new value of colorVacio
     */
    public void setColorVacio(Color colorVacio) {
        this.colorVacio = colorVacio; 
        setBackground(colorVacio); 
    }

    /**
     * Get the value of longitud
     *
     * @return the value of longitud
     */
    public int getLongitud() {
        return longitud;
    }

    /**
     * Set the value of longitud
     *
     * @param longitud new value of longitud
     */
    public void setLongitud(int longitud) {
        this.longitud = longitud;
        //Se establece el valor dela propiedad Columns del 
        //componente al valor longitud.
        this.setColumns(longitud);
    }

     public void gestionaEntrada() {
        this.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyTyped(KeyEvent e) {
                if(getText().length() >= longitud ) {
                    e.consume();
                }
                
            }
            
             @Override
            public void keyReleased(KeyEvent e) {
                if(getText().length() == 0){
                    setBackground(colorVacio);
                }else{
                    setBackground(Color.WHITE);
                }
                
            }
            
            
        });
    }
}
