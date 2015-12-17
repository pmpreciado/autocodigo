/**
 * ExtException.java
 *
 * Creado el 22-may-2015, 13:03:39
 */

package es.cajal.autocodigo.exception;

import es.cajal.autocodigo.Comun;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de una excepción de propósito general, con funciones extendidas a las excepciones estándar.
 *
 * ía Preciado
 */
public class ExtException extends Exception {


    /** Código del error producido */
    protected int codigo_error;

    /** Mensaje del error producido */
    protected String mensaje_error;
    
    /** Etiquetas para sustitución en el mensaje de error */
    protected List <String> l_tags;

    /** Descripción adicional, que se establece manualmente, al error producido */
    protected List <String> l_descripcion_adicional;

    /** Información de depuración, que no se mostrará al usuario, pero que aparecerá en la traza del error */
    protected List <String> l_informacion_debug;

    /** Lista de elementos que pueden acompañar a determinados tipos de error (por ejemplo, parámetros necesarios, valores incorrectos, etc.) */
    protected List <String> l_elementos;


    /** Consulta SQL que produjo el error */
    protected String sql_error;





    /**
     * Crea la instancia de la clase.
     */
    public ExtException() {
        codigo_error                    = Comun.NO_DEFINIDO;
        mensaje_error                   = null;
        l_tags                          = new ArrayList();
        l_descripcion_adicional         = new ArrayList();
        l_informacion_debug             = new ArrayList();
        l_elementos                     = new ArrayList();
        sql_error                       = null;
    }


    /**
     * Genera una excepción cuyo código de error es suministrado.
     *
     * @param codigo_error                  Código del error generado
     */
    public ExtException(int codigo_error) {
        this();
        setCodigoError(codigo_error);
        String mensaje = Errores.getMensajeError(codigo_error);
        setMensaje(mensaje);
    }


    /**
     * Genera una excepción cuyo código de error es suministrado.
     *
     * @param codigo_error                  Código del error generado
     * @param th                            Excepción causante
     */
    public ExtException(int codigo_error, Throwable th) {
        this(codigo_error);
        initCause(th);
    }


    /**
     * Genera una excepción cuyo código de error es suministrado, permitiendo especificar un tag de sustitución.
     *
     * @param codigo_error                  Código del error generado
     * @param tag                           String con un tag de sustitución
     */
    public ExtException(int codigo_error, String tag) {
        this(codigo_error);
        addTag(tag);
    }
    
    
    /**
     * Genera una excepción cuyo código de error es suministrado, permitiendo especificar un tag de sustitución.
     *
     * @param codigo_error                  Código del error generado
     * @param tag                           String con un tag de sustitución
     * @param th                            Excepción causante
     */
    public ExtException(int codigo_error, String tag, Throwable th) {
        this(codigo_error, tag);
        initCause(th);
    }


    /**
     * Genera una excepción cuyo código de error es suministrado, permitiendo especificar dos tags de sustitución.
     *
     * @param codigo_error                  Código del error generado
     * @param tag_1                         String con el primer tag de sustitución
     * @param tag_2                         String con el segundo tag de sustitución
     */
    public ExtException(int codigo_error, String tag_1, String tag_2) {
        this(codigo_error);
        addTag(tag_1);
        addTag(tag_2);
    }


    /**
     * Genera una excepción cuyo código de error es suministrado, permitiendo especificar un tag de sustitución.
     *
     * @param codigo_error                  Código del error generado
     * @param tags                          String [] con varios tags de sustitución
     */
    public ExtException(int codigo_error, String [] tags) {
        this(codigo_error);
        for (int i = 0; i < tags.length; i++) {
            addTag(tags[i]);
        }
    }



