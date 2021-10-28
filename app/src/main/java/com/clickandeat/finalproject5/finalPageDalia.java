package com.clickandeat.finalproject5;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import com.clickandeat.finalproject5.Adapter.allMenuAdapter;
import com.clickandeat.finalproject5.Adapter.popularAdapter;
import com.clickandeat.finalproject5.Model.allMenu;
import com.clickandeat.finalproject5.Model.Popular;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class finalPageDalia extends AppCompatActivity {

    popularAdapter popular_Adapter;
    List<Popular> popularLst = new ArrayList<>();
    ImageView backImg;
    Button searchPage;

    com.clickandeat.finalproject5.Adapter.allMenuAdapter allMenuAdapter;
    List<allMenu> allMenuLst = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_page_dalia);

        Bundle bundle=getIntent().getExtras();
        String resName=bundle.getString("name");
        Log.w("FODA", "heyy="+resName);

        FooterY.getInstance().initFooter(this);

        RecyclerView popularRecyclerView = findViewById(R.id.popular_recycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        popularRecyclerView.setLayoutManager(linearLayoutManager);
        popular_Adapter = new popularAdapter(this, popularLst);
        popularRecyclerView.setAdapter(popular_Adapter);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView allMenuRecyclerView = findViewById(R.id.all_menu_recycler);

        allMenuRecyclerView.setLayoutManager(linearLayoutManager2);
        allMenuAdapter = new allMenuAdapter(this, allMenuLst);
        allMenuRecyclerView.setAdapter(allMenuAdapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        reference.child("3").child(resName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    allMenu p = ds.getValue(allMenu.class);
                    allMenuLst.add(p);
                }
                allMenuAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        String priorityRes = "Priority" + resName;
        Log.w("FODA", "PPPP="+priorityRes);

        reference.child("3").child(priorityRes).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Popular p = ds.getValue(Popular.class);
                    popularLst.add(p);
                }
                popular_Adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        backImg = findViewById(R.id.backImg);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalPageDalia.super.onBackPressed();
            }
        });

        searchPage = findViewById(R.id.searchPage);
        searchPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalPageDalia.super.onBackPressed();
            }
        });
    }
}