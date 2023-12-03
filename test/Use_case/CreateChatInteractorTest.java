package Use_case;

import data_access.ChatListDataAccessObject;
import data_access.UserDataAccessObject;
import entity.CommonChatFactory;
import entity.CommonUserFactory;
import org.junit.Test;
import use_case.create_chat.*;
import use_case.login.*;

import static org.junit.Assert.*;

public class CreateChatInteractorTest {

    @Test
    public void successTest() {

        CreateChatInputData createChatInputData = new CreateChatInputData("admin2");
        String masterKey = "$2a$10$xfVheBzZjicxu..Dy7zLHeBNVrrPWZ/jEK/qfX7nTY.WKY/Tx9LM2";
        CommonUserFactory userFactory = new CommonUserFactory();
        CommonChatFactory chatFactory = new CommonChatFactory();
        CreateChatUserDataAccessInterface userRepository = new UserDataAccessObject(masterKey, userFactory);
        CreateChatDataAccessInterface chatRepository = new ChatListDataAccessObject(masterKey, chatFactory);
        ((UserDataAccessObject) userRepository).setCurrentUsername("admin");
        CreateChatOutputBoundary successPresenter = new CreateChatOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateChatOutputData outputData) {
                assertEquals("admin2", outputData.getUserToChat());
                assertEquals(outputData.getSender(), "admin");
            }

        };
        CreateChatInputBoundary interactor = new CreateChatInteractor(chatRepository ,userRepository, successPresenter,  chatFactory );
        interactor.execute(createChatInputData);

    }
}
