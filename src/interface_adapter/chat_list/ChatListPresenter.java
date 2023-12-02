package interface_adapter.chat_list;

import interface_adapter.ViewManagerModel;
import use_case.chat_list.ChatListOutputBoundary;
import use_case.chat_list.ChatListOutputData;


public class ChatListPresenter implements ChatListOutputBoundary {

    private final interface_adapter.chat_list.ChatListViewModel chatlistViewModel;
    private final ViewManagerModel viewManagerModel;
    public ChatListPresenter(ChatListViewModel chatlistViewModel, ViewManagerModel viewManagerModel) {
        this.chatlistViewModel = chatlistViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ChatListOutputData chatListOutputData) {
        ChatListState currentState = chatlistViewModel.getState();
        currentState.setUpdated(chatListOutputData.isUpdated());
        currentState.setChatList(chatListOutputData.getChatList());
        chatlistViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(chatlistViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        System.out.println(error);
    }
}