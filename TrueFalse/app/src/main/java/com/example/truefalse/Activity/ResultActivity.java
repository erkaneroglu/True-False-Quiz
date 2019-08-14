package com.example.truefalse.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.truefalse.R;

public class ResultActivity extends AppCompatActivity {
    TextView mFinalScore, mHighScore;
    Button mRetryButton, mShareButton;
    int score, highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.activity_result);
        init();

        mRetryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this, GameActivity.class));
                ResultActivity.this.finish();
            }
        });

        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "My Score!");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hello! My True False game score is : " + score);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
    }

    private void init() {
        mFinalScore = (TextView) findViewById(R.id.outOf);
        mRetryButton = (Button) findViewById(R.id.retry);
        mShareButton = (Button) findViewById(R.id.resultBtnShare);
        mHighScore = (TextView) findViewById(R.id.highScore);

        Bundle bundle = getIntent().getExtras();
        score = bundle.getInt("finalScore");
        mFinalScore.setText("" + score);

        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        highScore = prefs.getInt("score", 0);

        if (highScore > score) {
            mHighScore.setText(Integer.toString(highScore));
        } else {
            highScore = score;
            mHighScore.setText(Integer.toString(highScore));
            prefs.edit().putInt("score", highScore).apply();
        }
    }
}
