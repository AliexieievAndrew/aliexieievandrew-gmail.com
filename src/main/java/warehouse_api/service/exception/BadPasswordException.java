package warehouse_api.service.exception;

public class BadPasswordException extends Exception{
    public BadPasswordException(String msg) {
        super(msg);
    }
}
