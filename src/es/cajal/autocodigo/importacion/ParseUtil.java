/**
 * ParseReglas.java
 *
 * Creado el 19-may-2015, 10:21:46
 */

package es.cajal.autocodigo.importacion;

import es.cajal.autocodigo.Comun;
import es.cajal.autocodigo.generacion.CCampo;
import java.util.ArrayList;
import java.util.List;


/**
 * Reglas y utilidades para el parseo del código a importar.
 * 
 * @author pmpreciado
 */
public class ParseUtil {

    
    /** Tipos de datos para los campos de BD */
    private static final String [] ARR_TIPOS_DATO_BD = {"INT", "CHAR", "TEXT", "DECIMAL", "FLOAT", "BOOLEAN", "DATETIME", "DATE", "TIME"};
    
    
    
    /**
     * Dada una línea de código, obtiene todos los tokens encontrados.
     * Los token son las palabras detectadas, usando como separador los espacios en blanco.
     * Esta función descarta los tokens en blanco.
     * 
     * @param linea                             Línea de código
     * 
     * @return                                  Lista con los tokens encontrados
     */
    public static List <String> getTokens(String linea) {
        linea = linea.trim();
        List <String> l_tokens_all = Comun.split(linea, " ");
        List <String> l_tokens = new ArrayList();

        for (String token : l_tokens_all) {
            token = token.trim();
            if (!token.isEmpty()) {
                l_tokens.add(token);
            }
        }
        
        return l_tokens;
    }
    
    
    
    /**
     * Dada una línea de código, comprueba si es un comentario.
     * 
     * @param linea                             Línea de código
     * 
     * @return                                  'true' si es un comentario
     *                                          'false' en caso contrario
     */
    public static boolean esComentario(String linea) {
        
        linea = linea.trim();
        
        if (linea.startsWith("//")) {
            return true;
        }
        
        if (linea.startsWith("/*")) {
            return true;
        }

        return false;
    }
 
    
    /**
     * Dada una línea de código con un comentario, obtiene el comentario en sí, eliminando los caracteres que definen el comentario.
     * La forma de extraer el comentario es eliminar las secuencias de caracteres de apertura y cierre de comentarios.
     * 
     * @param linea_comentario                  Línea de código con el comentario
     * 
     * @return                                  Texto del comentario
     */
    public static String extraerComentario(String linea_comentario) {
        
        String comentario = linea_comentario;
        
        comentario = Comun.replaceAll(comentario, "//", "");
        comentario = Comun.replaceAll(comentario, "/**", "");
        comentario = Comun.replaceAll(comentario, "/*", "");
        comentario = Comun.replaceAll(comentario, "*/", "");

        comentario = comentario.trim();
        return comentario;
    }

    
    
    /**
     * Dada una línea de código, comprueba si es una declaración de un campo.
     * La declaración de un campo tiene una forma similar a alguna de estas:
     *          int variable;
     *          public int variable;
     * 
     * @param linea                             Línea de código
     * 
     * @return                                  'true' si es una declaración de un campo
     *                                          'false' en caso contrario
     */
    static boolean esDeclaracionCampo(String linea) {
        
        linea = linea.trim();
        List <String> l_tokens = getTokens(linea);
        
        if (l_tokens.size() < 2) {
            return false;
        }
        
        // El último carácter debe ser un ";"
        String ultimo_caracter = Comun.ultimos(linea, 1);
        if (!ultimo_caracter.equals(";")) {
            return false;
        }
        
        // Descartamos los modificadores de acceso
        int indice = 0;
        String token;
        do {
            if (indice >= l_tokens.size()) {
                // No hay más tokens
                return false;
            }

            token = l_tokens.get(indice++);
            boolean es_maj = CCampo.esModificadorAccesoJava(token);
            if (!es_maj) {
                break;
            }
        } while (true);
        
        
        boolean es_tdj = CCampo.esTipoDatoJava(token);
        
        if (!es_tdj) {
            return false;
        }
        
        // Todas las comprobaciones pasadas con éxito
        return true;
    }
    
    

    /**
     * Dado un token, comprueba si es una etiqueta "param".
     * 
     * @param token                             Token a comprobar
     * 
     * @return                                  'true' si es una etiqueta "param"
     *                                          'false' en caso contrario
     */
    public static boolean esTagParam(String token) {
        
        token = token.trim();

        if (token.equalsIgnoreCase("*@param")) {
            return true;
        }

        if (token.equalsIgnoreCase("@param")) {
            return true;
        }
        
        if (token.equalsIgnoreCase("param")) {
            return true;
        }
        
        return false;
    }

    
    /**
     * Dada una línea de código, comprueba si es un comentario de tipo "param".
     * Los comentarios de tipo "param" tienen un formato similar a este:
     *      * @param fecha_conciliacion            Fecha de conciliación
     * 
     * @param linea                             Línea de código
     * 
     * @return                                  'true' si es un comentario de tipo "param"
     *                                          'false' en caso contrario
     */
    static boolean esComentarioParamCampo(String linea) {
        
        linea = linea.trim();
        List <String> l_tokens = getTokens(linea);
        
        if (l_tokens.size() < 2) {
            return false;
        }
        
        // Buscamos el token "param"
        int indice = 0;
        String token;
        do {
            if (indice >= l_tokens.size()) {
                // No hay más tokens
                return false;
            }

            token = l_tokens.get(indice++);
            boolean es_param = esTagParam(token);
            
            if (!es_param) {
                break;
            }
        } while (true);
        
        // Justo después del "param", debe haber otro token con el identificador del campo
        if (indice >= l_tokens.size()) {
            // No hay más tokens
            return false;
        }
        
        return true;
    }

    

