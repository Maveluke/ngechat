package use_case.block_contact;

public interface BlockContactOutputBoundary {
    void prepareSuccessView();

    void prepareFailView(String error);
}
