package Use_case;

import data_access.ChatListDataAccessObject;
import data_access.UserDataAccessObject;
import entity.CommonChatFactory;
import entity.CommonUserFactory;
import interface_adapter.create_chat.CreateChatController;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import use_case.create_chat.*;
import use_case.login.*;

import static org.junit.Assert.*;

public class CreateChatInteractorTest {

    CreateChatInputData createChatInputData;
    UserDataAccessObject userRepository;
    ChatListDataAccessObject chatRepository;

    CommonUserFactory userFactory = new CommonUserFactory();
    CommonChatFactory chatFactory = new CommonChatFactory();


    @BeforeEach
    public void init() {

                String masterKey = "$2a$10$xfVheBzZjicxu..Dy7zLHeBNVrrPWZ/jEK/qfX7nTY.WKY/Tx9LM2";

                chatRepository = new ChatListDataAccessObject(masterKey, chatFactory);
                userRepository = new UserDataAccessObject(masterKey, userFactory);

                userRepository.setCurrentUsername("admin");
            }
    @Test
    public void successTest() {

        init();
        CreateChatOutputBoundary successPresenter = new CreateChatOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateChatOutputData outputData) {
                assertEquals("admin2", outputData.getUserToChat());
                assertEquals(outputData.getSender(), "admin");
            }

        };

        CreateChatInputData createChatInputData = new CreateChatInputData("admin2");
        CreateChatInputBoundary interactor = new CreateChatInteractor(chatRepository ,userRepository, successPresenter,  chatFactory );
        CreateChatController controller = new CreateChatController(interactor);
        controller.execute(createChatInputData.getUsername());



    }



}
