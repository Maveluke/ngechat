package Use_case;

import org.junit.Test;
import entity.CommonUserFactory;
import data_access.UserDataAccessObject;
import use_case.block_contact.BlockContactInputData;
import use_case.block_contact.BlockContactInteractor;
import use_case.block_contact.BlockContactOutputBoundary;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class BlockContactInteractorTest {

    @Test
    public void successTest() {
        CommonUserFactory commonUserFactory = new CommonUserFactory();

        String masterKey = "$2a$10$xfVheBzZjicxu..Dy7zLHeBNVrrPWZ/jEK/qfX7nTY.WKY/Tx9LM2";
        UserDataAccessObject userDataAccessObject = new UserDataAccessObject(masterKey, commonUserFactory);

        userDataAccessObject.setCurrentUsername("admin");
        userDataAccessObject.addFriend("admin", "admin3");

        BlockContactInputData blockContactInputData = new BlockContactInputData("admin3");

        BlockContactOutputBoundary blockContactPresenter = new BlockContactOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                assertFalse(userDataAccessObject.get("admin").isFriendWith("admin3"));
                assertFalse(userDataAccessObject.get("admin3").isFriendWith("admin"));

            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected. ");
            }
        };
        BlockContactInteractor blockContactInteractor = new BlockContactInteractor(blockContactPresenter, userDataAccessObject);
        blockContactInteractor.execute(blockContactInputData);
    }
}