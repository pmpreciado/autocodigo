/**
 * Comun.java
 *
 * Creado el 04-feb-2014, 12:33:17
 */

package es.cajal.autocodigo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Definiciones, tipos y funciones comunes usados por la aplicación.
 *
 * @author Pedro María Preciado
 */
public class Comun {

    /** Nombre de la aplicación */
    public static String NOMBRE_APLICACION = "AutoCodigo";
    
    /** Nueva línea */
    public static final String NL = System.getProperty("line.separator");


    /** No definido */
    public static final int NO_DEFINIDO                 = -1;

    /** No definido BBDD */
    public static final int NO_DEFINIDO_BD              = 0;

    /** No definido */
    public static final double NO_DEFINIDO_DOUBLE       = -99999999.99;


    
    /**
     * Comprueba si una cadena dada está vacía. Si la cadena es 'null' o no tiene caracteres, se considera vacía.
     *
     * @param cadena                    Cadena a comprobar
     * @return                          'true' si la cadena está vacía
     *                                  'false' si no
     */
    public static boolean vacio(String cadena) {
        if (cadena == null) return true;
        if (cadena.length() == 0) return true;
        return false;
    }


    /**
     * Comprueba si un String [] está vacío. Si el String [] es 'null' o no tiene elementos, se considera vacío.
     *
     * @param array                     String [] comprobar
     * @return                          'true' si el String [] está vacío
     *                                  'false' si no
     */
    public static boolean vacio(String [] array) {
        if (array == null) return true;
        if (array.length == 0) return true;
        return false;
    }


    /**
     * Comprueba si un int [] está vacío. Si el int [] es 'null' o no tiene elementos, se considera vacío.
     *
     * @param array                     int [] comprobar
     * @return                          'true' si el int [] está vacío
     *                                  'false' si no
     */
    public static boolean vacio(int [] array) {
        if (array == null) return true;
        if (array.length == 0) return true;
        return false;
    }


    /**
     * Comprueba si un String [][] está vacío. Si el String [][] es 'null' o no tiene elementos, se considera vacío.
     *
     * @param array                     String [][] comprobar
     * @return                          'true' si el String [] está vacío
     *                                  'false' si no
     */
    public static boolean vacio(String [][] array) {
        if (array == null) return true;
        if (array.length == 0) return true;
        return false;
    }


    /**
     * Comprueba si una lista dada está vacía. Si la lista es 'null' o no tiene elementos, se considera vacía.
     *
     * @param l                         Lista a comprobar
     * @return                          'true' si la lista está vacía
     *                                  'false' si no
     */
    public static boolean vacio(List l) {
        if (l == null) return true;
        if (l.isEmpty()) return true;
        return false;
    }


    /**
     * Reimplementación de la función "substring", pero más fácil de utilizar que la nativa
     * de Java, que es un coñazo.
     * Si la subcadena a obtener sobrepasa la longitud de la inicial, se ajusta el resultado
     * a la cadena dentro de los límites, sin lanzar excepción.
     * Ejemplos:
     *   <ul>
     *     <li> miSubstring("amigo", 0, 0) => "a"
     *     <li> miSubstring("amigo", 0, 1) => "am"
     *     <li> miSubstring("amigo", 1, 4) => "migo"
     *   </ul>

     * @param cadena                    Cadena sobre la que se quiere extraer un subconjunto
     * @param desde                     Primer caracter a extraer (empezando en la posición 0)
     * @param hasta                     Último caracter (empezando en la posición 0)
     * @return                          Subcadena
     */
    public static String miSubstring(String cadena, int desde, int hasta) {
        if (cadena.length() == 0) return "";

        if (desde < 0) desde = 0;
        if (desde >= cadena.length()) return "";
        if (hasta >= cadena.length()) hasta = cadena.length() - 1;

        String subcadena = cadena.substring(desde, hasta + 1);
        return subcadena;
    }


    /**
     * Reimplementación de la función "substring", pero más fácil de utilizar que la nativa
     * de Java, que es un coñazo.
     * Si la subcadena a obtener sobrepasa la longitud de la inicial, se ajusta el resultado
     * a la cadena dentro de los límites, sin lanzar excepción.
     * Ejemplos:
     *   <ul>
     *     <li> miSubstringLong("amigo", 0, 0) => ""
     *     <li> miSubstringLong("amigo", 0, 1) => "a"
     *     <li> miSubstringLong("amigo", 3, 2) => "go"
     *   </ul>
     *
     * @param cadena                    Cadena sobre la que se quiere extraer un subconjunto
     * @param desde                     Primer caracter a extraer (empezando en la posición 0)
     * @param longitud                  Número de caracteres a extraer
     * @return                          Subcadena
     */
    public static String miSubstringLong(String cadena, int desde, int longitud) {
        if (cadena.length() == 0) return "";

        if (desde < 0) desde = 0;
        if (desde >= cadena.length()) return "";
        if (desde + longitud > cadena.length()) longitud = cadena.length() - desde;

        String subcadena = cadena.substring(desde, desde + longitud);

        return subcadena;
    }

    
    /**
     * Divide una cadena en fragmentos del tamaño dado.
     * El último de los fragmentos sera el resto de la cadena, y puede ser de tamaño menor.
     * 
     * @param cadena                        Cadena a dividir
     * @param longitud                      Longitud de los fragmentos
     * 
     * @return                              Fragmentos
     */
    public static List <String> split(String cadena, int longitud) {
        List <String> l_result = new ArrayList();
        if (cadena == null) {
            return l_result;
        }
        
        if (cadena.length() == 0) {
            l_result.add(cadena);
            return l_result;
        }
        
        while (cadena.length() > 0) {
            String s = Comun.primeros(cadena, longitud);
            l_result.add(s);
            cadena = Comun.eliminarPrimeros(cadena, longitud);
        }
        
        return l_result;
    }
    

    /**
     * Divide una cadena en varias, troceándola por donde encuentre el caracter dado.
     * A diferencia de String.split(), esta función no usa expresiones regulares.
     * 
     * @param cadena                        Cadena a dividir
     * @param delimitador                   Caracter o palabra delimitador
     * 
     * @return                              Fragmentos
     */
    public static List <String> split(String cadena, String delimitador)
    {
        List <String> l = new ArrayList();

        while (true)
        {
            int index = cadena.indexOf(delimitador);
            if (index == -1)
            {
                l.add(cadena);
                return l;
            } else
            {
                l.add(cadena.substring(0, index));
                cadena = cadena.substring(index + delimitador.length());
            }
        }
    }
    
    
    /**
     * Une varias cadenas en una.
     * Las cadenas unidas van separadas por el separador dado.
     * 
     * @param arr_cadenas                       Cadenas a unir
     * @param separador                         Separador
     * 
     * @return                                  Cadena con el resultado
     */
    public static String join(String [] arr_cadenas, String separador) {
        
        if (arr_cadenas == null) {
            return null;
        }
        
        StringBuilder s = new StringBuilder();
        
        for (int i = 0; i < arr_cadenas.length; i++) {
            if (i > 0) {
                s.append(separador);
            }

            String cadena = arr_cadenas[i];
            s.append(cadena);
        }
        
        return s.toString();
    }
    
    
    /**
     * Une varias cadenas en una.
     * Las cadenas unidas van separadas por el separador dado.
     * 
     * @param l_cadenas                         Cadenas a unir
     * @param separador                         Separador
     * 
     * @return                                  Cadena con el resultado
     */
    public static String join(Collection <String> l_cadenas, String separador) {
        
        if (l_cadenas == null) {
            return null;
        }
        
        StringBuilder s = new StringBuilder();
        
        for (String cadena : l_cadenas) {
        
            if (s.length() > 0) {
                s.append(separador);
            }
            
            s.append(cadena);
        }
        
        return s.toString();
    }
    
    
    /**
     * Reemplaza una subcadena en una cadena, dada por su posición y su longitud.
     * Ejemplos:
     *   <ul>
     *     <li> reemplazarPosicion("amiguitos", 2, 4, "x") => "amxtos"
     *     <li> reemplazarPosicion("amiguitos", 2, 4, "123") => "am123tos"
     *   </ul>
     *
     * @param cadena                        Cadena original
     * @param indice                        Índice que ocupa el carácter a sustituir (empezando en la posición 0)
     * @param longitud                      Número de carácteres a sustituir
     * @param texto_nuevo                   Carácter (o cadena) que sustituirá al carácter
     * @return                              Nueva cadena
     */
    public static String reemplazarPosicion(String cadena, int indice, int longitud, String texto_nuevo) {
        if (indice < 0 || indice >= cadena.length()) return cadena;

        String primeros = primeros(cadena, indice);
        String ultimos = ultimos(cadena, cadena.length() - indice - longitud);
        String nueva_cadena = primeros + texto_nuevo + ultimos;
        return nueva_cadena;
    }


