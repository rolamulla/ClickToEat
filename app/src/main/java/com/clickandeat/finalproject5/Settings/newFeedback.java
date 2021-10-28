package com.clickandeat.finalproject5.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.clickandeat.finalproject5.R;
import com.clickandeat.finalproject5.daliaHome;

public class newFeedback extends AppCompatActivity {
    TextView tvFeedback;
    Button sendFeedbackBtn;
    ImageView backImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_feedback);

        tvFeedback = findViewById(R.id.tvFeedback);

        backImg = findViewById(R.id.backImg);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newFeedback.super.onBackPressed();
            }
        });
    }
}