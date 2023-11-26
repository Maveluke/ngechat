package use_case.create_chat;

public interface CreateChatDataAccessInterface {

    boolean chatExist(String targetUser);


    void createChat(String userToChat);
}
