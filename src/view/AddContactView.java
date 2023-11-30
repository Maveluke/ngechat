package view;

import interface_adapter.add_contact.AddContactController;
import interface_adapter.add_contact.AddContactState;
import interface_adapter.add_contact.AddContactViewModel;
import interface_adapter.friends_list.FriendsListController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AddContactView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "add contact";

    private final AddContactViewModel addContactViewModel;
    private final FriendsListController friendsListController;

    final JTextField usernameInputField = new JTextField(15);

    private final JLabel usernameErrorField = new JLabel();

    final JButton add;
    final JButton cancel;
    private final AddContactController addContactController;
    private final FriendsListController friendsListController;

    public AddContactView(AddContactViewModel addContactViewModel, FriendsListController friendsListController, AddContactController addContactController){
        this.addContactViewModel = addContactViewModel;
        this.friendsListController = friendsListController;
        this.addContactController = addContactController;
        this.addContactViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Add Contact");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(AddContactViewModel.USERNAME_LABEL), usernameInputField);
        usernameInfo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        JPanel buttons = new JPanel();
        buttons.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        add = new JButton(AddContactViewModel.ADD_BUTTON_LABEL);
        cancel = new JButton(AddContactViewModel.CANCEL_BUTTON_LABEL);

        buttons.add(add);
        buttons.add(cancel);

        add.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(add)) {
                            AddContactState currentState = addContactViewModel.getState();

                            addContactController.execute(
                                    currentState.getUsername()
                            );
                            currentState = addContactViewModel.getState();
                            if (currentState.getUsernameError() == null) friendsListController.execute();
                        }
                    }
                }
        );
        cancel.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        friendsListController.execute();
                    }
                }
        );

        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        AddContactState currentState = addContactViewModel.getState();
                        currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                        addContactViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(usernameInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        AddContactState state = (AddContactState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }
}
