package data_access;

import entity.User;
import use_case.chat_list.ChatListDataAccessInterface;
import use_case.create_chat.CreateChatDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatListDataAccessObject implements ChatListDataAccessInterface, CreateChatDataAccessInterface {

    private String currentUsername;

    @Override
    public HashMap<String, ArrayList<String>> getChats() {
        return null;
    }

    @Override
    public boolean chatIsEmpty() {
        return false;
    }

    @Override
    public void deleteChat() {
    }

    @Override
    public boolean chatExist(String targetUser) {
        for (String key : getChats().keySet()) {
            if (key.equals(targetUser)) {
                return true;
            }
        }
        return false;
    }

}