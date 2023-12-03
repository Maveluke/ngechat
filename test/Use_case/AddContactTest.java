package Use_case;

import data_access.ChatListDataAccessObject;
import data_access.UserDataAccessObject;
import entity.CommonChatFactory;
import entity.CommonUserFactory;
import entity.User;
import interface_adapter.add_contact.AddContactController;
import interface_adapter.add_contact.AddContactViewModel;
import interface_adapter.friends_list.FriendsListViewModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import use_case.add_contact.AddContactInputData;
import use_case.add_contact.AddContactInteractor;
import use_case.add_contact.AddContactOutputBoundary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AddContactTest {

    private UserDataAccessObject userDataAccessObject;
    private ChatListDataAccessObject chatListDataAccessObject;
    private FriendsListViewModel friendsListViewModel;
    private AddContactViewModel addContactViewModel;
    private User currentUser;
    private User friend;
    @BeforeEach
    public void init() {
        String masterKey = "$2a$10$xfVheBzZjicxu..Dy7zLHeBNVrrPWZ/jEK/qfX7nTY.WKY/Tx9LM2";
        CommonUserFactory userFactory = new CommonUserFactory();
        this.userDataAccessObject = new UserDataAccessObject(masterKey, userFactory);
        CommonChatFactory commonChatFactory = new CommonChatFactory();
        userDataAccessObject.setCurrentUsername("admin");
        this.currentUser = userDataAccessObject.get("admin");
        this.friend = userDataAccessObject.get("admin3");
    }

    @Test
    public void testAddContactInteractor() {
        init();
        AddContactOutputBoundary successPresenter = new AddContactOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                assert(currentUser.isFriendWith("admin3"));
                assert(friend.isFriendWith("admin"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };
        AddContactInteractor addContactInteractor = new AddContactInteractor(successPresenter, userDataAccessObject);
        AddContactInputData addContactInputData = new AddContactInputData("admin3");
        AddContactController addContactController = new AddContactController(addContactInteractor);
        addContactController.execute(addContactInputData.getFriendUsername());
        tearDown();
    }
    @AfterEach
    public void tearDown() {
        userDataAccessObject.blockFriend(currentUser.getName(), friend.getName());
    }
}
