package view;

import entity.Chat;
import interface_adapter.chat_list.ChatListController;
import interface_adapter.chat_list.ChatListViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ChatListView {
    private final ChatListViewModel chatListViewModel;
    private final ChatListController chatListController;
    public ChatListView(ChatListController controller, ChatListViewModel chatlistViewModel){
        
        this.chatListController = controller;
        this.chatListViewModel = chatlistViewModel;
        
        JFrame chatframe = new JFrame("ngechat");
        JPanel user = new JPanel();
        user.setLayout(new BoxLayout(user, BoxLayout.X_AXIS));

        ImageIcon profpic = new ImageIcon("src/View/Photos/GenericPP.jpg");
        Image image = profpic.getImage();
        Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        profpic = new ImageIcon(newimg);
        JLabel _profpic = new JLabel(profpic);
        user.add(_profpic);

        JLabel username = new JLabel(chatlistViewModel.getState().getUsername());
        user.add(username);

        ImageIcon delete = new ImageIcon("src/Photos/Delete.png");
        Image _delete = delete.getImage();
        Image newdelete = _delete.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        delete = new ImageIcon(newdelete);
        JLabel deleteicon = new JLabel(delete);
        user.add(deleteicon, BorderLayout.EAST);

        ImageIcon addchat = new ImageIcon("src/Photos/Plus.png");
        Image _add = addchat.getImage();
        Image newadd = _add.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        addchat = new ImageIcon(newadd);
        JLabel addicon = new JLabel(addchat);
        user.add(addicon, BorderLayout.EAST);

        chatframe.add(user);

        HashMap<String, ArrayList<String>> chatlist = chatlistViewModel.getState().getChatList();
        for (String person: chatlist.keySet()) {
            JPanel chatpanel = new JPanel();
            chatpanel.setLayout(new BoxLayout(chatpanel, BoxLayout.X_AXIS));

            ImageIcon pic = new ImageIcon("src/View/Photos/GenericPP2.jpg");
            Image _image = pic.getImage();
            Image _newimg = _image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
            pic = new ImageIcon(_newimg);
            JLabel _pic = new JLabel(pic);
            chatpanel.add(_pic);

            JPanel _chatpanel = new JPanel();
            _chatpanel.setLayout(new BoxLayout(_chatpanel, BoxLayout.Y_AXIS));

            JLabel name = new JLabel(person);
            _chatpanel.add(name);

            JLabel message = new JLabel(chatlist.get(person).get(2) + ": " +
                    chatlist.get(person).get(0) + " (" +
                    chatlist.get(person).get(1) + ")");
            _chatpanel.add(message);

            chatpanel.add(_chatpanel);
            chatframe.add(chatpanel);

            chatpanel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
//              TODO: go to inchat
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

        chatframe.pack();
        chatframe.setVisible(true);
        chatframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        user.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//          TODO: go to profile
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
