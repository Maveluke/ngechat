package use_case.chatlist;

import entity.Chat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class ChatListOutputData {

    private ArrayList<Chat> ChatList;

    public ChatListOutputData(ArrayList<Chat> chatList){
        this.ChatList = chatList;
    }

    public ArrayList<Chat> getChatList(){
        return ChatList;
    }

}
