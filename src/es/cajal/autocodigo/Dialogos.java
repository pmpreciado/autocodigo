/**
 * Dialogos.java
 *
 * Creado el 05-feb-2014, 10:44:02
  */

package es.cajal.autocodigo;

import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JOptionPane;


/**
 * Facilita el uso de cuadros de diálogos sencillos.
 *
 * @author Pedro María Preciado
 */
public class Dialogos {


    /** 
     * Crea la instancia de la clase.
     */
    public Dialogos() {
    }


    /**
     * Muestra un mensaje de información, usando un cuadro de diálogo.
     *
     * @param cnt           Objeto contenedor
     * @param padre         Componente que será el marco padre del cuadro de diálogo.
     *                      Si es 'null', se usa el marco por defecto.
     * @param mensaje       Mensaje a mostrar
     * @param titulo        Título del mensaje
     * @param tipo          Tipo del mensaje (puede ser 'null'):
     *   <ul>
     *     <li> JOptionPane.ERROR_MESSAGE
     *     <li> JOptionPane.INFORMATION_MESSAGE
     *     <li> JOptionPane.WARNING_MESSAGE
     *     <li> JOptionPane.QUESTION_MESSAGE
     *     <li> JOptionPane.PLAIN_MESSAGE
     *   </ul>
     * @param icono         Icono que acompañará el mensaje (puede ser 'null', y se usa el icono por defecto)
     */
    public static void showMessageDialog(Contenedor cnt, Component padre, Object mensaje, String titulo, int tipo, Icon icono) {

        JOptionPane.showMessageDialog(padre, mensaje, titulo, tipo, icono);
    }
    
    
    /**
     * Muestra un mensaje de información, usando un cuadro de diálogo.
     *
     * @param cnt           Objeto contenedor
     * @param padre         Componente que será el marco padre del cuadro de diálogo.
     *                      Si es 'null', se usa el marco por defecto.
     * @param mensaje       Mensaje a mostrar
     * @param titulo        Título del mensaje
     * @param tipo          Tipo del mensaje (puede ser 'null'):
     *   <ul>
     *     <li> JOptionPane.ERROR_MESSAGE
     *     <li> JOptionPane.INFORMATION_MESSAGE
     *     <li> JOptionPane.WARNING_MESSAGE
     *     <li> JOptionPane.QUESTION_MESSAGE
     *     <li> JOptionPane.PLAIN_MESSAGE
     *   </ul>
     */
    public static void showMessageDialog(Contenedor cnt, Component padre, Object mensaje, String titulo, int tipo) {

        JOptionPane.showMessageDialog(padre, mensaje, titulo, tipo);
    }

    
    
    /**
     * Muestra un cuadro de diálogo con varias opciones en las que el usuario debe
     * elegir una.
     *
     * @param cnt           Objeto contenedor
     * @param padre         Componente que será el marco padre del cuadro de diálogo.
     *                      Si es 'null', se usa el marco por defecto.
     * @param mensaje       Mensaje a mostrar
     * @param titulo        Título del mensaje
     * @param opciones      En entero que desgina las opciones disponibles:
     *   <ul>
     *     <li>JOptionPane.DEFAULT_OPTION
     *     <li>JOptionPane.YES_NO_OPTION
     *     <li>JOptionPane.YES_NO_CANCEL_OPTION
     *     <li>JOptionPane.OK_CANCEL_OPTION
     *   </ul>
     *			
     * @param tipo          Tipo del mensaje (puede ser 'null'):
     *   <ul>
     *     <li> JOptionPane.ERROR_MESSAGE
     *     <li> JOptionPane.INFORMATION_MESSAGE
     *     <li> JOptionPane.WARNING_MESSAGE
     *     <li> JOptionPane.QUESTION_MESSAGE
     *     <li> JOptionPane.PLAIN_MESSAGE
     *   </ul>
     * @return              Un entero indicando la opción seleccionada
     *   <ul>
     *     <li> JOptionPane.YES_OPTION
     *     <li> JOptionPane.NO_OPTION
     *     <li> JOptionPane.CANCEL_OPTION
     *     <li> JOptionPane.OK_OPTION
     *     <li> JOptionPane.CLOSED_OPTION
     *   </ul>
     */    
    public static int showConfirmDialog(Contenedor cnt, Component padre, Object mensaje, String titulo, int opciones, int tipo) {

        int r = JOptionPane.showConfirmDialog(padre, mensaje, titulo, opciones, tipo);
        return r;
    }
    
    
}
