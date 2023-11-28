package view.contacts_list;

import interface_adapter.block_contact.BlockContactState;
import interface_adapter.friends_list.FriendsListController;
import interface_adapter.friends_list.FriendsListViewModel;
import interface_adapter.block_contact.BlockContactController;
import interface_adapter.block_contact.BlockContactViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

public class FriendsListView extends JPanel{

    public final String viewName = "friends list";
    private final FriendsListController friendsListController;
    private final FriendsListViewModel friendsListViewModel;
    private final BlockContactController blockContactController;
    private final BlockContactViewModel blockContactViewModel;
    public FriendsListView(FriendsListController controller, FriendsListViewModel friendsListViewModel,
                    BlockContactController blockContactController, BlockContactViewModel blockContactViewModel) {
        this.friendsListController = controller;
        this.friendsListViewModel = friendsListViewModel;
        this.blockContactController = blockContactController;
        this.blockContactViewModel = blockContactViewModel;

        HashMap<String, String> friendslist = friendsListViewModel.getFriendslist();
        ArrayList<JPanel> friendsListPanels = new ArrayList<JPanel>();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        JFrame friendframe = new JFrame("ngechat");
//        friendframe.setLayout(new BoxLayout(friendframe.getContentPane(), BoxLayout.Y_AXIS));

        JPanel header = new JPanel();
        header.setLayout(new BorderLayout());

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
//              TODO: implement add friend
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
//              TODO: implement back (go back to chatlist)
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

        this.add(header);

        header.add(buttons, BorderLayout.EAST);

        JPanel friendsPanel = new JPanel();
        friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.Y_AXIS));

        for (String friend: friendslist.keySet()) {
            JPanel friendpanel = new JPanel();
            friendpanel.setLayout(new BoxLayout(friendpanel, BoxLayout.X_AXIS));

            ImageIcon profpic = new ImageIcon("src/view/Photos/GenericPP.jpg");
//            TODO: replace with actual profpic
            Image image = profpic.getImage();
            Image newImg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
            profpic = new ImageIcon(newImg);
            JLabel _profPic = new JLabel(profpic);
            friendpanel.add(_profPic);

            JLabel name = new JLabel(friend);
            name.setName("Username");
            friendpanel.add(name);

            friendpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            friendsPanel.add(friendpanel);

            friendpanel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
//                    TODO: go to inchat
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

        this.add(friendsPanel, BorderLayout.WEST);

        JScrollPane scrollPane = new JScrollPane(friendsPanel);

        this.add(scrollPane, BorderLayout.WEST);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane);

//        friendframe.pack();
//        friendframe.setVisible(true);
//        friendframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setBlockCheckboxesVisibility(ArrayList<JPanel> panelList, JPanel buttons) {
        for (JPanel friendpanel: panelList) {
            if (friendpanel.getName() == null) {
                JCheckBox blockcheckbox = new JCheckBox("");
                blockcheckbox.setName("block");
                friendpanel.add(blockcheckbox);
                friendpanel.setName("blocking");

            }
            else if (friendpanel.getName().equals("blocking")) {
                for (Component component: friendpanel.getComponents()) {
                    if (component.getName() == "block") {
                        friendpanel.remove(component);
                    }
                }
                friendpanel.setName(null);
            }
            friendpanel.revalidate();
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
                            ArrayList<String> selectedFriends = new ArrayList<String>();
                            for (JPanel friendPanel: panelList) {
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
                            blockContactController.execute(selectedFriends);
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
    }
}
