package use_case.chat_list;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatListOutputData {

    private HashMap<String, ArrayList<String>> ChatList;
    private boolean updated;

    public ChatListOutputData(HashMap<String, ArrayList<String>> chatList, boolean updated){
        this.ChatList = chatList;
        this.updated = updated;
    }

    public HashMap<String, ArrayList<String>> getChatList(){
        return ChatList;
    }

    public boolean isUpdated() {
        return updated;
    }

}