    /**
     * Genera una excepción cuyo código de error es suministrado, permitiendo especificar un tag de sustitución.
     *
     * @param codigo_error                  Código del error generado
     * @param tag                           int con un tag de sustitución
     */
    public ExtException(int codigo_error, int tag) {
        this(codigo_error);
        addTag(tag);
    }

    
    /**
     * Genera una excepción cuyo código de error es suministrado, permitiendo especificar un tag de sustitución.
     *
     * @param codigo_error                  Código del error generado
     * @param tag                           int con un tag de sustitución
     * @param th                            Excepción causante
     */
    public ExtException(int codigo_error, int tag, Throwable th) {
        this(codigo_error, tag);
        initCause(th);
    }
    

    /**
     * Genera una excepción cuyo código de error es suministrado, permitiendo especificar dos tags de sustitución.
     *
     * @param codigo_error                  Código del error generado
     * @param tag_1                         int con el primer tag de sustitución
     * @param tag_2                         int con el segundo tag de sustitución
     */
    public ExtException(int codigo_error, int tag_1, int tag_2) {
        this(codigo_error);
        addTag(tag_1);
        addTag(tag_2);
    }


    /**
     * Genera una excepción, estableciendo como causante una excepción dada.
     *
     * @param eex                           Excepción causante
     */
    public ExtException(Throwable eex) {
        this(Errores.ERR_GEN_INDETERMINADO, eex);
    }


    /**
     * Establece el código del error.
     *
     * @param codigo_error                  Código del error a establecer
     */
    public void setCodigoError(int codigo_error) {
        this.codigo_error = codigo_error;
    }


    /**
     * Retorna el código de error.
     *
     * @return                              Código del error
     */
    public int getCodigoError() {
        return codigo_error;
    }


    /**
     * Retorna el código de error.
     * Esta función es un alias de getCodigoError().
     *
     * @return                              Código del error
     */
    public int getErrorCode() {
        return getCodigoError();
    }

    
    /**
     * Establece el mensaje del error.
     *
     * @param mensaje_error                 Mensaje del error
     */
    public void setMensaje(String mensaje_error) {
        this.mensaje_error = mensaje_error;
    }

    
    /**
     * Obtiene el mensaje del error, sustituyendo las etiquetas de sustitución por sus valores.
     *
     * @return                              Mensaje del error
     */
    public String getMensaje() {
        if (mensaje_error == null) {
            return "";
        }
        String mensaje_tags = Comun.sustituirTagsCadena(mensaje_error, l_tags);
        return mensaje_tags;
    }


    /**
     * Obtiene la lista recursiva de mensajes de error de la excepción así como de las excepciones causantes,
     * mientras estas sean de la clase ExtException.
     * 
     * @return                                  Lista con los mensajes de error recursivos
     */
    public List <String> getMensajesRecursivos() {
        
        List <String> l_mensajes_recursivos = new ArrayList();
        
        String mensaje = getMensaje();
        l_mensajes_recursivos.add(mensaje);
                
        Throwable cause = this.getCause();
        while (cause != null && cause instanceof ExtException) {
            String mensaje_cause = ((ExtException) cause).getMensaje();
            l_mensajes_recursivos.add(mensaje_cause);
            cause = ((ExtException) cause).getCause();
        }
                
        return l_mensajes_recursivos;
    }
    
    
    /**
     * Obtiene en una única cadena, la lista recursiva de mensajes de error de la excepción así como de las excepciones causantes,
     * mientras estas sean de la clase ExtException.
     * Los mensajes van separados por saltos de línea.
     * 
     * @return                                  Lista con los mensajes de error recursivos
     */
    public String getCadenaMensajesRecursivos() {
        
        List <String> l_mensajes_recursivos = getMensajesRecursivos();
        String s_mensajes_recursivos = Comun.join(l_mensajes_recursivos, Comun.NL);
        return s_mensajes_recursivos;
    }
    
//    
//    /**
//     * Obtiene el StackTrace de la excepción en una cadena.
//     *
//     * @return                      Cadena con el StackTrace
//     */
//    public String getCadenaStackTrace() {
//        String st = Comun.getStackTrace(this);
//        return st;
//    }
        
    
    /**
     * Obtiene en una misma cadena la descripción del mensaje del error junto con la descripción adicional.
     *
     * @return                              Descripción del error
     */
    public String getDescripcion() {
        String desc = getMensaje() + Comun.NL + getCadenaDescripcionAdicional();
        return desc;
    }


