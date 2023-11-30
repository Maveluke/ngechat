package interface_adapter.in_chat;

import interface_adapter.ViewModel;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class InChatPrivateViewModel extends ViewModel {

    InChatPrivateState state;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public InChatPrivateViewModel(){
        super("in chat");
    };

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public InChatPrivateState getState() {
        return state;
    }

    public void setState(InChatPrivateState newState) {
        this.state = newState;
    }
}
