package Use_case;

import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import interface_adapter.signup.SignupController;
import use_case.signup.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SignupTest {

    private CommonUserFactory commonUserFactory;
    private UserDataAccessObject userDataAccessObject;

    @BeforeEach
    public void init() {
        this.commonUserFactory = new CommonUserFactory();
        String masterKey = "$2a$10$xfVheBzZjicxu..Dy7zLHeBNVrrPWZ/jEK/qfX7nTY.WKY/Tx9LM2";
        this.userDataAccessObject = new UserDataAccessObject(masterKey, commonUserFactory);
    }

    @Test
    public void successTest() {
        init();

        SignupInputData signupInputData = new SignupInputData("user", "user", "user");

        SignupOutputBoundary signupOutputBoundary = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                assertTrue(userDataAccessObject.existsByName("user"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected. ");
            }
        };
        SignupInteractor signupInteractor = new SignupInteractor(userDataAccessObject, signupOutputBoundary, commonUserFactory);
        SignupController signupController = new SignupController(signupInteractor);

        signupController.execute(signupInputData.getUsername(), signupInputData.getPassword(), signupInputData.getRepeatPassword());
    }

}
