package interface_adapter.chat_list;

import use_case.chat_list.ChatListInputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

public class ChatListController {

    final ChatListInputBoundary chatListInteractor;
    public ChatListController(ChatListInputBoundary chatListInteractor) {
        this.chatListInteractor = chatListInteractor;
    }

    public void execute() {
        chatListInteractor.execute();
    }
}
