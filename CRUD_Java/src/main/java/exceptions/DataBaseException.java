package exceptions;

public class DataBaseException extends Exception {

    public DataBaseException(String message, Throwable throwable) {
        super(message,throwable);
    }
}
