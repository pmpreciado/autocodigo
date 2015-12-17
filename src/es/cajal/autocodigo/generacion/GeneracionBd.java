/**
 * GeneracionBd.java
 *
 * Creado el 21-may-2015, 10:07:57
 */

package es.cajal.autocodigo.generacion;

import es.cajal.autocodigo.Comun;
import es.cajal.autocodigo.Contenedor;
import java.util.ArrayList;
import java.util.List;

/**
 * Funciones para generar el código de acceso a la base de datos.
 * 
 * @author pmpreciado
 */
public class GeneracionBd extends GeneracionComun {

    /** Campo con la clave primaria (solamente soportamos una clave primaria única) */
    CCampo clave_primaria;
    
    /** Campo con el objeto contenedor */
    CCampo obj_contenedor;
    
    /** Campo con el objeto secundario para el acceso a bases de datos */
    CCampo obj_secundario;

    /** Argumentos fijos que se pasan a las llamadas de consulta a la base de datos */
    String args_llamada_consulta;
    String args_llamada_consulta_param;
    
    
    /** Argumentos básicos a pasar a las funciones (Sólo contenedor + objeto secundario) */
    List <CCampo> l_args_basicos;
    
    /** Argumentos sólo clave primaria */
    List <CCampo> l_args_clave_primaria;

    /** Argumentos básicos + clave primaria */
    List <CCampo> l_args_basicos_clave_primaria;

    /** Todos los campos */
    List <CCampo> l_args_campos;

    /** Todos los campos sin la clave primaria */
    List <CCampo> l_args_campos_sin_clave_primaria;
    
    /** Todos los campos, con la clave primaria al final */
    List <CCampo> l_args_campos_clave_primaria_final;
    
    /** Argumentos básicos + todos los campos */
    List <CCampo> l_args_completos;
    
