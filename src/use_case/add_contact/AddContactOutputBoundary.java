package use_case.add_contact;

public interface AddContactOutputBoundary {

    void prepareSuccessView();

    void prepareFailView(String error);

}
