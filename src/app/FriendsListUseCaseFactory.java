package app;

import data_access.ChatListDataAccessObject;
import data_access.UserDataAccessObject;
import entity.CommonChatFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.chat_list.ChatListController;
import interface_adapter.chat_list.ChatListPresenter;
import interface_adapter.chat_list.ChatListViewModel;
import interface_adapter.block_contact.BlockContactController;
import interface_adapter.block_contact.BlockContactPresenter;
import interface_adapter.block_contact.BlockContactViewModel;
import interface_adapter.create_chat.CreateChatController;
import interface_adapter.create_chat.CreateChatPresenter;
import interface_adapter.friends_list.FriendsListController;
import interface_adapter.friends_list.FriendsListPresenter;
import interface_adapter.friends_list.FriendsListViewModel;
import interface_adapter.in_chat.InChatPrivateController;
import interface_adapter.in_chat.InChatPrivatePresenter;
import interface_adapter.in_chat.InChatPrivateViewModel;
import use_case.block_contact.BlockContactDataAccessInterface;
import use_case.block_contact.BlockContactInputBoundary;
import use_case.block_contact.BlockContactInteractor;
import use_case.block_contact.BlockContactOutputBoundary;
import use_case.chat_list.ChatListDataAccessInterface;
import use_case.chat_list.ChatListInputBoundary;
import use_case.chat_list.ChatListInteractor;
import use_case.chat_list.ChatListOutputBoundary;
import use_case.create_chat.CreateChatInputBoundary;
import use_case.create_chat.CreateChatInteractor;
import use_case.create_chat.CreateChatOutputBoundary;
import use_case.friends_list.FriendsListDataAccessInterface;
import use_case.friends_list.FriendsListInputBoundary;
import use_case.friends_list.FriendsListInteractor;
import use_case.friends_list.FriendsListOutputBoundary;
import use_case.in_chat.InChatDataAccessInterface;
import use_case.in_chat.InChatInputBoundary;
import use_case.in_chat.InChatInteractor;
import use_case.in_chat.InchatOutputBoundary;
import use_case.login.LoginDataAccessInterface;
import view.FriendsListView;

import javax.swing.*;
import java.io.IOException;

public class FriendsListUseCaseFactory {

    public static FriendsListView create(
            ViewManagerModel viewManagerModel, FriendsListViewModel friendsListViewModel,
            ChatListViewModel chatListViewModel,
            ChatListDataAccessObject chatListDataAccessObject, BlockContactViewModel blockContactViewModel, UserDataAccessObject userDataAccessObject, InChatPrivateViewModel inChatPrivateViewModel) {

        try {
            InChatPrivateController inChatPrivateController = createInChatController(viewManagerModel, inChatPrivateViewModel, chatListDataAccessObject, chatListDataAccessObject);
            CreateChatController createChatController = createCreateChatController(viewManagerModel, inChatPrivateViewModel, chatListDataAccessObject, userDataAccessObject);
            FriendsListController friendsListController = createFriendsListController(viewManagerModel, friendsListViewModel, userDataAccessObject);
            ChatListController chatListController = createChatListController(viewManagerModel, chatListViewModel, chatListDataAccessObject, userDataAccessObject);
            BlockContactController blockContactController = createBlockContactController(viewManagerModel, friendsListViewModel, blockContactViewModel, userDataAccessObject);
            return new FriendsListView(friendsListController, friendsListViewModel, chatListController, SwitchViewUseCaseFactory.createSwitchViewUseCase(viewManagerModel), blockContactController, blockContactViewModel, inChatPrivateController, inChatPrivateViewModel, createChatController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Something's wrong. Please Try Again");
        }

        return null;
    }

    private static FriendsListController createFriendsListController(ViewManagerModel viewManagerModel, FriendsListViewModel friendsListViewModel, FriendsListDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        FriendsListOutputBoundary friendsListOutputBoundary = new FriendsListPresenter(viewManagerModel, friendsListViewModel);

        FriendsListInputBoundary friendsListInteractor = new FriendsListInteractor(
                userDataAccessObject, friendsListOutputBoundary);

        return new FriendsListController(friendsListInteractor);
    }

    private static CreateChatController createCreateChatController(ViewManagerModel viewManagerModel, InChatPrivateViewModel inChatPrivateViewModel, ChatListDataAccessObject chatListDataAccessObject, UserDataAccessObject userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        CreateChatOutputBoundary createChatPresenter = new CreateChatPresenter(inChatPrivateViewModel,viewManagerModel );
        CommonChatFactory chatFactory = new CommonChatFactory();

        CreateChatInputBoundary createChatInteractor = new CreateChatInteractor(
                chatListDataAccessObject, userDataAccessObject, createChatPresenter, chatFactory);

        return new CreateChatController(createChatInteractor);
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

    private static BlockContactController createBlockContactController(ViewManagerModel viewManagerModel, FriendsListViewModel friendsListViewModel, BlockContactViewModel blockContactViewModel, BlockContactDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        BlockContactOutputBoundary blockContactOutputBoundary = new BlockContactPresenter(viewManagerModel, friendsListViewModel, blockContactViewModel);

        BlockContactInputBoundary blockContactInteractor = new BlockContactInteractor(
                 blockContactOutputBoundary, userDataAccessObject);

        return new BlockContactController(blockContactInteractor);
    }

    private static InChatPrivateController createInChatController(ViewManagerModel viewManagerModel, InChatPrivateViewModel inChatPrivateViewModel, InChatDataAccessInterface inChatDataAccessInterface, ChatListDataAccessInterface chatListDataAccessInterface) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        InchatOutputBoundary inchatOutputBoundary = new InChatPrivatePresenter(inChatPrivateViewModel,viewManagerModel);

        InChatInputBoundary inChatInteractor = new InChatInteractor(
                inChatDataAccessInterface, chatListDataAccessInterface,inchatOutputBoundary);

        return new InChatPrivateController(inChatInteractor);
    }
}



