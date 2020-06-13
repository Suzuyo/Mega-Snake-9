package com.suzuyo.gui;

import com.suzuyo.WindowController;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class MenuComponent implements PanelEvent {
    private JButton playButton;
    private JButton exitButton;
    @Getter private JPanel rootPanel;

    public MenuComponent() {
        addPlayButtonListener();
        addExitButtonListener();
    }

    private void createUIComponents() {
        rootPanel = new MenuPanel();
        playButton = new JButton();
        playButton.setBorder(BorderFactory.createLineBorder(new Color(23, 60, 21), 2));
        exitButton = new JButton();
        exitButton.setBorder(BorderFactory.createLineBorder(new Color(23, 60, 21), 2));
    }

    @Override
    public void beginShow() {
    }

    @Override
    public void hide() {

    }

    private void addPlayButtonListener() {
        playButton.addActionListener(event -> clickPlayButton());
    }

    public void clickPlayButton() {
        WindowController.getInstance().hideMenuPanel();
        WindowController.getInstance().showPlayPanel();
    }

    private void addExitButtonListener() {
        exitButton.addActionListener(event -> clickExitButton());
    }

    public void clickExitButton() {
        System.exit(0);
    }
}
