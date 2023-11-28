package use_case.in_chat;

public class InChatInputData {

    private String friendName;

    public InChatInputData(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendName() {
        return friendName;
    }
}
