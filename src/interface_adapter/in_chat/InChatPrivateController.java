package interface_adapter.in_chat;

import use_case.in_chat.InChatInputBoundary;
import use_case.in_chat.InChatInputData;
import use_case.in_chat.InchatOutputData;

public class InChatPrivateController {


    private InChatInputBoundary inChatInteractor;

    public InChatPrivateController(InChatInputBoundary inChatInteractor){
        this.inChatInteractor = inChatInteractor;


    }

    public void execute(String friendName) {
        InChatInputData inChatInputData = new InChatInputData(friendName);

        inChatInteractor.execute(inChatInputData);


    }


}
