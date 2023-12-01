package Use_case;

import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import org.junit.Test;
import use_case.signup.*;

import static org.junit.Assert.assertTrue;

public class SignupInteractorTest {

    @Test
    public void successTest() {
        CommonUserFactory commonUserFactory = new CommonUserFactory();
        String masterKey = "$2a$10$xfVheBzZjicxu..Dy7zLHeBNVrrPWZ/jEK/qfX7nTY.WKY/Tx9LM2";
        UserDataAccessObject userDataAccessObject = new UserDataAccessObject(masterKey, commonUserFactory);

        SignupInputData signupInputData = new SignupInputData("user", "user", "user");

        SignupOutputBoundary signupOutputBoundary = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                assertTrue(userDataAccessObject.existsByName("user"));
            }

            @Override
            public void prepareFailView(String error) {
                System.out.println("Use case failure is unexpected. " + error);
            }
        };
        SignupInteractor signupInteractor = new SignupInteractor(userDataAccessObject, signupOutputBoundary, commonUserFactory);
        signupInteractor.execute(signupInputData);
    }

}
