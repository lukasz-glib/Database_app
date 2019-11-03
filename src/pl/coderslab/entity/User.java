package pl.coderslab.entity;

public class User {

    private Integer id;
    private String username;
    private String email;
    private String password;
    private Integer userGroupId;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password, Integer userGroupId) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userGroupId = userGroupId;
    }

    public User(Integer id, String username, String email, String password, Integer userGroupId) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userGroupId = userGroupId;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public void hashPassword(String password) {
        this.password = org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());
    }
}
