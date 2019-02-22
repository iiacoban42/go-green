import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name="username")
    private String username;

    @Column(name="hash_password")
    private String hashPassword;

    public User(String username, String hashPassword){
        this.username = username;
        this.hashPassword = hashPassword;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

}
