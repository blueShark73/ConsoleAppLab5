package exceptions;

/**
 * id указанный в команде не найден
 */
public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(String message){
        super(message);
    }
}
