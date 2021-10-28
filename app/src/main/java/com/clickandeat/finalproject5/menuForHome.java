package com.clickandeat.finalproject5;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.clickandeat.finalproject5.Adapter.allMenuAdapter;
import com.clickandeat.finalproject5.Model.allMenu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class menuForHome extends AppCompatActivity {
    com.clickandeat.finalproject5.Adapter.allMenuAdapter allMenuAdapter;
    List<allMenu> allMenuLst=new ArrayList<>();
    ImageView backImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_for_home);

        backImg = findViewById(R.id.backImg);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuForHome.super.onBackPressed();
            }
        });

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this );
        linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView allMenuRecyclerView = findViewById(R.id.all_menu_recycler);

        allMenuRecyclerView.setLayoutManager(linearLayoutManager2);
        allMenuAdapter = new allMenuAdapter(this, allMenuLst);
        allMenuRecyclerView.setAdapter(allMenuAdapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        reference.child("0").child("allMenu").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()) {
                    allMenu p=ds.getValue(allMenu.class);
                    //Toast.makeText(yarkaHome.this,p.getName(),Toast.LENGTH_LONG).show();
                    allMenuLst.add(p);
                }
                allMenuAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}