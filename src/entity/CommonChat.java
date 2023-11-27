package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class CommonChat implements Chat{
    private final String binID;
    private ArrayList<ArrayList<Object>> messages; // Array of a pair consists of sender and message
    private String name; // friend's name

    public CommonChat(ArrayList<ArrayList<Object>> messages, String binID){
        this.messages = new ArrayList<>(messages);
        this.binID = binID;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Message getLastMessage() {
        return (Message) messages.get(messages.size() - 1).get(1);
    }

    public void addMessage(String senderUsername, Message message){
        ArrayList<Object> newMessage = new ArrayList<>();
        newMessage.add(senderUsername);
        newMessage.add(message);
        this.messages.add(newMessage);
    }

    public String getBinID() {
        return binID;
    }
}

