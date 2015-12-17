/**
 * ModeloTablaCampos.java
 *
 * Creado el 20-may-2015, 13:12:34
 */

package es.cajal.autocodigo.tablas;

import es.cajal.autocodigo.Comun;
import es.cajal.autocodigo.generacion.CCampo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author pmpreciado
 */
public class ModeloTablaCampos extends ModeloTablaBase {

    /** Índices de las columnas */
    private static int c = 0;
    public static int IND_COL_N                 = c++;
    public static int IND_COL_NOMBRE_CAMPO      = c++;
    public static int IND_COL_TIPO_DATO_BD      = c++;
    public static int IND_COL_DESCRIPCION       = c++;
    public static int IND_COL_TIPO_DATO_JAVA    = c++;

    /** Columnas de la tabla */
    private static final String [] NOMBRES_COLUMNAS = {
        "N",
        "Nombre campo", 
        "Tipo de dato BD",
        "Descripción", 
        "Tipo de dato Java", 
    };
    
    
    
    /** Lista con los campos que se van a mostrar en la tabla */
    List <CCampo> l_campos;
    

    
    
    /** 
     * Crea la instancia de la clase.
     */
    public ModeloTablaCampos() {
        super();
        l_campos = new ArrayList();
    }

    
    /**
     * Obtiene los nombres de las columnas de la tabla.
     * 
     * @return                                  Nombres de las columnas de la tabla
     */
    @Override
    public String [] getNombresColumnas () {
        return NOMBRES_COLUMNAS;
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
        
        if (c == IND_COL_N) {
            return Integer.class;
        } else if (c == IND_COL_TIPO_DATO_JAVA) {
            return JComboBox.class;
        }
        
        return super.getColumnClass(c);
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
        if (c == IND_COL_N) return false;
        return true;
    }


