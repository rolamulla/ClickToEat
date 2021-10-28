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
import com.clickandeat.finalproject5.Model.Recommended;
import com.clickandeat.finalproject5.foodDetails;
import com.clickandeat.finalproject5.R;

import java.util.List;

public class recommendedAdapter extends RecyclerView.Adapter<recommendedAdapter.RecommendedViewHolder> {

    private Context context;
    private List<Recommended> recommendedList;

    public recommendedAdapter(Context context, List<Recommended> recommendedList) {
        this.context = context;
        this.recommendedList = recommendedList;
    }

    @NonNull
    @Override
    public RecommendedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recommended_recycler_items, parent, false);
        return new RecommendedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.recommendedName.setText(recommendedList.get(position).getName());
        holder.recommendedRating.setText(recommendedList.get(position).getRating());
        holder.recommendedPrice.setText("₪ "+recommendedList.get(position).getPrice());

        holder.recommendedRestaurantName.setText(recommendedList.get(position).getRestaurant());

        Glide.with(context).load(recommendedList.get(position).getImage()).into(holder.recommendedImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, foodDetails.class);
                i.putExtra("name", recommendedList.get(position).getName());
                i.putExtra("price", recommendedList.get(position).getPrice());
                i.putExtra("rating", recommendedList.get(position).getRating());
                i.putExtra("image", recommendedList.get(position).getImage());

                i.putExtra("note", recommendedList.get(position).getNote());
                i.putExtra("restaurant", recommendedList.get(position).getRestaurant());
                i.putExtra("category", recommendedList.get(position).getCategory());

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recommendedList.size();
    }

    public class RecommendedViewHolder extends RecyclerView.ViewHolder{

        ImageView recommendedImage;
        TextView recommendedName, recommendedRating, recommendedRestaurantName, recommendedPrice;


        public RecommendedViewHolder(@NonNull View itemView) {
            super(itemView);

            recommendedImage = itemView.findViewById(R.id.recommended_image);
            recommendedName = itemView.findViewById(R.id.recommended_name);
            recommendedRating = itemView.findViewById(R.id.recommended_rating);
            recommendedPrice = itemView.findViewById(R.id.recommended_price);
            recommendedRestaurantName = itemView.findViewById(R.id.restaurant_Name);

        }
    }
}