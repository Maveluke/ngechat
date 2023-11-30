package view.in_chat;

import interface_adapter.chat_list.ChatListController;
import interface_adapter.chat_list.ChatListViewModel;
import interface_adapter.in_chat.InChatPrivateController;
import interface_adapter.in_chat.InChatPrivateViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InChatPrivateView extends JPanel{
    private final InChatPrivateController inChatPrivateController;
    private final InChatPrivateViewModel inChatPrivateViewModel;

    private final ChatListViewModel chatListViewModel;

    private final ChatListController chatListController;

    InChatPrivateView(InChatPrivateController controller, InChatPrivateViewModel inChatPrivateViewModel, ChatListViewModel chatListViewModel, ChatListController chatListController) {
        this.inChatPrivateController = controller;
        this.inChatPrivateViewModel = inChatPrivateViewModel;
        this.chatListViewModel = chatListViewModel;
        this.chatListController = chatListController;

//        JFrame inchatframe = new JFrame("ngechat");
//        inchatframe.setLayout(new BoxLayout(inchatframe.getContentPane(), BoxLayout.Y_AXIS));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel header = new JPanel();
        header.setLayout(new BorderLayout());

        String friendName = inChatPrivateViewModel.getState().getFriendName();
        
        JLabel friend  = new JLabel(friendName);
        header.add(friend);

        ImageIcon back = new ImageIcon("src/view/Photos/Return.png");
//      TODO: change to the actual profpic
        Image _back = back.getImage();
        Image newback = _back.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        back = new ImageIcon(newback);
        JLabel backicon = new JLabel(back);
        backicon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//
                chatListController.execute();

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        header.add(backicon, BorderLayout.EAST);

        this.add(header);
    }
}