    /**
     * Reemplaza un carácter en una cadena, dado por su posición.
     * Ejemplos:
     *   <ul>
     *     <li> reemplazarPosicion("amigo", 2, "x") => "amxgo"
     *     <li> reemplazarPosicion("amigo", 2, "123") => "am123go"
     *   </ul>
     *
     * @param cadena                        Cadena original
     * @param indice                        Índice que ocupa el carácter a sustituir (empezando en la posición 0)
     * @param texto_nuevo                   Carácter (o cadena) que sustituirá al carácter
     * @return                              Nueva cadena
     */
    public static String reemplazarPosicion(String cadena, int indice, String texto_nuevo) {

        return reemplazarPosicion(cadena, indice, 1, texto_nuevo);
    }


    /**
     * Comprueba si la cadena 1 es mayor que la cadena 2 en orden alfabético.
     *
     * @param cadena_1                  Primera cadena
     * @param cadena_2                  Segunda cadena
     * @return                          'true' si cadena_1 > cadena_2
     *                                  'false' si cadena_1 <= cadena_2
     */
    public static boolean esMayor(String cadena_1, String cadena_2) {
        if (cadena_1 == null || cadena_2 == null) return false;

        cadena_1 = quitarAcentos(cadena_1);
        cadena_2 = quitarAcentos(cadena_2);

        if (cadena_1.compareToIgnoreCase(cadena_2) < 0) return false;
        if (cadena_1.compareToIgnoreCase(cadena_2) == 0) return false;
        return true;
    }


    /**
     * Comprueba si la cadena 1 es menor que la cadena 2 en orden alfabético.
     *
     * @param cadena_1                  Primera cadena
     * @param cadena_2                  Segunda cadena
     * @return                          'true' si cadena_1 < cadena_2
     *                                  'false' si cadena_1 >= cadena_2
     */
    public static boolean esMenor(String cadena_1, String cadena_2) {
        if (cadena_1 == null || cadena_2 == null) return false;

        cadena_1 = quitarAcentos(cadena_1);
        cadena_2 = quitarAcentos(cadena_2);

        if (cadena_1.compareToIgnoreCase(cadena_2) > 0) return false;
        if (cadena_1.compareToIgnoreCase(cadena_2) == 0) return false;
        return true;
    }


    /**
     * Comprueba si la cadena 1 es igual a la cadena 2 en orden alfabético.
     *
     * @param cadena_1                  Primera cadena
     * @param cadena_2                  Segunda cadena
     * @return                          'true' si cadena_1 == cadena_2
     *                                  'false' si cadena_1 != cadena_2
     */
    public static boolean esIgual(String cadena_1, String cadena_2) {
        if (cadena_1 == null || cadena_2 == null) return false;

        if (cadena_1.compareToIgnoreCase(cadena_2) == 0) return true;
        return false;
    }


    /**
     * Obtiene el mayor entre dos números dados.
     *
     * @param numero_1                      Primer número
     * @param numero_2                      Segundo número
     *
     * @return                              El mayor de los dos números
     */
    public static int mayor(int numero_1, int numero_2) {
        if (numero_1 > numero_2) return numero_1;
        return numero_2;
    }


    /**
     * Obtiene el mayor entre tres números dados.
     *
     * @param numero_1                      Primer número
     * @param numero_2                      Segundo número
     * @param numero_3                      Tercer número
     *
     * @return                              El mayor de los tres números
     */
    public static int mayor(int numero_1, int numero_2, int numero_3) {
        int m1 = mayor(numero_1, numero_2);
        int m2 = mayor(m1, numero_3);
        return m2;
    }


    /**
     * Obtiene el mayor entre dos números dados.
     *
     * @param numero_1                      Primer número
     * @param numero_2                      Segundo número
     *
     * @return                              El mayor de los dos números
     */
    public static float mayor(float numero_1, float numero_2) {
        if (numero_1 > numero_2) return numero_1;
        return numero_2;
    }


    /**
     * Obtiene el segundo mayor entre tres números dados.
     *
     * @param numero_1                      Primer número
     * @param numero_2                      Segundo número
     * @param numero_3                      Tercer número
     *
     * @return                              El segundo mayor de los tres números
     */
    public static int segundoMayor(int numero_1, int numero_2, int numero_3) {

        if (numero_1 >= numero_2 && numero_1 >= numero_3) {
            int sm = mayor(numero_2, numero_3);
            return sm;
        }

        if (numero_2 >= numero_1 && numero_2 >= numero_3) {
            int sm = mayor(numero_1, numero_3);
            return sm;
        }

        int sm = mayor(numero_1, numero_2);
        return sm;
    }


    /**
     * Obtiene el menor entre dos números dados.
     *
     * @param numero_1                      Primer número
     * @param numero_2                      Segundo número
     *
     * @return                              El menor de los dos números
     */
    public static int menor(int numero_1, int numero_2) {
        if (numero_1 < numero_2) return numero_1;
        return numero_2;
    }


    /**
     * Obtiene el menor entre dos números dados.
     *
     * @param numero_1                      Primer número
     * @param numero_2                      Segundo número
     *
     * @return                              El menor de los dos números
     */
    public static float menor(float numero_1, float numero_2) {
        if (numero_1 < numero_2) return numero_1;
        return numero_2;
    }


    /**
     * Obtiene los 'n' primeros caracteres de una cadena.
     *
     * @param cadena                        Cadena sobre la que se quiere extraer los caracteres
     * @param n                             'n' primeros caracteres a extraer
     * @return                              Subcadena extraída
     */
    public static String primeros(String cadena, int n) {
        if (cadena == null) return null;
        if (cadena.length() == 0) return "";
        if (n <= 0) return "";
        if (n > cadena.length()) n = cadena.length();

        String subcadena = cadena.substring(0, n);
        return subcadena;
    }


