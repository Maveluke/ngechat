package use_case.block_contact;

public interface BlockContactOutputBoundary {
    void prepareSuccessView(String friendUsername);

    void prepareFailView(String error);
}
