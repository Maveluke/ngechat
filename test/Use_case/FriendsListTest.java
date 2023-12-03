package Use_case;

import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import interface_adapter.friends_list.FriendsListController;
import interface_adapter.friends_list.FriendsListPresenter;
import org.junit.Test;
import use_case.friends_list.FriendsListInputBoundary;
import use_case.friends_list.FriendsListInteractor;
import use_case.friends_list.FriendsListOutputBoundary;
import use_case.friends_list.FriendsListOutputData;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class FriendsListTest {

    @Test
    public void successTest() {
        String masterKey = "$2a$10$xfVheBzZjicxu..Dy7zLHeBNVrrPWZ/jEK/qfX7nTY.WKY/Tx9LM2";
        CommonUserFactory userFactory = new CommonUserFactory();
        UserDataAccessObject userRepository = new UserDataAccessObject(masterKey, userFactory);
        userRepository.addFriend("chris", "budi");

        userRepository.setCurrentUsername("chris");

        FriendsListOutputBoundary successPresenter = new FriendsListOutputBoundary() {
            @Override
            public void prepareSuccessView(FriendsListOutputData friendsListOutputData) {

                ArrayList<String> expectedArray = new ArrayList<>();
                expectedArray.add("budi");

                assertThat(friendsListOutputData.getFriends(), is(equalTo(expectedArray)));
            }

        };
        FriendsListInputBoundary interactor = new FriendsListInteractor(userRepository, successPresenter);

        interactor.execute();

        FriendsListController friendsListController = new FriendsListController(interactor);
        friendsListController.execute();
    }

}