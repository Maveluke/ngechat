package use_case.add_chat;

import entity.ChatFactory;
import entity.User;
import interface_adapter.add_contact.AddContactPresenter;
import use_case.chat_list.ChatListDataAccessInterface;
import use_case.chat_list.ChatListOutputBoundary;
import use_case.chat_list.ChatListOutputData;

import java.util.ArrayList;
import java.util.HashMap;

public class AddChatInteractor implements AddChatInputBoundary {

    private AddChatDataAccessInterface addChatDataAccessObject;
    private AddChatOutputBoundary addChatPresenter;

    private ChatFactory chatFactory;


    public AddChatInteractor(AddChatDataAccessInterface addChatDataAccessObject, AddChatOutputBoundary addChatListPresenter, ChatFactory chatFactory){
        this.addChatDataAccessObject = addChatDataAccessObject;
        this.addChatPresenter = addChatListPresenter;
        this.chatFactory = chatFactory;
    }

    @Override
    public void execute() {

        addChatPresenter.prepareSuccessView();

    }


}
