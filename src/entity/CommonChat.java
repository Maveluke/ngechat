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
    public Message getLastMessage() {
        return (Message) messages.get(messages.size() - 1).get(1);
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

        ArrayList<ArrayList<Object>> new_array = new ArrayList<>();
        for (int i = 0; i < this.messages.size(); i++) {
            String sender = (String) this.messages.get(i).get(0);
            Message message = (Message) this.messages.get(i).get(1);
            ArrayList<Object> inner_array = new ArrayList<>();
            ArrayList<Object> inner_inner_array = new ArrayList<>();

            // inserting all the attributes of message to the inner_inner_array
            inner_inner_array.set(0, message.getMessage());
            inner_inner_array.set(1, message.getTimeSent());
            inner_inner_array.set(2, message.getSender());

            inner_array.set(0, sender);
            inner_array.set(1, inner_inner_array);

            new_array.set(i, inner_array);
        }

        return new_array;
    }

}

