package interface_adapter.create_chat;

import interface_adapter.ViewManagerModel;
import interface_adapter.in_chat.InChatPrivateState;
import interface_adapter.in_chat.InChatPrivateViewModel;
import use_case.create_chat.CreateChatOutputBoundary;
import use_case.create_chat.CreateChatOutputData;
import use_case.in_chat.InchatOutputData;

public class CreateChatPresenter implements CreateChatOutputBoundary {

    private InChatPrivateViewModel inChatPrivateViewModel;

    private ViewManagerModel viewManagerModel;

    public CreateChatPresenter(InChatPrivateViewModel inChatPrivateViewModel, ViewManagerModel viewManagerModel) {
        this.inChatPrivateViewModel = inChatPrivateViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(CreateChatOutputData createChatOutputData) {

        InChatPrivateState inChatPrivateState = inChatPrivateViewModel.getState();
        inChatPrivateState.setSender(createChatOutputData.getSender());
        inChatPrivateState.setFriendName(createChatOutputData.getUserToChat());

        this.inChatPrivateViewModel.setState(inChatPrivateState);
        this.inChatPrivateViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(inChatPrivateViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

}
