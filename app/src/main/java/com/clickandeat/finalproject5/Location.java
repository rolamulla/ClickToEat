package com.clickandeat.finalproject5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.clickandeat.finalproject5.Helper.locationHelper;

public class Location extends AppCompatActivity {
    private ConstraintLayout daliaBtn, yarkaBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);


        daliaBtn=findViewById(R.id.daliaBtn);
        daliaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationHelper.getInstance().setLocationToDalia(Location.this);
                startActivity(new Intent(Location.this, daliaHome.class));
            }
        });

        yarkaBtn=findViewById(R.id.yarkaBtn);
        yarkaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationHelper.getInstance().setLocationToYarka(Location.this);
//                String location = locationHelper.getInstance().getLocation(Location.this);
                startActivity(new Intent(Location.this, yarkaHome.class));
            }
        });
    }
}