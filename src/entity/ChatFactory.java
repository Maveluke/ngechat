package entity;

public class ChatFactory {

    public Chat create(String username, Message message){
        return new Chat(username, message);
    }

}
