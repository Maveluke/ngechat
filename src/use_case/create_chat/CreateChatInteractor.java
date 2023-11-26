package use_case.create_chat;

import entity.Chat;
import entity.ChatFactory;

public class CreateChatInteractor implements CreateChatInputBoundary{

    private CreateChatDataAccessInterface createChatDataAccessObject;
    private CreateChatOutputBoundary createChatPresenter;

    private ChatFactory chatFactory;


    public CreateChatInteractor(CreateChatDataAccessInterface createChatDataAccessObject, CreateChatOutputBoundary createChatPresenter, ChatFactory chatFactory) {
        this.createChatDataAccessObject = createChatDataAccessObject;
        this.createChatPresenter = createChatPresenter;
        this.chatFactory = chatFactory;
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

            Chat newChat = chatFactory.create(userToChat, null);

            CreateChatOutputData createChatOutputData = new CreateChatOutputData(userToChat);

            createChatPresenter.prepareSuccessView(createChatOutputData);


        }


            //implement the case where the chat is just created
    }
}
