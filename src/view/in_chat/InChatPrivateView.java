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
        JPanel friend = new JPanel();
        friend.setLayout(new BoxLayout(friend, BoxLayout.X_AXIS));

        JLabel
    }
}
