package data_access;

import entity.User;
import entity.UserFactory;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.add_contact.AddContactDataAccessInterface;
import use_case.chat_list.ChatListDataAccessInterface;
import use_case.login.LoginDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserDataAccessObject implements SignupUserDataAccessInterface,
        AddContactDataAccessInterface, LoginDataAccessInterface {

    private static final String USER_BIN_ID = "";
    private static final MediaType mediaType = MediaType.parse("application/json");
    private final String masterKey;
    private static final String API_URL = "https://api.jsonbin.io/v3/b";
    private final UserFactory userFactory;
    private final Map<String, User> accounts = new HashMap<>();
    private String currentUsername = null;

    //  TODO: Implement the constructor by downloading files from the API
    public UserDataAccessObject(String masterKey, UserFactory userFactory){
        this.masterKey = masterKey;
        this.userFactory = userFactory;
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

            return new JSONArray(response.body().string()); // TODO: not sure whether this is possible
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
            // Update each user's friends list
            for (int i = 0; i < usersList.length(); i++) {
                JSONObject userJSON = usersList.getJSONObject(i);
                User user = get(userJSON.getString("username"));
                updateFriendsLocal(user, userJSON.getJSONArray("friends"));
            }
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

            user.userAddFriend(friend, binID);
            friend.userAddFriend(user, binID);
            return true;
        }
        return false;
    }
    // Create a new bin for user and friend to chat
    private String createBinID(){
        RequestBody body = RequestBody.create(new JSONObject().toString(), mediaType);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("X-Master-Key", this.masterKey)
                .build();
        try{
            Response response = client.newCall(request).execute();
            return new JSONObject(response.body().toString()).getJSONObject("metadata").getString("id");
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
        // TODO: Change the implementation to update instead of uploading a new user
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SS");

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
            JSONArray usersList = new JSONArray(downloadResponse.body().string());
            // Update locally
            usersList.put(userToSave);
            // Update remotely
            updateRemoteUsers(usersList);
        }catch (Exception e){
            System.out.println("Fail to get response when downloading users");
        }

    }

    public void updateRemoteUsers(JSONArray usersList){
        RequestBody updateBody = RequestBody.create(usersList.toString(), mediaType);
        OkHttpClient client = new OkHttpClient();
        Request uploadRequest = new Request.Builder()
                .url(API_URL + "/" + USER_BIN_ID)
                .post(updateBody)
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
    public User getCurrentUser() {
        return accounts.get(this.currentUsername);
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }
}
