package Use_case;

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
import interface_adapter.create_chat.CreateChatController;
import interface_adapter.create_chat.CreateChatPresenter;
import interface_adapter.friends_list.FriendsListViewModel;
import interface_adapter.in_chat.InChatPrivateViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.send_message.SendMessageController;
import interface_adapter.send_message.SendMessagePresenter;
import interface_adapter.send_message.SendMessageViewModel;
import interface_adapter.signup.SignupViewModel;
import org.junit.Test;
import use_case.chat_list.ChatListInputBoundary;
import use_case.chat_list.ChatListInteractor;
import use_case.chat_list.ChatListOutputBoundary;
import use_case.create_chat.CreateChatInputBoundary;
import use_case.create_chat.CreateChatInteractor;
import use_case.create_chat.CreateChatOutputBoundary;
import use_case.create_chat.CreateChatOutputData;
import use_case.send_message.*;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SendMessageTest {
    @Test
    public void testSendMessage() throws IOException{
        String messageText = "Hello";
        String sender = "admin";
        String friendName = "budi";

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

        userDataAccessObject.setCurrentUsername("admin");

        ChatListOutputBoundary chatListPresenter = new ChatListPresenter(chatListViewModel, viewManagerModel);
        ChatListInputBoundary chatListInteractor = new ChatListInteractor(userDataAccessObject, chatListDataAccessObject, chatListPresenter);
        ChatListController chatListController = new ChatListController(chatListInteractor);
        chatListController.execute();

        CreateChatOutputBoundary createChatPresenter = new CreateChatPresenter(inChatPrivateViewModel, viewManagerModel);
        CreateChatInputBoundary createChatInteractor = new CreateChatInteractor(chatListDataAccessObject, userDataAccessObject, createChatPresenter, commonChatFactory);
        CreateChatController createChatController = new CreateChatController(createChatInteractor);
        createChatController.execute("budi");


        SendMessageOutputBoundary sendMessagePresenter = new SendMessagePresenter(sendMessageViewModel, viewManagerModel);
        SendMessageInputBoundary sendMessageInteractor = new SendMessageInteractor(chatListDataAccessObject, sendMessagePresenter);
        SendMessageController sendMessageController = new SendMessageController(sendMessageInteractor);
        sendMessageController.execute(messageText, sender, friendName);
        assertEquals(sender, sendMessageViewModel.getState().getSender());
        assertEquals("", sendMessageViewModel.getState().getMessage());

        SendMessageInputData sendMessageInputData = new SendMessageInputData(messageText, sender, friendName);
        assertEquals(messageText, sendMessageInputData.getMessageText());
        assertEquals(sender, sendMessageInputData.getSender());
        assertEquals(friendName, sendMessageInputData.getFriendName());

        sendMessageInteractor.execute(sendMessageInputData);
        SendMessageOutputData sendMessageOutputData = new SendMessageOutputData(messageText, LocalDateTime.now().toString(), sender);
        sendMessagePresenter.prepareSuccessView(sendMessageOutputData);
    }

}
