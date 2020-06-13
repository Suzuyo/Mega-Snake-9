package com.suzuyo.gui;

import lombok.Getter;

import javax.swing.*;

public class GameComponent {
    @Getter private JPanel rootPanel;
    @Getter private MenuComponent menuComponent;
    private JPanel menuPanel;
    @Getter private PlayComponent playComponent;
    private JPanel playPanel;
    @Getter private FinishGameComponent finishGameComponent;
    private JPanel finishGamePanel;

    private void createUIComponents() {
        menuComponent = new MenuComponent();
        menuPanel = menuComponent.getRootPanel();
        playComponent = new PlayComponent();
        playPanel = playComponent.getRootPanel();
        finishGameComponent = new FinishGameComponent();
        finishGamePanel = finishGameComponent.getRootPanel();
    }
}
