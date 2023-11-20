package interface_adapter.add_contact;

public class AddContactState {
    private String username = "";
    private String usernameError = null;

    public AddContactState(AddContactState copy) {
        username = copy.username;
        usernameError = copy.usernameError;
    }

    public AddContactState() {}

    public String getUsername() {
        return username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }
}
