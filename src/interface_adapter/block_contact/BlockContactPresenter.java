package interface_adapter.block_contact;

import interface_adapter.ViewManagerModel;
import interface_adapter.friends_list.FriendsListViewModel;
import use_case.block_contact.BlockContactOutputBoundary;

public class BlockContactPresenter implements BlockContactOutputBoundary {

    private final BlockContactViewModel blockContactViewModel;
    private final FriendsListViewModel friendsListViewModel;
    private ViewManagerModel viewManagerModel;

    public BlockContactPresenter(ViewManagerModel viewManagerModel,
                                 FriendsListViewModel friendsListViewModel,
                                 BlockContactViewModel blockContactViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.friendsListViewModel = friendsListViewModel;
        this.blockContactViewModel = blockContactViewModel;
    }
    @Override
    public void prepareSuccessView() {

    }

    @Override
    public void prepareFailView(String error) {

    }
}
