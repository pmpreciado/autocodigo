/**
 * GeneracionComun.java
 *
 * Creado el 21-may-2015, 10:09:31
 */

package es.cajal.autocodigo.generacion;

import es.cajal.autocodigo.Comun;
import es.cajal.autocodigo.Contenedor;
import java.util.ArrayList;
import java.util.List;

/**
 * Funciones para generar fragmentos de código de uso común.
 * 
 * @author pmpreciado
 */
public class GeneracionComun {
    
    protected static final String NL = Comun.NL;
    
    /** Nombre de la tabla para la que se está generando el código automático */
    String nombre_tabla;

    /** Tabulador */
    String tab;
    
    /** Campos que el usuario ha insertado en la tabla del formulario */
    List <CCampo> l_campos;
    
    
    
    
    /**
     * Crea la instancia de la clase.
     * 
     * @param cnt                               Objeto contenedor
     */
    public GeneracionComun(Contenedor cnt) {
        
        this.tab = generarTab(cnt);
        this.nombre_tabla = cnt.formAutoCodigo.getNombreTabla();
        this.l_campos = cnt.formAutoCodigo.getCamposTabla();
    }

    
    
    /**
     * Obtiene la cadena que representa un tabulador.
     * 
     * @param cnt                               Objeto contenedor
     * 
     * @return                                  Cadena que representa un tabulador
     */
    private String generarTab(Contenedor cnt) {
        String s = Comun.repetirCaracter(" ", cnt.formOpciones.formato_espacios_tab);
        return s;
    }
    
    
    /**
     * Obtiene el primer número de columna en la que pueda ir una tabulación, posterior a todos los nombres de los campos.
     * Se comprueba la máxima longitud de los nombres de los campos, ya que la columna a utilizar debe estar a la derecha de todos ellos.
     * 
     * @param cnt                               Objeto contenedor
     * @param l_campos                          Campos a considerar
     * @param desplazamiento                    Número de caracteres que va a estar desplazado cada campo. Es como si el nombre del campo
     *                                          tuviera +desplazamiento caracteres de longitud
     * 
     * @return                                  Número de columna
     */
    protected int getNumeroColumnaPosteriorACampos(Contenedor cnt, List <CCampo> l_campos, int desplazamiento) {
        int mayor_longitud = CCampo.getMayorLongitud(l_campos);
        
        int num_columna = mayor_longitud + desplazamiento + 1;       // Siguiente posición
        int espacios_tab = cnt.formOpciones.formato_espacios_tab;
        
        while ((num_columna + 1) % espacios_tab != 0) {
            num_columna++;
        }
        
        return num_columna;
    }
    
    
    /**
     * Obtiene el primer número de columna en la que pueda ir una tabulación, posterior a todos los nombres de los campos.
     * Se comprueba la máxima longitud de los nombres de los campos, ya que la columna a utilizar debe estar a la derecha de todos ellos.
     * 
     * @param cnt                               Objeto contenedor
     * @param l_campos                          Campos a considerar
     * 
     * @return                                  Número de columna
     */
    protected int getNumeroColumnaPosteriorACampos(Contenedor cnt, List <CCampo> l_campos) {
        int desplazamiento = 0;
        int num_columna = getNumeroColumnaPosteriorACampos(cnt, l_campos, desplazamiento);
        return num_columna;
    }
    
    
    /**
     * Genera un objeto de tipo campo para contener la información del objeto Contenedor.
     * 
     * @param cnt                               Objeto contenedor
     * @return                                  Campo generado
     */
    protected CCampo getArgumentoContenedor(Contenedor cnt) {
        
        String clase_contenedor = cnt.formOpciones.tag_contenedor_clase;
        String nombre_contenedor = cnt.formOpciones.tag_contenedor_nombre;
        String desc_contenedor = cnt.formOpciones.tag_contenedor_desc;
        
        CCampo arg_contenedor = new CCampo(nombre_contenedor, "", desc_contenedor, clase_contenedor);
        return arg_contenedor;
    }
    
    
    /**
     * Concatena varias líneas de código dentro de una sola cadena.
     * Se usarán saltos de línea para la concatenación. Al final de la última línea se insertará otro salto.
     * 
     * @param l_lineas                          Líneas a concatenar
     * 
     * @return                                  Cadena con la concatenación de las líneas
     */
    protected StringBuilder concatenarLineas(List <String> l_lineas) {
        
        StringBuilder s = new StringBuilder();
        
        for (String linea : l_lineas) {
            s.append(linea);
            s.append(Comun.NL);
        }
        
        return s;
    }

    
    /**
     * Inserta espacios en blanco al principio de cada línea suministrada.
     * 
     * @param l_lineas                          Líneas
     * @param num_espacios_antes                Número de espacios a insertar al principio de cada línea
     * 
     * @return                                  Lista con las nuevas líneas
     */
    protected List <String> insertarEspaciosAntes(List <String> l_lineas, int num_espacios_antes) {
        
        String espacios_antes = Comun.repetirCaracter(" ", num_espacios_antes);

        List <String> l_result = new ArrayList();
        
        for (String linea : l_lineas) {
            linea = espacios_antes + linea;
            l_result.add(linea);
        }
        
        return l_result;
    }
    
    
    /**
     * Inserta una tabulación al comienzo de cada línea dada.
     * 
     * @param cnt                               Objeto contenedor
     * @param l_lineas                          Líneas
     * 
     * @return                                  Líneas con la tabulación al comienzo
     */
    protected List <String> insertarTab(Contenedor cnt, List <String> l_lineas) {
        
        List <String> l_lineas_tab = insertarEspaciosAntes(l_lineas, cnt.formOpciones.formato_espacios_tab);
        return l_lineas_tab;
    }


