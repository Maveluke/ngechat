package interface_adapter.block_contact;

import use_case.block_contact.BlockContactInputBoundary;
import use_case.block_contact.BlockContactInputData;

import java.util.ArrayList;

public class BlockContactController {

    final BlockContactInputBoundary blockContactInteractor;

    public BlockContactController(BlockContactInputBoundary blockContactInteractor) {
        this.blockContactInteractor = blockContactInteractor;
    }

    public void execute(ArrayList<String> friendUsernames) {
        for (String friendUsername: friendUsernames) {
            BlockContactInputData blockContactInputData = new BlockContactInputData(friendUsername);
            blockContactInteractor.execute(blockContactInputData);
        }
    }
}
