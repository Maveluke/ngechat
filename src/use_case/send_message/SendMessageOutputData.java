package use_case.send_message;

import java.time.LocalDateTime;

public class SendMessageOutputData {

    private String sender;

    private String datetime;

    private String text;

    public SendMessageOutputData(String text, String datetime, String sender) {
        this.text = text;
        this.sender = sender;
        this.datetime = datetime;
    }

    public String getSender() {
        return sender;
    }

    public String getDatetime() {
        return datetime;
    }
}
