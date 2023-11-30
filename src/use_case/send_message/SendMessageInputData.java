package use_case.send_message;

public class SendMessageInputData {

    private String messageText;

    private String sender;

    private String binID;

    public SendMessageInputData(String messageText, String sender, String binID) {
        this.messageText = messageText;
        this.sender = sender;
        this.binID = binID;
    }

    public String getBinID() {
        return binID;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getSender() {
        return sender;
    }
}
