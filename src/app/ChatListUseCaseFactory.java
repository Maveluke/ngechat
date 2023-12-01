package app;

import data_access.ChatListDataAccessObject;
import data_access.UserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.chat_list.ChatListController;
import interface_adapter.chat_list.ChatListPresenter;
import interface_adapter.chat_list.ChatListViewModel;
import interface_adapter.friends_list.FriendsListController;
import interface_adapter.friends_list.FriendsListPresenter;
import interface_adapter.friends_list.FriendsListViewModel;
import interface_adapter.in_chat.InChatPrivateController;
import interface_adapter.in_chat.InChatPrivatePresenter;
import interface_adapter.in_chat.InChatPrivateViewModel;
import use_case.chat_list.ChatListDataAccessInterface;
import use_case.chat_list.ChatListInputBoundary;
import use_case.chat_list.ChatListInteractor;
import use_case.chat_list.ChatListOutputBoundary;
import use_case.friends_list.FriendsListDataAccessInterface;
import use_case.friends_list.FriendsListInputBoundary;
import use_case.friends_list.FriendsListInteractor;
import use_case.friends_list.FriendsListOutputBoundary;
import use_case.in_chat.InChatDataAccessInterface;
import use_case.in_chat.InChatInputBoundary;
import use_case.in_chat.InChatInteractor;
import use_case.in_chat.InchatOutputBoundary;
import use_case.login.LoginDataAccessInterface;
import view.ChatListView;

import java.io.IOException;

public class ChatListUseCaseFactory {
    public static ChatListView create(
            ViewManagerModel viewManagerModel,
            ChatListViewModel chatListViewModel,
            FriendsListViewModel friendsListViewModel,
            InChatPrivateViewModel inChatPrivateViewModel,
            ChatListDataAccessObject chatListDataAccessObject, UserDataAccessObject userDataAccessObject) {

        ChatListController chatListController = createChatListController(viewManagerModel, chatListViewModel, chatListDataAccessObject, userDataAccessObject);
        try{
            FriendsListController friendsListController = createFriendsListController(viewManagerModel, friendsListViewModel, userDataAccessObject);
            InChatPrivateController inChatPrivateController = createInChatController(viewManagerModel, inChatPrivateViewModel, chatListDataAccessObject, chatListDataAccessObject);
            return new ChatListView(chatListController, chatListViewModel, friendsListController, inChatPrivateController, inChatPrivateViewModel);
        }catch (IOException e){
            System.out.println("Fail to generate friendsListController in ChatListUseCaseFactory");
        }
        return null;
    }

    private static ChatListController createChatListController(
            ViewManagerModel viewManagerModel,
            ChatListViewModel chatListViewModel,
            ChatListDataAccessInterface chatListDataAccessObject,
            LoginDataAccessInterface userDataAccessObject){

        // Notice how we pass this method's parameters to the Presenter.
        ChatListOutputBoundary chatListOutputBoundary = new ChatListPresenter(chatListViewModel, viewManagerModel);

        ChatListInputBoundary chatListInteractor = new ChatListInteractor(userDataAccessObject, chatListDataAccessObject, chatListOutputBoundary);

        return new ChatListController(chatListInteractor);
    }
    private static FriendsListController createFriendsListController(ViewManagerModel viewManagerModel, FriendsListViewModel friendsListViewModel, FriendsListDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        FriendsListOutputBoundary friendsListOutputBoundary = new FriendsListPresenter(viewManagerModel, friendsListViewModel);

        FriendsListInputBoundary friendsListInteractor = new FriendsListInteractor(
                userDataAccessObject, friendsListOutputBoundary);

        return new FriendsListController(friendsListInteractor);

    }

    private static InChatPrivateController createInChatController(ViewManagerModel viewManagerModel, InChatPrivateViewModel inChatPrivateViewModel, InChatDataAccessInterface inChatDataAccessInterface, ChatListDataAccessInterface chatListDataAccessInterface) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        InchatOutputBoundary inchatOutputBoundary = new InChatPrivatePresenter(inChatPrivateViewModel,viewManagerModel);

        InChatInputBoundary inChatInteractor = new InChatInteractor(
                inChatDataAccessInterface, chatListDataAccessInterface,inchatOutputBoundary);

        return new InChatPrivateController(inChatInteractor);
    }
}
