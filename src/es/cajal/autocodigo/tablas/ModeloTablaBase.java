/**
 * ModeloTablaBase.java
 *
 * Creado el 20-may-2015, 13:13:08
 */

package es.cajal.autocodigo.tablas;

import es.cajal.autocodigo.Comun;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


/**
 * Modelo base del que extender los modelos de las tablas.
 *
 * @author Pedro María Preciado
 */
public abstract class ModeloTablaBase extends AbstractTableModel {


    private static final String [] NOMBRES_COLUMNAS = {
    };
    
    
    protected List <Object []> l_filas; ;
    
    

    
    /** 
     * Crea la instancia de la clase.
     */
    public ModeloTablaBase() {
        l_filas = new ArrayList();
    }

    
    /**
     * Obtiene los nombres de las columnas de la tabla.
     * 
     * @return                                  Nombres de las columnas de la tabla
     */
    public String [] getNombresColumnas() {
        return NOMBRES_COLUMNAS;
    }
    

    /**
     * Obtiene el número de filas de la tabla, sin contar la cabecera.
     *
     * @return                                  Número de filas de la tabla
     */
    @Override
    public int getRowCount() {
        int nf = Comun.size(l_filas);
        return nf;
    }


    /**
     * Establece el número de filas de la tabla.
     * Si el número de filas a establecer es inferior al actual, las filas que sobren serán descartadas.
     * Si el número de filas a establecer es superior al actual, se añadirán nuevas filas en blanco al final.
     * 
     * @param num_filas                         Número de filas
     */
    void setRowCount(int num_filas) {
        int nf_actual = getRowCount();
        if (nf_actual > num_filas) {
            l_filas = l_filas.subList(0, nf_actual);
        } else {
            int num_columnas = this.getColumnCount();
            int num_filas_añadir = num_filas - nf_actual;
            for (int i = 0; i < num_filas_añadir; i++) {
                
                Object [] nueva_fila = new Object[num_columnas];
                this.addRow(nueva_fila);
            }
        }
    }
    
    
    /**
     * Obtiene el número de columnas de la tabla
     *
     * @return                                  Número de columnas de la tabla
     */
    @Override
    public int getColumnCount() {
        String [] nombres_columnas = getNombresColumnas();
        int nc = nombres_columnas.length;
        return nc;
    }

    
    /**
     * Obtiene el nombre de una columna determinada de la tabla
     *
     * @param c                                 Número de columna (empezando en 0)
     * 
     * @return                                  Nombre de la columna
     */
    @Override
    public String getColumnName(int c) {
        String [] nombres_columnas = getNombresColumnas();
        return nombres_columnas[c];
    }
    
    
    /**
     * Obtiene el contenido de una celda de la tabla.
     *
     * @param f                                 Número de fila (empezando en 0)
     * @param c                                 Número de columna (empezando en 0)
     * 
     * @return                                  Contenido de la celda
     */
    @Override
    public Object getValueAt(int f, int c) {
        Object [] obj_fila = l_filas.get(f);
        Object valor = obj_fila[c];
        return valor;
    }
    
    
    /**
     * Determina si una celda es editable o no.
     *
     * @param f                                 Número de fila
     * @param c                                 Número de columna
     * 
     * @return                                  'true' si la celda es editable
     *                                          'false' si no lo es
     */
    @Override
    public boolean isCellEditable(int f, int c) {
        return true;
    }
    
    
    /**
     * Establece el valor de una celda de la tabla.
     *
     * @param f                                 Número de fila
     * @param c                                 Número de columna
     * @param valor                             Valor a establecer
     */
    @Override
    public void setValueAt(Object valor, int f, int c) {
        Object [] obj_fila = l_filas.get(f);
        
        obj_fila[c] = valor;
        l_filas.set(f, obj_fila);
        fireTableCellUpdated(f, c);
    }

    
    /**
     * Determina la clase predeterminada para las celdas de una columna dada.
     * Para que se pueda determinar, la tabla debe tener, al menos, una fila.
     * 
     * @param c                                 Número de columna
     * 
     * @return                                  Clase de los elementos de esa columna
     *                                          'null' si la tabla no tiene ninguna fila.
     */
    @Override
    public Class getColumnClass(int c) {
        int nf = getRowCount();
        if (nf == 0) {
            return String.class;
        }
        
        Object celda = getValueAt(0, c);
        if (celda == null) {
            return String.class;
        }
        
        Class clase = celda.getClass();
        return clase;
    }
    
    
    /**
     * Añade una nueva fila al final de la tabla.
     *
     * @param nueva_fila                        Contenido de las celdas de la nueva fila
     */
    public void addRow(Object [] nueva_fila)
    {
        l_filas.add(nueva_fila);
        fireTableRowsInserted(l_filas.size() - 1, l_filas.size() - 1);
    }

    
    /**
     * Añade una fila vacía al final de la tabla.
     */
    public void addEmptyRow()
    {
        int num_columnas = this.getColumnCount();
        Object [] nueva_fila = new Object[num_columnas];
        this.addRow(nueva_fila);
    }
    
    
    /**
     * Inserta una nueva fila en la tabla, desplazando las siguientes.
     *
     * @param indice                            Índice que ocupara la fila (empezando en 0)
     * @param nueva_fila                        Contenido de las celdas de la nueva fila
     */
    public void addRow(int indice, Object [] nueva_fila)
    {
        if (indice < 0) {
            indice = this.getRowCount();
        }
        
        l_filas.add(indice, nueva_fila);
        fireTableRowsInserted(indice, indice);
    }

    
    /**
     * Inserta una nueva fila vacía en la tabla, desplazando las demás.
     * 
     * @param indice                            Índice que ocupara la fila (empezando en 0)
     */
    public void addEmptyRowAt(int indice)
    {
        int num_columnas = this.getColumnCount();
        Object [] nueva_fila = new Object[num_columnas];
        this.addRow(indice, nueva_fila);
    }
    
    
    /**
     * Elimina la fila indicada.
     * 
     * @param indice                            ïndice de la fila a eliminar
     */
    public void deleteRow(int indice) {
        if (indice > this.getRowCount()) {
            return;
        }
        
        this.l_filas.remove(indice);
        this.fireTableRowsDeleted(indice, indice);
    }
    
    
    /**
     * Borra el contenido de la tabla.
     */
    public void clear()
    {
        l_filas.clear();
        fireTableDataChanged();
    }


}
