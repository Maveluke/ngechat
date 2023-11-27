package use_case.create_chat;

public class CreateChatOutputData {

    String userToChat;

    public CreateChatOutputData(String userToChat){
        this.userToChat = userToChat;
    }

    public String getUserToChat() {
        return userToChat;
    }
}
