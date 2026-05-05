package jcolonia.daw2025.world;

/**
 * Custom Exception para encapsular los errores de la capa de persistencia.
 * Aísla a las capas superiores de los detalles técnicos como SQLException.
 */
public class AccesoDatosException extends Exception {

    private static final long serialVersionUID = 1L;

   
    public AccesoDatosException(String message) {
        super(message);
    }

    
    public AccesoDatosException(String message, Throwable cause) {
        super(message, cause);
    }
}