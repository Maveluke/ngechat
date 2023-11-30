package interface_adapter.send_message;

import interface_adapter.ViewModel;
import interface_adapter.in_chat.InChatPrivateState;

import javax.swing.text.View;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SendMessageViewModel extends ViewModel {


    SendMessageState state;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public SendMessageViewModel(){
        super("send message");
    };

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SendMessageState getState() {
        return state;
    }

    public void setState(SendMessageState newState) {
        this.state = newState;
    }

}
