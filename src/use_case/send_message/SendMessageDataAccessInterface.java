package use_case.send_message;

import entity.CommonMessage;
import entity.Message;

public interface SendMessageDataAccessInterface {


    public Message createMessage();

    public void sendMessage(Message message, String bindID);

}
