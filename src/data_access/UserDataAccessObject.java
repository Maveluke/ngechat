package data_access;

import entity.User;
import okhttp3.*;
import org.json.JSONObject;
import use_case.signup.SignupUserDataAccessInterface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class UserDataAccessObject implements SignupUserDataAccessInterface {

    private final String masterKey;
    private final String downloadURL;
    private final Map<String, User> accounts = new HashMap<>();

    //  TODO: Implement the constructor by downloading files from the API
    public UserDataAccessObject(String masterKey, String downloadURL){
        this.masterKey = masterKey;
        this.downloadURL = downloadURL;
    }

    // TODO: Implement existsByName
    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    // TODO: Implement save (and upload changes to API)
    @Override
    public void save(User user) {
        MediaType mediaType = MediaType.parse("application/json");
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SS");

        JSONObject userToSave = new JSONObject();
        userToSave.put(user.getName(), user.getPassword());

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
}
