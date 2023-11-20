package use_case.friends_list;

import use_case.chat_list.ChatListOutputData;

public interface FriendsListOutputBoundary {


    void prepareFailView(String noChatAvailable);


    void prepareSuccessView(FriendsListOutputData friendsListOutputData);
}
