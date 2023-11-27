package view.contacts_list;

import interface_adapter.friends_list.FriendsListController;
import interface_adapter.friends_list.FriendsListViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FriendsListView {
    private final FriendsListController friendsListController;
    private final FriendsListViewModel friendsListViewModel;
    FriendsListView(FriendsListController controller, FriendsListViewModel friendsListViewModel) {
        this.friendsListController = controller;
        this.friendsListViewModel = friendsListViewModel;

        JFrame friendframe = new JFrame("ngechat");
        friendframe.setLayout(new BoxLayout(friendframe.getContentPane(), BoxLayout.Y_AXIS));
        JPanel header = new JPanel();
        header.setLayout(new BorderLayout());

        JLabel friends = new JLabel("Friends");
        header.add(friends, BorderLayout.WEST);

        JPanel buttons = new JPanel();

        ImageIcon addfriend = new ImageIcon("src/view/Photos/Plus.png");
        Image _add = addfriend.getImage();
        Image newadd = _add.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        addfriend = new ImageIcon(newadd);
        JLabel addicon = new JLabel(addfriend);
        addicon.addMouseListener(new MouseListener() {
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
        buttons.add(addicon, BorderLayout.EAST);

        ImageIcon block = new ImageIcon("src/view/Photos/Cross.png");
        Image _block = block.getImage();
        Image newblock = _block.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        block = new ImageIcon(newblock);
        JLabel blockicon = new JLabel(block);
        blockicon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//              TODO: implement block friend
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
        buttons.add(blockicon, BorderLayout.EAST);

        ImageIcon back = new ImageIcon("src/view/Photos/Return.png");
        Image _back = back.getImage();
        Image newback = _back.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        back = new ImageIcon(newback);
        JLabel backicon = new JLabel(back);
        backicon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//              TODO: implement back (go back to profile)
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
        buttons.add(backicon, BorderLayout.EAST);

        friendframe.add(header);

        header.add(buttons, BorderLayout.EAST);

        JPanel friendspanel = new JPanel();
        friendspanel.setLayout(new BoxLayout(friendspanel, BoxLayout.Y_AXIS));

//        for() {
////            TODO: implement for loop
//            JPanel friendpanel = new JPanel();
//            friendpanel.setLayout(new BoxLayout(friendpanel, BoxLayout.X_AXIS));
//
//            ImageIcon profpic = new ImageIcon("src/view/Photos/GenericPP.jpg");
////            TODO: replace with actual profpic
//            Image image = profpic.getImage();
//            Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
//            profpic = new ImageIcon(newimg);
//            JLabel _profpic = new JLabel(profpic);
//            friendpanel.add(_profpic);
//
//            JLabel name = new JLabel("Username");
////            TODO: replace with actual username
//            friendpanel.add(name);
//
//            friendpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
//            friendspanel.add(friendpanel);
//        }

        friendframe.add(friendspanel, BorderLayout.WEST);

        JScrollPane scrollpane = new JScrollPane(friendspanel);

        friendframe.add(scrollpane, BorderLayout.WEST);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        friendframe.getContentPane().add(scrollpane);

        friendframe.pack();
        friendframe.setVisible(true);
        friendframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
