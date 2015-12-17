/**
 * GeneracionRegistro.java
 *
 * Creado el 22-may-2015, 9:14:06
 */

package es.cajal.autocodigo.generacion;

import es.cajal.autocodigo.Comun;
import es.cajal.autocodigo.Contenedor;
import java.util.ArrayList;
import java.util.List;

/**
 * Funciones para generar el código de de la clase que encapsula los campos de la tabla en un registro.
 * 
 * @author pmpreciado
 */
public class GeneracionRegistro extends GeneracionComun {
    
    /**
     * Crea la instancia de la clase.
     * 
     * @param cnt                               Objeto contenedor
     */
    public GeneracionRegistro(Contenedor cnt) {
        super(cnt);
        //this.nombre_tabla = cnt.formAutoCodigo.getNombreTabla();
        inicializar(cnt);
    }
 
    
    /**
     * Inicializa la primera vez algunos atributos de la clase, para que estén disponibles para todos los métodos.
     * 
     * @param cnt 
     */
    private void inicializar(Contenedor cnt) {
    }
    
    
    /**
     * Genera las líneas código con los imports de la clase.
     * 
     * @param cnt                               Objeto contenedor
     * 
     * @return                                  Líneas de código generadas
     */
    private List <String> generarImports(Contenedor cnt) {
        String tags_imports_bd = cnt.formOpciones.tag_imports_registro;
        List <String> l_imports = Comun.split(tags_imports_bd, Comun.NL);
        return l_imports;
    }
    
    
    /**
     * Genera las líneas de comentario con la descripción de la clase.
     * Incluye la lista de los campos en formato JavaDoc.
     * 
     * @param cnt                               Objeto contenedor
     * 
     * @return                                  Líneas de código generadas
     */
    private List <String> generarDescripcionClase(Contenedor cnt) {
        List <String> l_lineas = new ArrayList();
    
        l_lineas.add("/**");
        l_lineas.add(" * Atributos y métodos para almacenar y facilitar el proceso de registros de la tabla '" + nombre_tabla + "'.");
        if (!Comun.vacio(cnt.formOpciones.tag_author)) {
            l_lineas.add(" * ");
            l_lineas.add(" * @author " + cnt.formOpciones.tag_author);
        }
        l_lineas.add(" */");
        
        return l_lineas;
    }

    
    /**
     * Genera la línea de código que define el nombre de la clase.
     * La línea tiene una sintaxis similar a esta:
     *      public class DbSopr_pruebas extends DbUtil {
     * 
     * @param cnt                               Objeto contenedor
     * 
     * @return                                  Código generado
     */
    private String generarDefinicionClase(Contenedor cnt) {
        
        String nombre_clase = cnt.formAutoCodigo.getNombreClaseAccesoRegistro();
        
        String c = "public class " + nombre_clase;
        c += " {";
        
        return c;
    }
    
    
    /**
     * Genera las líneas de código para definir los atributos que contendrán cada campo de la tabla.
     * 
     * @param cnt                               Objeto contenedor
     * 
     * @return                                  Líneas generadas
     */
    private List <String> generarAtributos(Contenedor cnt) {
        List <String> l_lineas = new ArrayList();
        
        String ambito_identificadores = cnt.formOpciones.jb_ambito_identificadores;
        
        for (int i = 0; i < l_campos.size(); i++) {
            CCampo campo = l_campos.get(i);
            
            // Descripción del campo
            if (campo.descripcion != null) {
                String descripcion = "/** " + campo.descripcion + " */";
                l_lineas.add(descripcion);
            }
            
            // Declaración del campo
            String declaracion = ambito_identificadores + " " + campo.tipo_java + " " + campo.nombre + ";";
            l_lineas.add(declaracion);
            
            if (campo.descripcion != null) {
                l_lineas.add("");
            }
        }
        
        l_lineas = super.insertarTab(cnt, l_lineas);
        
        return l_lineas;
    }
    
    
    /**
     * Genera el código de la función del constructor de la clase.
     * 
     * @param cnt                               Objeto contenedor
     * 
     * @return                                  Líneas generadas
     */
    private List <String> generarConstructor(Contenedor cnt) {
        
        List <String> l_lineas = new ArrayList();
        List <String> l_lineas_tab = new ArrayList();
        
        // Comentarios
        String descripcion_funcion = "Crea una nueva instancia de la clase.";
        
        List <CCampo> l_argumentos = null;
        List <String> l_descripcion_retorno = null;
        List <String> l_comentarios = generarComentariosFuncion(cnt, descripcion_funcion, l_argumentos, l_descripcion_retorno);
        l_lineas.addAll(l_comentarios);
        
        // Cuerpo
        String nombre_clase = cnt.formAutoCodigo.getNombreClaseAccesoRegistro();
        String nombre_funcion = "public " + nombre_clase;
        List <String> l_definicion = generarDefinicionFuncion(cnt, nombre_funcion, l_argumentos);
        l_lineas.addAll(l_definicion);
        
        insertarAlFinal(" {", l_lineas);
        
        // Inicialización de atributos
            String linea;
            String [] arr_tipos_java = {CCampo.TDJ_DECIMAL, CCampo.TDJ_FECHA, CCampo.TDJ_HORA};
            List <CCampo> l_campos_extraidos = CCampo.extraerPorTipoJava(l_campos, arr_tipos_java);
            int numero_columna_posterior_a_campos = super.getNumeroColumnaPosteriorACampos(cnt, l_campos_extraidos);
            
            for (CCampo campo : l_campos_extraidos) {
                linea = campo.nombre;
                linea = super.añadirEspaciosHasta(linea, numero_columna_posterior_a_campos);

                if (campo.tipo_java.equals(CCampo.TDJ_DECIMAL)) {
                    linea += " = TipoDecimal.noDefinido();";
                } else if (campo.tipo_java.equals(CCampo.TDJ_FECHA)) {
                    linea += " = TipoFecha.noDefinida();";
                } else if (campo.tipo_java.equals(CCampo.TDJ_HORA)) {
                    linea += " = TipoHora.noDefinida();";
                } else {
                    continue;
                }
                
                l_lineas_tab.add(linea);
            }
        
            l_lineas_tab = super.insertarTab(cnt, l_lineas_tab);
            l_lineas.addAll(l_lineas_tab);
        
        l_lineas.add("}");
        
        l_lineas = super.insertarTab(cnt, l_lineas);
        return l_lineas;
    }
    
    
    /**
     * Genera el código de la función del constructor a partir de un objeto de la misma clase.
     * 
     * @param cnt                               Objeto contenedor
     * 
     * @return                                  Líneas generadas
     */
    private List <String> generarConstructorCopia(Contenedor cnt) {
        
        List <String> l_lineas = new ArrayList();
        List <String> l_lineas_tab = new ArrayList();
        
        String nombre_clase = cnt.formAutoCodigo.getNombreClaseAccesoRegistro();
        String nombre_objeto = "o";
        
        // Comentarios
        String descripcion_funcion = "Crea una nueva instancia de la clase, que es inicializada a partir del objeto suministrado.";
        
        //public CCampo(String nombre, String tipo_bd, String descripcion, String tipo_java) {
        CCampo campo_arg = new CCampo(nombre_objeto, "", "Objeto a partir del cual se inicializará esta instancia", nombre_clase);
        
        List <CCampo> l_argumentos = new ArrayList();
        l_argumentos.add(campo_arg);
                
        List <String> l_descripcion_retorno = null;
        List <String> l_comentarios = generarComentariosFuncion(cnt, descripcion_funcion, l_argumentos, l_descripcion_retorno);
        l_lineas.addAll(l_comentarios);
        
        // Cuerpo
        String nombre_funcion = "public " + nombre_clase;
        List <String> l_definicion = generarDefinicionFuncion(cnt, nombre_funcion, l_argumentos);
        l_lineas.addAll(l_definicion);
        
        insertarAlFinal(" {", l_lineas);
        
        // Inicialización de atributos
            String linea;
            int numero_columna_posterior_a_campos = super.getNumeroColumnaPosteriorACampos(cnt, l_campos);
            
            for (CCampo campo : l_campos) {
                linea = campo.nombre;
                linea = super.añadirEspaciosHasta(linea, numero_columna_posterior_a_campos);
                
                if (campo.tipo_java.equals(CCampo.TDJ_BIGDECIMAL) ||
                    campo.tipo_java.equals(CCampo.TDJ_STRINGBUILDER) ||
                    campo.tipo_java.equals(CCampo.TDJ_DECIMAL) ||
                    campo.tipo_java.equals(CCampo.TDJ_FECHA) ||
                    campo.tipo_java.equals(CCampo.TDJ_HORA) ||
                    campo.tipo_java.equals(CCampo.TDJ_DECIMAL))
                {
                    linea += " = new " + campo.tipo_java + "(" + nombre_objeto + "." + campo.nombre + ");";
                } else {
                    linea += " = " + nombre_objeto + "." + campo.nombre + ";";
                }
                
                l_lineas_tab.add(linea);
            }
        
            l_lineas_tab = super.insertarTab(cnt, l_lineas_tab);
            l_lineas.addAll(l_lineas_tab);
        
        l_lineas.add("}");
        
        l_lineas = super.insertarTab(cnt, l_lineas);
        return l_lineas;
    }
    
    
    /**
     * Genera el código de la función setInfo, para establecer todos los atributos de la instancia.
     * 
     * @param cnt                               Objeto contenedor
     * 
     * @return                                  Líneas generadas
     */
    private List <String> generarFuncionSetInfo(Contenedor cnt) {
        
        List <String> l_lineas = new ArrayList();
        List <String> l_lineas_tab = new ArrayList();
        
        // Comentarios
        String descripcion_funcion = "Establece los atributos de la instancia.";
        List <CCampo> l_argumentos = l_campos;
        List <String> l_descripcion_retorno = null;
        List <String> l_comentarios = generarComentariosFuncion(cnt, descripcion_funcion, l_argumentos, l_descripcion_retorno);
        l_lineas.addAll(l_comentarios);
        
        // Cuerpo
        String nombre_funcion = "public void setInfo";
        List <String> l_definicion = generarDefinicionFuncion(cnt, nombre_funcion, l_argumentos);
        l_lineas.addAll(l_definicion);
        l_lineas.add("{");
        
        // Inicialización de atributos
            String linea;
            int desplazamiento = "this.".length();
            int numero_columna_posterior_a_campos = super.getNumeroColumnaPosteriorACampos(cnt, l_campos, desplazamiento);
            
            for (CCampo campo : l_campos) {
                linea = "this." + campo.nombre;
                linea = super.añadirEspaciosHasta(linea, numero_columna_posterior_a_campos);
                linea += " = " + campo.nombre + ";";
                l_lineas_tab.add(linea);
            }
        
            l_lineas_tab = super.insertarTab(cnt, l_lineas_tab);
            l_lineas.addAll(l_lineas_tab);
        
        l_lineas.add("}");
        
        l_lineas = super.insertarTab(cnt, l_lineas);
        return l_lineas;
    }
    
    
    /**
     * Genera el código de la función "set" para un campo dado.
     * 
     * @param cnt                               Objeto contenedor
     * @param campo                             Campo para el que se va a generar la función "set"
     * 
     * @return                                  Líneas generadas
     */
    private List <String> generarFuncionSet(Contenedor cnt, CCampo campo) {
        List <String> l_lineas = new ArrayList();
        
        // Comentarios
        String descripcion_funcion = "Establece el valor del atributo '" + campo.nombre + "'.";
        List <CCampo> l_argumentos = new ArrayList();
        l_argumentos.add(campo);
        
        List <String> l_descripcion_retorno = null;
        List <String> l_comentarios = generarComentariosFuncion(cnt, descripcion_funcion, l_argumentos, l_descripcion_retorno);
        l_lineas.addAll(l_comentarios);
        
        // Cuerpo
        String nombre_funcion = super.getCamelCase(campo.nombre);
        nombre_funcion = "set" + nombre_funcion;
        nombre_funcion = "public void " + nombre_funcion;
        List <String> l_definicion = generarDefinicionFuncion(cnt, nombre_funcion, l_argumentos);
        l_lineas.addAll(l_definicion);
        l_lineas.add("{");
        
        // Inicialización de atributos
            String linea = tab + "this." + campo.nombre + " = " + campo.nombre + ";";
            l_lineas.add(linea);
            
        l_lineas.add("}");
        
        l_lineas = super.insertarTab(cnt, l_lineas);
        return l_lineas;
    }
    
    
    /**
     * Genera el código de la función "get" para un campo dado.
     * 
     * @param cnt                               Objeto contenedor
     * @param campo                             Campo para el que se va a generar la función "get"
     * 
     * @return                                  Líneas generadas
     */
    private List <String> generarFuncionGet(Contenedor cnt, CCampo campo) {
        List <String> l_lineas = new ArrayList();
        
        // Comentarios
        String descripcion_funcion = "Obtiene el valor del atributo '" + campo.nombre + "'.";
        List <CCampo> l_argumentos = null;
        String descripcion_retorno = "Valor del atributo '" + campo.nombre + "'.";
        List <String> l_comentarios = generarComentariosFuncion(cnt, descripcion_funcion, l_argumentos, descripcion_retorno);
        l_lineas.addAll(l_comentarios);
        
        // Cuerpo
        String nombre_funcion = super.getCamelCase(campo.nombre);
        nombre_funcion = "get" + nombre_funcion;
        nombre_funcion = "public " + campo.tipo_java + " " + nombre_funcion;
        List <String> l_definicion = generarDefinicionFuncion(cnt, nombre_funcion, l_argumentos);
        l_lineas.addAll(l_definicion);
        l_lineas.add("{");
        
        // Inicialización de atributos
            String linea = tab + "return " + campo.nombre + ";";
            l_lineas.add(linea);
            
        l_lineas.add("}");
        
        l_lineas = super.insertarTab(cnt, l_lineas);
        return l_lineas;
    }
    
    
    /**
     * Genera el código de las funciones "setter" y "getter" de cada atributo.
     * 
     * @param cnt                               Objeto contenedor
     * 
     * @return                                  Líneas generadas
     */
    private List <String> generarFuncionesSetterGetter(Contenedor cnt) {
        boolean generar_get = cnt.formOpciones.jb_generar_get;
        boolean generar_set = cnt.formOpciones.jb_generar_set;
        
        List <String> l_lineas = new ArrayList();
        List <String> l_lineas_get;
        List <String> l_lineas_set;
        
        for (CCampo campo : l_campos) {

            if (generar_set) {
                l_lineas_set = generarFuncionSet(cnt, campo);
                l_lineas.addAll(l_lineas_set);
                l_lineas.add("");
                l_lineas.add("");
            }

            if (generar_get) {
                l_lineas_get = generarFuncionGet(cnt, campo);
                l_lineas.addAll(l_lineas_get);
                l_lineas.add("");
                l_lineas.add("");
            }
        }

        return l_lineas;
    }
    
    
    /**
     * Genera el código de la función setInfoBd, para establecer los atributos de la instancia a partir de la información extraída de la base de datos.
     * 
     * @param cnt                               Objeto contenedor
     * 
     * @return                                  Líneas generadas
     */
    private List <String> generarFuncionSetInfoBd(Contenedor cnt) {
        
        List <String> l_lineas = new ArrayList();
        List <String> l_lineas_tab = new ArrayList();
        
        // Comentarios
        String descripcion_funcion = "Establece los atributos de la instancia, a partir de la información que se obtiene de la base de datos.";
        List <CCampo> l_argumentos = new ArrayList();
        CCampo arg = new CCampo("info_bd", "", "String [] con el registro de base de datos", "String []");
        l_argumentos.add(arg);
        List <String> l_descripcion_retorno = null;
        List <String> l_comentarios = generarComentariosFuncion(cnt, descripcion_funcion, l_argumentos, l_descripcion_retorno);
        l_lineas.addAll(l_comentarios);
        
        // Cuerpo
        String nombre_funcion = "public void setInfoBd";
        List <String> l_definicion = generarDefinicionFuncion(cnt, nombre_funcion, l_argumentos);
        l_lineas.addAll(l_definicion);
        l_lineas.add("{");
        
        // Inicialización de atributos
            l_lineas_tab.add("info_bd = Comun.trim(info_bd);");
            l_lineas_tab.add("");
            l_lineas_tab.add("int c = 0;");
            
            String linea;
            int desplazamiento = "this.".length();
            int numero_columna_posterior_a_campos = super.getNumeroColumnaPosteriorACampos(cnt, l_campos, desplazamiento);
            
            for (CCampo campo : l_campos) {
                linea = "this." + campo.nombre;
                linea = super.añadirEspaciosHasta(linea, numero_columna_posterior_a_campos);
                linea += " = ";
                
                
                if (campo.tipo_java.equals(CCampo.TDJ_INT) ||
                    campo.tipo_java.equals(CCampo.TDJ_INTEGER)) 
                {
                    linea += "Comun.toEntero(info_bd[c++]);";
                    
                } else if (campo.tipo_java.equals(CCampo.TDJ_STRING)) {
                    linea += "info_bd[c++];";
                    
                } else if (campo.tipo_java.equals( CCampo.TDJ_BOOLEAN) ||
                           campo.tipo_java.equals(CCampo.TDJ_BOOLEAN_2))
                {
                    linea += "Comun.toEntero(info_bd[c++]) > 0;";
                    
                } else if (campo.tipo_java.equals(CCampo.TDJ_LONG)) {
                    linea += "Comun.toLong(info_bd[c++]);";
                    
                } else if (campo.tipo_java.equals(CCampo.TDJ_FLOAT)) {
                    linea += "Float.parseFloat(info_bd[c++]);";
                    
                } else if (campo.tipo_java.equals(CCampo.TDJ_DOUBLE)) {
                    linea += "Double.parseDouble(info_bd[c++]);";
                    
                } else if (campo.tipo_java.equals(CCampo.TDJ_BIGDECIMAL) ||
                           campo.tipo_java.equals(CCampo.TDJ_FECHA) ||
                           campo.tipo_java.equals(CCampo.TDJ_HORA) ||
                           campo.tipo_java.equals(CCampo.TDJ_DECIMAL) ||
                           campo.tipo_java.equals(CCampo.TDJ_STRINGBUILDER))
                {
                    linea += "new " + campo.tipo_java + "(info_bd[c++]);";
                }
                
                l_lineas_tab.add(linea);
            }
        
            l_lineas_tab = super.insertarTab(cnt, l_lineas_tab);
            l_lineas.addAll(l_lineas_tab);
        
        l_lineas.add("}");
        
        l_lineas = super.insertarTab(cnt, l_lineas);
        return l_lineas;
    }
    