    /**
     * Obtiene los 'n' últimos caracteres de una cadena.
     *
     * @param cadena                        Cadena sobre la que se quiere extraer los caracteres
     * @param n                             'n' últimos caracteres a extraer
     * @return                              Subcadena extraída
     */
    public static String ultimos(String cadena, int n) {
        if (cadena == null) return null;
        if (cadena.length() == 0) return "";
        if (n <= 0) return "";
        if (n > cadena.length()) n = cadena.length();

        String subcadena = cadena.substring(cadena.length() - n);
        return subcadena;
    }


    /**
     * Elimina los 'n' primeros caracteres de una cadena.
     *
     * @param cadena                        Cadena sobre la que se quiere eliminar los caracteres
     * @param n                             'n' primeros caracteres a eliminar
     * @return                              Cadena final
     */
    public static String eliminarPrimeros(String cadena, int n) {
        String subcadena = ultimos(cadena, cadena.length() - n);
        return subcadena;
    }


    /**
     * Elimina los 'n' últimos caracteres de una cadena.
     *
     * @param cadena                        Cadena sobre la que se quiere eliminar los caracteres
     * @param n                             'n' últimos caracteres a eliminar
     * @return                              Cadena final
     */
    public static String eliminarUltimos(String cadena, int n) {
        String subcadena = primeros(cadena, cadena.length() - n);
        return subcadena;
    }


    /**
     * Obtiene los 'n' primeros dígitos de un número.
     *
     * @param numero                        Número sobre el que se quiere extraer los dígitos
     * @param n                             'n' primeros dígitos a extraer
     * @return                              Dígitos extraídos
     */
    public static int primeros(int numero, int n) {
        String s_numero = Integer.toString(numero);
        String s_primeros = primeros(s_numero, n);
        return Integer.parseInt(s_primeros);
    }


    /**
     * Obtiene los 'n' últimos dígitos de un número.
     *
     * @param numero                        Número sobre el que se quiere extraer los dígitos
     * @param n                             'n' últimos dígitos a extraer
     * @return                              Dígitos extraídos
     */
    public static int ultimos(int numero, int n) {
        String s_numero = Integer.toString(numero);
        String s_ultimos = ultimos(s_numero, n);
        return Integer.parseInt(s_ultimos);
    }


    /**
     * Dado un número, obtiene el literal correspondiente.
     * Si no hay literal, obtiene el mismo número en una cadena.
     *
     * @param numero                        Número para el que se va a obtener el texto
     * @return                              Texto literal (por ejemplo, "cuatro")
     */
    public static String numero2Literal(int numero) {
        switch (numero) {
            case 0: return "cero";
            case 1: return "uno";
            case 2: return "dos";
            case 3: return "tres";
            case 4: return "cuatro";
            case 5: return "cinco";
            case 6: return "seis";
            case 7: return "siete";
            case 8: return "ocho";
            case 9: return "nueve";
            case 10: return "diez";
            case 11: return "once";
            case 12: return "doce";
            case 13: return "trece";
            case 14: return "catorce";
            case 15: return "quince";
            case 16: return "dieciséis";
            case 17: return "diecisiete";
            case 18: return "dieciocho";
            case 19: return "diecinueve";
            case 20: return "veinte";
            case 21: return "veintiuno";
            case 22: return "veintidós";
            case 23: return "veintitrés";
            case 24: return "veinticuatro";
            case 25: return "veinticinco";
            case 26: return "veintiséis";
            case 27: return "veintisiete";
            case 28: return "veintiocho";
            case 29: return "veintinueve";
            case 30: return "treinta";
            case 40: return "cuarenta";
            case 50: return "cincuenta";
            case 60: return "sesenta";
            case 70: return "setenta";
            case 80: return "ochenta";
            case 90: return "noventa";
            case 100: return "cien";
            case 1000: return "mil";
            case 10000: return "diez mil";
            case 100000: return "cien mil";
            case 1000000: return "un millón";
        }

        return Integer.toString(numero);
    }


    /**
     * Transforma un char, representando un dígito, al entero correspondiente.
     * Ejemplo: '9' -> 9
     *
     * @param c                             Caracter con el dígito
     * @return                              Número entero correspondiente
     */
    public static int char2Int(char c) {
        int n = (int) c - 48;
        return n;
    }


    /**
     * Transforma un char a una cadena con ese caracter.
     * Ejemplo: 's' -> "s"
     *
     * @param c                             Caracter con el char
     * @return                              Cadena con el char
     */
    public static String char2String(char c) {
        char [] cc = {c};
        String s = new String(cc);
        return s;
    }


    /**
     * Dado un caracter, obtiene el carácter equivalente en mayúsculas.
     *
     * @param c                             Caracter original
     * @return                              Caracter en mayúsculas
     */
    public static char mayusculas(char c) {
        if (c >= 'a' && c <= 'z') return (char) (c - 32);
        if (c == 'ñ') return 'Ñ';
        if (c == 'á') return 'Á';
        if (c == 'é') return 'É';
        if (c == 'í') return 'Í';
        if (c == 'ó') return 'Ó';
        if (c == 'ú') return 'Ú';
        if (c == 'ü') return 'Ü';
        return c;
    }


    /**
     * Dado un caracter, obtiene el carácter equivalente en minúsculas.
     *
     * @param c                             Caracter original
     * @return                              Caracter en minúsculas
     */
    public static char minusculas(char c) {
        if (c >= 'A' && c <= 'Z') return (char) (c + 32);
        if (c == 'Ñ') return 'ñ';
        if (c == 'Á') return 'á';
        if (c == 'É') return 'é';
        if (c == 'Í') return 'í';
        if (c == 'Ó') return 'ó';
        if (c == 'Ú') return 'ú';
        if (c == 'Ü') return 'ú';
        return c;
    }


    /**
     * Dado un caracter, obtiene el carácter equivalente sin acentos ni diéresis.
     * La Ñ la respeta.
     *
     * @param c                             Caracter original
     * @return                              Caracter sin acentos
     */
    public static char sinAcentos(char c) {

        switch (c) {
            case 'Á': return 'A';
            case 'É': return 'E';
            case 'Í': return 'I';
            case 'Ó': return 'O';
            case 'Ú': return 'U';
            case 'Ü': return 'U';

            case 'á': return 'a';
            case 'é': return 'e';
            case 'í': return 'i';
            case 'ó': return 'o';
            case 'ú': return 'u';
            case 'ü': return 'u';
        }

        return c;
    }


