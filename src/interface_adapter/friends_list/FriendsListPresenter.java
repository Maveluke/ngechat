package interface_adapter.friends_list;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.friends_list.FriendsListOutputBoundary;
import use_case.friends_list.FriendsListOutputData;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class FriendsListPresenter implements FriendsListOutputBoundary {

    private final FriendsListViewModel friendsListViewModel;
    private ViewManagerModel viewManagerModel;

    public FriendsListPresenter(ViewManagerModel viewManagerModel,
                                FriendsListViewModel friendsListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.friendsListViewModel = friendsListViewModel;
    }

    @Override
    public void prepareSuccessView(FriendsListOutputData response) {


        HashMap<String, String> friendsList = response.getFriends();

        FriendsListState friendsListState = friendsListViewModel.getState();

        friendsListState.setFriendsList(friendsList);

        this.friendsListViewModel.setState(friendsListState);

        friendsListViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(friendsListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
}
