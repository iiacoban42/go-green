package server.entity;

public class RegisterCredentials {
    private String email;
    private String username;
    private String password;

    RegisterCredentials() {
        super();
    }

    /**
     * Constructs class.
     * @param email string
     * @param username string
     * @param password string
     */
    public RegisterCredentials(String email, String username, String password) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
