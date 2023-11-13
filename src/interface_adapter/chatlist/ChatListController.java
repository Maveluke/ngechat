package interface_adapter.chatlist;

import use_case.chatlist.ChatListInputBoundary;

public class ChatListController {
     private final ChatListInputBoundary userChatListInteractor;
    public ChatListController(ChatListInputBoundary userChatListInteractor) {
        this.userChatListInteractor = userChatListInteractor;
    }

    public void execute() {
        userChatListInteractor.execute();
    }
}