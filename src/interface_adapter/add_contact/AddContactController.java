package interface_adapter.add_contact;

import use_case.add_contact.AddContactInputBoundary;
import use_case.add_contact.AddContactInputData;

public class AddContactController {

    final AddContactInputBoundary addContactInteractor;
    public AddContactController(AddContactInputBoundary addContactInteractor) {
        this.addContactInteractor = addContactInteractor;
    }


    public void execute(String friendUsername) {
        AddContactInputData addContactInputData = new AddContactInputData(friendUsername);

        addContactInteractor.execute(addContactInputData);
    }
}