    /** Excepciones elevadas */
    List <CCampo> l_throws;

    
    /**
     * Crea la instancia de la clase.
     * 
     * @param cnt                               Objeto contenedor
     */
    public GeneracionBd(Contenedor cnt) {
        super(cnt);
        
        inicializar(cnt);
    }
    
    
    /**
     * Inicializa la primera vez algunos atributos de la clase, para que estén disponibles para todos los métodos.
     * 
     * @param cnt 
     */
    private void inicializar(Contenedor cnt) {
        // Clave primaria
        clave_primaria = super.l_campos.get(0);
        
        // Objeto contenedor
        if (cnt.formOpciones.tag_inc_contenedor_llamadas_bd) {
            obj_contenedor = super.getArgumentoContenedor(cnt);
        }

        // Objeto secundario para el acceso a bases de datos
        obj_secundario = getArgumentoSecundarioBd(cnt);
        
        // Argumentos fijos que se pasan a las llamadas de consulta a la base de datos
        if (obj_secundario != null) {
            args_llamada_consulta = obj_secundario.nombre + ", sql";
        } else {
            args_llamada_consulta = obj_contenedor.nombre + ", sql";
        }
        args_llamada_consulta_param = args_llamada_consulta + ", param";
        
        inicializarArgs();
        inicializarThrows(cnt);
    }

    
    /**
     * Inicializa los argumentos a pasar a las funciones.
     * Una vez inicializados, estarán disponibles para toda la clase.
     */
    private void inicializarArgs() {
        

        // Argumentos básicos (Sólo contenedor + objeto secundario)
        l_args_basicos = new ArrayList();
        
        if (obj_contenedor != null && obj_contenedor.nombre != null) {
            l_args_basicos.add(obj_contenedor);
        }
        if (obj_secundario != null && obj_secundario.nombre != null) {
            l_args_basicos.add(obj_secundario);
        }
        
        // Argumento clave primaria
        l_args_clave_primaria = new ArrayList();
        l_args_clave_primaria.add(clave_primaria);
        
        // Argumentos básicos + clave primaria
        l_args_basicos_clave_primaria = new ArrayList();
        l_args_basicos_clave_primaria.addAll(l_args_basicos);
        l_args_basicos_clave_primaria.add(clave_primaria);

        // Todos los campos
        l_args_campos = new ArrayList();
        l_args_campos.addAll(super.l_campos);
                
        // Todos los campos sin la clave primaria
        l_args_campos_sin_clave_primaria = new ArrayList();
        l_args_campos_sin_clave_primaria.addAll(super.l_campos);
        l_args_campos_sin_clave_primaria.remove(0);

        // Todos los campos, con la clave primaria al final
        l_args_campos_clave_primaria_final = new ArrayList();
        l_args_campos_clave_primaria_final.addAll(l_args_campos_sin_clave_primaria);
        l_args_campos_clave_primaria_final.add(clave_primaria);
        
        
        // Argumentos básicos + todos los campos
        l_args_completos = new ArrayList();
        l_args_completos.addAll(l_args_basicos);
        for (CCampo arg_campo : l_campos) {
            l_args_completos.add(arg_campo);
        }
    }

    
    /**
     * Genera uno o varios objetos de tipo campo para contener la información de las excepciones elevadas por las funciones de base de datos.
     * 
     * @param cnt                               Objeto contenedor
     * @return                                  Campos generado
     */
    private void inicializarThrows(Contenedor cnt) {
        
        l_throws = new ArrayList();
        CCampo campo_th;
        
        String excepcion_1_nombre = cnt.formOpciones.tag_excepcion_1_nombre;
        if (!Comun.vacio(excepcion_1_nombre)) {
            String excepcion_1_desc = cnt.formOpciones.tag_excepcion_1_desc;
            campo_th = new CCampo();
            campo_th.nombre = excepcion_1_nombre;
            campo_th.descripcion = excepcion_1_desc;
            l_throws.add(campo_th);
        }

        String excepcion_2_nombre = cnt.formOpciones.tag_excepcion_2_nombre;
        if (!Comun.vacio(excepcion_2_nombre)) {
            String excepcion_2_desc = cnt.formOpciones.tag_excepcion_2_desc;
            campo_th = new CCampo();
            campo_th.nombre = excepcion_2_nombre;
            campo_th.descripcion = excepcion_2_desc;
            l_throws.add(campo_th);
        }
    }

    
    /**
     * Genera un objeto de tipo campo para contener la información del objeto secundario usado en las funciones de base de datos.
     * 
     * @param cnt                               Objeto contenedor
     * @return                                  Campo generado
     *                                          'null' si no se va a usar el objeto secundario
     */
    private CCampo getArgumentoSecundarioBd(Contenedor cnt) {
        String clase_secundario = cnt.formOpciones.tag_objeto_secundario_bd_clase;
        String nombre_secundario = cnt.formOpciones.tag_objeto_secundario_bd_nombre;
        String desc_secundario = cnt.formOpciones.tag_objeto_secundario_bd_desc;
        
        if (clase_secundario == null || nombre_secundario == null) {
            return null;
        }
        
        CCampo arg_secundario = new CCampo(nombre_secundario, "", desc_secundario, clase_secundario);
        return arg_secundario;
    }
    
    
    
