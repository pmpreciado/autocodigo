/**
 * TablaCampos.java
 *
 * Creado el 18-may-2015, 14:58:47
 */

package es.cajal.autocodigo.tablas;

import es.cajal.autocodigo.Comun;
import es.cajal.autocodigo.generacion.CCampo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 * Funciones para gestionar la tabla de campos de la base de datos.
 * 
 * @author pmpreciado
 */
public class TablaCampos {

//    /** Columnas de la tabla */
//    public static final int COLUMNA_N               = 0;
//    public static final int COLUMNA_NOMBRE_CAMPO    = 1;
//    public static final int COLUMNA_TIPO_DATO_BD    = 2;
//    public static final int COLUMNA_DESCRIPCION     = 3;
//    public static final int COLUMNA_TIPO_DATO_JAVA  = 4;


    /** Número de filas preferido */
    public static final int NUM_FILAS_PREFERIDO     = 20;
    
    
    public JTable jTableCampos;

    
    
    public static class TablaCamposCellRenderer extends javax.swing.table.DefaultTableCellRenderer {
        
        /**
         * Table Cell Renderer para sombrear la primera columna de la tabla de campos
         * 
         * @param table
         * @param value
         * @param isSelected
         * @param hasFocus
         * @param row
         * @param column
         * 
         * @return 
         */
        @Override
        public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, java.lang.Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            java.awt.Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            cellComponent.setBackground(java.awt.Color.LIGHT_GRAY);
            return cellComponent;
        }
    }

    
    /**
     * Constructor de la clase.
     * 
     * @param tabla_campos                      Elemento JTable con la tabla de campos
     */
    public TablaCampos(JTable tabla_campos) {
        this.jTableCampos = tabla_campos;
    }
    
    
    /**
     * Inicializa la tabla de campos.
     */
    public void init() {
        
        // Adaptamos la tabla para que permita copy-paste
        new ExcelAdapter(this);

        ModeloTablaCampos mtc = getModel();
        mtc.setNumeroFilas(NUM_FILAS_PREFERIDO);
        
        // Hacemos que la primera columna sea de color gris
        TablaCamposCellRenderer cr = new TablaCamposCellRenderer();
        jTableCampos.getColumnModel().getColumn(ModeloTablaCampos.IND_COL_N).setCellRenderer(cr);
        
        JComboBox cb = this.generarComboBoxTipoDatoJava(null);
        jTableCampos.getColumnModel().getColumn(ModeloTablaCampos.IND_COL_TIPO_DATO_JAVA).setCellEditor(new DefaultCellEditor(cb));
    }
    
    
    /**
     * Obtiene el modelo de la tabla.
     * 
     * @return                                  Modelo de la tabla
     */
    public ModeloTablaCampos getModel() {
        ModeloTablaCampos mtc = (ModeloTablaCampos) jTableCampos.getModel(); 
        return mtc;
    }
    
    
    /**
     * Genera un ComboBox que contiene los tipos de datos de Java que pueden ser seleccionados.
     * 
     * @param td_seleccionado                   Tipo de dato preseleccionado
     * 
     * @return                                  ComboBox generado
     */
    private JComboBox generarComboBoxTipoDatoJava(String td_seleccionado) {
        JComboBox combo_td_java = new JComboBox();
        
        combo_td_java.setModel(new javax.swing.DefaultComboBoxModel(CCampo.ARR_TDJ));
        
        if (!Comun.vacio(td_seleccionado)) {
            combo_td_java.setSelectedItem(td_seleccionado);
        }
        
        return combo_td_java;
    }
    
    

