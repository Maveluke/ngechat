package view.in_chat;

import interface_adapter.in_chat.InChatPrivateController;
import interface_adapter.in_chat.InChatPrivateViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InChatPrivateView {
    private final InChatPrivateController inChatPrivateController;
    private final InChatPrivateViewModel inChatPrivateViewModel;
    InChatPrivateView(InChatPrivateController controller, InChatPrivateViewModel inChatPrivateViewModel) {
        this.inChatPrivateController = controller;
        this.inChatPrivateViewModel = inChatPrivateViewModel;

        JFrame inchatframe = new JFrame("ngechat");
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel friend  = new JLabel("Name");
//      TODO: change Name to actual username of the friend
        header.add(friend);

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

        inchatframe.add(header);

        inchatframe.pack();
        inchatframe.setVisible(true);
        inchatframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
