package interface_adapter.chat_list;

import interface_adapter.ViewManagerModel;
import interface_adapter.friends_list.FriendsListState;
import use_case.chat_list.ChatListOutputBoundary;
import use_case.chat_list.ChatListOutputData;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatListPresenter implements ChatListOutputBoundary {

    private final ChatListViewModel chatListViewModel;
    private ViewManagerModel viewManagerModel;

    public ChatListPresenter(ViewManagerModel viewManagerModel,
                             ChatListViewModel chatListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.chatListViewModel = chatListViewModel;
    }

    @Override
    public void prepareSuccessView(ChatListOutputData response) {
        // On success, switch to the chat_list view.

        HashMap<String, ArrayList<String>> chatList = response.getChatList();

        ChatListState chatListState = chatListViewModel.getState();

        chatListState.setChatList(chatList);

        this.chatListViewModel.setState(chatListState);

        chatListViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(chatListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();



//        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
//        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
//
//        LoginState loginState = loginViewModel.getState();
//        loginState.setUsername(response.getUsername());
//        this.loginViewModel.setState(loginState);
//        loginViewModel.firePropertyChanged();
//
//        viewManagerModel.setActiveView(loginViewModel.getViewName());
//        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
    }
}
