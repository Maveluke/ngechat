package use_case.send_message;

import java.time.LocalDateTime;

public class SendMessageOutputData {

    String sender;

    LocalDateTime datetime;

    String text;

    public SendMessageOutputData(String text, LocalDateTime datetime, String sender) {
        this.text = text;
        this.sender = sender;
        this.datetime = datetime;
    }







}
