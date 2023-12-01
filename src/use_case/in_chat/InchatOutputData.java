package use_case.in_chat;

import java.util.ArrayList;

public class InchatOutputData {


    private ArrayList<ArrayList<Object>> messages;

    private String friendName;

    private String username;

    public InchatOutputData(ArrayList<ArrayList<Object>> messages, String friendName, String username) {
        this.messages = messages;
        this.friendName = friendName;
        this.username = username;
    }

    public ArrayList<ArrayList<Object>> getMessages() {
        return messages;
    }

    public String getFriendName() {
        return friendName;
    }

    public String getUsername() {
        return username;
    }
}
