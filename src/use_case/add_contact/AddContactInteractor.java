package use_case.add_contact;

public class AddContactInteractor implements AddContactInputBoundary {
    /**
     * This class is responsible for adding a friend to the current user's friend list.
     * It will call the data access object to add the friend to the current user's friend list.
     * It will call the presenter to prepare the view.
     */

    private final AddContactDataAccessInterface userDataAccessObject;
    private final AddContactOutputBoundary addContactPresenter;

    public AddContactInteractor(AddContactOutputBoundary addContactOutputBoundary,
                                AddContactDataAccessInterface dataAccessObject){
        this.userDataAccessObject = dataAccessObject;
        this.addContactPresenter = addContactOutputBoundary;
    }

    public void execute(AddContactInputData addContactInputData) {
        /**
         * This method is used to add a friend to the current user's friend list.
         */
        String username = userDataAccessObject.getCurrentUser().getName();
        String friendUsername = addContactInputData.getFriendUsername();

        if (!userDataAccessObject.existsByName(friendUsername)) {
            addContactPresenter.prepareFailView("User does not exist!");

        }
        else if (username.equals(friendUsername)) {
            addContactPresenter.prepareFailView("You cannot add yourself!");
        }
        else{
            if (userDataAccessObject.addFriend(username, friendUsername)){
                addContactPresenter.prepareSuccessView();
            }
            else{
                addContactPresenter.prepareFailView("Fail to add friend");
            }
        }
    }
}
