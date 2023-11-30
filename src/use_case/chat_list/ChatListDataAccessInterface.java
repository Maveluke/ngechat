package use_case.chat_list;

import java.util.ArrayList;
import java.util.HashMap;

public interface ChatListDataAccessInterface {


    public HashMap<String, ArrayList<String>> getChats();


    // implement this by checking if getChat().is_empty() is true
    public boolean chatIsEmpty();

    // Not necessary for now, might need it later
    public void deleteChat();

    void updateChatWithBinID(String username, String binID);
}
