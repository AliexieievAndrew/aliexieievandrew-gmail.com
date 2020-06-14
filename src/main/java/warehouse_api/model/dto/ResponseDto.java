package warehouse_api.model.dto;

public class ResponseDto {

    private int errorCode = 0;

    private Object info;

    public ResponseDto() {
    }

    public ResponseDto(Object info) {
        this.info = info;
    }

    public ResponseDto(int errorCode, Object info) {
        this.errorCode = errorCode;
        this.info = info;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }
}
