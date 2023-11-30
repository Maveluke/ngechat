package use_case.send_message;

public class SendMessageInputData {

    private String friendName;

    private String username;

    private String binID;

    public SendMessageInputData(String friendName, String username, String binID) {
        this.friendName = friendName;
        this.username = username;
        this.binID = binID;
    }

    public String getBinID() {
        return binID;
    }

    public String getFriendName() {
        return friendName;
    }

    public String getUsername() {
        return username;
    }
}