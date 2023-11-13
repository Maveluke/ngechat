package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.chat_list.ChatListState;
import interface_adapter.chat_list.ChatListViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    private final interface_adapter.login.LoginViewModel loginViewModel;
    private final ChatListViewModel chatListViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          ChatListViewModel chatListViewModel,
                          interface_adapter.login.LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.chatListViewModel = chatListViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        ChatListState chatListState = chatListViewModel.getState();

        // TODO: add something (?)
        this.chatListViewModel.setState(chatListState);
        this.chatListViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(chatListViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}