    /**
     * Añade el texto dado al final de la última linea dentro de la lista de líneas suministrada.
     * 
     * @param texto                             Texto a concatenar al final de la última línea
     * @param l_lineas                          Líneas
     */
    protected void insertarAlFinal(String texto, List <String> l_lineas) {
        String ultima_linea = l_lineas.get(l_lineas.size() - 1);
        ultima_linea += texto;
        l_lineas.set(l_lineas.size() - 1, ultima_linea);
    }
    
    
    
    /**
     * Añade espacios a una cadena hasta que su longitud sea la dada.
     * 
     * @param cadena                            Cadena original
     * @param longitud_a_alcanzar               Longitud a alcanzar
     * 
     * @return                                  Cadena con espacios añadidos
     */
    protected String añadirEspaciosHasta(String cadena, int longitud_a_alcanzar) {
        while (cadena.length() < longitud_a_alcanzar) {
            cadena = cadena + " ";
        }
        
        return cadena;
    }
    
    
    /**
     * Trocea un texto en varias líneas. para que se ajuste a un ancho máximo.
     * Divide por los espacios en blanco.
     * 
     * @param texto_original                Texto a trocear
     * @param ancho_maximo                  Ancho máximo de línea
     * 
     * @return                              Líneas
     */
    protected List <String> trocearTexto(String texto_original, int ancho_maximo) {
        List <String> l_lineas = new ArrayList();
        
        List <String> l_palabras = Comun.split(texto_original, " ");
        
        String linea_actual = "";
        for (String palabra : l_palabras) {
            int nueva_longitud = linea_actual.length() + palabra.length();
            if (nueva_longitud > ancho_maximo) {
                l_lineas.add(linea_actual);
                linea_actual = palabra;
            } else {
                if (linea_actual.length() > 0) {
                    linea_actual = linea_actual + " ";
                }
                linea_actual += palabra;
            }
        }
        
        if (linea_actual.length() > 0) {
            l_lineas.add(linea_actual);
        }
        
        return l_lineas;
    }
    
    
    /**
     * Obtiene el identificador dado en formato "CamelCase".
     * Se sigue esta regla:
     * 
     *      identificador           CamelCase
     *      ------------------      ------------------
     *      cliente                 Cliente
     *      inv_proceso             InvProceso
     *      valor_actual_activo     ValorActualActivo
     * 
     * @param identificador                     Identificador a formatear
     * 
     * @return                                  Nombre de la clase
     */
    protected static String getCamelCase(String identificador) {
        
        StringBuilder camel_case = new StringBuilder();
        
        boolean mays_siguiente = true;
        for (int i = 0; i < identificador.length(); i++) {
            String c = Comun.miSubstringLong(identificador, i, 1);
            
            if (c.equals("_")) {
                mays_siguiente = true;
                continue;
            }
            
            if (mays_siguiente) {
                c = c.toUpperCase();
                mays_siguiente = false;
            }
            
            camel_case.append(c);
        }
        
        return camel_case.toString();
    }
    
    
    
