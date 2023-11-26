package interface_adapter.chat_list;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatListState {

    String currentUser;

    HashMap<String, ArrayList<String>> chatList;

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public HashMap<String, ArrayList<String>> getChatList(){
        return chatList;
    }

}
