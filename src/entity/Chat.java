package entity;

import java.util.ArrayList;

public interface Chat {
    /***
     * This class represents a collection of messages.
     */
    String getName();

    Message getLastMessage();

    ArrayList<ArrayList<Object>> getMessages();
}
