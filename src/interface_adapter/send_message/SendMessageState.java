package interface_adapter.send_message;

import interface_adapter.in_chat.InChatPrivateState;

import java.util.ArrayList;

public class SendMessageState {


    String message;

    String dateTime;

    String sender;

    public SendMessageState(SendMessageState copy) {
        this.message = copy.message;
        this.dateTime = copy.dateTime;
        this.sender = copy.sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

}
