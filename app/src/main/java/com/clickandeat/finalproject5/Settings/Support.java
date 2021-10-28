package com.clickandeat.finalproject5.Settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.clickandeat.finalproject5.R;

public class Support extends AppCompatActivity {
    EditText editTo, editSubject, editMessege;
    Button sendBtn;
    ImageView backImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        backImg = findViewById(R.id.backImg);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Support.super.onBackPressed();
            }
        });

        editTo = findViewById(R.id.editTo);
        editSubject = findViewById(R.id.editSubject);
        editMessege = findViewById(R.id.editMessege);
        sendBtn = findViewById(R.id.sendBtn);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + editTo.getText().toString()));
                intent.putExtra(Intent.EXTRA_SUBJECT, editSubject.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT, editMessege.getText().toString());
                startActivity(intent);
            }
        });
    }
}