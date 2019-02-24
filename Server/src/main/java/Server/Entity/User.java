package Server.Entity;

public class User {

    private int id;
    private String name;
    private  String password;

    /**
     *Constructor.
     */
    public User(int id , String name , String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public java.lang.String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public void setPassword(java.lang.String password) {
        this.password = password;
    }

}
