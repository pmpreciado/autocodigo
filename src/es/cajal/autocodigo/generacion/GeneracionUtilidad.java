/**
 * GeneracionUtilidad.java
 *
 * Creado el 25-may-2015, 19:53:16
 */

package es.cajal.autocodigo.generacion;

import es.cajal.autocodigo.Comun;
import es.cajal.autocodigo.Contenedor;
import java.util.ArrayList;
import java.util.List;

/**
 * Funciones para generar el código de la clase con utilidades para manipular los registros.
 * 
 * @author pmpreciado
 */
public class GeneracionUtilidad extends GeneracionComun {

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

    /** Argumentos básicos a pasar a las funciones (Sólo contenedor (opcional) + objeto secundario) */
    List <CCampo> l_args_basicos_contenedor_opcional;
    
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


    /** Nombre de la clase usada para el acceso al registro */
    String nombre_clase_registro;
    
    /** Nombre de la clase usada para el acceso a la base de datos */
    String nombre_clase_bd;

    /** Nombre de la instancia de la clase usada para el acceso al registro */
    String nombre_instancia_clase_registro;
    
    /** Nombre del objeto para la lista de objetos de la clase usada para el acceso al registro */
    String nombre_lista_clase_registro;

    /** Nombre de la instancia de la clase usada para el acceso a la base de datos */
    String nombre_instancia_clase_bd;

    
    
    /**
     * Crea la instancia de la clase.
     * 
     * @param cnt                               Objeto contenedor
     */
    public GeneracionUtilidad(Contenedor cnt) {
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
        obj_contenedor = super.getArgumentoContenedor(cnt);

        // Objeto secundario para el acceso a bases de datos
        obj_secundario = getArgumentoSecundarioBd(cnt);
        
        // Argumentos fijos que se pasan a las llamadas de consulta a la base de datos
        if (obj_secundario != null) {
            args_llamada_consulta = obj_secundario.nombre + ", sql";
        } else {
            args_llamada_consulta = obj_contenedor.nombre + ", sql";
        }
        args_llamada_consulta_param = args_llamada_consulta + ", param";
        
        inicializarArgs(cnt);
        inicializarThrows(cnt);
        
        nombre_clase_registro = cnt.formAutoCodigo.getNombreClaseAccesoRegistro();
        nombre_clase_bd = GeneracionBd.generarNombreClase(nombre_tabla);
        nombre_instancia_clase_registro = generarNombreInstanciaRegistro();
        nombre_lista_clase_registro = generarNombreListaInstanciaRegistro();
        nombre_instancia_clase_bd = generarNombreInstanciaBd();
    }


    
    
