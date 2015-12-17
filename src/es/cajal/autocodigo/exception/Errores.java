/**
 * Errores.java
 *
 * Creado el 22-may-2015, 13:03:44
 */

package es.cajal.autocodigo.exception;

/**
 * Definición de códigos de error genéricos.
 */
public class Errores {


    /** Tipo de error: Error de aplicación */
    public static final int TE_APLICACION               = 0;

    /** Tipo de error: Error de base de datos */
    public static final int TE_BASE_DE_DATOS            = 1;

    /** Tipo de error: Error del usuario */
    public static final int TE_USUARIO                  = 3;

    /** Tipo de error: Error del formulario de entrada */
    public static final int TE_FORMULARIO               = 4;

    /** Tipo de error: Error genérico */
    public static final int TE_GENERICO                = 99;

    /** Tipo de error: Aviso genérico */
    public static final int TE_AVISO                  = 200;



    //--------------------------------------------------------------------------
    // ERRORES DE APLICACIÓN
    //--------------------------------------------------------------------------

    /** Errores: Error indeterminado */
    public static final int ERR_AP_INDETERMINADO                            = -200001;

    /** Errores: Página solicitada no existe */
    public static final int ERR_AP_PAGINA_NO_EXISTENTE                      = -200002;

    /** Errores: Error al inicializar la aplicación */
    public static final int ERR_AP_INICIALIZAR_APLICACION                   = -200003;

    /** Errores: Error de Entrada/Salida */
    public static final int ERR_AP_ES                                       = -200004;

    /** Errores: No se encuentra el fichero de propiedades %0% */
    public static final int ERR_AP_FICH_PROPIEDADES_NO_ENCONTRADO           = -200005;

    /** Errores: Error al leer el fichero de propiedades %0% */
    public static final int ERR_AP_LEER_FICH_PROPIEDADES                    = -200006;

    /** Errores: Error al escribir el fichero de propiedades %0% */
    public static final int ERR_AP_ESCRIBIR_FICH_PROPIEDADES                = -200007;

    /** Errores: Falta propiedad %0% en el el fichero de propiedades */
    public static final int ERR_AP_FICH_PROPIEDADES_FALTA_PROPIEDAD         = -200008;

    /** Errores: El identificador %0% no se corresponde con ningún campo */
    public static final int ERR_AP_ID_CAMPO_NO_ENCONTRADO                   = -200009;

    /** Errores: No ha sido definida la página web */
    public static final int ERR_AP_PAG_WEB_NO_DEFINIDA                      = -200010;

    /** Errores: El parámetro %0% no está definido */
    public static final int ERR_AP_PARAMETRO_NO_DEFINIDO                    = -200011;

    /** Errores: El parámetro %0% no se puede modificar */
    public static final int ERR_AP_PARAMETRO_NO_MODIFICABLE                 = -200012;

    /** Errores: Error al leer la imagen */
    public static final int ERR_AP_LEER_IMAGEN                              = -200013;


    //--------------------------------------------------------------------------
    // ERRORES DE BASE DE DATOS
    //--------------------------------------------------------------------------

    /** Errores: Error indeterminado */
    public static final int ERR_BD_INDETERMINADO                            = -200101;

    /** Errores: No se encuentra el driver JDBC para el acceso a la base de datos %0% */
    public static final int ERR_BD_DRIVER_NO_ENCONTRADO                     = -200102;

    /** Errores: No se han suministrado los parámetros de conexión a la base de datos */
    public static final int ERR_BD_NO_HAY_PARAMETROS                        = -200103;

    /** Errores: No está establecida la conexión con la base de datos */
    public static final int ERR_BD_CONEXION_NO_ESTABLECIDA                  = -200104;

    /** Errores: Error en la conexión con la base de datos */
    public static final int ERR_BD_CONEXION                                 = -200105;

    /** Errores: Error al establecer la conexión con la base de datos %0% */
    public static final int ERR_BD_ESTABLECER_CONEXION                      = -200106;

