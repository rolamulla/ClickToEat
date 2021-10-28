package com.clickandeat.finalproject5.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.clickandeat.finalproject5.R;
import com.clickandeat.finalproject5.signIn;

public class settingsActivity extends AppCompatActivity {

    Button logoutBtn, changePassBtn, feedbackBtn, locationBtn, supportBtn;
    ImageView backImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        backImg = findViewById(R.id.backImg);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsActivity.super.onBackPressed();
            }
        });

        logoutBtn=findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(settingsActivity.this, signIn.class));
            }
        });

        supportBtn=findViewById(R.id.supportBtn);
        supportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(settingsActivity.this, Support.class));
            }
        });

        changePassBtn=findViewById(R.id.changePassBtn);
        changePassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(settingsActivity.this, changePassword.class));
            }
        });


        feedbackBtn=findViewById(R.id.feedbackBtn);
        feedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(settingsActivity.this, newFeedback.class));
            }
        });

        locationBtn=findViewById(R.id.locationBtn);
        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(settingsActivity.this, locationOn.class));
            }
        });
    }
}