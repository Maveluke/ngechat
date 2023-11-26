package app;

import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_contact.AddContactViewModel;
import interface_adapter.chat_list.ChatListViewModel;
import interface_adapter.friends_list.FriendsListViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import view.*;

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
        }catch (Exception e){
            System.out.println("The creation of User Data Access Object is unsuccessful");
            throw new IOException();
        }
        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, chatListViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        AddContactView addContactView = AddContactViewFactory.create(viewManagerModel, addContactViewModel, friendsListViewModel, userDataAccessObject);
        views.add(addContactView, addContactView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
