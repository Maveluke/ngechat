package use_case.add_chat;

import entity.User;
import use_case.chat_list.ChatListDataAccessInterface;
import use_case.chat_list.ChatListOutputBoundary;
import use_case.chat_list.ChatListOutputData;

import java.util.ArrayList;
import java.util.HashMap;

public class AddChatInteractor implements AddChatInputBoundary {

    private AddChatDataAccessInterface addChatDataAccessObject;
    private AddChatOutputBoundary addChatListPresenter;

    private


    public AddChatInteractor(AddChatDataAccessInterface addChatDataAccessObject, AddChatOutputBoundary addChatListPresenter){
        this.addChatDataAccessObject = addChatDataAccessObject;
        this.addChatListPresenter = addChatListPresenter;
    }

    @Override
    public void execute(AddChatInputData addChatInputData) {
        if (add) {
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
