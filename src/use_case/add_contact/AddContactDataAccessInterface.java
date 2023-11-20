package use_case.add_contact;

import entity.User;

public interface AddContactDataAccessInterface {
    boolean existsByName(String identifier);

    boolean addFriend(String username, String friendUsername);

    User getCurrentUser();
}
