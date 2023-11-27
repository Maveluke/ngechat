package use_case.create_chat;

import entity.Chat;

public interface CreateChatDataAccessInterface {

    boolean chatExist(String targetUser);

    String getBinID(String friendUsername);

    void addChat(String friendUsername, Chat chat);
}

