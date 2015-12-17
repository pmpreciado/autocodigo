/**
 * CCampo.java
 *
 * Creado el 19-may-2015, 10:10:07
 */

package es.cajal.autocodigo.generacion;

import es.cajal.autocodigo.Comun;
import es.cajal.autocodigo.importacion.ParseUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Guarda las propiedades de un campo de la base dadtos.
 * 
 * @author pmpreciado
 */
public class CCampo {

    /** Tipos de dato de base de datos */
    public static final String TDBD_INT             = "INT";
    public static final String TDBD_VARCHAR         = "VARCHAR";
    public static final String TDBD_DECIMAL         = "DECIMAL";
    public static final String TDBD_BOOLEAN         = "BOOLEAN";
    

    /** Tipos de dato Java */
    public static final String TDJ_INT              = "int";
    public static final String TDJ_INTEGER          = "Integer";
    public static final String TDJ_STRING           = "String";
    public static final String TDJ_BOOLEAN          = "boolean";
    public static final String TDJ_BOOLEAN_2        = "Boolean";
    public static final String TDJ_LONG             = "long";
    public static final String TDJ_FLOAT            = "float";
    public static final String TDJ_DOUBLE           = "double";
    public static final String TDJ_BIGDECIMAL       = "BigDecimal";
    public static final String TDJ_FECHA            = "TipoFecha";
    public static final String TDJ_HORA             = "TipoHora";
    public static final String TDJ_DECIMAL          = "TipoDecimal";
    public static final String TDJ_STRINGBUILDER    = "StringBuilder";
    
    public static final String [] ARR_TDJ = 
    {
        TDJ_INT,
        TDJ_INTEGER,
        TDJ_STRING,
        TDJ_BOOLEAN,
        TDJ_BOOLEAN_2,
        TDJ_LONG,
        TDJ_FLOAT,
        TDJ_DOUBLE,
        TDJ_BIGDECIMAL,
        TDJ_FECHA,
        TDJ_HORA,
        TDJ_DECIMAL,
        TDJ_STRINGBUILDER
    };
    
    
    /** Modificadores de acceso Java */
    public static final String MAJ_PUBLIC           = "public";
    public static final String MAJ_PROTECTED        = "protected";
    public static final String MAJ_PRIVATE          = "private";
    public static final String MAJ_DEFAULT          = "default";
    public static final String MAJ_STATIC           = "static";
    
    public static final String [] ARR_MAJ = 
    {
        MAJ_PUBLIC,
        MAJ_PROTECTED,
        MAJ_PRIVATE,
        MAJ_DEFAULT,
        MAJ_STATIC
    };
    
    

    
    /** Nombre del campo en la base de datos */
    public String nombre;
    
    /** Tipo de la base de datos */
    public String tipo_bd;
    
    /** Descripción del campo */
    public String descripcion;
    
    /** Tipo asociado en Java */
    public String tipo_java;
    
    
    /**
     * Crea la instancia de la clase.
     */
    public CCampo() {
    }
    
    
    
    /**
     * Crea la instancia de la clase.
     * 
     * @param nombre
     * @param tipo_bd
     * @param descripcion
     * @param tipo_java 
     */
    public CCampo(String nombre, String tipo_bd, String descripcion, String tipo_java) {
        this.nombre         = nombre;
        this.tipo_bd        = tipo_bd;
        this.descripcion    = descripcion;
        this.tipo_java      = tipo_java;
    }
    
    
    
    /**
     * Comprueba si el token dado se corresponde con un tipo de dato Java.
     * 
     * @param token                             Token a comprobar
     * 
     * @return                                  'true' si el token dado se corresponde con un tipo de dato Java
     *                                          'false' si no
     */
    public static boolean esTipoDatoJava(String token) {
        int pos = Comun.enArray(ARR_TDJ, token);
        return pos >= 0;
    }
    
    
    
    /**
     * Comprueba si el token dado se corresponde con un modificador de acceso Java.
     * 
     * @param token                             Token a comprobar
     * 
     * @return                                  'true' si el token dado se corresponde con un modificador de acceso Java
     *                                          'false' si no
     */
    public static boolean esModificadorAccesoJava(String token) {
        int pos = Comun.enArray(ARR_MAJ, token);
        return pos >= 0;
    }
    
    

