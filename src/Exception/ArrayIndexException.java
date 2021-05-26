package exception;

public class ArrayIndexException extends Exception{
    public ArrayIndexException() {
    }

    public ArrayIndexException(String message) {
        super(message);
    }

    public ArrayIndexException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArrayIndexException(Throwable cause) {
        super(cause);
    }
}
