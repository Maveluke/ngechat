package Use_case;

import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import org.junit.Test;
import use_case.login.*;

import static org.junit.Assert.*;

public class LoginInteractorTest {
    @Test
    public void successTest() {
        LoginInputData loginInputData = new LoginInputData("admin", "admin");
        String masterKey = "$2a$10$xfVheBzZjicxu..Dy7zLHeBNVrrPWZ/jEK/qfX7nTY.WKY/Tx9LM2";
        CommonUserFactory userFactory = new CommonUserFactory();
        LoginDataAccessInterface userRepository = new UserDataAccessObject(masterKey, userFactory);

        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("admin", user.getUsername());
                assertTrue(userRepository.existsByName("admin"));
            }

            @Override
            public void prepareFailView(String error) { fail("Use case failure is unexpected.");

            }
        };
        LoginInputBoundary interactor = new LoginInteractor(successPresenter, userRepository);
        interactor.execute(loginInputData);

    }

    @Test
    public void failureTest() {
        LoginInputData loginInputData = new LoginInputData("timothy", "admin");
        String masterKey = "$2a$10$xfVheBzZjicxu..Dy7zLHeBNVrrPWZ/jEK/qfX7nTY.WKY/Tx9LM2";
        CommonUserFactory userFactory = new CommonUserFactory();
        LoginDataAccessInterface userRepository = new UserDataAccessObject(masterKey, userFactory);

        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error)  {
                assertEquals("Username does not exist!", error);

            }
        };
        LoginInputBoundary interactor = new LoginInteractor(successPresenter, userRepository);
        interactor.execute(loginInputData);

    }


}
