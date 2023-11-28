package interface_adapter.block_contact;

import java.util.ArrayList;

public class BlockContactState {

    private ArrayList<String> selected = new ArrayList<String>();

    public BlockContactState() {}

    public void setSelected(ArrayList<String> selected) {
        this.selected = selected;
    }
}
