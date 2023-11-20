package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.chat_list.ChatListViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import use_case.login.LoginDataAccessInterface;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            ChatListViewModel chatListViewModel,
            LoginDataAccessInterface userDataAccessObject) {

        LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, chatListViewModel, userDataAccessObject);
        return new LoginView(loginViewModel, loginController);
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
}