    /**
     * Genera las líneas código con los imports de la clase.
     * 
     * @param cnt                               Objeto contenedor
     * 
     * @return                                  Líneas de código generadas
     */
    private List <String> generarImports(Contenedor cnt) {
        String tags_imports_bd = cnt.formOpciones.tag_imports_bd;
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
        l_lineas.add(" * Métodos de acceso a la base de datos relacionados con la tabla '" + nombre_tabla + "'.");
        l_lineas.add(" * ");
        l_lineas.add(" * Estos son los campos de la tabla:");
        l_lineas.add(" *   <ul>");
        
        for (int i = 0; i < l_campos.size(); i++) {
            CCampo campo = l_campos.get(i);
            
            String linea = " *     <li> ";
            String s_indice_campo = Integer.toString(i);
            s_indice_campo = Comun.justificarIzquierda(s_indice_campo, 3);
            linea += s_indice_campo + " ";
            linea += campo.nombre;
            if (campo.descripcion != null) {
                linea += " ";
                linea = super.añadirEspaciosHasta(linea, cnt.formOpciones.formato_columna_comentarios - 1);
                linea += campo.descripcion;
            }
            
            l_lineas.add(linea);
        }
        
        l_lineas.add(" *   </ul>");
        if (!Comun.vacio(cnt.formOpciones.tag_author)) {
            l_lineas.add(" * ");
            l_lineas.add(" * @author " + cnt.formOpciones.tag_author);
        }
        l_lineas.add(" */");
        
        return l_lineas;
    }
    
    
    /**
     * Genera el nombre de la clase de acceso a la base de datos a partir del nombre de la tabla.
     * El nombre de la clase se genera en función del nombre de la tabla. Se sigue esta regla:
     * 
     *      Tabla                   Nombre de la clase
     *      ------------------      ------------------
     *      clientes                DbClientes
     *      inv_procesos            DbInvProcesos
     * 
     * @param nombre_tabla                      Nombre de la tabla
     * 
     * @return                                  Nombre de la clase
     */
    public static String generarNombreClase(String nombre_tabla) {
        
        String nombre_clase = GeneracionComun.getCamelCase(nombre_tabla);
        nombre_clase = "Db" + nombre_clase;
        return nombre_clase;
    }
    
    
    /**
     * Genera el nombre de la clase.
     * El nombre de la clase se genera en función del nombre de la tabla. Se sigue esta regla:
     * 
     *      Tabla                   Nombre de la clase
     *      ------------------      ------------------
     *      clientes                DbClientes
     *      inv_procesos            DbInvProcesos
     * 
     * @return                                  Nombre de la clase
     */
    private String generarNombreClase() {
        
        String nombre_clase = generarNombreClase(nombre_tabla);
        return nombre_clase;
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
        
        String nombre_clase = generarNombreClase();
        
        String c = "public class " + nombre_clase;
        
        String ex = cnt.formOpciones.tag_clase_extends_db;
        if (!Comun.vacio(ex)) {
            c += " extends " + ex;
        }
        
        c += " {";
        
        return c;
    }
    
    
    /**
     * Genera la línea de código para definir la constante que contiene el nombre de la tabla.
     * La línea tiene una sintaxis similar a esta:
     *      public static final String NOMBRE_TABLA = "clientes";
     * 
     * @return                                  Código generado
     */
    private String generarConstanteNombreTabla() {
        
        String c = tab + "public static final String NOMBRE_TABLA = \"" + nombre_tabla + "\";";
        return c;
    }
    
    
    /**
     * Genera las líneas de código que inicializan el objeto Parametros e insertan en el los parámetros de la consulta SQL.
     * 
     * @param l_campos                          Campos a introducir en el objeto Parametros
     * 
     * @return                                  Líneas generadas
     */
    private List <String> getBloqueParametros(Contenedor cnt, List <CCampo> l_campos) {
        List <String> l_lineas = new ArrayList();
        
        String linea;
        l_lineas.add("Parametros param = new Parametros();");
        for (CCampo campo : l_campos) {
            linea = "param.add(" + campo.nombre + ");";
            l_lineas.add(linea);
        }
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
        
        // Comentarios
        String descripcion_funcion = "Crea una nueva instancia de la clase.";
        
        List <CCampo> l_argumentos = null;
        List <String> l_descripcion_retorno = null;
        List <String> l_comentarios = generarComentariosFuncion(cnt, descripcion_funcion, l_argumentos, l_descripcion_retorno);
        l_lineas.addAll(l_comentarios);
        
        // Cuerpo
        String nombre_funcion = "public " + this.generarNombreClase();
        List <String> l_definicion = generarDefinicionFuncion(cnt, nombre_funcion, l_argumentos);
        l_lineas.addAll(l_definicion);
        
        insertarAlFinal(" {", l_lineas);
        l_lineas.add("}");
        
        l_lineas = super.insertarTab(cnt, l_lineas);
        return l_lineas;
    }
    
    
    /**
     * Genera el código de la función getRegistro().
     * 
     * @param cnt                               Objeto contenedor
     * 
     * @return                                  Líneas generadas
     */
    private List <String> generarFuncionGetRegistro(Contenedor cnt) {
        
        List <String> l_lineas = new ArrayList();
        List <String> l_lineas_tab = new ArrayList();
        
        // Comentarios
        String descripcion_funcion = "Obtiene un registro de la tabla.";
        List <CCampo> l_argumentos = this.l_args_basicos_clave_primaria;
        String descripcion_retorno = "String [] con la información";
        List <String> l_comentarios = generarComentariosFuncion(cnt, descripcion_funcion, l_argumentos, descripcion_retorno, l_throws);
        l_lineas.addAll(l_comentarios);
        
        // Cuerpo
        String nombre_funcion = "public String [] getRegistro";
        List <String> l_definicion = generarDefinicionFuncion(cnt, nombre_funcion, l_argumentos, l_throws);
        l_lineas.addAll(l_definicion);
        l_lineas.add("{");
        
            // Bloque Parametros
            List <String> l_parametros = getBloqueParametros(cnt, l_args_clave_primaria);
            l_lineas_tab.addAll(l_parametros);
            l_lineas_tab.add("");
        
            // Consulta SQL
            l_lineas_tab.add("String sql = \"SELECT * FROM \" + NOMBRE_TABLA + \" \" +");
            l_lineas_tab.add(tab + tab + "\"WHERE " + clave_primaria.nombre + " = ?\";");
            l_lineas_tab.add("");
            
            // Resultado
            //l_lineas_tab.add("ResultSet rs = consulta(" + args_llamada_consulta_param + ");");
            //l_lineas_tab.add("String [] result = getString(rs);");
            l_lineas_tab.add("String [] result = super.getString(" + args_llamada_consulta_param + ");");
            l_lineas_tab.add("");
            
            // Retorno
            l_lineas_tab.add("return result;");
            
            // Tabulamos las lineas_tab y las añadimos a las líneas de la función
            l_lineas_tab = super.insertarTab(cnt, l_lineas_tab);
            l_lineas.addAll(l_lineas_tab);
            
        l_lineas.add("}");
        
        l_lineas = super.insertarTab(cnt, l_lineas);
        return l_lineas;
    }
    
    

