package com.clickandeat.finalproject5.Settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.clickandeat.finalproject5.Adapter.myCartAdapter;
import com.clickandeat.finalproject5.Adapter.myFavoriteAdapter;
import com.clickandeat.finalproject5.Model.allMenu;
import com.clickandeat.finalproject5.Model.myCartModel;
import com.clickandeat.finalproject5.Model.myFavorite;
import com.clickandeat.finalproject5.R;
import com.clickandeat.finalproject5.yarkaHome;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Favorite extends AppCompatActivity {

    ImageView backImg;

    myFavoriteAdapter myFavoriteAdapter;
    List<myFavorite> myFavorites = new ArrayList<>();

    FirebaseFirestore db;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        backImg = findViewById(R.id.backImg);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Favorite.super.onBackPressed();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView myFavRecyclerView = findViewById(R.id.recyclerviewF);

        myFavRecyclerView.setLayoutManager(linearLayoutManager);
        myFavoriteAdapter = new myFavoriteAdapter(this, myFavorites);
        myFavRecyclerView.setAdapter(myFavoriteAdapter);

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(".", "_");

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        auth = FirebaseAuth.getInstance();

        db.collection("User").document(auth.getCurrentUser().getUid())
                .collection("Favorites").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                String documentId = documentSnapshot.getId();
                                myFavorite item = documentSnapshot.toObject(myFavorite.class);

                                item.setDocumentId(documentId);

                                myFavorites.add(item);
                            }
                            myFavoriteAdapter.notifyDataSetChanged();

                        } else {
                            Toast.makeText(Favorite.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }


}