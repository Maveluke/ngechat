package use_case.friendslist;

import use_case.chatlist.ChatListDataAccessInterface;
import use_case.chatlist.ChatListOutputBoundary;
import use_case.chatlist.ChatListOutputData;

import java.util.ArrayList;
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
        if (friendsListDataAccessObject.is_empty()) {
            friendsListPresenter.prepareFailView("No chat available");
        } else {
            HashMap<String, String> friendsList = friendsListDataAccessObject.getChats();
            ChatListOutputData chatListOutputData = new ChatListOutputData(chatList);
            friendsListPresenter.prepareSuccessView(chatListOutputData);
        }
    }


}
