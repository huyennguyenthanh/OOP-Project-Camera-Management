package exception;

public class CreateRoomException extends Exception{

	private static final long serialVersionUID = 1L;

	public CreateRoomException() {
    }

    public CreateRoomException(String message) {
        super(message);
    }

    public CreateRoomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateRoomException(Throwable cause) {
        super(cause);
    }
}