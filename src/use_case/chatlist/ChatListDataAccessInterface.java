package use_case.chatlist;

import entity.Chat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public interface ChatListDataAccessInterface {


    public HashMap<String, ArrayList<String>> getChats();


    // implement this by checking if getChat().is_empty() is true
    public boolean is_empty();

    // Not necessary for now, might need it later
    public void deleteChat();


}