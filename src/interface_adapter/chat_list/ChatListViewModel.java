package interface_adapter.chat_list;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChatListViewModel extends ViewModel {

    ChatListState state = new ChatListState();
    public ChatListViewModel() {
        super("chat list");
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ChatListState getState() {
        return state;
    }

    public void setState(ChatListState state){
        this.state = state;
    }
}
