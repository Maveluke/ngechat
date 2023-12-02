package interface_adapter.friends_list;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;

public class FriendsListViewModel extends ViewModel {

    public FriendsListState friendsListState = new FriendsListState();

    public FriendsListViewModel() {
        super("friends list");
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
  
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.friendsListState);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public FriendsListState getFriendsListState() {
        return friendsListState;
    }

    public void setFriendsListState(FriendsListState friendsListState) {
        this.friendsListState = friendsListState;
    }
}
