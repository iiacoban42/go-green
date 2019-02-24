package Client;

public class ControllerHttp {
    private String message;

    public ControllerHttp(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}