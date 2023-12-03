package Use_case;

import app.FriendsListUseCaseFactory;
import data_access.ChatListDataAccessObject;
import data_access.UserDataAccessObject;
import entity.CommonChatFactory;
import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_contact.AddContactViewModel;
import interface_adapter.block_contact.BlockContactViewModel;
import interface_adapter.chat_list.ChatListViewModel;
import interface_adapter.friends_list.FriendsListController;
import interface_adapter.friends_list.FriendsListPresenter;
import interface_adapter.friends_list.FriendsListViewModel;
import interface_adapter.in_chat.InChatPrivateViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.send_message.SendMessageViewModel;
import interface_adapter.signup.SignupViewModel;
import org.junit.Test;
import use_case.friends_list.FriendsListInputBoundary;
import use_case.friends_list.FriendsListInteractor;
import use_case.friends_list.FriendsListOutputBoundary;
import use_case.friends_list.FriendsListOutputData;
import view.FriendsListView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class FriendsListTest {

    @Test
    public void successTest() {
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

        String masterKey = "$2a$10$xfVheBzZjicxu..Dy7zLHeBNVrrPWZ/jEK/qfX7nTY.WKY/Tx9LM2";
        CommonUserFactory userFactory = new CommonUserFactory();
        UserDataAccessObject userRepository = new UserDataAccessObject(masterKey, userFactory);
        userRepository.addFriend("chris", "budi");

        userRepository.setCurrentUsername("chris");
        CommonChatFactory commonChatFactory = new CommonChatFactory();
        ChatListDataAccessObject chatListDataAccessObject;
        chatListDataAccessObject = new ChatListDataAccessObject(masterKey, commonChatFactory);
        FriendsListOutputBoundary successPresenter = new FriendsListOutputBoundary() {
            @Override
            public void prepareSuccessView(FriendsListOutputData friendsListOutputData) {

                ArrayList<String> expectedArray = new ArrayList<>();
                expectedArray.add("budi");

                assertThat(friendsListOutputData.getFriends(), is(equalTo(expectedArray)));
            }

        };
        FriendsListView friendsListView = FriendsListUseCaseFactory.create(viewManagerModel, friendsListViewModel, chatListViewModel, chatListDataAccessObject, blockContactViewModel, userRepository, inChatPrivateViewModel);
        views.add(friendsListView, friendsListView.viewName);

        FriendsListInputBoundary interactor = new FriendsListInteractor(userRepository, successPresenter);

        interactor.execute();

        FriendsListController friendsListController = new FriendsListController(interactor);
        friendsListController.execute();
    }

}