package Exception;

public class InvalidCamera extends Exception{
    public InvalidCamera() {
    }

    public InvalidCamera(String message) {
        super(message);
    }

    public InvalidCamera(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCamera(Throwable cause) {
        super(cause);
    }
}
