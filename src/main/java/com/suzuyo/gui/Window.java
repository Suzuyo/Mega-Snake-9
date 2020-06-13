package com.suzuyo.gui;

import javax.swing.*;

public class Window extends JFrame {

    public Window(GameComponent gameComponent) {
        super("Snake");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(gameComponent.getRootPanel());
        pack();
        setVisible(true);
    }
}
