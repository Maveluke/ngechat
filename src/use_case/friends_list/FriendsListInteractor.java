package use_case.friends_list;

import entity.User;
import use_case.chat_list.ChatListOutputData;

import java.util.ArrayList;
import java.util.HashMap;

public class FriendsListInteractor implements FriendsListInputBoundary {
    /**
     * This class is used to get the friends list of the current user
     */
    private FriendsListDataAccessInterface friendsListDataAccessObject;
    private FriendsListOutputBoundary friendsListPresenter;

    public FriendsListInteractor(FriendsListDataAccessInterface friendsListDataAccessObject, FriendsListOutputBoundary friendsListPresenter){
        this.friendsListDataAccessObject = friendsListDataAccessObject;
        this.friendsListPresenter = friendsListPresenter;
    }

    @Override
    public void execute() {
        /**
         * This method is used to get the friends list of the current user
         */
        User currentUser = friendsListDataAccessObject.getCurrentUser();
        String currentUserName = currentUser.getName();
        ArrayList<String> friendsList = friendsListDataAccessObject.getFriends();

        FriendsListOutputData friendsListOutputData = new FriendsListOutputData(friendsList, currentUserName);
        friendsListPresenter.prepareSuccessView(friendsListOutputData);

    }


}
