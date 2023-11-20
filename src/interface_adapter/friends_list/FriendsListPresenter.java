package interface_adapter.friends_list;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FriendsListPresenter implements SignupOutputBoundary {

    private final FriendsListViewModel friendsListViewModel;
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public FriendsListPresenter(ViewManagerModel viewManagerModel,
                                FriendsListViewModel friendsListViewModel,
                                LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.friendsListViewModel = friendsListViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputData response) {
        // On success, switch to the login view.
        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));

        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        FriendsListState friendsListState = friendsListViewModel.getState();
        friendsListState.setUsernameError(error);
        friendsListViewModel.firePropertyChanged();
    }
}
