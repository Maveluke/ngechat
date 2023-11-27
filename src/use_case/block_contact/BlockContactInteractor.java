package use_case.block_contact;

public class BlockContactInteractor implements BlockContactInputBoundary{

        private final BlockContactDataAccessInterface userDataAccessObject;
        private final BlockContactOutputBoundary blockContactPresenter;

        public BlockContactInteractor(BlockContactOutputBoundary blockContactOutputBoundary,
                                      BlockContactDataAccessInterface dataAccessObject) {
            this.userDataAccessObject = dataAccessObject;
            this.blockContactPresenter = blockContactOutputBoundary;
        }

        public void execute(BlockContactInputData blockContactInputData) {
            String username = userDataAccessObject.getCurrentUser().getName();
            String friendUsername = blockContactInputData.getFriendUsername();

            if (userDataAccessObject.blockFriend(username, friendUsername)) {
                blockContactPresenter.prepareSuccessView();
            }
            else {
                blockContactPresenter.prepareFailView("Fail to block friend");
            }
        }
}
