package use_case.signup;

import entity.User;
import entity.UserFactory;

import java.time.LocalDateTime;

public class SignupInteractor implements SignupInputBoundary {
    /**
     * This class is used to sign up
     */
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        /**
         * This method is used to sign up
         * @param signupInputData: the input data for signing up
         */
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        }
        else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }
        // TODO: Use PasswordValidatorService to validate the password
        else {
            User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword());
            userDataAccessObject.save(user);

            LocalDateTime now = LocalDateTime.now();
            SignupOutputData signupOutputData = new SignupOutputData(user.getName(), now.toString(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}