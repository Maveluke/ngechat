package use_case.chat_list;

public interface ChatListOutputBoundary {

    void prepareSuccessView(ChatListOutputData chatListOutputData);

    void prepareFailView(String error);

}
