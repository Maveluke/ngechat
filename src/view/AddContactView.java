package view;

import interface_adapter.add_contact.AddContactController;
import interface_adapter.add_contact.AddContactState;
import interface_adapter.add_contact.AddContactViewModel;
import interface_adapter.login.LoginState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AddContactView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "add contact";

    private final AddContactViewModel addContactViewModel;

    final JTextField usernameInputField = new JTextField(15);

    private final JLabel usernameErrorField = new JLabel();

    final JButton add;
    private final AddContactController addContactController;
    public AddContactView(AddContactViewModel addContactViewModel, AddContactController addContactController){
        this.addContactViewModel = addContactViewModel;
        this.addContactController = addContactController;
        this.addContactViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(AddContactViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(AddContactViewModel.USERNAME_LABEL), usernameInputField);

        JPanel buttons = new JPanel();
        add = new JButton(AddContactViewModel.ADD_BUTTON_LABEL);
        buttons.add(add);

        usernameInputField.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(add)) {
                            AddContactState currentState = addContactViewModel.getState();

                            addContactController.execute(
                                    currentState.getUsername()
                            );
                        }
                    }
                }
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
