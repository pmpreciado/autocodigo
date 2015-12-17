/**
 * ExcelAdapter.java
 *
 * Creado el 18-may-2015, 13:13:48
 */

package es.cajal.autocodigo.tablas;

import es.cajal.autocodigo.generacion.CCampo;
import es.cajal.autocodigo.importacion.ParseCodigo;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


import java.util.List;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import org.apache.logging.log4j.LogManager;


/**
 * ExcelAdapter enables Copy-Paste Clipboard functionality on JTables.
 * The clipboard data format used by the adapter is compatible with
 * the clipboard format used by Excel. This provides for clipboard
 * interoperability between enabled JTables and Excel.
 * 
 * --------
 * 
 * Adaptación de la clase "ExcelAdapter.java".
 * 
 * Esta clase hace que una tabla de tipo JTable tenga las funcionalidades de copiar y pegar.
 * Es muy sencillo de usar. Una vez definida la JTable, basta con instanciar esta clase:
 * 
 *      ExcelAdapter excel_adapter = new ExcelAdapter(tablaJTable);
 * 
 * La adaptción consiste en que la clase original estaba orientada a operar en una tabla genérica.
 * Estas son las modificaciones realizadas:
 *    - Se evita que se haga el pegado en celdas no editables.
 *    - Se evita pegar en la última columna, correspondiente con el tipo Java, y cuyo contenido se determinará automáticamente
 * 
 * Tomado de: http://www.javaworld.com/article/2077579/learn-java/java-tip-77--enable-copy-and-paste-functionality-between-swing-s-jtables-and-excel.html
 * 
 * @author pmpreciado
 */
public class ExcelAdapter implements ActionListener {
   
    private TablaCampos tabla_campos;
    private JTable jTableCampos;
    private Clipboard system;
    private StringSelection stsel;
    
   
   
    /**
     * The Excel Adapter is constructed with a
     * JTable on which it enables Copy-Paste and acts
     * as a Clipboard listener.
     * 
     * @param tabla_campos
     */
    public ExcelAdapter(TablaCampos tabla_campos)
    {
        this.tabla_campos = tabla_campos;
        
        jTableCampos = tabla_campos.jTableCampos;
        
        KeyStroke copy = KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK, false);
        // Identifying the copy KeyStroke user can modify this
        // to copy on some other Key combination.
        KeyStroke paste = KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK, false);
        // Identifying the Paste KeyStroke user can modify this
        //to copy on some other Key combination.
        jTableCampos.registerKeyboardAction(this, "Copy", copy, JComponent.WHEN_FOCUSED);
        jTableCampos.registerKeyboardAction(this, "Paste", paste, JComponent.WHEN_FOCUSED);
        system = Toolkit.getDefaultToolkit().getSystemClipboard();
    }
    
    
    /**
     * Public Accessor methods for the Table on which this adapter acts.
     * @return 
     */
    public JTable getJTable() {
        return jTableCampos;
    }
    
    public void setJTable(JTable jTable1) {
        this.jTableCampos = jTable1;
    }
    
    
    /**
     * This method is activated on the Keystrokes we are listening to
     * in this implementation. Here it listens for Copy and Paste ActionCommands.
     * Selections comprising non-adjacent cells result in invalid selection and
     * then copy action cannot be performed.
     * Paste is done by aligning the upper left corner of the selection with the
     * 1st element in the current selection of the JTable.
     * 
     * @param e
     */
   @Override
    public void actionPerformed(ActionEvent e)
    {
        // Copy
        if (e.getActionCommand().compareTo("Copy") == 0)
        {
            copiarDeTabla(jTableCampos);
        }
      
      
        // Paste (Adapatado a la tabla de ficheros)
        if (e.getActionCommand().compareTo("Paste") == 0)
        {
            try {
                String texto_a_pegar = (String) (system.getContents(this).getTransferData(DataFlavor.stringFlavor));
                List <CCampo> l_campos = ParseCodigo.codigo2Campos(texto_a_pegar, ParseCodigo.NAT_EXCEL);
                tabla_campos.campos2TablaPosActual(l_campos);
            } catch (Throwable th) {
                LogManager.getRootLogger().error("Error al pegar en la tabla", th);
            }

        }
    }

    
    /**
     * Copia al portapapeles las celdas seleccionadas en la tabla.
     * 
     * @param jtable
     */
    private void copiarDeTabla(JTable jtable) {
        StringBuilder sbf = new StringBuilder();

        // Check to ensure we have selected only a contiguous block of cells
        int numcols = jtable.getSelectedColumnCount();
        int numrows = jtable.getSelectedRowCount();

        // Si no hay celdas seleccionadas, volvemos
        if (numcols <= 0 || numrows <= 0) {
            return;
        }

        int [] rowsselected = jtable.getSelectedRows();
        int [] colsselected = jtable.getSelectedColumns();

        if (!((numrows - 1 == rowsselected[rowsselected.length - 1] - rowsselected[0] &&
               numrows == rowsselected.length) &&
               (numcols - 1 == colsselected[colsselected.length - 1] - colsselected[0] &&
               numcols == colsselected.length)))
        {
           JOptionPane.showMessageDialog(null, "Selección inválida", "Selección inválida", JOptionPane.ERROR_MESSAGE);
           return;
        }

        for (int i = 0; i < numrows; i++)
        {
           for (int j = 0; j < numcols; j++)
           {
               sbf.append(jtable.getValueAt(rowsselected[i],colsselected[j]));
               if (j < numcols - 1) {
                   sbf.append("\t");
               }
           }
           sbf.append("\n");
        }
        stsel  = new StringSelection(sbf.toString());
        system = Toolkit.getDefaultToolkit().getSystemClipboard();
        system.setContents(stsel, stsel);
    }
    
}
