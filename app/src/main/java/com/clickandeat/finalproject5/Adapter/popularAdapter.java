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
import com.clickandeat.finalproject5.Model.Popular;
import com.clickandeat.finalproject5.foodDetails;
import com.clickandeat.finalproject5.R;

import java.util.List;

public class popularAdapter extends RecyclerView.Adapter<popularAdapter.PopularViewHolder> {

    private Context context;
    private List<Popular> popularList;


    public popularAdapter(Context context, List<Popular> popularList) {
        this.context = context;
        this.popularList = popularList;
    }


    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_recycler_items, parent, false);
        return new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull popularAdapter.PopularViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Popular popular = popularList.get(position);
        //Toast.makeText(context,position+" ",Toast.LENGTH_LONG).show();

        //set values
        holder.popularName.setText(popular.getName());
        // holder.tvText.setText(post.getText());
        //  holder.popularName.setText(popularList.get(position).getName());
        Glide.with(context).load(popularList.get(position).getImage()).into(holder.popularImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, foodDetails.class);
                i.putExtra("name", popularList.get(position).getName());
                i.putExtra("price", popularList.get(position).getPrice());
                i.putExtra("rating", popularList.get(position).getRating());
                i.putExtra("image", popularList.get(position).getImage());


                i.putExtra("note", popularList.get(position).getNote());
                i.putExtra("restaurant", popularList.get(position).getRestaurant());
                i.putExtra("category", popularList.get(position).getCategory());

                context.startActivity(i);
            }
        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, viewAllActivity.class);
//                intent.putExtra("type", popularList.get(position).getCategory());
//                context.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return popularList.size();
    }

    public  static class PopularViewHolder extends RecyclerView.ViewHolder{

        ImageView popularImage;
        TextView popularName;

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);

            popularImage = itemView.findViewById(R.id.popular_image);
            popularName = itemView.findViewById(R.id.popular_name);

        }
    }
}