package interface_adapter.friends_list;

import use_case.friends_list.FriendsListInputBoundary;

public class FriendsListController {

    final FriendsListInputBoundary friendsListInteractor;
    public FriendsListController(FriendsListInputBoundary friendsListInteractor) {
        this.friendsListInteractor = friendsListInteractor;
    }

    public void execute() {
        friendsListInteractor.execute();
    }
}
