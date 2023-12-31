package use_case.login;

import entity.User;

public interface LoginDataAccessInterface {

    boolean existsByName(String identifier);

    User get(String username);

    void setCurrentUsername(String username);

    User getCurrentUser();
}
