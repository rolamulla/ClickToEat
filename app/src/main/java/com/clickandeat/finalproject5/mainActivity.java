package com.clickandeat.finalproject5;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class mainActivity extends AppCompatActivity {
    Button clickToEatBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clickToEatBtn = findViewById(R.id.clickToEatBtn);

        clickToEatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mainActivity.this, signIn.class));
                finish();
            }
        });
    }
}