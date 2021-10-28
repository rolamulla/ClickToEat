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
import com.clickandeat.finalproject5.Model.viewAll;
import com.clickandeat.finalproject5.R;

import java.util.List;

public class viewAllAdapter extends RecyclerView.Adapter<viewAllAdapter.viewAllViewHolder> {
    Context context;
    List<viewAll> viewAllList;

    public viewAllAdapter(Context context, List<viewAll> viewAllList) {
        this.context = context;
        this.viewAllList = viewAllList;
    }

    @NonNull
    @Override
    public viewAllAdapter.viewAllViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_all_item, parent, false);

        return new viewAllAdapter.viewAllViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewAllAdapter.viewAllViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.viewAllName.setText(viewAllList.get(position).getName());
        holder.viewAllPrice.setText("₪ "+viewAllList.get(position).getPrice());
        holder.viewAllRating.setText(viewAllList.get(position).getRating());
        holder.viewAllResturantName.setText(viewAllList.get(position).getRestaurant());

        Glide.with(context).load(viewAllList.get(position).getImage()).into(holder.viewAllImage);

//        if (viewAllList.get(position).getCategory().equals("meat")){
//
//        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, foodDetails.class);
                i.putExtra("name", viewAllList.get(position).getName());
                i.putExtra("price", viewAllList.get(position).getPrice());
                i.putExtra("rating", viewAllList.get(position).getRating());
                i.putExtra("image", viewAllList.get(position).getImage());
                i.putExtra("note", viewAllList.get(position).getNote());
                i.putExtra("restaurant", viewAllList.get(position).getRestaurant());
                i.putExtra("category", viewAllList.get(position).getCategory());


                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return viewAllList.size();
    }

    public static class viewAllViewHolder extends RecyclerView.ViewHolder{

        TextView viewAllName, viewAllResturantName, viewAllRating, viewAllPrice;
        ImageView viewAllImage;

        public viewAllViewHolder(@NonNull View itemView) {
            super(itemView);

            viewAllName = itemView.findViewById(R.id.view_all_name);
            viewAllResturantName = itemView.findViewById(R.id.view_all_resturant_name);
            viewAllRating = itemView.findViewById(R.id.view_all_rating);
            viewAllPrice = itemView.findViewById(R.id.view_all_price);
            viewAllImage = itemView.findViewById(R.id.view_all_image);
        }
    }

}
