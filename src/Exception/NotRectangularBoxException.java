package exception;

public class NotRectangularBoxException extends Exception{
    public NotRectangularBoxException() {
    }

    public NotRectangularBoxException(String message) {
        super(message);
    }

    public NotRectangularBoxException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotRectangularBoxException(Throwable cause) {
        super(cause);
    }
}
