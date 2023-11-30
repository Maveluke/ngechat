package interface_adapter.send_message;

import interface_adapter.ViewManagerModel;
import interface_adapter.in_chat.InChatPrivateState;
import use_case.send_message.SendMessageOutputBoundary;
import use_case.send_message.SendMessageOutputData;

public class SendMessagePresenter implements SendMessageOutputBoundary {

    private SendMessageViewModel sendMessageViewModel;
    private ViewManagerModel viewManagerModel;

    public SendMessagePresenter(SendMessageViewModel sendMessageViewModel, ViewManagerModel viewManagerModel){
        this.sendMessageViewModel = sendMessageViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SendMessageOutputData sendMessageOutputData) {

        SendMessageState sendMessageState = sendMessageViewModel.getState();
        sendMessageState.setMessage(sendMessageState.getMessage());

        sendMessageState.setSender(sendMessageState.getSender());
        sendMessageState.setDateTime(sendMessageOutputData.getDatetime());

        this.sendMessageViewModel.setState(sendMessageState);
        this.sendMessageViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(sendMessageViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }
}