    /**
     * Intenta obtener el tipo de dato Java a partir del tipo de datos de base de datos.
     * 
     * @param nombre_campo                      Nombre del campo
     * @param tipo_java                         Tipo de datos de Java (ParseUtil.TD_xxx)
     * 
     * @return                                  Tipo de datos Java (ParseUtil.TD_xxx)
     */
    public static String getTipoBd(String nombre_campo, String tipo_java) {
        
        if (!Comun.vacio(nombre_campo)) {
            String nombre_campo_lwr = nombre_campo.toLowerCase();
            if (nombre_campo_lwr.contains("fech")) {
                return TDBD_INT;
            }
            if (nombre_campo_lwr.contains("hora")) {
                return TDBD_INT;
            }
        }
        
        if (!Comun.vacio(tipo_java)) {
            String tipo_java_lwr = tipo_java.toLowerCase();
            if (tipo_java_lwr.contains("int")) {
                return TDBD_INT;
            }
            if (tipo_java_lwr.contains("decimal")) {
                return TDBD_DECIMAL;
            }
            if (tipo_java_lwr.contains("boolean")) {
                return TDBD_BOOLEAN;
            }
            if (tipo_java_lwr.contains("char")) {
                return TDBD_VARCHAR;
            }
            if (tipo_java_lwr.contains("string")) {
                return TDBD_VARCHAR;
            }
            if (tipo_java_lwr.contains("text")) {
                return TDBD_VARCHAR;
            }
            if (tipo_java_lwr.contains("fecha")) {
                return TDBD_INT;
            }
            if (tipo_java_lwr.contains("hora")) {
                return TDBD_INT;
            }
        }
        
        return TDBD_VARCHAR;
    }
    
    
    /**
     * Intenta obtener el tipo de dato de la base de datos a partir del tipo de datos Java.
     * 
     * @param nombre_campo                      Nombre del campo
     * @param tipo_bd                           Tipo de datos de base de datos
     * 
     * @return                                  Tipo de datos Java (ParseUtil.TD_xxx)
     */
    public static String getTipoJava(String nombre_campo, String tipo_bd) {
        
        if (!Comun.vacio(nombre_campo)) {
            String nombre_campo_lwr = nombre_campo.toLowerCase();
            if (nombre_campo_lwr.contains("fech")) {
                return TDJ_FECHA;
            }
            if (nombre_campo_lwr.contains("hora")) {
                return TDJ_HORA;
            }
        }
        
        if (!Comun.vacio(tipo_bd)) {
            String tipo_bd_lwr = tipo_bd.toLowerCase();
            if (tipo_bd_lwr.contains("int")) {
                return TDJ_INT;
            }
            if (tipo_bd_lwr.contains("decimal")) {
                return TDJ_DECIMAL;
            }
            if (tipo_bd_lwr.contains("char")) {
                return TDJ_STRING;
            }
            if (tipo_bd_lwr.contains("string")) {
                return TDJ_STRING;
            }
            if (tipo_bd_lwr.contains("text")) {
                return TDJ_STRING;
            }
            if (tipo_bd_lwr.contains("date")) {
                return TDJ_FECHA;
            }
            if (tipo_bd_lwr.contains("time")) {
                return TDJ_HORA;
            }
        }
        
        return TDJ_STRING;
    }
    

    /**
     * Dado un identificador para Java, elimina todos los caracteres que pueden dar problemas.
     * 
     * @param identificador                     Identificador original
     * 
     * @return                                  identificador sin piojos
     */
    private static String despiojarIdentificador(String identificador) {
        String piojos = ";:-=./\\$%&$\"'!¡?¿ºª[]";
        identificador = Comun.despiojar(identificador, piojos);
        return identificador;
    }


