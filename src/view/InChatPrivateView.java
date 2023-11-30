package view;

import interface_adapter.chat_list.ChatListController;
import interface_adapter.chat_list.ChatListViewModel;
import interface_adapter.in_chat.InChatPrivateController;
import interface_adapter.in_chat.InChatPrivateViewModel;
import interface_adapter.signup.SignupViewModel;
import view.LabelTextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class InChatPrivateView extends JPanel implements ActionListener, PropertyChangeListener {
    private final InChatPrivateController inChatPrivateController;
    private final InChatPrivateViewModel inChatPrivateViewModel;

    private final ChatListViewModel chatListViewModel;

    private final ChatListController chatListController;


    // features : panels and buttons:

    private final JTextField textInputField = new JTextField(40);

    public InChatPrivateView(InChatPrivateController controller, InChatPrivateViewModel inChatPrivateViewModel, ChatListViewModel chatListViewModel, ChatListController chatListController) {
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

        LabelTextPanel textInput = new LabelTextPanel(
                new JLabel(SignupViewModel.USERNAME_LABEL), textInputField);

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

        ArrayList<ArrayList<Object>> allMessages = inChatPrivateViewModel.getState().getMessages();
        for (ArrayList<Object> messages : allMessages) {
            String sender = (String) messages.get(0);
            ArrayList<String> singleMessage = (ArrayList<String>) messages.get(1);

            String messageText = singleMessage.get(0);
            String dateTime = singleMessage.get(1);

            JPanel messagePanel = new JPanel();
            messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));

            JLabel senderLabel = new JLabel(sender);
            JLabel messageLabel = new JLabel("<html><b>Message:</b> " + messageText + "</html>");
            JLabel dateTimeLabel = new JLabel("Date: " + dateTime);

            this.add(messagePanel);
            this.add(Box.createVerticalStrut(10));



        }
    }




    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
