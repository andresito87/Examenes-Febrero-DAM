/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_rgb;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author lau
 */
public class e_rgb extends JFrame {
    public JLabel etiqueta;
    public JButton botonR, botonG, botonB;
    public JPanel panel;
    public int contadorR, contadorG, contadorB;
    public enum Pulsado { PULSADOR, PULSADOG, PULSADOB, NADA };//Enumerado
    Pulsado pulsado; //variable para el enumerado

    public  e_rgb() {
        this.setSize(600, 400);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        iniciarComponentes();
    }
    
    private void iniciarComponentes(){
        // inicializar
        contadorR = 0;
        contadorG = 0;
        contadorB = 0;
        pulsado = Pulsado.NADA; //Al inicio el enumerado está incializado a nada pq no se ha pulsado nada.
        
        // configurar panel
        panel = new JPanel();
        panel.setBounds(0, 0, 600, 400);
        panel.setLayout(null);
        this.add(panel);
        
        // etiqueta
        etiqueta = new JLabel("Color (R, G, B)");
        etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
        etiqueta.setBounds(100, 50, 400, 50);
        panel.add(etiqueta);
        
        // botonR
        botonR = new JButton("R");
        botonR.setBounds(100, 200, 100, 100);
        panel.add(botonR);
        
        //Van a modificar el valor de pulsado.
        botonR.addActionListener(new ActionListener(){
           @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("botón r pulado");
               pulsado = Pulsado.PULSADOR;
            }
            
        });
        
        
        // botonG
        botonG = new JButton("G");
        botonG.setBounds(250, 200, 100, 100);
        panel.add(botonG);
        
        botonG.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                pulsado = Pulsado.PULSADOG;
            }
            
        });
        
        // botonB
        botonB = new JButton("B");
        botonB.setBounds(400, 200, 100, 100);
        panel.add(botonB);
        botonB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                pulsado = Pulsado.PULSADOB;
            }
            
        });
        
        // wheel
        this.addMouseWheelListener(new MouseWheelListener(){
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                mouseWheelMoveListener(e);
            }
            
          });
         }
           //Mirará qué botón está pulsado y decidirá un color u otro.
            private void mouseWheelMoveListener(MouseWheelEvent e) {
               if (pulsado != Pulsado.NADA) {
                if (pulsado == Pulsado.PULSADOR) {
               // aumentar o disminuir el valor de R, dependiendo de las vueltas que ha dado la rueda del ratón.
                  contadorR += e.getWheelRotation();
                if (contadorR < 0 || contadorR > 255) {
                    contadorR -= e.getWheelRotation();
                }
            } else if (pulsado == Pulsado.PULSADOG) {
                // aumentar o disminuir el valor de G
                contadorG += e.getWheelRotation();
                if (contadorG < 0 || contadorG > 255) {
                    contadorG -= e.getWheelRotation();
                }
            } else {
                // aumentar o disminuir el valor de B
                contadorB += e.getWheelRotation();
                if (contadorB < 0 || contadorB > 255) {
                    contadorB -= e.getWheelRotation();
                }
            
            }
        }
               
            
        etiqueta.setText("Color ("+contadorR+", "+contadorG+", "+contadorB+")");
        panel.setBackground(new Color(contadorR, contadorG, contadorB));
    }
    
        
    public static void main(String[] args) {
         e_rgb i2 = new  e_rgb();
        i2.setVisible(true);
    }

   

    
    
}
