package entity;


public class CommonUserFactory implements UserFactory {
    @Override
    public User create(String name, String password) {
        String Id = ""; // TODO: Generate Id
        return new CommonUser(name, Id, password);
    }
}