    /**
     * Dado un token, comprueba si es una etiqueta "<li>".
     * 
     * @param token                             Token a comprobar
     * 
     * @return                                  'true' si es una etiqueta "param"
     *                                          'false' en caso contrario
     */
    public static boolean esTagLi(String token) {
        
        token = token.trim();

        if (token.equalsIgnoreCase("<li>")) {
            return true;
        }

        if (token.equalsIgnoreCase("<ol>")) {
            return true;
        }
        
        return false;
    }

    
    
    /**
     * Dada una línea de código, comprueba si tiene el formato de un elemento de una lista.
     * Las líneas de este tipo tienen un formato similar a este:
     *      * <li> 09 fecha_conciliacion        Fecha de conciliación
     * 
     * @param linea                             Línea de código
     * 
     * @return                                  'true' si es un elemento de una lista
     *                                          'false' en caso contrario
     */
    static boolean esElementoLista(String linea) {
        linea = linea.trim();
        List <String> l_tokens = getTokens(linea);
        
        if (l_tokens.size() < 2) {
            return false;
        }
        
        // Buscamos el token "<li>"
        int indice = 0;
        String token;
        do {
            if (indice >= l_tokens.size()) {
                // No hay más tokens
                return false;
            }

            token = l_tokens.get(indice++);
            boolean es_li = esTagLi(token);
            
            if (!es_li) {
                break;
            }
        } while (true);
        
        // Justo después del "<li>", puede venir:
        //   a) El número del campo
        //   b) El nombre del campo
        
        if (indice >= l_tokens.size()) {
            // No hay más tokens
            return false;
        }
        
        token = l_tokens.get(indice++);
        boolean es_numero = Comun.esNumero(token);
        
        // Si el token anterior era un número, debe existir al menos otro token con el identificador del campo
        if (es_numero) {
            if (indice >= l_tokens.size()) {
                // No hay más tokens
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Comprueba si el tipo de datos suministrado se corresponde con un tipo de datos de BD reconocido.
     * 
     * @param tipo_dato_comprobar               Tipo de dato a comprobar
     * 
     * @return                                  'true' si el tipo de datos suministrado se corresponde con un tipo de datos de BD reconocido
     *                                          'false' si no se reconoce
     */
    public static boolean esTipoDatoBd(String tipo_dato_comprobar) {
        for (int i = 0; i < ARR_TIPOS_DATO_BD.length; i++) {
            String tipo_dato_bd = ARR_TIPOS_DATO_BD[i];
            
            boolean contenido_1 = Comun.contiene(tipo_dato_bd, tipo_dato_comprobar);
            boolean contenido_2 = Comun.contiene(tipo_dato_comprobar, tipo_dato_bd);
            
            if (contenido_1 || contenido_2) {
                return true;
            }
        }
        
        return false;
    }
    

    /**
     * Dada una línea de código, comprueba si tiene el formato de definición de un campo en el script de creación de tabla de BD.
     * Las líneas de este tipo tienen un formato similar a este:
     *      id_reg                 INT,                     -- Identificador del registro
     * 
     * @param linea                             Línea de código
     * 
     * @return                                  'true' si es un elemento de script BD
     *                                          'false' en caso contrario
     */
    static boolean esElementoScriptBd(String linea) {
        linea = linea.trim();
        List <String> l_tokens = getTokens(linea);
        
        if (l_tokens.size() < 2) {
            return false;
        }
        
        // En el segundo token debe estar el tipo de datos para la base de datos
        //private static final String [] ARR_TIPOS_DATO_BD = {"INT", "CHAR", "TEXT", "DECIMAL", "FLOAT", "BOOLEAN", "DATETIME", "DATE", "TIME"};
        String tipo_dato = l_tokens.get(1);
        
        boolean tipo_dato_reconocido = esTipoDatoBd(tipo_dato);
        if (!tipo_dato_reconocido) {
            return false;
        }
        
        // Si hay más de dos tokens, el tercero debe ser un comentario
        if (l_tokens.size() < 3) {
            String token_3 = l_tokens.get(2);
            if (token_3.startsWith("--") || token_3.startsWith("//") || token_3.startsWith("/*")) {
                return true;
            }
            
            return false;
        }
        
        return true;
    }
    
}
