package entity;

public class Chat {
    /***
     * This class represents the chat shown in ChatListView
     */
    private Message lastMessage;

    private String username;

    public Chat(String username, Message message) {
        this.lastMessage = message;
        this.username = username;
    }

    public String getName() {
        return username;
    }

    public void setLastMessage(Message newMessage) {
        this.lastMessage = newMessage;
    }

    public Message getLastMessage(){
        return lastMessage;
    }

}
