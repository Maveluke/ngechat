package data_access;

import entity.User;
import entity.UserFactory;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.add_contact.AddContactDataAccessInterface;
import use_case.block_contact.BlockContactDataAccessInterface;
import use_case.chat_list.ChatListDataAccessInterface;
import use_case.create_chat.CreateChatDataAccessInterface;
import use_case.friends_list.FriendsListDataAccessInterface;
import use_case.login.LoginDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserDataAccessObject implements SignupUserDataAccessInterface,
        AddContactDataAccessInterface, LoginDataAccessInterface,
        FriendsListDataAccessInterface, BlockContactDataAccessInterface {

    private static final String USER_BIN_ID = "65642e610574da7622cc9825";
    private static final MediaType mediaType = MediaType.parse("application/json");
    private final String masterKey;
    private static final String API_URL = "https://api.jsonbin.io/v3/b";
    private final UserFactory userFactory;
    private final Map<String, User> accounts = new HashMap<>();
    private String currentUsername = null;

    public UserDataAccessObject(String masterKey, UserFactory userFactory){
        this.masterKey = masterKey;
        this.userFactory = userFactory;
        // Locally add user
        User admin = userFactory.create("admin", "admin");
        accounts.put("admin", admin);
        updateLocalUsers();
    }
    private JSONArray getUsersListRemote(){
        try{
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(API_URL + "/" + USER_BIN_ID)
                    .get()
                    .addHeader("X-Master-Key", this.masterKey)
                    .addHeader("X-Bin-Meta", "false")
                    .build();

            Response response = client.newCall(request).execute();
            String tempString = response.body().string();
            return new JSONObject(tempString).getJSONArray("users");
        }catch (IOException e){
            System.out.println("Fail to download users from API! with the error" + e);
        }
        return null;
    }
    private void updateLocalUsers(){
        JSONArray usersList = getUsersListRemote();
        if (usersList != null){
            // Adding all users to local
            for (int i = 0; i < usersList.length(); i++) {
                JSONObject userJSON = usersList.getJSONObject(i);
                // Checking whether the user exists locally
                if(!accounts.containsKey(userJSON.getString("username"))){
                    User user = userFactory.create(userJSON.getString("username"), userJSON.getString("password"));
                    accounts.put(userJSON.getString("username"), user);
                }
            }
            System.out.println(this);
            System.out.println();
            // Update each user's friends list
            for (int i = 0; i < usersList.length(); i++) {
                JSONObject userJSON = usersList.getJSONObject(i);
                User user = get(userJSON.getString("username"));
                updateFriendsLocal(user, userJSON.getJSONArray("friends"));
            }
            System.out.println(this);
            System.out.println();
        }
    }

    // Precondition: the local friends list is a subset of the remote friends list.
    private void updateFriendsLocal(User user, JSONArray friendsListJSON){
        // Add friend from remote (if applicable)
        for (int i = 0; i < friendsListJSON.length(); i++) {
            JSONObject friendJSON = friendsListJSON.getJSONObject(i);

            if(!user.isFriendWith(friendJSON.getString("username"))){
                User friend = get(friendJSON.getString("username")); // Getting friend from <accounts>
                user.userAddFriend(friend, friendJSON.getString("binID"));
            }
        }
        HashMap<User, String> localFriendsList = user.getFriendToBinMap();
        boolean isStillFriend = false;

        // Remove friend from local (if applicable)
        for (User friend :
                localFriendsList.keySet()) {
            for (int i = 0; i < friendsListJSON.length(); i++) {
                JSONObject friendJSON = friendsListJSON.getJSONObject(i);
                if(friend.getName().equals(friendJSON.getString("username"))) {
                    isStillFriend = true;
                    break;
                }
            }
            if(!isStillFriend) {
                user.userRemoveFriend(friend);
            }
            isStillFriend = false;
        }
    }
    @Override
    public boolean addFriend(String username, String friendUsername) {
        User user = accounts.get(username);
        User friend = accounts.get(friendUsername);
        if (!user.isFriendWith(friendUsername)){
            String binID = createBinID();

            if(binID.equals("error")) return false;
            // Add friend locally
            user.userAddFriend(friend, binID);
            friend.userAddFriend(user, binID);

            // Add friend remotely
            addFriendRemotely(username, friendUsername, binID);
            return true;
        }
        return false;
    }
    private void addFriendRemotely(String username, String friendUsername, String binID){
        JSONArray usersRemote = getUsersListRemote();
        for (int i = 0; i < usersRemote.length(); i++) {
            JSONObject currentUserJSON = usersRemote.getJSONObject(i);
            if (currentUserJSON.getString("username").equals(username)){
                JSONArray oldFriendsList = currentUserJSON.getJSONArray("friends");

                JSONObject newFriend = new JSONObject();
                newFriend.put("username", friendUsername);
                newFriend.put("binID", binID);

                oldFriendsList.put(newFriend);
                currentUserJSON.put("friends", oldFriendsList);
                usersRemote.put(i, currentUserJSON);
            }
            if (currentUserJSON.getString("username").equals(friendUsername)){
                JSONArray oldFriendsList = currentUserJSON.getJSONArray("friends");

                JSONObject newFriend = new JSONObject();
                newFriend.put("username", username);
                newFriend.put("binID", binID);

                oldFriendsList.put(newFriend);
                currentUserJSON.put("friends", oldFriendsList);
                usersRemote.put(i, currentUserJSON);
            }
        }
        updateRemoteUsers(usersRemote);
    }

    // Create a new bin for user and friend to chat
    private String createBinID(){
        JSONObject messagesInfoJSON = new JSONObject();
        messagesInfoJSON.put("messages", new JSONArray());
        RequestBody body = RequestBody.create(messagesInfoJSON.toString(), mediaType);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("X-Master-Key", this.masterKey)
                .build();
        try{
            Response response = client.newCall(request).execute();
            String tempResponse = response.body().string();
            return new JSONObject(tempResponse).getJSONObject("metadata").getString("id");
        }catch (Exception e){
            System.out.println("Fail to get response");
        }
        return "error";
    }

    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        JSONObject userToSave = new JSONObject();
        userToSave.put("username", user.getName());
        userToSave.put("password", user.getPassword());
        JSONArray userFriends = new JSONArray();
        userToSave.put("friends", userFriends);

        OkHttpClient client = new OkHttpClient();
        Request downloadRequest = new Request.Builder()
                .url(API_URL + "/" + USER_BIN_ID)
                .get()
                .addHeader("Content-Type", "application/json")
                .addHeader("X-Master-Key", this.masterKey)
                .addHeader("X-Bin-Meta", "false")
                .build();
        try{
            Response downloadResponse = client.newCall(downloadRequest).execute();
            JSONArray usersList = new JSONObject(downloadResponse.body().string()).getJSONArray("users");
            // Update locally
            accounts.put(user.getName(), user);
            // Update remotely
            usersList.put(userToSave);
            updateRemoteUsers(usersList);
            System.out.println(this);
        }catch (Exception e){
            System.out.println("Fail to get response when downloading users");
        }

    }

    public void updateRemoteUsers(JSONArray usersList){
        JSONObject body = new JSONObject();
        body.put("users", usersList);
        RequestBody updateBody = RequestBody.create(body.toString(), mediaType);
        OkHttpClient client = new OkHttpClient();
        Request uploadRequest = new Request.Builder()
                .url(API_URL + "/" + USER_BIN_ID)
                .put(updateBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("X-Master-Key", this.masterKey)
                .build();

        try{
            Response updateResponse = client.newCall(uploadRequest).execute();
        }catch (Exception e){
            System.out.println("Fail to get response when uploading users");
        }
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    @Override
    public boolean blockFriend(String username, String friendUsername) {
        User user = accounts.get(username);
        User friend = accounts.get(friendUsername);

        // Add friend locally
        user.userRemoveFriend(friend);
        friend.userRemoveFriend(user);

        // Add friend remotely
        blockFriendRemotely(username, friendUsername);
        return true;
    }

    private void blockFriendRemotely(String username, String friendUsername) {
        JSONArray usersRemote = getUsersListRemote();
        for (int i = 0; i < usersRemote.length(); i++) {
            JSONObject currentUserJSON = usersRemote.getJSONObject(i);
            if (currentUserJSON.getString("username").equals(username)) {
                JSONArray oldFriendsList = currentUserJSON.getJSONArray("friends");

                for (int j = 0; j < oldFriendsList.length(); j++) {
                    JSONObject friendJSON = oldFriendsList.getJSONObject(j);
                    if (friendJSON.getString("username").equals(friendUsername)) {
                        oldFriendsList.remove(j);
                    }
                }

                currentUserJSON.put("friends", oldFriendsList);
                usersRemote.put(i, currentUserJSON);
            }
            if (currentUserJSON.getString("username").equals(friendUsername)) {
                JSONArray oldFriendsList = currentUserJSON.getJSONArray("friends");

                for (int j = 0; j < oldFriendsList.length(); j++) {
                    JSONObject friendJSON = oldFriendsList.getJSONObject(j);
                    if (friendJSON.getString("username").equals(username)) {
                        oldFriendsList.remove(j);
                    }

                    currentUserJSON.put("friends", oldFriendsList);
                    usersRemote.put(i, currentUserJSON);
                }
            }
        }
        updateRemoteUsers(usersRemote);
    }

    @Override
    public User getCurrentUser() {
        return accounts.get(this.currentUsername);
    }
    @Override
    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    @Override
    public boolean friendsIsEmpty() {
        return getFriends().isEmpty();
    }

    @Override
    public ArrayList<String> getFriends(){
        ArrayList<String> ret = new ArrayList<>();
        for (User friend:
        accounts.get(currentUsername).getFriendToBinMap().keySet()) {
            ret.add(friend.getName());
        }
        return ret;
    }

    @Override
    public String toString(){
        String ret = "";
        if (this.currentUsername != null) ret += this.currentUsername;
        else ret += "null";
        ret += "\n";

        for (String username :
                accounts.keySet()) {
            ret += String.format("username: %s\n", username);
            User currentUser = accounts.get(username);
            ret += String.format("password: %s\n", currentUser.getPassword());
            ret += "friends: \n";
            for (User friend:
                 currentUser.getFriendToBinMap().keySet()) {
                ret += String.format("- %s\n", friend.getName());
            }
        }
        return ret;
    }
}