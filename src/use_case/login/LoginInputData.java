package use_case.login;

public class LoginInputData {

    private final String Username;
    private String Password;

    public LoginInputData(String Username, String Password) {
        this.Username = Username;
        this.Password = Password;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

}
