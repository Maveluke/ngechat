package use_case.chat_list;

import entity.User;

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
        if (chatListDataAccessObject.chatIsEmpty()) {
            chatListPresenter.prepareFailView("No chat available");
        } else {
            User currentUser = chatListDataAccessObject.getCurrentUser();
            String currentUserName = currentUser.getName();

            HashMap<String, ArrayList<String>> chatList = chatListDataAccessObject.getChats();

            ChatListOutputData chatListOutputData = new ChatListOutputData(chatList, currentUserName);
            chatListPresenter.prepareSuccessView(chatListOutputData);
        }
    }

}
