package use_case.block_contact;

public class BlockContactOutputData {
    boolean IsBlocked;

    public BlockContactOutputData(boolean isBlocked) { this.IsBlocked = isBlocked; }

    public boolean getIsBlocked() {return IsBlocked; }
}
