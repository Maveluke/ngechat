package use_case.create_chat;

public class CreateChatOutputData {

    private String userToChat;
    private String sender;

    public CreateChatOutputData(String userToChat, String sender){
        this.userToChat = userToChat;
        this.sender = sender;
    }

    public String getUserToChat() {
        return userToChat;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