    /**
     * Carga la información del campo a partir del código Java para declararlo.
     * La informaicón cargada es:
     *   - El nombre del campo
     *   - El tipo de dato Java
     * 
     * La declaración de un campo tiene una forma similar a alguna de estas:
     *          int variable;
     *          public int variable;
     * 
     * @param linea                             Línea de código
     */
    public void setFromDeclaracion(String linea) {
        linea = linea.trim();
        List <String> l_tokens = ParseUtil.getTokens(linea);
        
        // Descartamos los modificadores de acceso
        int indice = 0;
        String token;
        do {
            if (indice >= l_tokens.size()) {
                // No hay más tokens
                return;
            }

            token = l_tokens.get(indice++);
            boolean es_maj = esModificadorAccesoJava(token);
            if (!es_maj) {
                break;
            }
        } while (true);
        
        
        // Detectamos el tipo de dato Java
        boolean es_tdj = esTipoDatoJava(token);
        if (!es_tdj) {
            return;
        }
        this.tipo_java = token;
        
        if (indice >= l_tokens.size()) {
            // No hay más tokens
            return;
        }
        
        // Detectamos el nombre del identificador
        token = l_tokens.get(indice++);
        this.nombre = token;
        
        // Eliminamos caracteres que pueden dar problemas en identificadores Java
        this.nombre = despiojarIdentificador(this.nombre);
    }
    
    
    /**
     * Carga la información del campo a partir de una línea de comentario de tipo "param".
     * La informaicón cargada es:
     *   - El nombre del campo
     *   - La descripción
     * 
     * Los comentarios de tipo "param" tienen un formato similar a este:
     *      * @param fecha_conciliacion            Fecha de conciliación
     * 
     * @param linea                             Línea de código
     */
    public void setFromComentarioParamCampo(String linea) {
        
        linea = linea.trim();
        List <String> l_tokens = ParseUtil.getTokens(linea);
        
        if (l_tokens.size() < 2) {
            return;
        }
        
        // Buscamos el token "param"
        
        // Descartamos los modificadores de acceso
        int indice = 0;
        String token;
        do {
            if (indice >= l_tokens.size()) {
                // No hay más tokens
                return;
            }

            token = l_tokens.get(indice++);
            boolean es_param = ParseUtil.esTagParam(token);
            
            if (es_param) {
                break;
            }
        } while (true);
        
        // Obtenemos el token siguiente al "param", debe ser el nombre del identificador
        if (indice >= l_tokens.size()) {
            // No hay más tokens
            return;
        }
        
        
        String identificador = l_tokens.get(indice++);
        
        // A continuación, debe figurar la descripción del campo
        String descripcion_concatenada = "";
        for (int i = indice; i < l_tokens.size(); i++) {
            token = l_tokens.get(i);
            descripcion_concatenada += token + " ";
        }
        
        this.nombre = identificador;
        this.descripcion = descripcion_concatenada.trim();
    }
    
    
    /**
     * Carga la información del campo a partir de una línea con un elemento de tipo lista ("<li>").
     * La informaicón cargada es:
     *   - El nombre del campo
     *   - La descripción
     * 
     * Las líneas de este tipo tienen un formato similar a este:
     *      * <li> 09 fecha_conciliacion        Fecha de conciliación
     * 
     * @param linea                             Línea de código
     */
    public void setFromElementoLista(String linea) {
        linea = linea.trim();
        List <String> l_tokens = ParseUtil.getTokens(linea);
        
        if (l_tokens.size() < 2) {
            return;
        }
        
        // Buscamos el token "<li>"
        int indice = 0;
        String token;
        do {
            if (indice >= l_tokens.size()) {
                // No hay más tokens
                return;
            }

            token = l_tokens.get(indice++);
            boolean es_li = ParseUtil.esTagLi(token);
            
            if (es_li) {
                break;
            }
        } while (true);
        
        // Justo después del "<li>", puede venir:
        //   a) El número del campo
        //   b) El nombre del campo
        
        if (indice >= l_tokens.size()) {
            // No hay más tokens
        }
        
        token = l_tokens.get(indice++);
        boolean es_numero = Comun.esNumero(token);
        
        // Si el token anterior era un número, debe existir al menos otro token con el identificador del campo
        if (es_numero) {
            if (indice >= l_tokens.size()) {
                // No hay más tokens
                return;
            }
            token = l_tokens.get(indice++);
        }
        
        String identificador = token;
        
        // A continuación del identificador, debe venir la descripción
        String descripcion_concatenada = "";
        for (int i = indice; i < l_tokens.size(); i++) {
            token = l_tokens.get(i);
            descripcion_concatenada += token + " ";
        }
        
        this.nombre = identificador;
        this.descripcion = descripcion_concatenada.trim();
    }
    
    
    
