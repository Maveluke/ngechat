package Use_case;

import app.InChatUseCaseFactory;
import data_access.ChatListDataAccessObject;
import data_access.UserDataAccessObject;
import entity.CommonChatFactory;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_contact.AddContactViewModel;
import interface_adapter.block_contact.BlockContactViewModel;
import interface_adapter.chat_list.ChatListController;
import interface_adapter.chat_list.ChatListPresenter;
import interface_adapter.chat_list.ChatListViewModel;
import interface_adapter.friends_list.FriendsListController;
import interface_adapter.friends_list.FriendsListViewModel;
import interface_adapter.in_chat.InChatPrivateController;
import interface_adapter.in_chat.InChatPrivatePresenter;
import interface_adapter.in_chat.InChatPrivateViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.send_message.SendMessageViewModel;
import interface_adapter.signup.SignupViewModel;
import org.junit.Test;
import use_case.chat_list.ChatListInputBoundary;
import use_case.chat_list.ChatListInteractor;
import use_case.chat_list.ChatListOutputBoundary;
import use_case.friends_list.FriendsListInputBoundary;
import use_case.friends_list.FriendsListInteractor;
import use_case.friends_list.FriendsListOutputBoundary;
import use_case.friends_list.FriendsListOutputData;
import use_case.in_chat.*;
import view.InChatPrivateView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InChatTest {


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

        InChatInputData inChatInputData = new InChatInputData("budi", "chris");
        String masterKey = "$2a$10$xfVheBzZjicxu..Dy7zLHeBNVrrPWZ/jEK/qfX7nTY.WKY/Tx9LM2";
        CommonChatFactory commonChatFactory = new CommonChatFactory();
        ChatListDataAccessObject chatListDataAccessObject = new ChatListDataAccessObject(masterKey, commonChatFactory);

        InchatOutputBoundary successPresenter = new InchatOutputBoundary() {
            @Override
            public void prepareSuccessView(InchatOutputData inchatOutputData) {

                ArrayList<ArrayList<Object>> expectedArray = new ArrayList<>();

                assertThat(inchatOutputData.getMessages(), is(equalTo(expectedArray)));

                String expectedUsername = "chris";

                assertThat(inchatOutputData.getUsername(), is(equalTo(expectedUsername)));

                String expectedFriend = "budi";

                assertThat(inchatOutputData.getFriendName(), is(equalTo(expectedFriend)));
            }

        };

        UserFactory userFactory = new CommonUserFactory();
        UserDataAccessObject userDataAccessObject = new UserDataAccessObject(masterKey, userFactory);
        userDataAccessObject.setCurrentUsername("chris");
        InChatPrivateView inChatPrivateView = InChatUseCaseFactory.create(viewManagerModel, inChatPrivateViewModel, sendMessageViewModel, chatListViewModel, chatListDataAccessObject, userDataAccessObject);
        views.add(inChatPrivateView, inChatPrivateView.viewName);

        ChatListOutputBoundary chatListPresenter = new ChatListPresenter(chatListViewModel, viewManagerModel);
        ChatListInputBoundary chatListInteractor = new ChatListInteractor(userDataAccessObject, chatListDataAccessObject, chatListPresenter);
        ChatListController chatListController = new ChatListController(chatListInteractor);
        chatListController.execute();

        InchatOutputBoundary inChatPresenter = new InChatPrivatePresenter(inChatPrivateViewModel, viewManagerModel);
        InChatInputBoundary interactor = new InChatInteractor(chatListDataAccessObject, chatListDataAccessObject, inChatPresenter);

        interactor.execute(inChatInputData);

        InChatPrivateController inChatPrivateController = new InChatPrivateController(interactor);
        inChatPrivateController.execute(inChatInputData.getFriendName(), inChatInputData.getUsername());
    }

}
