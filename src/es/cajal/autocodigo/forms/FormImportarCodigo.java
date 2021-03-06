/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cajal.autocodigo.forms;

import es.cajal.autocodigo.Contenedor;
import es.cajal.autocodigo.Dialogos;
import es.cajal.autocodigo.generacion.CCampo;
import es.cajal.autocodigo.importacion.ParseCodigo;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author pmpreciado
 */
public class FormImportarCodigo extends javax.swing.JFrame {

    
    private static final int TAB_IMPORTAR_EXCEL         = 0;
    private static final int TAB_IMPORTAR_DEFINICION    = 1;
    private static final int TAB_IMPORTAR_PARAM         = 2;
    private static final int TAB_IMPORTAR_LISTA         = 3;
    private static final int TAB_IMPORTAR_SCRIPT_BD     = 4;
    
    
    /** Referencia al objeto contenedor */
    Contenedor cnt;
    
    
    
    /**
     * Creates new form FormImportarCodigo
     * 
     * @param cnt                               Referencia al objeto contenedor
     */
    public FormImportarCodigo(Contenedor cnt) {
        this.cnt = cnt;
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

        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        tabbedPanelImportar = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        taCodigoEjemploDefinicion1 = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        taCodigoExcel = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        taCodigoEjemploDefinicion = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        taCodigoDefinicion = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        taCodigoEjemploParam = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        taCodigoParam = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        taCodigoEjemploLista = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        taCodigoLista = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        taCodigoEjemploDefinicion2 = new javax.swing.JTextArea();
        jScrollPane10 = new javax.swing.JScrollPane();
        taCodigoScriptBd = new javax.swing.JTextArea();
        btnLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Importar código");

        btnCancelar.setMnemonic('C');
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAceptar.setMnemonic('A');
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        tabbedPanelImportar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("<html>Pegue aquí los campos que definen los atributos de la clase, tras copiarlos desde una tabla de de Excel o similar.<br>\nEl formato de Excel es el de filas separados por saltos de línea y campos separados por tabulaciones.<br>\n<br>\nLas filas pegadas deben tener estos campos:</html>");

        taCodigoEjemploDefinicion1.setEditable(false);
        taCodigoEjemploDefinicion1.setBackground(new java.awt.Color(226, 226, 226));
        taCodigoEjemploDefinicion1.setColumns(20);
        taCodigoEjemploDefinicion1.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 10)); // NOI18N
        taCodigoEjemploDefinicion1.setRows(5);
        taCodigoEjemploDefinicion1.setText("Nombre del campo    Tipo de datos en la BD    Descripción del campo (opcional)");
        jScrollPane7.setViewportView(taCodigoEjemploDefinicion1);

        taCodigoExcel.setColumns(20);
        taCodigoExcel.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 10)); // NOI18N
        taCodigoExcel.setRows(5);
        jScrollPane8.setViewportView(taCodigoExcel);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane8)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 327, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPanelImportar.addTab("Importar de Tabla Excel", jPanel5);

        jLabel1.setText("Pegue aquí el código para definir los atributos de la clase, siguiendo este ejemplo:");

        taCodigoEjemploDefinicion.setEditable(false);
        taCodigoEjemploDefinicion.setBackground(new java.awt.Color(226, 226, 226));
        taCodigoEjemploDefinicion.setColumns(20);
        taCodigoEjemploDefinicion.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 10)); // NOI18N
        taCodigoEjemploDefinicion.setRows(5);
        taCodigoEjemploDefinicion.setText("    /** Nombre para el campo. Debe ser único por cada tipo_dato de registro, y es case-insensitive */\n    private String nombre_campo;\n\n    /** Tipo de dato (CCampo.TD_xxx) */\n    private int tipo_dato;\n\n    /** Longitud */\n    private int longitud;\n\n    /** Número de decimales (solo para datos numéricos decimales) */\n    private int num_decimales;");
        jScrollPane3.setViewportView(taCodigoEjemploDefinicion);

        taCodigoDefinicion.setColumns(20);
        taCodigoDefinicion.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 10)); // NOI18N
        taCodigoDefinicion.setRows(5);
        jScrollPane1.setViewportView(taCodigoDefinicion);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 473, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPanelImportar.addTab("Importar de definición", jPanel1);

        jLabel2.setText("Pegue aquí el código para definir los atributos de la clase, siguiendo este ejemplo:");

        taCodigoEjemploParam.setEditable(false);
        taCodigoEjemploParam.setBackground(new java.awt.Color(226, 226, 226));
        taCodigoEjemploParam.setColumns(20);
        taCodigoEjemploParam.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 10)); // NOI18N
        taCodigoEjemploParam.setRows(5);
        taCodigoEjemploParam.setText("     * @param empresa                       Siempre 5000\n     * @param institucion                   Código de institución de la entidad\n     * @param sem_conciliado                Semáforo que indica que la oepración está conciliada\n     * @param fecha_conciliacion            Fecha de conciliación\n     * @param sem_rentabilidad              Indica si se ha aplicado el cálculo de la rentabilidad a la cuenta de valores\n     * @param fecha_rentabilidad            Fecha en la que se ha aplicado el cálculo de la rentabilidad\n     * @param fecha_transmision             Fecha de transmisión del fichero\n     * @param tipo_fichero                  Tipo de fichero");
        jScrollPane4.setViewportView(taCodigoEjemploParam);

        taCodigoParam.setColumns(20);
        taCodigoParam.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 10)); // NOI18N
        taCodigoParam.setRows(5);
        jScrollPane2.setViewportView(taCodigoParam);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 473, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPanelImportar.addTab("Importar de @param", jPanel3);

        jLabel3.setText("Pegue aquí el código para definir los atributos de la clase, siguiendo este ejemplo:");

        taCodigoEjemploLista.setEditable(false);
        taCodigoEjemploLista.setBackground(new java.awt.Color(226, 226, 226));
        taCodigoEjemploLista.setColumns(20);
        taCodigoEjemploLista.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 10)); // NOI18N
        taCodigoEjemploLista.setRows(5);
        taCodigoEjemploLista.setText(" *     <li>  0 entidad                  Entidad\n *     <li>  1 cta_asociada             Cuenta a la vista asociada a la cuenta de valores\n *     <li>  2 sem_conciliado           Semáforo que indica que la oepración está conciliada\n *     <li>  3 fecha_conciliacion       Fecha de conciliación\n *     <li>  4 sem_rentabilidad         Indica si se ha aplicado el cálculo de la rentabilidad a la cuenta de valores afectada por la operación\n *     <li>  5 fecha_rentabilidad       Fecha en la que se ha aplicado el cálculo de la rentabilidad\n *     <li>  6 empresa                  Siempre 5000\n *     <li>  7 institucion              Código de institución de la entidad\n *     <li>  8 fecha_transmision        Fecha de transmisión del fichero");
        jScrollPane5.setViewportView(taCodigoEjemploLista);

        taCodigoLista.setColumns(20);
        taCodigoLista.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 10)); // NOI18N
        taCodigoLista.setRows(5);
        jScrollPane6.setViewportView(taCodigoLista);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 473, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabbedPanelImportar.addTab("Importar de lista JavaDoc", jPanel4);

        jLabel5.setText("<html>Pegue aquí el script de base de datos para la creación de la tabla, siguiendo este ejemplo</html>");

        taCodigoEjemploDefinicion2.setEditable(false);
        taCodigoEjemploDefinicion2.setBackground(new java.awt.Color(226, 226, 226));
        taCodigoEjemploDefinicion2.setColumns(20);
        taCodigoEjemploDefinicion2.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 10)); // NOI18N
        taCodigoEjemploDefinicion2.setRows(5);
        taCodigoEjemploDefinicion2.setText("CREATE TABLE test_table\n(\n    id_reg                 INT,                     -- Identificador del registro\n    importe                DECIMAL(10,0),           -- Importe\n    nombre                 VARCHAR(12),             -- Nombre del registro\n    fecha                  INT,                     -- Fecha\n    \n    PRIMARY KEY (id_reg)\n);");
        jScrollPane9.setViewportView(taCodigoEjemploDefinicion2);

        taCodigoScriptBd.setColumns(20);
        taCodigoScriptBd.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 10)); // NOI18N
        taCodigoScriptBd.setRows(5);
        jScrollPane10.setViewportView(taCodigoScriptBd);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane10)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 437, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabbedPanelImportar.addTab("Importar de script BD", jPanel6);

        btnLimpiar.setMnemonic('L');
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setToolTipText("");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedPanelImportar)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPanelImportar, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        cancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        aceptar();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

