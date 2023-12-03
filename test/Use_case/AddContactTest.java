package Use_case;

import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import interface_adapter.add_contact.AddContactController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import use_case.add_contact.AddContactInputData;
import use_case.add_contact.AddContactInteractor;
import use_case.add_contact.AddContactOutputBoundary;

import static org.junit.jupiter.api.Assertions.fail;

public class AddContactTest {

    private UserDataAccessObject userDataAccessObject;
    private User currentUser;
    private User friend;
    @BeforeEach
    public void init() {
        String masterKey = "$2a$10$xfVheBzZjicxu..Dy7zLHeBNVrrPWZ/jEK/qfX7nTY.WKY/Tx9LM2";
        CommonUserFactory userFactory = new CommonUserFactory();
        this.userDataAccessObject = new UserDataAccessObject(masterKey, userFactory);
        userDataAccessObject.setCurrentUsername("admin");
        this.currentUser = userDataAccessObject.getCurrentUser();
        this.friend = userDataAccessObject.get("user");
    }

    @Test
    public void testAddContact() {
        userDataAccessObject.setCurrentUsername("admin");
        this.currentUser = userDataAccessObject.get("admin");
        this.friend = userDataAccessObject.get("chris");
    }

    @Test
    public void testAddContactInteractor() {
        init();
        AddContactOutputBoundary successPresenter = new AddContactOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                assert(currentUser.isFriendWith("chris"));
                assert(friend.isFriendWith("admin"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };
        AddContactInteractor addContactInteractor = new AddContactInteractor(successPresenter, userDataAccessObject);
        AddContactInputData addContactInputData = new AddContactInputData("user");
        AddContactController addContactController = new AddContactController(addContactInteractor);
        addContactController.execute(addContactInputData.getFriendUsername());
        tearDown();
    }
    @AfterEach
    public void tearDown() {
        userDataAccessObject.blockFriend(currentUser.getName(), friend.getName());
    }
}
