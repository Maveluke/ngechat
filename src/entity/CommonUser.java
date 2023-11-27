package entity;

import java.util.HashMap;

public class CommonUser implements User {

        private String Username;
        private final HashMap<User, String> friendToBinMap = new HashMap<>();
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

        public HashMap<User, String> getFriendToBinMap() {
                return new HashMap<>(friendToBinMap);

        }
        @Override
        public boolean isFriendWith(String friendUsername){
                for(User friend: friendToBinMap.keySet()){
                        if(friend.getName().equals(friendUsername)) return true;
                }
                return false;
        }
        @Override
        public boolean userAddFriend(User friend, String binID){
                if(this.isFriendWith(friend.getName())){
                        return false;
                }
                friendToBinMap.put(friend, binID);
                return true;
        }

        @Override
        public boolean userRemoveFriend(User friend){
                if(this.isFriendWith(friend.getName())){
                        friendToBinMap.remove(friend);
                        return true;
                }
                return false;
        }
}

