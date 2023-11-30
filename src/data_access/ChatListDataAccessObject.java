package data_access;

import entity.*;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.chat_list.ChatListDataAccessInterface;
import use_case.create_chat.CreateChatDataAccessInterface;
import use_case.in_chat.InChatDataAccessInterface;
import use_case.send_message.SendMessageDataAccessInterface;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class ChatListDataAccessObject implements ChatListDataAccessInterface, CreateChatDataAccessInterface, InChatDataAccessInterface, SendMessageDataAccessInterface {

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
        JSONArray messages = getChatJSONRemotely(binID);
        ArrayList<ArrayList<Object>> messagesInfo = new ArrayList<>();

        for (int i = 0; i < messages.length(); i++) {
            JSONObject singleMessageInfo = messages.getJSONObject(i);

            ArrayList<Object> senderToMessage = new ArrayList<>();

            LocalDateTime timeSent = LocalDateTime.parse(singleMessageInfo.getString("timeSent"));
            Message tempMessage = new CommonMessage(singleMessageInfo.getString("message"), timeSent, singleMessageInfo.getString("sender"));

            senderToMessage.add(singleMessageInfo.getString("sender"));
            senderToMessage.add(tempMessage);
            messagesInfo.add(senderToMessage);
        }
        // So that the Chat will not be created
        if (messages.isEmpty()){
            return null;
        }
        return chatFactory.create(messagesInfo, binID);
    }
    private JSONArray getChatJSONRemotely(String binID){
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
            return responseJSON.getJSONArray("messages");
        } catch (IOException e){
            System.out.println("Fail to download chat from API! with the error: " + e);
        }
        return null;
    }

    @Override
    public Message createMessage() {
        return null;
    }

    @Override
    public void sendMessage(Message message, String binID){
        String contentMessage = message.getMessage();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy, hh:mm:ss:SS");
        String timeSent = message.getTimeSent().format(formatter);
        String sender = message.getSender();

        // Update Chat locally
        // TODO: Implement after inChatPrivate is implemented
        // Update Chat remotely
         JSONObject singleMessageInfo = new JSONObject();
         singleMessageInfo.put("sender", sender);
         singleMessageInfo.put("timeSent", timeSent);
         singleMessageInfo.put("message", contentMessage);

        JSONArray retrievedMessages = getChatJSONRemotely(binID);
        retrievedMessages.put(singleMessageInfo);
        updateChatRemote(retrievedMessages, binID);
    }
    private void updateChatRemote(JSONArray messages, String binID){
        JSONObject body = new JSONObject();
        body.put("messages", messages);
        RequestBody updateBody = RequestBody.create(body.toString(), mediaType);
        OkHttpClient client = new OkHttpClient();
        Request uploadRequest = new Request.Builder()
                .url(API_URL + "/" + binID)
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
    public void updateChatWithBinID(String friendUsername, String binID) {
        this.friendTobinID.put(friendUsername, binID);
        Chat existingChat = getChatRemote(binID);
        if (existingChat != null) chatList.put(friendUsername, existingChat);
    }

    @Override
    public HashMap<String, ArrayList<String>> getChats() { // Friend username to an array consists of last message, timeSent, sender, in the same order.
        HashMap<String, ArrayList<String>> ret = new HashMap<>();
        for (String friendUsername :
                chatList.keySet()) {
            ArrayList<String> tempArray = new ArrayList<>();
            Chat currentChat = chatList.get(friendUsername);
            Message lastCommonMessage = currentChat.getLastMessage();
            tempArray.add(lastCommonMessage.getMessage());
            tempArray.add(lastCommonMessage.getTimeSent().toString());
            tempArray.add(lastCommonMessage.getSender());
            ret.put(friendUsername, tempArray);
        }
        return ret;
    }

    @Override
    public boolean chatIsEmpty() {
        return chatList.isEmpty();
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

    public Chat getChat(String friendName) {
        return chatList.get(friendName);
    }

}