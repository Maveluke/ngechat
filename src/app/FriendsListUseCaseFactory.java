package app;

import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.friends_list.FriendsListController;
import interface_adapter.friends_list.FriendsListPresenter;
import interface_adapter.friends_list.FriendsListViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.friends_list.FriendsListDataAccessInterface;
import use_case.friends_list.FriendsListInputBoundary;
import use_case.friends_list.FriendsListInteractor;
import use_case.friends_list.FriendsListOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;
import view.SignupView;
import view.contacts.FriendsListView;

import javax.swing.*;
import java.io.IOException;

public class FriendsListUseCaseFactory {

    public static FriendsListView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, FriendsListViewModel friendsListViewModel, UserDataAccessObject userDataAccessObject) {

        try {
            FriendsListController friendsListController = createFriendsListController(viewManagerModel, friendsListViewModel, userDataAccessObject);
            return new FriendsListView(friendsListController, friendsListViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Something's wrong. Please Try Again");
        }

        return null;
    }

    private static FriendsListController createFriendsListController(ViewManagerModel viewManagerModel, FriendsListViewModel friendsListViewModel, FriendsListDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        FriendsListOutputBoundary friendsListOutputBoundary = new FriendsListPresenter(viewManagerModel, friendsListViewModel);

        FriendsListInputBoundary friendsListInteractor = new FriendsListInteractor(
                userDataAccessObject, friendsListOutputBoundary);

        return new FriendsListController(friendsListInteractor);
    }
}



