package com.suzuyo.snake;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.suzuyo.snake.actors.score.Score;
import com.suzuyo.snake.utils.DurationUtils;

import java.util.*;

public class PlayActivity extends AppCompatActivity {
    private static final GameState GAME_STATE = GameState.getInstance();
    private static final ScoresReader SCORES_READER = ScoresReader.getInstance();
    private static final ScoresWriter SCORES_WRITER = ScoresWriter.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        BoardView boardView = findViewById(R.id.boardView);
        boardView.setOnFinishListener(this::finishGame);
        ViewTreeObserver viewTreeObserver = boardView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    boardView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    boardView.beginPlay();
                    updateData();
                    PlayTimer playTimer = new PlayTimer();
                    playTimer.schedule(0, 150, () -> {
                        if (GAME_STATE.getState() != GameState.State.DURING) {
                            playTimer.cancel();
                        } else {
                            findViewById(R.id.boardView).invalidate();
                            boardView.tick();
                            updateData();
                        }
                    });
                }
            });
        }
    }

    public void finishGame() {
        startActivity(new Intent(this, FinishGameActivity.class));
        List<Score> scores = SCORES_READER.read(getBaseContext());
        scores.add(new Score(GAME_STATE.getSnakeLength(), GAME_STATE.getDurationTime()));
        scores.sort(Comparator.comparingInt(Score::getSnakeLength));
        int start = scores.size() > 3 ? scores.size() - 3 : 0;
        List<Score> result = scores.subList(start, scores.size());
        SCORES_WRITER.write(getBaseContext(), result);
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return findViewById(R.id.boardView).onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return findViewById(R.id.boardView).onKeyDown(keyCode, event);
    }

    private void updateData() {
        Date start = GAME_STATE.getBeginPlayTime();
        Date end = new Date();
        long allSeconds = DurationUtils.between(start, end);
        String value = DurationUtils.toString(allSeconds);
        TextView timeView = findViewById(R.id.timeView);
        timeView.setText(getString(R.string.play_time, value));
        TextView snakeLengthView = findViewById(R.id.snakeLengthView);
        snakeLengthView.setText(getString(R.string.play_snake_length, GAME_STATE.getSnakeLength()));
    }
}