    /**
     * Genera el código de la función infoBd2List, que obtiene una lista con elementos de la clase del registro a partir de los registros obtenidos de la base de datos.
     * 
     * @param cnt                               Objeto contenedor
     * 
     * @return                                  Líneas generadas
     */
    private List <String> generarFuncionInfoBd2List(Contenedor cnt) {
        
        List <String> l_lineas = new ArrayList();
        List <String> l_lineas_tab = new ArrayList();
        
        String nombre_clase = cnt.formAutoCodigo.getNombreClaseAccesoRegistro();
        
        // Comentarios
        String descripcion_funcion = "Obtiene una lista con elementos de " + nombre_clase + " a partir de los registros obtenidos de la base de datos.";
        List <CCampo> l_argumentos = new ArrayList();
        CCampo arg = new CCampo("info_bd", "", "String [][] con los registros extraídos de la base de datos", "String [][]");
        l_argumentos.add(arg);
        String descripcion_retorno = "Lista generada";
        List <String> l_comentarios = generarComentariosFuncion(cnt, descripcion_funcion, l_argumentos, descripcion_retorno);
        l_lineas.addAll(l_comentarios);
        
        // Cuerpo
        String nombre_funcion = "public static List <" + nombre_clase + "> infoBd2List";
        List <String> l_definicion = generarDefinicionFuncion(cnt, nombre_funcion, l_argumentos);
        l_lineas.addAll(l_definicion);
        l_lineas.add("{");
        
        // Inicialización de atributos
            l_lineas_tab.add("List <" + nombre_clase + "> l_registros = new ArrayList();");
            l_lineas_tab.add("");
            
            l_lineas_tab.add("if (info_bd == null) {");
            l_lineas_tab.add(tab + "return l_registros;");
            l_lineas_tab.add("}");
            l_lineas_tab.add("");
            
            l_lineas_tab.add("for (int i = 0; i < info_bd.length; i++) {");
                l_lineas_tab.add(tab + nombre_clase + " registro = new " + nombre_clase + "();");
                l_lineas_tab.add(tab + "registro.setInfoBd(info_bd[i]);");
                l_lineas_tab.add(tab + "l_registros.add(registro);");
            l_lineas_tab.add("}");
            l_lineas_tab.add("");
            
            l_lineas_tab.add("return l_registros;");

            l_lineas_tab = super.insertarTab(cnt, l_lineas_tab);
            l_lineas.addAll(l_lineas_tab);
        
        l_lineas.add("}");
        
        l_lineas = super.insertarTab(cnt, l_lineas);
        return l_lineas;
    }            


    
    /**
     * Genera el código de la función toString, para mostrar los atributos de la instancia.
     * 
     * @param cnt                               Objeto contenedor
     * 
     * @return                                  Líneas generadas
     */
    private List <String> generarFuncionToString(Contenedor cnt) {
        
        List <String> l_lineas = new ArrayList();
        List <String> l_lineas_tab = new ArrayList();
        
        // Comentarios
        String descripcion_funcion = "Genera una cadena de texto con la información de la instancia.";
        List <CCampo> l_argumentos = null;
        String descripcion_retorno = "Cadena generada";
        List <String> l_comentarios = generarComentariosFuncion(cnt, descripcion_funcion, l_argumentos, descripcion_retorno);
        l_lineas.addAll(l_comentarios);
        
        // Cuerpo
        String nombre_funcion = "public String toString";
        List <String> l_definicion = generarDefinicionFuncion(cnt, nombre_funcion, l_argumentos);
        l_lineas.add("@Override");
        l_lineas.addAll(l_definicion);
        l_lineas.add("{");
        
            l_lineas_tab.add("StringBuilder s = new StringBuilder();");
            l_lineas_tab.add("");
            
            // Mostramos los atributos
            String linea;
            int desplazamiento = 14;    // Por el s.append(" "
            int tab_1 = super.getNumeroColumnaPosteriorACampos(cnt, l_campos, desplazamiento);
            
            for (int i = 0; i < l_campos.size(); i++) {
                CCampo campo = l_campos.get(i);
                boolean hay_mas = i < l_campos.size() - 1;
                
                linea = "s.append(\"" + campo.nombre + ": \" ";
                linea = super.añadirEspaciosHasta(linea, tab_1);
                linea += " + " + campo.nombre;
                
                if (campo.tipo_java.equals(CCampo.TDJ_BIGDECIMAL) ||
                    campo.tipo_java.equals(CCampo.TDJ_FECHA) ||
                    campo.tipo_java.equals(CCampo.TDJ_HORA) ||
                    campo.tipo_java.equals(CCampo.TDJ_DECIMAL) ||
                    campo.tipo_java.equals(CCampo.TDJ_STRINGBUILDER)
                ) {
                    linea += ".toString()";
                }
                
                if (hay_mas) {
                    linea += " + \", \"";
                }
                linea += " + Comun.NL);";
                l_lineas_tab.add(linea);
            }
            
            l_lineas_tab.add("");
            l_lineas_tab.add("return s.toString();");
            
            l_lineas_tab = super.insertarTab(cnt, l_lineas_tab);
            l_lineas.addAll(l_lineas_tab);
        
        l_lineas.add("}");
        
        l_lineas = super.insertarTab(cnt, l_lineas);
        return l_lineas;
    }
    
    
            
            
    /**
     * Genera la clase completa de utilidad, con funciones y utilidades para manipular los registros.
     * 
     * @param cnt                               Objeto contenedor
     * 
     * @return                                  Código de la clase generado
     */
    public StringBuilder generarClaseRegistro(Contenedor cnt) {
        
        List <String> l_lineas = new ArrayList();
        
        // Imports
        List <String> l_imports = generarImports(cnt);
        l_lineas.addAll(l_imports);
        l_lineas.add("");
        l_lineas.add("");

        // Comentarios con la descripción de la clase
        List <String> l_descripcion_clase = generarDescripcionClase(cnt);
        l_lineas.addAll(l_descripcion_clase);
        
        // Definición de la clase
        String definicion_clase = generarDefinicionClase(cnt);
        l_lineas.add(definicion_clase);
        l_lineas.add("");
        
        // Definción de los atributos que contendrán cada campo
        List <String> l_atributos = generarAtributos(cnt);
        l_lineas.addAll(l_atributos);
        l_lineas.add("");
        l_lineas.add("");
        
        // Constructor
        List <String> l_constructor = generarConstructor(cnt);
        l_lineas.addAll(l_constructor);
        l_lineas.add("");
        l_lineas.add("");

        // Constructor a partir de un objeto de la misma clase
        List <String> l_constructor_copia = generarConstructorCopia(cnt);
        l_lineas.addAll(l_constructor_copia);
        l_lineas.add("");
        l_lineas.add("");

        // Función setInfo
        List <String> l_funcion_set_info = generarFuncionSetInfo(cnt);
        l_lineas.addAll(l_funcion_set_info);
        l_lineas.add("");
        l_lineas.add("");

        // Función setter y getter
        List <String> l_funciones_setter_getter = generarFuncionesSetterGetter(cnt);
        l_lineas.addAll(l_funciones_setter_getter);

        // Función setInfoBd
        List <String> l_funcion_set_info_bd = generarFuncionSetInfoBd(cnt);
        l_lineas.addAll(l_funcion_set_info_bd);
        l_lineas.add("");
        l_lineas.add("");
        
        // Función infoBd2List
        List <String> l_funcion_info_bd_2_list = generarFuncionInfoBd2List(cnt);
        l_lineas.addAll(l_funcion_info_bd_2_list);
        l_lineas.add("");
        l_lineas.add("");
        
        // Función toString
        List <String> l_funcion_to_string = generarFuncionToString(cnt);
        l_lineas.addAll(l_funcion_to_string);
        l_lineas.add("");
       
        // Fin de la clase
        l_lineas.add("}");
        l_lineas.add("");
        
        StringBuilder s_clase = super.concatenarLineas(l_lineas);
        return s_clase;
    }
    
}