    /**
     * Inicializa los argumentos a pasar a las funciones.
     * Una vez inicializados, estarán disponibles para toda la clase.
     * 
     * @param cnt                               Objeto contenedor
     */
    private void inicializarArgs(Contenedor cnt) {
        
        // Argumentos básicos (Sólo contenedor + objeto secundario)
        l_args_basicos = new ArrayList();
        l_args_basicos_contenedor_opcional = new ArrayList();
        
        if (obj_contenedor != null && obj_contenedor.nombre != null) {
            l_args_basicos.add(obj_contenedor);

            if (cnt.formOpciones.tag_inc_contenedor_llamadas_bd) {
                l_args_basicos_contenedor_opcional.add(obj_contenedor);
            }
        }
        if (obj_secundario != null && obj_secundario.nombre != null) {
            l_args_basicos.add(obj_secundario);
            l_args_basicos_contenedor_opcional.add(obj_secundario);
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
     * Genera un objeto de tipo CCampo para contener al objeto de acceso al registro.
     * 
     * @return                                  Campo generado
     */
    protected CCampo getArgumentoObjetoAccesoRegistro() {
        String desc = "Objeto de la clase " + nombre_clase_registro;
        CCampo arg_contenedor = new CCampo(nombre_instancia_clase_registro, "", desc, nombre_clase_registro);
        return arg_contenedor;
    }
    
    
    /**
     * Genera las líneas código con los imports de la clase.
     * 
     * @param cnt                               Objeto contenedor
     * 
     * @return                                  Líneas de código generadas
     */
    private List <String> generarImports(Contenedor cnt) {
        String tags_imports_bd = cnt.formOpciones.tag_imports_utilidad;
        List <String> l_imports = Comun.split(tags_imports_bd, Comun.NL);
        return l_imports;
    }
    
    
    /**
     * Genera el nombre de la instancia de la clase de acceso al registro.
     * El nombre es generado a partir del de la tabla. Se sigue esta regla:
     * 
     *      Tabla                   Nombre de la instancia
     *      ------------------      ------------------
     *      clientes                cliente
     *      inv_procesos            inv_proceso
     *      perfil                  perfil
     * 
     * @return                                  Nombre de la clase
     */
    public String generarNombreInstanciaRegistro() {
        
        String nombre_instancia =  nombre_tabla;
        if (nombre_instancia.endsWith(",")) {
            nombre_instancia = Comun.eliminarUltimos(nombre_instancia, 1);
        }
        
        return nombre_instancia;
    }
    
    
    /**
     * Genera el nombre de la lista de objetos de la clase de acceso al registro.
     * El nombre es generado a partir del de la tabla. Se sigue esta regla:
     * 
     *      Tabla                   Nombre de la instancia
     *      ------------------      ------------------
     *      clientes                l_cliente
     *      inv_procesos            l_inv_proceso
     *      perfil                  l_perfil
     * 
     * @return                                  Nombre de la clase
     */
    public String generarNombreListaInstanciaRegistro() {
        
        String nombre_lista =  nombre_tabla;
        nombre_lista = "l_" + nombre_lista;
        return nombre_lista;
    }
    
    
    
    /**
     * Genera el nombre de la instancia de la clase de acceso a la base de datos.
     * El nombre es generado a partir del de la tabla. Se sigue esta regla:
     * 
     *      Tabla                   Nombre de la instancia
     *      ------------------      ------------------
     *      clientes                db_clientes
     *      inv_procesos            db_inv_procesos
     * 
     * @return                                  Nombre de la clase
     */
    public String generarNombreInstanciaBd() {
        
        String nombre_instancia = "db_" + nombre_tabla;
        return nombre_instancia;
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
        l_lineas.add(" * Funciones y utilidades para gestionar la información de la tabla '" + nombre_tabla + "'.");
        if (!Comun.vacio(cnt.formOpciones.tag_author)) {
            l_lineas.add(" * ");
            l_lineas.add(" * @author " + cnt.formOpciones.tag_author);
        }
        l_lineas.add(" */");
        
        return l_lineas;
    }
    
    
    /**
     * Genera el nombre de la clase.
     * El nombre de la clase se genera en función del nombre de la tabla. Se sigue esta regla:
     * 
     *      Tabla                   Nombre de la clase
     *      ------------------      ------------------
     *      clientes                Clientes
     *      inv_procesos            InvProcesos
     * 
     * @return                                  Nombre de la clase
     */
    private String generarNombreClase() {
        
        String nombre_clase = getCamelCase(nombre_tabla);
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
        
        c += " {";
        
        return c;
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
        String descripcion_funcion = "Obtiene la información de un registro dado.";
        List <CCampo> l_argumentos = this.l_args_basicos_clave_primaria;
        String descripcion_retorno = "Objeto de clase " + nombre_clase_registro + " con la información";
        List <String> l_comentarios = generarComentariosFuncion(cnt, descripcion_funcion, l_argumentos, descripcion_retorno, l_throws);
        l_lineas.addAll(l_comentarios);
        
        // Cuerpo
        String nombre_funcion = "public " + nombre_clase_registro + " getRegistro";
        List <String> l_definicion = generarDefinicionFuncion(cnt, nombre_funcion, l_argumentos, l_throws);
        l_lineas.addAll(l_definicion);
        l_lineas.add("{");
        
            String lista_argumentos = CCampo.getListaNombresCampos(l_args_basicos_contenedor_opcional);
                    
            l_lineas_tab.add(nombre_clase_bd + " " + nombre_instancia_clase_bd + " = new " + nombre_clase_bd + "();");
            l_lineas_tab.add("String [] info_bd = " + nombre_instancia_clase_bd + ".getRegistro(" + lista_argumentos + ");");
            l_lineas_tab.add("if (info_bd == null || info_bd.length == 0) {");
            l_lineas_tab.add(tab + "return null;");
            l_lineas_tab.add("}");
            l_lineas_tab.add("");
            
            l_lineas_tab.add(nombre_clase_registro + " " + nombre_instancia_clase_registro + " = new " + nombre_clase_registro + "();");
            l_lineas_tab.add(nombre_instancia_clase_registro + ".setInfoBd(info_bd);");
            l_lineas_tab.add("return " + nombre_instancia_clase_registro + ";");
            
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
        String descripcion_funcion = "Obtiene la información de todos los registros.";
        List <CCampo> l_argumentos = this.l_args_basicos;
        String descripcion_retorno = "Lista con los registros";
        List <String> l_comentarios = generarComentariosFuncion(cnt, descripcion_funcion, l_argumentos, descripcion_retorno, l_throws);
        l_lineas.addAll(l_comentarios);
        
        // Cuerpo
        String nombre_funcion = "public List <" + nombre_clase_registro + "> getRegistros";
        List <String> l_definicion = generarDefinicionFuncion(cnt, nombre_funcion, l_argumentos, l_throws);
        l_lineas.addAll(l_definicion);
        l_lineas.add("{");
        
            String lista_argumentos_basicos = CCampo.getListaNombresCampos(this.l_args_basicos_contenedor_opcional);
                    
            l_lineas_tab.add(nombre_clase_bd + " " + nombre_instancia_clase_bd + " = new " + nombre_clase_bd + "();");
            l_lineas_tab.add("String [][] info_bd = " + nombre_instancia_clase_bd + ".getRegistros(" + lista_argumentos_basicos + ");");
            l_lineas_tab.add("List <" + nombre_clase_registro + "> " + nombre_lista_clase_registro + " = " + nombre_clase_registro + ".infoBd2List(info_bd);");
            l_lineas_tab.add("return " + nombre_lista_clase_registro + ";");
            
            // Tabulamos las lineas_tab y las añadimos a las líneas de la función
            l_lineas_tab = super.insertarTab(cnt, l_lineas_tab);
            l_lineas.addAll(l_lineas_tab);
            
        l_lineas.add("}");
        
        l_lineas = super.insertarTab(cnt, l_lineas);
        return l_lineas;
    }
    
    
//    /**
//     * Genera el código de la función existe().
//     * 
//     * @param cnt                               Objeto contenedor
//     * 
//     * @return                                  Líneas generadas
//     */
//    private List <String> generarFuncionExiste(Contenedor cnt) {
//        
//        List <String> l_lineas = new ArrayList();
//        List <String> l_lineas_tab = new ArrayList();
//        
//        // Comentarios
//        String descripcion_funcion = "Comprueba si existe el registro cuyo identificador se suministra.";
//        List <CCampo> l_argumentos = this.l_args_basicos_clave_primaria;
//        List <String> l_descripcion_retorno = new ArrayList();
//        l_descripcion_retorno.add("'true' si existe");
//        l_descripcion_retorno.add("'false' si no existe");
//
//        List <String> l_comentarios = generarComentariosFuncion(cnt, descripcion_funcion, l_argumentos, l_descripcion_retorno, l_throws);
//        l_lineas.addAll(l_comentarios);
//        
//        // Cuerpo
//        String nombre_funcion = "public boolean existe";
//        List <String> l_definicion = generarDefinicionFuncion(cnt, nombre_funcion, l_argumentos, l_throws);
//        l_lineas.addAll(l_definicion);
//        l_lineas.add("{");
//        
//            // Consulta SQL
//            l_lineas_tab.add("String sql = \"SELECT COUNT(*) FROM \" + NOMBRE_TABLA + \" \" +");
//            l_lineas_tab.add(tab + tab + "\"WHERE " + clave_primaria.nombre + " = ?\";");
//            l_lineas_tab.add("");
//            
//            // Resultado
//            //l_lineas_tab.add("ResultSet rs = consulta(" + args_llamada_consulta_param + ");");
//            l_lineas_tab.add("int valor = super.getValor(" + args_llamada_consulta_param + ");");
//            l_lineas_tab.add("boolean existe = valor > 0;");
//            l_lineas_tab.add("");
//            
//            // Retorno
//            l_lineas_tab.add("return existe;");
//            
//            // Tabulamos las lineas_tab y las añadimos a las líneas de la función
//            l_lineas_tab = super.insertarTab(cnt, l_lineas_tab);
//            l_lineas.addAll(l_lineas_tab);
//            
//        l_lineas.add("}");
//        
//        l_lineas = super.insertarTab(cnt, l_lineas);
//        return l_lineas;
//    }
//    
//
//    /**
//     * Genera el código de la función getNextId().
//     * 
//     * @param cnt                               Objeto contenedor
//     * 
//     * @return                                  Líneas generadas
//     */
//    private List <String> generarFuncionGetNextId(Contenedor cnt) {
//        
//        List <String> l_lineas = new ArrayList();
//        List <String> l_lineas_tab = new ArrayList();
//        
//        // Comentarios
//        String descripcion_funcion = "Obtiene el siguiente identificador disponible para un nuevo registro en la tabla.";
//        List <CCampo> l_argumentos = this.l_args_basicos;
//        String descripcion_retorno = "Siguiente valor disponible";
//        List <String> l_comentarios = generarComentariosFuncion(cnt, descripcion_funcion, l_argumentos, descripcion_retorno, l_throws);
//        l_lineas.addAll(l_comentarios);
//        
//        // Cuerpo
//        String nombre_funcion = "public int getNextId";
//        List <String> l_definicion = generarDefinicionFuncion(cnt, nombre_funcion, l_argumentos, l_throws);
//        l_lineas.addAll(l_definicion);
//        l_lineas.add("{");
//        
//            // Llamada a función getNextId de la superclase
//            String arg_1 = "";
//            if (obj_secundario.nombre != null) {
//                arg_1 = obj_secundario.nombre + ", ";
//            }
//            l_lineas_tab.add("int siguiente = getNextIdMySql(" + arg_1 + " NOMBRE_TABLA, \"" + clave_primaria.nombre + "\");");
//            
//            // Retorno
//            l_lineas_tab.add("return siguiente;");
//            
//            // Tabulamos las lineas_tab y las añadimos a las líneas de la función
//            l_lineas_tab = super.insertarTab(cnt, l_lineas_tab);
//            l_lineas.addAll(l_lineas_tab);
//            
//        l_lineas.add("}");
//        
//        l_lineas = super.insertarTab(cnt, l_lineas);
//        return l_lineas;
//    }
//    
    
    /**
     * Genera una cadena, separada por comas, con la lista de argumentos necesarios para la llamada a la función de BD que da de alta un nuevo registro.
     * 
     * @return                                  Lista generada
     */
    private String getListaArgumentosAlta() {
        StringBuilder s = new StringBuilder();
        
        for (int i = 0; i < Comun.size(l_args_basicos_contenedor_opcional); i++) {
            CCampo campo = l_args_basicos_contenedor_opcional.get(i);
            s.append(campo.nombre);
            s.append(", ");
        }

        
        for (int i = 0; i < Comun.size(l_args_campos); i++) {
            CCampo campo = l_args_campos.get(i);
            s.append(nombre_instancia_clase_registro + "." + campo.nombre);
            
            if (i < Comun.size(l_args_campos) - 1) {
                s.append(", ");
            }
        }
        
        return s.toString();
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
        String descripcion_funcion = "Da de alta un nuevo registro en la base de datos. Si el registro no tiene identificador asignado, se le asigna el siguiente disponible.";
        List <CCampo> l_argumentos = new ArrayList(this.l_args_basicos);
        CCampo arg_obj_acceso_registro = getArgumentoObjetoAccesoRegistro();
        l_argumentos.add(arg_obj_acceso_registro);
        
        String descripcion_retorno = "Número de registros afectados tras la consulta";
        List <String> l_comentarios = generarComentariosFuncion(cnt, descripcion_funcion, l_argumentos, descripcion_retorno, l_throws);
        l_lineas.addAll(l_comentarios);
        
        // Cuerpo
        String nombre_funcion = "public void alta";
        List <String> l_definicion = generarDefinicionFuncion(cnt, nombre_funcion, l_argumentos, l_throws);
        l_lineas.addAll(l_definicion);
        l_lineas.add("{");
        
            String lista_argumentos_basicos = CCampo.getListaNombresCampos(this.l_args_basicos_contenedor_opcional);
                    
            l_lineas_tab.add(nombre_clase_bd + " " + nombre_instancia_clase_bd + " = new " + nombre_clase_bd + "();");
            l_lineas_tab.add("");
            
            l_lineas_tab.add("if (" + arg_obj_acceso_registro.nombre + "." + clave_primaria.nombre + " == Comun.NO_DEFINIDO) {");
                l_lineas_tab.add(tab + arg_obj_acceso_registro.nombre + "." + clave_primaria.nombre + " = " + nombre_instancia_clase_bd + ".getNextId(" + lista_argumentos_basicos + ");");
            l_lineas_tab.add("}");
            
            l_lineas_tab.add("");
            String lista_argumentos_alta = getListaArgumentosAlta();
            String llamada = "int result = " + nombre_instancia_clase_bd + ".alta(" + lista_argumentos_alta + ");";
            int ancho_max = cnt.formOpciones.formato_ancho_linea_maximo - tab.length() - tab.length();
            List <String> l_trozos = trocearTexto(llamada, ancho_max);
            for (int i = 0; i < Comun.size(l_trozos); i++) {
                String trozo = l_trozos.get(i);
                if (i == 0) {
                    l_lineas_tab.add(trozo);
                } else {
                    l_lineas_tab.add(tab + tab + trozo);
                }
            }
            l_lineas_tab.add("");
            
            l_lineas_tab.add("if (result != 1) {");
            l_lineas_tab.add(tab + "throw new " + cnt.formOpciones.tag_excepcion_1_nombre + "(Errores.ERR_BD_ALTA);");
            l_lineas_tab.add("}");
            
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
    public StringBuilder generarClaseUtilidad(Contenedor cnt) {
        
        List <String> l_lineas = new ArrayList();
        
        // Imports
        List <String> l_imports = generarImports(cnt);
        l_lineas.addAll(l_imports);
        l_lineas.add("");
        l_lineas.add("");

        // Comentarios con la descripción de la clase
        List <String> l_descripcion_clase = generarDescripcionClase(cnt);
        l_lineas.addAll(l_descripcion_clase);
        l_lineas.add("");
        
        // Definición de la clase
        String definicion_clase = generarDefinicionClase(cnt);
        l_lineas.add(definicion_clase);
        
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

//        // Función existe(...)
//        List <String> l_funcion_existe = generarFuncionExiste(cnt);
//        l_lineas.addAll(l_funcion_existe);
//        l_lineas.add("");
//        l_lineas.add("");
//
//        // Función getNextId(...)
//        List <String> l_funcion_get_next_id = generarFuncionGetNextId(cnt);
//        l_lineas.addAll(l_funcion_get_next_id);
//        l_lineas.add("");
//        l_lineas.add("");
//        
        // Función alta(...)
        List <String> l_funcion_alta = generarFuncionAlta(cnt);
        l_lineas.addAll(l_funcion_alta);
        l_lineas.add("");
        l_lineas.add("");
//
//        // Funcion update(...)
//        List <String> l_funcion_update = generarFuncionUpdate(cnt);
//        l_lineas.addAll(l_funcion_update);
//        l_lineas.add("");
//        l_lineas.add("");
//
//        // Funcion setCampo(...)
//        List <String> l_funcion_set_campo = generarFuncionSetValor(cnt);
//        l_lineas.addAll(l_funcion_set_campo);
//        l_lineas.add("");
//        l_lineas.add("");
//
//        // Funcion delete(...)
//        List <String> l_funcion_delete = generarFuncionDelete(cnt);
//        l_lineas.addAll(l_funcion_delete);
//        l_lineas.add("");
        
        // Fin de la clase
        l_lineas.add("}");
        l_lineas.add("");
        
        StringBuilder s_clase = super.concatenarLineas(l_lineas);
        return s_clase;
    }

    
}
