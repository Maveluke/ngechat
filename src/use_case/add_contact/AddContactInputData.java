package use_case.add_contact;

public class AddContactInputData {
    private final String friendUsername;

    public AddContactInputData(String friendUsername) {
        this.friendUsername = friendUsername;
    }

    public String getFriendUsername() {
        return friendUsername;
    }
}
