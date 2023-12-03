package use_case.send_message;

import entity.CommonMessage;
import entity.Message;

public interface SendMessageDataAccessInterface{


    Message createMessage(String text, String sender);

    void sendMessage(Message message, String bindID, String friendName);

    String getBinID(String friendUsername);

}
