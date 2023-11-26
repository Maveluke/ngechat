package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.chat_list.ChatListController;
import interface_adapter.chat_list.ChatListPresenter;
import interface_adapter.chat_list.ChatListViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import use_case.chat_list.ChatListDataAccessInterface;
import use_case.chat_list.ChatListInputBoundary;
import use_case.chat_list.ChatListInteractor;
import use_case.chat_list.ChatListOutputBoundary;
import use_case.login.LoginDataAccessInterface;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import view.ChatListView;
import view.LoginView;

public class ChatListUseCaseFactory {
    public static ChatListView create(
            ViewManagerModel viewManagerModel,
            ChatListViewModel chatListViewModel,
            ChatListDataAccessInterface chatListDataAccessObject) {

        ChatListController chatListController = createChatListController(viewManagerModel, chatListViewModel, chatListDataAccessObject);
        return new ChatListView(chatListController, chatListViewModel);
    }

    private static ChatListController createChatListController(
            ViewManagerModel viewManagerModel,
            ChatListViewModel chatListViewModel,
            ChatListDataAccessInterface chatListDataAccessObject){

        // Notice how we pass this method's parameters to the Presenter.
        ChatListOutputBoundary chatListOutputBoundary = new ChatListPresenter(chatListViewModel, viewManagerModel);

        ChatListInputBoundary chatListInteractor = new ChatListInteractor(chatListDataAccessObject, chatListOutputBoundary);

        return new ChatListController(chatListInteractor);
    }
}
