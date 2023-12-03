package use_case.block_contact;

public class BlockContactInteractor implements BlockContactInputBoundary{
        /**
         * This class is responsible for blocking a friend from the current user's friend list.
         * It will call the data access object to block the friend from the current user's friend list.
         * It will call the presenter to prepare the view.
         */
        private final BlockContactDataAccessInterface userDataAccessObject;
        private final BlockContactOutputBoundary blockContactPresenter;

        public BlockContactInteractor(BlockContactOutputBoundary blockContactOutputBoundary,
                                      BlockContactDataAccessInterface dataAccessObject) {
            this.userDataAccessObject = dataAccessObject;
            this.blockContactPresenter = blockContactOutputBoundary;
        }

        public void execute(BlockContactInputData blockContactInputData) {
            /**
             * This method is used to block a friend from the current user's friend list.
             */
            String username = userDataAccessObject.getCurrentUser().getName();
            String friendUsername = blockContactInputData.getFriendUsername();

            if (userDataAccessObject.blockFriend(username, friendUsername)) {
                blockContactPresenter.prepareSuccessView(friendUsername);
            }
            else {
                blockContactPresenter.prepareFailView("Fail to block friend");
            }
        }
}
