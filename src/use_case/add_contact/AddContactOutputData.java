package use_case.add_contact;

public class AddContactOutputData {

    boolean IsAdded;

    public AddContactOutputData(boolean isAdded) {
        this.IsAdded = isAdded;
    }

    public boolean getIsAdded(){
        return IsAdded;
    }
}
