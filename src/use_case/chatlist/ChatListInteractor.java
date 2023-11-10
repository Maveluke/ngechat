package use_case.chatlist;

import entity.Chat;

import java.util.ArrayList;
import java.util.HashMap;

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
            HashMap<String, ArrayList<String>> chatList = chatListDataAccessObject.getChats();
            ChatListOutputData chatListOutputData = new ChatListOutputData(chatList);
            chatListPresenter.prepareSuccessView(chatListOutputData);
        }
    }
}
