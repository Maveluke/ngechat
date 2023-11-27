package entity;

import java.util.ArrayList;

public class CommonChatFactory {

    public Chat create(ArrayList<ArrayList<Object>> messages, String binID){
        return new CommonChat(messages, binID);
    }

}
