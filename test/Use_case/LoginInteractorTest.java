package Use_case;

import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import org.junit.Test;
import use_case.login.*;
import use_case.signup.*;

import static org.junit.Assert.*;

public class LoginInteractorTest {
    @Test
    public void successTest() {
        LoginInputData loginInputData = new LoginInputData("Timothy", "ILOVEJESUS");
        String masterKey = "$2a$10$xfVheBzZjicxu..Dy7zLHeBNVrrPWZ/jEK/qfX7nTY.WKY/Tx9LM2";
        String uploadURL = "https://api.jsonbin.io/v3/b";
        String downloadURL = "https://api.jsonbin.io/v3/b/653f1b8c54105e766fc8df34?meta=false";
        LoginDataAccessInterface userRepository = new UserDataAccessObject(masterKey, uploadURL, downloadURL);

        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("Timothy", user.getUsername());
                assertTrue(userRepository.existsByName("Timothy"));
            }

            @Override
            public void prepareFailView(String error) { fail("Use case failure is unexpected.");

            }
        };
        LoginInputBoundary interactor = new LoginInteractor(successPresenter, userRepository);
        interactor.execute(loginInputData);

    }


}
