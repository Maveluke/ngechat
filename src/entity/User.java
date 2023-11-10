package entity;

public interface User {

    public String getName();

    void changeName(String name);

    public String getID();

    public String getPassword();

    public boolean userAddFriend(User friend);

    public boolean userRemoveFriend(User friend);
}
