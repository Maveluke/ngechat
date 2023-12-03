package Use_case;

import data_access.ChatListDataAccessObject;
import data_access.UserDataAccessObject;
import entity.CommonChatFactory;
import entity.CommonUserFactory;
import interface_adapter.friends_list.FriendsListController;
import interface_adapter.in_chat.InChatPrivateController;
import org.junit.Test;
import use_case.friends_list.FriendsListInputBoundary;
import use_case.friends_list.FriendsListInteractor;
import use_case.friends_list.FriendsListOutputBoundary;
import use_case.friends_list.FriendsListOutputData;
import use_case.in_chat.*;
import view.InChatPrivateView;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InChatTest {


    @Test
    public void successTest() {
        InChatInputData inChatInputData = new InChatInputData("budi", "chris");
        String masterKey = "$2a$10$xfVheBzZjicxu..Dy7zLHeBNVrrPWZ/jEK/qfX7nTY.WKY/Tx9LM2";
        CommonChatFactory commonChatFactory = new CommonChatFactory();
        ChatListDataAccessObject chatListDataAccessObject = new ChatListDataAccessObject(masterKey, commonChatFactory);

        InchatOutputBoundary successPresenter = new InchatOutputBoundary() {
            @Override
            public void prepareSuccessView(InchatOutputData inchatOutputData) {

                ArrayList<ArrayList<Object>> expectedArray = new ArrayList<>();

                assertThat(inchatOutputData.getMessages(), is(equalTo(expectedArray)));

                String expectedUsername = "chris";

                assertThat(inchatOutputData.getUsername(), is(equalTo(expectedUsername)));

                String expectedFriend = "budi";

                assertThat(inchatOutputData.getFriendName(), is(equalTo(expectedFriend)));
            }

        };
        InChatInputBoundary interactor = new InChatInteractor(chatListDataAccessObject, chatListDataAccessObject, successPresenter);

        interactor.execute(inChatInputData);

        InChatPrivateController inChatPrivateController = new InChatPrivateController(interactor);
        inChatPrivateController.execute(inChatInputData.getFriendName(), inChatInputData.getUsername());
    }

}
