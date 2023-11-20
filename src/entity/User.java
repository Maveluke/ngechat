package entity;

import java.util.ArrayList;

public interface User {

    String getName();

    void changeName(String name);

    String getPassword();

    ArrayList<User> getFriendsList();

    boolean userAddFriend(User friend);

    boolean userRemoveFriend(User friend);
}
