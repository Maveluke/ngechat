package use_case.friends_list;

import use_case.chat_list.ChatListOutputData;

public interface FriendsListOutputBoundary {


    void prepareSuccessView(FriendsListOutputData friendsListOutputData);
}
