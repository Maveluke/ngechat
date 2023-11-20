package use_case.friendslist;

import java.util.HashMap;

public interface FriendsListDataAccessInterface {
    boolean is_empty();

    HashMap<String, String> getChats();
}