    /**
     * Genera el código de la función getRegistros().
     * 
     * @param cnt                               Objeto contenedor
     * 
     * @return                                  Líneas generadas
     */
    private List <String> generarFuncionGetRegistros(Contenedor cnt) {
        
        List <String> l_lineas = new ArrayList();
        List <String> l_lineas_tab = new ArrayList();
        
        // Comentarios
        String descripcion_funcion = "Obtiene todos los registros de la tabla.";
        List <CCampo> l_argumentos = this.l_args_basicos;
        String descripcion_retorno = "String [][] con la información";
        List <String> l_comentarios = generarComentariosFuncion(cnt, descripcion_funcion, l_argumentos, descripcion_retorno, l_throws);
        l_lineas.addAll(l_comentarios);
        
        // Cuerpo
        String nombre_funcion = "public String [][] getRegistros";
        List <String> l_definicion = generarDefinicionFuncion(cnt, nombre_funcion, l_argumentos, l_throws);
        l_lineas.addAll(l_definicion);
        l_lineas.add("{");
        
            // Consulta SQL
            l_lineas_tab.add("String sql = \"SELECT * FROM \" + NOMBRE_TABLA + \" \" +");
            l_lineas_tab.add(tab + tab + "\"ORDER BY " + clave_primaria.nombre + "\";");
            l_lineas_tab.add("");
            
            // Resultado
            //l_lineas_tab.add("ResultSet rs = consulta(" + args_llamada_consulta + ");");
            //l_lineas_tab.add("String [][] result = getStrings(rs);");
            l_lineas_tab.add("String [][] result = super.getStrings(" + args_llamada_consulta + ");");
            l_lineas_tab.add("");
            
            // Retorno
            l_lineas_tab.add("return result;");
            
            // Tabulamos las lineas_tab y las añadimos a las líneas de la función
            l_lineas_tab = super.insertarTab(cnt, l_lineas_tab);
            l_lineas.addAll(l_lineas_tab);
            
        l_lineas.add("}");
        
        l_lineas = super.insertarTab(cnt, l_lineas);
        return l_lineas;
    }
    
    
    /**
     * Genera el código de la función existe().
     * 
     * @param cnt                               Objeto contenedor
     * 
     * @return                                  Líneas generadas
     */
    private List <String> generarFuncionExiste(Contenedor cnt) {
        
        List <String> l_lineas = new ArrayList();
        List <String> l_lineas_tab = new ArrayList();
        
        // Comentarios
        String descripcion_funcion = "Comprueba si existe el registro cuyo identificador se suministra.";
        List <CCampo> l_argumentos = this.l_args_basicos_clave_primaria;
        List <String> l_descripcion_retorno = new ArrayList();
        l_descripcion_retorno.add("'true' si existe");
        l_descripcion_retorno.add("'false' si no existe");

        List <String> l_comentarios = generarComentariosFuncion(cnt, descripcion_funcion, l_argumentos, l_descripcion_retorno, l_throws);
        l_lineas.addAll(l_comentarios);
        
        // Cuerpo
        String nombre_funcion = "public boolean existe";
        List <String> l_definicion = generarDefinicionFuncion(cnt, nombre_funcion, l_argumentos, l_throws);
        l_lineas.addAll(l_definicion);
        l_lineas.add("{");
        
            // Bloque Parametros
            List <String> l_parametros = getBloqueParametros(cnt, l_args_clave_primaria);
            l_lineas_tab.addAll(l_parametros);
            l_lineas_tab.add("");
        
            // Consulta SQL
            l_lineas_tab.add("String sql = \"SELECT COUNT(*) FROM \" + NOMBRE_TABLA + \" \" +");
            l_lineas_tab.add(tab + tab + "\"WHERE " + clave_primaria.nombre + " = ?\";");
            l_lineas_tab.add("");
            
            // Resultado
            //l_lineas_tab.add("ResultSet rs = consulta(" + args_llamada_consulta_param + ");");
            l_lineas_tab.add("int valor = super.getValor(" + args_llamada_consulta_param + ");");
            l_lineas_tab.add("boolean existe = valor > 0;");
            l_lineas_tab.add("");
            
            // Retorno
            l_lineas_tab.add("return existe;");
            
            // Tabulamos las lineas_tab y las añadimos a las líneas de la función
            l_lineas_tab = super.insertarTab(cnt, l_lineas_tab);
            l_lineas.addAll(l_lineas_tab);
            
        l_lineas.add("}");
        
        l_lineas = super.insertarTab(cnt, l_lineas);
        return l_lineas;
    }
    

