package interface_adapter.chatlist;

import interface_adapter.ViewManagerModel;
import use_case.chatlist.ChatListOutputData;
import use_case.chatlist.ChatListOutputBoundary;

public class ChatListPresenter implements ChatListOutputBoundary{

    private final interface_adapter.chatlist.ChatListViewModel chatlistViewModel;
    private ViewManagerModel viewManagerModel;
    public ChatListPresenter(ChatListViewModel chatlistViewModel, ViewManagerModel viewManagerModel) {
        this.chatlistViewModel = chatlistViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ChatListOutputData chatListOutputData) {
        chatlistViewModel.set_chat(chatListOutputData.getChatList());
        chatlistViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        System.out.println("error");
    }
}
