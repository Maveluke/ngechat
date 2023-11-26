package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class CommonChat implements Chat{
    private Message lastMessage;
    private final String binID;
    private HashMap<String, ArrayList<Message>> messages; // sender to message
    private String name; // friend's name

    public CommonChat(HashMap<String, ArrayList<Message>> messages, String binID){
        this.messages = new HashMap<>(messages);
        this.binID = binID;
    }
    @Override
    public String getName() {
        return name;
    }

    public void addMessage(String senderUsername, Message message){
        this.messages.get(senderUsername).add(message);
    }

    public String getBinID() {
        return binID;
    }
}

