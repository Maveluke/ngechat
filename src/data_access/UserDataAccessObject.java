package data_access;

import entity.User;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.add_chat.AddChatDataAccessInterface;
import use_case.add_contact.AddContactDataAccessInterface;
import use_case.chat_list.ChatListDataAccessInterface;
import use_case.create_chat.CreateChatDataAccessInterface;
import use_case.friends_list.FriendsListDataAccessInterface;
import use_case.login.LoginDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserDataAccessObject implements SignupUserDataAccessInterface,
        AddContactDataAccessInterface, ChatListDataAccessInterface, LoginDataAccessInterface, FriendsListDataAccessInterface,
        CreateChatDataAccessInterface {

    private final String masterKey;
    private final String downloadURL;
    private final String uploadURL;
    private final Map<String, User> accounts = new HashMap<>();
    private String currentUsername = null;

    //  TODO: Implement the constructor by downloading files from the API
    public UserDataAccessObject(String masterKey, String uploadURL, String downloadURL){
        this.masterKey = masterKey;
        this.uploadURL = uploadURL;
        this.downloadURL = downloadURL;
    }

    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        MediaType mediaType = MediaType.parse("application/json");
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SS");

        JSONObject userToSave = new JSONObject();
        userToSave.put(user.getName(), user.getPassword());
        JSONArray userFriends = new JSONArray(); // key: username, value: collection ID (?)
        userToSave.put("friends", userFriends);

        LocalDateTime localDateTime = LocalDateTime.now();
        RequestBody body = RequestBody.create(userToSave.toString(), mediaType);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.jsonbin.io/v3/b")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("X-Master-Key", "$2a$10$xfVheBzZjicxu..Dy7zLHeBNVrrPWZ/jEK/qfX7nTY.WKY/Tx9LM2")
                .addHeader("X-Bin-Name", localDateTime.format(myFormat))
                .addHeader("X-Collection-Id", "654030710574da7622bfdce6") // User Collection
                .build();

        try{
            Response response = client.newCall(request).execute();
            accounts.put(user.getName(), user);
        }catch (Exception e){
            System.out.println("Fail to get response");
        }
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    @Override
    public boolean addFriend(String username, String friendUsername) {
        User user = accounts.get(username);
        User friend = accounts.get(friendUsername);
        return user.userAddFriend(friend) && friend.userAddFriend(user);
    }

    @Override
    public User getCurrentUser() {
        return accounts.get(this.currentUsername);
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    @Override
    public HashMap<String, ArrayList<String>> getChats() {
        // TODO : Implement this
        return null;
    }

    public boolean chatExist(String targetUser) {
        for (String key : getChats().keySet()) {
            if (key.equals(targetUser)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void createChat(String userToChat) {
        // TODO : Implement this (Not really creating chat, just add the name to the API bin)
    }

    @Override
    public boolean chatIsEmpty() {
        return getChats().isEmpty();
    }

    @Override
    public boolean friendsIsEmpty() {
        return getFriends().isEmpty();
    }

    @Override
    public HashMap<String, String> getFriends() {
        // TODO : Implement this

        return null;
    }

    @Override
    public void deleteChat() {

    }
}