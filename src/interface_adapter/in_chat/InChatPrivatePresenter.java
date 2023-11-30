package interface_adapter.in_chat;

import interface_adapter.ViewManagerModel;
import use_case.in_chat.InchatOutputBoundary;
import use_case.in_chat.InchatOutputData;

public class InChatPrivatePresenter implements InchatOutputBoundary {

    private InChatPrivateViewModel inChatPrivateViewModel;

    private ViewManagerModel viewManagerModel;

    public InChatPrivatePresenter(InChatPrivateViewModel inChatPrivateViewModel, ViewManagerModel viewManagerModel) {
        this.inChatPrivateViewModel = inChatPrivateViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(InchatOutputData inchatOutputData) {

        InChatPrivateState inChatPrivateState = inChatPrivateViewModel.getState();
        inChatPrivateState.setMessages(inchatOutputData.getMessages());

        inChatPrivateState.setFriendName(inchatOutputData.getFriendName());

        this.inChatPrivateViewModel.setState(inChatPrivateState);
        this.inChatPrivateViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(inChatPrivateViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
