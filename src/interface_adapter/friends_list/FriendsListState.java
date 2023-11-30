package interface_adapter.friends_list;

import java.util.ArrayList;
import java.util.HashMap;

public class FriendsListState {

    private ArrayList<String> friendsList = new ArrayList<>();

    public FriendsListState(FriendsListState copy) {
        friendsList.clear();
        friendsList.addAll(copy.friendsList);
    }

    public FriendsListState() {
    }

    public ArrayList<String> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(ArrayList<String> friendsList) {
        this.friendsList = friendsList;
    }
}
