package Model.Template;

public class UserTemplate {

    public UserTemplate(int id, String name, String email, String nickName, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.nickName = nickName;
        this.password = password;
    }

    private int id;

    public UserTemplate(String name, String email, String nickName, String password) {
        this.name = name;
        this.email = email;
        this.nickName = nickName;
        this.password = password;
    }

    private String name;
    private String email;
    private String nickName;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickName(String username) {
        this.nickName = username;
    }
}