    /** Errores: Error al cerrar la conexión con la base de datos */
    public static final int ERR_BD_CERRAR_CONEXION                          = -200107;

    /** Errores: Error al crear el pool de conexiones con la base de datos */
    public static final int ERR_BD_CREAR_POOL                               = -200108;

    /** Errores: Error al obtener el pool de conexiones con la base de datos */
    public static final int ERR_BD_OBTENER_POOL                             = -200109;

    /** Error en la consulta a la base de datos */
    public static final int ERR_BD_CONSULTA_BD                              = -200110;

    /** Error en el acceso a la base de datos */
    public static final int ERR_BD_ACCESO_BD                                = -200111;

    /** Se ha alcanzado el máximo número de cursores */
    public static final int ERR_BD_MAXIMO_NUMERO_CURSORES                   = -200112;

    /** El texto introducido en el campo '%0%' es demasiado largo */
    public static final int ERR_BD_CAMPO_DEMASIADO_LARGO                    = -200113;

    /** Error al preparar la consulta SQL */
    public static final int ERR_BD_PREPARAR_CONSULTA_SQL                    = -200114;

    /** Error en la consulta SQL */
    public static final int ERR_BD_CONSULTA_SQL                             = -200115;

    /** Error al obtener el resultado de la consulta SQL */
    public static final int ERR_BD_OBTENER_RESULTADO_CONSULTA               = -200116;

    /** Error al iniciar una transacción */
    public static final int ERR_BD_INICIAR_TRANSACCION                      = -200117;

    /** Error al hacer el commit de la transacción */
    public static final int ERR_BD_COMMIT_TRANSACCION                       = -200118;

    /** Error al hacer el rollback de la transacción */
    public static final int ERR_BD_ROLLBACK_TRANSACCION                     = -200119;

    /** Error al establecer el modo de commit automático para las transacciones de base de datos */
    public static final int ERR_BD_SET_AUTOCOMMIT                           = -200120;

    
    //--------------------------------------------------------------------------
    // ERRORES DEL USUARIO
    //--------------------------------------------------------------------------

    /** El usuario '%0%' no se encuentra en el sistema */
    public static final int ERR_US_USUARIO_NO_EXISTE                        = -200301;

    /** El usuario '%0%' ya existe en la base de datos */
    public static final int ERR_US_USUARIO_EXISTE                           = -200302;

    /** El usuario no está autorizado para realizar esta operación */
    public static final int ERR_US_NO_AUTORIZADO                            = -200303;

    /** El usuario o la contraseña es incorrecto */
    public static final int ERR_US_USUARIO_O_PW_INCORRECTO                  = -200304;

    /** Ningún usuario ha iniciado la sesión o ésta ha caducado */
    public static final int ERR_US_NO_HAY_USUARIO_LOGGED                    = -200305;

    /** Error al dar de alta la sesión del usuario */
    public static final int ERR_US_ALTA_SESION                              = -200306;

    /** La sesión no ha sido iniciada o bien ha caducado */
    public static final int ERR_US_SESION_NO_INICIADA                       = -200307;

    /** El usuario no se encuentra activo */
    public static final int ERR_US_NO_ACTIVO                                = -200308;

    /** La contraseña debe tener un mínimo de %0% caracteres */
    public static final int ERR_US_CONTRASEÑA_CORTA                         = -200309;

    /** El usuario no está identificado o bien la identificación ha caducado */
    public static final int ERR_US_USUARIO_NO_IDENTIFICADO                  = -200310;

    /** La cuenta del usuario ha expirado */
    public static final int ERR_US_CUENTA_EXPIRADA                          = -200311;


    //--------------------------------------------------------------------------
    // ERRORES DEL FORMULARIO DE ENTRADA
    //--------------------------------------------------------------------------

    /** Falta cumplimentar un campo obligatorio */
    public static final int ERR_FORM_CAMPO_OBLIGATORIO                      = -200401;

