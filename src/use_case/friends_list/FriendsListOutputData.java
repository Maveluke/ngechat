package use_case.friends_list;

import java.util.ArrayList;
import java.util.HashMap;

public class FriendsListOutputData {

    private ArrayList<String> friendsList;

    private String currentUserName;

    public FriendsListOutputData(ArrayList<String> friendsList, String currentUserName) {
        this.friendsList = friendsList;
        this.currentUserName = currentUserName;
    }

    public ArrayList<String> getFriends() {
        return friendsList;
    }

    public String getCurrentUserName(){
        return currentUserName;
    }

}
