package use_case.send_message;

import entity.CommonMessage;
import entity.Message;

public interface SendMessageDataAccessInterface{


    public Message createMessage(String text, String sender);

    public void sendMessage(Message message, String bindID);

    String getBinID(String friendUsername);

}
