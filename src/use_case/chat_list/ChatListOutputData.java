package use_case.chat_list;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatListOutputData {

    private HashMap<String, ArrayList<String>> ChatList;

    private String currentUserName;

    public ChatListOutputData(HashMap<String, ArrayList<String>> chatList, String currentUserName){
        this.ChatList = chatList;
        this.currentUserName = currentUserName;
    }

    public HashMap<String, ArrayList<String>> getChatList(){
        return ChatList;
    }

}