    /**
     * Genera el código de la función getNextId().
     * 
     * @param cnt                               Objeto contenedor
     * 
     * @return                                  Líneas generadas
     */
    private List <String> generarFuncionGetNextId(Contenedor cnt) {
        
        List <String> l_lineas = new ArrayList();
        List <String> l_lineas_tab = new ArrayList();
        
        // Comentarios
        String descripcion_funcion = "Obtiene el siguiente identificador disponible para un nuevo registro en la tabla.";
        List <CCampo> l_argumentos = this.l_args_basicos;
        String descripcion_retorno = "Siguiente valor disponible";
        List <String> l_comentarios = generarComentariosFuncion(cnt, descripcion_funcion, l_argumentos, descripcion_retorno, l_throws);
        l_lineas.addAll(l_comentarios);
        
        // Cuerpo
        String nombre_funcion = "public int getNextId";
        List <String> l_definicion = generarDefinicionFuncion(cnt, nombre_funcion, l_argumentos, l_throws);
        l_lineas.addAll(l_definicion);
        l_lineas.add("{");
        
            // Llamada a función getNextId de la superclase
            String arg_1 = "";
            if (obj_secundario != null && obj_secundario.nombre != null) {
                arg_1 = obj_secundario.nombre + ", ";
            }
            l_lineas_tab.add("int siguiente = getNextIdMySql(" + arg_1 + " NOMBRE_TABLA, \"" + clave_primaria.nombre + "\");");
            
            // Retorno
            l_lineas_tab.add("return siguiente;");
            
            // Tabulamos las lineas_tab y las añadimos a las líneas de la función
            l_lineas_tab = super.insertarTab(cnt, l_lineas_tab);
            l_lineas.addAll(l_lineas_tab);
            
        l_lineas.add("}");
        
        l_lineas = super.insertarTab(cnt, l_lineas);
        return l_lineas;
    }
    
    
    
