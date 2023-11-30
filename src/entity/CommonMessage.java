package entity;

import java.time.LocalDateTime;

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

    public LocalDateTime getTimeSent() {
        return timeSent;
    }

    public String getSender() {
        return this.senderUsername;
    }
}
