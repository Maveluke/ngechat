package interface_adapter.friends_list;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class FriendsListViewModel extends ViewModel {

    private FriendsListState state = new FriendsListState();

    public FriendsListViewModel() {
        super("friends list");
    }

    public void setState(FriendsListState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public FriendsListState getState() {
        return state;
    }
}