    /**
     * Obtiene en una misma cadena la descripción del mensaje del error junto con la descripción adicional.
     * Esta función es un alias de getDescripcion().
     *
     * @return                              Mensaje del error
     */
    public String getDescription() {
        return getDescripcion();
    }


    /**
     * Retorna en una cadena el código seguido de la descripción del error.
     * La descripción se compone del mensaje del error junto con la descripción adicional.
     *
     * @return                              Número de error + ": " + Descripción del error
     */
    public String getCodigoYDescripcion() {
        String texto = "Error " + Integer.toString(getCodigoError()) + ": " + getDescripcion();
        return texto;
    }


    /**
     * Añade un valor a las etiquetas de sustitución.
     *
     * @param tag                           String con un tag de sustitución
     */
    public void addTag(String tag) {
        l_tags.add(tag);
    }


    /**
     * Añade un valor de tipo entero a las etiquetas de sustitución.
     *
     * @param tag                           int con un tag de sustitución
     */
    public void addTag(int tag) {
        l_tags.add(Integer.toString(tag));
    }


    /**
     * Establece la descripción adicional del error, sustituyendo a la anterior, si la hubiera.
     *
     * @param descripcion                   Descripción adicional del error
     */
    public void setDescripcionAdicional(String descripcion) {
        l_descripcion_adicional = new ArrayList();
        addDescripcionAdicional(descripcion);
    }


    /**
     * Añade un texto a la descripción adicional del error.
     * Si ya hubiera descripción adicional, el texto se añade después, conservando el ya existente.
     *
     * @param descripcion                   Descripción adicional del error
     */
    public void addDescripcionAdicional(String descripcion) {
        l_descripcion_adicional.add(descripcion);
    }


    /**
     * Obtiene los mensajes de la descripción adicional.
     *
     * @return                              Lista con los mensajes de la descripción adicional
     */
    public List <String> getDescripcionAdicional() {
        return  l_descripcion_adicional;
    }
    
    
    /**
     * Obtiene en una cadena la descripción adicional del error.
     * Si hay varias descripciones, se separan con saltos de línea.
     *
     * @return                              Descripción adicional del error
     */
    public String getCadenaDescripcionAdicional() {
        
        String da = Comun.join(l_descripcion_adicional, Comun.NL);
        return da;
    }
    

    /**
     * Información de depuración.
     *
     * @param informacion_debug             Información de depuración
     */
    public void setInformacionDebug(String informacion_debug) {
        l_informacion_debug = new ArrayList();
        addDescripcionAdicional(informacion_debug);
    }


    /**
     * Añade un texto a la información de depuración.
     * Si ya hubiera información de depuración, el texto se añade después, conservando el ya existente.
     *
     * @param informacion_debug             Información de depuración
     */
    public void addInformacionDebug(String informacion_debug) {
        l_informacion_debug.add(informacion_debug);
    }


    /**
     * Obtiene en una cadena la descripción adicional del error.
     * Si hay varias líneas de información, se separan con saltos de línea.
     *
     * @return                              Información de depuración
     */
    public String getInformacionDebug() {
        String result = "";
        for (int i = 0; i < l_informacion_debug.size(); i++) {
            String d = l_informacion_debug.get(i);
            if (i > 0) result += Comun.NL;
            result += d;
        }

        return result;
    }


    /**
     * Añade un elemento a la lista de elementos que pueden acompañar a determinados tipos de error (por ejemplo, parámetros necesarios, valores incorrectos, etc.).
     *
     * @param elemento                      Elemento a añadir
     */
    public void addElemento(String elemento) {
        l_elementos.add(elemento);
    }


