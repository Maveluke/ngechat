package data_access;

import entity.*;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.chat_list.ChatListDataAccessInterface;
import use_case.create_chat.CreateChatDataAccessInterface;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class ChatListDataAccessObject implements ChatListDataAccessInterface, CreateChatDataAccessInterface {

    private static final MediaType mediaType = MediaType.parse("application/json");
    private final String masterKey;
    private HashMap<String, String> friendTobinID; // friend username to binId
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
            JSONArray messages = responseJSON.getJSONArray("messages");
            ArrayList<ArrayList<Object>> messagesInfo = new ArrayList<>();

            for (int i = 0; i < messages.length(); i++) {
                JSONObject singleMessageInfo = messages.getJSONObject(i);
                ArrayList<Object> senderToMessage = new ArrayList<>();
                LocalDateTime timeSent = LocalDateTime.parse(singleMessageInfo.getString("timeSent"));
                Message tempMessage = new Message(singleMessageInfo.getString("message"), timeSent, singleMessageInfo.getString("sender"));
                senderToMessage.add(singleMessageInfo.getString("sender"));
                senderToMessage.add(tempMessage);
            }
            // So that the Chat will not be created
            if (messages.isEmpty()){
                return null;
            }
            return chatFactory.create(messagesInfo, binID);
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
    public boolean chatIsEmpty() {
        return false;
    }

    @Override
    public void deleteChat() {
    }

    @Override
    public boolean chatExist(String targetUser) {
        for (String key : getChats().keySet()) {
            if (key.equals(targetUser)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getBinID(String friendUsername) {
        return friendTobinID.get(friendUsername);
    }

    @Override
    public void addChat(String friendUsername, Chat chat) {
        chatList.put(friendUsername, chat);
    }
    @Override
    public String toString(){
        String ret = "friends: \n";
        for (String friendUsername :
                friendTobinID.keySet()) {
            ret += String.format("%s : %s\n", friendUsername, friendTobinID.get(friendUsername));
        }
        ret += "Chats: \n";
        for (String friendUsername :
                chatList.keySet()) {
            ret += String.format("%s\n", chatList.get(friendUsername).toString());
        }
        return ret;
    }

}