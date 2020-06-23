package com.suzuyo.snake;

import android.content.Context;
import android.content.SharedPreferences;
import com.suzuyo.snake.actors.score.Score;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScoresWriter {
    private static ScoresWriter INSTANCE;

    public void write(Context context, List<Score> scores) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("scores", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (int i = 0; i < scores.size(); i++) {
            Score score = scores.get(i);
            editor.putInt("scores.snakeLength." + (i + 1), score.getSnakeLength());
            editor.putString("scores.time." + (i + 1), score.getTime());
        }
        editor.apply();
    }

    public static ScoresWriter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ScoresWriter();
        }
        return INSTANCE;
    }
}
