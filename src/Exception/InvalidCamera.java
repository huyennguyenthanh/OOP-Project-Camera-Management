package exception;

public class InvalidCamera extends Exception{

	private static final long serialVersionUID = 1L;

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