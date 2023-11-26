package data_access;

import use_case.chat_list.ChatListDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatListDataAccessObject implements ChatListDataAccessInterface {


    @Override
    public HashMap<String, ArrayList<String>> getChats() {
        return null;
    }

    @Override
    public boolean is_empty() {
        return false;
    }

    @Override
    public void deleteChat() {

    }
}