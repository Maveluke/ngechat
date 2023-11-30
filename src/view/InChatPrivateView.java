package view;

import interface_adapter.chat_list.ChatListController;
import interface_adapter.chat_list.ChatListViewModel;
import interface_adapter.in_chat.InChatPrivateController;
import interface_adapter.in_chat.InChatPrivateViewModel;
import interface_adapter.send_message.SendMessageController;
import interface_adapter.send_message.SendMessageViewModel;
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
import java.lang.reflect.Array;
import java.util.ArrayList;

public class InChatPrivateView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "in chat";
     private final InChatPrivateController inChatPrivateController;
    private final InChatPrivateViewModel inChatPrivateViewModel;

    private final ChatListViewModel chatListViewModel;

    private final ChatListController chatListController;

    private final SendMessageController sendMessageController;
    private final SendMessageViewModel sendMessageViewModel;


    // features : panels and buttons:

    private final JTextField textInputField = new JTextField(40);

    public InChatPrivateView(InChatPrivateController controller, InChatPrivateViewModel inChatPrivateViewModel, SendMessageViewModel sendMessageViewModel, SendMessageController sendMessageController,
                ChatListController chatListController, ChatListViewModel chatListViewModel) {
        this.inChatPrivateController = controller;
        this.inChatPrivateViewModel = inChatPrivateViewModel;
        this.chatListViewModel = chatListViewModel;
        this.chatListController = chatListController;
        this.sendMessageController = sendMessageController;
        this.sendMessageViewModel = sendMessageViewModel;

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

        ImageIcon profpic = new ImageIcon("src/View/Photos/GenericPP.jpg");
//      TODO: change profpic filename to the actual profpic file name
        Image image = profpic.getImage();
        Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        profpic = new ImageIcon(newimg);
        JLabel _profpic = new JLabel(profpic);


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
        header.add(_profpic, BorderLayout.WEST);

        this.add(header);


        ArrayList<ArrayList<Object>> allMessages = new ArrayList<>();
        ArrayList<Object> inner = new ArrayList<>();
        ArrayList<Object> inner_inner = new ArrayList<>();

        inner_inner.add(0, "Hi, ajfhajkghajg agbjakhgjkahg agbjkahgjkaeg");
        inner_inner.add(1, "date");
        inner_inner.add(2, "benny");

        inner.add(0, "benny");
        inner.add(1, inner_inner);

        allMessages.add(0, inner);

        ArrayList<Object> inner2 = new ArrayList<>();
        ArrayList<Object> inner_inner2 = new ArrayList<>();

        inner_inner2.add(0, "How are you doing? akfajkhfjkagjkang akjfbajkgjkagkjag agbajkbgkjabgk");
        inner_inner2.add(1, "date");
        inner_inner2.add(2, "wisnu");

        inner2.add(0, "wisnu");
        inner2.add(1, inner_inner2);

        allMessages.add(inner2);

        // ArrayList<ArrayList<Object>> allMessages = inChatPrivateViewModel.getState().getMessages();
        for (ArrayList<Object> messages : allMessages) {

            JPanel finalPanel = new JPanel();
            finalPanel.setLayout(new BoxLayout(finalPanel, BoxLayout.Y_AXIS));


            String sender = (String) messages.get(0);
            ArrayList<String> singleMessage = (ArrayList<String>) messages.get(1);

            String messageText = singleMessage.get(0);
            String dateTime = singleMessage.get(1);

            JPanel messagePanel = new JPanel();
            messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));


            // Create a custom Font with a larger size
            Font customFont = new Font("Times New Roman", Font.BOLD, 16);

            //  Create a custom color (for example, red)
            Color customColor;

            if ("benny".equals(sender)) {
                customColor = Color.RED;

            } else{
                customColor = Color.DARK_GRAY.brighter();
            }

            JLabel senderLabel = new JLabel(sender);
            JLabel messageLabel = new JLabel("<html><b><font size='5' color='" + getColorHex(customColor) + "'>" + messageText + "</html>");
            JLabel dateTimeLabel = new JLabel("Date: " + dateTime);

            messageLabel.setFont(customFont);

//            if (inChatPrivateViewModel.getState().getSender().equals(sender)) {
            if ("benny".equals(sender)) {
                setAlignmentX(Component.RIGHT_ALIGNMENT);

                senderLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
                messageLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
                dateTimeLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

                messageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            } else {
                setAlignmentX(Component.LEFT_ALIGNMENT);

                senderLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                messageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                dateTimeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

                messageLabel.setHorizontalAlignment(SwingConstants.LEFT);
            }

            setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

//            senderLabel.setAlignmentY(Component.LEFT_ALIGNMENT);
//            messageLabel.setAlignmentY(Component.LEFT_ALIGNMENT);
//            dateTimeLabel.setAlignmentY(Component.LEFT_ALIGNMENT);



            messagePanel.add(senderLabel);
            messagePanel.add(messageLabel);
            messagePanel.add(dateTimeLabel);

            // Add vertical spacing between messages using Box.createVerticalStrut
            add(Box.createVerticalStrut(10));

            finalPanel.add(messagePanel);
            this.add(finalPanel);

            this.add(Box.createRigidArea(new Dimension(40, 0))); // Adjust the width as needed
        }
    }
    /*
    Helper method to set out the colour of the text
     */
    private String getColorHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
