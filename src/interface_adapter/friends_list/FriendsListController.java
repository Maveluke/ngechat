package interface_adapter.friends_list;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

public class FriendsListController {

    final SignupInputBoundary userSignupUseCaseInteractor;
    public FriendsListController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    public void execute(String username, String password1, String password2) {
        SignupInputData signupInputData = new SignupInputData(
                username, password1, password2);

        userSignupUseCaseInteractor.execute(signupInputData);
    }
}
