package entity;

import java.util.Map;

public class CommonGroup implements Group {

    public String name;

    private final Map<String, User> Users;


    public CommonGroup(String name, Map<String, User> users) {
        this.name = name;
        Users = users;
    };

    public void addUsers(User user){
        this.Users.put(user.getName(), user);
    }

}
