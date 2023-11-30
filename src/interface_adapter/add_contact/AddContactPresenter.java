package interface_adapter.add_contact;

import interface_adapter.ViewManagerModel;
import interface_adapter.friends_list.FriendsListState;
import interface_adapter.friends_list.FriendsListViewModel;
import use_case.add_contact.AddContactOutputBoundary;
import use_case.add_contact.AddContactOutputData;
import use_case.login.LoginOutputData;

public class AddContactPresenter implements AddContactOutputBoundary {

    private final AddContactViewModel addContactViewModel;
    private final FriendsListViewModel friendsListViewModel;
    private ViewManagerModel viewManagerModel;

    public AddContactPresenter(ViewManagerModel viewManagerModel,
                               FriendsListViewModel friendsListViewModel,
                               AddContactViewModel addContactViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.friendsListViewModel = friendsListViewModel;
        this.addContactViewModel = addContactViewModel;
    }

    @Override
    public void prepareSuccessView() {
        // On success, switch to friend list view.
        this.viewManagerModel.setActiveView(friendsListViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        AddContactState addContactState = addContactViewModel.getState();
        addContactState.setUsernameError(error);
        addContactViewModel.firePropertyChanged();
    }
}
