package exception;

public class CreateRoomException extends Exception{
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