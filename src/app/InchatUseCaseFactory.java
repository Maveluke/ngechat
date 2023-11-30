package app;

import data_access.UserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.block_contact.BlockContactController;
import interface_adapter.block_contact.BlockContactPresenter;
import interface_adapter.block_contact.BlockContactViewModel;
import interface_adapter.chat_list.ChatListViewModel;
import interface_adapter.friends_list.FriendsListController;
import interface_adapter.friends_list.FriendsListPresenter;
import interface_adapter.friends_list.FriendsListViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import use_case.block_contact.BlockContactDataAccessInterface;
import use_case.block_contact.BlockContactInputBoundary;
import use_case.block_contact.BlockContactInteractor;
import use_case.block_contact.BlockContactOutputBoundary;
import use_case.friends_list.FriendsListDataAccessInterface;
import use_case.friends_list.FriendsListInputBoundary;
import use_case.friends_list.FriendsListInteractor;
import use_case.friends_list.FriendsListOutputBoundary;
import use_case.login.LoginDataAccessInterface;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import view.LoginView;
import view.contacts_list.FriendsListView;

import javax.swing.*;
import java.io.IOException;

public class InchatUseCaseFactory {
    public static FriendsListView create(
            ViewManagerModel viewManagerModel, FriendsListViewModel friendsListViewModel, BlockContactViewModel blockContactViewModel, UserDataAccessObject userDataAccessObject) {

        try {
            FriendsListController friendsListController = createFriendsListController(viewManagerModel, friendsListViewModel, userDataAccessObject);
            BlockContactController blockContactController = createBlockContactController(viewManagerModel, friendsListViewModel, blockContactViewModel, userDataAccessObject);
            return new FriendsListView(friendsListController, friendsListViewModel, blockContactController, blockContactViewModel);
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

    private static BlockContactController createBlockContactController(ViewManagerModel viewManagerModel, FriendsListViewModel friendsListViewModel, BlockContactViewModel blockContactViewModel, BlockContactDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        BlockContactOutputBoundary blockContactOutputBoundary = new BlockContactPresenter(viewManagerModel, friendsListViewModel, blockContactViewModel);

        BlockContactInputBoundary blockContactInteractor = new BlockContactInteractor(
                blockContactOutputBoundary, userDataAccessObject);

        return new BlockContactController(blockContactInteractor);
    }
}