//    
//    /**
//     * Dada una fila de la tabla, comprueba si contiene datos.
//     * 
//     * @param num_fila                          Número de la fila (empezando en 0)
//     *
//     * @return                                  'true' si tiene datos
//     *                                          'false' si no tiene
//     */
//    public boolean filaTieneDatos(int num_fila) {
//        String celda_1 = (String) jTableCampos.getValueAt(num_fila, COLUMNA_NOMBRE_CAMPO);
//        String celda_2 = (String) jTableCampos.getValueAt(num_fila, COLUMNA_TIPO_DATO_BD);
//        String celda_3 = (String) jTableCampos.getValueAt(num_fila, COLUMNA_DESCRIPCION);
//        String celda_4 = (String) jTableCampos.getValueAt(num_fila, COLUMNA_TIPO_DATO_JAVA);
//        
//        if (Comun.vacio(celda_1) && Comun.vacio(celda_2) && Comun.vacio(celda_3) && Comun.vacio(celda_4)) {
//            return false;
//        }
//        
//        return true;
//    }
//    
//    
//    /**
//     * Obtiene el índice de la primera fila vacía tras las filas de datos.
//     * 
//     * @return                                  Índice de la primera fila vacía tras las filas de datos
//     *                                          -1, si no hay ninguna fila vacía tras las filas de datos
//     */
//    public int getIndicePrimeraFilaVaciaTrasDatos() {
//        
//        int num_filas = jTableCampos.getRowCount();
//        
//        boolean encontrada_fila_con_datos = false;
//        //boolean tiene_datos;
//        for (int i = 0; i < num_filas; i++) {
//            boolean tiene_datos = filaTieneDatos(i);
//            if (tiene_datos) {
//                encontrada_fila_con_datos = true;
//            } else {
//                if (encontrada_fila_con_datos) {
//                    return i;
//                }
//            }
//        }
//        return -1;
//    }
//
//    
//    /**
//     * Limpia el contenido de la tabla de campos.
//     */
//    public void clear() {
//        setNumeroFilas(0);
//        setNumeroFilas(NUM_FILAS_PREFERIDO);
//    }
//    
//    
//    /**
//     * Establece el número de filas que debe tener la tabla.
//     * 
//     * @param num_filas                         Nuevo número de filas
//     */
//    public void setNumeroFilas(int num_filas) {
//        ModeloTablaCampos dtm = (ModeloTablaCampos) jTableCampos.getModel();
//        dtm.setRowCount(num_filas);
//    }
//    
//
//    /**
//     * Incrementa el número de filas que debe tener la tabla.
//     * 
//     * @param inc_num_filas                     Incremento en el número de filas
//     */
//    void incNumFilas(int inc_num_filas) {
//        DefaultTableModel dtm = (DefaultTableModel) jTableCampos.getModel();
//        int num_filas_actual = dtm.getRowCount();
//        dtm.setRowCount(num_filas_actual + inc_num_filas);
//    }
//    
//
//    
//    /**
//     * Actualiza el campo "tipo de dato Java" de una fila de la tabla dada por su índice.
//     * El tipo de dato Java se intenta averiguar a partir del nombre del campo y del tipo de dato de la base de datos.
//     * 
//     * @param indice_fila                       Índice de la fila de la tabla donde se encuentra el campo cuyo tipo de dato Java se va a actualizar
//     */
//    void actualizarTipoDatoJava(int indice_fila) {
//        String nombre_campo = (String) jTableCampos.getValueAt(indice_fila, COLUMNA_NOMBRE_CAMPO);
//        String tipo_dato_bd = (String) jTableCampos.getValueAt(indice_fila, COLUMNA_TIPO_DATO_BD);
//        String tipo_dato_java = CCampo.getTipoJava(nombre_campo, tipo_dato_bd);
//        jTableCampos.setValueAt(tipo_dato_java, indice_fila, TablaCampos.COLUMNA_TIPO_DATO_JAVA);
//    }
//    
//    

