package use_case.send_message;

import entity.CommonMessage;
import entity.Message;

import java.time.LocalDateTime;

public class SendMessageInteractor implements SendMessageInputBoundary {
    /**
     * This class is used to send a message to a friend
     */
    private final SendMessageDataAccessInterface sendMessageDataAccessObject;

    private final SendMessageOutputBoundary sendMessagePresenter;


    public SendMessageInteractor(SendMessageDataAccessInterface sendMessageDataAccessObject, SendMessageOutputBoundary sendMessagePresenter) {
        this.sendMessageDataAccessObject = sendMessageDataAccessObject;
        this.sendMessagePresenter = sendMessagePresenter;
    }


    // we need Bin ID as part of the input data to call the s
    public void execute(SendMessageInputData sendMessageInputData) {

        String messageText = sendMessageInputData.getMessageText();
        String sender = sendMessageInputData.getSender();

        // Info :
        // The sender must be the currentUser

        // 1. Make the message
        Message message = sendMessageDataAccessObject.createMessage(messageText, sender);

        // 2. Store the message in the API
        String binID = sendMessageDataAccessObject.getBinID(sendMessageInputData.getFriendName());

        // 3. Store the message in the DAO
        sendMessageDataAccessObject.sendMessage(message, binID, sendMessageInputData.getFriendName());

        String datetime = message.getTimeSent();
        String text = message.getMessage();

        SendMessageOutputData sendMessageOutputData = new SendMessageOutputData(text, datetime ,sender);

        sendMessagePresenter.prepareSuccessView(sendMessageOutputData);


    }
}



