package view.contacts_list;

import interface_adapter.friendslist.FriendsListController;
import interface_adapter.friendslist.FriendsListViewModel;

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
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel friends = new JLabel("Friends");
        header.add(friends);

        ImageIcon addfriend = new ImageIcon("src/Photos/Plus.png");
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
        header.add(addicon, BorderLayout.EAST);

        ImageIcon block = new ImageIcon("src/Photos/Cross.png");
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
        header.add(blockicon, BorderLayout.EAST);

        ImageIcon back = new ImageIcon("src/Photos/Return.png");
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
        header.add(backicon, BorderLayout.EAST);

        friendframe.add(header);

        for() {
//      TODO: implement the actual for loop
            JPanel friendpanel = new JPanel();
            friendpanel.setLayout(new BoxLayout(friendpanel, BoxLayout.X_AXIS));

            ImageIcon profpic = new ImageIcon("src/View/Photos/GenericPP.jpg");
//          TODO: change profpic filename to the actual profpic file name
            Image image = profpic.getImage();
            Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
            profpic = new ImageIcon(newimg);
            JLabel _profpic = new JLabel(profpic);
            friendpanel.add(_profpic);

            JLabel name = new JLabel("Username");
//          TODO: change username to the actual Username of the friend
            friendpanel.add(name);
        }

        friendframe.pack();
        friendframe.setVisible(true);
        friendframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
