package use_case.friends_list;

import entity.User;

import java.util.ArrayList;
import java.util.HashMap;

public interface FriendsListDataAccessInterface {
    boolean friendsIsEmpty();

    ArrayList<String> getFriends();

    User getCurrentUser();

}
