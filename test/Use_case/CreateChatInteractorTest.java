package Use_case;

import data_access.ChatListDataAccessObject;
import data_access.UserDataAccessObject;
import entity.CommonChatFactory;
import entity.CommonUserFactory;
import interface_adapter.create_chat.CreateChatController;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import use_case.create_chat.*;

import static org.junit.Assert.assertEquals;

public class CreateChatInteractorTest {

    CommonUserFactory userFactory = new CommonUserFactory();
    CommonChatFactory chatFactory = new CommonChatFactory();

    @Test
    public void successTest() {
        CreateChatInputData createChatInputData = new CreateChatInputData("admin2");
        String masterKey = "$2a$10$xfVheBzZjicxu..Dy7zLHeBNVrrPWZ/jEK/qfX7nTY.WKY/Tx9LM2";
        ChatListDataAccessObject chatRepository = new ChatListDataAccessObject(masterKey, chatFactory);
        UserDataAccessObject userRepository = new UserDataAccessObject(masterKey, userFactory);

        userRepository.setCurrentUsername("admin");
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
