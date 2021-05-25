package exception;

public class InvalidObject extends Exception{

	private static final long serialVersionUID = 1L;

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