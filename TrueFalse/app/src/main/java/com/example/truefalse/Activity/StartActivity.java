package com.example.truefalse.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.truefalse.R;

public class StartActivity extends AppCompatActivity {
    TextView tvGec;
    ImageView ivInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.activity_start);
        init();

        tvGec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
            }
        });

        ivInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog infoDialog = new Dialog(StartActivity.this);
                infoDialog.setContentView(R.layout.info_dialog);
                Button btnRate = (Button) infoDialog.findViewById(R.id.btnRate);
                Button btnMoreApp = (Button) infoDialog.findViewById(R.id.btnMoreApp);
                Button btnShare = (Button) infoDialog.findViewById(R.id.btnShare);
                Button btnFeedback = (Button) infoDialog.findViewById(R.id.btnFeedback);
                ImageView ivClose = (ImageView) infoDialog.findViewById(R.id.ivClose);
                infoDialog.setCancelable(true);
                infoDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

                btnRate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.example.truefalse");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });

                btnMoreApp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.example.truefalse");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });

                btnShare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT,
                                "Hey check out my app at: https://play.google.com/store/apps/details?id=com.example.truefalse");
                        sendIntent.setType("text/plain");
                        startActivity(sendIntent);
                    }
                });

                btnFeedback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto", "erogluerkan51@gmail.com", null));
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                        startActivity(Intent.createChooser(emailIntent, "Send email..."));
                    }
                });

                ivClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        infoDialog.cancel();
                    }
                });

                infoDialog.show();
            }
        });
    }

    public void init() {
        tvGec = findViewById(R.id.tvStart);
        ivInfo = findViewById(R.id.ivInfo);
    }

    @Override
    public void onBackPressed() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.exit_app_dialog);

        Button btnYes = (Button) dialog.findViewById(R.id.EbtnYes);
        Button btnNo = (Button) dialog.findViewById(R.id.EbtnNo);
        dialog.setCancelable(true);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
}
