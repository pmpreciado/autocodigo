/**
 * Contenedor.java
 *
 * Creado el 03-feb-2014, 18:46:42
 */
package es.cajal.autocodigo;

import es.cajal.autocodigo.exception.ExtException;
import es.cajal.autocodigo.forms.FormAutoCodigo;
import es.cajal.autocodigo.forms.FormGenerarCodigo;
import es.cajal.autocodigo.forms.FormImportarCodigo;
import es.cajal.autocodigo.forms.FormOpciones;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Clase para contener las instancias de objetos globales de la aplicación.
 *
 * @author Pedro María Preciado
 */
public class Contenedor {

    public Logger logger;
    public Aplicacion aplicacion;
    public Propiedades propiedades;
    
    
    /** Referencia a los formularios */
    
        /** Formulario principal de la aplicación */
        public FormAutoCodigo formAutoCodigo;
    
        /** Formulario de opciones de la aplicación */
        public FormOpciones formOpciones;

        /** Formulario para importar código */
        public FormImportarCodigo formImportarCodigo;
        
        /** Formulario para mostrar el código generado */
        public FormGenerarCodigo formGenerarCodigo;

        
        
    /**
     * Crea la instancia de la clase.
     * 
     * @throws ExtException                     Error al leer el fichero de propiedades
     *                                          Falta alguna propiedad
     *                                          El pool_manager no está definido
     *                                          No se puede obtener una conexión del pool_manager
     *                                          El pool de conexiones no está creado
     *                                          Error al establecer la conexión
     */
    public Contenedor() throws ExtException {
        init();
    }

    /**
     * Inicializa el objeto contenedor.
     * 
     * @throws ExtException                     Error al leer el fichero de propiedades
     *                                          Falta alguna propiedad
     *                                          El pool_manager no está definido
     *                                          No se puede obtener una conexión del pool_manager
     *                                          El pool de conexiones no está creado
     *                                          Error al establecer la conexión
     *                                          No se pueden inicializar las funciones criptográficas
     */
    public void init() throws ExtException {

        initLogger();
        
        logger.info("Iniciando " + Comun.NOMBRE_APLICACION);
        initAplicacion();
        
        logger.info("Iniciando propiedades");
        initPropiedades();
    }
        
    /**
     * Comprueba si el objeto contenedor está inicializado.
     * 
     * @return                              'true' si está inicializado
     *                                      'false' si no lo está
     */
    public boolean inicializado() {
        if (logger == null) return false;
        if (aplicacion == null) return false;
        if (propiedades == null) return false;
        return true;
    }

    
    /**
     * Destruye el objeto contenedor, liberando las conexiones del pool.
     */
    public void destroy() {
        aplicacion = null;
        propiedades = null;
    }
    
    
    private void initLogger() {
        logger = LogManager.getRootLogger();
    }
    
    
    /**
     * Inicia el objeto "aplicacion".
     */
    protected void initAplicacion() {
        aplicacion = new Aplicacion();
    }
    
    
    /**
     * Inicia el objeto "propiedades", cargando el fichero de propiedades.
     * 
     * @throws ExtException                     Error al leer el fichero de propiedades
     *                                          Falta alguna propiedad
     */
    private void initPropiedades() throws ExtException
    {
        propiedades = new Propiedades();
        propiedades.cargarFicheroPropiedades(this);
    }
    

    /**
     * Establece la referencia al formulario principal de la aplicación.
     * 
     * @param formAutoCodigo                    Referencia al formulario principal de la aplicación
     */
    public void setFormAutocodigo(FormAutoCodigo formAutoCodigo) {
        this.formAutoCodigo = formAutoCodigo;
    }

    
    /**
     * Establece la referencia al formulario de opciones.
     * 
     * @param formOpciones                    Referencia al formulario de opciones
     */
    public void setFormOpciones(FormOpciones formOpciones) {
        this.formOpciones = formOpciones;
    }

    
    /**
     * Establece la referencia al formulario de importar código.
     * 
     * @param formImportarCodigo                Referencia al formulario de importar código
     */
    public void setFormImportarCodigo(FormImportarCodigo formImportarCodigo) {
        this.formImportarCodigo = formImportarCodigo;
    }
    
    
    /**
     * Establece la referencia al formulario para mostrar el código generado.
     * 
     * @param formGenerarCodigo                Referencia al formulario para mostrar el código generado
     */
    public void setFormGenerarCodigo(FormGenerarCodigo formGenerarCodigo) {
        this.formGenerarCodigo = formGenerarCodigo;
    }

}
