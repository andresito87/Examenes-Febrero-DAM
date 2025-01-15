/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultoraIT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author xarxa
 */
public class Principal extends javax.swing.JFrame {

    private Connection conexion = null;

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        this.setLocationRelativeTo(null);
        Conectar();
        cargarDatosEnTabla("Todos");
        cargarDatosEnCombo();
        int nempleados = consultaContarEmpleados("Todos");
        jTextFieldNumeroEmpleados.setText(nempleados + "");
        double presupuesto = consultaSumaPresupuesto("Todos");
        jTextFieldPresupuesto.setText(presupuesto + "");

    }

    public void Conectar() {

        try {
            String url = "jdbc:mysql://localhost:3306/consultoraIT";
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexion = DriverManager.getConnection(url, "nuevo", "");

            JOptionPane.showMessageDialog(this, "Conexión establecida con la base de datos");
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public void Cerrar() {

        try {
            conexion.close();
            JOptionPane.showMessageDialog(this, "Conexión cerrada con la base de datos");
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al cerrar la conexión");
        }

    }

    public ArrayList<Empleado> consultaEmpleados(String nombreDept) {

        ArrayList<Empleado> empleados = new ArrayList<>();

        try {
            String sql = "SELECT * FROM empleados ";
            if (!nombreDept.equals("Todos")) {

                sql += "INNER JOIN departamentos ON empleados.dept_no = departamentos.dept_no WHERE departamentos.dnombre = '" + nombreDept + "'";

            }
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                Empleado empleado = new Empleado(rs.getInt("emp_no"),
                        rs.getString("apellido"),
                        rs.getString("oficio"),
                        rs.getInt("dir"),
                        rs.getDouble("salario"),
                        rs.getDouble("comision"),
                        rs.getInt("dept_no")
                );
                empleados.add(empleado);
            }

            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al extraer los empleados");
        }

        return empleados;

    }

    private void cargarDatosEnTabla(String nombreDept) {
        // Obtener la lista de coches llamando al método listadoCoches() de cocheBean
        ArrayList<Empleado> empleados = consultaEmpleados(nombreDept);

// Obtener el modelo de la tabla jTableCoches y convertirlo a DefaultTableModel
        DefaultTableModel model = (DefaultTableModel) jTableEmpleados.getModel();

// Limpiar la tabla antes de cargar nuevos datos estableciendo el número de filas en 0
        model.setRowCount(0);

// Iterar sobre cada coche en la lista de coches
        for (Empleado empleado : empleados) {
            // Crear un arreglo de objetos con los datos del coche actual
            Object[] rowData = {empleado.getEmp_no(), empleado.getApellido(), empleado.getOficio(), empleado.getDir(), empleado.getSalario(), empleado.getComision(), empleado.getDept_no()};

            // Agregar una nueva fila a la tabla con los datos del coche actual
            model.addRow(rowData);
        }
    }

    public ArrayList<Departamento> consultaDepartamentos() {

        ArrayList<Departamento> departamentos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM departamentos";
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                Departamento departamento = new Departamento(rs.getInt("dept_no"),
                        rs.getString("dnombre"),
                        rs.getString("loc")
                );
                departamentos.add(departamento);
            }

            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al extraer los departamentos");
        }

        return departamentos;

    }

    private void cargarDatosEnCombo() {
        // Obtener la lista de coches llamando al método listadoCoches() de cocheBean
        ArrayList<Departamento> departamentos = consultaDepartamentos();

        jComboBoxDepartamentos.addItem("Todos");

// Iterar sobre cada coche en la lista de coches
        for (Departamento departamento : departamentos) {

            jComboBoxDepartamentos.addItem(departamento.getDnombre());

        }
    }

    public int consultaContarEmpleados(String nombreDept) {

        int nempleados = 0;
        try {
            String sql = "SELECT COUNT(*) AS nempleados FROM empleados ";
            if (!nombreDept.equals("Todos")) {

                sql += "INNER JOIN departamentos ON empleados.dept_no = departamentos.dept_no WHERE departamentos.dnombre = '" + nombreDept + "'";

            }
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                nempleados = rs.getInt("nempleados");

            }

            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al extraer los empleados");
        }

        return nempleados;

    }

    public double consultaSumaPresupuesto(String nombreDept) {

        double presupuesto = 0.0;
        try {
            String sql = "SELECT SUM(salario+comision) AS presupuesto FROM empleados ";
            if (!nombreDept.equals("Todos")) {

                sql += "INNER JOIN departamentos ON empleados.dept_no = departamentos.dept_no WHERE departamentos.dnombre = '" + nombreDept + "'";

            }
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                presupuesto = rs.getDouble("presupuesto");

            }

            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al calcular el presupuesto");
        }

        return presupuesto;

    }

    public void consultaIncrementarSalario(String nombreDept) {

        if (nombreDept.equals("Todos")) {

            JOptionPane.showMessageDialog(this, "No se puede aplicar una subida generalizada");

        } else {
            try {
                String sql = "UPDATE empleados  ";
                {

                    sql += "INNER JOIN departamentos ON empleados.dept_no = departamentos.dept_no SET salario=salario*1.15 WHERE departamentos.dnombre = '" + nombreDept + "'";

                }
                Statement s = conexion.createStatement();
                s.execute(sql);

            } catch (SQLException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error al incrementar los salarios");
            }

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEmpleados = new javax.swing.JTable();
        jComboBoxDepartamentos = new javax.swing.JComboBox<>();
        jLabelDepartamentos = new javax.swing.JLabel();
        jButtonIncrementar = new javax.swing.JButton();
        jLabelNumeroEmpleados = new javax.swing.JLabel();
        jLabelPresupuesto = new javax.swing.JLabel();
        jTextFieldNumeroEmpleados = new javax.swing.JTextField();
        jTextFieldPresupuesto = new javax.swing.JTextField();
        jButtonSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "NºEmpleado", "Apellido", "Oficio", "dir", "Salario", "Comisión", "Departamento"
            }
        ));
        jScrollPane1.setViewportView(jTableEmpleados);

        jComboBoxDepartamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDepartamentosActionPerformed(evt);
            }
        });

        jLabelDepartamentos.setText("Departamentos");

        jButtonIncrementar.setText("Incrementar");
        jButtonIncrementar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIncrementarActionPerformed(evt);
            }
        });

        jLabelNumeroEmpleados.setText("NºEmpleados:");

        jLabelPresupuesto.setText("Presupuesto:");

        jTextFieldPresupuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPresupuestoActionPerformed(evt);
            }
        });

        jButtonSalir.setText("Salir");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelDepartamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonIncrementar)
                    .addComponent(jComboBoxDepartamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(104, 104, 104))
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNumeroEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPresupuesto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldNumeroEmpleados)
                    .addComponent(jTextFieldPresupuesto, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSalir)
                .addGap(116, 116, 116))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxDepartamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDepartamentos))
                .addGap(18, 18, 18)
                .addComponent(jButtonIncrementar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNumeroEmpleados)
                    .addComponent(jTextFieldNumeroEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPresupuesto)
                    .addComponent(jTextFieldPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSalir))
                .addGap(51, 51, 51))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldPresupuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPresupuestoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPresupuestoActionPerformed

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        Cerrar();
        dispose();
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jComboBoxDepartamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDepartamentosActionPerformed
        String nombreDept = jComboBoxDepartamentos.getSelectedItem().toString();
        cargarDatosEnTabla(nombreDept);

        int nempleados = consultaContarEmpleados(nombreDept);
        jTextFieldNumeroEmpleados.setText(nempleados + "");
        double presupuesto = consultaSumaPresupuesto(nombreDept);
        jTextFieldPresupuesto.setText(presupuesto + "");
    }//GEN-LAST:event_jComboBoxDepartamentosActionPerformed

    private void jButtonIncrementarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncrementarActionPerformed
       String nombreDept = jComboBoxDepartamentos.getSelectedItem().toString();
        consultaIncrementarSalario(nombreDept);
        cargarDatosEnTabla(nombreDept);
        double presupuesto = consultaSumaPresupuesto(nombreDept);
        jTextFieldPresupuesto.setText(presupuesto + "");
    }//GEN-LAST:event_jButtonIncrementarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonIncrementar;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JComboBox<String> jComboBoxDepartamentos;
    private javax.swing.JLabel jLabelDepartamentos;
    private javax.swing.JLabel jLabelNumeroEmpleados;
    private javax.swing.JLabel jLabelPresupuesto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEmpleados;
    private javax.swing.JTextField jTextFieldNumeroEmpleados;
    private javax.swing.JTextField jTextFieldPresupuesto;
    // End of variables declaration//GEN-END:variables
}
