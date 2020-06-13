package com.suzuyo.gui;

import com.suzuyo.GameMode;
import com.suzuyo.GameState;
import com.suzuyo.WindowController;
import com.suzuyo.utils.DurationUtils;
import lombok.Getter;

import javax.swing.*;
import java.time.Duration;
import java.time.LocalDateTime;

public class FinishGameComponent implements PanelEvent {
    @Getter private JPanel rootPanel;
    private JButton menuButton;
    private JLabel timeField;
    private JLabel snakeLengthField;
    private JLabel scoreField;

    public FinishGameComponent() {
        addMenuButtonListener();
    }

    @Override
    public void beginShow() {
        GameMode gameMode = GameMode.getInstance();
        LocalDateTime beginPlayTime = gameMode.getBeginPlayTime();
        LocalDateTime endPlayTime = gameMode.getEndPlayTime();
        Duration duration = DurationUtils.between(beginPlayTime, endPlayTime);
        String durationText = DurationUtils.toString(duration);
        timeField.setText(durationText);
        int snakeLength = gameMode.getSnake().getSnakeElements().size();
        snakeLengthField.setText(String.valueOf(snakeLength));
        scoreField.setText(String.valueOf(GameState.getInstance().getScore()));
    }

    @Override
    public void hide() {
    }

    private void addMenuButtonListener() {
        menuButton.addActionListener(event -> clickMenuButton());
    }

    public void clickMenuButton() {
        WindowController.getInstance().hideFinishGame();
        WindowController.getInstance().showMenuPanel();
    }
}
