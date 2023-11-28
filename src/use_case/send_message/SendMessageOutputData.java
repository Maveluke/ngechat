package use_case.send_message;

import java.time.LocalDateTime;

public class SendMessageOutputData {

    private String sender;

    private LocalDateTime datetime;

    private String text;

    public SendMessageOutputData(String text, LocalDateTime datetime, String sender) {
        this.text = text;
        this.sender = sender;
        this.datetime = datetime;
    }

    public String getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }
}