    /**
     * Genera el código de la función alta().
     * 
     * @param cnt                               Objeto contenedor
     * 
     * @return                                  Líneas generadas
     */
    private List <String> generarFuncionAlta(Contenedor cnt) {
        
        List <String> l_lineas = new ArrayList();
        List <String> l_lineas_tab = new ArrayList();
        
        // Comentarios
        String descripcion_funcion = "Da de alta un nuevo registro en la tabla.";
        List <CCampo> l_argumentos = this.l_args_completos;
        String descripcion_retorno = "Número de registros afectados tras la consulta";
        List <String> l_comentarios = generarComentariosFuncion(cnt, descripcion_funcion, l_argumentos, descripcion_retorno, l_throws);
        l_lineas.addAll(l_comentarios);
        
        // Cuerpo
        String nombre_funcion = "public int alta";
        List <String> l_definicion = generarDefinicionFuncion(cnt, nombre_funcion, l_argumentos, l_throws);
        l_lineas.addAll(l_definicion);
        l_lineas.add("{");
        
            // Bloque Parametros
            List <String> l_parametros = getBloqueParametros(cnt, l_args_campos);
            l_lineas_tab.addAll(l_parametros);
            l_lineas_tab.add("");
        
            // Consulta SQL
            List <String> l_lineas_nombres_campos = generarListaNombresCampos(cnt, l_args_campos);
            l_lineas_tab.add("String sql = \"INSERT INTO \" + NOMBRE_TABLA + \" \" +");
            for (int i = 0; i < l_lineas_nombres_campos.size(); i++) {
                String linea_nombres = l_lineas_nombres_campos.get(i);
                if (i == 0) {
                    linea_nombres = "(" + linea_nombres;
                }
                if (i == l_lineas_nombres_campos.size() - 1) {
                    linea_nombres = linea_nombres + ")";
                }
                
                l_lineas_tab.add(tab + tab + "\"" + linea_nombres + "\" +");
            }
            l_lineas_tab.add(tab + tab + "\"VALUES \" + param.getCadenaValues();");
            l_lineas_tab.add("");
            
            // Resultado
            l_lineas_tab.add("int result = super.consultaActualizacion(" + args_llamada_consulta_param + ");");
            
            // Retorno
            l_lineas_tab.add("return result;");
            
            // Tabulamos las lineas_tab y las añadimos a las líneas de la función
            l_lineas_tab = super.insertarTab(cnt, l_lineas_tab);
            l_lineas.addAll(l_lineas_tab);
            
        l_lineas.add("}");
        
        l_lineas = super.insertarTab(cnt, l_lineas);
        return l_lineas;
    }
            
    
    /**
     * Genera el código de la función update().
     * 
     * @param cnt                               Objeto contenedor
     * 
     * @return                                  Líneas generadas
     */
    private List <String> generarFuncionUpdate(Contenedor cnt) {
        
        List <String> l_lineas = new ArrayList();
        List <String> l_lineas_tab = new ArrayList();
        
        // Comentarios
        String descripcion_funcion = "Actualiza el registro cuyo identificador se suministra.";
        List <CCampo> l_argumentos = this.l_args_completos;
        String descripcion_retorno = "Número de registros afectados tras la consulta";
        List <String> l_comentarios = generarComentariosFuncion(cnt, descripcion_funcion, l_argumentos, descripcion_retorno, l_throws);
        l_lineas.addAll(l_comentarios);
        
        // Cuerpo
        String nombre_funcion = "public int update";
        List <String> l_definicion = generarDefinicionFuncion(cnt, nombre_funcion, l_argumentos, l_throws);
        l_lineas.addAll(l_definicion);
        l_lineas.add("{");
        
            // Bloque Parametros
            List <String> l_parametros = getBloqueParametros(cnt, l_args_campos_clave_primaria_final);
            l_lineas_tab.addAll(l_parametros);
            l_lineas_tab.add("");
        
            // Consulta SQL
            List <String> l_lineas_nombres_campos = generarListaNombresCampos(cnt, l_campos);
            l_lineas_tab.add("String sql = \"UPDATE \" + NOMBRE_TABLA + \" \" +");
            l_lineas_tab.add(tab + tab + "\"SET \" +");
            
            for (int i = 0; i < l_args_campos_sin_clave_primaria.size(); i++) {
                
                boolean hay_mas = i < l_args_campos_sin_clave_primaria.size() - 1;
                CCampo campo = l_args_campos_sin_clave_primaria.get(i);
                
                String linea = tab + tab + tab + tab + "\"" + campo.nombre + " = ?";
                if (hay_mas) {
                    linea += ",";
                }
                linea += " \" +";
                
                l_lineas_tab.add(linea);
            }
            l_lineas_tab.add(tab + tab + "\"WHERE " + clave_primaria.nombre + " = ?\";");
            
            // Resultado
            l_lineas_tab.add("int result = super.consultaActualizacion(" + args_llamada_consulta_param + ");");
            l_lineas_tab.add("");
            
            // Retorno
            l_lineas_tab.add("return result;");
            
            // Tabulamos las lineas_tab y las añadimos a las líneas de la función
            l_lineas_tab = super.insertarTab(cnt, l_lineas_tab);
            l_lineas.addAll(l_lineas_tab);
            
        l_lineas.add("}");
        
        l_lineas = super.insertarTab(cnt, l_lineas);
        return l_lineas;
    }
    
    
    /**
     * Genera el código de la función setValor().
     * 
     * @param cnt                               Objeto contenedor
     * 
     * @return                                  Líneas generadas
     */
    private List <String> generarFuncionSetValor(Contenedor cnt) {
        
        List <String> l_lineas = new ArrayList();
        List <String> l_lineas_tab = new ArrayList();

        if (l_campos.size() < 2) {
            return l_lineas;
        }
        CCampo segundo_campo = l_campos.get(1);
        
        // Comentarios
        String descripcion_funcion = "Establece el valor del campo " + segundo_campo.nombre + " para el registro cuyo identificador se suministra.";
        List <CCampo> l_argumentos = this.l_args_basicos;
        l_argumentos.add(segundo_campo);
        l_argumentos.add(clave_primaria);
        
        String descripcion_retorno = "Número de registros afectados tras la consulta";
        List <String> l_comentarios = generarComentariosFuncion(cnt, descripcion_funcion, l_argumentos, descripcion_retorno, l_throws);
        l_lineas.addAll(l_comentarios);
        
        // Cuerpo
        String nombre_funcion = "public int setValor";
        List <String> l_definicion = generarDefinicionFuncion(cnt, nombre_funcion, l_argumentos, l_throws);
        l_lineas.addAll(l_definicion);
        l_lineas.add("{");
        
            // Bloque Parametros
            List <CCampo> l_argumentos_parametros = new ArrayList();
            l_argumentos_parametros.add(segundo_campo);
            l_argumentos_parametros.add(clave_primaria);
            List <String> l_parametros = getBloqueParametros(cnt, l_argumentos_parametros);
            l_lineas_tab.addAll(l_parametros);
            l_lineas_tab.add("");
        
            // Consulta SQL
            l_lineas_tab.add("String sql = \"UPDATE \" + NOMBRE_TABLA + \" \" +");
            l_lineas_tab.add(tab + tab + "\"SET " + segundo_campo.nombre + " = ? \" + ");
            l_lineas_tab.add(tab + tab + "\"WHERE " + clave_primaria.nombre + " = ?\";");
            l_lineas_tab.add("");
            
            // Resultado
            l_lineas_tab.add("int result = super.consultaActualizacion(" + args_llamada_consulta_param + ");");
            
            // Retorno
            l_lineas_tab.add("return result;");
            
            // Tabulamos las lineas_tab y las añadimos a las líneas de la función
            l_lineas_tab = super.insertarTab(cnt, l_lineas_tab);
            l_lineas.addAll(l_lineas_tab);
            
        l_lineas.add("}");
        
        l_lineas = super.insertarTab(cnt, l_lineas);
        return l_lineas;
    }
    
    
    /**
     * Genera el código de la función delete().
     * 
     * @param cnt                               Objeto contenedor
     * 
     * @return                                  Líneas generadas
     */
    private List <String> generarFuncionDelete(Contenedor cnt) {
        
        List <String> l_lineas = new ArrayList();
        List <String> l_lineas_tab = new ArrayList();
        
        // Comentarios
        String descripcion_funcion = "Borra un registro dado por su identificador.";
        List <CCampo> l_argumentos = this.l_args_basicos_clave_primaria;
        String descripcion_retorno = "Número de registros afectados";

        List <String> l_comentarios = generarComentariosFuncion(cnt, descripcion_funcion, l_argumentos, descripcion_retorno, l_throws);
        l_lineas.addAll(l_comentarios);
        
        // Cuerpo
        String nombre_funcion = "public int delete";
        List <String> l_definicion = generarDefinicionFuncion(cnt, nombre_funcion, l_argumentos, l_throws);
        l_lineas.addAll(l_definicion);
        l_lineas.add("{");
        
            // Bloque Parametros
            List <String> l_parametros = getBloqueParametros(cnt, l_args_clave_primaria);
            l_lineas_tab.addAll(l_parametros);
            l_lineas_tab.add("");
        
            // Consulta SQL
            l_lineas_tab.add("String sql = \"DELETE FROM \" + NOMBRE_TABLA + \" \" + ");
            l_lineas_tab.add(tab + tab + "\"WHERE " + clave_primaria.nombre + " = ?\";");
            l_lineas_tab.add("");
            
            // Resultado
            l_lineas_tab.add("int result = consultaActualizacion(" + args_llamada_consulta_param + ");");
            
            // Retorno
            l_lineas_tab.add("return result;");
            
            // Tabulamos las lineas_tab y las añadimos a las líneas de la función
            l_lineas_tab = super.insertarTab(cnt, l_lineas_tab);
            l_lineas.addAll(l_lineas_tab);
            
        l_lineas.add("}");
        
        l_lineas = super.insertarTab(cnt, l_lineas);
        return l_lineas;
    }
    
    
    
