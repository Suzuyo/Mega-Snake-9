package com.suzuyo.snake;

import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class FullscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        Button playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(view -> startActivity(new Intent(this, PlayActivity.class)));
        Button scoresButton = findViewById(R.id.scores_button);
        scoresButton.setOnClickListener(view -> startActivity(new Intent(this, ScoresActivity.class)));
        Button exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(view -> finish());
    }
}
