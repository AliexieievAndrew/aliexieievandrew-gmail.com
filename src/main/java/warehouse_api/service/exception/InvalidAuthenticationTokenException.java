package warehouse_api.service.exception;

public class InvalidAuthenticationTokenException extends RuntimeException {

    public InvalidAuthenticationTokenException(String message) {
        super(message);
    }
}
