package use_case.chat_list;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatListOutputData {

    private HashMap<String, ArrayList<String>> ChatList;

    public ChatListOutputData(HashMap<String, ArrayList<String>> chatList){
        this.ChatList = chatList;
    }

    public HashMap<String, ArrayList<String>> getChatList(){
        return ChatList;
    }

}


