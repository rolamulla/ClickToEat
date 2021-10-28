package com.clickandeat.finalproject5.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.disklrucache.DiskLruCache;
import com.clickandeat.finalproject5.R;
import com.clickandeat.finalproject5.Model.myCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class myCartAdapter extends RecyclerView.Adapter<myCartAdapter.ViewHolder> {

    Context context;
    List<myCartModel> cartModelList;
    int totalTxt = 0 ;
    FirebaseFirestore firestore;
    FirebaseAuth auth;


    int thePrice;
    int totalQuantity1 = 1, totalPrice = 0 ;



    public myCartAdapter(Context context, List<myCartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_in_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.nameMana.setText(cartModelList.get(position).getName());
        holder.quntity.setText(cartModelList.get(position).getQuantity());
        holder.price.setText(cartModelList.get(position).getPrice());
        holder.totalPrice1.setText(String.valueOf(cartModelList.get(position).getTotalPrice()));

        holder.resturantName.setText(cartModelList.get(position).getResturant());

        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firestore.collection("User").document(auth.getCurrentUser().getUid())
                        .collection("addToCart").document(cartModelList.get(position).getDocumentId())
                        .delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            cartModelList.remove(cartModelList.get(position));
                            notifyDataSetChanged();
                            Toast.makeText(context, "Your Item Deleted !", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        holder.addItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                thePrice = Integer.parseInt(cartModelList.get(position).getPrice());
                if (totalQuantity1 < 10) {
                    totalQuantity1++;
                    holder.quntity.setText(String.valueOf(totalQuantity1));
                    totalPrice = (thePrice) * totalQuantity1;
                    holder.totalPrice1.setText(String.valueOf(totalPrice));
                }
                totalTxt =totalTxt + cartModelList.get(position).getTotalPrice();
                Intent intent = new Intent("MyTotalAmount");
                intent.putExtra("totalAmount",totalTxt);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                Glide.with(context).load(cartModelList.get(position).getImageView()).into(holder.imageItem);


//                double totaltxt =0.0;
//                for(myCartModel myCartModel:cartModelList){
//                    totaltxt+=myCartModel.getTotalPrice();
//
//                }
//                Intent intent = new Intent("MyTotalAmount");
//                intent.putExtra("totalAmount",totalTxt);
//

            }
        });

        holder.removeItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thePrice = Integer.parseInt(cartModelList.get(position).getPrice());
                if (totalQuantity1 > 1) {
                    totalQuantity1--;
                    holder.quntity.setText(String.valueOf(totalQuantity1));
                    totalPrice = (thePrice) * totalQuantity1;
                    holder.totalPrice1.setText(String.valueOf(totalPrice));
                }
                totalTxt =totalTxt + cartModelList.get(position).getTotalPrice();
                Intent intent = new Intent("MyTotalAmount");
                intent.putExtra("totalAmount",totalTxt);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);


            }
        });



        //pass total amount to my Cart

        totalTxt =totalTxt + cartModelList.get(position).getTotalPrice();
        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra("totalAmount",totalTxt);

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageItem ,addItem1 , removeItem1, deleteItem;
        TextView nameMana, quntity, price,totalPrice1, resturantName  ;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameMana = itemView.findViewById(R.id.nameMana);
            quntity = itemView.findViewById(R.id.quntity);
            price = itemView.findViewById(R.id.price);
            totalPrice1 = itemView.findViewById(R.id.totalPrice1);
            resturantName = itemView.findViewById(R.id.resturantName);
            imageItem = itemView.findViewById(R.id.imageItem);
            addItem1 = itemView.findViewById(R.id.plusCardBtn1);
            removeItem1 = itemView.findViewById(R.id.minusCardBtn1);
            deleteItem = itemView.findViewById(R.id.delete);


        }
    }
}
