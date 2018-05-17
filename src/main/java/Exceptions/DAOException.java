package Exceptions;

// Une classe d'exceptions spécifiques pour le DAO
public class DAOException extends Exception {
    /**
     * 
     * @param message 
     */
    public DAOException(String message) {
        super(message);
    }

}
