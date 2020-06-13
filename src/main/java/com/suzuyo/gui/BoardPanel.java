package com.suzuyo.gui;

import com.suzuyo.*;
import com.suzuyo.actors.apple.AppleService;
import com.suzuyo.actors.snake.SnakeService;
import com.suzuyo.actors.tunnel.TunnelService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BoardPanel extends JPanel implements KeyListener {
    public static int SIZE = 480;
    public static int FIELD_SIZE = 20;
    public static int FIELD_COUNT = SIZE / FIELD_SIZE;
    private final GameMode gameMode;

    public BoardPanel() {
        this.gameMode = GameMode.getInstance();
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addKeyListener(this);
    }

    public void play() {
        requestFocus();
        gameMode.beginPlay();
        new Thread(() -> {
            while (GameState.getInstance().getState() == GameState.State.DURING) {
                try {
                    Thread.sleep(GameMode.getInstance().getSpeed());
                    repaint();
                    gameMode.tick();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        GameMode gameMode = GameMode.getInstance();
        if (gameMode.getApple() != null) {
            AppleService.getInstance().paint(g2d, gameMode.getApple());
        }
        if (gameMode.getTunnel() != null) {
            TunnelService.getInstance().paint(g2d, gameMode.getTunnel());
        }
        if (gameMode.getSnake() != null) {
            SnakeService.getInstance().paint(g2d, gameMode.getSnake());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            gameMode.pressRight();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            gameMode.pressLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            gameMode.pressUp();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            gameMode.pressDown();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
