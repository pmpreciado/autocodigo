/**
 * IdentificacionTextoPegar.java
 *
 * Creado el 18-may-2015, 18:37:53
 */

package es.cajal.autocodigo.importacion;

import es.cajal.autocodigo.Comun;
import es.cajal.autocodigo.generacion.CCampo;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * Funciones para la identificación del texto a pegar en la tabla con los campos.
 * 
 * @author pmpreciado
 */
public class ParseCodigo {

    
    /** Distintas naturalezas para el texto a pegar en la tabla */
    public static final int NAT_EXCEL               = 1;
    public static final int NAT_DEFINICION          = 2;
    public static final int NAT_PARAM               = 3;
    public static final int NAT_LISTA               = 4;
    public static final int NAT_SCRIPT_BD           = 5;
    
    
    /** Subcadenas que puede contener un código de tipo NAT_EXCEL para ser identificado como tal */
    private static final String [] ARR_TOKEN_EXCEL = {
        "\t"
    };
    
    
    /** Subcadenas que puede contener un código de tipo NAT_DEFINICION para ser identificado como tal */
    private static final String [] ARR_TOKEN_DEFINICION = {
        "String ",
        "int ",
        "long ",
        "TipoDecimal ",
        "TipoFecha ",
        "TipoHora "
    };
            
    /** Subcadenas que puede contener un código de tipo NAT_PARAM para ser identificado como tal */
    private static final String [] ARR_TOKEN_PARAM = {
        "@param ",
    };
    
    /** Subcadenas que puede contener un código de tipo NAT_LISTA para ser identificado como tal */
    private static final String [] ARR_TOKEN_LISTA = {
        "<li>",
    };
    
    /** Subcadenas que puede contener un código de tipo NAT_SCRIPT_BD para ser identificado como tal */
    private static final String [] ARR_TOKEN_SCRIPT_BD = {
        "create ",
        "table ",
        "varchar ",
        "decimal ",
        "primary ",
        "key ",
    };
    
    
    
    
    
    /**
     * Cuenta el número de veces que cada una de las subcadenas aparecen dentro de la cadena.
     * 
     * @param cadena                            Cadena
     * @param arr_subcadenas                    Subacadenas a buscar dentro de la cadena
     * 
     * @return                                  Número total de ocurrencias (de todas las subcadenas)
     */
    private static int contarOcurrencias(String cadena, String [] arr_subcadenas) {
        int total = 0;
        
        for (int i = 0; i < Comun.length(arr_subcadenas); i++) {
            String subcadena = arr_subcadenas[i];
            
            int n = StringUtils.countMatches(cadena, subcadena);
            total += n;
        }
        
        return total;
    }
    
    
    
    /**
     * Dado un texto a ser pegado en la tabla, identifica la naturaleza de dicho texto.
     * 
     * @param texto_a_pegar                     Texto a pegar
     * 
     * @return                                  Naturaleza del texto (IdentificacionTextoPegar.NAT_xxx)
     *                                          -1, si no se puede identificar
     */
    public static int identificarNaturalezaTexto(String texto_a_pegar) {
        
        // Comprobamos si es TEXTO_DEFINICION
        int num_ocurrencias_excel       = contarOcurrencias(texto_a_pegar, ARR_TOKEN_EXCEL);
        int num_ocurrencias_definicion  = contarOcurrencias(texto_a_pegar, ARR_TOKEN_DEFINICION);
        int num_ocurrencias_param       = contarOcurrencias(texto_a_pegar, ARR_TOKEN_PARAM);
        int num_ocurrencias_lista       = contarOcurrencias(texto_a_pegar, ARR_TOKEN_LISTA);
        int num_ocurrencias_script_bd   = contarOcurrencias(texto_a_pegar.toLowerCase(), ARR_TOKEN_SCRIPT_BD);
        
        if (
                num_ocurrencias_excel > num_ocurrencias_definicion &&
                num_ocurrencias_excel > num_ocurrencias_param &&
                num_ocurrencias_excel > num_ocurrencias_lista &&
                num_ocurrencias_excel > num_ocurrencias_script_bd
            )
        {
            return NAT_EXCEL;
        }
        
        if (
                num_ocurrencias_definicion > num_ocurrencias_excel &&
                num_ocurrencias_definicion > num_ocurrencias_param &&
                num_ocurrencias_definicion > num_ocurrencias_lista &&
                num_ocurrencias_definicion > num_ocurrencias_script_bd
            )
        {
            return NAT_DEFINICION;
        }
        
        if (
                num_ocurrencias_param > num_ocurrencias_excel &&
                num_ocurrencias_param > num_ocurrencias_definicion &&
                num_ocurrencias_param > num_ocurrencias_lista &&
                num_ocurrencias_param > num_ocurrencias_script_bd
            )
        {
            return NAT_PARAM;
        }

        if (
                num_ocurrencias_lista > num_ocurrencias_excel &&
                num_ocurrencias_lista > num_ocurrencias_definicion &&
                num_ocurrencias_lista > num_ocurrencias_param &&
                num_ocurrencias_lista > num_ocurrencias_script_bd
            )
        {
            return NAT_LISTA;
        }
        
        if (
                num_ocurrencias_script_bd > num_ocurrencias_excel &&
                num_ocurrencias_script_bd > num_ocurrencias_definicion &&
                num_ocurrencias_script_bd > num_ocurrencias_param &&
                num_ocurrencias_script_bd > num_ocurrencias_lista
            )
        {
            return NAT_SCRIPT_BD;
        }
        

        return -1;
    }
    
    
    /**
     * Comprueba si la naturaleza del código suministrado coincide con la naturaleza esperada.
     * 
     * @param codigo                            Código a comprobar
     * @param naturaleza_esperada               Naturaleza que se espera para el código (IdentificacionTextoPegar.NAT_xxx)
     * 
     * @return                                  
     */
    public static boolean textoReconocido(String codigo, int naturaleza_esperada) {
        
        int naturaleza_detectada = ParseCodigo.identificarNaturalezaTexto(codigo);
        boolean reconocido = naturaleza_detectada == naturaleza_esperada;
        return reconocido;
    }

    
    
