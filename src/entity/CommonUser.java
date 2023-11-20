package entity;

import java.util.ArrayList;

public class CommonUser implements User {

        private String Username;
        private final ArrayList<User> friendsList = new ArrayList<>();
        private final String Password;

        public CommonUser(String username, String password){
                this.Username = username;
                this.Password = password;
        }

        public String getName(){
                return Username;
        }

        @Override
        public void changeName(String name) {
                this.Username = name;
        }

        public String getPassword() {return this.Password;}

        public ArrayList<User> getFriendsList() {
                return new ArrayList<>(friendsList);
        }
        @Override
        public boolean userAddFriend(User friend){
                if(friendsList.contains(friend)){
                        return false;
                }
                return friendsList.add(friend);
        }
        @Override
        public boolean userRemoveFriend(User friend){
                return friendsList.remove(friend);
        }
}

