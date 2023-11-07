package use_case.chatlist;

import java.util.ArrayList;

public class ChatListInteractor implements ChatListInputBoundary{


    private ChatListDataAccessInterface chatListDataAccessObject;
    private ChatListOutputBoundary chatListPresenter;

    public ChatListInteractor(ChatListDataAccessInterface chatListDataAccessObject, ChatListOutputBoundary chatListPresenter){
        this.chatListDataAccessObject = chatListDataAccessObject;
        this.chatListPresenter = chatListPresenter;
    }

    @Override
    public void execute() {
        if (chatListDataAccessObject.is_empty()) {
            chatListPresenter.prepareFailView("No chat available");
        } else {
            ArrayList<chat> chatList = chatListDataAccessObject.getChat()
            ChatListOutputData chatListOutputData = new ChatListOutputData(chatList);
            chatListPresenter.prepareSuccessView(chatListOutputData);
        }
    }
}
