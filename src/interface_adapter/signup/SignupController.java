package interface_adapter.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

public class SignupController {

    final SignupInputBoundary userSignupInteractor;
    public SignupController(SignupInputBoundary userSignupInteractor) {
        this.userSignupInteractor = userSignupInteractor;
    }

    public void execute(String username, String password1, String password2) {
        SignupInputData signupInputData = new SignupInputData(
                username, password1, password2);

        userSignupInteractor.execute(signupInputData);
    }
}
