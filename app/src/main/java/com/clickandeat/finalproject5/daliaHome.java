package com.clickandeat.finalproject5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.clickandeat.finalproject5.Adapter2.restaurantAdapter;
import com.clickandeat.finalproject5.Adapter2.restaurantAdapterDalia;
import com.clickandeat.finalproject5.Interface.RestaurantClickListener;
import com.clickandeat.finalproject5.Adapter.allMenuAdapter;
import com.clickandeat.finalproject5.Adapter.homeAdapter;
import com.clickandeat.finalproject5.Adapter.popularAdapter;
import com.clickandeat.finalproject5.Adapter.recommendedAdapter;
import com.clickandeat.finalproject5.Model.allMenu;
import com.clickandeat.finalproject5.Model.Popular;
import com.clickandeat.finalproject5.Model.Recommended;
import com.clickandeat.finalproject5.Model.homeCategory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class daliaHome extends AppCompatActivity {

    com.clickandeat.finalproject5.Adapter.popularAdapter popularAdapter;
    com.clickandeat.finalproject5.Adapter.recommendedAdapter recommendedAdapter ;
    restaurantAdapterDalia restaurant_Adapter;
    List<Popular> popularLst=new ArrayList<>();
    List<Recommended> recommendedLst=new ArrayList<>();

    ImageView backImg;

    com.clickandeat.finalproject5.Adapter.allMenuAdapter allMenuAdapter;
    List<allMenu> allMenuLst=new ArrayList<>();

    /////Search view
//    EditText search_box;
//    private List<allMenu> viewAllMenu;
//    private RecyclerView recyclerViewSearch ;
//    private allMenuAdapter viewAllAdapter;
    FirebaseFirestore db;

    List <homeCategory> categoryList=new ArrayList<>();
    com.clickandeat.finalproject5.Adapter.homeAdapter homeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FooterY.getInstance().initFooter(this);
/////////////
        RecyclerView homeCategoryDaliaRecyclerView = findViewById(R.id.categoryListRecyclerView);

        LinearLayoutManager linearLayoutManagerCategory = new LinearLayoutManager(this);
        linearLayoutManagerCategory.setOrientation(LinearLayoutManager.HORIZONTAL);

        homeCategoryDaliaRecyclerView.setLayoutManager(linearLayoutManagerCategory);
        homeAdapter = new homeAdapter(this, categoryList);
        homeCategoryDaliaRecyclerView.setAdapter(homeAdapter);

///////////////
        RecyclerView popularRecyclerView = findViewById(R.id.popular_recycler);
        RecyclerView recommendedRecyclerView = findViewById(R.id.recommended_recycler);
        RecyclerView resListRecyclerView = findViewById(R.id.resListRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        popularRecyclerView.setLayoutManager(linearLayoutManager);
        popularAdapter = new popularAdapter(this, popularLst);
        popularRecyclerView.setAdapter(popularAdapter);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this );
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);

        recommendedRecyclerView.setLayoutManager(linearLayoutManager1);
        recommendedAdapter = new recommendedAdapter(this, recommendedLst);
        recommendedRecyclerView.setAdapter(recommendedAdapter);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this );
        linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView allMenuRecyclerView = findViewById(R.id.all_menu_recycler);

        allMenuRecyclerView.setLayoutManager(linearLayoutManager2);
        allMenuAdapter = new allMenuAdapter(this, allMenuLst);
        allMenuRecyclerView.setAdapter(allMenuAdapter);

        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(this );
        linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);

        resListRecyclerView.setLayoutManager(linearLayoutManager3);

        RestaurantClickListener listener = new RestaurantClickListener() {
            @Override
            public void onRestaurantClick(String name) {
                daliaHome.this.onRestaurantClick(name);
            }
        };

        restaurant_Adapter = new restaurantAdapterDalia(this, listener);
        resListRecyclerView.setAdapter(restaurant_Adapter);

        //String email = FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(".", "_");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        reference.child("1").child("allMenu").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()) {
                    allMenu p=ds.getValue(allMenu.class);
                    allMenuLst.add(p);
                }
                allMenuAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        reference.child("1").child("popular").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()) {
                    Popular p=ds.getValue(Popular.class);
                    popularLst.add(p);
                }
                popularAdapter.notifyDataSetChanged();
            }

            //   DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        reference.child("1").child("recommended").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()) {
                    Recommended p=ds.getValue(Recommended.class);
                    recommendedLst.add(p);
                }
                recommendedAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        backImg = findViewById(R.id.backImg);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daliaHome.super.onBackPressed();
            }
        });

        this.db = FirebaseFirestore.getInstance();

        db.collection("homeCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if( task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                homeCategory homeCategory = document.toObject(homeCategory.class);
                                categoryList.add(homeCategory);
                                homeAdapter.notifyDataSetChanged();
                            }
                        }else{
                            Toast.makeText(daliaHome.this,"Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


        //search
        //search_box = findViewById(R.id.search_rec);
//        recyclerViewSearch.setLayoutManager((new LinearLayoutManager(getApplicationContext())));
//        recyclerViewSearch.setAdapter(viewAllAdapter);
//        recyclerViewSearch.setHasFixedSize(true);
//        search_box.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//                if (s.toString().isEmpty()){
//                    viewAllMenu.clear();
//                    viewAllAdapter.notifyDataSetChanged();
//                }else{
//                    searchProduct(s.toString());
//                }
//            }
//        });
//
//    }
//
//    private void searchProduct(String type) {
//
//        if( !type.isEmpty()){
//        //    db.collection();
//        }
    }

    public void onRestaurantClick(String name){
        Log.w("FODA", "clicked on restaurant name="+name);
        Intent intent = new Intent(this, finalPageDalia.class);
        intent.putExtra("name", name);
        startActivity(intent);
    }
}