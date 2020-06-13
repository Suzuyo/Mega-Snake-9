package com.suzuyo;

import com.suzuyo.gui.GameComponent;
import com.suzuyo.gui.Window;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class WindowController {
    private static WindowController INSTANCE;
    private final Window window;
    private final GameComponent gameComponent;

    private WindowController() {
        this.gameComponent = new GameComponent();
        window = new Window(gameComponent);
    }

    public void showMenuPanel() {
        showPanel("menu");
        gameComponent.getMenuComponent().beginShow();
    }

    public void hideMenuPanel() {
        gameComponent.getMenuComponent().hide();
    }

    public void showPlayPanel() {
        showPanel("play");
        gameComponent.getPlayComponent().beginShow();
    }

    public void hidePlayPanel() {
        gameComponent.getPlayComponent().hide();
    }

    public void showFinishGame() {
        showPanel("finishGame");
        gameComponent.getFinishGameComponent().beginShow();
    }

    public void hideFinishGame() {
        gameComponent.getFinishGameComponent().hide();
    }

    private void showPanel(String name) {
        JPanel rootPanel = gameComponent.getRootPanel();
        CardLayout cardLayout = (CardLayout) rootPanel.getLayout();
        cardLayout.show(rootPanel, name);
    }

    public static synchronized WindowController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WindowController();
        }
        return INSTANCE;
    }
}
