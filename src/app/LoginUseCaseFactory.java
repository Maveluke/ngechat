package app;

import data_access.ChatListDataAccessObject;
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
import view.LoginView;

public class LoginUseCaseFactory {

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            ChatListViewModel chatListViewModel,
            ChatListDataAccessObject chatListDataAccessObject,
            LoginDataAccessInterface userDataAccessObject) {

        LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, chatListViewModel, userDataAccessObject);
        ChatListController chatListController = createChatListController(viewManagerModel, chatListViewModel, chatListDataAccessObject, userDataAccessObject);
        return new LoginView(loginViewModel, loginController, chatListController);
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            ChatListViewModel chatListViewModel,
            LoginDataAccessInterface userDataAccessObject){

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, chatListViewModel, loginViewModel);

        LoginInputBoundary loginInteractor = new LoginInteractor(loginOutputBoundary,userDataAccessObject);

        return new LoginController(loginInteractor);
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
}


