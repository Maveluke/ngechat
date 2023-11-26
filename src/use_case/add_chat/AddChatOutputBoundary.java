package use_case.add_chat;

import use_case.chat_list.ChatListOutputData;

public interface AddChatOutputBoundary {


    void prepareFailView(String noChatAvailable);

    void prepareSuccessView();
}
