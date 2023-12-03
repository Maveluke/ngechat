package Use_case;

import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import interface_adapter.login.LoginController;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import use_case.login.*;

import static org.junit.Assert.*;

public class LoginTest {

    CommonUserFactory userFactory = new CommonUserFactory();

    LoginDataAccessInterface userRepository;

    @BeforeEach
    public void init(){

        String masterKey = "$2a$10$xfVheBzZjicxu..Dy7zLHeBNVrrPWZ/jEK/qfX7nTY.WKY/Tx9LM2";

        userRepository = new UserDataAccessObject(masterKey, userFactory);
    }

    @Test
    public void successTest() {

        init();
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
        LoginInputData loginInputData = new LoginInputData("admin", "admin");
        LoginInputBoundary interactor = new LoginInteractor(successPresenter, userRepository);
        LoginController controller = new LoginController(interactor);
        controller.execute(loginInputData.getUsername(), loginInputData.getPassword());

    }



    @Test
    public void failureTest() {

        init();

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
        LoginInputData loginInputData = new LoginInputData("timothy", "admin");
        LoginInputBoundary interactor = new LoginInteractor(successPresenter, userRepository);
        LoginController controller = new LoginController(interactor);
        controller.execute(loginInputData.getUsername(), loginInputData.getPassword());

    }


}
