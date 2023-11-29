package use_case.in_chat;

import java.util.ArrayList;

public class InchatOutputData {


    private ArrayList<ArrayList<Object>> messages;

    public InchatOutputData(ArrayList<ArrayList<Object>> messages) {
        this.messages = messages;
    }

    public ArrayList<ArrayList<Object>> getMessages() {
        return messages;
    }
}
