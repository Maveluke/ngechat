package Use_case;

import org.junit.Test;
import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import use_case.add_contact.AddContactInputData;

import static org.junit.Assert.assertTrue;

public class AddContactInteractorTest {
    @Test
    public void successTest() {
        CommonUserFactory commonUserFactory = new CommonUserFactory();
        User user1 = commonUserFactory.create("user1", "user1");
        User user2 = commonUserFactory.create("user2", "user2");

        String masterKey = "$2a$10$xfVheBzZjicxu..Dy7zLHeBNVrrPWZ/jEK/qfX7nTY.WKY/Tx9LM2";
        UserDataAccessObject userDataAccessObject = new UserDataAccessObject(masterKey, commonUserFactory);
        userDataAccessObject.save(user1);
        userDataAccessObject.save(user2);

        AddContactInputData addContactInputData = new AddContactInputData(user2.getName());


    }
}
