package Use_case;

import app.SignupUseCaseFactory;
import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_contact.AddContactViewModel;
import interface_adapter.block_contact.BlockContactViewModel;
import interface_adapter.chat_list.ChatListViewModel;
import interface_adapter.friends_list.FriendsListViewModel;
import interface_adapter.in_chat.InChatPrivateViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.send_message.SendMessageViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupViewModel;
import use_case.signup.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import view.SignupView;
import view.ViewManager;

import javax.swing.*;

import java.awt.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SignupTest {

    private CommonUserFactory commonUserFactory;
    private UserDataAccessObject userDataAccessObject;

    @BeforeEach
    public void init() {

        this.commonUserFactory = new CommonUserFactory();
        String masterKey = "$2a$10$xfVheBzZjicxu..Dy7zLHeBNVrrPWZ/jEK/qfX7nTY.WKY/Tx9LM2";
        this.userDataAccessObject = new UserDataAccessObject(masterKey, commonUserFactory);
    }

    @Test
    public void successTest() {
        init();

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

        SignupInputData signupInputData = new SignupInputData("user", "user", "user");

        SignupOutputBoundary signupOutputBoundary = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                assertTrue(userDataAccessObject.existsByName("user"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected. ");
            }
        };
        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        SignupInteractor signupInteractor = new SignupInteractor(userDataAccessObject, signupOutputBoundary, commonUserFactory);
        SignupController signupController = new SignupController(signupInteractor);

        signupController.execute(signupInputData.getUsername(), signupInputData.getPassword(), signupInputData.getRepeatPassword());
    }

}
