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
    private final ChatListController chatListController;
    private final ChatListViewModel chatListViewModel;
    ChatListView(ChatListController controller, ChatListViewModel chatlistViewModel){
        
        this.chatListController = controller;
        this.chatListViewModel = chatlistViewModel;
        
        JFrame chatframe = new JFrame("ngechat");
        chatframe.setLayout(new BoxLayout(chatframe.getContentPane(), BoxLayout.Y_AXIS));
        JPanel header = new JPanel();
        header.setLayout(new BorderLayout());

        ImageIcon profpic = new ImageIcon("src/View/Photos/GenericPP.jpg");
//      TODO: change profpic filename to the actual profpic file name
        Image image = profpic.getImage();
        Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        profpic = new ImageIcon(newimg);
        JLabel _profpic = new JLabel(profpic);
        _profpic.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                TODO: go to profile
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
        header.add(_profpic, BorderLayout.WEST);

        JLabel username = new JLabel(chatlistViewModel.getUsername());
        header.add(username, BorderLayout.CENTER);

        JPanel buttons = new JPanel();

        ImageIcon addchat = new ImageIcon("src/view/Photos/Plus.png");
        Image _add = addchat.getImage();
        Image newadd = _add.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        addchat = new ImageIcon(newadd);
        JLabel addicon = new JLabel(addchat);
        addicon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//              TODO: implement add chat
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

        ImageIcon delete = new ImageIcon("src/view/Photos/Delete.png");
        Image _delete = delete.getImage();
        Image newdelete = _delete.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        delete = new ImageIcon(newdelete);
        JLabel deleteicon = new JLabel(delete);
        deleteicon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//              TODO: implement delete chat
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
        buttons.add(deleteicon, BorderLayout.EAST);

        header.add(buttons, BorderLayout.EAST);

        chatframe.add(header);

        HashMap<String, ArrayList<String>> chatlist = chatlistViewModel.getState().getChatList();
        for (String person: chatlist.keySet()) {
            JPanel chatpanel = new JPanel();
            chatpanel.setLayout(new BoxLayout(chatpanel, BoxLayout.Y_AXIS));

            ImageIcon pic = new ImageIcon("src/View/Photos/GenericPP2.jpg");
//          TODO: change profpic filename to the actual profpic file name
            Image _image = pic.getImage();
            Image _newimg = _image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
            pic = new ImageIcon(_newimg);
            JLabel _pic = new JLabel(pic);
            chatpanel.add(_pic);

            JPanel _chatpanel = new JPanel();
            _chatpanel.setLayout(new BoxLayout(_chatpanel, BoxLayout.X_AXIS));

            JLabel name = new JLabel(person);
            name.setAlignmentX(Component.LEFT_ALIGNMENT);
            _chatpanel.add(name);

            JLabel message = new JLabel(chatlist.get(person).get(2) + ": " +
                    chatlist.get(person).get(0) + " (" +
                    chatlist.get(person).get(1) + ")");
            message.setAlignmentX(Component.LEFT_ALIGNMENT);
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

        header.addMouseListener(new MouseListener() {
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
