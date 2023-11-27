package interface_adapter.block_contact;

import use_case.block_contact.BlockContactInputBoundary;
import use_case.block_contact.BlockContactInputData;

public class BlockContactController {

    final BlockContactInputBoundary blockContactInteractor;

    public BlockContactController(BlockContactInputBoundary blockContactInteractor) {
        this.blockContactInteractor = blockContactInteractor;
    }

    public void execute(String friendUsername) {
        BlockContactInputData blockContactInputData = new BlockContactInputData(friendUsername);
        blockContactInteractor.execute(blockContactInputData);
    }
}
