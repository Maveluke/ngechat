package Use_case;

import app.ChatListUseCaseFactory;
import data_access.ChatListDataAccessObject;
import data_access.UserDataAccessObject;
import entity.CommonChatFactory;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_contact.AddContactViewModel;
import interface_adapter.block_contact.BlockContactViewModel;
import interface_adapter.chat_list.ChatListController;
import interface_adapter.chat_list.ChatListPresenter;
import interface_adapter.chat_list.ChatListViewModel;
import interface_adapter.friends_list.FriendsListViewModel;
import interface_adapter.in_chat.InChatPrivateViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.send_message.SendMessageViewModel;
import interface_adapter.signup.SignupViewModel;
import org.junit.Test;
import use_case.chat_list.ChatListInputBoundary;
import use_case.chat_list.ChatListInteractor;
import use_case.chat_list.ChatListOutputBoundary;
import view.ChatListView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ChatListTest {

    @Test
    public void testChatList() throws IOException{
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
        UserFactory userFactory = new CommonUserFactory();
        UserDataAccessObject userDataAccessObject;
        try{
            userDataAccessObject = new UserDataAccessObject(masterKey, userFactory);
        }catch (Exception e){
            System.out.println("The creation of User Data Access Object is unsuccessful with error: " + e.getMessage());
            throw new IOException();
        }
        CommonChatFactory commonChatFactory = new CommonChatFactory();
        ChatListDataAccessObject chatListDataAccessObject;
        try {
            chatListDataAccessObject = new ChatListDataAccessObject(masterKey, commonChatFactory);
        }catch (Exception e){
            System.out.println("The creation of ChatListDAO is unsuccessful");
            throw new IOException();
        }
        ChatListView chatListView = ChatListUseCaseFactory.create(viewManagerModel, chatListViewModel, friendsListViewModel, inChatPrivateViewModel, chatListDataAccessObject, userDataAccessObject);
        views.add(chatListView, chatListView.viewName);
        userDataAccessObject.setCurrentUsername("admin");
        ChatListOutputBoundary chatListPresenter = new ChatListPresenter(chatListViewModel, viewManagerModel);
        ChatListInputBoundary chatListInteractor = new ChatListInteractor(userDataAccessObject, chatListDataAccessObject, chatListPresenter);
        ChatListController chatListController = new ChatListController(chatListInteractor);
        chatListController.execute();
        assertEquals(chatListViewModel.getState().getChatList(), chatListDataAccessObject.getChats());
    }
}
