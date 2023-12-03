package Use_case;

import app.LoginUseCaseFactory;
import data_access.ChatListDataAccessObject;
import data_access.UserDataAccessObject;
import entity.CommonChat;
import entity.CommonChatFactory;
import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_contact.AddContactViewModel;
import interface_adapter.block_contact.BlockContactViewModel;
import interface_adapter.chat_list.ChatListViewModel;
import interface_adapter.friends_list.FriendsListViewModel;
import interface_adapter.in_chat.InChatPrivateViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginViewModel;
import interface_adapter.send_message.SendMessageViewModel;
import interface_adapter.signup.SignupViewModel;
import org.junit.Test;
import use_case.login.*;
import view.LoginView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.*;

public class LoginTest {
    @Test
    public void successTest() {
        LoginInputData loginInputData = new LoginInputData("admin", "admin");
        String masterKey = "$2a$10$xfVheBzZjicxu..Dy7zLHeBNVrrPWZ/jEK/qfX7nTY.WKY/Tx9LM2";
        CommonUserFactory userFactory = new CommonUserFactory();
        LoginDataAccessInterface userRepository = new UserDataAccessObject(masterKey, userFactory);

        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("admin", user.getUsername());
                assertTrue(userRepository.existsByName("admin"));
            }

            @Override
            public void prepareFailView(String error) { fail("Use case failure is unexpected.");

            }
        };
        LoginInputBoundary interactor = new LoginInteractor(successPresenter, userRepository);
        LoginController loginController = new LoginController(interactor);
        loginController.execute(loginInputData.getUsername(), loginInputData.getPassword());
    }

    @Test
    public void failureTest() {
        JFrame application = new JFrame("ngechat");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        AddContactViewModel addContactViewModel = new AddContactViewModel();
        FriendsListViewModel friendsListViewModel = new FriendsListViewModel();
        ChatListViewModel chatListViewModel = new ChatListViewModel();
        BlockContactViewModel blockContactViewModel = new BlockContactViewModel();
        SendMessageViewModel sendMessageViewModel = new SendMessageViewModel();
        InChatPrivateViewModel inChatPrivateViewModel = new InChatPrivateViewModel();

        LoginInputData loginInputData = new LoginInputData("timothy", "admin");
        String masterKey = "$2a$10$xfVheBzZjicxu..Dy7zLHeBNVrrPWZ/jEK/qfX7nTY.WKY/Tx9LM2";
        CommonUserFactory userFactory = new CommonUserFactory();
        LoginDataAccessInterface userRepository = new UserDataAccessObject(masterKey, userFactory);

        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error)  {
                assertEquals("Username does not exist!", error);

            }
        };
        CommonChatFactory commonChatFactory = new CommonChatFactory();
        ChatListDataAccessObject chatListDataAccessObject = new ChatListDataAccessObject(masterKey, commonChatFactory);
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, chatListViewModel, chatListDataAccessObject, userRepository);
        views.add(loginView, loginView.viewName);

        LoginInputBoundary interactor = new LoginInteractor(successPresenter, userRepository);
        LoginController loginController = new LoginController(interactor);
        loginController.execute(loginInputData.getUsername(), loginInputData.getPassword());
        interactor.execute(loginInputData);
    }

}