//    /**
//     * @param args the command line arguments
//     */
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
//            java.util.logging.Logger.getLogger(FormImportarCodigo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FormImportarCodigo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FormImportarCodigo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FormImportarCodigo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FormImportarCodigo().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextArea taCodigoDefinicion;
    private javax.swing.JTextArea taCodigoEjemploDefinicion;
    private javax.swing.JTextArea taCodigoEjemploDefinicion1;
    private javax.swing.JTextArea taCodigoEjemploDefinicion2;
    private javax.swing.JTextArea taCodigoEjemploLista;
    private javax.swing.JTextArea taCodigoEjemploParam;
    private javax.swing.JTextArea taCodigoExcel;
    private javax.swing.JTextArea taCodigoLista;
    private javax.swing.JTextArea taCodigoParam;
    private javax.swing.JTextArea taCodigoScriptBd;
    private javax.swing.JTabbedPane tabbedPanelImportar;
    // End of variables declaration//GEN-END:variables


    /**
     * Muestra el formulario.
     */
    void mostrar() {
        this.setVisible(true);
    }

    
    /**
     * Sale del formulario sin guardar los cambios.
     */
    private void cancelar() {
        this.setVisible(false);
    }

    
    /**
     * Sale del formulario guardando los cambios.
     */    
    private void aceptar() {
        
        int tab = tabbedPanelImportar.getSelectedIndex();
        
        String codigo = "";
        int naturaleza_especificada = -1;
        int naturaleza_detectada = -1;
        boolean reconocido = false;
        
        switch (tab) {
            
            case TAB_IMPORTAR_EXCEL:
                codigo = taCodigoExcel.getText().trim();
                naturaleza_especificada = ParseCodigo.NAT_EXCEL;
                naturaleza_detectada = ParseCodigo.identificarNaturalezaTexto(codigo);
                break;
                
            case TAB_IMPORTAR_DEFINICION:
                codigo = taCodigoDefinicion.getText().trim();
                naturaleza_especificada = ParseCodigo.NAT_DEFINICION;
                naturaleza_detectada = ParseCodigo.identificarNaturalezaTexto(codigo);
                break;
                
            case TAB_IMPORTAR_PARAM:
                codigo = taCodigoParam.getText().trim();
                naturaleza_especificada = ParseCodigo.NAT_PARAM;
                naturaleza_detectada = ParseCodigo.identificarNaturalezaTexto(codigo);
                break;

            case TAB_IMPORTAR_LISTA:
                codigo = taCodigoLista.getText().trim();
                naturaleza_especificada = ParseCodigo.NAT_LISTA;
                naturaleza_detectada = ParseCodigo.identificarNaturalezaTexto(codigo);
                break;
                
            case TAB_IMPORTAR_SCRIPT_BD:
                codigo = taCodigoScriptBd.getText().trim();
                naturaleza_especificada = ParseCodigo.NAT_SCRIPT_BD;
                naturaleza_detectada = ParseCodigo.identificarNaturalezaTexto(codigo);
                break;
                
        }
        
        if (codigo.length() == 0) {
            String mensaje = "No se ha introducido ningún código";
            mostrarError(mensaje);
            return;
        }
        
        /*
        reconocido = naturaleza_detectada == naturaleza_especificada;
        if (!reconocido) {
            String mensaje = "No se puede indentificar el código introducido";
            mostrarError(mensaje);
            return;
        }
        */
        
        // Cargamos la tabla de campos con la información extraída del código
        List <CCampo> l_campos = ParseCodigo.codigo2Campos(codigo, naturaleza_especificada);
        
        if (l_campos.isEmpty()) {
            String mensaje = "No se ha identificado ningún campo";
            mostrarError(mensaje);
            return;
        }
        
        cnt.formAutoCodigo.tabla_campos.campos2TablaClear(l_campos);
        
        
        // Ocultamos este formulario
        this.setVisible(false);
    }

    
    /**
     * Borra el texto de los cuadros de entrada.
     */
    private void limpiar() {
        this.taCodigoExcel.setText("");
        this.taCodigoDefinicion.setText("");
        this.taCodigoParam.setText("");
        this.taCodigoLista.setText("");
        this.taCodigoScriptBd.setText("");
    }
    
    
    
    /**
     * Muestra el mensaje de error dado.
     * 
     * @param mensaje_error                     Mensaje de error a mostrar antes de cerrar
     */
    private void mostrarError(String mensaje_error) {
        Dialogos.showMessageDialog(cnt, this, mensaje_error, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    
    /**
     * Oculta la ventana actual, mostrando previamente el mensaje de error dado.
     * 
     * @param mensaje_error                     Mensaje de error a mostrar antes de cerrar
     */
    private void mostrarErrorYOcultarVentana(String mensaje_error) {
        mostrarError(mensaje_error);
        this.setVisible(false);
    }
    
}
