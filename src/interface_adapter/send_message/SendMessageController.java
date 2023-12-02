package interface_adapter.send_message;

import use_case.in_chat.InChatInputData;
import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInputData;
import use_case.send_message.SendMessageOutputBoundary;

public class SendMessageController {

    SendMessageInputBoundary sendMessageInteractor;

    public SendMessageController(SendMessageInputBoundary sendMessageInteractor){
        this.sendMessageInteractor = sendMessageInteractor;
    }

    public void execute(String messageText, String sender, String friendName) {
        SendMessageInputData sendMessageInputData = new SendMessageInputData(messageText, sender, friendName);

        sendMessageInteractor.execute(sendMessageInputData);


    }




}
