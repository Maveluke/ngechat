package use_case.friends_list;

import entity.User;
import use_case.chat_list.ChatListOutputData;

import java.util.HashMap;

public class FriendsListInteractor implements FriendsListInputBoundary {

    private FriendsListDataAccessInterface friendsListDataAccessObject;
    private FriendsListOutputBoundary friendsListPresenter;

    public FriendsListInteractor(FriendsListDataAccessInterface friendsListDataAccessObject, FriendsListOutputBoundary friendsListPresenter){
        this.friendsListDataAccessObject = friendsListDataAccessObject;
        this.friendsListPresenter = friendsListPresenter;
    }

    @Override
    public void execute() {

            User currentUser = friendsListDataAccessObject.getCurrentUser();
            String currentUserName = currentUser.getName();
            HashMap<String, String> friendsList = friendsListDataAccessObject.getFriends();

            FriendsListOutputData friendsListOutputData = new FriendsListOutputData(friendsList, currentUserName);
            friendsListPresenter.prepareSuccessView(friendsListOutputData);

    }


}
