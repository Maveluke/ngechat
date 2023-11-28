package entity;

import java.util.ArrayList;

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
    public CommonMessage getLastMessage() {
        return (CommonMessage) messages.get(messages.size() - 1).get(1);
    }

    public void addMessage(String senderUsername, CommonMessage commonMessage){
        ArrayList<Object> newMessage = new ArrayList<>();
        newMessage.add(senderUsername);
        newMessage.add(commonMessage);
        this.messages.add(newMessage);
    }

    public String getBinID() {
        return binID;
    }

    public ArrayList<ArrayList<Object>> getMessages() {
        return messages;
    }

}

