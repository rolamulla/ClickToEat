package com.clickandeat.finalproject5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.clickandeat.finalproject5.Adapter.viewAllAdapter;
import com.clickandeat.finalproject5.Model.viewAll;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class viewAllActivity extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase;
    RecyclerView recyclerView;
    viewAllAdapter view_All_Adapter;
    List <viewAll> viewAllList;
    FirebaseFirestore firestore;
    ImageView backImg;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        backImg = findViewById(R.id.backImg);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewAllActivity.super.onBackPressed();
            }
        });

        firestore = FirebaseFirestore.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();

        String category = getIntent().getStringExtra("category");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        auth = FirebaseAuth.getInstance();


        recyclerView = findViewById(R.id.view_all_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewAllList = new ArrayList<>();
        view_All_Adapter = new viewAllAdapter(this, viewAllList);

        recyclerView.setAdapter(view_All_Adapter);

        if ( category != null&& category.equalsIgnoreCase("meat")){
            firestore.collection("AllMenuYarka")
                    .whereEqualTo("category","meat").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot :task.getResult().getDocuments()){
                        viewAll viewAll = documentSnapshot.toObject(viewAll.class);
                        viewAllList.add(viewAll);
                        view_All_Adapter.notifyDataSetChanged();


                    }

                }
            });

        }
        if ( category != null&& category.equalsIgnoreCase("druze food")){
            firestore.collection("AllMenuYarka")
                    .whereEqualTo("category","druze food").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot :task.getResult().getDocuments()){
                        viewAll viewAll = documentSnapshot.toObject(viewAll.class);
                        viewAllList.add(viewAll);
                        view_All_Adapter.notifyDataSetChanged();


                    }

                }
            });

        }

        if ( category != null && category.equalsIgnoreCase("soups")){
            firestore.collection("AllMenuYarka")
                    .whereEqualTo("category","soups").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot :task.getResult().getDocuments()){
                        viewAll viewAll = documentSnapshot.toObject(viewAll.class);
                        viewAllList.add(viewAll);
                        view_All_Adapter.notifyDataSetChanged();


                    }

                }
            });
        }

        if ( category != null && category.equalsIgnoreCase("humburger")){
            firestore.collection("AllMenuYarka")
                    .whereEqualTo("category","humburger").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot :task.getResult().getDocuments()){
                        viewAll viewAll = documentSnapshot.toObject(viewAll.class);
                        viewAllList.add(viewAll);
                        view_All_Adapter.notifyDataSetChanged();


                    }

                }
            });
        }

        if ( category != null && category.equalsIgnoreCase("drinks")){
            firestore.collection("AllMenuYarka")
                    .whereEqualTo("category","drinks").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot :task.getResult().getDocuments()){
                        viewAll viewAll = documentSnapshot.toObject(viewAll.class);
                        viewAllList.add(viewAll);
                        view_All_Adapter.notifyDataSetChanged();


                    }

                }
            });
        }

        if ( category != null && category.equalsIgnoreCase("healthy")){
            firestore.collection("AllMenuYarka")
                    .whereEqualTo("category","healthy").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot :task.getResult().getDocuments()){
                        viewAll viewAll = documentSnapshot.toObject(viewAll.class);
                        viewAllList.add(viewAll);
                        view_All_Adapter.notifyDataSetChanged();


                    }

                }
            });
        }

        if ( category != null && category.equalsIgnoreCase("fish")){
            firestore.collection("AllMenuYarka")
                    .whereEqualTo("category","fish").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot :task.getResult().getDocuments()){
                        viewAll viewAll = documentSnapshot.toObject(viewAll.class);
                        viewAllList.add(viewAll);
                        view_All_Adapter.notifyDataSetChanged();


                    }

                }
            });
        }

    }
}