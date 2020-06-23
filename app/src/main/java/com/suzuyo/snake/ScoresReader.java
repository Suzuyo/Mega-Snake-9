package com.suzuyo.snake;

import android.content.Context;
import android.content.SharedPreferences;
import com.suzuyo.snake.actors.score.Score;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScoresReader {
    private static ScoresReader INSTANCE;

    public List<Score> read(Context context) {
        List<Score> scores = new LinkedList<>();
        SharedPreferences sharedPreferences = context.getSharedPreferences("scores", MODE_PRIVATE);
        for (int i = 1; sharedPreferences.getInt("scores.snakeLength." + i, -1) != -1; i++) {
            int snakeLength = sharedPreferences.getInt("scores.snakeLength." + i, -1);
            String time = sharedPreferences.getString("scores.time." + i, "00:00:00");
            scores.add(new Score(snakeLength, time));
        }
        return scores;
    }

    public static ScoresReader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ScoresReader();
        }
        return INSTANCE;
    }
}
