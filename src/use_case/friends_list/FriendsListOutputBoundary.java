package use_case.friendslist;

import use_case.chatlist.ChatListOutputData;

public interface FriendsListOutputBoundary {


    void prepareFailView(String noChatAvailable);


    void prepareSuccessView(ChatListOutputData chatListOutputData);
}
