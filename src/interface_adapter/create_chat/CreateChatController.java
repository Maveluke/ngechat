package interface_adapter.create_chat;

import use_case.create_chat.CreateChatInputBoundary;
import use_case.create_chat.CreateChatInputData;
import use_case.in_chat.InChatInputBoundary;
import use_case.in_chat.InChatInputData;

public class CreateChatController {

    private CreateChatInputBoundary createChatInteractor;

    public CreateChatController(CreateChatInputBoundary createChatInteractor) {
        this.createChatInteractor = createChatInteractor;

    }

    public void execute(String friendName) {
        CreateChatInputData createChatInputData = new CreateChatInputData(friendName);

        createChatInteractor.execute(createChatInputData);


    }
}
