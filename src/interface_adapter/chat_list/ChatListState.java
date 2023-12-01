package interface_adapter.chat_list;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatListState {
    private String username = "Username";

    private HashMap<String, ArrayList<String>> chatList = new HashMap<String, ArrayList<String>>();
    private boolean updated = false;

    public ChatListState(){};

    public String getUsername() {
        return username;
    }

    public HashMap<String, ArrayList<String>> getChatList() {
        return chatList;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setChatList(HashMap<String, ArrayList<String>> chatList) {
        this.chatList = chatList;
    }
}