    /**
     * Dado un fragmento de código de la naturaleza especificada, extrae los campos en él contenidos.
     * 
     * @param codigo                            Código
     * @param naturaleza                        Naturaleza del código
     * 
     * @return                                  Lista de campos
     */
    public static List <CCampo> codigo2Campos(String codigo, int naturaleza) {
        List <CCampo> l_campos = new ArrayList();
        
        switch (naturaleza) {
            case NAT_EXCEL:
                l_campos = excel2Campos(codigo);
                break;

            case NAT_DEFINICION:
                l_campos = codigoDefinicion2Campos(codigo);
                break;
                
            case NAT_PARAM:
                l_campos = codigoParam2Campos(codigo);
                break;

            case NAT_LISTA:
                l_campos = codigoLista2Campos(codigo);
                break;
                
            case NAT_SCRIPT_BD:
                l_campos = codigoScriptBd2Campos(codigo);
                break;

        }
        
        CCampo.updateTiposDatos(l_campos);
        return l_campos;
    }
    

    /**
     * Dado un texto que debe contener campos copiados de una hoja Excel o similar, extrae los campos en él definidos.
     * Los campos copiados de una hoja Excel están organizados como filas separadas por saltos de línea y campos separados por tabuladores.
     * 
     * @param codigo                            Código
     * 
     * @return                                  Lista de campos
     */
    private static List <CCampo> excel2Campos(String codigo) {
        List <CCampo> l_campos = new ArrayList();
        
        List <String> l_filas = Comun.split(codigo, "\n");
        CCampo campo_actual = new CCampo();
        
        for (int i = 0; i < Comun.size(l_filas); i++) {
            
            // Tomamos la siguiente línea. Si está vacía, saltamos a la siguiente
            String fila = Comun.trim(l_filas.get(i));
            if (fila.isEmpty()) {
                continue;
            }
            
            List <String> l_celdas = Comun.split(fila, "\t");
            
            // Si no hay celdas, saltamos a la siguiente
            if (l_celdas.isEmpty()) {
                continue;
            }
            
            campo_actual = new CCampo();
            
            // Celda 0 --> Nombre del campo
            campo_actual.nombre = l_celdas.get(0);
            
            // Celda 1 --> Tipo de dato para BD
            if (l_celdas.size() > 1) {
                campo_actual.tipo_bd = l_celdas.get(1);
            }
            
            // Celda 3 --> Descripción opcional
            if (l_celdas.size() > 2) {
                campo_actual.descripcion = l_celdas.get(2);
            }
            
            l_campos.add(campo_actual);
        }
        
        return l_campos;
    }
            
    
    /**
     * Dado un fragmento de código, expresado como definición, extrae los campos en él definidos.
     * 
     * @param codigo                            Código
     * 
     * @return                                  Lista de campos
     */
    private static List <CCampo> codigoDefinicion2Campos(String codigo) {
        List <CCampo> l_campos = new ArrayList();
        
        List <String> l_lineas = Comun.split(codigo, "\n");
        CCampo campo_actual = new CCampo();
        
        for (int i = 0; i < Comun.size(l_lineas); i++) {
            
            // Tomamos la siguiente línea. Si está vacía, continuamos
            String linea = Comun.trim(l_lineas.get(i));
            if (linea.isEmpty()) {
                continue;
            }
            
            // Si la línea es un comentario, lo interpretamos como la descripción del campo que viene después
            boolean es_comentario = ParseUtil.esComentario(linea);
            if (es_comentario) {
                String comentario = ParseUtil.extraerComentario(linea);
                campo_actual.descripcion = comentario;
            } else {
                boolean es_declaracion_campo = ParseUtil.esDeclaracionCampo(linea);
                if (es_declaracion_campo) {
                    campo_actual.setFromDeclaracion(linea);
                    l_campos.add(campo_actual);
                    
                    campo_actual = new CCampo();
                }
            }
        }
        
        return l_campos;
    }
    
    

