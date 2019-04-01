package client.requests;

public class Session {

    private static Token token;

    public static Token getToken() {
        return token;
    }

    public static void setToken(Token token) {
        Session.token = token;
    }
}
