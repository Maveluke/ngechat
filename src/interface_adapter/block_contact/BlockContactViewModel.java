package interface_adapter.block_contact;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BlockContactViewModel extends ViewModel {

    public final static String TITLE_LABEL = "Block Contact View";

    private BlockContactState state = new BlockContactState();

    public BlockContactViewModel() { super("block contact"); }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() { support.firePropertyChange("state", null, this.state);}

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void setState(BlockContactState state) { this.state = state; }

    public BlockContactState getState() { return state; }
}
