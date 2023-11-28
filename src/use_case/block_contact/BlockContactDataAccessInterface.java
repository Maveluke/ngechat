package use_case.block_contact;

import entity.User;

public interface BlockContactDataAccessInterface {
    boolean blockFriend(String username, String friendUsername);

    User getCurrentUser();
}
