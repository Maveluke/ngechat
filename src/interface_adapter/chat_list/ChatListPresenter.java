package interface_adapter.chat_list;

import interface_adapter.ViewManagerModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

public class ChatListPresenter implements SignupOutputBoundary {

    private final ChatListViewModel chatListViewModel;
    private ViewManagerModel viewManagerModel;

    public ChatListPresenter(ViewManagerModel viewManagerModel,
                             ChatListViewModel chatListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.chatListViewModel = chatListViewModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputData response) {
        // On success, switch to the chat_list view.

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
