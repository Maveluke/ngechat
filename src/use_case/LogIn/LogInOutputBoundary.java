package use_case.LogIn;

public interface LogInOutputBoundary {

    void prepareSuccessView(LogInOutputData user);

    void prepareFailView(String error);

}