    /**
     * Busca si en un int[] existe un elemento determinado.
     *
     * @param array             Array donde se realiza la búsqueda
     * @param elemento          Elemento a buscar
     * @return
     *   <ul>
     *     <li> n >= 0          Posición en la que se encontró el elemento
     *     <li> -1              El elemento no figura en el array
     *   </ul>
     */
    public static int enArray(int [] array, int elemento) {
        if (array == null) return -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == elemento) return i;
        }
        return -1;
    }

    
    /**
     * Busca si en un char[] existe un elemento determinado.
     *
     * @param array             Array donde se realiza la búsqueda
     * @param elemento          Elemento a buscar
     * @return
     *   <ul>
     *     <li> n >= 0          Posición en la que se encontró el elemento
     *     <li> -1              El elemento no figura en el array
     *   </ul>
     */
    public static int enArray(char [] array, char elemento) {
        if (array == null) return -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == elemento) return i;
        }
        return -1;
    }
        

    /**
     * Busca si en un String [] existe un elemento determinado.
     * Si hubiera más de una coincidencia, retorna la primera encontrada.
     *
     * @param array             Array donde se realiza la búsqueda
     * @param elemento          Elemento a buscar
     * @return
     *   <ul>
     *     <li> n >= 0          Posición en la que se encontró el elemento
     *     <li> -1              El elemento no figura en el array
     *   </ul>
     */
    public static int enArray(String [] array, String elemento) {
        if (array == null) return -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(elemento)) return i;
        }
        return -1;
    }

    /**
     * Busca si en un String [] existe un elemento determinado.
     * Si hubiera más de una coincidencia, retorna la primera encontrada.
     * La búsqueda es insensible a mayúsculas y minúsculas.
     * 
     * @param array             Array donde se realiza la búsqueda
     * @param elemento          Elemento a buscar
     * @return
     *   <ul>
     *     <li> n >= 0          Posición en la que se encontró el elemento
     *     <li> -1              El elemento no figura en el array
     *   </ul>
     */
    public static int enArrayIgnoreCase(String [] array, String elemento) {
        if (array == null) return -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equalsIgnoreCase(elemento)) return i;
        }
        return -1;
    }
    

    /**
     * Busca si en un String [][] existe un elemento determinado.
     * Si hubiera más de una coincidencia, retorna la primera encontrada.
     * 
     * @param array             Array donde se realiza la búsqueda
     * @param elemento          Elemento a buscar
     * @param indice            Índice de la 2ª dimensión donde se buscará
     * @return
     *   <ul>
     *     <li> n >= 0          Posición en la que se encontró el elemento
     *     <li> -1              El elemento no figura en el array
     *   </ul>
     */
    public static int enArray(String [][] array, String elemento, int indice) {
        if (array == null) return -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i][indice].equals(elemento)) return i;
        }
        return -1;
    }

    
    /**
     * Busca si en un String [][] existe un elemento determinado.
     * Si hubiera más de una coincidencia, retorna la primera encontrada.
     * La búsqueda es insensible a mayúsculas y minúsculas.
     * 
     * @param array             Array donde se realiza la búsqueda
     * @param elemento          Elemento a buscar
     * @param indice            Índice de la 2ª dimensión donde se buscará
     * @return
     *   <ul>
     *     <li> n >= 0          Posición en la que se encontró el elemento
     *     <li> -1              El elemento no figura en el array
     *   </ul>
     */
    public static int enArrayIgnoreCase(String [][] array, String elemento, int indice) {
        if (array == null) return -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i][indice].equalsIgnoreCase(elemento)) return i;
        }
        return -1;
    }

    
    /**
     * Justifica una cadena con caracteres a la izquierda.
     *
     * @param cadena                        Cadena a justificar
     * @param longitud                      Longitud final de la cadena
     * @param caracter                      Caracter a usar para justificar
     *
     * @return                              Cadena justificada
     */
    public static String justificarIzquierda(String cadena, int longitud, String caracter) {
        if (cadena == null) cadena = "";
        while (cadena.length() < longitud) cadena = caracter + cadena;
        return cadena;
    }


    /**
     * Justifica una cadena con caracteres a la derecha.
     *
     * @param cadena                        Cadena a justificar
     * @param longitud                      Longitud final de la cadena
     * @param caracter                      Caracter a usar para justificar
     *
     * @return                              Cadena justificada
     */
    public static String justificarDerecha(String cadena, int longitud, String caracter) {
        if (cadena == null) cadena = "";
        while (cadena.length() < longitud) cadena = cadena + caracter;
        return cadena;
    }


    /**
     * Justifica una cadena con caracteres a la izquierda y derecha.
     *
     * @param cadena                        Cadena a justificar
     * @param longitud                      Longitud final de la cadena
     * @param caracter                      Caracter a usar para justificar
     *
     * @return                              Cadena justificada
     */
    public static String justificarCentro(String cadena, int longitud, String caracter) {
        if (cadena == null) cadena = "";
        boolean izquierda = true;
        while (cadena.length() < longitud) {
            if (izquierda) cadena = caracter + cadena;
            else cadena = cadena + caracter;

            izquierda = !izquierda;
        }
        return cadena;
    }


    /**
     * Justifica una cadena con espacios a la izquierda.
     *
     * @param cadena                        Cadena a justificar
     * @param longitud                      Longitud final de la cadena
     *
     * @return                              Cadena justificada
     */
    public static String justificarIzquierda(String cadena, int longitud) {
        String caracter = " ";
        return justificarIzquierda(cadena, longitud, caracter);

    }


    /**
     * Justifica una cadena con espacios a la derecha.
     *
     * @param cadena                        Cadena a justificar
     * @param longitud                      Longitud final de la cadena
     *
     * @return                              Cadena justificada
     */
    public static String justificarDerecha(String cadena, int longitud) {
        String caracter = " ";
        return justificarDerecha(cadena, longitud, caracter);

    }


    /**
     * Justifica una cadena con espacios a la izquierda y derecha.
     *
     * @param cadena                        Cadena a justificar
     * @param longitud                      Longitud final de la cadena
     *
     * @return                              Cadena justificada
     */
    public static String justificarCentro(String cadena, int longitud) {
        String caracter = " ";
        return justificarCentro(cadena, longitud, caracter);
    }


    /**
     * Retorna una cadena formada por una secuencia de "n" caracteres.
     *
     * @param c                             Caracter de la secuencia
     * @param r                             Número de repeticiones
     * 
     * @return                              Cadena generada
     */
    public static String repetirCaracter(String c, int r)
    {
        StringBuilder s = new StringBuilder("");
        while (s.length() < r) s.append(c);
        return s.toString();
    }
    

    /**
     * Elimina los valores nulos de un String [], susitituyéndolos por cadenas vacías "".
     *
     * @param array_original                String [] original
     * @return                              String [] cuyos nulos han sido sustituidos por cadenas vacías
    */
    public static String [] eliminarNulos(String [] array_original) {
        if (array_original == null) return null;

        String [] result = new String [array_original.length];
        for (int i = 0; i < array_original.length; i++) {
            if (array_original[i] ==  null) result[i] = "";
            else result[i] = array_original[i];
        }

        return result;
    }


    /**
     * Elimina los valores nulos de un String [][], susitituyéndolos por cadenas vacías "".
     *
     * @param array_original                String [][] original
     * @return                              String [][] cuyos nulos han sido sustituidos por cadenas vacías
     */
    public static String [][] eliminarNulos(String [][] array_original) {
        if (array_original == null) return null;
        if (array_original[0] == null) return null;

        String [][] result = new String [array_original.length][array_original[0].length];

        for (int i = 0; i < array_original.length; i++) {
            for (int j = 0; j < array_original[0].length; j++) {
                if (array_original[i][j] ==  null) result[i][j] = "";
                else result[i][j] = array_original[i][j];
            }
        }

        return result;
    }

    
    /**
     * Hace un trim() a una cadena. Si la cadena es 'null' no provoca error.
     *
     * @param cadena                        Cadena original
     * @return                              Cadena con el trim
     */
    public static String trim(String cadena) {
        if (cadena == null) return null;
        return cadena.trim();
    }
    

    /**
     * Hace un trim() a todos los elementos de un String [].
     *
     * @param array_original                String [] original
     * @return                              String [] con el trim() de los elementos originales
     */
    public static String [] trim(String [] array_original) {
        if (array_original == null) return null;

        String [] result = new String [array_original.length];
        for (int i = 0; i < array_original.length; i++) {
            if (array_original[i] == null) result[i] = null;
            else result[i] = array_original[i].trim();
        }

        return result;
    }


    /**
     * Hace un trim() a todos los elementos de un String [][].
     *
     * @param array_original                String [][] original
     * @return                              String [][] con el trim() de los elementos originales
     */
    public static String [][] trim(String [][] array_original) {
        if (array_original == null) return null;
        if (array_original.length == 0) return array_original;
        if (array_original[0] == null) return null;

        String [][] result = new String [array_original.length][array_original[0].length];

        for (int i = 0; i < array_original.length; i++) {
            for (int j = 0; j < array_original[0].length; j++) {
                if (array_original[i][j] == null) result[i][j] = null;
                else result[i][j] = array_original[i][j].trim();
            }
        }

        return result;
    }


    /**
     * Convierte en mayúsculas todos los elementos de un String [].
     *
     * @param array_original                String [] original
     * @return                              String [] con los elementos en mayúsculas
     */
    public static String [] toUpperCase(String [] array_original) {
        if (array_original == null) return null;

        String [] result = new String [array_original.length];
        for (int i = 0; i < array_original.length; i++) {
            if (array_original[i] == null) result[i] = null;
            else result[i] = array_original[i].toUpperCase();
        }

        return result;
    }



    /**
     * Convierte en mayúsculas todos los elementos de un String [][].
     *
     * @param array_original                String [][] original
     * @return                              String [][] con los elementos en mayúsculas
     */
    public static String [][] toUpperCase(String [][] array_original) {
        if (array_original == null) return null;
        if (array_original[0] == null) return null;

        String [][] result = new String [array_original.length][array_original[0].length];

        for (int i = 0; i < array_original.length; i++) {
            for (int j = 0; j < array_original[0].length; j++) {
                if (array_original[i][j] == null) result[i][j] = null;
                else result[i][j] = array_original[i][j].toUpperCase();
            }
        }

        return result;
    }
    

    /**
     * Comprueba si una cadena dada representa un número entero.
     *
     * @param cadena                Cadena
     * @return                      'true' si la cadena representa un entero, 'false' si no
     */
    public static boolean esEntero(String cadena)
    {
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if (i == 0 && c == '-') continue;
            if (c < '0' || c > '9') return false;
        }

        return true;
    }


    /**
     * Comprueba si una cadena dada representa un número entero positivo.
     *
     * @param cadena                Cadena
     * @return                      'true' si la cadena representa un entero, 'false' si no
     */
    public static boolean esEnteroPositivo(String cadena)
    {
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if (c < '0' || c > '9') return false;
        }

        return true;
    }


    /**
     * Comprueba si una cadena de entrada representa un número, entero o en coma flotante.
     * Para la comprobación se utiliza un expresión regular.
     *
     * @param cadena                Cadena con un posible número, por ejemplo -8234.06
     * @return                      'true' si la cadena contiene un número
     *                              'false' si no
     */
    public static boolean esNumero(String cadena) {
        if (cadena.length() == 0) return false;
        // Regex para reconocer un número en coma flotante tipo, -8234.06
        return cadena.matches("[\\-\\+]?[0-9]*\\.?[0-9]*");
    }


    /**
     * Comprueba si un carácter de entrada contiene un número.
     *
     * @param c                     Carácter con un posible número
     * @return                      'true' si el carácter contiene un número
     *                              'false' si no
     */
    public static boolean esNumero(char c) {
        if (c >= '0' && c <= '9') return true;
        return false;
    }


    /**
     * Comprueba si un carácter de entrada contiene una letra de la A a la Z.
     * Los acentos y eñe también se consideran letras.
     *
     * @param c                     Carácter con la posible letra
     * @return                      'true' si el carácter contiene una letra
     *                              'false' si no
     */
    public static boolean esLetra(char c) {
        if (c >= 'A' && c <= 'Z') return true;
        if (c >= 'a' && c <= 'z') return true;
        if (c == 'Ñ' || c == 'ñ') return true;
        if (c == 'Á' || c == 'á') return true;
        if (c == 'É' || c == 'é') return true;
        if (c == 'Í' || c == 'í') return true;
        if (c == 'Ó' || c == 'ó') return true;
        if (c == 'Ú' || c == 'ú' || c == 'Ü' || c == 'ü') return true;

        return false;
    }


    /**
     * Comprueba si un carácter de es una letra vocal.
     *
     * @param c                     Carácter con la posible letra
     * @return                      'true' si el carácter contiene una vocal
     *                              'false' si no
     */
    public static boolean esVocal(char c) {
        if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') return true;
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') return true;

        if (c == 'Á' || c == 'á') return true;
        if (c == 'É' || c == 'é') return true;
        if (c == 'Í' || c == 'í') return true;
        if (c == 'Ó' || c == 'ó') return true;
        if (c == 'Ú' || c == 'ú' || c == 'Ü' || c == 'ü') return true;

        return false;
    }


    /**
     * Comprueba si un carácter de es una letra consonante.
     *
     * @param c                     Carácter con la posible letra
     * @return                      'true' si el carácter contiene una consonante
     *                              'false' si no
     */
    public static boolean esConsonante(char c) {
        if (!esLetra(c)) return false;
        if (esVocal(c)) return false;
        return true;
    }


    /**
     * Dada una cadena, obtiene otra igual en la que se han reemplazado ciertos caracteres por otros.
     *
     * @param cadena                        Cadena original
     * @param caracteres_originales         Caracteres a reemplazar
     * @param caracteres_finales            Caracteres por los que serán reemplazados (El array debe tener la misma longitud)
     * 
     * @return                              Cadena sin acentos ni diéresis
     */
    public static String reemplazarCaracteres(String cadena, char [] caracteres_originales, char [] caracteres_finales) {

        StringBuilder r = new StringBuilder();
        
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            char nuevo_c;
            
            int pos = Comun.enArray(caracteres_originales, c);
            if (pos == -1) {
                nuevo_c = c;
            } else {
                nuevo_c = caracteres_finales[pos];
            }
            
            r.append(nuevo_c);
        }
        
        return r.toString();
    }
    
    
    /**
     * Dada una cadena, obtiene otra igual pero en la que se han reemplazado los acentos y diéresis por las vocales equivalentes sin estas puntuaciones.
     * La Ñ la respeta.
     *
     * Ejemplo: Ángel -> Angel
     *          Cigüeña -> Cigueña
     *
     * @param cadena                        Cadena original
     * @return                              Cadena sin acentos ni diéresis
     */
    public static String quitarAcentos(String cadena) {

        char caracteres_originales [] = {'Á', 'É', 'Í', 'Ó', 'Ú', 'Ü', 'á', 'é', 'í', 'ó', 'ú', 'ü'};
        char caracteres_finales []    = {'A', 'E', 'I', 'O', 'U', 'U', 'a', 'e', 'i', 'o', 'u', 'u'};

        String s = reemplazarCaracteres(cadena, caracteres_originales, caracteres_finales);
        return s;
    }
    
    
    /**
     * Dada una cadena, obtiene otra igual pero en la que se han reemplazado los acentos y diéresis por las vocales equivalentes sin estas puntuaciones.
     * La Ñ es sustituida por la N.
     *
     * Ejemplo: Ángel -> Angel
     *          Cigüeña -> Cigueña
     *
     * @param cadena                        Cadena original
     * @return                              Cadena sin acentos ni diéresis
     */
    public static String quitarAcentosYEñes(String cadena) {

        char caracteres_originales [] = {'Á', 'É', 'Í', 'Ó', 'Ú', 'Ü', 'á', 'é', 'í', 'ó', 'ú', 'ü', 'ñ', 'Ñ'};
        char caracteres_finales []    = {'A', 'E', 'I', 'O', 'U', 'U', 'a', 'e', 'i', 'o', 'u', 'u', 'n', 'N'};

        String s = reemplazarCaracteres(cadena, caracteres_originales, caracteres_finales);
        return s;
    }


    /**
     * Comprueba si una cadena contiene a otra en su interior. Se ignoran las mayúsculas y minúsculas.
     *
     * @param cadena                        Cadena grande
     * @param subcadena                     Cadena a buscar
     *
     * @return                              'true' si la cadena grande contiene a la subcadena
     *                                      'false' si no
     */
    public static boolean contiene(String cadena, String subcadena) {

        if (Comun.vacio(subcadena)) return true;
        if (Comun.vacio(cadena)) return false;

        cadena = quitarAcentos(cadena);
        subcadena = quitarAcentos(subcadena);

        String regex = "(?i).*" + subcadena + ".*";
        boolean encontrado = false;
        try {
            encontrado = cadena.matches(regex);
        } catch (java.util.regex.PatternSyntaxException psex) {
            psex.printStackTrace();
        }
        return encontrado;
    }


    /**
     * Transforma un array de elementos a un String con los elementos del array
     * separados por el separador dado.
     * Los elementos del String son encerrados entre comillas o el caracter que se elija para encerrarlos.
     * Ejemplo: {1, 2, 3}  =>   "'1'|'2'|'3'" (usando el separador "|" y el encerrador "'")
     * Si el array de entrada es nulo o vacío, retorna la cadena vacía.
     *
     * @param array                         Array con los elementos
     * @param separador                     Carácter (o caracteres) separador
     * @param encerrador                    Carácter (o caracteres) para encerrar a cada elemento de la lista
     *
     * @return                              String con los elementos separados
     */
    public static String array2Lista(String [] array, String separador, String encerrador) {
        StringBuilder cadena = new StringBuilder("");
        if (array == null || array.length == 0) return "";

        boolean primero = true;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) continue;

            if (!primero) cadena.append(separador);
            cadena.append(encerrador + array[i] + encerrador);
            primero = false;
        }

        return cadena.toString();
    }


    /**
     * Transforma un array de elementos a un String con los elementos del array
     * separados por el separador dado.
     * Los elementos del String son encerrados entre comillas o el caracter que se elija para encerrarlos.
     * Ejemplo: {1, 2, 3}  =>   "'1'|'2'|'3'" (usando el separador "|" y el encerrador "'")
     * Si el array de entrada es nulo o vacío, retorna la cadena vacía.
     *
     * @param array                         Array con los elementos
     * @param separador                     Carácter (o caracteres) separador
     * @param encerrador                    Carácter (o caracteres) para encerrar a cada elemento de la lista
     *
     * @return                              String con los elementos separados
     */
    public static String array2Lista(int [] array, String separador, String encerrador) {
        StringBuilder cadena = new StringBuilder("");
        if (array == null || array.length == 0) return "";

        boolean primero = true;
        for (int i = 0; i < array.length; i++) {
            if (!primero) cadena.append(separador);
            cadena.append(encerrador + Integer.toString(array[i]) + encerrador);
            primero = false;
        }

        return cadena.toString();
    }


    /**
     * Transforma una lista de elementos a un String con dichos elementos separados por el separador dado.
     * Los elementos del String son encerrados entre comillas o el caracter que se elija para encerrarlos.
     * Ejemplo: {1, 2, 3}  =>   "'1'|'2'|'3'" (usando el separador "|" y el encerrador "'")
     * Si el array de entrada es nulo o vacío, retorna la cadena vacía.
     *
     * @param l_lista                       List con los elementos
     * @param separador                     Carácter (o caracteres) separador
     * @param encerrador                    Carácter (o caracteres) para encerrar a cada elemento de la lista
     *
     * @return                              String con los elementos separados
     */
    public static String list2Lista(List <String> l_lista, String separador, String encerrador) {

        String [] lista = Comun.list2String(l_lista);
        String result = array2Lista(lista, separador, encerrador);
        return result;
    }

    /**
     * Transforma un array de elementos a un String con los elementos del array
     * separados por el separador dado.
     * Ejemplo: {1, 2, 3}  =>   "1|2|3" (usando el separador "|")
     * Si el array de entrada es nulo o vacío, retorna la cadena vacía.
     *
     * @param array                         Array con los elementos
     * @param separador                     Carácter (o caracteres) separador
     * @return                              String con los elementos separados
     */
    public static String array2Lista(String [] array, String separador) {
        String lista = array2Lista(array, separador, "");
        return lista;
    }


    /**
     * Transforma un array de elementos a un String con los elementos del array
     * separados por el separador dado.
     * Ejemplo: {1, 2, 3}  =>   "1|2|3" (sin las comillas) (usando el separador "|")
     * Si el array de entrada es nulo o vacío, retorna la cadena vacía.
     *
     * @param array                         Array con los elementos
     * @param separador                     Carácter (o caracteres) separador
     * @return                              String con los elementos separados
     */
    public static String array2Lista(int [] array, String separador) {
        String lista = array2Lista(array, separador, "");
        return lista;
    }

    
    /**
     * Genera una cadena con el contenido del array suministrado.
     * 
     * @param array                         Array con los elementos
     * 
     * @return                              Cadena generada
     */
    public static String array2String(Object array[]) {
        StringBuilder s = new StringBuilder();
                
        for (int i = 0; i < Comun.length(array); i++) {
            Object o = array[i];
            s.append(i + ": ");
            if (o == null) s.append("null");
            else s.append(o.toString());
            s.append(NL);
        }
        
        return s.toString();
    }
    
    
    /**
     * Genera una cadena con el contenido del array suministrado.
     * 
     * @param array                         Array con los elementos
     * 
     * @return                              Cadena generada
     */
    public static String array2String(Object array[][]) {
        StringBuilder s = new StringBuilder();
                
        for (int i = 0; i < length(array); i++) {
            s.append(i + ": ");
            Object o2 = array[i];
            if (o2 == null) s.append("null");
            else {
                for (int j = 0; j < length(array[i]); j++) {
                    if (j > 0) s.append(" , ");
                    
                    Object o = array[i][j];                    
                    if (o == null) s.append("null");
                    else s.append(o.toString());
                }
            }
            s.append(NL);
        }
        
        return s.toString();
    }
    
    
    /**
     * Transforma una lista de elementos a un String con dichos elementos separados por el separador dado.
     * Los elementos del String son encerrados entre comillas o el caracter que se elija para encerrarlos.
     * Ejemplo: {1, 2, 3}  =>   "'1'|'2'|'3'" (usando el separador "|" y el encerrador "'")
     * Si el array de entrada es nulo o vacío, retorna la cadena vacía.
     *
     * @param l_lista                       List <String> con los elementos
     * @param separador                     Carácter (o caracteres) separador
     *
     * @return                              String con los elementos separados
     */
    public static String list2Lista(List <String> l_lista, String separador) {
        String result = list2Lista(l_lista, separador, "");
        return result;
    }


    /**
     * Concatena todos los elementos de un String [] en una única cadena.
     *
     * @param array                         String [] con los elementos a concatenar
     * @return                              Cadena resultado de concatenar los elementos
     */
    public static String concatenarArray(String [] array) {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < length(array); i++) {
            if (array[i] != null) s.append(array[i]);
        }

        return s.toString();
    }


    /**
     * Elimina caracteres no deseados dentro de una cadena.
     *
     * @param cadena                        Cadena original
     * @param piojos                        Cadena con los caracteres no deseados
     * @return                              Nueva cadena donde se han eliminado los caracteres indeseados
     */
    public static String despiojar(String cadena, String piojos) {
        if (cadena == null) return cadena;

        String result = "";
        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);
            if (piojos.indexOf(caracter) == -1) result = result + caracter;

        }
        return result;
    }


    /**
     * Obtiene el tamaño de un int [], aceptando incluso que el vector sea 'null'.
     *
     * @param array                         Array del que se va a obtener el tamaño
     * @return                              Tamaño del array (0, si el vector es 'null')
     */
    public static int length(int [] array) {
        if (array == null) return 0;
        return array.length;
    }


    /**
     * Obtiene el tamaño de un String [], aceptando incluso que el vector sea 'null'.
     *
     * @param array                         Array del que se va a obtener el tamaño
     * @return                              Tamaño del array (0, si el vector es 'null')
     */
    public static int length(String [] array) {
        if (array == null) return 0;
        return array.length;
    }


    /**
     * Obtiene el tamaño de un Object [], aceptando incluso que el vector sea 'null'.
     *
     * @param array                         Array del que se va a obtener el tamaño
     * @return                              Tamaño del array (0, si el vector es 'null')
     */
    public static int length(Object [] array) {
        if (array == null) return 0;
        return array.length;
    }


    /**
     * Obtiene el tamaño de un String [][], aceptando incluso que el vector sea 'null'.
     *
     * @param array                         Array del que se va a obtener el tamaño
     * @return                              Tamaño del array (0, si el vector es 'null')
     */
    public static int length(String [][] array) {
        if (array == null) return 0;
        return array.length;
    }


    /**
     * Obtiene el tamaño de una lista, aceptando incluso que sea 'null'.
     *
     * @param l                             List del que se va a obtener el tamaño
     * @return                              Tamaño de la lista (0, si es 'null')
     */
    public static int size(List l) {
        if (l == null) return 0;
        return l.size();
    }


    /**
     * Transforma un List con elementos de tipo Integer, en un int [].
     *
     * @param l_int                         List con los int
     * @return                              int [] con los elementos del List original
     *                                      'null' si la lista original era 'null'
     */
    public static int [] list2Int(List <Integer> l_int) {

        if (l_int == null) return null;

        int [] result = new int[l_int.size()];
        for (int i = 0; i < l_int.size(); i++) {
            result[i] = l_int.get(i);
        }

        return result;
    }


    /**
     * Transforma un List con elementos de tipo String, en un String [].
     *
     * @param l_string                      ArrayList con los String
     * @return                              String [] con los elementos del List original
     *                                      'null' si la lista original era 'null'
     */
    public static String [] list2String(List <String> l_string) {

        if (l_string == null) return null;

        String [] result = new String[l_string.size()];
        for (int i = 0; i < l_string.size(); i++) {
            result[i] = l_string.get(i);
        }

        return result;
    }


    /**
     * Transforma un int [] en un List con elementos de tipo Integer.
     *
     * @param ints                          int [] con los elementos del List original
     * @return                              List <Integer> con los elementos
     *                                      'null' si el array de entrada era 'null'
     */
    public static List <Integer> int2List(int [] ints) {

        if (ints == null) return null;

        List <Integer> al_result = new ArrayList();
        for (int i = 0; i < ints.length; i++) {
            al_result.add(ints[i]);

        }
        return al_result;
    }


    /**
     * Transforma un String [] en un List con elementos de tipo String.
     *
     * @param strings                       String [] con los elementos del vector original
     * @return                              List <String> con los elementos
     *                                      'null' si el array de entrada era 'null'
     */
    public static List <String> string2List(String [] strings) {

        if (strings == null) return null;

        List <String> al_result = new ArrayList();
        for (int i = 0; i < strings.length; i++) {
            al_result.add(strings[i]);

        }
        return al_result;
    }


    /**
     * Transforma un Object [] en un List con elementos de tipo Object.
     *
     * @param objects                       Object [] con los elementos del vector original
     * @return                              List <Object> con los elementos
     *                                      'null' si el array de entrada era 'null'
     */
    public static List <Object> object2List(Object [] objects) {

        if (objects == null) return null;

        List <Object> al_result = new ArrayList();
        for (int i = 0; i < objects.length; i++) {
            al_result.add(objects[i]);

        }
        return al_result;
    }


    /**
     * A partir de un array de enteros, retorna un array de cadenas, con los mismos elementos.
     *
     * @param int_array                     Array de enteros
     * @return                              Array de cadenas
     *                                      'null' si el array de entrada era 'null'
     */
    public static String [] intArray2StringArray(int [] int_array) {
        if (int_array == null) return null;

        String [] string_array = new String[int_array.length];
        for (int i = 0; i < int_array.length; i++) {
            string_array[i] = Integer.toString(int_array[i]);
        }

        return string_array;
    }


    /**
     * A partir de un array de cadenas, cuyos elementos son enteros, retorna un array de enteros con los mismos elementos.
     *
     * @param string_array                  Array de cadenas
     * @return                              Array de enteros
     *                                      'null' si el array de entrada era 'null'
     */
    public static int [] stringArray2IntArray(String [] string_array) {
        if (string_array == null) return null;

        int [] int_array = new int[string_array.length];
        for (int i = 0; i < string_array.length; i++) {
            int_array[i] = Comun.toEntero(string_array[i]);
        }

        return int_array;
    }


    /**
     * Añade comillas simples al principio y al final de una cadena, siempre que esta no
     * está ya entrecomillada.
     *
     * @param cadena                        Cadena original
     * @return                              Cadena con comillas simples al principio y al final
     */
    public static String entrecomillar(String cadena) {
        if (cadena == null) return null;

        if (cadena.length() == 0) return "''";

        if (!primeros(cadena, 1).equals("'")) cadena = "'" + cadena;
        if (!ultimos(cadena, 1).equals("'")) cadena = cadena + "'";

        return cadena;
    }


    /**
     * Añade comillas simples al principio y al final de un número.
     *
     * @param numero                        Número
     * @return                              Cadena con comillas simples al principio y al final
     */
    public static String entrecomillar(int numero) {

        String cadena = "'" + Integer.toString(numero) + "'";
        return cadena;
    }


    /**
     * Convierte una cadena a un entero de modo que, si hay un error de conversión, retorna el valor NO_DEFINIDO.
     *
     * @param cadena                            Cadena que contiene el entero
     * @return                                  Entero
     */
     public static int toEntero(String cadena) {
         int n;

         try {
             n = Integer.parseInt(cadena.trim());
         } catch (Exception ex) {
             n = Comun.NO_DEFINIDO;
         }

         return n;
     }


    /**
     * Convierte una cadena a un booleano, de acuerdo a estas condiciones:
     * 
     *      cadena = null           --> false
     *      cadena = ""             --> false
     *      cadena = "0"            --> false
     *      cadena = "-1"           --> false
     *      cadena = "alfabético"   --> false
     *      cadena = "n"            --> true (n es un número entero, distinto de 0 y 1)
     *
     * @param cadena                            Cadena que contiene el valor
     * 
     * @return                                  Booleano
     */
     public static boolean toBoolean(String cadena) {
         
         boolean b;
         
         int n;
         try {
             n = Integer.parseInt(cadena.trim());
             if (n == 0 || n == -1) {
                 return false;
             }
             
             return true;
             
         } catch (Exception ex) {
             return false;
         }
     }
     
     
    /**
     * Convierte una cadena a un entero de modo que, si hay un error de conversión, retorna el valor predeterminado.
     *
     * @param cadena                            Cadena que contiene el entero
     * @param valor_predeterminado              Valor a retornar en caso de error
     *
     * @return                                  Entero
     */
     public static int toEntero(String cadena, int valor_predeterminado) {
         int n;

         try {
             n = Integer.parseInt(cadena.trim());
         } catch (Exception ex) {
             n = valor_predeterminado;
         }

         return n;
     }


    /**
     * Convierte una cadena a un entero largo de modo que, si hay un error de conversión, retorna el valor NO_DEFINIDO.
     *
     * @param cadena                            Cadena que contiene el entero
     * @return                                  Entero largo
     */
     public static long toLong(String cadena) {
         long n;

         try {
             n = Long.parseLong(cadena);
         } catch (NumberFormatException ex) {
             n = Comun.NO_DEFINIDO;
         }

         return n;
     }

     
    /**
     * Convierte una cadena a un entero largo de modo que, si hay un error de conversión, retorna el valor predeterminado.
     *
     * @param cadena                            Cadena que contiene el entero
     * @param valor_predeterminado              Valor a retornar en caso de error
     *
     * @return                                  Entero
     */
     public static long toLong(String cadena, long valor_predeterminado) {
         long n;

         try {
             n = Long.parseLong(cadena);
         } catch (Exception ex) {
             n = valor_predeterminado;
         }

         return n;
     }
     

    /**
     * Convierte una cadena a un double de modo que, si hay un error de conversión,
     * retorna el valor NO_DEFINIDO_DOUBLE.
     *
     * @param cadena                            Cadena que contiene el double
     * @return                                  Double
     */
     public static double toDouble(String cadena) {
         double n;

         try {
             n = Double.parseDouble(cadena);
         } catch (NumberFormatException ex) {
             n = Comun.NO_DEFINIDO_DOUBLE;
         }

         return n;
     }


    /**
     * Convierte un entero a una cadena de modo que, si el entero es el valor NO_DEFINIDO,
     * retorna la cadena vacía.
     *
     * @param entero                            Entero a pasar a cadena
     * @return                                  Cadena
     */
     public static String toString(int entero) {
         if (entero == Comun.NO_DEFINIDO) return "";
         return Integer.toString(entero);
     }

     
    /**
     * Convierte un entero largo a una cadena de modo que, si el entero es el valor NO_DEFINIDO,
     * retorna la cadena vacía.
     *
     * @param entero                            Entero a pasar a cadena
     * @return                                  Cadena
     */
     public static String toString(long entero) {
         if (entero == Comun.NO_DEFINIDO) return "";
         return Long.toString(entero);
     }
     
   
    /**
     * Concatena varias cadenas en una sola.
     * Las cadenas concatendas van separadas por un espacio.
     *
     * @param cadena                            Cadenas a concatenar
     * 
     * @return                                  Texto con la concatenación
     */
    public static String concatenarCadenas(String ... cadena) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < cadena.length; i++) {
            s.append(trim(cadena[i]));
            s.append(" ");
        }
        
        String result = s.toString().trim();
        return result;
    }


    /**
     * Reemplaza todas las ocurrencias de un texto dentro de una cadena por otro texto.
     * Esta función se comporta como el replaceAll nativo de Java, pero no trata con expresiones regulares.
     * Sirve para paliar los problemas del replaceAll de Java con ciertos caracteres poco habituales, que dan errores de conversión.
     * La búsqueda del texto es sensible a las mayúsculas / minúsculas.
     * Ejemplos:
     *     replaceAll("pepito conejito", "ito", "?")    --> "pep? conej?"
     *     replaceAll("xxxxxxx", "x", "y")              --> "yyyyyy"
     *     replaceAll("xxxxxxx", "x", "yy")             --> "yyyyyyyyyyyy"
     *     replaceAll("xxxxxxx", "xx", "y")             --> "yyyx"
     *
     * @param cadena_original               Cadena original
     * @param texto_buscar                  Texto a buscar
     * @param texto_reemplazo               Texto por el que se va a reemplazar el texto a buscar
     *
     * @return                              Nueva cadena
     */
    public static String replaceAll(String cadena_original, String texto_buscar, String texto_reemplazo) {
        int lb = texto_buscar.length();
        int lr = texto_reemplazo.length();

        String result = cadena_original;
        if (result == null) {
            return null;
        }
        
        for (int i = 0; i < result.length() - lb + 1; i++) {
            String subcadena = miSubstringLong(result, i, lb);
            if (subcadena.equals(texto_buscar)) {
                result = reemplazarPosicion(result, i, lb, texto_reemplazo);
                i += lr - 1;
            }
        }

        return result;
    }
    

    /**
     * Inserta el contenido dinámico, almacenados en un Vector <String> dentro de una cadena.
     * La cadena de entrada contiene etiquetas de sustitución, delimitados por un caracter especial suministrado,
     * en formato: %0%, %1%, %2%..., suponiendo que el carácter de sustitución es el "%".
     * Estas etiquetas son sustituidas por los valores en cada posición del vector.
     *
     * @param cadena_original               Cadena original (con las etiquetas)
     * @param l_tags                       List <String> con el contenido a introducir en cada etiqueta
     *                                      Si es 'null', no se toca el mensaje original
     * @param delimitador                   Carácter usado para delimitar los tags dentro de la cadena original
     *
     * @return                              La cadena original donde se han sustituido las etiquetas por los tags
     */
    public static String sustituirTagsCadena(String cadena_original, List <String> l_tags, String delimitador) {

        // Insertamos los textos dinámicos dentro del mensaje de error
        for (int i = 0; i < Comun.size(l_tags); i++) {
            String buscar = delimitador + Integer.toString(i) + delimitador;

            if (cadena_original.indexOf(buscar) != -1) {
                String t = l_tags.get(i);
                cadena_original = cadena_original.replace(buscar, t);
            }
        }

        return cadena_original;
    }

    
    /**
     * Inserta el contenido dinámico, almacenados en un Vector <String> dentro de una cadena.
     * La cadena de entrada contiene etiquetas de sustitución en formato: %0%, %1%, %2%...
     * Estas etiquetas son sustituidas por los valores en cada posición del vector.
     *
     * @param cadena_original               Cadena original (con las etiquetas)
     * @param l_tags                        List <String> con el contenido a introducir en cada etiqueta
     *                                      Si es 'null', no se toca el mensaje original
     *
     * @return                              La cadena original donde se han sustituido las etiquetas por los tags
     */
    public static String sustituirTagsCadena(String cadena_original, List <String> l_tags) {
        String delimitador = "%";
        String nueva_cadena = sustituirTagsCadena(cadena_original, l_tags, delimitador);
        return nueva_cadena;
    }

}

