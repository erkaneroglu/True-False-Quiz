package com.example.truefalse.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.truefalse.Modal.Item;
import com.example.truefalse.Questions.Question;
import com.example.truefalse.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.Collections;

import es.dmoral.toasty.Toasty;

public class GameActivity extends AppCompatActivity {
    TextView tvQuestion, tvScore, tvTime;
    Button btnFalse, btnTrue;
    final Context context = this;
    final Context context2 = this;
    Question mQuestion;
    int questionsLength;
    CountDownTimer countDownTimer;
    ArrayList<Item> questionList;

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    int currentQuestion = 0;
    boolean winner = false;

    int score = 0;
    ImageView ivPass, ivHint, ivQuestionImage;
    String hint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.activity_game);
        init();
        startTimer();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.admob_interstatitial_ad_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mQuestion = new Question();
        questionsLength = mQuestion.questions.length;
        questionList = new ArrayList<>();

        for (int i = 0; i < questionsLength; i++) {
            questionList.add(new Item(mQuestion.getQuestionList(i), mQuestion.getAnswerList(i), mQuestion.getHintList(i), mQuestion.getImageList(i)));
        }

        Collections.shuffle(questionList);

        setObjects(currentQuestion);

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkQuestion(currentQuestion)) {
                    currentQuestion++;
                    score = score + 10;
                    countDownTimer.cancel();

                    if (currentQuestion < questionsLength) {
                        setObjects(currentQuestion);
                        countDownTimer.start();
                        updateScore(score);
                    } else {
                        winner = true;
                        Toasty.success(getApplicationContext(), "Congratulations! You answered all the questions!", Toast.LENGTH_LONG, true).show();
                        endGame();
                    }
                } else {
                    endGame();
                }
            }
        });

        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkQuestion(currentQuestion)) {
                    currentQuestion++;
                    score = score + 10;
                    countDownTimer.cancel();

                    if (currentQuestion < questionsLength) {
                        setObjects(currentQuestion);
                        countDownTimer.start();
                        updateScore(score);
                    } else {
                        winner = true;
                        Toasty.success(getApplicationContext(), "Congratulations! You answered all the questions!", Toast.LENGTH_LONG, true).show();
                        endGame();
                    }
                } else {
                    endGame();
                }
            }
        });

        ivHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHintDialog();
            }
        });

        ivPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPassDialog();
            }
        });
    }

    private void showHintDialog() {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.hint_dialog);

        Button btnYes = (Button) dialog.findViewById(R.id.HbtnYes);
        Button btnNo = (Button) dialog.findViewById(R.id.HbtnNo);
        dialog.setCancelable(true);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (score >= 5) {
                    score = score - 5;
                    updateScore(score);
                    Toasty.success(GameActivity.this, "" + hint, Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                } else {
                    Toasty.error(getApplicationContext(), "You don't have enough scores!", Toast.LENGTH_LONG, true).show();
                    dialog.cancel();
                }
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showPassDialog() {
        final Dialog dialog2 = new Dialog(context2);
        dialog2.setContentView(R.layout.pass_dialog);

        Button btnYes = (Button) dialog2.findViewById(R.id.TbtnYes);
        Button btnNo = (Button) dialog2.findViewById(R.id.TbtnNo);
        dialog2.setCancelable(true);
        dialog2.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (score >= 10) {
                    currentQuestion++;
                    score = score - 10;
                    updateScore(score);
                    if (currentQuestion < questionsLength) {
                        setObjects(currentQuestion);
                        countDownTimer.start();
                        updateScore(score);
                    } else {
                        winner = true;
                        Toasty.success(getApplicationContext(), "Congratulations! You answered all the questions!", Toast.LENGTH_LONG, true).show();
                        endGame();
                    }
                    dialog2.cancel();
                } else {
                    Toasty.error(getApplicationContext(), "You don't have enough scores!", Toast.LENGTH_LONG, true).show();
                    dialog2.cancel();
                }

            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });
        dialog2.show();
    }

    private void setObjects(int number) {
        tvQuestion.setText(questionList.get(number).getQuestion());
        ivQuestionImage.setImageResource(questionList.get(number).getImages());
        hint = questionList.get(number).getHint();
    }

    private boolean checkQuestion(int number) {
        String answer = questionList.get(number).getAnswer();
        return answer.equals("true");
    }

    @Override
    public void onBackPressed() {
        finish();
        countDownTimer.cancel();
    }

    private void endGame() {
        Intent i = new Intent(GameActivity.this, ResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("finalScore", score);
        i.putExtras(bundle);
        GameActivity.this.finish();
        startActivity(i);
        countDownTimer.cancel();
        showAd();
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(11000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTime.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                endGame();
            }
        }.start();
    }

    private void init() {
        tvQuestion = findViewById(R.id.tvQuestion);
        btnFalse = findViewById(R.id.btnFalse);
        btnTrue = findViewById(R.id.btnTrue);
        tvScore = findViewById(R.id.tvScore);
        ivPass = findViewById(R.id.ivPass);
        ivHint = findViewById(R.id.ivHint);
        ivQuestionImage = findViewById(R.id.ivQuestionImage);
        tvTime = findViewById(R.id.tvTime);
    }

    public void showAd() {
        mInterstitialAd.show();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
    }

    private void updateScore(int score) {
        tvScore.setText("" + score);
    }
}
