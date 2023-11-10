package use_case.add_contact;

public class AddContactInteractor implements AddContactInputBoundary {

    private final AddContactDataAccessInterface userDataAccessObject;
    private final AddContactOutputBoundary addContactPresenter;

    public AddContactInteractor(AddContactOutputBoundary addContactOutputBoundary,
                                AddContactDataAccessInterface dataAccessObject){
        this.userDataAccessObject = dataAccessObject;
        this.addContactPresenter = addContactOutputBoundary;
    }

    public void execute(AddContactInputData addContactInputData) {

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
