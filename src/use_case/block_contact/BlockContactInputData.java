package use_case.block_contact;

public class BlockContactInputData {
    private final String friendUsername;

    public BlockContactInputData(String friendUsername) { this.friendUsername = friendUsername; }

    public String getFriendUsername() { return friendUsername; }
}
