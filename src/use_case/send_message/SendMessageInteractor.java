package use_case.send_message;

import entity.CommonMessage;
import entity.Message;

import java.time.LocalDateTime;

public class SendMessageInteractor implements SendMessageInputBoundary {

    private final SendMessageDataAccessInterface sendMessageDataAccessObject;

    private final SendMessageOutputBoundary sendMessagePresenter;


    public SendMessageInteractor(SendMessageDataAccessInterface sendMessageDataAccessObject, SendMessageOutputBoundary sendMessagePresenter) {
        this.sendMessageDataAccessObject = sendMessageDataAccessObject;
        this.sendMessagePresenter = sendMessagePresenter;
    }


    // we need Bin ID as part of the input data to call the s
    public void execute(SendMessageInputData sendMessageInputData) {


        // 1. Make the message
        Message message = sendMessageDataAccessObject.createMessage();

        // 2. Store the message in the API
        String binID = sendMessageInputData.getBinID();

        // 3. Store the message in the DAO
        sendMessageDataAccessObject.sendMessage(message, binID);

        String sender = message.getSender();
        LocalDateTime datetime = message.getTimeSent();
        String text = message.getMessage();

        SendMessageOutputData sendMessageOutputData = new SendMessageOutputData(text, datetime ,sender);

        sendMessagePresenter.prepareSuccessView(sendMessageOutputData);


    }
}


