package Client;

public class ControllerHTTP {
    private String message;
    public ControllerHTTP(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString(){
        return message;
    }
}