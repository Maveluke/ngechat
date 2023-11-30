package use_case.send_message;

public class SendMessageInputData {

    private String messageText;

    private String sender;

    private String friendName;


    public SendMessageInputData(String messageText, String sender, String friendName) {
        this.messageText = messageText;
        this.sender = sender;
        this.friendName = friendName;
    }


    public String getMessageText() {
        return messageText;
    }

    public String getSender() {
        return sender;
    }

    public String getFriendName() {
        return friendName;
    }
}
