package interface_adapter.chatlist;

import interface_adapter.ViewManagerModel;
import use_case.chat_list.ChatListOutputData;
import use_case.chat_list.ChatListOutputBoundary;

public class ChatListPresenter implements ChatListOutputBoundary{

    private final interface_adapter.chatlist.ChatListViewModel chatlistViewModel;
    private final ViewManagerModel viewManagerModel;
    public ChatListPresenter(ChatListViewModel chatlistViewModel, ViewManagerModel viewManagerModel) {
        this.chatlistViewModel = chatlistViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ChatListOutputData chatListOutputData) {
        chatlistViewModel.set_chat(chatListOutputData.getChatList());
        chatlistViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(chatlistViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        System.out.println(error);
    }
}