    /**
     * Genera la clase completa con los atributos y métodos para el acceso a la base de datos.
     * 
     * @param cnt                               Objeto contenedor
     * 
     * @return                                  Código de la clase generado
     */
    public StringBuilder generarClaseAccesoBd(Contenedor cnt) {
        
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
        
        // Constante con el nombre de la tabla
        String constante_nombre_tabla = generarConstanteNombreTabla();
        l_lineas.add(constante_nombre_tabla);
        l_lineas.add("");
        l_lineas.add("");
        
        // Constructor
        List <String> l_constructor = generarConstructor(cnt);
        l_lineas.addAll(l_constructor);
        l_lineas.add("");
        l_lineas.add("");
        
        // Función generarGetRegistro(...)
        List <String> l_funcion_get_registro = generarFuncionGetRegistro(cnt);
        l_lineas.addAll(l_funcion_get_registro);
        l_lineas.add("");
        l_lineas.add("");
        
        // Función generarGetRegistros(...)
        List <String> l_funcion_get_registros = generarFuncionGetRegistros(cnt);
        l_lineas.addAll(l_funcion_get_registros);
        l_lineas.add("");
        l_lineas.add("");

        // Función existe(...)
        List <String> l_funcion_existe = generarFuncionExiste(cnt);
        l_lineas.addAll(l_funcion_existe);
        l_lineas.add("");
        l_lineas.add("");

        // Función getNextId(...)
        List <String> l_funcion_get_next_id = generarFuncionGetNextId(cnt);
        l_lineas.addAll(l_funcion_get_next_id);
        l_lineas.add("");
        l_lineas.add("");
        
        // Función alta(...)
        List <String> l_funcion_alta = generarFuncionAlta(cnt);
        l_lineas.addAll(l_funcion_alta);
        l_lineas.add("");
        l_lineas.add("");

        // Funcion update(...)
        List <String> l_funcion_update = generarFuncionUpdate(cnt);
        l_lineas.addAll(l_funcion_update);
        l_lineas.add("");
        l_lineas.add("");

        // Funcion setValorCampo(...)
        List <String> l_funcion_set_campo = generarFuncionSetValor(cnt);
        l_lineas.addAll(l_funcion_set_campo);
        l_lineas.add("");
        l_lineas.add("");

        // Funcion delete(...)
        List <String> l_funcion_delete = generarFuncionDelete(cnt);
        l_lineas.addAll(l_funcion_delete);
        l_lineas.add("");
        
        // Fin de la clase
        l_lineas.add("}");
        l_lineas.add("");
        
        StringBuilder s_clase = super.concatenarLineas(l_lineas);
        return s_clase;
    }
    
}
