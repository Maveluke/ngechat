package use_case.create_chat;

import entity.Chat;
import entity.CommonChatFactory;

import java.util.ArrayList;

public class CreateChatInteractor implements CreateChatInputBoundary{

    private CreateChatDataAccessInterface createChatDataAccessObject;
    private CreateChatOutputBoundary createChatPresenter;

    private CommonChatFactory commonChatFactory;


    public CreateChatInteractor(CreateChatDataAccessInterface createChatDataAccessObject, CreateChatOutputBoundary createChatPresenter, CommonChatFactory commonChatFactory) {
        this.createChatDataAccessObject = createChatDataAccessObject;
        this.createChatPresenter = createChatPresenter;
        this.commonChatFactory = commonChatFactory;
    }

    @Override
    public void execute(CreateChatInputData createChatInputData) {
            //implement the case where the chat already exists

        String userToChat = createChatInputData.getUsername();

        if (createChatDataAccessObject.chatExist(userToChat)) {

            CreateChatOutputData createChatOutputData = new CreateChatOutputData(userToChat);

            createChatPresenter.prepareSuccessView(createChatOutputData);
            // it will send the name of the friend we want to chat
            // so then after that we can display the in-chat view.
        } else {
            // Add the new chat to ChatListDAO
            ArrayList<ArrayList<Object>> newMessageInfo = new ArrayList<>();
            Chat newChat = commonChatFactory.create(newMessageInfo, createChatDataAccessObject.getBinID(userToChat));
            createChatDataAccessObject.addChat(userToChat, newChat);

            CreateChatOutputData createChatOutputData = new CreateChatOutputData(userToChat);

            createChatPresenter.prepareSuccessView(createChatOutputData);
        }
    }
}
