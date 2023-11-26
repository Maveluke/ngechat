package interface_adapter.friends_list;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import use_case.friends_list.FriendsListOutputBoundary;
import use_case.friends_list.FriendsListOutputData;

public class FriendsListPresenter implements FriendsListOutputBoundary {

    private final FriendsListViewModel friendsListViewModel;
    private ViewManagerModel viewManagerModel;

    public FriendsListPresenter(ViewManagerModel viewManagerModel,
                                FriendsListViewModel friendsListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.friendsListViewModel = friendsListViewModel;
    }

    @Override
    public void prepareSuccessView(FriendsListOutputData friendsListOutputData) {

    }

    @Override
    public void prepareFailView(String noChatAvailable) {
        System.out.println(noChatAvailable);
    }
}
