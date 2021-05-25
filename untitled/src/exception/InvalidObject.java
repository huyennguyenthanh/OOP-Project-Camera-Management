package exception;

public class InvalidObject extends Exception{
    public InvalidObject() {
    }

    public InvalidObject(String message) {
        super(message);
    }

    public InvalidObject(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidObject(Throwable cause) {
        super(cause);
    }
}