package app;

import data_access.ChatListDataAccessObject;
import data_access.UserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.chat_list.ChatListController;
import interface_adapter.chat_list.ChatListPresenter;
import interface_adapter.chat_list.ChatListViewModel;
import interface_adapter.in_chat.InChatPrivateController;
import interface_adapter.in_chat.InChatPrivatePresenter;
import interface_adapter.in_chat.InChatPrivateViewModel;
import interface_adapter.send_message.SendMessageController;
import interface_adapter.send_message.SendMessagePresenter;
import interface_adapter.send_message.SendMessageViewModel;
import use_case.chat_list.ChatListDataAccessInterface;
import use_case.chat_list.ChatListInputBoundary;
import use_case.chat_list.ChatListInteractor;
import use_case.chat_list.ChatListOutputBoundary;
import use_case.in_chat.InChatDataAccessInterface;
import use_case.in_chat.InChatInputBoundary;
import use_case.in_chat.InChatInteractor;
import use_case.in_chat.InchatOutputBoundary;
import use_case.login.LoginDataAccessInterface;
import use_case.send_message.SendMessageDataAccessInterface;
import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInteractor;
import use_case.send_message.SendMessageOutputBoundary;
import view.InChatPrivateView;

import javax.swing.*;
import java.io.IOException;

public class InChatUseCaseFactory {
    public static InChatPrivateView create(
            ViewManagerModel viewManagerModel, InChatPrivateViewModel inChatPrivateViewModel, SendMessageViewModel sendMessageViewModel, ChatListViewModel chatListViewModel, ChatListDataAccessObject chatListDataAccessObject, UserDataAccessObject userDataAccessObject) {

        try {
            InChatPrivateController inChatPrivateController = createInChatController(viewManagerModel, inChatPrivateViewModel, chatListDataAccessObject, chatListDataAccessObject);
            SendMessageController sendMessageController = createSendMessageController(viewManagerModel, sendMessageViewModel, chatListDataAccessObject);
            ChatListController chatListController = createChatListController(viewManagerModel, chatListViewModel, chatListDataAccessObject, userDataAccessObject);
            return new InChatPrivateView(inChatPrivateController, inChatPrivateViewModel, sendMessageViewModel, sendMessageController, chatListController, chatListViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Something's wrong. Please Try Again");
        }

        return null;
    }

    private static InChatPrivateController createInChatController(ViewManagerModel viewManagerModel, InChatPrivateViewModel inChatPrivateViewModel, InChatDataAccessInterface inChatDataAccessInterface, ChatListDataAccessInterface chatListDataAccessInterface) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        InchatOutputBoundary inchatOutputBoundary = new InChatPrivatePresenter(inChatPrivateViewModel,viewManagerModel);

        InChatInputBoundary inChatInteractor = new InChatInteractor(
                inChatDataAccessInterface, chatListDataAccessInterface,inchatOutputBoundary);

        return new InChatPrivateController(inChatInteractor);
    }

    private static SendMessageController createSendMessageController(ViewManagerModel viewManagerModel, SendMessageViewModel sendMessageViewModel, SendMessageDataAccessInterface sendMessageDataAccessInterface) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SendMessageOutputBoundary sendMessagePresenter = new SendMessagePresenter(sendMessageViewModel, viewManagerModel);

        SendMessageInputBoundary sendMessageInteractor = new SendMessageInteractor(
                sendMessageDataAccessInterface, sendMessagePresenter);

        return new SendMessageController(sendMessageInteractor);
    }

    private static ChatListController createChatListController(
            ViewManagerModel viewManagerModel,
            ChatListViewModel chatListViewModel,
            ChatListDataAccessInterface chatListDataAccessObject,
            LoginDataAccessInterface userDataAccessObject){

        // Notice how we pass this method's parameters to the Presenter.
        ChatListOutputBoundary chatListOutputBoundary = new ChatListPresenter(chatListViewModel, viewManagerModel);

        ChatListInputBoundary chatListInteractor = new ChatListInteractor(userDataAccessObject, chatListDataAccessObject, chatListOutputBoundary);

        return new ChatListController(chatListInteractor);
    }
}
