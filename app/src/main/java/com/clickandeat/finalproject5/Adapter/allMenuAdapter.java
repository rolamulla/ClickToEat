package com.clickandeat.finalproject5.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.clickandeat.finalproject5.foodDetails;
import com.clickandeat.finalproject5.Model.allMenu;
import com.clickandeat.finalproject5.R;

import java.util.List;

public class allMenuAdapter extends RecyclerView.Adapter<allMenuAdapter.AllMenuViewHolder> {

    Context context;
    List<allMenu> allmenuList;

    public allMenuAdapter(Context context, List<allMenu> allmenuList) {
        this.context = context;
        this.allmenuList = allmenuList;
    }

    @NonNull
    @Override
    public AllMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.allmenu_recycler_items, parent, false);

        return new AllMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllMenuViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.allMenuName.setText(allmenuList.get(position).getName());
        holder.allMenuPrice.setText("₪ "+allmenuList.get(position).getPrice());
        holder.allMenuRating.setText(allmenuList.get(position).getRating());
        holder.allMenuResturantName.setText(allmenuList.get(position).getRestaurant());

        Glide.with(context).load(allmenuList.get(position).getImage()).into(holder.allMenuImage);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, foodDetails.class);
                i.putExtra("name", allmenuList.get(position).getName());
                i.putExtra("price", allmenuList.get(position).getPrice());
                i.putExtra("rating", allmenuList.get(position).getRating());
                i.putExtra("image", allmenuList.get(position).getImage());

                i.putExtra("note", allmenuList.get(position).getNote());
                i.putExtra("restaurant", allmenuList.get(position).getRestaurant());
                i.putExtra("category", allmenuList.get(position).getCategory());


                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allmenuList.size();
    }

    public static class AllMenuViewHolder extends RecyclerView.ViewHolder{

        TextView allMenuName, allMenuResturantName, allMenuRating, allMenuPrice;
        ImageView allMenuImage;

        public AllMenuViewHolder(@NonNull View itemView) {
            super(itemView);

            allMenuName = itemView.findViewById(R.id.all_menu_name);
            allMenuResturantName = itemView.findViewById(R.id.all_menu_resturant_name);
            allMenuRating = itemView.findViewById(R.id.all_menu_rating);
            allMenuPrice = itemView.findViewById(R.id.all_menu_price);
            allMenuImage = itemView.findViewById(R.id.all_menu_image);
        }
    }
}