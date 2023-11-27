package use_case.friends_list;

import entity.User;

import java.util.HashMap;

public interface FriendsListDataAccessInterface {
    boolean friendsIsEmpty();

    HashMap<String, String> getFriends();

    User getCurrentUser();

}
