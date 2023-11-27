package use_case.login;

import entity.User;

import java.util.Objects;

public class LoginInteractor implements LoginInputBoundary {

    private final LoginDataAccessInterface userDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginOutputBoundary logInOutputBoundary,
                           LoginDataAccessInterface dataAccessObject){
        this.userDataAccessObject = dataAccessObject;
        this.loginPresenter = logInOutputBoundary;
    }

    public void execute(LoginInputData logInInputData) {

        String username = logInInputData.getUsername();
        String password = logInInputData.getPassword();

        if (!userDataAccessObject.existsByName(logInInputData.getUsername())) {
            loginPresenter.prepareFailView("Username does not exist!");

        } else {
            String pwd = userDataAccessObject.get(username).getPassword();
            if (!Objects.equals(password, pwd)){
                loginPresenter.prepareFailView("Password incorrect!");
            } else {

                User user = userDataAccessObject.get(username);
                userDataAccessObject.setCurrentUsername(username);
                LoginOutputData loginOutputData = new LoginOutputData(user.getName());
                loginPresenter.prepareSuccessView(loginOutputData);
            }
            
        }

    }
}
