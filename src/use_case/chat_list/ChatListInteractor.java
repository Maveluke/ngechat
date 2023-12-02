package use_case.chat_list;

import entity.User;
import use_case.login.LoginDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatListInteractor implements ChatListInputBoundary{

    private LoginDataAccessInterface userDataAccessObject;
    private ChatListDataAccessInterface chatListDataAccessObject;
    private ChatListOutputBoundary chatListPresenter;

    public ChatListInteractor(LoginDataAccessInterface userDataAccessObject, ChatListDataAccessInterface chatListDataAccessObject, ChatListOutputBoundary chatListPresenter){
        this.userDataAccessObject = userDataAccessObject;
        this.chatListDataAccessObject = chatListDataAccessObject;
        this.chatListPresenter = chatListPresenter;
    }

    @Override
    public void execute() {
        HashMap<User, String> userFriendTobinID = userDataAccessObject.getCurrentUser().getFriendToBinMap();
        boolean updated = false;
        for (User friendUser :
                userFriendTobinID.keySet()) {
            updated = chatListDataAccessObject.updateChatWithBinID(friendUser.getName(), userFriendTobinID.get(friendUser));
        }
        HashMap<String, ArrayList<String>> chatList = chatListDataAccessObject.getChats();
        ChatListOutputData chatListOutputData = new ChatListOutputData(chatList, updated);
        chatListPresenter.prepareSuccessView(chatListOutputData);
    }
}
