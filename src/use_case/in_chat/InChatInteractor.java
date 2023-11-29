package use_case.in_chat;

import entity.User;
import use_case.chat_list.ChatListDataAccessInterface;
import use_case.chat_list.ChatListInputBoundary;
import use_case.chat_list.ChatListOutputBoundary;
import use_case.chat_list.ChatListOutputData;
import use_case.login.LoginDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class InChatInteractor implements InChatInputBoundary {

    private InChatDataAccessInterface inChatDataAccessObject;
    private ChatListDataAccessInterface chatListDataAccessObject;
    private InchatOutputBoundary inChatPresenter;

    public InChatInteractor(InChatDataAccessInterface inChatDataAccessObject, ChatListDataAccessInterface chatListDataAccessObject, InchatOutputBoundary inChatPresenter){
        this.inChatDataAccessObject = inChatDataAccessObject;
        this.chatListDataAccessObject = chatListDataAccessObject;
        this.inChatPresenter = inChatPresenter;
    }

    @Override
    public void execute(InChatInputData inChatInputData) {


        String friendName = inChatInputData.getFriendName();
        ArrayList<ArrayList<Object>> messages = inChatDataAccessObject.getChat(friendName).getMessages();

        InchatOutputData inchatOutputData = new InchatOutputData(messages);

        inChatPresenter.prepareSuccessView(inchatOutputData);

    }


}
