package entity;

import java.time.LocalDateTime;

public class CommonUserFactory implements UserFactory {

    String Id = "";
    @Override
    public User create(String name, String password) {
        return new CommonUser(name, Id,  password);
    }
}