    /**
     * Añade varios elementos a la lista de elementos que pueden acompañar a determinados tipos de error (por ejemplo, parámetros necesarios, valores incorrectos, etc.).
     *
     * @param elementos                     Elementos a añadir
     */
    public void addElementos(String [] elementos) {
        for (int i = 0; i < elementos.length; i++) {
            addElemento(elementos[i]);
        }
    }


    /**
     * Obtiene los elementos que pueden acompañar a determinados tipos de error (por ejemplo, parámetros necesarios, valores incorrectos, etc.).
     *
     * @return                              List <String> con los elementos
     */
    public List <String> getElementos() {
        return l_elementos;
    }


    /**
     * Comprueba si se han especificado elementos para acompañar al error (por ejemplo, parámetros necesarios, valores incorrectos, etc.).
     *
     * @return                              'true' si se han especificado elementos
     *                                      'false' en caso contrario
     */
    public boolean hayElementos() {
        return l_elementos.size() > 0;
    }


    /**
     * Establece la consulta SQL que provocó el error.
     *
     * @param sql_error                     Consulta SQL que provocó el error.
     */
    public void setSQLError(String sql_error) {
        this.sql_error = sql_error;
    }


    /**
     * Retorna la consulta SQL que provocó el error.
     *
     * @return                              Consulta SQL que provocó el error (si es 'null', es que no hay)
     */
    public String getSQLError() {
        if (sql_error == null) return "";
        return sql_error;
    }


    /**
     * Retorna en una cadena, el código seguido del mensaje del error, teniendo en cuenta los tags de sustitución.
     *
     * @return                              Número de error + ": " + Mensaje del error, habiendo sustituido los tags
     */
    public String getCodigoYMensaje() {
        if (getCodigoError() == Comun.NO_DEFINIDO) return "";
        String texto = Integer.toString(getCodigoError()) + ": " + getMensaje();
        return texto;
    }


    /**
     * Retorna la descripción de la excepción causante del error.
     *
     * @return                              Descripción de la excepción causante del error (si es 'null', es que no hay)
     */
    public String getDescripcionCausante() {
        Throwable causante = this.getCause();
        
        if (causante == null) return "";
        
        return causante.getMessage();
    }


    /**
     * Obtiene el mensaje asociado a la excepción.
     *
     * @return
     */
    @Override public String getMessage() {
        String info_error = getInfoError();
        return info_error;
    }


    /**
     * Obtiene una cadena con información detallada del error.
     * 
     * @return                                  Información detallada del error
     */
    public String getInfoError() {
        StringBuilder info = new StringBuilder();
        
        info.append(Comun.NL);
        info.append("EXCEPCION --------------------------------------" + Comun.NL);
        info.append(Comun.NL);
        info.append("Error:                     " + getCodigoYMensaje() + Comun.NL);
        info.append("Descripción:               " + Comun.NL + getCadenaDescripcionAdicional() + Comun.NL);

        String sqle = getSQLError();
        if (!Comun.vacio(sqle)) {
            info.append("SQL Error:                 " + sqle + Comun.NL);
        }
        
        String info_debug = getInformacionDebug();
        if (!Comun.vacio(info_debug)) {
            info.append("Información de depuración: " + info_debug + Comun.NL);
        }
        
        if (hayElementos()) {
            info.append("Elementos: " + Comun.NL);
            for (int i = 0; i < Comun.size(l_elementos); i++) {
                String e = l_elementos.get(i);
                info.append("    - " + e + Comun.NL);
            }
        }

        info.append("------------------------------------------------" + Comun.NL);

        String s_info = info.toString();
        return s_info;
    }


    /**
     * Genera una cadena con la información del error.
     * 
     * @return                              Cadena generada
     */
    @Override
    public String toString() {
        String s = getInfoError();
        return s;
    }
}
