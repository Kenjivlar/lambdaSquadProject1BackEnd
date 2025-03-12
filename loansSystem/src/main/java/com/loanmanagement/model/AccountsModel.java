public class AccountsModel {
    private int id;
    private String email;
    private String password;
    private int idAccountType;

    public AccountsModel() {
    }

    public AccountsModel(String email, String password, int idAccountType) {
        this.email = email;
        this.password = password;
        this.idAccountType = idAccountType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdAccountType() {
        return idAccountType;
    }

    public void setIdAccountType(int idAccountType) {
        this.idAccountType = idAccountType;
    }
}