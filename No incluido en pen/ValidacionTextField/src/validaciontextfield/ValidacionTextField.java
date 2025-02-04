/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package validaciontextfield;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author andres
 */
public class ValidacionTextField {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Validación de JTextField");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(4, 2, 10, 10));

        // ======= Validación con DocumentListener (Validación en tiempo real) =======
        JLabel label1 = new JLabel("Solo números (DocumentListener):");
        JTextField textField1 = new JTextField(15);
        JLabel messageLabel1 = new JLabel("");

        textField1.getDocument().addDocumentListener(new DocumentListener() {
            void validate() {
                String text = textField1.getText();
                if (text.matches("\\d*")) {
                    messageLabel1.setForeground(Color.GREEN);
                    messageLabel1.setText("Válido");
                } else {
                    messageLabel1.setForeground(Color.RED);
                    messageLabel1.setText("Solo números");
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                validate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validate();
            }
        });

        // ======= Validación con InputVerifier (No permite salir si es inválido) =======
        JLabel label2 = new JLabel("Solo números (InputVerifier):");
        JTextField textField2 = new JTextField(15);

        textField2.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                JTextField tf = (JTextField) input;
                return tf.getText().matches("\\d+"); // Solo números
            }

            @Override
            public boolean shouldYieldFocus(JComponent input) {
                boolean valid = verify(input);
                if (!valid) {
                    JOptionPane.showMessageDialog(input, "Solo números permitidos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                return valid;
            }
        });

        // ======= Validación con FocusListener (Mensaje al salir del campo) =======
        JLabel label3 = new JLabel("Solo números (FocusListener):");
        JTextField textField3 = new JTextField(15);
        JLabel messageLabel3 = new JLabel("");

        textField3.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                messageLabel3.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                String text = textField3.getText();
                if (!text.matches("\\d+")) {
                    messageLabel3.setForeground(Color.RED);
                    messageLabel3.setText("Solo números permitidos");
                }
            }
        });

        // ======= Validación con KeyListener (Bloqueo de teclas no permitidas) =======
        JLabel label4 = new JLabel("Solo números (KeyListener):");
        JTextField textField4 = new JTextField(15);

        textField4.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) { // Solo permite números
                    e.consume(); // Bloquea la tecla
                }
            }
        });

        // Agregar componentes al frame
        frame.add(label1);
        frame.add(textField1);
        frame.add(messageLabel1);
        frame.add(label2);
        frame.add(textField2);
        frame.add(new JLabel()); // Espacio vacío
        frame.add(label3);
        frame.add(textField3);
        frame.add(messageLabel3);
        frame.add(label4);
        frame.add(textField4);
        frame.add(new JLabel()); // Espacio vacío

        frame.setVisible(true);
    }
}
