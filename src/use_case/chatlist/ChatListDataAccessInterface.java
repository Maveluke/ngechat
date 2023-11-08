package use_case.chatlist;

import entity.Chat;

import java.util.ArrayList;
import java.util.HashMap;

public interface ChatListDataAccessInterface {

    public ArrayList<Chat> getChat();


    // implement this by checking if getChat().is_empty() is true
    public boolean is_empty();

    // Not necessary for now, might need it later
    public void deleteChat();


}
