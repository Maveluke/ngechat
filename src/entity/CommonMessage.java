package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonMessage implements Message{
    private final String message;
    private final LocalDateTime timeSent;
    private final String senderUsername;
    public CommonMessage(String message, LocalDateTime timeSent, String senderUsername){
        this.message = message;
        this.timeSent = timeSent;
        this.senderUsername = senderUsername;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeSent() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timeSent.format(formatter);
    }

    public String getSender() {
        return this.senderUsername;
    }
    @Override
    public String toString(){
        String ret = "";
        ret += this.message;
        ret += "\n";
        ret += this.timeSent;
        ret += "\n";
        return ret;
    }
}

