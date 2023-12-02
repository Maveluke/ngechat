package use_case.in_chat;

public class InChatInputData {

    private String friendName;
    private String username;

    public InChatInputData(String friendName, String username) {
        this.friendName = friendName;
        this.username = username;
    }

    public String getFriendName() {
        return friendName;
    }

    public String getUsername() {
        return username;
    }
}
