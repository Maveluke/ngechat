package data_access;

import entity.Chat;
import entity.User;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.chat_list.ChatListDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ChatListDataAccessObject implements ChatListDataAccessInterface {

    private static final MediaType mediaType = MediaType.parse("application/json");
    private final String masterKey;
    private HashMap<String, Chat> chatList = new HashMap<>();
    private static final String API_URL = "https://api.jsonbin.io/v3/b";
    public ChatListDataAccessObject(String masterKey, HashMap<String, String> friendTobinID){
        this.masterKey = masterKey;

    }
    private JSONObject getChat(String binID){
        try{
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(API_URL + "/" + binID)
                    .get()
                    .addHeader("X-Master-Key", this.masterKey)
                    .addHeader("X-Bin-Meta", "false")
                    .build();

            Response response = client.newCall(request).execute();

            return new JSONObject(response.body().string()); // TODO: not sure whether this is possible
        }catch (IOException e){
            System.out.println("Fail to download users from API! with the error" + e);
        }
        return null;
    }
    @Override
    public HashMap<String, ArrayList<String>> getChats() {
        return null;
    }

    @Override
    public boolean is_empty() {
        return false;
    }

    @Override
    public void deleteChat() {

    }
}
