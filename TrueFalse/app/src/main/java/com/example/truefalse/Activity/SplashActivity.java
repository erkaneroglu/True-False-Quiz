package com.example.truefalse.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.truefalse.R;

public class SplashActivity extends AppCompatActivity {
    ImageView ivSplash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.splash_activity);
        init();

        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.splash_anim);
        ivSplash.startAnimation(myanim);

        final Intent intent = new Intent(getApplicationContext(), StartActivity.class);
        Thread timer = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }

    public void init() {
        ivSplash = findViewById(R.id.ivSplash);
    }
}
