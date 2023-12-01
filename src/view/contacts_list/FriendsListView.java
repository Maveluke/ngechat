package view.contacts_list;

import interface_adapter.add_contact.AddContactController;
import interface_adapter.add_contact.AddContactViewModel;
import interface_adapter.chat_list.ChatListController;
import interface_adapter.create_chat.CreateChatController;
import interface_adapter.friends_list.FriendsListController;
import interface_adapter.friends_list.FriendsListState;
import interface_adapter.friends_list.FriendsListViewModel;
import interface_adapter.block_contact.BlockContactController;
import interface_adapter.block_contact.BlockContactViewModel;
import interface_adapter.in_chat.InChatPrivateController;
import interface_adapter.in_chat.InChatPrivateViewModel;
import interface_adapter.switch_view.SwitchViewController;

import javax.print.attribute.HashPrintJobAttributeSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FriendsListView extends JPanel implements PropertyChangeListener {
    public final String viewName = "friends list";
    private final FriendsListController friendsListController;
    private final ChatListController chatListController;
    private final BlockContactController blockContactController;
    private final FriendsListViewModel friendsListViewModel;
    private final BlockContactViewModel blockContactViewModel;
    private HashMap<String, JPanel> friendsListPanels = new HashMap<>();



    private InChatPrivateController inChatPrivateController;
    private InChatPrivateViewModel inChatPrivateViewModel;
    private CreateChatController createChatController;

    public FriendsListView(FriendsListController controller, FriendsListViewModel friendsListViewModel,
                           ChatListController chatListController, SwitchViewController switchViewController,
                           BlockContactController blockContactController, BlockContactViewModel blockContactViewModel,
                           InChatPrivateController inChatPrivateController, InChatPrivateViewModel inChatPrivateViewModel,
                           CreateChatController createChatController) {
        this.friendsListController = controller;
        this.chatListController = chatListController;
        this.blockContactController = blockContactController;
        this.friendsListViewModel = friendsListViewModel;
        this.blockContactViewModel = blockContactViewModel;
        this.inChatPrivateViewModel = inChatPrivateViewModel;
        this.inChatPrivateController = inChatPrivateController;
        this.createChatController = createChatController;
        this.friendsListViewModel.addPropertyChangeListener(this);



        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        JFrame friendframe = new JFrame("ngechat");
//        friendframe.setLayout(new BoxLayout(friendframe.getContentPane(), BoxLayout.Y_AXIS));

        JPanel header = new JPanel();
        header.setName("Header");
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
        header.setBackground(Color.gray);
        header.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));

        JLabel friends = new JLabel("Friends");
        header.add(friends, BorderLayout.WEST);

        JPanel buttons = new JPanel();

        ImageIcon addfriend = new ImageIcon("src/view/Photos/Plus.png");
        Image _add = addfriend.getImage();
        Image newAdd = _add.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        addfriend = new ImageIcon(newAdd);
        JLabel addIcon = new JLabel(addfriend);
        addIcon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switchViewController.execute("add contact");
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
        buttons.add(addIcon, BorderLayout.EAST);

        ImageIcon block = new ImageIcon("src/view/Photos/Cross.png");
        Image _block = block.getImage();
        Image newBlock = _block.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        block = new ImageIcon(newBlock);
        JLabel blockIcon = new JLabel(block);
        blockIcon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
              setBlockCheckboxesVisibility(friendsListPanels, buttons);
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
        buttons.add(blockIcon, BorderLayout.EAST);

        ImageIcon back = new ImageIcon("src/view/Photos/Return.png");
        Image _back = back.getImage();
        Image newBack = _back.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        back = new ImageIcon(newBack);
        JLabel backIcon = new JLabel(back);
        backIcon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
        buttons.add(backIcon, BorderLayout.EAST);

        header.add(buttons, BorderLayout.EAST);
        header.setBackground(Color.GRAY);
        buttons.setBackground(Color.GRAY);
        this.add(header);

        JPanel friendsPanel = new JPanel();
        friendsPanel.setName("Friends Panel");
        friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.Y_AXIS));
        if(friendsListViewModel.getFriendsListState() != null){
            for (String friend: friendsListViewModel.getFriendsListState().getFriendsList()) {
                JPanel friendpanel = new JPanel();
                friendpanel.setLayout(new BoxLayout(friendpanel, BoxLayout.X_AXIS));

                ImageIcon profpic = new ImageIcon("src/view/Photos/GenericPP.jpg");
//            TODO: replace with actual profpic
                Image image = profpic.getImage();
                Image newImg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
                profpic = new ImageIcon(newImg);
                JLabel _profPic = new JLabel(profpic);
                friendpanel.add(_profPic);
                friendpanel.setBackground(Color.red);
                JLabel name = new JLabel(friend);
                name.setName("Username");
                friendpanel.add(name);
                friendpanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

                friendpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                friendsPanel.add(friendpanel);

                friendpanel.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        createChatController.execute(friend);
                        inChatPrivateController.execute(friend);
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
            }

        }

        JScrollPane scrollPane = new JScrollPane(friendsPanel);
        this.add(friendsPanel, BorderLayout.WEST);


        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

