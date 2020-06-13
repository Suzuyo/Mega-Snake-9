package com.suzuyo.gui;

import com.suzuyo.GameMode;
import com.suzuyo.GameState;
import com.suzuyo.utils.DurationUtils;
import lombok.Getter;

import javax.swing.*;
import java.time.Duration;
import java.time.LocalDateTime;

public class PlayComponent implements PanelEvent {
    @Getter private JPanel rootPanel;
    private JPanel boardPanel;
    private JLabel timeField;
    private JLabel snakeLengthField;
    private JLabel scoreField;

    private void createUIComponents() {
        rootPanel = new PlayPanel();
        boardPanel = new BoardPanel();
    }

    @Override
    public void beginShow() {
        GameState.getInstance().setState(GameState.State.DURING);
        ((BoardPanel) boardPanel).play();
        tick();
    }

    @Override
    public void hide() {
    }

    private void tick() {
        new Thread(() -> {
            while (GameState.getInstance().getState() == GameState.State.DURING) {
                try {
                    Thread.sleep(200);
                    LocalDateTime start = GameMode.getInstance().getBeginPlayTime();
                    LocalDateTime end = LocalDateTime.now();
                    Duration duration = DurationUtils.between(start, end);
                    String value = DurationUtils.toString(duration);
                    timeField.setText("Time : " + value);
                    snakeLengthField.setText("Snake length : " + GameMode.getInstance().getSnake().getSnakeElements().size());
                    scoreField.setText("Score : " + GameState.getInstance().getScore());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
