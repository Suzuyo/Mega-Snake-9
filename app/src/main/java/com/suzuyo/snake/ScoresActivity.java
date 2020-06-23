package com.suzuyo.snake;

import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.suzuyo.snake.actors.score.Score;

import java.util.Collections;
import java.util.List;

public class ScoresActivity extends AppCompatActivity {
    private static final ScoresReader SCORES_READER = ScoresReader.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        Button menuButton = findViewById(R.id.menuButton2);
        menuButton.setOnClickListener(view -> finish());
        List<Score> scores = SCORES_READER.read(getBaseContext());
        Collections.reverse(scores);
        if (scores.size() > 0) {
            Score score = scores.get(0);
            TextView firstSnakeLength = findViewById(R.id.firstSnakeLength);
            firstSnakeLength.setText(String.valueOf(score.getSnakeLength()));
            TextView firstScore = findViewById(R.id.firstTime);
            firstScore.setText(score.getTime());
        }
        if (scores.size() > 1) {
            Score score = scores.get(1);
            TextView secondSnakeLength = findViewById(R.id.secondSnakeLength);
            secondSnakeLength.setText(String.valueOf(score.getSnakeLength()));
            TextView secondScore = findViewById(R.id.secondTime);
            secondScore.setText(score.getTime());
        }
        if (scores.size() > 2) {
            Score score = scores.get(2);
            TextView thirdSnakeLength = findViewById(R.id.thirdSnakeLength);
            thirdSnakeLength.setText(String.valueOf(score.getSnakeLength()));
            TextView thirdScore = findViewById(R.id.thirdTime);
            thirdScore.setText(score.getTime());
        }
    }
}
