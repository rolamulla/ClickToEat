package com.clickandeat.finalproject5;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.clickandeat.finalproject5.Model.allMenu;
import com.clickandeat.finalproject5.Model.viewAll;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class foodDetails extends AppCompatActivity {

    ImageView imageView;
    TextView itemName, itemPrice, itemRating, itemNote, itemRestaurant, itemCategory;
    RatingBar ratingBar;

    String name, price, rating, image, note, restaurant, category, addToFav;
    int thePrice;
    TextView quantity;
    int totalQuantity = 1, totalPrice = 0;
    ImageView addItem, removeItem;
    Button addToCartBtn;
    ImageView backImg;
    ImageButton addToFaivrate;


    boolean isInMyFavorite = false;

    private FirebaseFirestore firestore;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        backImg = findViewById(R.id.backImg);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodDetails.super.onBackPressed();
            }
        });

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        price = intent.getStringExtra("price");
        rating = intent.getStringExtra("rating");
        image = intent.getStringExtra("image");
        note = intent.getStringExtra("note");
        restaurant = intent.getStringExtra("restaurant");
        category = intent.getStringExtra("category");
        addToFav = intent.getStringExtra("addToFav");


        imageView = findViewById(R.id.imageItem);
        itemName = findViewById(R.id.name);
        itemPrice = findViewById(R.id.price);
        itemRating = findViewById(R.id.rating);
        ratingBar = findViewById(R.id.ratingBar);


        quantity = findViewById(R.id.quantity);
        addItem = findViewById(R.id.plusCardBtn);
        removeItem = findViewById(R.id.minusCardBtn);
        addToCartBtn = findViewById(R.id.addToCartBtn);
        addToFaivrate = findViewById(R.id.addToFaivrate);

        itemNote = findViewById(R.id.note);
        itemRestaurant = findViewById(R.id.restaurantName);
        itemCategory = findViewById(R.id.category);


        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            checkIsFavorite();
        }


        Glide.with(getApplicationContext()).load(image).into(imageView);
        itemName.setText(name);
        itemPrice.setText(price);
        itemRating.setText(rating);
        itemNote.setText(note);
        itemRestaurant.setText(restaurant);
        itemCategory.setText(category);
        ratingBar.setRating(Float.parseFloat(rating));

        thePrice = Integer.parseInt(itemPrice.getText().toString());

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (totalQuantity < 10) {
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = thePrice * totalQuantity;
                }
            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (totalQuantity > 1) {
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = thePrice * totalQuantity;

                }
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity == 1) {
                    totalPrice = thePrice;

                }
                addToCart();
            }
        });

        addToFaivrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(auth.getCurrentUser() == null ){
//                    Toast.makeText(foodDetails.this,"You're not logged in", Toast.LENGTH_SHORT).show();
//                }else{
//                    if(isInMyFavorite){
//                        removeFromFavorite(foodDetails.this,auth.getCurrentUser().getUid());
//
//                    }else{
//                        addToFavorite(foodDetails.this,auth.getCurrentUser().getUid());
//                    }
//
//                }
                addToFavorite();
            }
        });


    }

    private void addToCart() {
        String saveCurrentDate, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String, Object> cartMap = new HashMap<>();

        cartMap.put("name", itemName.getText().toString());
        cartMap.put("price", itemPrice.getText().toString());
        cartMap.put("currentDate", saveCurrentDate);
        cartMap.put("currentTime", saveCurrentTime);
        cartMap.put("quantity", quantity.getText().toString());
        cartMap.put("totalPrice", totalPrice);
        cartMap.put("resturant", itemRestaurant.getText().toString());
        cartMap.put("imageView", imageView.toString());


        firestore.collection("User").document(auth.getCurrentUser().getUid())
                .collection("addToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {

                Toast.makeText(foodDetails.this, "Added To Cart", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    public void addToFavorite() {


        HashMap<String, Object> FavMap = new HashMap<>();
        FavMap.put("name", itemName.getText().toString());
        FavMap.put("price", itemPrice.getText().toString());
        FavMap.put("resturant", itemRestaurant.getText().toString());
        FavMap.put("imageView", imageView.toString());
        FavMap.put("rating", itemRating.getText().toString());


        firestore.collection("User").document(auth.getCurrentUser().getUid())
                .collection("Favorites").add(FavMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(foodDetails.this, "Added To favorites ...", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(foodDetails.this, "failed to add to favorite due to" + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
//            database.child(FirebaseAuth.getInstance().getUid())
//                    .child("Favorites").child(itemId).setValue(FavMap)
//                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void unused) {
//
//                            Toast.makeText(context,"Added to favorites ..",Toast.LENGTH_SHORT).show();
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//
//                    Toast.makeText(context ,"failed to add to favorite due to" + e.getMessage(),Toast.LENGTH_SHORT).show();
//                }
//            });


    public void checkIsFavorite() {

//        firestore.collection("User").document(auth.getCurrentUser().getUid())
//                .collection("Favorites").document(auth.getCurrentUser().getUid()).addValueEventListener()
//
//        if ( isInMyFavorite == true){
//            firestore.collection("User").document(auth.getCurrentUser().getUid())
//                    .collection("Favorites").document(auth.getCurrentUser().getUid())
//                   .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//
//                    for(DocumentSnapshot documentSnapshot :task.getResult().getDocuments()){
//                        viewAll viewAll = documentSnapshot.toObject(viewAll.class);
//                        viewAllList.add(viewAll);
//                        view_All_Adapter.notifyDataSetChanged();
//
//
//                    }
//
//                }
//            });
//
//        }
//
//        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Users");
//
//        database.child(auth.getUid()).child("Favorites").child(auth.getCurrentUser().getUid())
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                        isInMyFavorite = snapshot.exists();
//                        if (isInMyFavorite) {
//
//                            addToFaivrate.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    if (v.isSelected()) {
//
//                                        removeFromFavorite(foodDetails.this, auth.getCurrentUser().getUid());
//
//                                    } else {
//                                        addToFavorite(foodDetails.this, auth.getCurrentUser().getUid());
//                                    }
//                                }
//                            });
//                        } else {
//                            addToFaivrate.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    if (v.isSelected()) {
//                                        addToFavorite(foodDetails.this, auth.getCurrentUser().getUid());
//                                    } else {
//                                        removeFromFavorite(foodDetails.this, auth.getCurrentUser().getUid());
//                                    }
//                                }
//                            });
//
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });

    }

//
//    public void removeFromFavorite() {
//
//
//        firestore.collection("User").document(auth.getCurrentUser().getUid())
//                .collection("Favorites").document(myFa)
//                .delete().addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//
//                if (task.isSuccessful()) {
//                    cartModelList.remove(cartModelList.get(position));
//                    notifyDataSetChanged();
//                    Toast.makeText(context, "Your Item Deleted !", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(context, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Users");
//
//        database.child(FirebaseAuth.getInstance().getUid())
//                .child("Favorites").child(itemId).removeValue()
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//
//                        Toast.makeText(context, "Removed from favorites ..", Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//                Toast.makeText(context, "failed to remove from favorite due to" + e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//}


}