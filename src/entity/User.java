package entity;

import java.util.ArrayList;
import java.util.HashMap;

public interface User {

    String getName();

    void changeName(String name);

    String getPassword();

    HashMap<User, String> getFriendToBinMap();

    boolean userAddFriend(User friend, String binID);

    boolean userRemoveFriend(User friend);

    boolean isFriendWith(String friendUsername);
}
