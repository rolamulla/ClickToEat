package com.clickandeat.finalproject5;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.clickandeat.finalproject5.Adapter.myCartAdapter;
import com.clickandeat.finalproject5.Model.myCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.clickandeat.finalproject5.Model.myCartModel;

import java.io.Serializable;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {
    ImageView backImg, backImg1;

    Button buttonAddToCart, buyNow;

    FirebaseFirestore db;
    FirebaseAuth auth;

    RecyclerView recyclerView;
    myCartAdapter my_Cart_Adapter;
    List<myCartModel> cartModelList = new ArrayList<>();

    TextView overTotalAmount;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        backImg = findViewById(R.id.backImg);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cart.super.onBackPressed();
            }
        });
        backImg1 = findViewById(R.id.backImg1);
        backImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cart.super.onBackPressed();
            }
        });


        buttonAddToCart = findViewById(R.id.buttonAddToCart);
        buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Cart.this, yarkaHome.class));
            }
        });

        //db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        buyNow = findViewById(R.id.buy_now);

//        progressBar = findViewById(R.id.constraint1);
//        progressBar.setVisibility(View.VISIBLE);
//
//        recyclerView.setVisibility(View.GONE);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView myCartRecyclerView = findViewById(R.id.recyclerview);

        myCartRecyclerView.setLayoutManager(linearLayoutManager);
        my_Cart_Adapter = new myCartAdapter(this, cartModelList);
        myCartRecyclerView.setAdapter(my_Cart_Adapter);

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(".", "_");

        FirebaseFirestore data = FirebaseFirestore.getInstance();

        overTotalAmount = findViewById(R.id.totalTxt);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("MyTotalAmount"));

        data.collection("User").document(auth.getCurrentUser().getUid())
                .collection("addToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                try {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        String documentId = documentSnapshot.getId();
                        myCartModel item = documentSnapshot.toObject(myCartModel.class);

                        item.setDocumentId(documentId);

                        cartModelList.add(item);
                        Log.w(" Hi ", item.toString());
                    }
                    my_Cart_Adapter.notifyDataSetChanged();
//                    progressBar.setVisibility(View.GONE);
//                    recyclerView.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        });

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, OrderActivity.class);
                intent.putExtra("itemList", (Serializable) cartModelList);
                startActivity(intent);
            }
        });


    }


    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int totalBill = intent.getIntExtra("totalAmount", 0);
            overTotalAmount.setText(totalBill + "₪");


        }
    };
}
