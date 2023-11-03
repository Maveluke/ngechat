package use_case.LogIn;

public class LogInInputData {

    private final String Username;
    private String Password;

    public LogInInputData(String Username, String Password) {
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
