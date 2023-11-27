package data_access;

import entity.Chat;
import entity.CommonChat;
import entity.Message;
import entity.User;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.chat_list.ChatListDataAccessInterface;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class ChatListDataAccessObject implements ChatListDataAccessInterface {

    private static final MediaType mediaType = MediaType.parse("application/json");
    private final String masterKey;
    private HashMap<String, String> friendTobinID;
    private HashMap<String, Chat> chatList = new HashMap<>(); // Friend username to Chat
    private CommonChatFactory chatFactory;
    private static final String API_URL = "https://api.jsonbin.io/v3/b";
    public ChatListDataAccessObject(String masterKey, CommonChatFactory chatFactory){
        this.masterKey = masterKey;
        this.chatFactory = chatFactory;
    }
    private Chat getChatRemote(String binID){
        try{
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(API_URL + "/" + binID)
                    .get()
                    .addHeader("X-Master-Key", this.masterKey)
                    .addHeader("X-Bin-Meta", "false")
                    .build();

            Response response = client.newCall(request).execute();
            JSONObject responseJSON = new JSONObject(response.body().string());
            JSONArray senders = responseJSON.getJSONArray("senders");
            HashMap<String, ArrayList<Message>> newMessagesMap = new HashMap<>();

            boolean isChatEmpty = true;

            for (int i = 0; i < senders.length(); i++) {
                JSONObject sender = senders.getJSONObject(i);
                String senderUsername = sender.getString("sender");
                JSONArray messagesJSON = sender.getJSONArray("messages");
                ArrayList<Message> tempMessagesArray = new ArrayList<>();
                if (!messagesJSON.isEmpty()) isChatEmpty = false;
                for (int j = 0; j < messagesJSON.length(); j++) {
                    LocalDateTime timeSent = LocalDateTime.parse(messagesJSON.getJSONObject(i).getString("timeSent"));
                    Message message = new Message(messagesJSON.getJSONObject(i).getString("message"), timeSent, senderUsername);
                    tempMessagesArray.add(message);
                }
                newMessagesMap.put(senderUsername, tempMessagesArray);
            }
            // So that the Chat will not be created
            if (isChatEmpty){
                return null;
            }
            return chatFactory.create(newMessagesMap, binID);
        }catch (IOException e){
            System.out.println("Fail to download chat from API! with the error" + e);
        }
        return null;
    }
    @Override
    public void addFriendTobinID(String username, String binID) {
        this.friendTobinID.put(username, binID);
    }

    @Override
    public HashMap<String, ArrayList<String>> getChats() { // Friend username to an array consists of last message, timeSent, sender, in the same order.
        HashMap<String, ArrayList<String>> ret = new HashMap<>();
        for (String friendUsername :
                chatList.keySet()) {
            ArrayList<String> tempArray = new ArrayList<>();
            Chat currentChat = chatList.get(friendUsername);
            Message lastMessage = currentChat.getLastMessage();
            tempArray.add(lastMessage.getMessage());
            tempArray.add(lastMessage.getTimeSent().toString());
            tempArray.add(lastMessage.getSender());
            ret.put(friendUsername, tempArray);
        }
        return ret;
    }

    @Override
    public boolean is_empty() {
        return false;
    }

    @Override
    public void deleteChat() {

    }
}
