package use_case.chatlist;

import use_case.login.LoginOutputData;

public interface ChatListOutputBoundary {

    void prepareSuccessView(ChatListOutputData chatListOutputData);

    void prepareFailView(String error);

}
