package use_case.create_chat;

import data_access.UserDataAccessObject;
import entity.Chat;
import entity.CommonChatFactory;

import java.util.ArrayList;

public class CreateChatInteractor implements CreateChatInputBoundary{

    private CreateChatDataAccessInterface createChatDataAccessObject;
    private CreateChatUserDataAccessInterface userDataAccessObject;
    private CreateChatOutputBoundary createChatPresenter;

    private CommonChatFactory commonChatFactory;


    public CreateChatInteractor(CreateChatDataAccessInterface createChatDataAccessObject, CreateChatUserDataAccessInterface userDataAccessObject, CreateChatOutputBoundary createChatPresenter, CommonChatFactory commonChatFactory) {
        this.createChatDataAccessObject = createChatDataAccessObject;
        this.createChatPresenter = createChatPresenter;
        this.commonChatFactory = commonChatFactory;
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public void execute(CreateChatInputData createChatInputData) {
            //implement the case where the chat already exists

        String userToChat = createChatInputData.getUsername();

        if (createChatDataAccessObject.chatExist(userToChat)) {

            CreateChatOutputData createChatOutputData = new CreateChatOutputData(userToChat, userDataAccessObject.getCurrentUser().getName());

            createChatPresenter.prepareSuccessView(createChatOutputData);
            // it will send the name of the friend we want to chat
            // so then after that we can display the in-chat view.
        } else {
            // Add the new chat to ChatListDAO
            ArrayList<ArrayList<Object>> newMessageInfo = new ArrayList<>();
            Chat newChat = commonChatFactory.create(newMessageInfo, createChatDataAccessObject.getBinID(userToChat));
            createChatDataAccessObject.addChat(userToChat, newChat);

            CreateChatOutputData createChatOutputData = new CreateChatOutputData(userToChat, userDataAccessObject.getCurrentUser().getName());

            createChatPresenter.prepareSuccessView(createChatOutputData);
        }
    }
}
