package com.clickandeat.finalproject5.Adapter2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.clickandeat.finalproject5.Model.restaurantDomain;
import com.clickandeat.finalproject5.Interface.RestaurantClickListener;
import com.clickandeat.finalproject5.R;

import java.util.ArrayList;

public class restaurantAdapterDalia extends RecyclerView.Adapter<restaurantAdapterDalia.ViewHolderDalia> {
    ArrayList<restaurantDomain> restaurant_Domains;
    Context context;
    RestaurantClickListener listener;
    public restaurantAdapterDalia(Context context) {
        this.context= context;
        restaurant_Domains = new ArrayList<restaurantDomain>();

        restaurantDomain cat1 = new restaurantDomain();
        cat1.setTitle("Coffe B");
        cat1.setPic(ContextCompat.getDrawable(this.context, R.drawable.coffeb));
        restaurant_Domains.add(cat1);

        restaurantDomain cat2 = new restaurantDomain();
        cat2.setTitle("Crunch");
        cat2.setPic(ContextCompat.getDrawable(this.context,R.drawable.crunch));
        restaurant_Domains.add(cat2);

        restaurantDomain cat3 = new restaurantDomain();
        cat3.setTitle("Rakweh");
        cat3.setPic(ContextCompat.getDrawable(this.context,R.drawable.rakweh));
        restaurant_Domains.add(cat3);

        this.restaurant_Domains = restaurant_Domains;
    }
    public restaurantAdapterDalia(Context context, RestaurantClickListener listener) {
        this(context);
        if (listener != null)
            this.listener = listener;
    }
    @NonNull
    @Override
    public ViewHolderDalia onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.viewholder_restaurant, parent, false);
        return new ViewHolderDalia(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDalia holder, int position) {

        restaurantDomain restaurantDomain = restaurant_Domains.get(position);
        final String restaurantName = restaurantDomain.getTitle();
        holder.categoryName.setText(restaurantName);
        holder.categoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restaurantAdapterDalia.this.listener.onRestaurantClick(restaurantName);
            }
        });

        holder.mainLayout.setBackground(restaurantDomain.getPic());

//        Glide.with(holder.itemView.getContext())
//                .load(drawableResourceId)
//                .into(holder.categoryPic);
    }


    @Override
    public int getItemCount() {
        return restaurant_Domains.size();
    }

    public class ViewHolderDalia extends RecyclerView.ViewHolder {
        Button categoryName;
//        ImageView categoryPic;
        ConstraintLayout mainLayout;

        public ViewHolderDalia(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}