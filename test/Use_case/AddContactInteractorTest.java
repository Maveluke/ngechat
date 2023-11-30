package Use_case;

import org.junit.Test;
import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import use_case.add_contact.AddContactInputData;
import use_case.add_contact.AddContactInteractor;
import use_case.add_contact.AddContactOutputBoundary;

import static org.junit.Assert.assertTrue;

public class AddContactInteractorTest {
    @Test
    public void successTest() {
        CommonUserFactory commonUserFactory = new CommonUserFactory();

        String masterKey = "$2a$10$xfVheBzZjicxu..Dy7zLHeBNVrrPWZ/jEK/qfX7nTY.WKY/Tx9LM2";
        UserDataAccessObject userDataAccessObject = new UserDataAccessObject(masterKey, commonUserFactory);

        userDataAccessObject.setCurrentUsername("admin");

        AddContactInputData addContactInputData = new AddContactInputData("admin3");

        AddContactOutputBoundary addContactPresenter = new AddContactOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                assertTrue(userDataAccessObject.get("admin").isFriendWith("admin3"));
                assertTrue(userDataAccessObject.get("admin3").isFriendWith("admin"));
            }

            @Override
            public void prepareFailView(String error) {
                System.out.println("Failed to add contact. ");
            }
        };
        AddContactInteractor addContactInteractor = new AddContactInteractor(addContactPresenter, userDataAccessObject);
        addContactInteractor.execute(addContactInputData);

        userDataAccessObject.blockFriend("admin", "admin3");
    }
}
