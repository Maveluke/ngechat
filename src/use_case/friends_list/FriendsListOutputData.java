package use_case.friends_list;

import java.util.HashMap;

public class FriendsListOutputData {

    private HashMap<String, String> friendsList;

    private String currentUserName;

    public FriendsListOutputData(HashMap<String, String> friendsList, String currentUserName) {
        this.friendsList = friendsList;
        this.currentUserName = currentUserName;
    }

    public HashMap<String, String> getFriends() {
        return friendsList;
    }

    public String getCurrentUserName(){
        return currentUserName;
    }

}
