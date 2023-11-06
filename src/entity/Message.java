package entity;

import java.time.LocalDateTime;

public abstract class Message {
    private final String message;
    private final LocalDateTime timeSent;
    private final User sender;
    public Message(String message, LocalDateTime timeSent, User sender){
        this.message = message;
        this.timeSent = timeSent;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimeSent() {
        return timeSent;
    }

    public User getSender() {
        return sender;
    }
}
