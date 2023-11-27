package interface_adapter.chat_list;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatListState {

    private String currentUser = "";

    private HashMap<String, ArrayList<String>> chatList = null;

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public HashMap<String, ArrayList<String>> getChatList(){
        return chatList;
    }

    public void setChatList(HashMap<String, ArrayList<String>> chatList) {
        this.chatList = chatList;
    }

}
