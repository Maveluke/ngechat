package use_case.chatlist;

import entity.Chat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class ChatListOutputData {

    private HashMap<String, Chat> ChatList;

    public ChatListOutputData(HashMap<String, Chat> chatList){
        this.ChatList = chatList;
    }

    public HashMap<String, Chat> getChatList(){
        return ChatList;
    }

}