    /** Faltan cumplimentar los siguientes campos */
    public static final int ERR_FORM_CAMPOS_OBLIGATORIOS                    = -200402;

    /** Falta cumplimentar alguno de estos campos */
    public static final int ERR_FORM_AL_MENOS_UNO_DE_ESTOS_CAMPOS           = -200403;

    /** Los siguientes campos contienen valores no permitidos */
    public static final int ERR_FORM_CAMPOS_RESTRINGUIDOS                   = -200404;



    //--------------------------------------------------------------------------
    // OTROS ERRORES
    //--------------------------------------------------------------------------

    /** Errores: Error personalizado: El mensaje lo da el "tag" */
    public static final int ERR_GEN_PERSONALIZADO                            = -210001;
    
    /** Errores: Error indeterminado */
    public static final int ERR_GEN_INDETERMINADO                            = -210002;

    /** Errores: Error otro */
    public static final int ERR_GEN_OTRO                                     = -210003;



    /**
     * Obtiene el mensaje de error correspondiente al código de error dado.
     *
     * @param codigo_error                  Código de error
     *
     * @return                              Descripción del mensaje de error
     */
    public static String getMensajeError(int codigo_error)
    {
        String mensaje;
        switch (codigo_error) {

            //--------------------------------------------------------------------------
            // ERRORES DE APLICACIÓN
            //--------------------------------------------------------------------------

            case ERR_AP_INDETERMINADO:
                mensaje = "Error indeterminado";
                break;

            case ERR_AP_PAGINA_NO_EXISTENTE:
                mensaje = "La página solicitada no existe";
                break;

            case ERR_AP_INICIALIZAR_APLICACION:
                mensaje = "Error al inicializar la aplicación";
                break;

            case ERR_AP_ES:
                mensaje = "Error de Entrada/Salida";
                break;

            case ERR_AP_FICH_PROPIEDADES_NO_ENCONTRADO:
                mensaje = "No se encuentra el fichero de propiedades %0%";
                break;

            case ERR_AP_LEER_FICH_PROPIEDADES:
                mensaje = "Error al leer el fichero de propiedades %0%";
                break;

            case ERR_AP_ESCRIBIR_FICH_PROPIEDADES:
                mensaje = "Error al escribir el fichero de propiedades %0%";
                break;
            
            case ERR_AP_FICH_PROPIEDADES_FALTA_PROPIEDAD:
                mensaje = "Falta propiedad %0% en el fichero de propiedades %1%";
                break;

            case ERR_AP_ID_CAMPO_NO_ENCONTRADO:
                mensaje = "El identificador %0% no se corresponde con ningún campo";
                break;

            case ERR_AP_PAG_WEB_NO_DEFINIDA:
                mensaje = "No ha sido definida la página web";
                break;

            case ERR_AP_PARAMETRO_NO_DEFINIDO:
                mensaje = "El parámetro %0% no está definido";
                break;

            case ERR_AP_PARAMETRO_NO_MODIFICABLE:
                mensaje = "El parámetro %0% no se puede modificar";
                break;

            case ERR_AP_LEER_IMAGEN:
                mensaje = "Error al leer la imagen";
                break;


            //--------------------------------------------------------------------------
            // ERRORES DE BASE DE DATOS
            //--------------------------------------------------------------------------

            case ERR_BD_INDETERMINADO:
                mensaje = "Error indeterminado";
                break;

            case ERR_BD_DRIVER_NO_ENCONTRADO:
                mensaje = "No se encuentra el driver JDBC de acceso a la base de datos %0%";
                break;

            case ERR_BD_NO_HAY_PARAMETROS:
                mensaje = "No se han suministrado los parámetros de conexión a la base de datos";
                break;

            case ERR_BD_CONEXION_NO_ESTABLECIDA:
                mensaje = "No está establecida la conexión con la base de datos";
                break;

            case ERR_BD_CONEXION:
                mensaje = "Error en la conexión con la base de datos";
                break;

            case ERR_BD_ESTABLECER_CONEXION:
                mensaje = "Error al establecer la conexión con la base de datos %0%";
                break;

            case ERR_BD_CERRAR_CONEXION:
                mensaje = "Error al cerrar la conexión con la base de datos %0%";
                break;

            case ERR_BD_CREAR_POOL:
                mensaje = "Error al crear el pool de conexiones con la base de datos";
                break;
                
            case ERR_BD_OBTENER_POOL:
                mensaje = "Error al obtener el pool de conexiones con la base de datos";
                break;

            case ERR_BD_CONSULTA_BD:
                mensaje = "Error en la consulta a la base de datos";
                break;

            case ERR_BD_ACCESO_BD:
                mensaje = "Error en el acceso a la base de datos";
                break;

            case ERR_BD_MAXIMO_NUMERO_CURSORES:
                mensaje = "Se ha alcanzado el máximo número de cursores";
                break;

            case ERR_BD_CAMPO_DEMASIADO_LARGO:
                mensaje = "El texto introducido en el campo '%0%' es demasiado largo";
                break;

            case ERR_BD_PREPARAR_CONSULTA_SQL:
                mensaje = "Error al preparar la consulta SQL";
                break;

            case ERR_BD_CONSULTA_SQL:
                mensaje = "Error en la consulta SQL";
                break;

            case ERR_BD_OBTENER_RESULTADO_CONSULTA:
                mensaje = "Error al obtener el resultado de la consulta SQL";
                break;

            case ERR_BD_INICIAR_TRANSACCION:
                mensaje = "Error al iniciar una transacción";
                break;

            case ERR_BD_COMMIT_TRANSACCION:
                mensaje = "Error al hacer el commit de la transacción";
                break;

            case ERR_BD_ROLLBACK_TRANSACCION:
                mensaje = "Error al hacer el rollback de la transacción";
                break;

            case ERR_BD_SET_AUTOCOMMIT:
                mensaje = "Error al establecer el modo de commit automático para las transacciones de base de datos";
                break;

            //--------------------------------------------------------------------------
            // ERRORES DEL USUARIO
            //--------------------------------------------------------------------------

            case ERR_US_USUARIO_NO_EXISTE:
                mensaje = "El usuario '%0%' no se encuentra en el sistema";
                break;

            case ERR_US_USUARIO_EXISTE:
                mensaje = "El usuario '%0%' ya existe en la base de datos";
                break;

            case ERR_US_NO_AUTORIZADO:
                mensaje = "El usuario no está autorizado para realizar esta operación";
                break;

            case ERR_US_USUARIO_O_PW_INCORRECTO:
                mensaje = "El usuario o la contraseña es incorrecto";
                break;

            case ERR_US_NO_HAY_USUARIO_LOGGED:
                mensaje = "Ningún usuario ha iniciado la sesión o ésta ha caducado";
                break;

            case ERR_US_ALTA_SESION:
                mensaje = "Error al dar de alta la sesión del usuario";
                break;

            case ERR_US_SESION_NO_INICIADA:
                mensaje = "La sesión no ha sido iniciada o bien ha caducado";
                break;

            case ERR_US_NO_ACTIVO:
                mensaje = "El usuario '%0%' no se encuentra activo";
                break;

            case ERR_US_CONTRASEÑA_CORTA:
                mensaje = "La contraseña debe tener un mínimo de %0% caracteres";
                break;

            case ERR_US_USUARIO_NO_IDENTIFICADO:
                mensaje = "El usuario no está identificado o bien la identificación ha caducado";
                break;

            case ERR_US_CUENTA_EXPIRADA:
                mensaje = "La cuenta del usuario ha expirado";
                break;


            //--------------------------------------------------------------------------
            // ERRORES DE FORMULARIOS DE ENTRADA
            //--------------------------------------------------------------------------

            case ERR_FORM_CAMPO_OBLIGATORIO:
                mensaje = "Falta cumplimentar el campo '%0%'";
                break;

            case ERR_FORM_CAMPOS_OBLIGATORIOS:
                mensaje = "Faltan cumplimentar los siguientes campos:";
                break;

            case ERR_FORM_AL_MENOS_UNO_DE_ESTOS_CAMPOS:
                mensaje = "Hay que cumplimentar al menos uno de estos campos:";
                break;

            case ERR_FORM_CAMPOS_RESTRINGUIDOS:
                mensaje = "Los siguientes campos contienen valores no permitidos:";
                break;


            //--------------------------------------------------------------------------
            // ERRORES GENERICOS
            //--------------------------------------------------------------------------

            case ERR_GEN_PERSONALIZADO:
                mensaje = "%0%";
                break;
                
            case ERR_GEN_INDETERMINADO:
                mensaje = "Error indeterminado";
                break;

            case ERR_GEN_OTRO:
                mensaje = "Error";
                break;

            default:
                //mensaje = "Error indeterminado";
                mensaje = null;
        }

        return mensaje;
    }

//
//    /**
//     * Obtiene el mensaje del error, sustituyendo las etiquetas de sustitución por sus valores.
//     *
//     * @param codigo_error                  Código de error
//     * @param l_tags                        Lista con las etiquetas de sustitución
//     *                                      Si es 'null', no se usarán etiquetas de sustitución
//     * 
//     * @return                              Mensaje del error
//     */
//    public static String getMensajeError(int codigo_error, List <String> l_tags) {
//        String mensaje_error = getMensajeError(codigo_error);
//        String mensaje_tags = Comun.sustituirTagsCadena(mensaje_error, l_tags);
//        return mensaje_tags;
//    }
//    
//    
//    /**
//     * Obtiene el mensaje del error, sustituyendo la etiqueta de sustitución suministrada.
//     *
//     * @param codigo_error                  Código de error
//     * @param tag                           Etiqueta de sustitución
//     * 
//     * @return                              Mensaje del error
//     */
//    public static String getMensajeError(int codigo_error, String tag) {
//        
//        List <String> l_tags = new ArrayList();
//        l_tags.add(tag);
//        
//        String mensaje_tags = getMensajeError(codigo_error, l_tags);
//        return mensaje_tags;
//    }
    
    
    /**
     * Obtiene el tipo de error a partir del número.
     *
     * @param error                         Número de error
     *
     * @return                              Tipo de error
     */
    public static int getTipoError(int error) {
        if (error <= -200001 && error > -200099) return TE_APLICACION;
        if (error <= -200101 && error > -200199) return TE_BASE_DE_DATOS;
        if (error <= -200301 && error > -200399) return TE_USUARIO;
        if (error <= -200401 && error > -200499) return TE_FORMULARIO;
        if (error <= -210001 && error > -210099) return TE_GENERICO;
        if (error <= -300001 && error > -399799) return TE_AVISO;
        return TE_GENERICO;
    }


    /**
     * Obtiene la descripción de un tipo de error dado.
     *
     * @param tipo_error                    Tipo del error
     *
     * @return                              Descripción asociada al tipo de error
     */
    public static String getDescripcionTipoError(int tipo_error)
    {
        String mensaje;
        switch (tipo_error) {
            case TE_APLICACION:
                mensaje = "Error de aplicación";
                break;
            case TE_BASE_DE_DATOS:
                mensaje = "Error de base de datos";
                break;
            case TE_USUARIO:
                mensaje = "Error del usuario";
                break;
            case TE_FORMULARIO:
                mensaje = "Error en el formulario";
                break;
            case TE_GENERICO:
                mensaje = "Error";
                break;
            case TE_AVISO:
                mensaje = "Aviso";
                break;
            default:
                mensaje = "Error";
        }
        return mensaje;
    }
}