    /**
     * Genera la parte de los comentarios de la función correspondiente a la descripción de lo que hace dicha función.
     * Las líneas generadas se adaptan al ancho máximo.
     * 
     * @param cnt                               Objeto contenedor
     * @param descripcion_funcion               Descripción de lo que hace la función
     * 
     * @return                                  Líneas con los comentarios
     */
    List <String> generarComentariosDescripcionFuncion(Contenedor cnt, String descripcion_funcion) {
        
        int ancho_max = cnt.formOpciones.formato_ancho_linea_maximo - " * ".length();
        
        List <String> l_trozos = trocearTexto(descripcion_funcion, ancho_max);
        List <String> l_lineas = new ArrayList();
        
        StringBuilder s = new StringBuilder();
        for (String trozo : l_trozos) {
            String linea = " * " + trozo;
            l_lineas.add(linea);
        }
        
        return l_lineas;
    }
    
    
    /**
     * Genera una línea de comentario de función que describe el param de un argumento de entrada.
     * 
     * @param cnt                               Objeto contenedor
     * @param nombre                            Nombre del argumento
     * @param descripcion                       Descripción del argumento
     * 
     * @return                                  Línea generada
     */
    private String generarComentarioParam(Contenedor cnt, String nombre, String descripcion) {
        
        String s = " * @param " + nombre;
        
        if (!Comun.vacio(descripcion)) {
            s += " ";
            // Columna de comentarios - tamaño del tabulador (ya que a está línea luego se le inserta un tabulador por la izquierda)
            s = añadirEspaciosHasta(s, cnt.formOpciones.formato_columna_comentarios - cnt.formOpciones.formato_espacios_tab - 1);
            s += descripcion;
        }
        
        return s;
    }
    
    
    /**
     * Genera las líneas de comentario de función con los param de los argumentos de entrada.
     * 
     * @param cnt                               Objeto contenedor
     * @param l_argumentos                      Argumentos de entrada
     * 
     * @return                                  Líneas con los comentarios
     */
    private List <String> generarComentariosParam(Contenedor cnt, List <CCampo> l_argumentos) {
        
        List <String> l_lineas = new ArrayList();
        
        for (CCampo argumento: l_argumentos) {
            String param_argumento = generarComentarioParam(cnt, argumento.nombre, argumento.descripcion);
            l_lineas.add(param_argumento);
        }
        
        return l_lineas;
    }
    
    
    /**
     * Genera las líneas de comentarios de función que describen el retorno de una función.
     * 
     * @param cnt                               Objeto contenedor
     * @param l_descripcion                     Descripción del retorno
     * 
     * @return                                  Línea generada
     */
    private List <String> generarComentariosReturn(Contenedor cnt, List <String> l_descripcion) {
        
        List <String> l_lineas = new ArrayList();
        String s;
        
        boolean primera_linea = true;
        for (String linea_descripcion: l_descripcion) {
            s = " * ";
            if (primera_linea) {
                s += "@return";
                primera_linea = false;
            }
            
            if (!Comun.vacio(linea_descripcion)) {
                s += " ";
                s = añadirEspaciosHasta(s, cnt.formOpciones.formato_columna_comentarios - cnt.formOpciones.formato_espacios_tab - 1);
                s += linea_descripcion;
            }
            
            l_lineas.add(s);
        }
        
        return l_lineas;
    }
    
    
    /**
     * Genera una línea de comentario de función que describe una excepción elevada por una función.
     * 
     * @param cnt                               Objeto contenedor
     * @param nombre                            Nombre de la excepción
     * @param descripcion                       Descripción de la excepción
     * 
     * @return                                  Línea generada
     */
    private String generarComentarioThrows(Contenedor cnt, String nombre, String descripcion) {
        
        String s = " * @throws " + nombre;
        if (!Comun.vacio(descripcion)) {
            s += " ";
            s = añadirEspaciosHasta(s, cnt.formOpciones.formato_columna_comentarios - cnt.formOpciones.formato_espacios_tab - 1);
            s += descripcion;
        }
        
        return s;
    }
    
    
    /**
     * Genera las líneas de comentario de función con las excepciones que puede elevar.
     * 
     * @param cnt                               Objeto contenedor
     * @param l_throws                          Excepciones que puede elevar
     * 
     * @return                                  Líneas generadas
     */
    private List <String> generarComentariosThrows(Contenedor cnt, List <CCampo> l_throws) {
        
        List <String> l_lineas = new ArrayList();
        
        for (CCampo th: l_throws) {
            String throws_th = generarComentarioThrows(cnt, th.nombre, th.descripcion);
            l_lineas.add(throws_th);
        }
        
        return l_lineas;
    }
    
    
    /**
     * Genera los comentarios de una función.
     * 
     * @param cnt                               Objeto contenedor
     * @param descripcion_funcion               Descripción de lo que hace la función
     * @param l_argumentos                      Argumentos de entrada a la función
     * @param l_descripcion_retorno             Descripción del retorno. Si es 'null', no se usa
     * @param l_throws                          Excepciones elevadas por la función
     * 
     * @return                                  Texco con los comentarios generados
     */
    protected List <String> generarComentariosFuncion(Contenedor cnt, String descripcion_funcion, List <CCampo> l_argumentos, List <String> l_descripcion_retorno, List <CCampo> l_throws) {
        
        List <String> l_lineas = new ArrayList();
        
        l_lineas.add("/**");
        
        // Descripción
        List <String> l_comentarios_descripcion_funcion = generarComentariosDescripcionFuncion(cnt, descripcion_funcion);
        l_lineas.addAll(l_comentarios_descripcion_funcion);
        
        // Param
        if (!Comun.vacio(l_argumentos)) {
            List <String> l_comentarios_argumentos_funcion = generarComentariosParam(cnt, l_argumentos);
            l_lineas.add(" *");
            l_lineas.addAll(l_comentarios_argumentos_funcion);
        }
        
        // Return
        if (l_descripcion_retorno != null) {
            List <String> l_comentarios_retorno = generarComentariosReturn(cnt, l_descripcion_retorno);
            l_lineas.add(" *");
            l_lineas.addAll(l_comentarios_retorno);
        }
        
        // Throws
        if (!Comun.vacio(l_throws)) {
            List <String> l_comentarios_throws = generarComentariosThrows(cnt, l_throws);
            l_lineas.add(" *");
            l_lineas.addAll(l_comentarios_throws);
        }
        
        l_lineas.add(" */");
        
        return l_lineas;
    }

    
    /**
     * Genera los comentarios de una función.
     * La función no generará excepciones.
     * 
     * @param cnt                               Objeto contenedor
     * @param descripcion_funcion               Descripción de lo que hace la función
     * @param l_argumentos                      Argumentos de entrada a la función
     * @param l_descripcion_retorno             Descripción del retorno. Si es 'null', no se usa
     * 
     * @return                                  Texco con los comentarios generados
     */
    protected List <String> generarComentariosFuncion(Contenedor cnt, String descripcion_funcion, List <CCampo> l_argumentos, List <String> l_descripcion_retorno) {
        
        List <CCampo> l_throws = null;
        List <String> l_lineas = generarComentariosFuncion(cnt, descripcion_funcion, l_argumentos, l_descripcion_retorno, l_throws);
        return l_lineas;
    }
    
    
    /**
     * Genera los comentarios de una función.
     * 
     * @param cnt                               Objeto contenedor
     * @param descripcion_funcion               Descripción de lo que hace la función
     * @param l_argumentos                      Argumentos de entrada a la función
     * @param descripcion_retorno               Descripción del retorno. Si es 'null', no se usa
     * @param l_throws                          Excepciones elevadas por la función
     * 
     * @return                                  Texco con los comentarios generados
     */
    protected List <String> generarComentariosFuncion(Contenedor cnt, String descripcion_funcion, List <CCampo> l_argumentos, String descripcion_retorno, List <CCampo> l_throws) {
        
        List <String> l_descripcion_retorno = null;
        if (descripcion_retorno != null) {
            l_descripcion_retorno = new ArrayList();
            l_descripcion_retorno.add(descripcion_retorno);
        }
        
        List <String> l_lineas = generarComentariosFuncion(cnt, descripcion_funcion, l_argumentos, l_descripcion_retorno, l_throws);
        return l_lineas;
    }
    
    
    /**
     * Genera los comentarios de una función.
     * La función no generará excepciones.
     * 
     * @param cnt                               Objeto contenedor
     * @param descripcion_funcion               Descripción de lo que hace la función
     * @param l_argumentos                      Argumentos de entrada a la función
     * @param descripcion_retorno               Descripción del retorno. Si es 'null', no se usa
     * 
     * @return                                  Texco con los comentarios generados
     */
    protected List <String> generarComentariosFuncion(Contenedor cnt, String descripcion_funcion, List <CCampo> l_argumentos, String descripcion_retorno) {
        List <CCampo> l_throws = null;
        List <String> l_lineas = generarComentariosFuncion(cnt, descripcion_funcion, l_argumentos, descripcion_retorno, l_throws);
        return l_lineas;
    }
    
    
    /**
     * Genera una lista con los nombres de los campos dados.
     * La lista puede ocupar una o más líneas, si se supera la longitud máxima permitida.
     * 
     * @param cnt                               Objeto contenedor
     * @param l_campos                          Campos a incluir en las líneas
     * 
     * @return                                  Líneas generadas
     */
    protected List <String> generarListaNombresCampos(Contenedor cnt, List <CCampo> l_campos) {
        
        int ancho_maximo = cnt.formOpciones.formato_ancho_linea_maximo;
        
        List <String> l_lineas = new ArrayList();
        String linea_actual = "";
        
        for (int i = 0; i < l_campos.size(); i++) {
            CCampo campo = l_campos.get(i);
            boolean hay_mas = i < l_campos.size() - 1;
            
            String nuevo_campo = campo.nombre;
            if (hay_mas) {
                nuevo_campo += ", ";
            }
            
            int nueva_longitud = linea_actual.length() + nuevo_campo.length();
            
            if (nueva_longitud > ancho_maximo) {
                l_lineas.add(linea_actual);
                linea_actual = "";
            }
            
            linea_actual += nuevo_campo;
        }
        
        if (linea_actual.length() > 0) {
            l_lineas.add(linea_actual);
        }
        
        return l_lineas;
    }
    
    
    /**
     * Genera la línea o las líneas de código para definir una función.
     * El ancho de la línea generada estará adaptada a la longitud máxima de las líneas de código.
     * Ejemplo del código generado:
     *          public String [] getRegistro(Contenedor cnt, Pagina pagina, String concepto_garantia)
     * 
     * @param cnt                               Objeto contenedor
     * @param nombre_funcion                    Nombre de la función, incluyendo los modificadores de acceso y el retorno (Ejemplo "public static String [] getRegistro")
     * @param l_argumentos                      Argumentos de entrada a la función
     * @param l_throws                          Excepciones elevadas por la función
     * 
     * @return                                  Líneas generadas
     */
    protected List <String> generarDefinicionFuncion(Contenedor cnt, String nombre_funcion, List <CCampo> l_argumentos, List <CCampo> l_throws) {
        
        int ancho_maximo = cnt.formOpciones.formato_ancho_linea_maximo;
        
        List <String> l_lineas = new ArrayList();
        String linea_actual = nombre_funcion + "(";
        
        for (int i = 0; i < Comun.size(l_argumentos); i++) {
            CCampo argumento = l_argumentos.get(i);
            boolean hay_mas = i < l_argumentos.size() - 1;
            
            String declaracion_argumento = argumento.tipo_java + " " + argumento.nombre;
            if (hay_mas) {
                declaracion_argumento += ", ";
            }
            
            int nueva_longitud = linea_actual.length() + declaracion_argumento.length();
            if (nueva_longitud > ancho_maximo) {
                l_lineas.add(linea_actual);
                linea_actual = tab + tab;
            }
            
            linea_actual += declaracion_argumento;
        }
        
        linea_actual += ")";
        
        for (int i = 0; i < Comun.size(l_throws); i++) {
            CCampo th = l_throws.get(i);
            boolean hay_mas = i < l_throws.size() - 1;
            
            String declaracion_th = th.nombre;
            if (i == 0) {
                declaracion_th = " throws " + declaracion_th;
            }
            if (hay_mas) {
                declaracion_th += ", ";
            }
            
            int nueva_longitud = linea_actual.length() + declaracion_th.length();
            if (nueva_longitud > ancho_maximo) {
                l_lineas.add(linea_actual);
                linea_actual = tab + tab;
            }
            
            linea_actual += declaracion_th;
        }
        
        if (linea_actual.trim().length() > 0) {
            l_lineas.add(linea_actual);
        }
        
        return l_lineas;
    }
    
    
    /**
     * Genera la línea o las líneas de código para definir una función.
     * La función no elevará excepciones
     * El ancho de la línea generada estará adaptada a la longitud máxima de las líneas de código.
     * Ejemplo del código generado:
     *          public String [] getRegistro(Contenedor cnt, Pagina pagina, String concepto_garantia)
     * 
     * @param cnt                               Objeto contenedor
     * @param nombre_funcion                    Nombre de la función, incluyendo los modificadores de acceso y el retorno (Ejemplo "public static String [] getRegistro")
     * @param l_argumentos                      Argumentos de entrada a la función
     * 
     * @return                                  Líneas generadas
     */
    protected List <String> generarDefinicionFuncion(Contenedor cnt, String nombre_funcion, List <CCampo> l_argumentos) {
        List <CCampo> l_throws = null;
        
        List <String> l_lineas = generarDefinicionFuncion(cnt, nombre_funcion, l_argumentos, l_throws);
        return l_lineas;
    }
    
    
}
