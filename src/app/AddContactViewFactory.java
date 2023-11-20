package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_contact.AddContactController;
import interface_adapter.add_contact.AddContactPresenter;
import interface_adapter.add_contact.AddContactViewModel;
import interface_adapter.friends_list.FriendsListViewModel;
import use_case.add_contact.AddContactDataAccessInterface;
import use_case.add_contact.AddContactInputBoundary;
import use_case.add_contact.AddContactInteractor;
import use_case.add_contact.AddContactOutputBoundary;
import view.AddContactView;

public class AddContactViewFactory {
    /** Prevent instantiation. */
    private AddContactViewFactory() {}
    public static AddContactView create(ViewManagerModel viewManagerModel,
                                        AddContactViewModel addContactViewModel,
                                        FriendsListViewModel friendsListViewModel,
                                        AddContactDataAccessInterface userDataAccessObject){
        AddContactController addContactController = createAddContactController(viewManagerModel,
                addContactViewModel, friendsListViewModel, userDataAccessObject);
        return new AddContactView(addContactViewModel, addContactController);
    }
    private static AddContactController createAddContactController(ViewManagerModel viewManagerModel,
                                                                   AddContactViewModel addContactViewModel,
                                                                   FriendsListViewModel friendsListViewModel,
                                                                   AddContactDataAccessInterface userDataAccessObject){
        AddContactOutputBoundary addContactPresenter = new AddContactPresenter(viewManagerModel, friendsListViewModel, addContactViewModel);
        AddContactInputBoundary addContactInteractor = new AddContactInteractor(addContactPresenter, userDataAccessObject);
        return new AddContactController(addContactInteractor);
    }
}
