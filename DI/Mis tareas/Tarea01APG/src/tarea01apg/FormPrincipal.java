package tarea01apg;

import java.awt.Cursor;
import javax.swing.ImageIcon;

/**
 *
 * @author andres
 */
public class FormPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FormPrincipal
     */
    public FormPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTitulo = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        labelImagenFondo = new javax.swing.JLabel();
        botonAnadirBicicleta = new javax.swing.JButton();
        menuPrincipal = new javax.swing.JMenuBar();
        menuGestion = new javax.swing.JMenu();
        menuItemAnadirBicicleta = new javax.swing.JMenu();
        menuItemSalir = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BicisParaTodos S.L.");
        setBackground(new java.awt.Color(0, 153, 153));
        setIconImages(null);

        panelTitulo.setBackground(new java.awt.Color(153, 0, 153));
        panelTitulo.setForeground(new java.awt.Color(0, 153, 153));

        labelTitulo.setBackground(new java.awt.Color(0, 153, 153));
        labelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(0, 255, 204));
        labelTitulo.setText("Bienvenid@s a BicisParaTodos S.L.");

        labelImagenFondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelImagenFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/biciPequena.jpg"))); // NOI18N

        botonAnadirBicicleta.setBackground(new java.awt.Color(102, 255, 102));
        botonAnadirBicicleta.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        botonAnadirBicicleta.setForeground(new java.awt.Color(0, 0, 0));
        botonAnadirBicicleta.setText("Añadir Bicicleta");
        botonAnadirBicicleta.setToolTipText("pulsar para añadir una bicicleta");
        botonAnadirBicicleta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 3, true));
        botonAnadirBicicleta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonAnadirBicicleta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonAnadirBicicleta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonAnadirBicicletaMouseClicked(evt);
            }
        });
        botonAnadirBicicleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAnadirBicicletaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTituloLayout = new javax.swing.GroupLayout(panelTitulo);
        panelTitulo.setLayout(panelTituloLayout);
        panelTituloLayout.setHorizontalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTituloLayout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(labelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(175, 175, 175))
            .addGroup(panelTituloLayout.createSequentialGroup()
                .addComponent(labelImagenFondo)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelTituloLayout.createSequentialGroup()
                .addGap(206, 206, 206)
                .addComponent(botonAnadirBicicleta, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTituloLayout.setVerticalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelImagenFondo)
                .addGap(27, 27, 27)
                .addComponent(botonAnadirBicicleta, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );

        menuPrincipal.setForeground(new java.awt.Color(204, 204, 204));

        menuGestion.setBackground(new java.awt.Color(102, 102, 102));
        menuGestion.setForeground(new java.awt.Color(0, 0, 0));
        menuGestion.setText("Gestión de Stock");
        menuGestion.setToolTipText("elegir una opcion");

        menuItemAnadirBicicleta.setText("Añadir Bicicleta");
        menuItemAnadirBicicleta.setToolTipText("pulsar para añadir una bicicleta");
        menuItemAnadirBicicleta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuItemAnadirBicicletaMouseClicked(evt);
            }
        });
        menuGestion.add(menuItemAnadirBicicleta);

        menuItemSalir.setText("Salir");
        menuItemSalir.setToolTipText("pulsar para salir");
        menuItemSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuItemSalirMouseClicked(evt);
            }
        });
        menuGestion.add(menuItemSalir);

        menuPrincipal.add(menuGestion);

        setJMenuBar(menuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 640, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonAnadirBicicletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAnadirBicicletaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonAnadirBicicletaActionPerformed

    private void menuItemAnadirBicicletaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuItemAnadirBicicletaMouseClicked
        FormNuevaBicicleta formNuevaBici = new FormNuevaBicicleta(this, true);
        formNuevaBici.setVisible(true);
    }//GEN-LAST:event_menuItemAnadirBicicletaMouseClicked

    private void menuItemSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuItemSalirMouseClicked
        this.dispose();
    }//GEN-LAST:event_menuItemSalirMouseClicked

    private void botonAnadirBicicletaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonAnadirBicicletaMouseClicked
        FormNuevaBicicleta formNuevaBici = new FormNuevaBicicleta(this, true);
        // Cargar la imagen del icono desde los recursos
        ImageIcon icono = new ImageIcon(Main.class.getResource("/imagenes/icon.png"));
        // Establecer el icono en el JFrame
        formNuevaBici.setIconImage(icono.getImage());
        formNuevaBici.setVisible(true);
    }//GEN-LAST:event_botonAnadirBicicletaMouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FormPrincipal().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAnadirBicicleta;
    private javax.swing.JLabel labelImagenFondo;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JMenu menuGestion;
    private javax.swing.JMenu menuItemAnadirBicicleta;
    private javax.swing.JMenu menuItemSalir;
    private javax.swing.JMenuBar menuPrincipal;
    private javax.swing.JPanel panelTitulo;
    // End of variables declaration//GEN-END:variables
}
