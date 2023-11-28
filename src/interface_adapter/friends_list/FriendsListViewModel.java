package interface_adapter.friends_list;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.security.PublicKey;
import java.util.HashMap;

public class FriendsListViewModel extends ViewModel {

    public HashMap<String, String> friendslist = new HashMap<String, String>();

    public FriendsListViewModel() {
        super("friends list");
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("friendslist", null, friendslist);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void setFriendslist(HashMap<String, String> friendslist) {
        this.friendslist = friendslist;
    }

    public HashMap<String, String> getFriendslist() {
        return friendslist;
    }
}