//    
//    
//    /**
//     * Introduce en la tabla de campos el campo dado.
//     * 
//     * @param indice_fila                       Índice de la fila de la tabla donde se va a introducir el campo dado
//     * @param campo                             Campo a pasar a la tabla
//     */
//    public void setCampoAt(int indice_fila, CCampo campo) {
//        
//        if (this.jTableCampos.getRowCount() <= indice_fila) {
//            this.incNumFilas(1);
//        }
//        
//        this.jTableCampos.setValueAt(indice_fila,       indice_fila, COLUMNA_N);
//        this.jTableCampos.setValueAt(campo.nombre,      indice_fila, COLUMNA_NOMBRE_CAMPO);
//        this.jTableCampos.setValueAt(campo.tipo_bd,     indice_fila, COLUMNA_TIPO_DATO_BD);
//        this.jTableCampos.setValueAt(campo.descripcion, indice_fila, COLUMNA_DESCRIPCION);
//        this.jTableCampos.setValueAt(campo.tipo_java,   indice_fila, COLUMNA_TIPO_DATO_JAVA);
//    }
//    
//    
//    
//    /**
//     * Introduce al final de la fila los datos del campo dado.
//     * 
//     * @param campo                             Campo a pasar a la tabla
//     */
//    public void setCampo(CCampo campo) {
//        int indice_fila = getIndicePrimeraFilaVaciaTrasDatos();
//        if (indice_fila == -1) {
//            incNumFilas(1);
//            indice_fila = this.jTableCampos.getRowCount() - 1;
//        }
//        
//        setCampoAt(indice_fila, campo);
//    }
//    
    
    
    
    
    /**
     * Borra el contenido de la tabla.
     */
    public void clear() {
        ModeloTablaCampos mtc = getModel();
        mtc.clear();
        mtc.setNumeroFilas(NUM_FILAS_PREFERIDO);
    }
    
    
    /**
     * Introduce los campos dados en la tabla a partir de la fila actualmente seleccionada. Si hubiera varias filas seleccionada, se toma la primera.
     * Se sobrescriben tantas filas como campos se inserten.
     * El resto de filas no se ve afectado.
     * 
     * @param l_campos                          Campos a introducir
     */
    public void campos2TablaPosActual(List <CCampo> l_campos) {
        
        // Si no hay filas suficientes en la tabla, añadimos más para dar cabida a los campos
        int indice_fila = this.jTableCampos.getSelectedRow();
        
        ModeloTablaCampos mtc = getModel();
        mtc.campos2TablaAt(indice_fila, l_campos);
    }
    
    
    /**
     * Introduce los campos dados en la tabla.
     * La tabla es vaciada antes de la operación.
     * 
     * @param l_campos                          Campos a introducir
     */
    public void campos2TablaClear(List <CCampo> l_campos) {
        ModeloTablaCampos mtc = getModel();
        mtc.campos2TablaClear(l_campos);
    }    
    

    /**
     * Genera una lista de campos a partir de la información de la tabla.
     * 
     * @return                                  Lista de campos
     */
    public List <CCampo> tabla2Campos() {
        List <CCampo> l_campos = new ArrayList();
        ModeloTablaCampos mtc = getModel();
        
        int num_filas = mtc.getRowCount();
        
        for (int i = 0; i < num_filas; i++) {
            String nombre_campo = (String) mtc.getValueAt(i, ModeloTablaCampos.IND_COL_NOMBRE_CAMPO);
            if (!Comun.vacio(nombre_campo)) {
                String tipo_dato_bd = (String) mtc.getValueAt(i, ModeloTablaCampos.IND_COL_TIPO_DATO_BD);
                String descripcion = (String) mtc.getValueAt(i, ModeloTablaCampos.IND_COL_DESCRIPCION);
                String tipo_dato_java = (String) mtc.getValueAt(i, ModeloTablaCampos.IND_COL_TIPO_DATO_JAVA);
                if (Comun.vacio(tipo_dato_java)) {
                    tipo_dato_java = CCampo.TDJ_STRING;
                }
                
                CCampo campo = new CCampo(nombre_campo, tipo_dato_bd, descripcion, tipo_dato_java);
                l_campos.add(campo);
            }
        }
        
        return l_campos;
    }
    
    
}
