package com.clickandeat.finalproject5;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.clickandeat.finalproject5.Adapter.viewAllAdapter;
import com.clickandeat.finalproject5.Adapter2.restaurantAdapter;
import com.clickandeat.finalproject5.Interface.RestaurantClickListener;
import com.clickandeat.finalproject5.Adapter.allMenuAdapter;
import com.clickandeat.finalproject5.Adapter.homeAdapter;
import com.clickandeat.finalproject5.Adapter.popularAdapter;
import com.clickandeat.finalproject5.Adapter.recommendedAdapter;
import com.clickandeat.finalproject5.Model.allMenu;
import com.clickandeat.finalproject5.Model.Popular;
import com.clickandeat.finalproject5.Model.Recommended;
import com.clickandeat.finalproject5.Model.homeCategory;
import com.clickandeat.finalproject5.Model.viewAll;
import com.clickandeat.finalproject5.Settings.Favorite;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class yarkaHome extends AppCompatActivity {

   popularAdapter popularAdapter;
   recommendedAdapter recommendedAdapter ;
    restaurantAdapter restaurant_Adapter;
    List<Popular> popularLst=new ArrayList<>();
    List<Recommended> recommendedLst=new ArrayList<>();

    ImageView backImg;

    allMenuAdapter allMenuAdapter;
    List<allMenu> allMenuLst=new ArrayList<>();

    /////Search view
    EditText search_box;
     List<viewAll> viewAllList;
     RecyclerView recyclerViewSearch ;
     viewAllAdapter viewAllAdapter;

    FirebaseFirestore db;

    List <homeCategory> categoryList=new ArrayList<>();
    homeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FooterY.getInstance().initFooter(this);
/////////////
        RecyclerView homeCategoryYarkaRecyclerView = findViewById(R.id.categoryListRecyclerView);

        LinearLayoutManager linearLayoutManagerCategory = new LinearLayoutManager(this);
        linearLayoutManagerCategory.setOrientation(LinearLayoutManager.HORIZONTAL);

        homeCategoryYarkaRecyclerView.setLayoutManager(linearLayoutManagerCategory);
        homeAdapter = new homeAdapter(this, categoryList);
        homeCategoryYarkaRecyclerView.setAdapter(homeAdapter);

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
                yarkaHome.this.onRestaurantClick(name);
            }
        };

        this.db = FirebaseFirestore.getInstance();

        restaurant_Adapter = new restaurantAdapter(this, listener);
        resListRecyclerView.setAdapter(restaurant_Adapter);



        db.collection("PopularYarka").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Popular popular = document.toObject(Popular.class);
                                popularLst.add(popular);
                                popularAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(yarkaHome.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


        db.collection("RecommendedYarka").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Recommended recommended = document.toObject(Recommended.class);
                                recommendedLst.add(recommended);
                                recommendedAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(yarkaHome.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

        backImg = findViewById(R.id.backImg);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yarkaHome.super.onBackPressed();
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
                            Toast.makeText(yarkaHome.this,"Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


        //search
        recyclerViewSearch = findViewById(R.id.search_rec);
        search_box = findViewById(R.id.search_box);
        viewAllList= new ArrayList<>();
        viewAllAdapter =new viewAllAdapter(this, viewAllList);
        recyclerViewSearch.setLayoutManager((new LinearLayoutManager(this)));
        recyclerViewSearch.setAdapter(viewAllAdapter);
        recyclerViewSearch.setHasFixedSize(true);
        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().isEmpty()){
                    viewAllList.clear();
                    viewAllAdapter.notifyDataSetChanged();
                }else{
                    searchProduct(s.toString());
                }
            }
        });

    }

    private void searchProduct(String type) {

        if( !type.isEmpty()){
            db.collection("allMenu").whereEqualTo("category",type).get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    if(task.isSuccessful() && task.getResult()!=null){
                        viewAllList.clear();
                        viewAllAdapter.notifyDataSetChanged();
                        for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                            viewAll viewAll = documentSnapshot.toObject(viewAll.class);
                            viewAllList.add(viewAll);
                            viewAllAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }
    }


    public void onRestaurantClick(String name){
        Log.w("FODA", "clicked on restaurant name="+name);
        Intent intent = new Intent(this, finalPageYarka.class);
        intent.putExtra("name", name);
        startActivity(intent);
    }
}