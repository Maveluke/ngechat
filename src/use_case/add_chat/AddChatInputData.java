package use_case.add_chat;

public class AddChatInputData {

    private String currentUsername;

    public AddChatInputData(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }
}
