package interface_adapter.in_chat;

import java.util.ArrayList;

public class InChatPrivateState {

    // Don't forget to turn the message into something non-entity

    ArrayList<ArrayList<Object>> messages;

    String friendName;

    public InChatPrivateState(InChatPrivateState copy) {
        this.messages = copy.messages;
        this.friendName = copy.friendName;
    }

    public ArrayList<ArrayList<Object>> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<ArrayList<Object>> messages) {
        this.messages = messages;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }
}
