package Use_case;

import org.junit.Test;
import entity.User;
import entity.CommonUserFactory;
import data_access.UserDataAccessObject;
import use_case.block_contact.BlockContactInputData;
import use_case.block_contact.BlockContactInteractor;
import use_case.block_contact.BlockContactOutputBoundary;

import static org.junit.Assert.assertFalse;

public class BlockContactInteractorTest {

    @Test
    public void successTest() {
        CommonUserFactory commonUserFactory = new CommonUserFactory();
        User user1 = commonUserFactory.create("user1", "user1");
        User user2 = commonUserFactory.create("user2", "user2");

        String masterKey = "$2a$10$xfVheBzZjicxu..Dy7zLHeBNVrrPWZ/jEK/qfX7nTY.WKY/Tx9LM2";
        UserDataAccessObject userDataAccessObject = new UserDataAccessObject(masterKey, commonUserFactory);
        userDataAccessObject.save(user1);
        userDataAccessObject.save(user2);

        userDataAccessObject.addFriend(user1.getName(), user2.getName());

        BlockContactInputData blockContactInputData = new BlockContactInputData(user2.getName());

        BlockContactOutputBoundary blockContactPresenter = new BlockContactOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                assertFalse(user1.isFriendWith(user2.getName()));
                assertFalse(user2.isFriendWith(user1.getName()));

            }

            @Override
            public void prepareFailView(String error) {
                System.out.println("Fail to block friend" + error);
            }
        };
        BlockContactInteractor blockContactInteractor = new BlockContactInteractor(blockContactPresenter, userDataAccessObject);
        blockContactInteractor.execute(blockContactInputData);
    }
}