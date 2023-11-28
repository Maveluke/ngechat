package entity;

import java.util.ArrayList;

public interface Chat {
    /***
     * This class represents a collection of messages.
     */
    String getName();

    CommonMessage getLastMessage();

    ArrayList<ArrayList<Object>> getMessages();
}
