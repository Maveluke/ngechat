package view;

import interface_adapter.chat_list.ChatListController;
import interface_adapter.chat_list.ChatListViewModel;
import interface_adapter.friends_list.FriendsListController;
import interface_adapter.in_chat.InChatPrivateController;
import interface_adapter.in_chat.InChatPrivateViewModel;

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

public class ChatListView extends JPanel implements PropertyChangeListener {

    public final String viewName = "chat list";
    private final ChatListController chatListController;
    private final ChatListViewModel chatListViewModel;
    private final FriendsListController friendsListController;

    private final InChatPrivateController inChatPrivateController;

    private final InChatPrivateViewModel inChatPrivateViewModel;
    private ArrayList<Component> mainComponents = new ArrayList<>();
    private ArrayList<Component> chatPanels = new ArrayList<>();

    public ChatListView(ChatListController controller, ChatListViewModel chatlistViewModel, FriendsListController friendsListController
            , InChatPrivateController inChatPrivateController, InChatPrivateViewModel inChatPrivateViewModel) {

        this.chatListController = controller;
        this.chatListViewModel = chatlistViewModel;
        this.friendsListController = friendsListController;
        this.inChatPrivateController = inChatPrivateController;
        this.inChatPrivateViewModel = inChatPrivateViewModel;


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel header = new JPanel();
        header.setName("Header");
        header.setBackground(Color.gray);
        header.setLayout(new BorderLayout());
        header.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        this.chatListViewModel.addPropertyChangeListener(this);
        ImageIcon profpic = new ImageIcon("src/View/Photos/GenericPP.jpg");
//      TODO: change profpic filename to the actual profpic file name
        Image image = profpic.getImage();
        Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
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

        JLabel username = new JLabel(chatlistViewModel.getState().getUsername());
        username.setName("Username");
        this.mainComponents.add(username);
        header.add(username, BorderLayout.CENTER);

        JPanel buttons = new JPanel();

        ImageIcon addchat = new ImageIcon("src/view/Photos/Plus.png");
        Image _add = addchat.getImage();
        Image newadd = _add.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        addchat = new ImageIcon(newadd);
        JLabel addicon = new JLabel(addchat);
        addicon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                friendsListController.execute();
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
        buttons.setBackground(Color.gray);
        ImageIcon delete = new ImageIcon("src/view/Photos/Delete.png");
        Image _delete = delete.getImage();
        Image newdelete = _delete.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
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

        this.add(header);

        HashMap<String, ArrayList<String>> chatlist = chatlistViewModel.getState().getChatList();
        for (String person : chatlist.keySet()) {
            JPanel chatpanel = new JPanel();
            chatpanel.setLayout(new BoxLayout(chatpanel, BoxLayout.Y_AXIS));

            chatpanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
            ImageIcon pic = new ImageIcon("src/View/Photos/GenericPP2.jpg");
//          TODO: change profpic filename to the actual profpic file name
            Image _image = pic.getImage();
            Image _newimg = _image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
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
            chatpanel.setName(person);
            this.chatPanels.add(chatpanel);
            this.add(chatpanel);

            chatpanel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    inChatPrivateController.execute(person, chatListViewModel.getState().getUsername());
                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    chatpanel.setBackground(Color.lightGray);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    chatpanel.setBackground(Color.gray);
                }
            });
        }

//        chatframe.pack();
//        chatframe.setVisible(true);
//        chatframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        header.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
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


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        HashMap<String, ArrayList<String>> chatlist = chatListViewModel.getState().getChatList();
        // change the username in the first component in this.mainComponents
        ((JLabel) this.mainComponents.get(0)).setText(chatListViewModel.getState().getUsername());
        for (String person : chatlist.keySet()) {
            // check and find the panel with the same name as person
            boolean found = false;
            for (Component component : this.chatPanels) {
                if (component.getName().equals(person)) {
                    found = true;
                    JPanel chatpanel = (JPanel) component;
                    // find the panel with name and message
                    JPanel _chatpanel = (JPanel) chatpanel.getComponent(1);
                    JLabel name = (JLabel) _chatpanel.getComponent(0);
                    JLabel message = (JLabel) _chatpanel.getComponent(1);
                    name.setText(person);
                    message.setText(chatlist.get(person).get(2) + ": " +
                            chatlist.get(person).get(0) + " (" +
                            chatlist.get(person).get(1) + ")");
                    chatpanel.revalidate();
                    chatpanel.repaint();
                    break;
                }
            }
            if (!found) {
                JPanel chatpanel = new JPanel();
                chatpanel.setName(person);
                chatpanel.setBackground(Color.GRAY);
                chatpanel.setLayout(new BoxLayout(chatpanel, BoxLayout.X_AXIS));
                this.chatPanels.add(chatpanel);
                ImageIcon pic = new ImageIcon("src/View/Photos/GenericPP2.jpg");
//          TODO: change profpic filename to the actual profpic file name
                Image _image = pic.getImage();
                Image _newimg = _image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                pic = new ImageIcon(_newimg);
                JLabel _pic = new JLabel(pic);
                chatpanel.add(_pic);

                JPanel _chatpanel = new JPanel();
                _chatpanel.setLayout(new BoxLayout(_chatpanel, BoxLayout.Y_AXIS));

                JLabel name = new JLabel(person);
                name.setAlignmentX(Component.LEFT_ALIGNMENT);
                _chatpanel.add(name);

                JLabel message = new JLabel(chatlist.get(person).get(2) + ": " +
                        chatlist.get(person).get(0) + " (" +
                        chatlist.get(person).get(1) + ")");
                message.setAlignmentX(Component.LEFT_ALIGNMENT);
                _chatpanel.add(message);

                chatpanel.add(_chatpanel);
                this.add(chatpanel);

                chatpanel.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        inChatPrivateController.execute(person, chatListViewModel.getState().getUsername());
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        chatpanel.setBackground(Color.lightGray);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        chatpanel.setBackground(Color.gray);
                    }
                });
                chatpanel.revalidate();
                chatpanel.repaint();
                this.revalidate();
                this.repaint();
            }
        }
    }
}
