package use_case.LogIn;

import data_access.DataAccessObject;
import entity.User;

import java.util.Objects;

public class LoginInteractor implements LoginInputBoundary {

    private final LoginDataAccessInterface userDataAccessObject;
    private final LogInOutputBoundary loginPresenter;

    public LoginInteractor(LogInOutputBoundary logInOutputBoundary,
                           LoginDataAccessInterface dataAccessObject){
        this.userDataAccessObject = dataAccessObject;
        this.loginPresenter = logInOutputBoundary;
    }

    public void execute(LogInInputData logInInputData) {

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
                LogInOutputData loginOutputData = new LogInOutputData(user.getName());
                loginPresenter.prepareSuccessView(loginOutputData);

            }
            
        }

    }
}
