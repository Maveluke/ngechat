package use_case.create_chat;

public class CreateChatInputData {

    private String username;

    public CreateChatInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
