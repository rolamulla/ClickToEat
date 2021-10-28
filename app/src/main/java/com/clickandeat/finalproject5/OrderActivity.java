package com.clickandeat.finalproject5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.clickandeat.finalproject5.Model.myCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    FirebaseFirestore firestore ;
    FirebaseAuth auth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        List<myCartModel> list = (ArrayList<myCartModel>) getIntent().getSerializableExtra("itemList");

        if( list != null && list.size()>0){
            for(myCartModel model : list){

                final HashMap<String,Object> cartMap = new HashMap<>();

                cartMap.put("name", model.getName());
                cartMap.put("price", model.getPrice());
                cartMap.put("currentDate", model.getCurrentDate());
                cartMap.put("currentTime", model.getCurrentTime());
                cartMap.put("quantity", model.getQuantity());
                cartMap.put("totalPrice", model.getTotalPrice());
                cartMap.put("resturant",model.getResturant());
                cartMap.put("imageView", model.getImageView());


                firestore.collection("User").document(auth.getCurrentUser().getUid())
                        .collection("myOrders").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                        Toast.makeText(OrderActivity.this, "Your Order Has Been Placed", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }
}