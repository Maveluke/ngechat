package interface_adapter.chatlist;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;

public class ChatListViewModel extends ViewModel{

    private static String username = "Username";
//    TODO: use current user's username

    private HashMap<String, ArrayList<String>> chatList = new HashMap<String, ArrayList<String>>();

    public ChatListViewModel(String viewName) {
        super(viewName);
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("chatList", null, chatList);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public String getUsername() {
        return username;
    }

    public void set_chat(HashMap<String, ArrayList<String>> new_chatlist) {
        chatList = new_chatlist;
    }

    public HashMap<String, ArrayList<String>> getChatList() {
        return new HashMap<String, ArrayList<String>>(chatList);
    }

}
