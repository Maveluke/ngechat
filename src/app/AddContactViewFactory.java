package app;

import data_access.UserDataAccessObject;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_contact.AddContactController;
import interface_adapter.add_contact.AddContactPresenter;
import interface_adapter.add_contact.AddContactViewModel;
import interface_adapter.friends_list.FriendsListController;
import interface_adapter.friends_list.FriendsListPresenter;
import interface_adapter.friends_list.FriendsListViewModel;
import use_case.add_contact.AddContactDataAccessInterface;
import use_case.add_contact.AddContactInputBoundary;
import use_case.add_contact.AddContactInteractor;
import use_case.add_contact.AddContactOutputBoundary;
import use_case.friends_list.FriendsListDataAccessInterface;
import use_case.friends_list.FriendsListInputBoundary;
import use_case.friends_list.FriendsListInteractor;
import use_case.friends_list.FriendsListOutputBoundary;
import view.AddContactView;

import java.io.IOException;

public class AddContactViewFactory {
    /** Prevent instantiation. */
    private AddContactViewFactory() {}
    public static AddContactView create(ViewManagerModel viewManagerModel,
                                        AddContactViewModel addContactViewModel,
                                        FriendsListViewModel friendsListViewModel,
                                        UserDataAccessObject userDataAccessObject){
        AddContactController addContactController = createAddContactController(viewManagerModel,
                addContactViewModel, friendsListViewModel, userDataAccessObject);
        try{
            FriendsListController friendsListController = createFriendsListController(viewManagerModel, friendsListViewModel, userDataAccessObject);
            return new AddContactView(addContactViewModel, friendsListController, addContactController);
        }catch (IOException e){
            System.out.println("Error while creating friends list controller with error: " + e);
        }
        return null;
    }
    private static AddContactController createAddContactController(ViewManagerModel viewManagerModel,
                                                                   AddContactViewModel addContactViewModel,
                                                                   FriendsListViewModel friendsListViewModel,
                                                                   AddContactDataAccessInterface userDataAccessObject){
        AddContactOutputBoundary addContactPresenter = new AddContactPresenter(viewManagerModel, friendsListViewModel, addContactViewModel);
        AddContactInputBoundary addContactInteractor = new AddContactInteractor(addContactPresenter, userDataAccessObject);
        return new AddContactController(addContactInteractor);
    }
    private static FriendsListController createFriendsListController(ViewManagerModel viewManagerModel, FriendsListViewModel friendsListViewModel, FriendsListDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        FriendsListOutputBoundary friendsListOutputBoundary = new FriendsListPresenter(viewManagerModel, friendsListViewModel);

        FriendsListInputBoundary friendsListInteractor = new FriendsListInteractor(
                userDataAccessObject, friendsListOutputBoundary);

        return new FriendsListController(friendsListInteractor);
    }
}