    /**
     * Dado un fragmento de código, expresado como comentarios de tipo "param", extrae los campos en él definidos.
     * 
     * @param codigo                            Código
     * 
     * @return                                  Lista de campos
     */
    private static List <CCampo> codigoParam2Campos(String codigo) {
        List <CCampo> l_campos = new ArrayList();
        
        List <String> l_lineas = Comun.split(codigo, "\n");
        CCampo campo_actual = new CCampo();
        
        for (int i = 0; i < Comun.size(l_lineas); i++) {
            
            // Tomamos la siguiente línea. Si está vacía, continuamos
            String linea = Comun.trim(l_lineas.get(i));
            if (linea.isEmpty() || linea.equals("*")) {
                continue;
            }
            
            // Comprueba si la línea tiene el formato: * @param campo
            boolean es_comentario_param_campo = ParseUtil.esComentarioParamCampo(linea);
            if (es_comentario_param_campo) {
                campo_actual.setFromComentarioParamCampo(linea);
                l_campos.add(campo_actual);

                campo_actual = new CCampo();
            }
        }
        
        return l_campos;
    }

    
    /**
     * Dado un fragmento de código, expresado una lista de campos (<li>) dentro de comentarios JavaDoc, extrae los campos en él definidos.
     * 
     * @param codigo                            Código
     * 
     * @return                                  Lista de campos
     */
    private static List <CCampo> codigoLista2Campos(String codigo) {
        List <CCampo> l_campos = new ArrayList();
        
        List <String> l_lineas = Comun.split(codigo, "\n");
        CCampo campo_actual = new CCampo();
        
        for (int i = 0; i < Comun.size(l_lineas); i++) {
            
            // Tomamos la siguiente línea. Si está vacía, continuamos
            String linea = Comun.trim(l_lineas.get(i));
            if (linea.isEmpty() || linea.equals("*")) {
                continue;
            }
            
            // Comprueba si la línea tiene el formato: * <li> n campo
            boolean es_elemento_lista = ParseUtil.esElementoLista(linea);
            if (es_elemento_lista) {
                campo_actual.setFromElementoLista(linea);
                l_campos.add(campo_actual);

                campo_actual = new CCampo();
            }
        }
        
        return l_campos;
    }
    
    
    /**
     * Dado un fragmento de código, expresado un script de creación de tabla para una base de datos, extrae los campos en él definidos.
     * 
     * @param codigo                            Código
     * 
     * @return                                  Lista de campos
     */
    private static List <CCampo> codigoScriptBd2Campos(String codigo) {
        List <CCampo> l_campos = new ArrayList();
        
        List <String> l_lineas = Comun.split(codigo, "\n");
        CCampo campo_actual = new CCampo();
        
        // Buscamos la sentencia CREATE TABLE
        int indice_linea_campos = -1;
        for (int i = 0; i < Comun.size(l_lineas); i++) {
            
            // Tomamos la siguiente línea. Si está vacía, continuamos
            String linea = Comun.trim(l_lineas.get(i));
            if (linea.isEmpty()) {
                continue;
            }
            
            linea = linea.toUpperCase();
            if (linea.contains("CREATE") || linea.contains("TABLE")) {
                indice_linea_campos = i + 1;
            }
        }

        // ¿No se ha encontrado el CREATE TABLE? A lo mejor es que no se incluye
        if (indice_linea_campos == -1) {
            indice_linea_campos = 0;
        }

        
        // Extraemos los datos a partir del "CREATE TABLE"
        for (int i = indice_linea_campos; i < Comun.size(l_lineas); i++) {
            String linea = Comun.trim(l_lineas.get(i));
            if (linea.isEmpty()) {
                continue;
            }
            
            if (linea.equals("(")) {
                continue;
            }
            
            boolean es_elemento_script_bd = ParseUtil.esElementoScriptBd(linea);
            
            if (es_elemento_script_bd) {
                campo_actual.setFromElementoScriptBd(linea);
                l_campos.add(campo_actual);

                campo_actual = new CCampo();
                
            }
            
        }
        
        return l_campos;
    }
    

    
}
    