    /**
     * Dada una fila de la tabla, comprueba si contiene datos.
     * 
     * @param num_fila                          Número de la fila (empezando en 0)
     *
     * @return                                  'true' si tiene datos
     *                                          'false' si no tiene
     */
    public boolean filaTieneDatos(int num_fila) {
        String celda_1 = (String) getValueAt(num_fila, IND_COL_NOMBRE_CAMPO);
        String celda_2 = (String) getValueAt(num_fila, IND_COL_TIPO_DATO_BD);
        String celda_3 = (String) getValueAt(num_fila, IND_COL_DESCRIPCION);
        String celda_4 = (String) getValueAt(num_fila, IND_COL_TIPO_DATO_JAVA);
        
        if (Comun.vacio(celda_1) && Comun.vacio(celda_2) && Comun.vacio(celda_3) && Comun.vacio(celda_4)) {
            return false;
        }
        
        return true;
    }
    
    
    /**
     * Obtiene el índice de la primera fila vacía tras las filas de datos.
     * 
     * @return                                  Índice de la primera fila vacía tras las filas de datos
     *                                          -1, si no hay ninguna fila vacía tras las filas de datos
     */
    public int getIndicePrimeraFilaVaciaTrasDatos() {
        
        int num_filas = getRowCount();
        
        boolean encontrada_fila_con_datos = false;
        
        for (int i = 0; i < num_filas; i++) {
            boolean tiene_datos = filaTieneDatos(i);
            if (tiene_datos) {
                encontrada_fila_con_datos = true;
            } else {
                if (encontrada_fila_con_datos) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    
    
    /**
     * Establece el número de filas que debe tener la tabla.
     * 
     * @param num_filas                         Nuevo número de filas
     */
    public void setNumeroFilas(int num_filas) {
        super.setRowCount(num_filas);
        renumerarFilas();
    }
    

    /**
     * Incrementa el número de filas de la tabla.
     * 
     * @param inc_num_filas                     Incremento en el número de filas
     */
    void incNumFilas(int inc_num_filas) {
        int num_filas_actual = getRowCount();
        super.setRowCount(num_filas_actual + inc_num_filas);
        renumerarFilas();
    }
    
    /**
     * Añade una nueva fila al final de la tabla.
     *
     * @param nueva_fila                        Contenido de las celdas de la nueva fila
     */
    @Override
    public void addRow(Object [] nueva_fila)
    {
        super.addRow(nueva_fila);
        renumerarFilas();
    }

    
    /**
     * Añade una fila vacía al final de la tabla.
     */
    @Override
    public void addEmptyRow()
    {
        super.addEmptyRow();
        renumerarFilas();
    }
    
    
    /**
     * Inserta una nueva fila en la tabla, desplazando las siguientes.
     *
     * @param indice                            Índice que ocupara la fila (empezando en 0)
     * @param nueva_fila                        Contenido de las celdas de la nueva fila
     */
    @Override
    public void addRow(int indice, Object [] nueva_fila)
    {
        super.addRow(indice, nueva_fila);
        renumerarFilas();
    }
    
    
    /**
     * Inserta una nueva fila vacía en la tabla, desplazando las demás.
     * 
     * @param indice                            Índice que ocupara la fila (empezando en 0)
     */
    @Override
    public void addEmptyRowAt(int indice)
    {
        super.addEmptyRowAt(indice);
        renumerarFilas();
    }

    
    /**
     * Renumera las filas de la tabla.
     * La numeración está en la primera columna.
     */
    public void renumerarFilas() {
        for (int i = 0; i < this.getRowCount(); i++) {
            int num_fila_actual = -1;
            Object obj_num_fila_actual = this.getValueAt(i, IND_COL_N);
            if (obj_num_fila_actual != null) {
                num_fila_actual = (Integer) obj_num_fila_actual;
            }
            
            if (num_fila_actual != i) {
                this.setValueAt(i, i, IND_COL_N);
            }
        }
    }
    

    
    /**
     * Actualiza el campo "tipo de dato Java" de una fila de la tabla dada por su índice.
     * El tipo de dato Java se intenta averiguar a partir del nombre del campo y del tipo de dato de la base de datos.
     * 
     * @param indice_fila                       Índice de la fila de la tabla donde se encuentra el campo cuyo tipo de dato Java se va a actualizar
     */
    void actualizarTipoDatoJava(int indice_fila) {
        String nombre_campo = (String) getValueAt(indice_fila, IND_COL_NOMBRE_CAMPO);
        String tipo_dato_bd = (String) getValueAt(indice_fila, IND_COL_TIPO_DATO_BD);
        String tipo_dato_java = CCampo.getTipoJava(nombre_campo, tipo_dato_bd);
        setValueAt(tipo_dato_java, indice_fila, IND_COL_TIPO_DATO_JAVA);
    }
    


    
    
    /**
     * Introduce en la tabla de campos el campo dado.
     * 
     * @param indice_fila                       Índice de la fila de la tabla donde se va a introducir el campo dado
     * @param campo                             Campo a pasar a la tabla
     */
    public void setCampoAt(int indice_fila, CCampo campo) {
        
        if (getRowCount() <= indice_fila) {
            this.incNumFilas(1);
        }
        
        setValueAt(indice_fila,       indice_fila, IND_COL_N);
        setValueAt(campo.nombre,      indice_fila, IND_COL_NOMBRE_CAMPO);
        setValueAt(campo.tipo_bd,     indice_fila, IND_COL_TIPO_DATO_BD);
        setValueAt(campo.descripcion, indice_fila, IND_COL_DESCRIPCION);
        setValueAt(campo.tipo_java,   indice_fila, IND_COL_TIPO_DATO_JAVA);        
    }
    
    
    /**
     * Introduce al final de la fila los datos del campo dado.
     * 
     * @param campo                             Campo a pasar a la tabla
     */
    public void setCampo(CCampo campo) {
        int indice_fila = getIndicePrimeraFilaVaciaTrasDatos();
        if (indice_fila == -1) {
            incNumFilas(1);
            indice_fila = getRowCount() - 1;
        }
        
        setCampoAt(indice_fila, campo);
    }
    
    
    /**
     * Introduce los campos dados en la tabla a partir de la fila indicada.
     * Se sobrescriben tantas filas como campos se inserten.
     * El resto de filas no se ve afectado.
     * 
     * @param indice_fila                       Índice de la fila a partir de la que se van a introducir los campos
     * @param l_campos                          Campos a introducir
     */
    public void campos2TablaAt(int indice_fila, List <CCampo> l_campos) {
        
        // Si no hay filas suficientes en la tabla, añadimos más para dar cabida a los campos
        int nuevo_numero_filas = indice_fila + l_campos.size();
        
        if (getRowCount() < nuevo_numero_filas) {
            this.setNumeroFilas(nuevo_numero_filas + 10);
        }
        
        for (int i = 0; i < Comun.size(l_campos); i++) {
            CCampo campo = l_campos.get(i);
            setCampoAt(indice_fila++, campo);
        }
    }
    
    
    /**
     * Introduce los campos dados en la tabla.
     * La tabla es vaciada antes de la operación.
     * 
     * @param l_campos                          Campos a introducir
     */
    public void campos2TablaClear(List <CCampo> l_campos) {
        clear();
        this.setNumeroFilas(Comun.size(l_campos) + 10);
        
        for (int i = 0; i < Comun.size(l_campos); i++) {
            CCampo campo = l_campos.get(i);
            setCampoAt(i, campo);
        }
    }

    
    /**
     * Inserta en la tabla N filas vacías a partir de la fila dada
     * 
     * @param indice_fila                       Número de fila a partir de la que se hará la inserción
     * @param num_filas                         Número de filas a insertar
     */
    public void insertarFilasAt(int indice_fila, int num_filas) {
        int num_filas_actual = this.getRowCount();
        
        if (indice_fila >= num_filas_actual) {
            this.incNumFilas(num_filas);
        } else {
            for (int i = 0; i < num_filas; i++) {
                this.addEmptyRowAt(indice_fila);
            }
        }
    }
}
