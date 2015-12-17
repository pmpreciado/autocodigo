/**
 * Propiedades.java
 *
 * Creado el 03-feb-2014, 18:51:04
 */

package es.cajal.autocodigo;

import es.cajal.autocodigo.exception.Errores;
import es.cajal.autocodigo.exception.ExtException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * Gestión del fichero de propiedades de la aplicación.
 *
 * @author Pedro María Preciado
 */
public class Propiedades {

    
    /** Ruta del fichero de propiedades predeterminado */
    private static final String FP_PREDETERMINADO_RUTA_1  = "autocodigo.properties";
    private static final String FP_PREDETERMINADO_RUTA_2  = "bin/autocodigo.properties";
    

    /** Código para el salto de línea usado en el fichero de propiedades */
    private static final String SALTO_LINEA = "^p";
    
    
    /** Handle del fichero */
    private Properties properties;
    
    

    /** Variables para cargar las propiedades del fichero */
    
        /** Formato */
        public int prop_formato_espacios_tab;
        public int prop_formato_columna_comentarios;
        public int prop_formato_ancho_linea_maximo;
        
        /** Java Beans */
        public String prop_jb_ambito_identificadores;
        public boolean prop_jb_generar_get;
        public boolean prop_jb_generar_set;

        /** Tags */
        public String prop_tag_author;
        public String prop_tag_contenedor;
        public String prop_tag_contenedor_desc;
        public String prop_tag_objeto_secundario_bd;
        public String prop_tag_objeto_secundario_bd_desc;
        public boolean prop_tag_tag_inc_contenedor_llamadas_bd;
        public String prop_tag_excepcion_1_nombre;
        public String prop_tag_excepcion_1_desc;
        public String prop_tag_excepcion_2_nombre;
        public String prop_tag_excepcion_2_desc;
        public String prop_tag_imports_bd;
        public String prop_tag_imports_registro;
        public String prop_tag_imports_utilidad;
        public String prop_tag_clase_extends_db;
            
    
    /** 
     * Crea la instancia de la clase.
     */
    public Propiedades() {
        properties = new Properties();
    }

    
    /**
     * Obtiene una propiedad dada por su nombre.
     *
     * @param nombre_propiedad              Nombre de la propiedad que se quiere obtener
     * @return                              Valor de la propiedad
     */
    private String getPropiedad(String nombre_propiedad)
    {
        String valor = properties.getProperty(nombre_propiedad);
        valor = Comun.trim(valor);
        return valor;
    }
    
    
    /**
     * Obtiene una propiedad dada por su nombre.
     * Si no existe la propiedad,  en lugar de 'null', retorna un valor predeterminado que se pasa por parámetro.
     *
     * @param nombre_propiedad              Nombre de la propiedad que se quiere obtener
     * @param valor_predeterminado          Valor a retornar si la propiedad no existe
     * @return                              Valor de la propiedad
     */
    private String getPropiedad(String nombre_propiedad, String valor_predeterminado)
    {
        String valor = properties.getProperty(nombre_propiedad);
        valor = Comun.trim(valor);
        if (valor == null) valor = valor_predeterminado;
        return valor;
    }
    
    
    /**
     * Obtiene una propiedad numérica dada por su nombre.
     *
     * @param nombre_propiedad              Nombre de la propiedad que se quiere obtener
     * @return                              Valor de la propiedad
     */
    private int getPropiedadInt(String nombre_propiedad)
    {
        String s_valor = getPropiedad(nombre_propiedad);
        int valor = Comun.toEntero(s_valor);
        return valor;
    }
    
    
    /**
     * Obtiene una propiedad numérica dada por su nombre.
     * Si no existe la propiedad,  en lugar de 'null', retorna un valor predeterminado que se pasa por parámetro.
     *
     * @param nombre_propiedad              Nombre de la propiedad que se quiere obtener
     * @param valor_predeterminado          Valor a retornar si la propiedad no existe
     * 
     * @return                              Valor de la propiedad
     */
    private int getPropiedadInt(String nombre_propiedad, int valor_predeterminado)
    {
        String s_valor = getPropiedad(nombre_propiedad);
        if (s_valor == null) return valor_predeterminado;
        int valor = Comun.toEntero(s_valor);
        return valor;
    }
    
    
    /**
     * Obtiene una propiedad booleana dada por su nombre.
     *
     * @param nombre_propiedad              Nombre de la propiedad que se quiere obtener
     * @return                              Valor de la propiedad
     */
    private boolean getPropiedadBoolean(String nombre_propiedad)
    {
        int valor = getPropiedadInt(nombre_propiedad);
        return valor > 0;
    }
    
    
    /**
     * Obtiene una propiedad booleana dada por su nombre.
     *
     * @param nombre_propiedad              Nombre de la propiedad que se quiere obtener
     * @param valor_predeterminado          Valor a retornar si la propiedad no existe
     * @return                              Valor de la propiedad
     */
    private boolean getPropiedadBoolean(String nombre_propiedad, boolean valor_predeterminado)
    {
        String s_valor = getPropiedad(nombre_propiedad);
        if (s_valor == null) return valor_predeterminado;
        int valor = Comun.toEntero(s_valor);
        return valor > 0;
    }
    
    
    /**
     * Carga el fichero de propiedades
     *
     * @param cnt                           Objeto contenedor 
     * 
     * @throws ExtException                 Error al leer el fichero de propiedades
     *                                      Falta alguna propiedad
     */
    public void cargarFicheroPropiedades(Contenedor cnt) throws ExtException {
        
        try {
            FileInputStream in = new FileInputStream(FP_PREDETERMINADO_RUTA_1);
            properties.load(in);
            in.close();
            
        } catch (IOException ioex) { 
            
            try {
                FileInputStream in = new FileInputStream(FP_PREDETERMINADO_RUTA_2);
                properties.load(in);
                in.close();        
                
            } catch (IOException ioex2) {
                throw new ExtException(Errores.ERR_GEN_PERSONALIZADO, "Fichero de propiedades " + FP_PREDETERMINADO_RUTA_1 + " no encontrado", ioex);
            }
        }
        
        /** Formato */
        prop_formato_espacios_tab               = getPropiedadInt("formato.espacios_tab", 4);
        prop_formato_columna_comentarios        = getPropiedadInt("formato.columna_comentarios", 49);
        prop_formato_ancho_linea_maximo         = getPropiedadInt("formato.ancho_linea_maximo", 120);
        
        /** Java Beans */
        prop_jb_ambito_identificadores          = getPropiedad("jb.ambito_identificadores", "public");
        prop_jb_generar_get                     = getPropiedadBoolean("jb.generar_get", true);
        prop_jb_generar_set                     = getPropiedadBoolean("jb.generar_set", true);

        /** Tags */
        prop_tag_author                         = getPropiedad("tag.author", "");
        prop_tag_contenedor                     = getPropiedad("tag.contenedor", "");
        prop_tag_contenedor_desc                = getPropiedad("tag.contenedor_descripcion", "");
        prop_tag_objeto_secundario_bd           = getPropiedad("tag.objeto_secundario_bd", "");
        prop_tag_objeto_secundario_bd_desc      = getPropiedad("tag.objeto_secundario_bd_descripcion", "");
        prop_tag_tag_inc_contenedor_llamadas_bd = getPropiedadBoolean("tag.inc_contenedor_llamadas_bd", true);
        prop_tag_excepcion_1_nombre             = getPropiedad("tag.excepcion_1_nombre", "");
        prop_tag_excepcion_1_desc               = getPropiedad("tag.excepcion_1_descripcion", "");
        prop_tag_excepcion_2_nombre             = getPropiedad("tag.excepcion_2_nombre", "");
        prop_tag_excepcion_2_desc               = getPropiedad("tag.excepcion_2_descripcion", "");
        prop_tag_imports_bd                     = getPropiedad("tag.imports_bd", "");
        prop_tag_imports_bd = Comun.replaceAll(prop_tag_imports_bd, SALTO_LINEA, Comun.NL);
        prop_tag_imports_registro               = getPropiedad("tag.imports_registro", "");
        prop_tag_imports_registro = Comun.replaceAll(prop_tag_imports_registro, SALTO_LINEA, Comun.NL);
        prop_tag_imports_utilidad               = getPropiedad("tag.imports_utilidad", "");
        prop_tag_imports_utilidad = Comun.replaceAll(prop_tag_imports_utilidad, SALTO_LINEA, Comun.NL);
        
        prop_tag_clase_extends_db               = getPropiedad("tag.clase_extends_db", "");
    }
    
    
}
