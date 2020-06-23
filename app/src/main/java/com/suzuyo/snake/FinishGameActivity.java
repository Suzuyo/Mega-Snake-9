package com.suzuyo.snake;

import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class FinishGameActivity extends AppCompatActivity {
    private static final GameState GAME_STATE = GameState.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_game);
        String durationText = GAME_STATE.getDurationTime();
        TextView timeValueView = findViewById(R.id.timeValueView);
        timeValueView.setText(durationText);
        int snakeLength = GAME_STATE.getSnakeLength();
        TextView snakeLengthValueView = findViewById(R.id.snakeLengthValueView);
        snakeLengthValueView.setText(String.valueOf(snakeLength));
        Button menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(view -> finish());
    }
}
