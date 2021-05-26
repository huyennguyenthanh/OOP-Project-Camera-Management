package exception;

public class ExistedObjectException extends Exception{
    public ExistedObjectException() {
    }

    public ExistedObjectException(String message) {
        super(message);
    }

    public ExistedObjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistedObjectException(Throwable cause) {
        super(cause);
    }
}
