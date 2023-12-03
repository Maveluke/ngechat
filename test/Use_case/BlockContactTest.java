package Use_case;

import interface_adapter.block_contact.BlockContactController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import entity.CommonUserFactory;
import entity.User;
import data_access.UserDataAccessObject;
import use_case.block_contact.BlockContactInputData;
import use_case.block_contact.BlockContactInteractor;
import use_case.block_contact.BlockContactOutputBoundary;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class BlockContactTest {

    private UserDataAccessObject userDataAccessObject;
    private BlockContactInputData blockContactInputData;
    private User currentUser;
    private User friend;

    @BeforeEach
    public void init() {
        CommonUserFactory commonUserFactory = new CommonUserFactory();

        String masterKey = "$2a$10$xfVheBzZjicxu..Dy7zLHeBNVrrPWZ/jEK/qfX7nTY.WKY/Tx9LM2";
        this.userDataAccessObject = new UserDataAccessObject(masterKey, commonUserFactory);

        userDataAccessObject.setCurrentUsername("admin");
        userDataAccessObject.addFriend("admin", "budi");

        this.currentUser = userDataAccessObject.getCurrentUser();
        this.friend = userDataAccessObject.get("budi");

        this.blockContactInputData = new BlockContactInputData("budi");
    }

    @Test
    public void successTest() {
        BlockContactOutputBoundary blockContactPresenter = new BlockContactOutputBoundary() {
            @Override
            public void prepareSuccessView(String friendUsername) {
                assertFalse(currentUser.isFriendWith("budi"));
                assertFalse(friend.isFriendWith("admin"));

            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected. ");
            }
        };
        BlockContactInteractor blockContactInteractor = new BlockContactInteractor(blockContactPresenter, userDataAccessObject);
        BlockContactController blockContactController = new BlockContactController(blockContactInteractor);
        ArrayList friendUsernames = new ArrayList<String>();
        friendUsernames.add(blockContactInputData.getFriendUsername());
        blockContactController.execute(friendUsernames);
        tearDown();
    }

    @AfterEach
    public void tearDown() {
        userDataAccessObject.addFriend(currentUser.getName(), friend.getName());
    }
}