//        friendframe.pack();
//        friendframe.setVisible(true);
//        friendframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setBlockCheckboxesVisibility(HashMap<String, JPanel> panelList, JPanel buttons) {
        for (String friendUsername: panelList.keySet()) {
            JPanel friendPanel = panelList.get(friendUsername);
            if (friendPanel.getName() == null) {
                JCheckBox blockcheckbox = new JCheckBox("");
                blockcheckbox.setName("block");
                friendPanel.add(blockcheckbox);
                friendPanel.setName("blocking");

            }
            else if (friendPanel.getName().equals("blocking")) {
                for (Component component: friendPanel.getComponents()) {
                    if (component.getName() == "block") {
                        friendPanel.remove(component);
                    }
                }
                friendPanel.setName(null);
            }
            friendPanel.revalidate();
            friendPanel.repaint();
        }

        if (buttons.getName() == null) {
            JButton block = new JButton("Block");
            block.setName("blockButton");
            buttons.setName("blocking");
            buttons.add(block);
            block.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ArrayList<String> selectedFriends = new ArrayList<>();
                            for (String friendUsername: panelList.keySet()) {
                                JPanel friendPanel = panelList.get(friendUsername);
                                for (Component component: friendPanel.getComponents()) {
                                    if (component instanceof JCheckBox) {
                                        if (((JCheckBox) component).isSelected()) {
                                            for (Component _component: friendPanel.getComponents()) {
                                                if (_component.getName() == "Username") {
                                                    selectedFriends.add(((JLabel) _component).getText());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
//                            setBlockCheckboxesVisibility(friendsListPanels, );
                            blockContactController.execute(selectedFriends);
                            setBlockCheckboxesVisibility(panelList, buttons);
                        }
                    }
            );
        }
        else if (buttons.getName().equals("blocking")) {
            for (Component component: buttons.getComponents()) {
                if (component.getName() == "blockButton") {
                    buttons.remove(component);
                }
            }
            buttons.setName(null);
        }
        buttons.revalidate();
        buttons.repaint();

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        FriendsListState newState = (FriendsListState) evt.getNewValue();
        JPanel friendsPanel = new JPanel();
        JPanel buttons = new JPanel();
        for (Component component :
                this.getComponents()) {
            if (component.getName() != null && component.getName().equals("Friends Panel")) {
                friendsPanel = (JPanel) component;
            }
            if (component.getName() != null && component.getName().equals("Header")){
                for (Component subComponent :
                        ((JPanel) component).getComponents()) {
                    if(subComponent.getName() != null && subComponent.getName().equals("blocking")){
                        buttons = (JPanel) subComponent;
                    }
                }
            }
        }
        for (String friend : newState.getFriendsList()) {
            if (!this.friendsListPanels.containsKey(friend)) {
                JPanel newFriendPanel = new JPanel();
                JLabel newFriendName = new JLabel(friend);
                newFriendPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
                newFriendPanel.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        createChatController.execute(friend);
                        inChatPrivateController.execute(friend);
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
                newFriendName.setName("Username");
                ImageIcon profPic = new ImageIcon("src/view/Photos/GenericPP.jpg");
//            TODO: replace with actual profpic
                Image image = profPic.getImage();
                Image newImg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
                profPic = new ImageIcon(newImg);
                JLabel _profPic = new JLabel(profPic);
                newFriendPanel.add(_profPic);
                newFriendPanel.add(newFriendName);
                friendsPanel.add(newFriendPanel);
                this.friendsListPanels.put(friend, newFriendPanel);
            }
        }
        ArrayList<String> friendsListUsername = new ArrayList<>(this.friendsListPanels.keySet());
        ArrayList<String> newFriendsListUsername = newState.getFriendsList();
        for (String friendUsername :
                friendsListUsername) {
            if(!newFriendsListUsername.contains(friendUsername)){
                for (Component component :
                        friendsPanel.getComponents()) {
                    for (Component subComponent :
                            ((JPanel) component).getComponents()) {
                        if (subComponent instanceof JLabel && ((JLabel) subComponent).getText() != null && ((JLabel) subComponent).getText().equals(friendUsername)) friendsPanel.remove(component);
                    }
                }
                this.friendsListPanels.remove(friendUsername);
            }
        }
        friendsPanel.revalidate();
        friendsPanel.repaint();

        buttons.revalidate();
        buttons.repaint();
    }
}
