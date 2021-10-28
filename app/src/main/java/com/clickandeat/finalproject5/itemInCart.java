package com.clickandeat.finalproject5;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class itemInCart extends AppCompatActivity {

    ImageView imageView;
    TextView itemName;
    TextView itemPrice ,totalPrice1;
    String name, imageViewS;
    int thePrice;
    TextView quantity;
    int totalQuantity1 = 1, totalPrice = 0 ,price;
    ImageView addItem1, removeItem1;


    private FirebaseFirestore firestore;
    FirebaseAuth auth;


    private Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_in_cart);

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        price = Integer.parseInt(intent.getStringExtra("price"));
        imageViewS = intent.getStringExtra("imageViewS");

        imageView = findViewById(R.id.imageView);
        itemName = findViewById(R.id.name);
        itemPrice = findViewById(R.id.price);

        totalPrice1 = findViewById(R.id.totalPrice1);
        quantity = findViewById(R.id.quntity);
        addItem1 = findViewById(R.id.plusCardBtn1);
        removeItem1 = findViewById(R.id.minusCardBtn1);



        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();


        Glide.with(getApplicationContext()).load(imageViewS).into(imageView);
        itemName.setText(name);
        itemPrice.setText(price);

        totalPrice1.setText(totalPrice);

        thePrice = Integer.parseInt(itemPrice.getText().toString());

        addItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (totalQuantity1 < 10) {
                    totalQuantity1++;
                    quantity.setText(String.valueOf(totalQuantity1));
                    totalPrice = thePrice * totalQuantity1;
                }
            }
        });

        removeItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (totalQuantity1 > 1) {
                    thePrice = Integer.parseInt(itemPrice.getText().toString());
                    totalQuantity1--;
                    quantity.setText(String.valueOf(totalQuantity1));
                    totalPrice = thePrice * totalQuantity1;

                }

            }
        });



    }
}