    /**
     * Carga la información del campo a partir de una línea para definir un campo en un script de creación de tabla de base datos.
     * La informaicón cargada es:
     *   - El nombre del campo
     *   - El tipo de campo de base de datos
     *   - El tipo de campo de base de Java
     *   - La descripción
     * 
     * Las líneas de este tipo tienen un formato similar a este:
     *      id_reg      INT,        -- Identificador del registro
     * 
     * @param linea                             Línea de código
     */
    public void setFromElementoScriptBd(String linea) {
        
        
        linea = linea.trim();
        List <String> l_tokens = ParseUtil.getTokens(linea);
        
        if (l_tokens.size() < 2) {
            return;
        }
        
        // En el segundo token debe estar el tipo de datos para la base de datos
        //private static final String [] ARR_TIPOS_DATO_BD = {"INT", "CHAR", "TEXT", "DECIMAL", "FLOAT", "BOOLEAN", "DATETIME", "DATE", "TIME"};
        String tipo_dato = l_tokens.get(1);
        
        boolean tipo_dato_reconocido = ParseUtil.esTipoDatoBd(tipo_dato);
        if (!tipo_dato_reconocido) {
            return;
        }
        
        this.nombre = l_tokens.get(0);
        this.tipo_bd = l_tokens.get(1);
        if (tipo_bd.endsWith(",")) {
            tipo_bd = Comun.eliminarUltimos(tipo_bd, 1);
        }
        this.tipo_java = getTipoJava(nombre, tipo_bd);
        
        
        // Buscamos el inicio del comentario
        int indice_inicio_comentario = -1;
        for (int i = 2; i < l_tokens.size(); i++) {
            String token = l_tokens.get(i);
            if (token.startsWith("--") || token.startsWith("//") || token.startsWith("/*")) {
                indice_inicio_comentario = i;
            }
        }
        
        if (indice_inicio_comentario == -1) {
            this.descripcion = "";
        } else {
            
            // A continuación del identificador, debe venir la descripción
            String descripcion_concatenada = "";
            for (int i = indice_inicio_comentario; i < l_tokens.size(); i++) {
                String token = l_tokens.get(i);
                descripcion_concatenada += token + " ";
            }
            
            if (descripcion_concatenada.startsWith("--") || descripcion_concatenada.startsWith("//") || descripcion_concatenada.startsWith("/*")) {
                descripcion_concatenada = Comun.eliminarPrimeros(descripcion_concatenada, 2);
            }
            descripcion_concatenada = descripcion_concatenada.trim();
            this.descripcion = descripcion_concatenada;
        }
    }

    
    
    /**
     * Dada una lista de campos, intenta establecer automáticamente la información de los tipos de datos, si no existiera.
     * 
     * @param l_campos                          Lista de campos
     */
    public static void updateTiposDatos(List <CCampo> l_campos) {
        for (CCampo campo : l_campos) {
            
            if (Comun.vacio(campo.tipo_bd) && Comun.vacio(campo.tipo_java)) {
                String tipo_java = getTipoJava(campo.nombre, campo.tipo_bd);
                campo.tipo_java = tipo_java;
                String tipo_bd = getTipoBd(campo.nombre, campo.tipo_java);
                campo.tipo_bd = tipo_bd;
                
            } else if (Comun.vacio(campo.tipo_bd)) {
                String tipo_bd = getTipoBd(campo.nombre, campo.tipo_java);
                campo.tipo_bd = tipo_bd;
                
            } else if (Comun.vacio(campo.tipo_java)) {
                String tipo_java = getTipoJava(campo.nombre, campo.tipo_bd);
                campo.tipo_java = tipo_java;
            }
        }
    }
    
    
    /**
     * Dada una lista de campos, extrae sólo aquellos que sean de alguno de los tipos de datos Java de los suministrados.
     * 
     * @param l_campos                          Lista de campos
     * @param arr_tipos_java                    Tipos de datos Java a considerar
     * 
     * @return                                  Lista de campos extraída
     */
    public static List <CCampo> extraerPorTipoJava(List <CCampo> l_campos, String [] arr_tipos_java) {
        List <CCampo> l_campos_extraido = new ArrayList();
        
        for (CCampo campo : l_campos) {
            if (Comun.enArray(arr_tipos_java, campo.tipo_java) >= 0) {
                l_campos_extraido.add(campo);
            }
        }
        
        return l_campos_extraido;
    }
    
    
    /**
     * Obtiene la longitud máxima de todos los identificadores de los campos dados.
     * 
     * @param l_campos                          Lista de campos
     * 
     * @return                                  Longitud del nombre más largo de entre todos los campos
     */
    public static int getMayorLongitud(List <CCampo> l_campos) {
        
        int l_max = 0;
        for (CCampo campo : l_campos) {
            if (campo.nombre.length() > l_max) {
                l_max = campo.nombre.length();
            }
        }
        
        return l_max;
    }
    
    
    /**
     * Dada una lista de campos, genera una cadena con la lista de los nombres de dichos campos.
     * 
     * @param l_campos                          Lista de campos
     * 
     * @return                                  Cadena con la lista con los nombres de los campos, separados por comas
     */
    public static String getListaNombresCampos(List <CCampo> l_campos) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < Comun.size(l_campos); i++) {
            CCampo campo = l_campos.get(i);
            s.append(campo.nombre);
            
            if (i < Comun.size(l_campos) - 1) {
                s.append(", ");
            }
        }
        
        return s.toString();
    }
    
    
    
    /**
     * Obtiene una cadena con la información de la instancia.
     * 
     * @return                                  Cadena de texto generada
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        s.append("nombre: " + nombre + ", " + Comun.NL);
        s.append("tipo_bd: " + tipo_bd + ", " + Comun.NL);
        s.append("descripcion: " + descripcion + ", " + Comun.NL);
        s.append("tipo_java: " + tipo_java);
        
        return s.toString();
    }

}
