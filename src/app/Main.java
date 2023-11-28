package app;

import data_access.ChatListDataAccessObject;
import data_access.UserDataAccessObject;
import entity.CommonChatFactory;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_contact.AddContactViewModel;
import interface_adapter.chat_list.ChatListViewModel;
import interface_adapter.friends_list.FriendsListViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import view.*;
import view.contacts_list.FriendsListView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException{
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

        String masterKey = "$2a$10$xfVheBzZjicxu..Dy7zLHeBNVrrPWZ/jEK/qfX7nTY.WKY/Tx9LM2";
        UserFactory userFactory = new CommonUserFactory();
        UserDataAccessObject userDataAccessObject;
        try{
            userDataAccessObject = new UserDataAccessObject(masterKey, userFactory);
            // TODO: Delete later
            userDataAccessObject.setCurrentUsername("admin");
            //
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
        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, chatListViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        AddContactView addContactView = AddContactViewFactory.create(viewManagerModel, addContactViewModel, friendsListViewModel, userDataAccessObject);
        views.add(addContactView, addContactView.viewName);

        ChatListView chatListView = ChatListUseCaseFactory.create(viewManagerModel, chatListViewModel, friendsListViewModel, chatListDataAccessObject, userDataAccessObject);
        views.add(chatListView, chatListView.viewName);

        FriendsListView friendsListView = FriendsListUseCaseFactory.create(viewManagerModel, friendsListViewModel, userDataAccessObject);
        views.add(friendsListView, friendsListView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
