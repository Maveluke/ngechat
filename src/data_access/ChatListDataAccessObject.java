package data_access;

import entity.User;
import use_case.chat_list.ChatListDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatListDataAccessObject implements ChatListDataAccessInterface {


    @Override
    public HashMap<String, ArrayList<String>> getChats() {
        return null;
    }

    @Override
    public User getCurrentUser() {
        return null;
    }

    @Override
    public boolean chatIsEmpty() {
        return false;
    }

    @Override
    public void deleteChat() {

    }
}