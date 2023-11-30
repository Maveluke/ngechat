package use_case.in_chat;

import java.util.ArrayList;

public class InchatOutputData {


    private ArrayList<ArrayList<Object>> messages;

    private String friendName;

    public InchatOutputData(ArrayList<ArrayList<Object>> messages, String friendName) {
        this.messages = messages;
        this.friendName = friendName;
    }

    public ArrayList<ArrayList<Object>> getMessages() {
        return messages;
    }

    public String getFriendName() {
        return friendName;
    }
}
