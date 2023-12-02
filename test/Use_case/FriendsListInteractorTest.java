package Use_case;

import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import org.junit.Test;
import use_case.add_contact.AddContactDataAccessInterface;
import use_case.friends_list.*;
import use_case.login.*;
import use_case.signup.*;

import java.util.HashMap;

import static org.junit.Assert.*;

public class FriendsListInteractorTest {

//    @Test
//    public void successTest() {
//        String masterKey = "$2a$10$xfVheBzZjicxu..Dy7zLHeBNVrrPWZ/jEK/qfX7nTY.WKY/Tx9LM2";
//        CommonUserFactory userFactory = new CommonUserFactory();
//        UserDataAccessObject userRepository = new UserDataAccessObject(masterKey, userFactory);
//        userRepository.addFriend("admin", "admin2");
//
//        userRepository.setCurrentUsername("admin2");
//
//        FriendsListOutputBoundary successPresenter = new FriendsListOutputBoundary() {
//            @Override
//            public void prepareSuccessView(FriendsListOutputData friendsListOutputData) {
//
//                HashMap<String, String> expectedMap = new HashMap<>();
//                expectedMap.put("admin2", "admin");
//
//                System.out.println(expectedMap);
//                System.out.println(friendsListOutputData.getFriends());
//
//                assertEquals(friendsListOutputData.getFriends(), expectedMap);
//            }
//
//        };
//        FriendsListInputBoundary interactor = new FriendsListInteractor(userRepository, successPresenter);
//        interactor.execute();
//
//    }

}