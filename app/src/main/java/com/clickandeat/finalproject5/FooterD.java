package com.clickandeat.finalproject5;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.clickandeat.finalproject5.Settings.Account;
import com.clickandeat.finalproject5.Settings.Favorite;
import com.clickandeat.finalproject5.Settings.settingsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FooterD {

    FloatingActionButton homeBtn, profileBtn , settingsBtn ,favBtn ,cartBtn;
    private static FooterD instance = new FooterD();

    private FooterD(){}
    public static FooterD getInstance(){
        return instance;
    }
    protected void initFooter(final AppCompatActivity appCompatActivity) {

        homeBtn = appCompatActivity.findViewById(R.id.imageHome);
        profileBtn = appCompatActivity.findViewById(R.id.imageProfile);
        settingsBtn = appCompatActivity.findViewById(R.id.imageSettings);
        favBtn = appCompatActivity.findViewById(R.id.imageFavourite);
        cartBtn = appCompatActivity.findViewById(R.id.cartBtn);


        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appCompatActivity.startActivity(new Intent(appCompatActivity, daliaHome.class));
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appCompatActivity.startActivity(new Intent(appCompatActivity, Account.class));
            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appCompatActivity.startActivity(new Intent(appCompatActivity, settingsActivity.class));
            }
        });

        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appCompatActivity.startActivity(new Intent(appCompatActivity, Favorite.class));
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appCompatActivity.startActivity(new Intent(appCompatActivity, Cart.class));
            }
        });
    }
}