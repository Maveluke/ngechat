package interface_adapter.add_contact;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AddContactViewModel extends ViewModel {
    public final static String USERNAME_LABEL = "Enter your friend's username";

    public static final String ADD_BUTTON_LABEL = "Add";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private AddContactState state = new AddContactState();

    public AddContactViewModel() {
        super("add contact");
    }

    public void setState(AddContactState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public AddContactState getState() {
        return state;
    }
}
